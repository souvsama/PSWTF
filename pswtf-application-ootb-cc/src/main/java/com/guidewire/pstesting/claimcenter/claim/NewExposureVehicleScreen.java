package com.guidewire.pstesting.claimcenter.claim;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.claimcenter.ClaimCenterComponent;
import com.guidewire.pstesting.claimcenter.ClaimSummaryScreen;
import org.openqa.selenium.By;


public class NewExposureVehicleScreen extends ClaimCenterComponent {
    public static final String BASE_ID       = "NewExposure:NewExposureScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");
    public static final By     claimantLocator = By.id(BASE_ID + "NewExposureDV:NewClaimVehicleDamageDV:Claimant_Picker-inputEl");
    public static final By     updateButtonLocator = By.id(BASE_ID + "Update-btnInnerEl");

    public NewExposureVehicleScreen(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public NewExposureVehicleScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public NewExposureVehicleScreen setClaimant(String claimant) {
        getController().typeAndTab(claimantLocator, claimant);
        return this;
    }

    public ClaimSummaryScreen clickUpdateButton() {
        getController().click(updateButtonLocator).waitUntilUpdateDone();
        return new ClaimSummaryScreen(getController());
    }
}
