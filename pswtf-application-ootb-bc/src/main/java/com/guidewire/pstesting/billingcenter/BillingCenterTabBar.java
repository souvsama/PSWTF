package com.guidewire.pstesting.billingcenter;

import com.guidewire.pstesting.TabMenu;
import com.guidewire.pstesting.ScreenObjectController;

public class BillingCenterTabBar extends BillingCenterComponent {
    private final AccountTabMenu accountTab;
    private final PolicyTabMenu  policyTab;

    public BillingCenterTabBar(ScreenObjectController controller) {
        super(controller);
        policyTab = new PolicyTabMenu(controller);
        accountTab = new AccountTabMenu(controller);
    }

    public void clickAccountTab() {
        accountTab.click();
    }

    public AccountTabMenu clickAccountTabArrow() {
        accountTab.clickArrow();
        return accountTab;
    }

    public void clickPolicyTab() {
        policyTab.click();
    }

    public PolicyTabMenu clickPolicyTabArrow() {
        policyTab.clickArrow();
        return policyTab;
    }

    /**
     * Returns the currently selected tab menu.
     *
     * @return the currently selected tab menu
     */
    public TabMenu getSelectedTab() {
        if (accountTab.isSelected()) {
            return accountTab;
        } else if (policyTab.isSelected()) {
            return policyTab;
        }
        return null;
    }
}
