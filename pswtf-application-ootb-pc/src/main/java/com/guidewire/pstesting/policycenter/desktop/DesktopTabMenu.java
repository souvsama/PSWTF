package com.guidewire.pstesting.policycenter.desktop;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterTabMenu;
import org.openqa.selenium.By;

public class DesktopTabMenu extends PolicyCenterTabMenu {
    public static final String BASE_ID = "TabBar:DesktopTab:";

    static final By desktopTabLocator        = By.id("TabBar:DesktopTab");
    static final By desktopActivitiesLocator = By.id(BASE_ID + "Desktop_DesktopActivities-textEl");

    public DesktopTabMenu(ScreenObjectController controller) {
        super(controller, desktopTabLocator, desktopActivitiesLocator);
    }
}
