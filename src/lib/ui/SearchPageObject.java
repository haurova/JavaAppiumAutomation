package lib.ui;

import io.appium.java_client.AppiumDriver;
import org.junit.Assert;
import org.openqa.selenium.By;

public class SearchPageObject extends MainPageObject
{

    private static final String
        SKIP_BUTTON_ONBOARDING_INIT_ELEMENT = "//*[contains(@text, 'Skip')]",
        SEARCH_INIT_ELEMENT = "//*[contains(@text, 'Search Wikipedia')]",
        SEARCH_INPUT = "//*[contains(@text,'Search…')]",
        SEARCH_RESULT_BY_SUBSTRING_TPL = "//*[@resource-id='org.wikipedia:id/search_results_container']//*[@text='{SUBSTRING}']",
        SEARCH_CANCEL_BUTTON = "//*[@resource-id='org.wikipedia:id/search_close_btn']",
        SEARCH_RESULT_ELEMENT = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@class='android.view.ViewGroup']",
        NO_RESULTS_PLACEHOLDER = "//*[@resource-id='org.wikipedia:id/search_empty_image']";



    public SearchPageObject(AppiumDriver driver)
    {
        super(driver);

    }

    /* TEMPLATE METHODS */
    private static String getSearchResultElement(String subsctring)
    {
        return SEARCH_RESULT_BY_SUBSTRING_TPL.replace("{SUBSTRING}", subsctring);
    }
    /* TEMPLATE METHODS */

    public void skipOnboarding()
    {
        this.waitForElementAndClick(By.xpath(SKIP_BUTTON_ONBOARDING_INIT_ELEMENT), "Cannot find 'Skip' button", 5);
    }

    public void initSearchInput()
    {
        this.waitForElementAndClick(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find and click search init elements", 5);
        this.waitForElementPresent(By.xpath(SEARCH_INIT_ELEMENT), "Cannot find search input after clicking on it");

    }

    public void typeSearchLine(String search_line)
    {
        this.waitForElementAndSendKeys(By.xpath(SEARCH_INPUT), search_line, "Cannot find search input to type",5);

    }

    public void waitForCancelButtonAppear()
    {
     this.waitForElementPresent(By.xpath(SEARCH_CANCEL_BUTTON), "Cannot find 'X' button", 5);
    }

    public void waitForCancelButtonDisappear()
    {
        this.waitForElementNotPresent(By.xpath(SEARCH_CANCEL_BUTTON), "'X' button still displayed", 5);
    }

    public void clickCancelSearch()
    {
        this.waitForElementAndClick(By.xpath(SEARCH_CANCEL_BUTTON), "Cannot find and click 'X' button", 5);
    }

    public void waitForSearchResult(String substring)
    {
        String search_result_xpath = getSearchResultElement(substring);
        this.waitForElementPresent(By.xpath(search_result_xpath), "Cannot find search result with substring" + substring);
    }

    public void clickbyArticleWithSubstring(String substring)
    {
        String search_result_xpath = getSearchResultElement(substring);
        this.waitForElementAndClick(By.xpath(search_result_xpath), "Cannot find and click search result with substring" + substring, 10);
    }

    public int getAmountOfFoundArticles()
    {
        this.waitForElementPresent(
                By.xpath(SEARCH_RESULT_ELEMENT),
                "Cannot find anything by request",
                15
        );

        return this.getAmountOfElements(By.xpath(SEARCH_RESULT_ELEMENT));

     }

    public void waitForNoResultsPlaceholderImage()
    {
        this.waitForElementPresent(By.xpath(NO_RESULTS_PLACEHOLDER), "Search is not empty or there is no placeholder image displayed");
    }
}
