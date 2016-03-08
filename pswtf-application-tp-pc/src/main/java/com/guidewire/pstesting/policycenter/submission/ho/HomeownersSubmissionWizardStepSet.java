package com.guidewire.pstesting.policycenter.submission.ho;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PreQualificationScreen;
import com.guidewire.pstesting.policycenter.Product;
import com.guidewire.pstesting.policycenter.SubmissionWizardStepSetFactory;
import com.guidewire.pstesting.policycenter.submission.PolicyInformationScreen;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardStepSet;

public class HomeownersSubmissionWizardStepSet extends SubmissionWizardStepSet {
    public static final String RESOURCE_BASE_NAME = "locale.pc";

    public static final Product HOMEOWNERS_PRODUCT = Product.create("Homeowners", "Homeowners",
            "product.homeowners.name",
            new WizardFactory());

    public HomeownersSubmissionWizardStepSet(ScreenObjectController controller) {
        super(controller);
        controller.setResourceBaseName(RESOURCE_BASE_NAME);
        initialize();
    }

    private void initialize() {
        addStepFactory(new PreQualificationScreen.Factory(getController()));
        addStepFactory(new PolicyInformationScreen.Factory(getController()));
        addStepFactory(new DwellingScreen.Factory(getController()));
        addStepFactory(new DwellingConstructionScreen.Factory(getController()));
        addStepFactory(new CoveragesScreen.Factory(getController()));
    }

    //================================================================================
    // Factory
    //================================================================================

    public static class WizardFactory implements SubmissionWizardStepSetFactory {
        public SubmissionWizardStepSet create(ScreenObjectController controller) {
            return new HomeownersSubmissionWizardStepSet(controller);
        }
    }

}
