package com.guidewire.pstesting.policycenter;

import com.guidewire.pstesting.QuestionSet;
import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import org.openqa.selenium.By;

public class PreQualificationScreen extends SubmissionWizardScreen<PreQualificationScreen> {
    public static final String BASE_ID       = SubmissionWizardScreen.BASE_ID + "SubmissionWizard_PreQualificationScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By policyTypeLocator = By.id(BASE_ID + "SubmissionWizard_PolicyTypeInputSet:PolicyType-inputEl");
    static final By quoteLocator      = By.id(BASE_ID + "JobWizardToolbarButtonSet:QuoteOrReview-btnInnerEl");

    protected PreQualificationScreen(ScreenObjectController controller) {
        super(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public By getQuoteLocator() {
        return quoteLocator;
    }

    public PreQualificationScreen setPolicyType(String type) {
        getController().typeAndEnter(policyTypeLocator, type);
        return this;
    }

    public QuestionSet getQuestionSet(String id) {
        return new QuestionSet(getController(), getQuestionSetLocator(Integer.valueOf(id)));
    }

    public PreQualificationScreen answerQuestion(String questionSetId, String question, String answer) {
        getQuestionSet(questionSetId)
                .waitUntilVisible()
                .answerQuestion(question, "yes".equalsIgnoreCase(answer));
        return this;
    }

    private By getQuestionSetLocator(int index) {
        return By.id(BASE_ID + "PreQualQuestionSetsDV:QuestionSetsDV:" +
                Integer.toString(index) +
                ":QuestionSetLV");
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
         * Constructs a <code>PreQualificationScreen</code>.
         */
        public SubmissionWizardScreen create() {
            return new PreQualificationScreen(controller);
        }

        /**
         * Constructs a <code>PreQualificationScreen</code> and waits until it is visible.
         *
         * @return the new <code>PreQualificationScreen</code> instance
         */
        public SubmissionWizardScreen createAndWait() {
            return new PreQualificationScreen(controller).waitUntilVisible();
        }
    }
}
