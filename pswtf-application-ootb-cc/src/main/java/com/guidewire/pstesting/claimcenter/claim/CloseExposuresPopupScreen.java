package com.guidewire.pstesting.claimcenter.claim;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.claimcenter.ClaimCenterComponent;
import org.openqa.selenium.By;


public class CloseExposuresPopupScreen extends ClaimCenterComponent {
    public static final String BASE_ID       = "CloseExposurePopup:CloseExposureScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");
    public static final By closeExposureButtonLocator = By.id(BASE_ID + "Update-btnInnerEl");

    public CloseExposuresPopupScreen(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public CloseExposuresPopupScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public ExposuresScreen clickCloseExposureButtonAndConfirm() {
        getController().click(closeExposureButtonLocator)
                .pressEnter()
                .checkForErrors();
        return new ExposuresScreen(getController()).waitUntilVisible();
    }
}
