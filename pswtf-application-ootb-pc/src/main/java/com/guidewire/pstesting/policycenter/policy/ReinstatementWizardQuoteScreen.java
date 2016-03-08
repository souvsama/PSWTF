package com.guidewire.pstesting.policycenter.policy;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterApplication;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;

public class ReinstatementWizardQuoteScreen extends PolicyCenterComponent {
    public static final String BASE_ID = "ReinstatementWizard:ReinstatementWizard_QuoteScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By reinstateButtonLocator = By.id(BASE_ID + "JobWizardToolbarButtonSet:Reinstate-btnInnerEl");

    public ReinstatementWizardQuoteScreen(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() { return getController().pageContains(PAGE_CONTAINS); }

    public ReinstatementWizardQuoteScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public ReinstatementWizardQuoteScreen clickReinstate() {
        getController().click(reinstateButtonLocator);
        return this;
    }

    public ReinstatementWizardQuoteScreen clickOK() {
        getController().click(getController().getElementByResource(PolicyCenterApplication.OK_BUTTON));
        return this;
    }

}
