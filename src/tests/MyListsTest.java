package tests;

import lib.CoreTestCase;
import lib.Platform;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import lib.ui.factories.ArticlePageObjectFactory;
import lib.ui.factories.MyListsPageObjectFactory;
import lib.ui.factories.NavigationUIFactory;
import lib.ui.factories.SearchPageObjectFactory;
import org.junit.Test;

public class MyListsTest extends CoreTestCase
{
    private static String name_of_folder = "Learning programming";

    @Test
    public void testSaveFirstArticleToMyList()
    {

        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickbyArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);
        ArticlePageObject.waitForTitleElement();

        if(Platform.getInstance().isAndroid()){
            ArticlePageObject.addArticleToNewList(name_of_folder);
        } else {
            ArticlePageObject.addArticlesToMySaved();
        }

        ArticlePageObject.closeArticle();
        if(Platform.getInstance().isAndroid()) {
            ArticlePageObject.closeSyncPopup();
        }

        NavigationUI NavigationUI = NavigationUIFactory.get(driver);

        NavigationUI.clickMyLists();

        if(Platform.getInstance().isIOS()) {
            ArticlePageObject.closeSyncPopup();
        }

        MyListsPageObject MyListPageObject = MyListsPageObjectFactory.get(driver);

        if(Platform.getInstance().isAndroid()){
            MyListPageObject.openFolderByName(name_of_folder);
        }

        if(Platform.getInstance().isAndroid()){
            String article_title = ArticlePageObject.getArticleTitle();
            MyListPageObject.swipeByArticleToDelete(article_title);
        } else {
            String article_title = MyListPageObject.getTitleOfTheArticleFromTheList();
            MyListPageObject.swipeByArticleToDelete(article_title);
        }

    }

    /** Ex5: Тест: сохранение двух статей */
    @Test
    public void testSaveTwoArticlesAndDeleteOne()
    {
        String name_of_first_article = "Foals (band)";
        String name_of_second_article = "Interpol (band)";
        String name_of_folder = "Indie-rock bands";

        /* Пропуск онбординга и поиск первой статьи */
        SearchPageObject SearchPageObject = SearchPageObjectFactory.get(driver);

        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Foals");
        SearchPageObject.clickbyArticleWithSubstring("Foals (band)");

        /* Создание нового списка и добавление первой статьи в него */
        ArticlePageObject ArticlePageObject = ArticlePageObjectFactory.get(driver);

        ArticlePageObject.addArticleToNewList(name_of_folder);
        ArticlePageObject.closeArticle();

        /* Поиск второй статьи и добавление в только что созданный список */
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Interpol");
        SearchPageObject.clickbyArticleWithSubstring("Interpol (band)");
        ArticlePageObject.addArticleToExistingList(name_of_folder);
        ArticlePageObject.closeArticle();

        /* Переход в списки для чтения и открытие созданного списка */
        NavigationUI NavigationUI = NavigationUIFactory.get(driver);

        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = MyListsPageObjectFactory.get(driver);

        MyListsPageObject.openFolderByName(name_of_folder);

        /* Удаление первой статьи и проверка, что на странице осталась вторая */
        MyListsPageObject.swipeByArticleToDelete(name_of_first_article);
        MyListsPageObject.waitForArticleToAppearByTitle(name_of_second_article);
        String title_of_remained_article = MyListsPageObject.getTitleOfTheArticleFromTheList();

        assertEquals(
                "Wrong article left (" + title_of_remained_article + ")",
                name_of_second_article,
                title_of_remained_article
        );

    }
}
