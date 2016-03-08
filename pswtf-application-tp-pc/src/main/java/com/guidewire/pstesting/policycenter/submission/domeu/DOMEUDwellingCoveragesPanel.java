package com.guidewire.pstesting.policycenter.submission.domeu;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;

public class DOMEUDwellingCoveragesPanel extends PolicyCenterComponent {
    public static final String BASE_ID = DOMEUCoveragesScreen.BASE_ID +
            "DOMEUPerDwellingCoveragePanelSet:DwellingCoverageDetailCV:DOMEUDwellingMainCoveragesPanelSet:";

    static final By sumInsuredLocator
            = By.id(BASE_ID + "DOM_UK_Building:DOMEUCovCategoryDV:0:DOMCoverageInputSet:CoverageInputSet:CovPatternInputGroup:0:CovTermInputSet:DirectTermInput-inputEl");

    public DOMEUDwellingCoveragesPanel(ScreenObjectController controller) {
        super(controller);
    }

    public By getPageContains() {
        return sumInsuredLocator;
    }

    public boolean isVisible() {
        return getController().pageContains(sumInsuredLocator);
    }

    public DOMEUDwellingCoveragesPanel waitUntilVisible() {
        getController().waitUntilPageContains(getPageContains());
        return this;
    }

    public DOMEUDwellingCoveragesPanel setSumInsured(String sumInsured) {
        getController().type(sumInsuredLocator, sumInsured).waitUntilUpdateDone();
        return this;
    }
}
