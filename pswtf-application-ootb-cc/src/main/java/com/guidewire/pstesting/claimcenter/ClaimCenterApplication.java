package com.guidewire.pstesting.claimcenter;

import com.guidewire.pstesting.ApplicationPage;
import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.SuiteApplication;
import com.guidewire.pstesting.TabMenu;
import com.guidewire.pstesting.claimcenter.claim.ClaimTabMenu;
import com.guidewire.pstesting.claimcenter.claim.ClaimWizardStepSet;
import com.guidewire.pstesting.claimcenter.desktop.DesktopPage;
import com.guidewire.pstesting.claimcenter.desktop.DesktopTabMenu;

public class ClaimCenterApplication extends SuiteApplication {
    public static final String RESOURCE_BASE_NAME = "locale.cc";

    /* Product identifiers */
    public static final String PERSONAL_AUTO_PRODUCT   = "PersonalAutoLine";
    public static final String COMMERCIAL_AUTO_PRODUCT = "BusinessAutoLine";
    public static final String HOMEOWNERS_AUTO_PRODUCT = "HomeownersLine";

    private final ClaimCenterTabBar tabBar;

    protected ClaimCenterApplication(ScreenObjectController controller) {
        super(controller);
        tabBar = new ClaimCenterTabBar(controller);
    }

    public ClaimCenterApplication(ScreenObjectController controller, String host, String port, String folderName) {
        super(controller, host, port, folderName);
        tabBar = new ClaimCenterTabBar(controller);
        controller.setResourceBaseName(RESOURCE_BASE_NAME);
    }

    public ClaimCenterTabBar getTabBar() {
        return tabBar;
    }

    /**
     * Returns the currently selected page.
     *
     * @return the currently selected <code>ApplicationPage</code>. If unknown,
     *         <code>null</code> is returned.
     */
    public ApplicationPage getSelectedPage() {
        // Determine current page based on which menu tab is selected
        TabMenu tabMenu = tabBar.getSelectedTab();
        if (DesktopTabMenu.class.isInstance(tabMenu)) {
            return new DesktopPage(getController());
        }
        return null;
    }

    /**
     * Initiate a new claim
     *
     * @param product the <code>Product</code> to initiate a new claim on
     *
     * @return the <code>ClaimWizardStepSet</code> associated with the specified product
     */
    public ClaimWizardStepSet newClaim(Product product) {
        ClaimTabMenu claimTabMenu = getTabBar().clickClaimTabArrow();
        return claimTabMenu.clickNewClaim(product);
    }

}

