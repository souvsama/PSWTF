package com.guidewire.pstesting.claimcenter.claim.homeowners;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.claimcenter.Product;
import com.guidewire.pstesting.claimcenter.claim.*;

public class HomeownersClaimWizardStepSet extends ClaimWizardStepSet {
    public static final String RESOURCE_BASE_NAME = "locale.cc";

    public static final Product HOMEOWNERS_AUTO_PRODUCT = Product.create(new WizardFactory());

    public HomeownersClaimWizardStepSet(ScreenObjectController controller) {
        super(controller);
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
            return new HomeownersClaimWizardStepSet(controller);
        }
    }

}

