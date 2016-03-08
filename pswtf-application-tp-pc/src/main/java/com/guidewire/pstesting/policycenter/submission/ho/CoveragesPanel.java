package com.guidewire.pstesting.policycenter.submission.ho;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;

/**
 * This class represents the coverages card of the homeowners policy
 * submission coverages wizard step.
 */
public class CoveragesPanel extends PolicyCenterComponent {
    public static final String BASE_ID       = CoveragesScreen.BASE_ID + "HOMainCoveragesHOEPanelSet:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "1");  // Coverages section 1 title

    static final By fireDwellingLimitLocator       = By.id(BASE_ID + "coveragePatterIterId1:1:CoverageInputSet:CovPatternInputGroup:0:CovTermInputSet:DirectTermInput-inputEl");
    static final By homeownersDwellingLimitLocator = By.id(BASE_ID + "coveragePatterIterId1:1:CoverageInputSet:CovPatternInputGroup:0:CovTermPOCHOEInputSet:DirectTermInput-inputEl");

    public CoveragesPanel(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public CoveragesPanel waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public CoveragesPanel setHomeownersDwellingLimit(String dwellingLimit) {
        getController().type(homeownersDwellingLimitLocator, dwellingLimit).waitUntilUpdateDone();
        return this;
    }

    public CoveragesPanel setFireDwellingLimit(String dwellingLimit) {
        getController().type(fireDwellingLimitLocator, dwellingLimit).waitUntilUpdateDone();
        return this;
    }
}
