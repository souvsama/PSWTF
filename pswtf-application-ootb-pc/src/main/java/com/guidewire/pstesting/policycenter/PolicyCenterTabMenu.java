package com.guidewire.pstesting.policycenter;

import com.guidewire.pstesting.TabMenu;
import com.guidewire.pstesting.ScreenObjectController;
import org.openqa.selenium.By;

public abstract class PolicyCenterTabMenu extends TabMenu {
    public static final String RESOURCE_BASE_NAME = "locale.pc";

    protected PolicyCenterTabMenu(ScreenObjectController controller, By tabLocator) {
        super(controller, tabLocator);
    }

    protected PolicyCenterTabMenu(ScreenObjectController controller, By tabLocator, By tabContains) {
        super(controller, tabLocator, tabContains);
    }

    /**
     * Returns the base name of the resource bundle
     *
     * @return the base name of the resource bundle, a fully qualified class name
     */
    public String getResourceBaseName() {
        return RESOURCE_BASE_NAME;
    }
}
