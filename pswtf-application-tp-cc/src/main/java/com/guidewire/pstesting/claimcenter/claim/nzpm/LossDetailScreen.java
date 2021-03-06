package com.guidewire.pstesting.claimcenter.claim.nzpm;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.claimcenter.ClaimWizardScreen;
import com.guidewire.pstesting.WizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import org.openqa.selenium.By;

public class LossDetailScreen extends ClaimWizardScreen<LossDetailScreen> {

    public static final String BASE_ID         = "FNOLWizard:AutoWorkersCompWizardStepSet:FNOLWizard_NewLossDetailsScreen:LossDetailsAddressDV:";
    public static final String ADDRESS_BASE_ID = BASE_ID + "AddressDetailInputSetRef:CCAddressInputSet:globalAddressContainer:globalAddress:GlobalAddressInputSet:";

    static final By descriptionLocation = By.id(BASE_ID + "Description-inputEl");
    static final By lossCauseLocation   = By.id(BASE_ID + "Claim_LossCause-inputEl");
    static final By cityLocation        = By.id(ADDRESS_BASE_ID + "City-inputEl");
    static final By stateLocation       = By.id(ADDRESS_BASE_ID + "State-inputEl");

    protected LossDetailScreen(ScreenObjectController controller) {
        super(controller);
    }

    @Override
    public By getPageContains() {
        return descriptionLocation;
    }

    public LossDetailScreen setDescription(String description) {
        getController().type(descriptionLocation, description)
                .waitUntilUpdateDone();
        return this;
    }

    public LossDetailScreen setCause(String cause) {
        getController().clickTypeAndTab(lossCauseLocation, cause)
                .waitUntilUpdateDone();
        return this;
    }

    public LossDetailScreen setCity(String city) {
        getController().clickTypeAndTab(cityLocation, city)
                .waitUntilUpdateDone();
        return this;
    }

    public LossDetailScreen setState(String state) {
        getController().clickTypeAndTab(stateLocation, state)
                .waitUntilUpdateDone();
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
