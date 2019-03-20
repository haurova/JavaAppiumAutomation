package tests;

import lib.CoreTestCase;
import lib.ui.ArticlePageObject;
import lib.ui.MyListsPageObject;
import lib.ui.NavigationUI;
import lib.ui.SearchPageObject;
import org.junit.Test;

public class MyListsTest extends CoreTestCase
{
    @Test
    public void testSaveFirstArticleToMyList()
    {
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Java");
        SearchPageObject.clickbyArticleWithSubstring("Object-oriented programming language");

        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.waitForTitleElement();

        String article_title = ArticlePageObject.getArticleTitle();
        String name_of_folder = "Learning programming";

        ArticlePageObject.addArticleToNewList(name_of_folder);
        ArticlePageObject.closeArticle();

        NavigationUI NavigationUI = new NavigationUI(driver);

        NavigationUI.clickMyLists();

        MyListsPageObject MyListPageObject = new MyListsPageObject(driver);
        MyListPageObject.openFolderByName(name_of_folder);
        MyListPageObject.swipeByArticleToDelete(article_title);

    }

    /** Ex5: Тест: сохранение двух статей */
    @Test
    public void testSaveTwoArticlesAndDeleteOne()
    {
        String name_of_first_article = "Foals (band)";
        String name_of_second_article = "Interpol (band)";
        String name_of_folder = "Indie-rock bands";

        /* Пропуск онбординга и поиск первой статьи */
        SearchPageObject SearchPageObject = new SearchPageObject(driver);

        SearchPageObject.skipOnboarding();
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Foals");
        SearchPageObject.clickbyArticleWithSubstring("Foals (band)");

        /* Создание нового списка и добавление первой статьи в него */
        ArticlePageObject ArticlePageObject = new ArticlePageObject(driver);

        ArticlePageObject.addArticleToNewList(name_of_folder);
        ArticlePageObject.closeArticle();

        /* Поиск второй статьи и добавление в только что созданный список */
        SearchPageObject.initSearchInput();
        SearchPageObject.typeSearchLine("Interpol");
        SearchPageObject.clickbyArticleWithSubstring("Interpol (band)");
        ArticlePageObject.addArticleToExistingList(name_of_folder);
        ArticlePageObject.closeArticle();

        /* Переход в списки для чтения и открытие созданного списка */
        NavigationUI NavigationUI = new NavigationUI(driver);

        NavigationUI.clickMyLists();

        MyListsPageObject MyListsPageObject = new MyListsPageObject(driver);

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
