package com.guidewire.pstesting.policycenter.submission.pa;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;

public class DriverRolesPanel extends PolicyCenterComponent {
    public static final String NUMBER_OF_ACCIDENTS  = "driver.numberOfAccidents";
    public static final String NUMBER_OF_VIOLATIONS = "driver.numberOfViolations";

    public static final String BASE_ID = DriversScreen.BASE_ID +
            "PADriversPanelSet:DriversListDetailPanel:DriverDetailsCV:1:PolicyContactRolePanelSet:";

    static final By yearFirstLicensedFieldLocator          = By.id(BASE_ID + "PolicyDriverInfoDV:yearlicensed-inputEl");
    static final By accidentsViolationsSummaryTableLocator = By.id(BASE_ID + "2-body");

    public DriverRolesPanel(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(yearFirstLicensedFieldLocator);
    }

    public DriverRolesPanel waitUntilVisible() {
        getController().waitUntilPageContains(yearFirstLicensedFieldLocator);
        return this;
    }

    public DriverRolesPanel setYearFirstLicensed(String year) {
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

    public List<WebElement> findLists(By locator){
        return getController().findElements(locator);
    }

    protected void setAccidentViolationLevel(String rowText, int columnIndex, String listItem) {
        if (listItem != null) {
            WebElement rowElement = getController().findRow(accidentsViolationsSummaryTableLocator, rowText);
            WebElement cellElement = rowElement.findElements(By.tagName("td")).get(columnIndex);
            //Cell changes to input once list is invoked - use name attribute for each
            By cellInputLocator = By.name("c" + columnIndex);
            By listLocator = By.className("x-list-plain");
            try {
                getController().clickRightEdgeAndWait(cellElement, listLocator);
                } catch (Exception e) {
                getController().clickAndWaitFluently(cellElement, listLocator);
                }
            getController().setTextAndTab(cellInputLocator,listItem);
        }
    }

}
