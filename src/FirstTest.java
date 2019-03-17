import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class FirstTest
{
    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestDevice");
        capabilities.setCapability("platformVersion", "6.0.1");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", "/Users/haurova/Desktop/JavaAppiumAutomation/apks/org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

  /* @Test
    public void firstTest()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Skip')]"),
                "Cannot find 'Skip' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                15
        );

        waitForElementAndClear(
                By.xpath("//*[contains(@text,'Java')]"),
                "Cannot find and clear search input",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc=\"Clear query\"]"),
                "Cannot find X to cancel search",
                15
        );

        waitForElementNotPresent(
                By.xpath("//android.widget.ImageView[@content-desc=\"Clear query\"]"),
                "X is still presented on the page",
                5
        );

        waitForElementAndClear(
                By.xpath("(//*[contains(@text,'Search…')]\")"),
                "Cannot find search input",
                15
        );
    }

     @Test
    public void testCancelSearch()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Skip')]"),
                "Cannot find 'Skip' button",
                5
        );

        waitForElementAndClick(
                By.id("org.wikipedia:id/search_container"),
                "Cannot find 'Wikipedia search' input",
                30
        );

        waitForElementAndSendKeys(
                By.id("org.wikipedia:id/search_container"),
                "Ja",
                "Cannot find search input",
                15
        );



        waitForElementNotPresent(
                By.id("org.wikipedia:id/search_close_btn"),
                "X is still presented on the page",
                5
        );

    } */

    @Test
    public void testCompareArticleTitle()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Skip')]"),
                "Cannot find 'Skip' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                15
        );

        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_header_image_gradient']"),
                "Cannot find the article",
                15
        );

        WebElement title_element = waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_header_image_gradient']"),
                "Cannot find the article title",
                15
        );

        String article_title = title_element.getAttribute( "text");

        Assert.assertEquals(
                "We see unexpected title!",
                "Java (programming language)",
                article_title
        );
    }

    @Test
    public void testSwipeArticle() {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Skip')]"),
                "Cannot find 'Skip' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Appium",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_title'][@text='Appium']"),
                "Cannot find 'Appium' article in search",
                15
        );

        waitForElementNotPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/view_page_header_image_gradient']"),
                "Cannot find the article",
                15
        );

        swipeUpToFindElement(
                By.xpath("//*[@text='View page in browser']"),
                "Cannot find the end of the article",
                20

        );

    }

    @Test
    public void saveFirstArticleToMyList()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Skip')]"),
                "Cannot find 'Skip' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Java",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_container']//*[@text='Object-oriented programming language']"),
                "Cannot find 'Object-oriented programming language' topic searching by Java",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='Add this article to a reading list']"),
                "Cannot find button 'Save to reading list'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/onboarding_button']"),
                "Cannot find 'Got it' button in onboarding window",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/create_button']"),
                "Cannot find 'Create new list' button",
                5
        );

        String name_of_folder = "Learning programming";

        waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/text_input']"),
                name_of_folder,
                "Cannot enter text to the name of the list field",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='android:id/button1']"),
                "Cannot press OK button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'Back' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find 'My lists' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='" + name_of_folder + "']"),
                "Cannot find the list",
                5
        );

        swipeElementToLeft(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot find saved article"
        );

        waitForElementNotPresent(
                By.xpath("//android.widget.TextView[@text='Java (programming language)']"),
                "Cannot delete saved article (not found)",
                15
        );


    }

    @Test
    public void testAmountOfNotEmptySearch()
    {
        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Skip')]"),
                "Cannot find 'Skip' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        String search_line = "Linkin park discography";

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                search_line,
                "Cannot find search input",
                5
        );

        String search_result_locator = "//*[@resource-id='org.wikipedia:id/search_results_list']//*[@class='android.view.ViewGroup']";

        waitForElementPresent(
                By.xpath(search_result_locator),
                "Cannot find anything by request " + "'" + search_line + "'",
                15
        );

        int amount_of_search_results = getAmountOfElements(
                By.xpath(search_result_locator)
        );

        Assert.assertTrue(
                "We found too few results",
                amount_of_search_results > 0
        );

        System.out.println(amount_of_search_results);
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
    }

    private WebElement waitForElementPresent(By by, String error_message)
    {
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    private boolean waitForElementNotPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.invisibilityOfElementLocated(by)
        );

    }

    private WebElement waitForElementAndClear(By by, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.clear();
        return element;
    }

    protected void swipeUp(int timeOfSwipe)
    {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int start_y = (int) (size.height * 0.8);
        int end_y = (int) (size.height * 0.2);


        action
                .press(x, start_y)
                .waitAction(timeOfSwipe)
                .moveTo(x, end_y)
                .release()
                .perform();
    }

    protected void swipeUpQuick()
    {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String error_message, int max_swipes)
    {
        int already_swiped = 0;
        while (driver.findElements(by).size() == 0) {
            if (already_swiped > max_swipes) {
                waitForElementPresent(by, "Cannot find element by swiping up. \n" + error_message, 0);
                return;
            }

            swipeUpQuick();
            ++already_swiped;
        }
    }

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                10);
        int left_x = element.getLocation().getX();
        int right_x = left_x + element.getSize().getWidth();
        int upper_y = element.getLocation().getY();
        int lower_y = upper_y + element.getSize().getHeight();
        int middle_y = (upper_y + lower_y) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(right_x, middle_y)
                .waitAction(300)
                .moveTo(left_x, middle_y)
                .release()
                .perform();

    }

    private int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return ((List) elements).size();
    }
}
