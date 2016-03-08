package com.guidewire.pstesting.claimcenter;

import com.guidewire.pstesting.claimcenter.claim.ClaimWizardStepSetFactory;


public class Product {
    private ClaimWizardStepSetFactory wizardFactory;

    private Product(ClaimWizardStepSetFactory wizardFactory) {
        this.wizardFactory = wizardFactory;
    }

    public static Product create(ClaimWizardStepSetFactory wizardFactory) {return new Product(wizardFactory);}

    public ClaimWizardStepSetFactory getWizardFactory() {
        return wizardFactory;
    }

}
