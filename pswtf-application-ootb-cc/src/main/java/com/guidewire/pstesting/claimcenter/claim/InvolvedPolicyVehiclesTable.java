package com.guidewire.pstesting.claimcenter.claim;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.Table;
import org.openqa.selenium.By;

public class InvolvedPolicyVehiclesTable extends Table {
    public static final String BASE_ID       = "FNOLWizard:FNOLWizard_PickPolicyRiskUnitsScreen:";
    public static final String listViewID = BASE_ID + "PolicySummaryVehicleLV-body";
    public static final By vehicleListViewLocator = By.id(listViewID);
    //@FindBy(css = "tr .x-grid-checkcolumn") List<WebElement> listVehicles;

    public InvolvedPolicyVehiclesTable(ScreenObjectController controller, By locator) {
        super(controller, vehicleListViewLocator);
    }

    /**
     * Returns the locator for the web element used to select a row in the table.
     *
     * @param rowIndex the index of the row
     *
     * @return the locator for the web element used to select a row in the table
     */
    @Override
    protected By getRowSelectionLocator(int rowIndex) {
        rowIndex++;
        StringBuilder builder = new StringBuilder();
        builder.append("tr:nth-child(")
                .append(rowIndex)
                .append(") .x-grid-checkcolumn");
        return By.cssSelector(builder.toString());
    }

    protected void clickRowElement(By locator) {
        getController().click(locator);
    }
}
