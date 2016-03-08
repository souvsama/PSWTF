package com.guidewire.pstesting.claimcenter.claim.nzpm;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.claimcenter.Product;
import com.guidewire.pstesting.claimcenter.claim.*;

public class NZPrivateMotorClaimWizardStepSet extends ClaimWizardStepSet {
    public static final String RESOURCE_BASE_NAME = "locale.cc.tp";

    public static final Product NZPM_PRODUCT = Product.create(new WizardFactory());

    public NZPrivateMotorClaimWizardStepSet(ScreenObjectController controller) {
        super(controller);
        controller.setResourceBaseName(RESOURCE_BASE_NAME);
        initialize();
    }

    private void initialize() {
        addStepFactory(new SearchOrCreatePolicyScreen.Factory(getController()));
        addStepFactory(new BasicInformationScreen.Factory(getController()));
        addStepFactory(new LossDetailScreen.Factory(getController()));
        addStepFactory(new ServicesScreen.Factory(getController()));
        addStepFactory(new AssignSaveScreen.Factory(getController()));
    }

    //================================================================================
    // Factory
    //================================================================================

    public static class WizardFactory implements ClaimWizardStepSetFactory {
        public ClaimWizardStepSet create(ScreenObjectController controller) {
            return new NZPrivateMotorClaimWizardStepSet(controller);
        }
    }
}
