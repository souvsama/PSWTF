package com.guidewire.pstesting.billingcenter;

import com.guidewire.pstesting.ApplicationPage;
import com.guidewire.pstesting.ScreenObjectController;
import org.openqa.selenium.By;

public class PolicySearchScreen extends BillingCenterComponent implements ApplicationPage {
    public static final String BASE_ID       = "Policies:PolicySearchScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    public PolicySearchScreen(ScreenObjectController controller) {
        super(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public PolicySearchScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }
}
