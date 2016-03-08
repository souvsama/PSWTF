package com.guidewire.pstesting.policycenter.administration;

import com.guidewire.pstesting.TabMenu;
import com.guidewire.pstesting.ScreenObjectController;
import org.openqa.selenium.By;

public class AdministrationTabMenu extends TabMenu {
    public static final String TAB_ID = "TabBar:AdminTab";

    static final By usersAndSecurityLocator = By.id(TAB_ID + "Admin_UsersAndSecurity-textEl");

    public AdministrationTabMenu(ScreenObjectController controller) {
        super(controller, By.id("TabBar:AdminTab"), usersAndSecurityLocator);
    }
}
