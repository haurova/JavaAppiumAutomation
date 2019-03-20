import lib.CoreTestCase;
import lib.ui.MainPageObject;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;

public class Excercises extends CoreTestCase
{

    private MainPageObject MainPageObject;

    protected void setUp() throws Exception
    {
        super.setUp();

        MainPageObject = new MainPageObject(driver);
    }

    /** Ex2: Создание метода */
    @Test
    public void testMethodCreation()
    {
        /* На моём устройстве постоянно показывается онбординг, поэтому приходится каждый раз его пропускать */
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Skip')]"),
                "Cannot find 'Skip' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Cannot find text 'Search...'",
                5
        );
    }
    /** Ex3: Тест: отмена поиска */
    @Test
    public void testSearchCancellation()
    {
        /* На моём устройстве постоянно показывается онбординг, поэтому приходится каждый раз его пропускать */
        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Skip')]"),
                "Cannot find 'Skip' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Tea",
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_container']//*[@text='Drink made from infusing boiling water with the leaves of the tea plant']"),
                "Can't find article 'Tea'",
                15
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_container']//*[@text='Person who helps others to acquire knowledge, competences or values']"),
                "Can't find article 'Teacher'",
                15
        );

        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_container']//*[@text='American political movement']"),
                "Can't find article 'Tea Party Movement'",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_close_btn']"),
                "Cannot find X to cancel search",
                5
        );

        /* Ищу изображение-плейсхолдер, отображающееся на пустой странице поиска */
        MainPageObject.waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_empty_image']"),
                "The search is not empty",
                15
        );

    }

    /** Ex5: Тест: сохранение двух статей */
    @Test
    public void testSaveTwoArticlesAndDeleteOne()
    {
        String name_of_first_article = "Foals (band)";
        String name_of_second_article = "Interpol (band)";
        String name_of_folder = "Indie-rock bands";

        /* Пропуск онбординга и сохранение первой статьи в свежесозданный список */

        MainPageObject.waitForElementAndClick(
                    By.xpath("//*[contains(@text, 'Skip')]"),
                    "Cannot find 'Skip' button",
                    10
            );

        MainPageObject.waitForElementAndClick(
                    By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                    "Cannot find search input",
                    5
            );

        MainPageObject.waitForElementAndSendKeys(
                    By.xpath("//*[contains(@text,'Search…')]"),
                    "Foals",
                    "Cannot find search input",
                    5
            );

        MainPageObject.waitForElementAndClick(
                    By.xpath("//*[@resource-id='org.wikipedia:id/search_results_container']//*[@text='"+ name_of_first_article +"']"),
                    "Cannot find 'Foals (band)' topic searching by Foals",
                    15
            );

        MainPageObject.waitForElementAndClick(
                    By.xpath("//android.widget.ImageView[@content-desc='Add this article to a reading list']"),
                    "Cannot find button 'Save to reading list'",
                    5
            );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/onboarding_button']"),
                "Cannot find 'Got it' button in onboarding window",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/create_button']"),
                "Cannot find 'Create new list' button",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[@resource-id='org.wikipedia:id/text_input']"),
                name_of_folder,
                "Cannot enter text to the name of the list field",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='android:id/button1']"),
                "Cannot press OK button",
                5
        );

        /* Возврат назад на лендинг */

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'Back' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='android:id/button2']"),
                "Cannot press 'No thanks' button in sync pop-up",
                5
        );

        /* Поиск второй статьи и добавление её в список, созданный ранее */

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Interpol",
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_container']//*[@text='"+ name_of_second_article +"']"),
                "Cannot find 'Interpol (band)' topic searching by Interpol",
                15
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageView[@content-desc='Add this article to a reading list']"),
                "Cannot find button 'Save to reading list'",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@text='"+ name_of_folder +"']"),
                "Cannot find '" + name_of_folder + "' list",
                5
        );

        /* Возврат на лендинг и переход в списки для чтения */

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.ImageButton[@content-desc='Navigate up']"),
                "Cannot find 'Back' button",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='android:id/button2']"),
                "Cannot press 'No thanks' button in sync pop-up",
                5
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.FrameLayout[@content-desc='My lists']"),
                "Cannot find 'My lists' button",
                10
        );

        /* Переход в созданный список и удаление первой из статей */

        MainPageObject.waitForElementAndClick(
                By.xpath("//android.widget.TextView[@text='" + name_of_folder + "']"),
                "Cannot find '" + name_of_folder + "' list",
                15
        );

        MainPageObject.swipeElementToLeft(
                By.xpath("//android.widget.TextView[@text='Foals (band)']"),
                "Cannot find 'Foals' article to delete"
        );

        /* К сожалению, на моей версии приложения Wikipedia ни заголовок, ни описание статьи не имеют resource-id (проверяла и Appium Viewer, и uiautomatorviewer),
        поэтому мне приходится проверять, что в списке сохранённых статей осталась именно вторая статья, а не первая */

        String titleOfTheRemainedArticle =  MainPageObject.waitForElementAndGetAttribute(
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

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Skip')]"),
                "Cannot find 'Skip' button",
                10
        );

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[contains(@text, 'Search Wikipedia')]"),
                "Cannot find search input",
                5
        );

        MainPageObject.waitForElementAndSendKeys(
                By.xpath("//*[contains(@text,'Search…')]"),
                "Foals",
                "Cannot find search input",
                5
        );

        /* Открытие статьи и быстрая проверка на наличие тайтла. Падает, потому что тайтл не успевает загрузиться */

        MainPageObject.waitForElementAndClick(
                By.xpath("//*[@resource-id='org.wikipedia:id/search_results_container']//*[@text='"+ name_of_article +"']"),
                "Cannot find 'Foals (band)' topic searching by Foals",
                15
        );

        int amount_of_titles_on_the_page =  MainPageObject.getAmountOfElements(
                By.xpath("//*[@resource-id='heading_0']")
        );

        Assert.assertFalse(
                "No article title found",
                amount_of_titles_on_the_page == 0
        );
    }
}
