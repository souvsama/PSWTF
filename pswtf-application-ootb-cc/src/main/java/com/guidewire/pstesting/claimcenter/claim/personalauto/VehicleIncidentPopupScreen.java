package com.guidewire.pstesting.claimcenter.claim.personalauto;


import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import com.guidewire.pstesting.claimcenter.ClaimWizardScreen;
import org.openqa.selenium.By;

public class VehicleIncidentPopupScreen extends ClaimWizardScreen<VehicleIncidentPopupScreen> {
    public static final String BASE_ID       = "FNOLVehicleIncidentPopup:FNOLVehicleIncidentScreen:";
    public static final By PAGE_CONTAINS     = By.id(BASE_ID + "0");
    public static final By descriptionLocator     = By.id(BASE_ID + "Description-inputEl");
    public static final By okButtonLocator        = By.id(BASE_ID + "Update-btnInnerEl");

    public VehicleIncidentPopupScreen(ScreenObjectController controller) {
        super(controller);
    }

    @Override
    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public VehicleIncidentPopupScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public VehicleIncidentPopupScreen setDescription(String description) {
        getController().clickAndType(descriptionLocator, description);
        return this;
    }

    public LossDetailScreen clickOkButton() {
        getController().click(okButtonLocator);
        return new LossDetailScreen(getController()).waitUntilVisible();
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
         * Constructs a <code>VehicleIncidentPopupScreen</code>.
         */
        public WizardScreen create() {
            return new VehicleIncidentPopupScreen(controller);
        }

        /**
         * Constructs a <code>VehicleIncidentPopupScreen</code> and waits until it is visible.
         *
         * @return the new <code>VehicleIncidentPopupScreen</code> instance
         */
        public WizardScreen createAndWait() {
            return new VehicleIncidentPopupScreen(controller).waitUntilVisible();
        }
    }
}
