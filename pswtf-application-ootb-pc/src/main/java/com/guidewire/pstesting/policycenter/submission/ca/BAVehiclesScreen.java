package com.guidewire.pstesting.policycenter.submission.ca;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import org.openqa.selenium.By;

public class BAVehiclesScreen extends SubmissionWizardScreen<BAVehiclesScreen> {
    public static final String BASE_ID       = SubmissionWizardScreen.BASE_ID + "LOBWizardStepGroup:LineWizardStepSet:BAVehiclesScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By quoteLocator               = By.id(BASE_ID + "JobWizardToolbarButtonSet:QuoteOrReview-btnInnerEl");
    static final By createVehicleButtonLocator = By.id(BASE_ID + "BAVehiclesLV_tb:Add-btnInnerEl");

    private final BAVehiclePopup vehiclePopup;

    public BAVehiclesScreen(ScreenObjectController controller) {
        super(controller);
        vehiclePopup = new BAVehiclePopup(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public BAVehiclePopup clickCreateVehicle() {
        getController().click(createVehicleButtonLocator);
        return vehiclePopup.waitUntilVisible();
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
         * Constructs a <code>BAVehiclesScreen</code>.
         */
        public SubmissionWizardScreen create() {
            return new BAVehiclesScreen(controller);
        }

        /**
         * Constructs a <code>BAVehiclesScreen</code> and waits until it is visible.
         *
         * @return the new <code>BAVehiclesScreen</code> instance
         */
        public SubmissionWizardScreen createAndWait() {
            return new BAVehiclesScreen(controller).waitUntilVisible();
        }
    }
}
