package com.guidewire.pstesting.claimcenter.claim;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.claimcenter.ClaimCenterComponent;
import org.openqa.selenium.By;


public class FinancialTransactionsScreen extends ClaimCenterComponent {
    public static final String BASE_ID       = "ClaimFinancialsTransactions:ClaimFinancialsTransactionsScreen:";
    public static final String ALL_TYPES    = "All transaction types";
    public static final String RESERVE_TYPE  = "Reserves";
    public static final String CURRENCY = "$";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");
    public static final By transactionTypeLocator = By.id(BASE_ID + "TransactionsLVRangeInput-inputEl");
    public static final By transactionLVLocator = By.id(BASE_ID + "TransactionsLV-body");
    public static final By firstReserveLinkLocator = By.id(BASE_ID + "TransactionsLV:0:Amount");

    public FinancialTransactionsScreen(ScreenObjectController controller) {
        super(controller);
    }

    public ReserveDetailsScreen selectFirstReserve() {
        getController().click(firstReserveLinkLocator);
        return new ReserveDetailsScreen(getController()).waitUntilVisible();
    }

    public ReserveDetailsScreen selectReserveWithAmount(String amount){
        String logMsg = "Reserve is not in list";
        viewTransactionType(RESERVE_TYPE);
        int rowIndex = getController().findRowIndex(transactionLVLocator, CURRENCY + amount + ".00");
        if (rowIndex!=-1){
            logMsg = "Reserve in row: " + String.valueOf(rowIndex);
            getController().click(By.id(BASE_ID + "TransactionsLV:" + String.valueOf(rowIndex) + ":Amount"));
        }
        logger.info(logMsg);
        return new ReserveDetailsScreen(getController()).waitUntilVisible();
    }

    public FinancialTransactionsScreen viewAllTransactionTypes(){
        return viewTransactionType(ALL_TYPES);
    }

    public FinancialTransactionsScreen viewTransactionType(String type){
        getController().setDropDownField(transactionTypeLocator, type).waitUntilUpdateDone();
        return this;
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public FinancialTransactionsScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }
}
