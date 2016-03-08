package com.guidewire.pstesting.claimcenter.claim;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.claimcenter.ClaimCenterComponent;
import org.openqa.selenium.By;


public class FinancialPaymentsScreen extends ClaimCenterComponent {
    public static final String BASE_ID       = "ClaimFinancialsChecks:ClaimFinancialsChecksScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    public FinancialPaymentsScreen(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public FinancialPaymentsScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }
}
