package com.guidewire.pstesting.claimcenter.desktop;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.TabMenu;
import org.openqa.selenium.By;

public class DesktopTabMenu extends TabMenu {
    public static final String TAB_ID        = "TabBar:DesktopTab";
    public static final By PAGE_CONTAINS = By.id("DesktopActivities:DesktopActivitiesScreen:0");

    public DesktopTabMenu(ScreenObjectController controller) {
        super(controller, By.id(TAB_ID), PAGE_CONTAINS);
    }
}
