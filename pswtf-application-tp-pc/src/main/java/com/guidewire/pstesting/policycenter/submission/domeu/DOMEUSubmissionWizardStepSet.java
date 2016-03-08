package com.guidewire.pstesting.policycenter.submission.domeu;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PreQualificationScreen;
import com.guidewire.pstesting.policycenter.Product;
import com.guidewire.pstesting.policycenter.SubmissionWizardStepSetFactory;
import com.guidewire.pstesting.policycenter.submission.OfferingsScreen;
import com.guidewire.pstesting.policycenter.submission.PolicyInformationScreen;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardStepSet;

public class DOMEUSubmissionWizardStepSet extends SubmissionWizardStepSet {
    public static final String RESOURCE_BASE_NAME = "locale.pc";

    public static final Product DOMESTIC_PROPERTY_PRODUCT = Product.create("DomesticPropertyEU", "Domestic Property",
            "product.domesticPropertyEU.name",
            new WizardFactory());

    public DOMEUSubmissionWizardStepSet(ScreenObjectController controller) {
        super(controller);
        controller.setResourceBaseName(RESOURCE_BASE_NAME);
        initialize();
    }

    private void initialize() {
        addStepFactory(new PreQualificationScreen.Factory(getController()));
        addStepFactory(new OfferingsScreen.Factory(getController()));
        addStepFactory(new PolicyInformationScreen.Factory(getController()));
        addStepFactory(new DOMEUDwellingScreen.Factory(getController()));
        addStepFactory(new DOMEUCoveragesScreen.Factory(getController()));
    }

    //================================================================================
    // Factory
    //================================================================================

    public static class WizardFactory implements SubmissionWizardStepSetFactory {
        public SubmissionWizardStepSet create(ScreenObjectController controller) {
            return new DOMEUSubmissionWizardStepSet(controller);
        }
    }

}
