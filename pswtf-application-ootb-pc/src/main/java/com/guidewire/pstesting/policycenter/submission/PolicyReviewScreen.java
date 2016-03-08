package com.guidewire.pstesting.policycenter.submission;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardScreenFactory;
import org.openqa.selenium.By;

public class PolicyReviewScreen extends SubmissionWizardScreen<PolicyReviewScreen> {
    public static final String BASE_ID       = SubmissionWizardScreen.BASE_ID + "SubmissionWizard_PolicyReviewScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By quoteLocator = By.id("SubmissionWizard:SubmissionWizard_PolicyReviewScreen:JobWizardToolbarButtonSet:QuoteOrReview-btnInnerEl");

    public PolicyReviewScreen(ScreenObjectController controller) {
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
         * Constructs a <code>PolicyReviewScreen</code>.
         */
        public SubmissionWizardScreen create() {
            return new PolicyReviewScreen(controller);
        }

        /**
         * Constructs a <code>PolicyReviewScreen</code> and waits until it is visible.
         *
         * @return the new <code>PolicyReviewScreen</code> instance
         */
        public SubmissionWizardScreen createAndWait() {
            return new PolicyReviewScreen(controller).waitUntilVisible();
        }
    }
}
