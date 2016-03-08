package com.guidewire.pstesting.policycenter.submission.domeu;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import org.openqa.selenium.By;

/**
 * This class represents the domestic property policy submission coverages wizard step.
 */
public class DOMEUCoveragesScreen extends SubmissionWizardScreen<DOMEUCoveragesScreen> {
    public static final String BASE_ID       = SubmissionWizardScreen.BASE_ID + "LOBWizardStepGroup:LineWizardStepSet:DOMEULineCoveragesScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By quoteLocator = By.id(BASE_ID + "JobWizardToolbarButtonSet:QuoteOrReview-btnInnerEl");

    private DOMEUDwellingCoveragesPanel dwellingCoveragesPanel;

    /**
     * Constructs a <code>CoveragesScreen</code>.
     *
     * @param controller the <code>WebDriver</code> to use
     */
    public DOMEUCoveragesScreen(ScreenObjectController controller) {
        super(controller);
        dwellingCoveragesPanel = new DOMEUDwellingCoveragesPanel(getController());
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public By getQuoteLocator() {
        return quoteLocator;
    }

    public DOMEUDwellingCoveragesPanel getDwellingCoveragesPanel() {
        return dwellingCoveragesPanel;
    }

    //================================================================================
    // Factory
    //================================================================================

    public static class Factory implements WizardScreenFactory {
        private final ScreenObjectController controller;

        public Factory(ScreenObjectController controller) {
            this.controller = controller;
        }

        /**
         * Constructs a <code>DOMEUCoveragesScreen</code>.
         */
        public SubmissionWizardScreen create() {
            return new DOMEUCoveragesScreen(controller);
        }

        /**
         * Constructs a <code>DOMEUCoveragesScreen</code> and waits until it is visible.
         *
         * @return the new <code>DOMEUCoveragesScreen</code> instance
         */
        public SubmissionWizardScreen createAndWait() {
            return new DOMEUCoveragesScreen(controller).waitUntilVisible();
        }
    }
}
