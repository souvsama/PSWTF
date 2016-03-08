package com.guidewire.pstesting.policycenter.submission.smeshops;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PreQualificationScreen;
import com.guidewire.pstesting.policycenter.Product;
import com.guidewire.pstesting.policycenter.SubmissionWizardStepSetFactory;
import com.guidewire.pstesting.policycenter.submission.*;

public class SMEEUSubmissionWizardStepSet extends SubmissionWizardStepSet {
    public static final String RESOURCE_BASE_NAME = "locale.pc";

    public static final Product SME_SHOPS_PRODUCT = Product.create("SMEShops", "SME Shops",
            "product.smeShops.name",
            new WizardFactory());

    public SMEEUSubmissionWizardStepSet(ScreenObjectController controller) {
        super(controller);
        controller.setResourceBaseName(RESOURCE_BASE_NAME);
        initialize();
    }

    private void initialize() {
        addStepFactory(new OfferingsScreen.Factory(getController()));
        addStepFactory(new PreQualificationScreen.Factory(getController()));
        addStepFactory(new PolicyInformationScreen.Factory(getController()));
        addStepFactory(new SMEEUPremisesScreen.Factory(getController()));
        addStepFactory(new SMEEUAdditionalCoveragesScreen.Factory(getController()));
        addStepFactory(new RiskAnalysisScreen.Factory(getController()));
        addStepFactory(new PolicyReviewScreen.Factory(getController()));
    }

    //================================================================================
    // Factory
    //================================================================================

    public static class WizardFactory implements SubmissionWizardStepSetFactory {
        public SubmissionWizardStepSet create(ScreenObjectController controller) {
            return new SMEEUSubmissionWizardStepSet(controller);
        }
    }

}
