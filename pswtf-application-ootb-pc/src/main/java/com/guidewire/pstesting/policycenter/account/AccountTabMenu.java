package com.guidewire.pstesting.policycenter.account;

import com.guidewire.pstesting.TabMenu;
import com.guidewire.pstesting.ScreenObjectController;
import org.openqa.selenium.By;

public class AccountTabMenu extends TabMenu {
    static final String BASE_ID = "TabBar:AccountTab:";

    static final By accountTabLocator          = By.id("TabBar:AccountTab");
    static final By newAccountLocator          = By.id(BASE_ID + "AccountTab_NewAccount");
    static final By accountSearchLocator       = By.id(BASE_ID + "AccountTab_AccountNumberSearchItem-inputEl");
    static final By accountSearchButtonLocator = By.id(BASE_ID + "AccountTab_AccountNumberSearchItem_Button");

    public AccountTabMenu(ScreenObjectController controller) {
        super(controller, accountTabLocator, newAccountLocator);
    }

    public void clickArrow() {  // TODO: Is this overridden method required? (SLC)
        getController().clickRightEdgeAndWait(getLocator(), newAccountLocator);
    }

    public AccountInformationPage clickNewAccount() {
        getController().click(newAccountLocator);
        return new AccountInformationPage(getController()).waitUntilVisible();
    }

    public AccountPage search(String accountNumber) {
        getController().type(accountSearchLocator, accountNumber).waitUntilUpdateDone();
        getController().click(accountSearchButtonLocator);
        return new AccountPage(getController()).waitUntilVisible();
    }
}
