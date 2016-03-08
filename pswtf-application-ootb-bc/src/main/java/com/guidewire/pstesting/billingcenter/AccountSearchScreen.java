package com.guidewire.pstesting.billingcenter;

import com.guidewire.pstesting.ApplicationPage;
import com.guidewire.pstesting.ScreenObjectController;
import org.openqa.selenium.By;

public class AccountSearchScreen extends BillingCenterComponent implements ApplicationPage {
    public static final String BASE_ID       = "Accounts:AccountSearchScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    public AccountSearchScreen(ScreenObjectController controller) {
        super(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public AccountSearchScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }
}
