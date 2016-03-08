package com.guidewire.pstesting.policycenter.submission.ca;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PreQualificationScreen;
import com.guidewire.pstesting.policycenter.Product;
import com.guidewire.pstesting.policycenter.SubmissionWizardStepSetFactory;
import com.guidewire.pstesting.policycenter.submission.*;

public class BASubmissionWizardStepSet extends SubmissionWizardStepSet {
    public static final Product COMMERCIAL_AUTO_PRODUCT = Product.create("BusinessAuto", "Commercial Auto",
            "product.businessAuto.name",
            new WizardFactory());

    public BASubmissionWizardStepSet(ScreenObjectController controller) {
        super(controller);
        initialize();
    }

    private void initialize() {
        addStepFactory(new OfferingsScreen.Factory(getController()));
        addStepFactory(new PreQualificationScreen.Factory(getController()));
        addStepFactory(new BAPolicyInfoScreen.Factory(getController()));
        addStepFactory(new BALineScreen.Factory(getController()));
        addStepFactory(new LocationsScreen.Factory(getController()));
        addStepFactory(new BAVehiclesScreen.Factory(getController()));
        addStepFactory(new BAStateInfoScreen.Factory(getController()));
        addStepFactory(new BADriversScreen.Factory(getController()));
        addStepFactory(new CoveredVehiclesScreen.Factory(getController()));
        addStepFactory(new ModifiersScreen.Factory(getController()));
        addStepFactory(new RiskAnalysisScreen.Factory(getController()));
        addStepFactory(new PolicyReviewScreen.Factory(getController()));
    }

    //================================================================================
    // Factory
    //================================================================================

    public static class WizardFactory implements SubmissionWizardStepSetFactory {
        public SubmissionWizardStepSet create(ScreenObjectController controller) {
            return new BASubmissionWizardStepSet(controller);
        }
    }
}
