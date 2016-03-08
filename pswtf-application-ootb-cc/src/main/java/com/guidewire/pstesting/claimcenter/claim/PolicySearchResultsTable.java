package com.guidewire.pstesting.claimcenter.claim;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.Table;
import org.openqa.selenium.By;


public class PolicySearchResultsTable extends Table {
    static final String BASE_ID = "FNOLWizard:FNOLWizard_FindPolicyScreen:FNOLWizardFindPolicyPanelSet:";
    static final By lvResultsLocator = By.id(BASE_ID + "PolicyResultLV-body");

    public PolicySearchResultsTable(ScreenObjectController controller){
        super (controller,lvResultsLocator);
    }

    /**
     * Returns the locator for the web element used to select a row in the product offerings table.
     *
     * @param rowIndex the index of the row
     *
     * @return the locator for the web element used to select a row in the table
     */
    protected By getRowSelectionLocator(int rowIndex) {
        StringBuilder builder = new StringBuilder();
        builder.append("FNOLWizard:FNOLWizard_FindPolicyScreen:FNOLWizardFindPolicyPanelSet:PolicyResultLV:")
                .append(rowIndex)
                .append(":selectButton");
        return By.id(builder.toString());
    }
}
