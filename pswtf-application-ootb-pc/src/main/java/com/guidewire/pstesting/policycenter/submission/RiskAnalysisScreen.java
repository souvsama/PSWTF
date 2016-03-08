package com.guidewire.pstesting.policycenter.submission;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardScreenFactory;
import org.openqa.selenium.By;

public class RiskAnalysisScreen extends SubmissionWizardScreen<RiskAnalysisScreen> {
    public static final String BASE_ID = SubmissionWizardScreen.BASE_ID + "Job_RiskAnalysisScreen:";

    static final By quoteLocator          = By.id(BASE_ID + "JobWizardToolbarButtonSet:QuoteOrReview-btnInnerEl");
    static final By addIssueButtonLocator = By.id(BASE_ID + "RiskAnalysisCV_tb:AddManualIssue-btnInnerEl");
    static final By uwIssuesTabLocator    = By.id(BASE_ID + "RiskAnalysisCV:EvaluationIssuesCardTab-btnInnerEl");

    public RiskAnalysisScreen(ScreenObjectController controller) {
        super(controller);
    }

    public By getPageContains() {
        return uwIssuesTabLocator;
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
         * Constructs a <code>RiskAnalysisScreen</code>.
         */
        public SubmissionWizardScreen create() {
            return new RiskAnalysisScreen(controller);
        }

        /**
         * Constructs a <code>RiskAnalysisScreen</code> and waits until it is visible.
         *
         * @return the new <code>RiskAnalysisScreen</code> instance
         */
        public SubmissionWizardScreen createAndWait() {
            return new RiskAnalysisScreen(controller).waitUntilVisible();
        }
    }
}
