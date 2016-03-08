package com.guidewire.pstesting.billingcenter;

import com.guidewire.pstesting.ApplicationPage;
import com.guidewire.pstesting.ScreenObjectController;
import org.openqa.selenium.By;

public class AccountTabMenu extends BillingCenterTabMenu {
    public static final String BASE_ID = "TabBar:AccountsTab:";

    static final By accountTabLocator                = By.id("TabBar:AccountsTab");
    static final By accountNumberLocator             = By.id(BASE_ID + "AccountNumberSearchItem-inputEl");
    static final By accountNumberSearchButtonLocator = By.id(BASE_ID + "AccountNumberSearchItem_Button");

    public AccountTabMenu(ScreenObjectController controller) {
        super(controller, accountTabLocator, accountNumberLocator);
    }

    public void clickArrow() {
        getController().clickRightEdgeAndWait(getLocator(), accountNumberLocator);
    }

    public AccountTabMenu setPolicyNumber(String policyNumber) {
        if (!getController().elementExists(accountNumberLocator)) {
            clickArrow();
        }
        getController().typeAndEnter(accountNumberLocator, policyNumber);
        return this;
    }

    public ApplicationPage clickAccountNumberSearch() {
        if (!getController().elementExists(accountNumberSearchButtonLocator)) {
            clickArrow();
        }
        getController().click(accountNumberSearchButtonLocator);

        // Wait for a page to become visible
        getController().waitUntilPageContains(getController().getWaitTimeout(),
                                              AccountDetailSummaryScreen.PAGE_CONTAINS, AccountSearchScreen.PAGE_CONTAINS);

        // Which screen was displayed?
        if (getController().pageContains(AccountDetailSummaryScreen.PAGE_CONTAINS)) {
            return new AccountDetailSummaryScreen(getController());
        } else if (getController().pageContains(PolicySearchScreen.PAGE_CONTAINS)) {
            return new AccountSearchScreen(getController());
        }
        return null;
    }
}
