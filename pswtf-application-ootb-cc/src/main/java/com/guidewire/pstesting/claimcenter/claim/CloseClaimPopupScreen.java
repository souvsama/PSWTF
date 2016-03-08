package com.guidewire.pstesting.claimcenter.claim;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.claimcenter.ClaimCenterComponent;
import com.guidewire.pstesting.claimcenter.ClaimSummaryScreen;
import org.openqa.selenium.By;

/**
 * User: rgoodwin
 * Date: 04/02/2015
 */
public class CloseClaimPopupScreen extends ClaimCenterComponent {
    public static final String BASE_ID       = "CloseClaimPopup:CloseClaimScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");
    public static final By closeClaimButtonLocator = By.id(BASE_ID + "Update-btnInnerEl");

    public CloseClaimPopupScreen(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public CloseClaimPopupScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public ClaimSummaryScreen clickCloseClaimButton() {
        getController().click(closeClaimButtonLocator);
        return clickSummaryMenuLink();
    }
}
