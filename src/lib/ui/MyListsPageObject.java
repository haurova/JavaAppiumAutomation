package lib.ui;

import io.appium.java_client.AppiumDriver;

public class MyListsPageObject extends MainPageObject
{
    public static final String
            FOLDER_BY_NAME_TML = "xpath://android.widget.TextView[@text='{FOLDER_NAME}']",
            ARTICLE_BY_TITLE_TML = "xpath://android.widget.TextView[@text='{TITLE}']",
            ARTICLE_ID = "id:org.wikipedia:id/page_list_item_title";

    private static String getFolderXpathByName(String name_of_folder)
    {
        return FOLDER_BY_NAME_TML.replace("{FOLDER_NAME}", name_of_folder);
    }

    private static String getSavedArticleXpathByTitle(String article_title)
    {
        return ARTICLE_BY_TITLE_TML.replace("{TITLE}", article_title);
    }

    public MyListsPageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public void openFolderByName(String name_of_folder)

    {
        String folder_name_xpath = getFolderXpathByName(name_of_folder);
        this.waitForElementAndClick(
                folder_name_xpath,
                "Cannot find folder by name '" + name_of_folder + "'",
                5
        );
    }

    public void waitForArticleToAppearByTitle(String article_title)
    {
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementPresent(
                article_title_xpath,
                "Cannot find saved article with title '" + article_title + "'",
                15
        );
    }

    public void waitForArticleToDissapearByTitle(String article_title)
    {
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.waitForElementNotPresent(
                article_title_xpath,
                "Saved article still present with title '" + article_title + "'",
                15
        );
    }

    public void swipeByArticleToDelete(String article_title)
    {
        this.waitForArticleToAppearByTitle(article_title);
        String article_title_xpath = getSavedArticleXpathByTitle(article_title);
        this.swipeElementToLeft(
                article_title_xpath,
                "Cannot find saved article"
        );
        this.waitForArticleToDissapearByTitle(article_title);
    }

    public String getTitleOfTheArticleFromTheList()
    {
        return this.waitForElementAndGetAttribute(
                ARTICLE_ID,
                "text",
                "Cannot find title of article",
                15
        );
    }


}
