package com.guidewire.pstesting.claimcenter.claim.payment;


import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import com.guidewire.pstesting.claimcenter.ClaimWizardScreen;
import org.openqa.selenium.By;

public class CheckInstructionsScreen extends ClaimWizardScreen<CheckInstructionsScreen> {

    public static final String BASE_ID      = "NormalCreateCheckWizard:CheckWizard_CheckInstructionsScreen:";
    public static final By PAGE_CONTAINS    = By.id(BASE_ID + "ttlBar");

    public CheckInstructionsScreen(ScreenObjectController controller) {
        super(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
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
         * Constructs a <code>CheckInstructionsScreen</code>.
         */
        public WizardScreen create() {
            return new CheckInstructionsScreen(controller);
        }

        /**
         * Constructs a <code>CheckInstructionsScreen</code> and waits until it is visible.
         *
         * @return the new <code>CheckInstructionsScreen</code> instance
         */
        public WizardScreen createAndWait() {
            return new CheckInstructionsScreen(controller).waitUntilVisible();
        }
    }
}
