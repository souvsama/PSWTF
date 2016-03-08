package com.guidewire.pstesting.claimcenter.claim;


import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.claimcenter.ClaimCenterComponent;
import org.openqa.selenium.By;

public class AssignActivitiesScreen extends ClaimCenterComponent {
    public static final String BASE_ID       = "AssignActivitiesPopup:AssignmentPopupScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");
    public static final By searchRadioButtonLocator = By.id(BASE_ID + "AssignmentPopupDV:FromSearch_Choice-inputEl");
    public static final By usernameLocator = By.id(BASE_ID + "AssignmentSearchDV:Username-inputEl");
    public static final By searchLocator = By.id(BASE_ID + "AssignmentSearchDV:SearchAndResetInputSet:SearchLinksInputSet:Search");
    public static final By assignLocator = By.id(BASE_ID + "AssignmentUserLV:0:_Select");

    public AssignActivitiesScreen(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public AssignActivitiesScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public AssignActivitiesScreen clickSearchRadioButton() {
        getController().clickAndWait(searchRadioButtonLocator, usernameLocator);
        return this;
    }

    public AssignActivitiesScreen setUsernameSearchCriteria(String username) {
        getController().clickTypeAndTab(usernameLocator, username);
        return this;
    }

    public AssignActivitiesScreen clickSearch() {
        getController().clickAndWait(searchLocator, 1);
        return this;
    }

    public ClaimWorkplanScreen clickAssignButton() {
        getController().click(assignLocator);
        return new ClaimWorkplanScreen(getController()).waitUntilVisible();
    }
}
