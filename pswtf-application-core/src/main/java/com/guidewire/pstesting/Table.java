package com.guidewire.pstesting;

import com.guidewire.pstesting.utilities.JavascriptHelper;
import org.openqa.selenium.By;

public abstract class Table extends ApplicationComponent {
    private final By locator;

    public Table(ScreenObjectController controller, By locator) {
        super(controller);
        this.locator = locator;
    }

    public boolean isVisible() {
        return getController().pageContains(locator);
    }

    public Table waitUntilVisible() {
        getController().waitUntilPageContains(locator);
        return this;
    }

    public void selectRow(String text) {
        int rowIndex = getController().findRowIndex(locator, text);
        if (rowIndex == -1) {
            throw new IllegalArgumentException("No row with the text \"" + text + "\" exists in the table.");
        }
        clickRowElement(getRowSelectionLocator(rowIndex));
    }

    public void selectRowWithText(String text) {
        int rowIndex = getController().findRowIndex(locator, text);
        if (rowIndex == -1) {
            throw new IllegalArgumentException("No row with the text \"" + text + "\" exists in the table.");
        }
        clickRowElement(getRowSelectionLocator(rowIndex));
    }

    public boolean rowExists(String text) {
        return (getController().findRowIndex(locator, text) != -1);
    }

    public boolean lvPagerVisible(String locatorId){
        JavascriptHelper jscript = new JavascriptHelper(getController().getWebDriver());
        return jscript.isLVPagerVisible(locatorId);
    }

    /**
     * Returns the locator for the web element used to select a row in the table.
     *
     * @param rowIndex the index of the row
     *
     * @return the locator for the web element used to select a row in the table
     */
    protected abstract By getRowSelectionLocator(int rowIndex);

    protected void clickRowElement(By locator) {
        getController().click(locator).waitUntilUpdateDone();
        //getController().clickRightEdge(locator);
    }


}

