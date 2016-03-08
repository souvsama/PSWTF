package com.guidewire.pstesting.policycenter.policy;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import com.guidewire.pstesting.policycenter.submission.PolicySummaryScreen;
import org.openqa.selenium.By;

public class StartCancellationScreen extends PolicyCenterComponent {
    public static final String BASE_ID      = "StartCancellation:StartCancellationScreen:";
    public static final By PAGE_CONTAINS    = By.id(BASE_ID + "ttlBar");

    public static final By sourceLocator        = By.id(BASE_ID + "CancelPolicyDV:Source-inputEl");
    public static final By reasonLocator        = By.id(BASE_ID + "CancelPolicyDV:Reason-inputEl");
    public static final By refundMethodLocator  = By.id(BASE_ID + "CancelPolicyDV:CalcMethod-inputEl");
    public static final By reasonDescLocator    = By.id(BASE_ID + "CancelPolicyDV:ReasonDescription-inputEl");
    public static final By effectiveDateLocator = By.id(BASE_ID + "CancelPolicyDV:CancelDate_date-inputEl");
    public static final By startCancellationLocator = By.id(BASE_ID + "NewCancellation-btnInnerEl");
    public static final By cancelCancellationLocator = By.id(BASE_ID + "Cancel-btnInnerEl");

    protected StartCancellationScreen(ScreenObjectController controller) {
        super(controller);
    }

    public StartCancellationScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    /**
     * Enter the source of the cancellation
     * @param source as String - e.g. Carrier or Insured
     * @return  StartCancellation page
     */
    public StartCancellationScreen selectSource(String source){
        getController().setDropDownField(sourceLocator, source).waitUntilUpdateDone();
        return this;
    }

    /**
     * Enter the reason for cancellation
     * @param reason as String
     * @return  StartCancellation page
     */
    public StartCancellationScreen selectReason(String reason){
        getController().setDropDownField(reasonLocator, reason).waitUntilUpdateDone();
        return this;
    }

    /**
     * Get the value allocated for the refund method as a result of reason chosen
     * @return refund method as String
     */
    public String getRefundMethod(){
        return getController().getText(refundMethodLocator);
    }

    /**
     * Get the effective cancellation date
     * @return effective date as String
     */
    public String getEffectiveDate(){
        return getController().getValue(effectiveDateLocator);
    }

    /**
     * If not determined by reason, select the refund method
     * @param method as String
     * @return StartCancellation page
     */
    public StartCancellationScreen selectRefundMethod(String method){
        getController().setDropDownField(refundMethodLocator, method).waitUntilUpdateDone();
        return this;
    }

    /**
     * Add description for the reason selected
     * @param description as String
     * @return StartCancellation page
     */
    public StartCancellationScreen setReasonDesc(String description){
        getController().setTextField(reasonDescLocator, description);
        return this;
    }

    /**
     * Initiate the manual cancellation and check for errors
     * @return CancelConfirmationPage page
     */
    public CancellationWizardScreen startCancellation(){
        getController().click(startCancellationLocator).checkForErrors();
        return new CancellationWizardScreen(getController()).waitUntilVisible();
    }

    /**
     * Cancel the initiation of a cancellation
     * @return PolicySummaryScreen page
     */
    public PolicySummaryScreen cancelCancellation(){
        getController().click(cancelCancellationLocator);
        return new PolicySummaryScreen(getController()).waitUntilVisible();
    }
}
