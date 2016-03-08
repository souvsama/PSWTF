package com.guidewire.pstesting.policycenter.submission;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardScreenFactory;
import org.openqa.selenium.By;

public class LocationsScreen extends SubmissionWizardScreen<LocationsScreen> {
    public static final String BASE_ID       = SubmissionWizardScreen.BASE_ID + "LOBWizardStepGroup:LineWizardStepSet:LocationsScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By quoteLocator             = By.id(BASE_ID + "JobWizardToolbarButtonSet:QuoteOrReview-btnInnerEl");
    static final By newLocationButtonLocator = By.id(BASE_ID + "LocationsPanelSet:LocationsEdit_DP_tb:Add-btnInnerEl");

    public LocationsScreen(ScreenObjectController controller) {
        super(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public By getQuoteLocator() {
        return quoteLocator;
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
         * Constructs a <code>LocationsScreen</code>.
         */
        public SubmissionWizardScreen create() {
            return new LocationsScreen(controller);
        }

        /**
         * Constructs a <code>LocationsScreen</code> and waits until it is visible.
         *
         * @return the new <code>LocationsScreen</code> instance
         */
        public SubmissionWizardScreen createAndWait() {
            return new LocationsScreen(controller).waitUntilVisible();
        }
    }
}
