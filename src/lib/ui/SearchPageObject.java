package lib.ui;

import io.appium.java_client.AppiumDriver;

public class SearchPageObject extends MainPageObject
{

    private static final String
        SKIP_BUTTON_ONBOARDING_INIT_ELEMENT = "xpath://*[contains(@text, 'Skip')]",
        SEARCH_INIT_ELEMENT = "xpath://*[contains(@text, 'Search Wikipedia')]",
        SEARCH_INPUT = "xpath://*[contains(@text,'Search…')]",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "xpath://*[@resource-id='org.wikipedia:id/search_results_container']//*[@text='{SUBSTRING}']",
        SEARCH_CANCEL_BUTTON = "xpath://*[@resource-id='org.wikipedia:id/search_close_btn']",
        SEARCH_RESULT_ELEMENT = "xpath://*[@resource-id='org.wikipedia:id/search_results_list']//*[@class='android.view.ViewGroup']",
        NO_RESULTS_PLACEHOLDER = "xpath://*[@resource-id='org.wikipedia:id/search_empty_image']",
        SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TML = "xpath://android.widget.TextView[@text='{TITLE}']/following-sibling::android.widget.TextView[@text='{DESCRIPTION}']/..";


    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);

    }

    /* TEMPLATE METHODS */
    private static String getSearchResultElement(String subsctring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", subsctring);
    }

    private static String getSearchResultElementbyTitleAndDescription(String title, String description)
    {
        return SEARCH_RESULT_BY_TITLE_AND_DESCRIPTION_TML
                .replace("{TITLE}", title)
                .replace("{DESCRIPTION}", description);
    }
    /* TEMPLATE METHODS */

    public void skipOnboarding()
    {
        this.waitForElementAndClick(SKIP_BUTTON_ONBOARDING_INIT_ELEMENT, "Cannot find 'Skip' button", 5);
    }

    public void initSearchInput()
    {
        this.waitForElementAndClick(SEARCH_INIT_ELEMENT, "Cannot find and click search init elements", 5);
        this.waitForElementPresent(SEARCH_INIT_ELEMENT, "Cannot find search input after clicking on it");

    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(SEARCH_INPUT, search_line, "Cannot find search input to type",5);

    }

    public void waitForCancelButtonAppear()
    {
     this.waitForElementPresent(SEARCH_CANCEL_BUTTON, "Cannot find 'X' button", 5);
    }

    public void waitForCancelButtonDisappear()
    {
        this.waitForElementNotPresent(SEARCH_CANCEL_BUTTON, "'X' button still displayed", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(SEARCH_CANCEL_BUTTON, "Cannot find and click 'X' button", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getSearchResultElement(substring);
        this.waitForElementPresent(search_result_xpath, "Cannot find search result with substring" + substring);
    }

    public void waitForElementByTitleAndDescription(String title, String description)
    {
        String search_result_xpath = getSearchResultElementbyTitleAndDescription(title, description);
        this.waitForElementPresent(
                search_result_xpath,
                "Cannot find search result with title '" + title + "' " + "and description '" + description + "'");
    }

    public void clickbyArticleWithSubstring(String substring)
    {
        String search_result_xpath = getSearchResultElement(substring);
        this.waitForElementAndClick(search_result_xpath, "Cannot find and click search result with substring" + substring, 10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                SEARCH_RESULT_ELEMENT,
                "Cannot find anything by request",
                15
        );

        return this.getAmountOfElements(SEARCH_RESULT_ELEMENT);

     }

    public void waitForNoResultsPlaceholderImage()
    {
        this.waitForElementPresent(NO_RESULTS_PLACEHOLDER, "Search is not empty or there is no placeholder image displayed");
    }
}
