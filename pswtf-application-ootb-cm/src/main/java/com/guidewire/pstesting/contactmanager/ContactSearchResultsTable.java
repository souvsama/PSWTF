package com.guidewire.pstesting.contactmanager;

import com.guidewire.pstesting.Table;
import com.guidewire.pstesting.ScreenObjectController;
import org.openqa.selenium.By;

public class ContactSearchResultsTable extends Table {
    static final String BASE_ID = "ABContactSearch:ABContactSearchScreen:";

    static final By offeringsTableLocator = By.id(BASE_ID + "ContactSearchResultsLV-body");

    public ContactSearchResultsTable(ScreenObjectController controller) {
        super(controller, offeringsTableLocator);
    }

    /**
     * Returns the locator for the web element used to select a row in the table.
     *
     * @param rowIndex the index of the row
     *
     * @return the locator for the web element used to select a row in the table
     */
    protected By getRowSelectionLocator(int rowIndex) {
        StringBuilder builder = new StringBuilder();
        builder.append(BASE_ID)
                .append("ContactSearchResultsLV:")
                .append(rowIndex)
                .append(":DisplayName");
        return By.id(builder.toString());
    }

    @Override
    protected void clickRowElement(By locator) {
        getController().click(locator);
    }
}
