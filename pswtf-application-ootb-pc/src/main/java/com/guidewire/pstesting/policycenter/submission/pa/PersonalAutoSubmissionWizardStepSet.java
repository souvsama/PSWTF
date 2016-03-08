package com.guidewire.pstesting.policycenter.submission.pa;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PreQualificationScreen;
import com.guidewire.pstesting.policycenter.Product;
import com.guidewire.pstesting.policycenter.SubmissionWizardStepSetFactory;
import com.guidewire.pstesting.policycenter.submission.*;

public class PersonalAutoSubmissionWizardStepSet extends SubmissionWizardStepSet {
    public static final Product PERSONAL_AUTO_PRODUCT = Product.create("PersonalAuto", "Personal Auto",
            "product.personalAuto.name",
            new WizardFactory());

    public PersonalAutoSubmissionWizardStepSet(ScreenObjectController controller) {
        super(controller);
        initialize();
    }

    private void initialize() {
        addStepFactory(new OfferingsScreen.Factory(getController()));
        addStepFactory(new PreQualificationScreen.Factory(getController()));
        addStepFactory(new PolicyInformationScreen.Factory(getController()));
        addStepFactory(new DriversScreen.Factory(getController()));
        addStepFactory(new VehiclesScreen.Factory(getController()));
        addStepFactory(new CoveragesScreen.Factory(getController()));
        //TODO RG - Verify if logic is required to include/exclude
//        addStepFactory(new ModifiersScreen.Factory(getController()));
        addStepFactory(new RiskAnalysisScreen.Factory(getController()));
        addStepFactory(new PolicyReviewScreen.Factory(getController()));
    }

    //================================================================================
    // Factory
    //================================================================================

    public static class WizardFactory implements SubmissionWizardStepSetFactory {
        public SubmissionWizardStepSet create(ScreenObjectController controller) {
            return new PersonalAutoSubmissionWizardStepSet(controller);
        }
    }

}
