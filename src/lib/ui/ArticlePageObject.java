package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.WebElement;

public class ArticlePageObject extends MainPageObject
{
    private static final String
        TITLE = "xpath://*[@resource-id='heading_0']/android.view.View[1]",
        FOOTER_ELEMENT = "xpath://*[@text='View page in browser']",
        READING_LIST_BUTTON = "xpath://android.widget.ImageView[@content-desc='Add this article to a reading list']",
        ONBOARDING_GOT_IT_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/onboarding_button']",
        CREATE_NEW_LIST_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/create_button']",
        CREATE_NEW_LIST_NAME_FIELD = "xpath://*[@resource-id='org.wikipedia:id/text_input']",
        CREATE_NEW_LIST_OK_BUTTON = "xpath://*[@resource-id='android:id/button1']",
        BACK_BUTTON = "xpath://android.widget.ImageButton[@content-desc='Navigate up']",
        NO_THANKS_BUTTON_SYNC_POPUP = "xpath://*[@resource-id='android:id/button2']",
        ARTICLE_TITLE = "xpath://*[@resource-id='heading_0']";

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
        return title_element.getAttribute("text");
    }

    public void swipeToFooter()
    {
        this.swipeUpToFindElement(FOOTER_ELEMENT, "Cannot find the end of article", 20);
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

    public void closeArticle()
    {
        this.waitForElementAndClick(
                BACK_BUTTON,
                "Cannot find 'Back' button",
                5
        );

        this.waitForElementAndClick(
                NO_THANKS_BUTTON_SYNC_POPUP,
                "Cannot press 'No thanks' button in sync pop-up",
                5
        );
    }

    public int getAmountOfTitlesOnThePage()
    {
        return this.getAmountOfElements(ARTICLE_TITLE);
    }
}
