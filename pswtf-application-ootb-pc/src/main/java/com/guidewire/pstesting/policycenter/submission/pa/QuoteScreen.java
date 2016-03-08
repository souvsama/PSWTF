package com.guidewire.pstesting.policycenter.submission.pa;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterApplication;
import com.guidewire.pstesting.policycenter.submission.JobCompleteScreen;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardScreen;
import org.openqa.selenium.By;

public class QuoteScreen extends SubmissionWizardScreen<QuoteScreen> {
    public static final String BASE_ID       = "SubmissionWizard:SubmissionWizard_QuoteScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By bindOptionsMenuLocator     = By.id(BASE_ID + "JobWizardToolbarButtonSet:BindOptions-btnInnerEl");
    static final By issuePolicyMenuItemLocator = By.id(BASE_ID + "JobWizardToolbarButtonSet:BindOptions:BindAndIssue-textEl");

    public QuoteScreen(ScreenObjectController controller) {
        super(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public By getQuoteLocator() {
        throw new UnsupportedOperationException("Next operation not available");
    }

    public QuoteScreen clickBindOptions() {
        getController().clickAndWait(bindOptionsMenuLocator, issuePolicyMenuItemLocator);
        return this;
    }

    public JobCompleteScreen clickIssuePolicy() {
        getController().clickAndWait(issuePolicyMenuItemLocator,
                                     getController().getString(getResourceBaseName(),
                                                               PolicyCenterApplication.OK_BUTTON));
        getController().click(getController().getElementByResource(PolicyCenterApplication.OK_BUTTON));
        return new JobCompleteScreen(getController()).waitUntilVisible();
    }

    public JobCompleteScreen issuePolicy() {
        return clickBindOptions().clickIssuePolicy();
    }
}
