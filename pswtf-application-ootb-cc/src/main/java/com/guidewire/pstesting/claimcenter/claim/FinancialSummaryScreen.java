package com.guidewire.pstesting.claimcenter.claim;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.claimcenter.ClaimCenterComponent;
import com.guidewire.pstesting.claimcenter.claim.payment.ClaimPaymentWizardStepSet;
import org.openqa.selenium.By;


public class FinancialSummaryScreen extends ClaimCenterComponent {
    public static final String BASE_ID       = "ClaimFinancialsSummary:ClaimFinancialsSummaryScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");
    public static final By paymentDropdownLocator = By.id(BASE_ID + "FinancialsSummaryPanelSet:FinancialsSummaryLV:2:FinancialsSummaryLabel:FinancialsSummaryLabelMenuIcon");
    public static final By createPaymentMenuLocator = By.id(BASE_ID + "FinancialsSummaryPanelSet:FinancialsSummaryLV:2:FinancialsSummaryLabel:QuickMenu_CreateCheck-textEl");

    public FinancialSummaryScreen(ScreenObjectController controller) {
        super(controller);
    }

    public ClaimPaymentWizardStepSet createPayment() {
        getController().click(paymentDropdownLocator);
        getController().click(createPaymentMenuLocator);
        return new ClaimPaymentWizardStepSet(getController()).waitUntilVisible();
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public FinancialSummaryScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }
}
