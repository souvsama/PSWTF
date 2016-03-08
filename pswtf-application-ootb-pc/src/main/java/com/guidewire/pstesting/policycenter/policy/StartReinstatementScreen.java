package com.guidewire.pstesting.policycenter.policy;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;

public class StartReinstatementScreen extends PolicyCenterComponent {
    public static final String BASE_ID = "ReinstatementWizard:ReinstatementWizard_ReinstatePolicyScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By reasonCodeLocator = By.id(BASE_ID + "ReinstatePolicyDV:ReasonCode-inputEl");
    static final By quoteButtonLocator = By.id(BASE_ID + "JobWizardToolbarButtonSet:QuoteOrReview-btnInnerEl");

    public StartReinstatementScreen(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public StartReinstatementScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public StartReinstatementScreen setReasonCode(String reasonCode) {
        getController().typeAndEnter(reasonCodeLocator, reasonCode);
        return this;
    }

    public StartReinstatementScreen doQuote() {
        getController().click(quoteButtonLocator);
        return this;
    }

}
