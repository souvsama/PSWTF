package com.guidewire.pstesting.claimcenter;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.Table;
import org.openqa.selenium.By;


public class ActivitiesTable extends Table {
    static final String BASE_ID = "DesktopActivities:DesktopActivitiesScreen:";
    static final By lvActivitiesLocator = By.id(BASE_ID + "DesktopActivitiesLV-body");

    public ActivitiesTable(ScreenObjectController controller){
        super (controller,lvActivitiesLocator);
    }

    public ActivitiesTable getActivitiesTable(){
        return this;
    }

    /**
     * Returns the locator for the web element used to select a row in the product offerings table.
     *
     * @param rowIndex the index of the row
     *
     * @return the locator for the web element used to select a row in the table
     */
    public By getRowSelectionLocator(int rowIndex) {
        rowIndex++;
        StringBuilder builder = new StringBuilder();
        builder.append(".x-grid-checkcolumn:nth-of-type(")
                .append(rowIndex)
                .append(")");
        return By.cssSelector(builder.toString());
    }
}
