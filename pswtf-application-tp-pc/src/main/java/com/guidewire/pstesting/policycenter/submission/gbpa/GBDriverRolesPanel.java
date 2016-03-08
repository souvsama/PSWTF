package com.guidewire.pstesting.policycenter.submission.gbpa;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class GBDriverRolesPanel extends PolicyCenterComponent {
    public static final String NUMBER_OF_ACCIDENTS  = "driver.numberOfAccidents";
    public static final String NUMBER_OF_VIOLATIONS = "driver.numberOfViolations";

    public static final String BASE_ID = GBDriversScreen.BASE_ID +
            "GBPADriversPanelSet:DriversListDetailPanel:DriverDetailsCV:1:PolicyContactRolePanelSet:";

    static final By yearFirstLicensedFieldLocator          = By.id(BASE_ID + "PolicyDriverInfoDV:yearlicensed-inputEl");
    static final By accidentsViolationsSummaryTableLocator = By.id(BASE_ID + "2-body");

    public GBDriverRolesPanel(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(yearFirstLicensedFieldLocator);
    }

    public GBDriverRolesPanel waitUntilVisible() {
        getController().waitUntilPageContains(yearFirstLicensedFieldLocator);
        return this;
    }

    public GBDriverRolesPanel setYearFirstLicensed(String year) {
        getController().type(yearFirstLicensedFieldLocator, year).waitUntilUpdateDone();
        return this;
    }

    public void selectNumberOfAccidentsPolicyLevel(String accidents) {
        setAccidentViolationLevel(getController().getString(getResourceBaseName(), NUMBER_OF_ACCIDENTS), 1, accidents);
    }

    public void selectNumberOfAccidentsAccountLevel(String accidents) {
        setAccidentViolationLevel(getController().getString(getResourceBaseName(), NUMBER_OF_ACCIDENTS), 2, accidents);
    }

    public void selectNumberOfViolationsPolicyLevel(String violations) {
        setAccidentViolationLevel(getController().getString(getResourceBaseName(), NUMBER_OF_VIOLATIONS), 1, violations);
    }

    public void selectNumberOfViolationsAccountLevel(String violations) {
        setAccidentViolationLevel(getController().getString(getResourceBaseName(), NUMBER_OF_VIOLATIONS), 2, violations);
    }

    protected void setAccidentViolationLevel(String rowText, int columnIndex, String listItem) {
        if (listItem != null) {
            WebElement rowElement = getController().findRow(accidentsViolationsSummaryTableLocator, rowText);
            WebElement cellElement = rowElement.findElements(By.tagName("td")).get(columnIndex);
            By listLocator = By.className("x-list-plain");
            getController().clickAndWait(cellElement, listLocator)
                    .type(listItem)
                    .sleep(250)
                    .pressEnter()
                    .sleep(250);
        }
    }
}
