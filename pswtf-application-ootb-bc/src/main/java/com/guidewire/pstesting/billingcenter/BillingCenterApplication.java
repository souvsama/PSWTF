package com.guidewire.pstesting.billingcenter;

import com.guidewire.pstesting.ApplicationPage;
import com.guidewire.pstesting.SuiteApplication;
import com.guidewire.pstesting.ScreenObjectController;

public class BillingCenterApplication extends SuiteApplication {
    public static final String RESOURCE_BASE_NAME = "locale.bc";

    private final BillingCenterTabBar tabBar;

    public BillingCenterApplication(ScreenObjectController controller, String host, String port, String folderName) {
        super(controller, host, port, folderName);
        tabBar = new BillingCenterTabBar(controller);
        controller.setResourceBaseName(RESOURCE_BASE_NAME);
    }

    public BillingCenterTabBar getTabBar() {
        return tabBar;
    }

    /**
     * Returns the currently selected page.
     *
     * @return the currently selected <code>ApplicationPage</code>. If unknown,
     *         <code>null</code> is returned.
     */
    public ApplicationPage getSelectedPage() {
        return null;
    }

    public ApplicationPage searchForAccount(String accountNumber) {
        return getTabBar().clickAccountTabArrow().setPolicyNumber(accountNumber).clickAccountNumberSearch();
    }

    public ApplicationPage searchForPolicy(String policyNumber) {
        return getTabBar().clickPolicyTabArrow().setPolicyNumber(policyNumber).clickPolicyNumberSearch();
    }
}