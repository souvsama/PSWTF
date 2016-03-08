package com.guidewire.pstesting.claimcenter.claim.businessauto;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.claimcenter.Product;
import com.guidewire.pstesting.claimcenter.claim.*;

public class BusinessAutoClaimWizardStepSet extends ClaimWizardStepSet {
    public static final Product COMMERCIAL_AUTO_PRODUCT = Product.create(new WizardFactory());

    public BusinessAutoClaimWizardStepSet(ScreenObjectController controller) {
        super(controller);
        initialize();
    }

    private void initialize() {
        addStepFactory(new SearchOrCreatePolicyScreen.Factory(getController()));
        addStepFactory(new InvolvedPolicyVehiclesScreen.Factory(getController()));
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
            return new BusinessAutoClaimWizardStepSet(controller);
        }
    }

}
