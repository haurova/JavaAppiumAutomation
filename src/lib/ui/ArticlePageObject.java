package lib.ui;

import io.appium.java_client.AppiumDriver;
import lib.Platform;
import org.openqa.selenium.WebElement;

abstract public class ArticlePageObject extends MainPageObject
{
    protected static String
        TITLE,
        FOOTER_ELEMENT,
        READING_LIST_BUTTON,
        ONBOARDING_GOT_IT_BUTTON,
        CREATE_NEW_LIST_BUTTON,
        CREATE_NEW_LIST_NAME_FIELD,
        CREATE_NEW_LIST_OK_BUTTON,
        BACK_BUTTON,
        NO_THANKS_BUTTON_SYNC_POPUP,
        ARTICLE_TITLE;

    public ArticlePageObject(AppiumDriver driver)
    {
        super(driver);
    }

    public WebElement waitForTitleElement()
    {
        return this.waitForElementPresent(TITLE, "Cannot find article title", 15);
    }


    public String getArticleTitle()
    {
        WebElement title_element = waitForTitleElement();
        if (Platform.getInstance().isAndroid()) {
            return title_element.getAttribute("text");
        } else {
            return title_element.getAttribute("name");
        }
    }

    public void swipeToFooter()
    {
        if(Platform.getInstance().isAndroid()) {
            this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        } else {
            this.swipeUpTillElementAppear(FOOTER_ELEMENT, "Cannot find the end of article", 40);
        }
    }

    public void addArticleToNewList(String name_of_folder)
    {
        this.waitForElementAndClick(
                READING_LIST_BUTTON,
                "Cannot find button 'Save to reading list'",
                5
        );

        this.waitForElementAndClick(
                ONBOARDING_GOT_IT_BUTTON,
                "Cannot find 'Got it' button in onboarding window",
                5
        );

        this.waitForElementAndClick(
                CREATE_NEW_LIST_BUTTON,
                "Cannot find 'Create new list' button",
                5
        );

        this.waitForElementAndSendKeys(
                CREATE_NEW_LIST_NAME_FIELD,
                name_of_folder,
                "Cannot enter text to the name of the list field",
                5
        );

        this.waitForElementAndClick(
                CREATE_NEW_LIST_OK_BUTTON,
                "Cannot press OK button",
                5
        );
    }

    public void addArticleToExistingList(String name_of_folder)
    {
        this.waitForElementAndClick(
                READING_LIST_BUTTON,
                "Cannot find button 'Save to reading list'",
                5
        );

        this.waitForElementAndClick(
                "xpath://*[@text='"+ name_of_folder +"']",
                "Cannot find '" + name_of_folder + "' list",
                5
        );
    }

    public void addArticlesToMySaved() {
        this.waitForElementAndClick(READING_LIST_BUTTON, "Cannot find 'Save to reading list' button",10);
    }

    public void closeArticle()
    {
        this.waitForElementAndClick(
                BACK_BUTTON,
                "Cannot find 'Back' button",
                5
        );
    }

    public void closeSyncPopup()
    {
        this.waitForElementAndClick(
                NO_THANKS_BUTTON_SYNC_POPUP,
                "Cannot close sync pop-up",
                5
        );
    }



    public int getAmountOfTitlesOnThePage()
    {
        return this.getAmountOfElements(ARTICLE_TITLE);
    }
}
