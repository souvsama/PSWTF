package com.guidewire.pstesting.policycenter.policy;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterApplication;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import com.guidewire.pstesting.policycenter.submission.PolicySummaryScreen;
import org.openqa.selenium.By;

public class CancellationWizardScreen extends PolicyCenterComponent {
    public static final String BASE_ID      = "StartCancellation:StartCancellationScreen:";
    public static final By PAGE_CONTAINS    = By.id("CancellationWizard:CancellationWizard_QuoteScreen:ttlBar");
    public static final String MENU_BASE_ID = "CancellationWizard:CancellationWizard_QuoteScreen:JobWizardToolbarButtonSet:";

    public static final By cancellationIdLocator = By.id("CancellationWizard:0_header_hd-textEl");
    public static final By closeOptionsMenuLocator = By.id(MENU_BASE_ID + "CloseOptions");
    public static final By withdrawTxMenuLocator = By.id(MENU_BASE_ID + "CloseOptions:WithdrawJob-itemEl");
    public static final By bindOptionsMenuLocator = By.id(MENU_BASE_ID + "BindOptions");
    public static final By cancelNowMenuLocator = By.id(MENU_BASE_ID + "BindOptions:CancelNow-itemEl");
    public static final By scheduleCancellationMenuLocator = By.id(MENU_BASE_ID + "BindOptions:SubmitCancellation-itemEl");

    protected CancellationWizardScreen(ScreenObjectController controller) {
        super(controller);
    }

    public CancellationWizardScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    /**
     * Get the numerical id of the cancellation job
     * @return id as String
     */
    public String getCancellationId(){
        String[] temp = getCancellationText().split("\n");  // split the 'Quoted' text
        String[] temp1 = temp[0].split(" ");
        return temp1[1]; // return the second word from the 1st row confirmation text
    }

    /**
     * Return true/false indicating whether the cancellation is quoted
     * @return true - Quoted, false - not Quoted
     */
    public Boolean isQuoted(){
        String[] temp = getCancellationText().split("\n");
        return temp[1].equalsIgnoreCase("Quoted");
    }

    public PolicySummaryScreen withdrawCancellation(){
        getController().clickAndWait(closeOptionsMenuLocator, withdrawTxMenuLocator).clickAndWait(withdrawTxMenuLocator,
                getController().getString(getResourceBaseName(),
                        PolicyCenterApplication.OK_BUTTON));
        getController().click(getController().getElementByResource(PolicyCenterApplication.OK_BUTTON));
        CancellationWithdrawnScreen jobComplete = new CancellationWithdrawnScreen(getController()).waitUntilVisible();
        return jobComplete.viewPolicy();
    }

    public PolicySummaryScreen cancelNow(){
        getController().clickAndWait(bindOptionsMenuLocator, cancelNowMenuLocator).clickAndWait(cancelNowMenuLocator,
                getController().getString(getResourceBaseName(),
                        PolicyCenterApplication.OK_BUTTON));
        getController().click(getController().getElementByResource(PolicyCenterApplication.OK_BUTTON));
        CancellationWithdrawnScreen jobComplete = new CancellationWithdrawnScreen(getController()).waitUntilVisible();
        return jobComplete.viewPolicy();
    }

    public PolicySummaryScreen scheduleCancellation(){
        getController().clickAndWait(bindOptionsMenuLocator, cancelNowMenuLocator).clickAndWait(scheduleCancellationMenuLocator,
                getController().getString(getResourceBaseName(),
                        PolicyCenterApplication.OK_BUTTON));
        getController().click(getController().getElementByResource(PolicyCenterApplication.OK_BUTTON));
        CancellationWithdrawnScreen jobComplete = new CancellationWithdrawnScreen(getController()).waitUntilVisible();
        return jobComplete.viewPolicy();
    }

    /**
     * Get the entire text of the cancellation confirmation header
     * @return header as String
     */
    private String getCancellationText(){
        return getController().getText(cancellationIdLocator);
    }
}
