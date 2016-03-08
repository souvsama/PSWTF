package com.guidewire.pstesting.claimcenter.claim.homeowners;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import com.guidewire.pstesting.claimcenter.ClaimWizardScreen;
import org.openqa.selenium.By;

public class LossDetailScreen extends ClaimWizardScreen<LossDetailScreen> {
    public static final String BASE_ID = "FNOLWizard:AutoWorkersCompWizardStepSet:FNOLWizard_NewLossDetailsScreen:LossDetailsPanel:FNOLWizard_NewLossDetailsPanelSet:NewLossDetailsHomeownersDV:";

    static final By descriptionLocation = By.id(BASE_ID + "Description-inputEl");
    static final By lossCauseLocation   = By.id(BASE_ID + "Claim_LossCause-inputEl");

    public LossDetailScreen(ScreenObjectController controller) {
        super(controller);
    }

    public By getPageContains() {
        return descriptionLocation;
    }

    public LossDetailScreen setDescription(String description) {
        getController().type(descriptionLocation, description).waitUntilUpdateDone();
        return this;
    }

    public LossDetailScreen setLossCause(String cause) {
        getController().setDropDownField(lossCauseLocation, cause).waitUntilUpdateDone();
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
         * Constructs a <code>LossDetailScreen</code>.
         */
        public WizardScreen create() {
            return new LossDetailScreen(controller);
        }

        /**
         * Constructs a <code>LossDetailScreen</code> and waits until it is visible.
         *
         * @return the new <code>LossDetailScreen</code> instance
         */
        public WizardScreen createAndWait() {
            return new LossDetailScreen(controller).waitUntilVisible();
        }
    }
}

