package com.guidewire.pstesting.policycenter.submission;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import com.guidewire.pstesting.policycenter.policy.PolicyActionsMenu;
import com.guidewire.pstesting.policycenter.policy.StartCancellationScreen;
import com.guidewire.pstesting.policycenter.policy.StartReinstatementScreen;
import org.openqa.selenium.By;

public class PolicySummaryScreen extends PolicyCenterComponent {
    public static final String BASE_ID       = "PolicyFile_Summary:Policy_SummaryScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "0");

    static final By policyNumberLocator = By.id(BASE_ID + "Policy_Summary_PolicyDV:PolicyNumber-inputEl");

    public PolicyActionsMenu policyActionsMenu = new PolicyActionsMenu(getController());

    public PolicySummaryScreen(ScreenObjectController controller) {
        super(controller);
    }

    public String getPolicyNumber() {
        return getController().getText(policyNumberLocator);
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    @SuppressWarnings("unchecked")
    public PolicySummaryScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public PolicyActionsMenu getPolicyActionsMenu(){return policyActionsMenu;}

    public StartCancellationScreen cancelPolicy(){
        return policyActionsMenu.clickCancel();
    }

    public StartReinstatementScreen reinstatePolicy(){
        return policyActionsMenu.clickReinstate();
    }
}
