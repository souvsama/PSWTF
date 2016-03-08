package com.guidewire.pstesting.policycenter.submission.gbpa;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import org.openqa.selenium.By;

public class GBCoveragesScreen extends SubmissionWizardScreen<GBCoveragesScreen> {
    public static final String BASE_ID       = SubmissionWizardScreen.BASE_ID + "LOBWizardStepGroup:LineWizardStepSet:GBPALineScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By quoteLocator = By.id(BASE_ID + "JobWizardToolbarButtonSet:QuoteOrReview-btnInnerEl");

    public GBCoveragesScreen(ScreenObjectController controller) {
        super(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
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
         * Constructs a <code>GBCoveragesScreen</code>.
         */
        public SubmissionWizardScreen create() {
            return new GBCoveragesScreen(controller);
        }

        /**
         * Constructs a <code>GBCoveragesScreen</code> and waits until it is visible.
         *
         * @return the new <code>GBCoveragesScreen</code> instance
         */
        public SubmissionWizardScreen createAndWait() {
            return new GBCoveragesScreen(controller).waitUntilVisible();
        }
    }
}
