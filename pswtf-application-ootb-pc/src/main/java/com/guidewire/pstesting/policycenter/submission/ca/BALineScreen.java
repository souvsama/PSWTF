package com.guidewire.pstesting.policycenter.submission.ca;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import org.openqa.selenium.By;

public class BALineScreen extends SubmissionWizardScreen<BALineScreen> {
    public static final String BASE_ID       = "SubmissionWizard:LOBWizardStepGroup:LineWizardStepSet:BALineScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By quoteLocator        = By.id(BASE_ID + "JobWizardToolbarButtonSet:QuoteOrReview-btnInnerEl");
    static final By coveragesTabLocator = By.id(BASE_ID + "BALinePanelSet:CoveragesCardTab-btnInnerEl");

    private final BALineCoveragesPanel coveragesPanel;

    /**
     * Constructs a <code>BALineScreen</code>.
     *
     * @param controller the <code>WebDriver</code> to use
     */
    public BALineScreen(ScreenObjectController controller) {
        super(controller);
        coveragesPanel = new BALineCoveragesPanel(this, controller);
    }

    /**
     * Returns an object that identifies an element that is always present on the page.
     *
     * @return the element contained on the page
     */
    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public BALineCoveragesPanel selectCoveragesTab() {
        if (!coveragesPanel.isVisible()) {
            getController().click(coveragesTabLocator);
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
         * Constructs a <code>BusinessAutoLineScreen</code>.
         */
        public SubmissionWizardScreen create() {
            return new BALineScreen(controller);
        }

        /**
         * Constructs a <code>BusinessAutoLineScreen</code> and waits until it is visible.
         *
         * @return the new <code>BusinessAutoLineScreen</code> instance
         */
        public SubmissionWizardScreen createAndWait() {
            return new BALineScreen(controller).waitUntilVisible();
        }
    }
}
