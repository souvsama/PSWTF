package com.guidewire.pstesting.policycenter.submission.pa;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class VehicleDetailsPanel extends PolicyCenterComponent {
    public static final String BASE_ID = VehiclesScreen.BASE_ID + "PAVehiclesPanelSet:VehiclesListDetailPanel:VehiclesDetailsCV:";

    static final By vinFieldLocator          = By.id(BASE_ID + "PersonalAuto_VehicleDV:Vin_DV-inputEl");
    static final By licenseStateFieldLocator = By.id(BASE_ID + "PersonalAuto_VehicleDV:LicenseState_DV-inputEl");
    static final By costNewFieldLocator      = By.id(BASE_ID + "PersonalAuto_VehicleDV:CostNew_DV-inputEl");
    static final By addDriverMenuLocator     = By.id(BASE_ID + "PersonalAuto_AssignDriversDV:DriverPctLV_tb:AddDriver");

    public VehicleDetailsPanel(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(vinFieldLocator);
    }

    public VehicleDetailsPanel waitUntilVisible() {
        getController().waitUntilPageContains(vinFieldLocator);
        return this;
    }

    public VehicleDetailsPanel setVin(String vin) {
        getController().typeAndTab(vinFieldLocator, vin);
        return this;
    }

    public VehicleDetailsPanel setLicenseState(String state) {
        getController().clickAndType(licenseStateFieldLocator, state);
        getController().pressTab().waitUntilUpdateDone();
        return this;
    }

    public VehicleDetailsPanel setCostNew(String amount) {
        getController().type(costNewFieldLocator, amount).waitUntilUpdateDone();
        return this;
    }

    public VehicleDetailsPanel clickAddVehicleDriver() {
        getController().click(addDriverMenuLocator);
        return this;
    }

    public VehicleDetailsPanel selectVehicleDriverToAdd(String name) {
        getController().waitUntilPageContains(getMenuItemSelectionLocator(0));  // Wait until the drivers menu appears. There has to be at least one item.
        String driverName = name.contains("- $") ? name.substring(0, name.indexOf("-")-1) : name;
        String itemId = findMenuItemId(driverName);
        if (itemId == null) {
            throw new IllegalArgumentException("No item \"" + name + "\" exists in the menu.");
        }
        getController().click(By.id(itemId)).waitUntilUpdateDone();

        StringBuilder builder = new StringBuilder();
        builder.append("descendant::a[contains(text(),\"").append(driverName).append("\") and contains(@class,\"g-actionable\")]");
        By searchBy = By.xpath(builder.toString());
        getController().waitUntilPageContains(searchBy);

        return this;
    }

    protected By getMenuItemSelectionLocator(int itemIndex) {
        StringBuilder builder = new StringBuilder();
        builder.append(BASE_ID + "PersonalAuto_AssignDriversDV:DriverPctLV_tb:AddDriver:")
                .append(itemIndex)
                .append(":Driver-textEl");
        return By.id(builder.toString());
    }

    protected String findMenuItemId(String text) {
        // Create search locator
        StringBuilder builder = new StringBuilder();
        builder.append("descendant::span[contains(text(),\"").append(text).append("\") and contains(@class,\"x-menu-item-text\")]");
        By searchBy = By.xpath(builder.toString());
        // Search the elements
        List<WebElement> rows = getController().findElements(searchBy);
        if (rows.size() == 1) {
            return rows.get(0).getAttribute("id");
        }
        return null;
    }
}
