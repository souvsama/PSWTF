package com.guidewire.pstesting.policycenter.submission.smeshops;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardScreen;
import com.guidewire.pstesting.policycenter.submission.ca.CoveredVehiclesScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import org.openqa.selenium.By;

public class SMEEUPremisesScreen extends SubmissionWizardScreen<CoveredVehiclesScreen> {
    public static final String BASE_ID       = SubmissionWizardScreen.BASE_ID + "LOBWizardStepGroup:LineWizardStepSet:SMEEUPremisesScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By quoteLocator       = By.id(BASE_ID + "JobWizardToolbarButtonSet:QuoteOrReview-btnInnerEl");
    static final By newLocationLocator = By.id(BASE_ID + "SMEEUPremisesPanelSet:LocationsEdit_DP_tb:Add-btnInnerEl");

    public final SMEEULocationPopup locationPopup;

    public SMEEUPremisesScreen(ScreenObjectController controller) {
        super(controller);
        locationPopup = new SMEEULocationPopup(this, controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public By getQuoteLocator() {
        return quoteLocator;
    }

    public SMEEULocationPopup clickNewLocation() {
        getController().click(newLocationLocator);
        return locationPopup.waitUntilVisible();
    }

    //================================================================================
    // Factory
    //================================================================================

    public static class Factory implements WizardScreenFactory {
        private final ScreenObjectController controller;

        public Factory(ScreenObjectController controller) {
            this.controller = controller;
        }

        /**
         * Constructs a <code>SMEEUPremisesScreen</code>.
         */
        public SubmissionWizardScreen create() {
            return new SMEEUPremisesScreen(controller);
        }

        /**
         * Constructs a <code>SMEEUPremisesScreen</code> and waits until it is visible.
         *
         * @return the new <code>SMEEUPremisesScreen</code> instance
         */
        public SubmissionWizardScreen createAndWait() {
            return new SMEEUPremisesScreen(controller).waitUntilVisible();
        }
    }
}
