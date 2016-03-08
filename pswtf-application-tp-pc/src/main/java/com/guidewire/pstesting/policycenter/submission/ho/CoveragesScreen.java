package com.guidewire.pstesting.policycenter.submission.ho;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import org.openqa.selenium.By;

/**
 * This class represents the homeowners policy submission coverages wizard step.
 */
public class CoveragesScreen extends SubmissionWizardScreen<CoveragesScreen> {
    public static final String BASE_ID       = SubmissionWizardScreen.BASE_ID + "LOBWizardStepGroup:LineWizardStepSet:HOCoveragesHOEScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By quoteLocator      = By.id(BASE_ID + "JobWizardToolbarButtonSet:QuoteOrReview-btnInnerEl");
    static final By detailsTabLocator = By.id(BASE_ID + "HODwellingSingleHOEPanelSet:DwellingDetailsSingleIDTab-btnInnerEl");

    private final CoveragesPanel coveragesPanel;

    /**
     * Constructs a <code>CoveragesScreen</code>.
     *
     * @param controller the <code>WebDriver</code> to use
     */
    public CoveragesScreen(ScreenObjectController controller) {
        super(controller);
        coveragesPanel = new CoveragesPanel(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public CoveragesPanel selectCoveragesTab() {
        if (!coveragesPanel.isVisible()) {
            getController().click(detailsTabLocator);
            coveragesPanel.waitUntilVisible();
        }
        return coveragesPanel;
    }

    public By getQuoteLocator() {
        return quoteLocator;
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
         * Constructs a <code>CoveragesScreen</code>.
         */
        public SubmissionWizardScreen create() {
            return new CoveragesScreen(controller);
        }

        /**
         * Constructs a <code>CoveragesScreen</code> and waits until it is visible.
         *
         * @return the new <code>CoveragesScreen</code> instance
         */
        public SubmissionWizardScreen createAndWait() {
            return new CoveragesScreen(controller).waitUntilVisible();
        }
    }
}
