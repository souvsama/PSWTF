package com.guidewire.pstesting.policycenter.submission.smeshops;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;

public class SMEEUBasicCoverPanel extends PolicyCenterComponent {
    public static final String BASE_ID                         = "SMEEUPremisePopup:LocationScreen:";
    public static final String COV_PATTERN_INPUT_GROUP_BASE_ID = BASE_ID + "LocationDetailPanelSet:SMEEUPremiseDetailCV:0:SMEEUPremiseCovInputSet:CovPatternInputGroup:";

    static final By okButtonLocator         = By.id(BASE_ID + "Update-btnInnerEl");
    static final By buildingLocator         = By.id(COV_PATTERN_INPUT_GROUP_BASE_ID + "_checkbox");
    static final By buildingAmountLocator   = By.id(COV_PATTERN_INPUT_GROUP_BASE_ID + "0:CovTermInputSet:DirectTermInput-inputEl");
    static final By rentalIncomeLossLocator = By.id(COV_PATTERN_INPUT_GROUP_BASE_ID + "1:CovTermInputSet:DirectTermInput-inputEl");

    public SMEEUBasicCoverPanel(ScreenObjectController controller) {
        super(controller);
    }

    /**
     * Returns an object that represents an element that is always present on the page.
     *
     * @return the element contained on the page
     */
    public By getPageContains() {
        return buildingLocator;
    }

    public boolean isVisible() {
        return getController().pageContains(buildingLocator);
    }

    public SMEEUBasicCoverPanel waitUntilVisible() {
        getController().waitUntilPageContains(getPageContains());
        return this;
    }

    public void clickOk() {
        getController().click(okButtonLocator);
    }

    public SMEEUBasicCoverPanel enableBuildingCoverage(boolean enable) {
        ScreenObjectController controller = getController();
        // Determine if building coverage is checked
        boolean isEnabled = controller.elementExists(buildingAmountLocator);
        if (isEnabled != enable) {
            if (enable) {
                controller.click(buildingLocator).waitUntilPageContains(buildingAmountLocator);  // Check building coverage
            } else {
                controller.click(buildingLocator);
            }
            controller.waitUntilUpdateDone();
        }
        return this;
    }

    public SMEEUBasicCoverPanel setBuildingAmount(String amount) {
        getController().type(buildingAmountLocator, amount).waitUntilUpdateDone();
        return this;
    }

    public SMEEUBasicCoverPanel setRentalIncomeLoss(String amount) {
        getController().type(rentalIncomeLossLocator, amount).waitUntilUpdateDone();
        return this;
    }
}
