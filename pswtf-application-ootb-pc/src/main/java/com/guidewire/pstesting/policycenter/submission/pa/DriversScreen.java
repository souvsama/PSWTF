package com.guidewire.pstesting.policycenter.submission.pa;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardScreenFactory;
import com.guidewire.pstesting.policycenter.Driver;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DriversScreen extends SubmissionWizardScreen<DriversScreen> {
    public static final String BASE_ID       = SubmissionWizardScreen.BASE_ID + "LOBWizardStepGroup:LineWizardStepSet:PADriversScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By quoteLocator                  = By.id(BASE_ID + "JobWizardToolbarButtonSet:QuoteOrReview-btnInnerEl");
    static final By addDriverMenuLocator          = By.id(BASE_ID + "PADriversPanelSet:DriversListDetailPanel:DriversLV_tb:AddDriver");
    static final By existingDriverMenuItemLocator = By.id(BASE_ID + "PADriversPanelSet:DriversListDetailPanel:DriversLV_tb:AddDriver:AddExistingContact-itemEl");
    static final By driverRolesTabLocator         = By.id(BASE_ID + "PADriversPanelSet:DriversListDetailPanel:DriverDetailsCV:RolesCardTab-btnIconEl");

    public DriversScreen(ScreenObjectController controller) {
        super(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public By getQuoteLocator() {
        return quoteLocator;
    }

    public DriversScreen clickAdd() {
        getController().clickAndWait(addDriverMenuLocator, existingDriverMenuItemLocator);
        return this;
    }

    public DriversScreen hoverOverExistingDriver() {
        getController().hoverOver(existingDriverMenuItemLocator);
        return this;
    }

    public DriverContactDetailsPanel selectExistingDriver(int menuIndex) {
        By menuItemLocator = getMenuItemSelectionLocator(menuIndex);
        getController().waitUntilPageContains(getMenuItemSelectionLocator(menuIndex));
        getController().click(menuItemLocator);
        return new DriverContactDetailsPanel(getController()).waitUntilVisible();
    }

    public DriverContactDetailsPanel selectExistingDriver(String name) {
        getController().waitUntilPageContains(getMenuItemSelectionLocator(0));  // Wait until the drivers menu appears. There has to be at least one item.
        String rowId = findMenuItemId(name);
        if (rowId == null) {
            throw new IllegalArgumentException("No item \"" + name + "\" exists in the menu.");
        }
        getController().click(By.id(rowId));
        return new DriverContactDetailsPanel(getController()).waitUntilVisible();
    }

    public DriverRolesPanel clickRolesTab() {
        getController().click(driverRolesTabLocator);
        return new DriverRolesPanel(getController()).waitUntilVisible();
    }

    public DriversScreen addDriver(Driver driver) {
        clickAdd().hoverOverExistingDriver();
        String menuIndex = driver.getMenuIndex();
        DriverContactDetailsPanel driverContactDetailsPanel;
        if (menuIndex != null) {
            driverContactDetailsPanel = selectExistingDriver(Integer.valueOf(menuIndex));
        } else {
            String driverName = driver.getName();
            driverName = driverName.substring(0, driverName.indexOf("-")-1);
            driverContactDetailsPanel = selectExistingDriver(driverName);
        }
        // Set driver contact details
        //driverContactDetailsPanel.setMobilePhone(driver.getMobilePhone());
        driverContactDetailsPanel.setDateOfBirth(driver.getDateOfBirth());
        driverContactDetailsPanel.setLicenseNumber(driver.getLicenseNumber());
        driverContactDetailsPanel.setLicenseState(driver.getLicenseState());
        // Set driver roles
        DriverRolesPanel driverRolesPanel = clickRolesTab();
        String yearFirstLicensed = driver.getYearFirstLicensed();
        if (yearFirstLicensed != null) {
            driverRolesPanel.setYearFirstLicensed(yearFirstLicensed);
        }
        // Accident/Violation summary
        driverRolesPanel.selectNumberOfAccidentsPolicyLevel(driver.getNumberAccidentsPolicyLevel());
        driverRolesPanel.selectNumberOfAccidentsAccountLevel(driver.getNumberAccidentsAccountLevel());
        driverRolesPanel.selectNumberOfViolationsPolicyLevel(driver.getNumberViolationsPolicyLevel());
        driverRolesPanel.selectNumberOfViolationsAccountLevel(driver.getNumberViolationsAccountLevel());

        return this;
    }

    protected By getMenuItemSelectionLocator(int rowIndex) {
        StringBuilder builder = new StringBuilder();
        builder.append(BASE_ID + "PADriversPanelSet:DriversListDetailPanel:DriversLV_tb:AddDriver:AddExistingContact:")
                .append(rowIndex)
                .append(":UnassignedDriver-itemEl");
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

    //================================================================================
    // Factory
    //================================================================================

    public static class Factory implements WizardScreenFactory {
        private final ScreenObjectController controller;

        public Factory(ScreenObjectController controller) {
            this.controller = controller;
        }

        /**
         * Constructs a <code>DriversScreen</code>.
         */
        public SubmissionWizardScreen create() {
            return new DriversScreen(controller);
        }

        /**
         * Constructs a <code>DriversScreen</code> and waits until it is visible.
         *
         * @return the new <code>DriversScreen</code> instance
         */
        public SubmissionWizardScreen createAndWait() {
            return new DriversScreen(controller).waitUntilVisible();
        }
    }
}
