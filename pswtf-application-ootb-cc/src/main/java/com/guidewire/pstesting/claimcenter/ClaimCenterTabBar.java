package com.guidewire.pstesting.claimcenter;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.TabMenu;
import com.guidewire.pstesting.claimcenter.claim.ClaimTabMenu;
import com.guidewire.pstesting.claimcenter.desktop.DesktopTabMenu;

public class ClaimCenterTabBar extends ClaimCenterComponent {
    private final DesktopTabMenu desktopTab;
    private final ClaimTabMenu claimTab;

    public ClaimCenterTabBar(ScreenObjectController controller) {
        super(controller);
        desktopTab = new DesktopTabMenu(controller);
        claimTab = new ClaimTabMenu(controller);
    }

    public void clickDesktopTab() {
        desktopTab.click();
    }

    public void clickDesktopTabArrow() {
        desktopTab.clickArrow();
    }

    public ClaimTabMenu clickClaimTabArrow() {
        claimTab.clickArrow();
        return claimTab;
    }

    /**
     * Returns the currently selected tab menu.
     *
     * @return the currently selected tab menu
     */
    public TabMenu getSelectedTab() {
        if (desktopTab.isSelected()) {
            return desktopTab;
        }
        return null;
    }
}

