package com.guidewire.pstesting.policycenter.submission.gbpa;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PreQualificationScreen;
import com.guidewire.pstesting.policycenter.Product;
import com.guidewire.pstesting.policycenter.SubmissionWizardStepSetFactory;
import com.guidewire.pstesting.policycenter.submission.*;
import com.guidewire.pstesting.policycenter.submission.pa.ModifiersScreen;

public class GBPersonalAutoSubmissionWizardStepSet extends SubmissionWizardStepSet {
    public static final String RESOURCE_BASE_NAME = "locale.pc";

    public static final Product MOTOR_PRODUCT = Product.create("GBPersonalAuto", "Motor",
            "product.gbPersonalAuto.name",
            new WizardFactory());

    public GBPersonalAutoSubmissionWizardStepSet(ScreenObjectController controller) {
        super(controller);
        controller.setResourceBaseName(RESOURCE_BASE_NAME);
        initialize();
    }

    private void initialize() {
        addStepFactory(new OfferingsScreen.Factory(getController()));
        addStepFactory(new PreQualificationScreen.Factory(getController()));
        addStepFactory(new PolicyInformationScreen.Factory(getController()));
        addStepFactory(new GBDriversScreen.Factory(getController()));
        addStepFactory(new GBVehiclesScreen.Factory(getController()));
        addStepFactory(new GBCoveragesScreen.Factory(getController()));
        addStepFactory(new ModifiersScreen.Factory(getController()));
        addStepFactory(new RiskAnalysisScreen.Factory(getController()));
        addStepFactory(new PolicyReviewScreen.Factory(getController()));
    }

    //================================================================================
    // Factory
    //================================================================================

    public static class WizardFactory implements SubmissionWizardStepSetFactory {
        public SubmissionWizardStepSet create(ScreenObjectController controller) {
            return new GBPersonalAutoSubmissionWizardStepSet(controller);
        }
    }

}
