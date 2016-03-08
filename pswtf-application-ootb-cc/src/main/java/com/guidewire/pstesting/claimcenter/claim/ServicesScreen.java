package com.guidewire.pstesting.claimcenter.claim;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import com.guidewire.pstesting.claimcenter.ClaimWizardScreen;
import org.openqa.selenium.By;

public class ServicesScreen extends ClaimWizardScreen<ServicesScreen> {
    public static final String BASE_ID       = "FNOLWizard:AutoWorkersCompWizardStepSet:FNOLWizard_ServicesScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    public ServicesScreen(ScreenObjectController controller) {
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

        /*      Constructs a <code>ServicesScreen</code>.
        */
        public WizardScreen create() {
            return new ServicesScreen(controller);
        }

/*
         * Constructs a <code>ServicesScreen</code> and waits until it is visible.
         *
         * @return the new <code>ServicesScreen</code> instance
*/

        public WizardScreen createAndWait() {
            return new ServicesScreen(controller).waitUntilVisible();
        }
    }
}

