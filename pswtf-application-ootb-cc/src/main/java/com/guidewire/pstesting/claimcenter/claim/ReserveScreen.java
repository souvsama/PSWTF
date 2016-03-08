package com.guidewire.pstesting.claimcenter.claim;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.claimcenter.ClaimCenterComponent;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.List;

public class ReserveScreen extends ClaimCenterComponent {
    public static final String BASE_ID                  = "NewReserveSet:NewReserveSetScreen:";
    public static final String AVAILABLE_RESERVES       = "New Available Reserves";
    public static final By PAGE_CONTAINS            = By.id(BASE_ID + "ttlBar");
    public static final By headersReserveLVLocator  = By.id(BASE_ID + "ReservesSummaryDV:EditableReservesLV");
    public static final By editableReserveLVLocator = By.id(BASE_ID + "ReservesSummaryDV:EditableReservesLV-body");
    public static final By saveButtonLocator        = By.id(BASE_ID + "Update-btnInnerEl");
    public static final By editReserveAmount        = By.name("NewAmount");
    public static final By editExposureName         = By.name("Exposure");
    public static final By editCostType             = By.name("CostType");
    public static final By editCostCategory         = By.name("CostCategory");

    public ReserveScreen(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public ReserveScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public ReserveScreen setExposure(String exposure) {
        WebElement rowElement = getLastRowInReserveLV();
        WebElement exposureCell = getRowCell(rowElement, 1); // 2nd column in the LV
        By listLocator = By.className("x-list-plain");
        try {
            getController().clickRightEdgeAndWait(exposureCell, listLocator);
        } catch (Exception e) {
            getController().clickAndWaitFluently(exposureCell, listLocator);
        }
        getController().setTextAndTab(editExposureName,exposure);
/*
        getController()
                .clickAndWait(exposureCell, 1)
                .pressDown()
                .pressEnter()
                .sleep(1000);
*/
        return this;
    }

    public ReserveScreen setCostType(String costType) {
        WebElement rowElement = getLastRowInReserveLV();
        WebElement costTypeCell = getRowCell(rowElement, 3); // 4th column in the LV
        By listLocator = By.className("x-list-plain");
        try {
            getController().clickRightEdgeAndWait(costTypeCell, listLocator);
        } catch (Exception e) {
            getController().clickAndWaitFluently(costTypeCell, listLocator);
        }
        getController().setTextAndTab(editCostType,costType);
        return this;
    }

    public ReserveScreen setCostCategory(String costCategory) {
        WebElement rowElement = getLastRowInReserveLV();
        WebElement costCategoryCell = getRowCell(rowElement, 4); // 5th column in the LV
        By listLocator = By.className("x-list-plain");
        try {
            getController().clickRightEdgeAndWait(costCategoryCell, listLocator);
        } catch (Exception e) {
            getController().clickAndWaitFluently(costCategoryCell, listLocator);
        }
        getController().setTextAndTab(editCostCategory,costCategory);
        return this;
    }

    public ReserveScreen setReserveAmount(BigDecimal reserveAmount) {
        return setReserveAmount(reserveAmount.toString());
    }

    public ReserveScreen setReserveAmount(String reserveAmount) {
        // Adjust for Single or Multi-Currency
        // Use value adjusted for columns with no text - e.g. first and last
        int colIndex = getController().findColumnIndex(headersReserveLVLocator, AVAILABLE_RESERVES);
        WebElement rowElement = getLastRowInReserveLV();
        WebElement reserveAmountCell = getRowCell(rowElement, colIndex); // 9th column in the LV (Multi-Currency) - 8th in Single Currency
        getController()
                .clickAndWait(reserveAmountCell, editReserveAmount)
                .waitUntilUpdateDone()
                .setValue(editReserveAmount, reserveAmount)
                .pressTab();
        return this;
    }

    public FinancialTransactionsScreen clickSaveButton() {
        getController().click(saveButtonLocator);
        return new FinancialTransactionsScreen(getController());
    }

    private WebElement getLastRowInReserveLV() {
        WebElement tbodyElement = getController().findScreenObject(editableReserveLVLocator).findElement(By.tagName("tbody"));
        List<WebElement> rows = tbodyElement.findElements(By.tagName("tr"));
        return rows.get(rows.size() - 1);
    }

    private WebElement getRowCell(WebElement rowElement, int index) {
        return rowElement.findElements(By.tagName("td")).get(index);
    }
}
