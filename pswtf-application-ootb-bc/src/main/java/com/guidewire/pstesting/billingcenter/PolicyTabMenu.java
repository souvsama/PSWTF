package com.guidewire.pstesting.billingcenter;

import com.guidewire.pstesting.ApplicationPage;
import com.guidewire.pstesting.ScreenObjectController;
import org.openqa.selenium.By;

public class PolicyTabMenu extends BillingCenterTabMenu {
    public static final String BASE_ID = "TabBar:PoliciesTab:";

    static final By policyTabLocator                = By.id("TabBar:PoliciesTab");
    static final By policyNumberLocator             = By.id(BASE_ID + "PolicyNumberSearchItem-inputEl");
    static final By policyNumberSearchButtonLocator = By.id(BASE_ID + "PolicyNumberSearchItem_Button");

    public PolicyTabMenu(ScreenObjectController controller) {
        super(controller, policyTabLocator, policyNumberLocator);
    }

    public void clickArrow() {
        getController().clickRightEdgeAndWait(getLocator(), policyNumberLocator);
    }

    public PolicyTabMenu setPolicyNumber(String policyNumber) {
        if (!getController().elementExists(policyNumberLocator)) {
            clickArrow();
        }
        getController().type(policyNumberLocator, policyNumber).waitUntilUpdateDone();
        return this;
    }

    public ApplicationPage clickPolicyNumberSearch() {
        if (!getController().elementExists(policyNumberSearchButtonLocator)) {
            clickArrow();
        }
        getController().click(policyNumberSearchButtonLocator);

        // Wait for a page to become visible
        getController().waitUntilPageContains(getController().getWaitTimeout(),
                                              PolicyDetailSummaryScreen.PAGE_CONTAINS, PolicySearchScreen.PAGE_CONTAINS);

        // Which screen was displayed?
        if (getController().pageContains(PolicyDetailSummaryScreen.PAGE_CONTAINS)) {
            return new PolicyDetailSummaryScreen(getController());
        } else if (getController().pageContains(PolicySearchScreen.PAGE_CONTAINS)) {
            return new PolicySearchScreen(getController());
        }
        return null;
    }
}
