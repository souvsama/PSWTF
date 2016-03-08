package com.guidewire.pstesting.policycenter.submission.gbpa;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.Driver;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GBDriversScreen extends SubmissionWizardScreen<GBDriversScreen> {
    public static final String BASE_ID       = SubmissionWizardScreen.BASE_ID + "LOBWizardStepGroup:LineWizardStepSet:GBPADriversScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By quoteLocator                  = By.id(BASE_ID + "JobWizardToolbarButtonSet:QuoteOrReview-btnInnerEl");
    static final By addDriverMenuLocator          = By.id(BASE_ID + "GBPADriversPanelSet:DriversListDetailPanel:DriversLV_tb:AddDriver");
    static final By existingDriverMenuItemLocator = By.id(BASE_ID + "GBPADriversPanelSet:DriversListDetailPanel:DriversLV_tb:AddDriver:AddExistingContact-itemEl");
    static final By driverRolesTabLocator         = By.id(BASE_ID + "GBPADriversPanelSet:DriversListDetailPanel:DriverDetailsCV:RolesCardTab-btnIconEl");

    static final String addExistingContactLocatorPrefix = BASE_ID + "GBPADriversPanelSet:DriversListDetailPanel:DriversLV_tb:AddDriver:AddExistingContact:";
    static final String addExistingContactLocatorSuffix = ":UnassignedDriver-itemEl";

    public GBDriversScreen(ScreenObjectController controller) {
        super(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public By getQuoteLocator() {
        return quoteLocator;
    }

    public GBDriversScreen clickAdd() {
        getController().clickAndWait(addDriverMenuLocator, existingDriverMenuItemLocator);
        return this;
    }

    public GBDriversScreen hoverOverExistingDriver() {
        getController().hoverOver(existingDriverMenuItemLocator);
        return this;
    }

    public GBDriverContactDetailsPanel selectExistingDriver(int menuIndex) {
        By menuItemLocator = getMenuItemSelectionLocator(menuIndex);
        getController().waitUntilPageContains(getMenuItemSelectionLocator(menuIndex));
        getController().click(menuItemLocator);
        return new GBDriverContactDetailsPanel(getController()).waitUntilVisible();
    }

    public GBDriverContactDetailsPanel selectExistingDriver(String name) {
        getController().waitUntilPageContains(getMenuItemSelectionLocator(0));  // Wait until the drivers menu appears. There has to be at least one item.
        String rowId = findMenuItemId(name);
        if (rowId == null) {
            throw new IllegalArgumentException("No item \"" + name + "\" exists in the menu.");
        }
        getController().click(By.id(rowId));
        return new GBDriverContactDetailsPanel(getController()).waitUntilVisible();
    }

    public GBDriverRolesPanel clickRolesTab() {
        getController().click(driverRolesTabLocator);
        return new GBDriverRolesPanel(getController()).waitUntilVisible();
    }

    public GBDriversScreen addDriver(Driver driver) {
        clickAdd().hoverOverExistingDriver();
        String menuIndex = driver.getMenuIndex();
        GBDriverContactDetailsPanel driverContactDetailsPanel;
        if (menuIndex != null) {
            driverContactDetailsPanel = selectExistingDriver(Integer.valueOf(menuIndex));
        } else {
            driverContactDetailsPanel = selectExistingDriver(driver.getName());
        }
        // Set driver contact details
        driverContactDetailsPanel.setDateOfBirth(driver.getDateOfBirth());
        driverContactDetailsPanel.setSex(driver.getGender());
        driverContactDetailsPanel.setLicenseStartDate(driver.getLicenseStartDate());
        driverContactDetailsPanel.setLicenseNumber(driver.getLicenseNumber());
        // Set driver roles
        GBDriverRolesPanel driverRolesPanel = clickRolesTab();
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
        builder.append(addExistingContactLocatorPrefix)
                .append(rowIndex)
                .append(addExistingContactLocatorSuffix);
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

    //================================================================================
    // Factory
    //================================================================================

    public static class Factory implements WizardScreenFactory {
        private final ScreenObjectController controller;

        public Factory(ScreenObjectController controller) {
            this.controller = controller;
        }

        /**
         * Constructs a <code>GBDriversScreen</code>.
         */
        public SubmissionWizardScreen create() {
            return new GBDriversScreen(controller);
        }

        /**
         * Constructs a <code>GBDriversScreen</code> and waits until it is visible.
         *
         * @return the new <code>GBDriversScreen</code> instance
         */
        public SubmissionWizardScreen createAndWait() {
            return new GBDriversScreen(controller).waitUntilVisible();
        }
    }
}
