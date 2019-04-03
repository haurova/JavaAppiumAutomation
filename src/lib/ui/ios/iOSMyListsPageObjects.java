package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.MyListsPageObject;

public class iOSMyListsPageObjects extends MyListsPageObject {

    static
    {
        ARTICLE_BY_TITLE_TML = "xpath://XCUIElementTypeLink[@name='{TITLE}']";
        ARTICLE_ID = "xpath://XCUIElementTypeLink";
    }

    public iOSMyListsPageObjects(AppiumDriver driver)
    {
        super(driver);
    }
}