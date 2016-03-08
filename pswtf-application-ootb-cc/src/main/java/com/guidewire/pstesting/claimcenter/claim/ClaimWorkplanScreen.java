package com.guidewire.pstesting.claimcenter.claim;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.claimcenter.ClaimCenterComponent;
import org.openqa.selenium.By;


public class ClaimWorkplanScreen extends ClaimCenterComponent {
    public static final String BASE_ID       = "ClaimWorkplan:ClaimWorkplanScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "0");
    public static final By workplanLVLocator = By.id(BASE_ID + "WorkplanLV");
    public static final By assignButtonLocator = By.id(BASE_ID + "ClaimWorkplan_AssignButton-btnInnerEl");
    public static final By completeButtonLocator = By.id(BASE_ID + "ClaimWorkplan_CompleteButton-btnInnerEl");

    public ClaimWorkplanScreen(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public ClaimWorkplanScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public ClaimWorkplanScreen selectAllActivities() {
        getController().selectAllRowsCheckbox(workplanLVLocator);
/*
        WebElement lvElement = getController().findScreenObject(workplanLVLocator);
        WebElement selectAllElement = lvElement.findElement(By.cssSelector(".x-grid-checkcolumn"));
        getController().clickAndWait(selectAllElement, 1);
*/
        return this;
    }

    public AssignActivitiesScreen clickAssignButton() {
        getController().click(assignButtonLocator);
        return new AssignActivitiesScreen(getController()).waitUntilVisible();
    }

    public ClaimWorkplanScreen clickCompleteButton() {
        getController().click(completeButtonLocator);
        return this;
    }
}
