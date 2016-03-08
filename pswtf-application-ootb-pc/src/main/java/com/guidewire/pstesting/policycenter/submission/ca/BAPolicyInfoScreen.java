package com.guidewire.pstesting.policycenter.submission.ca;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.submission.PolicyInformationScreen;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;

/**
 * This class represents the business auto policy submission policy information wizard step.
 */
// TODO: Looks like this class is no longer needed. Replace with super class (SLC)
public class BAPolicyInfoScreen extends PolicyInformationScreen<BAPolicyInfoScreen> {
    public static final String BASE_ID = PolicyInformationScreen.BASE_ID + "SubmissionWizard_PolicyInfoDV:";

    /**
     * Constructs a <code>BAPolicyInfoScreen</code>.
     *
     * @param controller the <code>WebDriver</code> to use
     */
    public BAPolicyInfoScreen(ScreenObjectController controller) {
        super(controller);
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
         * Constructs a <code>BAPolicyInfoScreen</code>.
         */
        public SubmissionWizardScreen create() {
            return new BAPolicyInfoScreen(controller);
        }

        /**
         * Constructs a <code>BAPolicyInfoScreen</code> and waits until it is visible.
         *
         * @return the new <code>BAPolicyInfoScreen</code> instance
         */
        public SubmissionWizardScreen createAndWait() {
            return new BAPolicyInfoScreen(controller).waitUntilVisible();
        }
    }
}
