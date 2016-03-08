package com.guidewire.pstesting.policycenter;

import com.guidewire.pstesting.Table;
import com.guidewire.pstesting.ScreenObjectController;
import org.openqa.selenium.By;

public class ProductOfferingsTable extends Table {
    static final String BASE_ID = "NewSubmission:NewSubmissionScreen:ProductOffersDV:";
    static final String offeringsTableId = BASE_ID + "ProductSelectionLV:";

    static final By offeringsTableLocator     = By.id(BASE_ID + "ProductSelectionLV-body");
    static final By offeringsTablePageLocator = By.id(BASE_ID + "ProductSelectionLV:_ListPaging-inputEl");

    public ProductOfferingsTable(ScreenObjectController controller) {
        super(controller, offeringsTableLocator);
    }

    /**
     * Returns the current page of the product offerings table.
     *
     * @return the current page of the product offerings table or <code>null</code> if
     *         only a single page
     */
    public Integer getCurrentPage() {
        try {
            if (getController().isElementAvailable(offeringsTablePageLocator)) {
                String value = getController().getValue(offeringsTablePageLocator);
                if (value != null && value.length() > 0) {
                    return Integer.valueOf(value);
                } else {
                    logger.warn("Unable to retrieve offerings table's page value");
                }
            } else {
                logger.warn("Unable to locate offerings table's page element");
            }
        } catch (Exception e){}

        return null;
    }

    /**
     * Check if there is more than a single page
     * @return <code>true</code> if there is more than 1 page, <code>false</code> if not
     */
    public boolean isMoreThanOnePage(){
        return lvPagerVisible(offeringsTableId);
    }

    /**
     * Selects a specific page on the product offerings table.
     *
     * @param pageNumber the page number to select
     */
    public ProductOfferingsTable selectPage(String pageNumber) {
        if (getController().isElementAvailable(offeringsTablePageLocator)) {
            getController().typeAndEnter(offeringsTablePageLocator, pageNumber);
        }
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
        StringBuilder builder = new StringBuilder();
        builder.append("NewSubmission:NewSubmissionScreen:ProductOffersDV:ProductSelectionLV:")
                .append(rowIndex)
                .append(":addSubmission");
        return By.id(builder.toString());
    }
}
