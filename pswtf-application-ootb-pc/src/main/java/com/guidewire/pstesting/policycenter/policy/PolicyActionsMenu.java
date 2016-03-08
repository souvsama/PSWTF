package com.guidewire.pstesting.policycenter.policy;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;

public class PolicyActionsMenu extends PolicyCenterComponent{
    public static final String BASE_ID       = "PolicyFile:PolicyFileMenuActions:";
    public static final By MENU_CONTAINS = By.id(BASE_ID + "WizardMenuActions_Goto-textEl");

    public static final By actionButtonLocator  = By.id("PolicyFile:PolicyFileMenuActions-btnInnerEl");
    public static final By cancelMenuLocator    = By.id(BASE_ID + "PolicyFileMenuActions_NewWorkOrder:PolicyFileMenuActions_CancelPolicy-itemEl");
    public static final By reinstateMenuLocator = By.id(BASE_ID + "PolicyFileMenuActions_NewWorkOrder:PolicyFileMenuActions_ReinstatePolicy-itemEl");

    public PolicyActionsMenu(ScreenObjectController controller) {
        super(controller);
    }

    public PolicyActionsMenu click(){
        getController().clickAndWait(actionButtonLocator, MENU_CONTAINS);
        return this;
    }

    public StartCancellationScreen clickCancel(){
        this.click();
        getController().click(cancelMenuLocator);
        return new StartCancellationScreen(getController()).waitUntilVisible();
    }

    public StartReinstatementScreen clickReinstate(){
        this.click();
        getController().click(reinstateMenuLocator);
        return new StartReinstatementScreen(getController()).waitUntilVisible();
    }
}
