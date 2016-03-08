package com.guidewire.pstesting.billingcenter;

import com.guidewire.pstesting.ApplicationComponent;
import com.guidewire.pstesting.ScreenObjectController;

public abstract class BillingCenterComponent extends ApplicationComponent {
    public static final String RESOURCE_BASE_NAME = "locale.bc";

    protected BillingCenterComponent(ScreenObjectController controller) {
        super(controller);
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
