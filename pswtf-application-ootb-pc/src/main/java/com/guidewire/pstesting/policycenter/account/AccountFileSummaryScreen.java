package com.guidewire.pstesting.policycenter.account;

import com.guidewire.pstesting.ScreenObjectController;
import org.openqa.selenium.By;

public class AccountFileSummaryScreen extends AccountPage {
    static final String BASE_ID       = "AccountFile_Summary:AccountFile_SummaryScreen:";
    static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By accountNumberFieldLocator = By.id(BASE_ID + "AccountFile_Summary_BasicInfoDV:AccountNumber-inputEl");

    public AccountFileSummaryScreen(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public AccountFileSummaryScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public String getAccountNumber() {
        return getController().getText(accountNumberFieldLocator);
    }
}
