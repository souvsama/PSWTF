package com.guidewire.pstesting.claimcenter.claim;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import com.guidewire.pstesting.claimcenter.ClaimWizardScreen;
import org.openqa.selenium.By;

public class InvolvedPolicyVehiclesScreen extends ClaimWizardScreen<InvolvedPolicyVehiclesScreen> {
    public static final String BASE_ID       = "FNOLWizard:FNOLWizard_PickPolicyRiskUnitsScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");
    public static final By vehicleListViewLocator = By.id(BASE_ID + "PolicySummaryVehicleLV-body");

    public InvolvedPolicyVehiclesScreen(ScreenObjectController controller) {
        super(controller);
    }

    public InvolvedPolicyVehiclesScreen selectVehicleRowWithText(String rowText){
        InvolvedPolicyVehiclesTable involvedVehicle = new InvolvedPolicyVehiclesTable(getController(),vehicleListViewLocator);
        logger.info("Selecting involved vehicle: " + rowText);
        involvedVehicle.selectRowWithText(rowText);
        return this;
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
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
         * Constructs a <code>InvolvedPolicyVehiclesScreen</code>.
         */
        public WizardScreen create() {
            return new InvolvedPolicyVehiclesScreen(controller);
        }

        /**
         * Constructs a <code>InvolvedPolicyVehiclesScreen</code> and waits until it is visible.
         *
         * @return the new <code>InvolvedPolicyVehiclesScreen</code> instance
         */
        public WizardScreen createAndWait() {
            return new InvolvedPolicyVehiclesScreen(controller).waitUntilVisible();
        }
    }
}

