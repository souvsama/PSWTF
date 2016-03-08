package com.guidewire.pstesting.policycenter.submission.pa;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import org.openqa.selenium.By;

public class VehiclesScreen extends SubmissionWizardScreen<VehiclesScreen> {
    public static final String BASE_ID       = SubmissionWizardScreen.BASE_ID + "LOBWizardStepGroup:LineWizardStepSet:PAVehiclesScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By quoteLocator         = By.id(BASE_ID + "JobWizardToolbarButtonSet:QuoteOrReview-btnInnerEl");
    static final By createVehicleLocator = By.id(BASE_ID + "PAVehiclesPanelSet:VehiclesListDetailPanel_tb:Add-btnInnerEl");

    public final VehicleDetailsPanel vehicleDetailsPanel;

    public VehiclesScreen(ScreenObjectController controller) {
        super(controller);
        this.vehicleDetailsPanel = new VehicleDetailsPanel(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public By getQuoteLocator() {
        return quoteLocator;
    }

    public VehicleDetailsPanel getVehicleDetailsPanel() {
        return vehicleDetailsPanel;
    }

    public VehicleDetailsPanel clickCreateVehicle() {
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
         * Constructs a <code>VehiclesScreen</code>.
         */
        public SubmissionWizardScreen create() {
            return new VehiclesScreen(controller);
        }

        /**
         * Constructs a <code>VehiclesScreen</code> and waits until it is visible.
         *
         * @return the new <code>VehiclesScreen</code> instance
         */
        public SubmissionWizardScreen createAndWait() {
            return new VehiclesScreen(controller).waitUntilVisible();
        }
    }
}
