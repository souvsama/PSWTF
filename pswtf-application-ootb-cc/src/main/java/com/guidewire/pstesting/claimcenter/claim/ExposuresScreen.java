package com.guidewire.pstesting.claimcenter.claim;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.claimcenter.ClaimCenterComponent;
import org.openqa.selenium.By;


public class ExposuresScreen extends ClaimCenterComponent {
    public static final String BASE_ID       = "ClaimExposures:ClaimExposuresScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");
    public static final By exposuresLVLocator = By.id(BASE_ID + "ExposuresLV");
    public static final By closeExposureButtonLocator = By.id(BASE_ID + "ClaimExposures_CloseExposure-btnInnerEl");

    public ExposuresScreen(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public ExposuresScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public ExposuresScreen selectAllExposures() {
        getController().selectAllRowsCheckbox(exposuresLVLocator);
/*
        WebElement lvElement = getController().findScreenObject(exposuresLVLocator);
        WebElement selectAllElement = lvElement.findElement(By.cssSelector(".x-grid-checkcolumn"));
        getController().clickAndWait(selectAllElement, 1);
*/
        return this;
    }

    public CloseExposuresPopupScreen clickCloseExposureButton() {
        getController().click(closeExposureButtonLocator);
        return new CloseExposuresPopupScreen(getController()).waitUntilVisible();
    }
}
