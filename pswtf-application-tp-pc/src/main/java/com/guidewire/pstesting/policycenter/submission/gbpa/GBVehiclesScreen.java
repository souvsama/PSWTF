package com.guidewire.pstesting.policycenter.submission.gbpa;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import org.openqa.selenium.By;

public class GBVehiclesScreen extends SubmissionWizardScreen<GBVehiclesScreen> {
    public static final String BASE_ID       = SubmissionWizardScreen.BASE_ID + "LOBWizardStepGroup:LineWizardStepSet:GBPAVehiclesScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By quoteLocator         = By.id(BASE_ID + "JobWizardToolbarButtonSet:QuoteOrReview-btnInnerEl");
    static final By createVehicleLocator = By.id(BASE_ID + "GBPAVehiclesPanelSet:VehiclesListDetailPanel_tb:Add-btnInnerEl");

    public final GBVehicleDetailsPanel vehicleDetailsPanel;

    public GBVehiclesScreen(ScreenObjectController controller) {
        super(controller);
        this.vehicleDetailsPanel = new GBVehicleDetailsPanel(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public By getQuoteLocator() {
        return quoteLocator;
    }

    public GBVehicleDetailsPanel getVehicleDetailsPanel() {
        return vehicleDetailsPanel;
    }

    public GBVehicleDetailsPanel clickCreateVehicle() {
        getController().click(createVehicleLocator);
        return vehicleDetailsPanel.waitUntilVisible();
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
         * Constructs a <code>GBVehiclesScreen</code>.
         */
        public SubmissionWizardScreen create() {
            return new GBVehiclesScreen(controller);
        }

        /**
         * Constructs a <code>GBVehiclesScreen</code> and waits until it is visible.
         *
         * @return the new <code>GBVehiclesScreen</code> instance
         */
        public SubmissionWizardScreen createAndWait() {
            return new GBVehiclesScreen(controller).waitUntilVisible();
        }
    }
}
