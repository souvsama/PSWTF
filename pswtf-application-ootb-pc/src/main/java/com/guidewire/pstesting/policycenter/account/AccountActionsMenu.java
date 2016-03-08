package com.guidewire.pstesting.policycenter.account;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.NewSubmissionPage;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;

public class AccountActionsMenu extends PolicyCenterComponent {
    public static final String BASE_ID       = "AccountFile:AccountFileMenuActions:";
    public static final By MENU_CONTAINS = By.id(BASE_ID + "AccountFileMenuActions_Create-textEl");

    static final By actionsMenuLocator   = By.id("AccountFile:AccountFileMenuActions-btnInnerEl");
    static final By newSubmissionLocator = By.id(BASE_ID + "AccountFileMenuActions_Create:AccountFileMenuActions_NewSubmission-textEl");

    public AccountActionsMenu(ScreenObjectController controller) {
        super(controller);
    }

    public AccountActionsMenu click() {
        getController().clickAndWait(actionsMenuLocator, MENU_CONTAINS);
        return this;
    }

    public NewSubmissionPage clickNewSubmission() {
        getController().click(newSubmissionLocator);
        return new NewSubmissionPage(getController()).waitUntilVisible();
    }
}
