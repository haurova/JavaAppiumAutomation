package lib.ui.ios;

import io.appium.java_client.AppiumDriver;
import lib.ui.NavigationUI;

public class iOSNavigationUI extends NavigationUI {
    static {
        MY_LISTS = "id:Saved";
    }

    public iOSNavigationUI(AppiumDriver driver) {
        super(driver);
    }


}
