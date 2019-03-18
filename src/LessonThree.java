import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.ScreenOrientation;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;
import java.util.List;

public class LessonThree
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
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.quit();
    }

    /** Ex5: Тест: сохранение двух статей */
    @Test
    public void saveTwoArticlesAndDeleteOne()
    {
        String name_of_first_article = "Foals (band)";
        String name_of_second_article = "Interpol (band)";
        String name_of_folder = "Indie-rock bands";

        /* Пропуск онбординга и сохранение первой статьи в свежесозданный список */

        waitForElementAndClick(
                    By.xpath("//*[contains(@text, 'Skip')]"),
                    "Cannot find 'Skip' button",
                    10
            );

        waitForElementAndClick(
                    By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                    "Cannot find search input",
                    5
            );

        waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    "Foals",
                    "Cannot find search input",
                    5
            );

        waitForElementAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/search_results_container']//*[@text='"+ name_of_first_article +"']"),
                    "Cannot find 'Foals (band)' topic searching by Foals",
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

        /* Возврат назад на лендинг */

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'Back' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='android:id/button2']"),
                "Cannot press 'No thanks' button in sync pop-up",
                5
        );

        /* Поиск второй статьи и добавление её в список, созданный ранее */

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Interpol",
                "Cannot find search input",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_container']//*[@text='"+ name_of_second_article +"']"),
                "Cannot find 'Interpol (band)' topic searching by Interpol",
                15
        );

        waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='Add this article to a reading list']"),
                "Cannot find button 'Save to reading list'",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@text='"+ name_of_folder +"']"),
                "Cannot find '" + name_of_folder + "' list",
                5
        );

        /* Возврат на лендинг и переход в списки для чтения */

        waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'Back' button",
                5
        );

        waitForElementAndClick(
                By.xpath("//*[@resource-id='android:id/button2']"),
                "Cannot press 'No thanks' button in sync pop-up",
                5
        );

        waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find 'My lists' button",
                10
        );

        /* Переход в созданный список и удаление первой из статей */

        waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='" + name_of_folder + "']"),
                "Cannot find '" + name_of_folder + "' list",
                10
        );

        swipeElementToLeft(
                By.xpath("//android.widget.TextView[@text='Foals (band)']"),
                "Cannot find 'Foals' article to delete"
        );

        /* К сожалению, на моей версии приложения Wikipedia ни заголовок, ни описание статьи не имеют resource-id (проверяла и Appium Viewer, и uiautomatorviewer),
        поэтому мне приходится проверять, что в списке сохранённых статей осталась именно вторая статья, а не первая */

        String titleOfTheRemainedArticle = waitForElementAndGetAttribute(
                By.id("org.wikipedia:id/page_list_item_title"),
                "text",
                "Cannot find title of article",
                15
        );

        Assert.assertEquals(
                "Wrong article left (" + titleOfTheRemainedArticle + ")",
                name_of_second_article,
                titleOfTheRemainedArticle
        );

    }

    /** Ex6: Тест: assert title */
    @Test

    public void testAssertTitle()
    {
        String name_of_article = "Foals (band)";

        /* пропуск онбординга и поиск статьи */

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Skip')]"),
                "Cannot find 'Skip' button",
                10
        );

        waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Foals",
                "Cannot find search input",
                5
        );

        /* Открытие статьи и быстрая проверка на наличие тайтла. Падает, потому что тайтл не успевает загрузиться */

        waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_container']//*[@text='"+ name_of_article +"']"),
                "Cannot find 'Foals (band)' topic searching by Foals",
                15
        );

        int amount_of_titles_on_the_page = getAmountOfElements(
                By.xpath("//*[@resource-id='heading_0']")
        );

        Assert.assertFalse(
                "No article title found",
                amount_of_titles_on_the_page == 0
        );
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(
                ExpectedConditions.presenceOfElementLocated(by)
        );
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

    protected void swipeElementToLeft(By by, String error_message)
    {
        WebElement element = waitForElementPresent(
                by,
                error_message,
                15);
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

    private String waitForElementAndGetAttribute(By by, String attribute, String error_message, long timeoutInSeconds)
    {
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        return element.getAttribute(attribute);
    }

    private int getAmountOfElements(By by)
    {
        List elements = driver.findElements(by);
        return ((List) elements).size();
    }

}
