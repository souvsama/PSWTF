package com.guidewire.pstesting.policycenter.account;

import com.guidewire.pstesting.ApplicationComponent;
import com.guidewire.pstesting.ApplicationPage;
import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.NewSubmissionPage;
import org.openqa.selenium.By;

public class AccountPage extends ApplicationComponent implements ApplicationPage {
    public static final String BASE_ID       = "AccountFile_Summary:AccountFile_SummaryScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    public final AccountActionsMenu actionsMenu;

    public AccountPage(ScreenObjectController controller) {
        super(controller);
        actionsMenu = new AccountActionsMenu(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public AccountPage waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public AccountActionsMenu getActionsMenu() {
        return actionsMenu;
    }

    public NewSubmissionPage newSubmission() {
        return actionsMenu.click().clickNewSubmission();
    }
}
