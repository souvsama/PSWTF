package com.guidewire.pstesting.claimcenter.claim.personalauto;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.claimcenter.Product;
import com.guidewire.pstesting.claimcenter.claim.*;

public class PersonalAutoClaimWizardStepSet extends ClaimWizardStepSet {
    public static final Product PERSONAL_AUTO_PRODUCT = Product.create(new WizardFactory());

    public PersonalAutoClaimWizardStepSet(ScreenObjectController controller) {
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
            return new PersonalAutoClaimWizardStepSet(controller);
        }
    }

}
