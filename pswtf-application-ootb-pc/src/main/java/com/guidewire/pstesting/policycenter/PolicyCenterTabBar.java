package com.guidewire.pstesting.policycenter;

import com.guidewire.pstesting.TabMenu;
import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.account.AccountPage;
import com.guidewire.pstesting.policycenter.account.AccountTabMenu;
import com.guidewire.pstesting.policycenter.administration.AdministrationTabMenu;
import com.guidewire.pstesting.policycenter.desktop.DesktopTabMenu;
import com.guidewire.pstesting.policycenter.policy.PolicyTabMenu;

public class PolicyCenterTabBar extends PolicyCenterComponent {
    private final DesktopTabMenu        desktopTab;
    private final AccountTabMenu        accountTab;
    private final PolicyTabMenu         policyTab;
    private final AdministrationTabMenu administrationTab;

    public PolicyCenterTabBar(ScreenObjectController controller) {
        super(controller);
        desktopTab = new DesktopTabMenu(controller);
        accountTab = new AccountTabMenu(controller);
        policyTab = new PolicyTabMenu(controller);
        administrationTab = new AdministrationTabMenu(controller);
    }

    public void clickDesktopTab() {
        desktopTab.click();
    }

    public void clickDesktopTabArrow() {
        desktopTab.clickArrow();
    }

    public AccountPage clickAccountTab() {
        accountTab.click();
        return new AccountPage(getController());
    }

    public AccountTabMenu clickAccountTabArrow() {
        accountTab.clickArrow();
        return accountTab;
    }

    public PolicyTabMenu clickPolicyTabArrow(){
        policyTab.clickArrow();
        return policyTab;
    }

    /**
     * Returns the currently selected tab menu.
     *
     * @return the currently selected tab menu
     */
    public TabMenu getSelectedTab() {
        if (desktopTab.isSelected()) {
            return desktopTab;
        } else if (accountTab.isSelected()) {
            return accountTab;
        } else if (administrationTab.isSelected()) {
            return administrationTab;
        }
        return null;
    }
}
