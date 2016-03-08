package com.guidewire.pstesting.claimcenter.claim;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import com.guidewire.pstesting.claimcenter.ClaimWizardScreen;
import org.openqa.selenium.By;

public class AssignSaveScreen extends ClaimWizardScreen<AssignSaveScreen> {
    public static final String BASE_ID = "FNOLWizard:AutoWorkersCompWizardStepSet:FNOLWizard_AssignSaveScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");
    public static final By assignUserLocator = By.id(BASE_ID + "CommonAssign-inputEl");

    public AssignSaveScreen(ScreenObjectController controller) {
        super(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public AssignSaveScreen setAssignedUser(String assignedUser){
        getController().setValue(assignUserLocator, assignedUser);
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
         * Constructs a <code>AssignSaveScreen</code>.
         */
        public WizardScreen create() {
            return new AssignSaveScreen(controller);
        }

        /**
         * Constructs a <code>AssignSaveScreen</code> and waits until it is visible.
         *
         * @return the new <code>AssignSaveScreen</code> instance
         */
        public WizardScreen createAndWait() {
            return new AssignSaveScreen(controller).waitUntilVisible();
        }
    }
}

