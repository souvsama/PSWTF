package com.guidewire.pstesting.policycenter.submission.ca;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;

public class BAVehiclePopup extends PolicyCenterComponent {
    public static final String BASE_ID       = "BAVehiclePopup:VehicleScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By okButtonLocator          = By.id(BASE_ID + "Update-btnInnerEl");
    static final By vehicleDetailsTabLocator = By.id(BASE_ID + "BAVehicleCV:VehicleDetailCardTab-btnInnerEl");

    private final BAVehicleDetailPanel vehicleDetailPanel;

    public BAVehiclePopup(ScreenObjectController controller) {
        super(controller);
        vehicleDetailPanel = new BAVehicleDetailPanel(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public boolean isVisible() {
        return getController().pageContains(getPageContains());
    }

    @SuppressWarnings("unchecked")
    public BAVehiclePopup waitUntilVisible() {
        getController().waitUntilPageContains(getPageContains());
        return this;
    }

    public void clickOk() {
        getController().click(okButtonLocator);
    }

    public BAVehicleDetailPanel selectVehicleDetailsTab() {
        if (!vehicleDetailPanel.isVisible()) {
            getController().click(vehicleDetailsTabLocator);
            vehicleDetailPanel.waitUntilVisible();
        }
        return vehicleDetailPanel;
    }
}
