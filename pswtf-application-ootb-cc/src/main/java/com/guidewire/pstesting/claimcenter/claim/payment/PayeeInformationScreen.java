package com.guidewire.pstesting.claimcenter.claim.payment;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import com.guidewire.pstesting.claimcenter.ClaimWizardScreen;
import org.openqa.selenium.By;


public class PayeeInformationScreen extends ClaimWizardScreen<PayeeInformationScreen> {
    public static final String BASE_ID      = "NormalCreateCheckWizard:CheckWizard_CheckPayeesScreen:";
    public static final By PAGE_CONTAINS    = By.id(BASE_ID + "ttlBar");
    public static final By payeeNameLocator    = By.id(BASE_ID + "NewCheckPayeeDV:PrimaryPayee_Name-inputEl");

    public PayeeInformationScreen(ScreenObjectController controller) {
        super(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public PayeeInformationScreen setPayee(String payee) {
        getController().clickTypeAndTab(payeeNameLocator, payee).waitUntilUpdateDone();
        return this;
    }

    //================================================================================
    // Factory
    //================================================================================

    public static class Factory implements WizardScreenFactory {
        private final ScreenObjectController controller;

        public Factory(ScreenObjectController controller) {
            this.controller = controller;
        }

        /**
         * Constructs a <code>PayeeInformationScreen</code>.
         */
        public WizardScreen create() {
            return new PayeeInformationScreen(controller);
        }

        /**
         * Constructs a <code>PayeeInformationScreen</code> and waits until it is visible.
         *
         * @return the new <code>PayeeInformationScreen</code> instance
         */
        public WizardScreen createAndWait() {
            return new PayeeInformationScreen(controller).waitUntilVisible();
        }
    }
}
