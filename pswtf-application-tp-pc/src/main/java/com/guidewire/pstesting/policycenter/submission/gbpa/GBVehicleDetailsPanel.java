package com.guidewire.pstesting.policycenter.submission.gbpa;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.Driver;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GBVehicleDetailsPanel extends PolicyCenterComponent {
    public static final String BASE_ID = GBVehiclesScreen.BASE_ID + "GBPAVehiclesPanelSet:VehiclesListDetailPanel:VehiclesDetailsCV:";

    static final By licensePlateFieldLocator   = By.id(BASE_ID + "GBPAVehicleDetailsDV:Not_A_Trailer:LicensePlate_DV-inputEl");
    static final By vinFieldLocator            = By.id(BASE_ID + "GBPAVehicleDetailsDV:Not_A_Trailer:Vin_DV-inputEl");
    static final By vehicleGroupFieldLocator   = By.id(BASE_ID + "GBPAVehicleDetailsDV:Not_A_Trailer:VehicleGroup_DV-inputEl");
    static final By garageTypeFieldLocator     = By.id(BASE_ID + "GBPAVehicleDetailsDV:GBPAGarageLocationInputSet:garageType-inputEl");
    static final By costNewFieldLocator        = By.id(BASE_ID + "GBPAVehicleDetailsDV:CostNew_DV-inputEl");
    static final By equipmentValueFieldLocator = By.id(BASE_ID + "GBPAVehicleDetailsDV:EquipmentValue_DV-inputEl");
    static final By annualMileageFieldLocator  = By.id(BASE_ID + "GBPAVehicleDetailsDV:annualmiles_DV-inputEl");
    static final By primaryUseFieldLocator     = By.id(BASE_ID + "GBPAVehicleDetailsDV:primaryuse_DV-inputEl");
    static final By vehicleLookupButtonLocator = By.id(BASE_ID + "GBPAVehicleDetailsDV:Not_A_Trailer:lookup");

    static final By addDriverMenuLocator = By.id(BASE_ID + "GBPAAssignDriversDV:DriverPctLV_tb:AddDriver");

    static final String addDriverLocatorPrefix = BASE_ID + "GBPAAssignDriversDV:DriverPctLV_tb:AddDriver:";
    static final String addDriverLocatorSuffix = ":Driver-arrowEl";

    public GBVehicleDetailsPanel(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(vinFieldLocator);
    }

    public GBVehicleDetailsPanel waitUntilVisible() {
        getController().waitUntilPageContains(vinFieldLocator);
        return this;
    }

    public GBVehicleDetailsPanel setLicensePlate(String plate) {
        getController().setValue(licensePlateFieldLocator, plate);
        return this;
    }

    public GBVehicleDetailsPanel clickVehicleLookup() {
        getController().click(vehicleLookupButtonLocator).waitUntilUpdateDone();
        return this;
    }

    public GBVehicleDetailsPanel setVin(String vin) {
        getController().setValue(vinFieldLocator, vin);
        return this;
    }

    public GBVehicleDetailsPanel setVehicleGroup(String group) {
        getController().setValue(vehicleGroupFieldLocator, group);
        return this;
    }

    public GBVehicleDetailsPanel setGarageType(String type) {
        getController().typeAndTab(garageTypeFieldLocator, type);
        return this;
    }

    public GBVehicleDetailsPanel setCostNew(String amount) {
        getController().setValue(costNewFieldLocator, amount);
        return this;
    }

    public GBVehicleDetailsPanel setEquipmentValue(String amount) {
        getController().setValue(equipmentValueFieldLocator, amount);
        return this;
    }

    public GBVehicleDetailsPanel setAnnualMileage(String mileage) {
        getController().setValue(annualMileageFieldLocator, mileage);
        return this;
    }

    public GBVehicleDetailsPanel setPrimaryUse(String use) {
        getController().typeAndTab(primaryUseFieldLocator, use);
        return this;
    }

    public GBVehicleDetailsPanel clickAddVehicleDriver() {
        getController().click(addDriverMenuLocator);
        return this;
    }

    public GBVehicleDetailsPanel selectVehicleDriverToAdd(String name) {
        getController().waitUntilPageContains(getMenuItemSelectionLocator(0));  // Wait until the drivers menu appears. There has to be at least one item.
        String itemId = findMenuItemId(name);
        if (itemId == null) {
            throw new IllegalArgumentException("No item \"" + name + "\" exists in the menu.");
        }
        getController().click(By.id(itemId));

        StringBuilder builder = new StringBuilder();
        builder.append("descendant::a[text()=\"")
                .append(name)
                .append("\" and contains(@class,\"g-actionable\")]");
        By searchBy = By.xpath(builder.toString());
        getController().waitUntilPageContains(searchBy);

        return this;
    }

    public GBVehicleDetailsPanel addDriver(Driver driver) {
        clickAddVehicleDriver();
        selectVehicleDriverToAdd(driver.getName());
        return this;
    }

    protected By getMenuItemSelectionLocator(int itemIndex) {
        StringBuilder builder = new StringBuilder();
        builder.append(addDriverLocatorPrefix)
                .append(itemIndex)
                .append(addDriverLocatorSuffix);
        return By.id(builder.toString());
    }

    protected String findMenuItemId(String text) {
        // Create search locator
        StringBuilder builder = new StringBuilder();
        builder.append("descendant::span[text()=\"")
                .append(text)
                .append("\" and contains(@class,\"x-menu-item-text\")]");
        By searchBy = By.xpath(builder.toString());
        // Search the elements
        List<WebElement> rows = getController().findElements(searchBy);
        if (rows.size() == 1) {
            return rows.get(0).getAttribute("id");
        }
        return null;
    }
}
