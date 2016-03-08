package com.guidewire.pstesting.policycenter.submission.ca;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;

public class BAVehicleDetailPanel extends PolicyCenterComponent {
    public static final String BASE_ID = BAVehiclePopup.BASE_ID + "BAVehicleCV:";

    static final By garagedAtLocator   = By.id(BASE_ID + "BAGarageLocationInputSet:BAGarageLocationInput-inputEl");
    static final By vehicleTypeLocator = By.id(BASE_ID + "VehicleDV:Type-inputEl");
    static final By vinLocator         = By.id(BASE_ID + "VehicleDV:Vin-inputEl");
    static final By costLocator        = By.id(BASE_ID + "VehicleDV:Cost-inputEl");
    static final By classCodeLocator   = By.id(BASE_ID + "VehicleDV:ClassCode-inputEl");
    static final By modifierLabelLocator = By.id(BASE_ID + "VehicleDV:0:BooleanModifier-labelEl");

    public BAVehicleDetailPanel(ScreenObjectController controller) {
        super(controller);
    }

    public By getPageContains() {
        return vehicleTypeLocator;
    }

    public boolean isVisible() {
        return getController().pageContains(vehicleTypeLocator);
    }

    public BAVehicleDetailPanel waitUntilVisible() {
        getController().waitUntilPageContains(getPageContains());
        return this;
    }

    public BAVehicleDetailPanel setGaragedAt(String location) {
        getController().clickAndType(garagedAtLocator, location).pressTab();
        getController().waitForElement(modifierLabelLocator);
        return this;
    }

    public BAVehicleDetailPanel setVehicleType(String type) {
        getController().typeAndTab(vehicleTypeLocator, type);
        return this;
    }

    public BAVehicleDetailPanel setVin(String vin) {
        getController().typeAndTab(vinLocator, vin);
        return this;
    }

    public BAVehicleDetailPanel setCost(String cost) {
        getController().type(costLocator, cost).waitUntilUpdateDone();
        return this;
    }

    public BAVehicleDetailPanel setClassCode(String classCode) {
        getController().type(classCodeLocator, classCode).waitUntilUpdateDone();
        return this;
    }
}
