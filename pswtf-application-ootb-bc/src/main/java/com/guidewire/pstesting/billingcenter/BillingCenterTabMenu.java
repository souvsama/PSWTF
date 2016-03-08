package com.guidewire.pstesting.billingcenter;

import com.guidewire.pstesting.TabMenu;
import com.guidewire.pstesting.ScreenObjectController;
import org.openqa.selenium.By;

public abstract class BillingCenterTabMenu extends TabMenu {
    public static final String RESOURCE_BASE_NAME = "locale.bc";

    protected BillingCenterTabMenu(ScreenObjectController controller, By tabLocator, By tabContains) {
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
