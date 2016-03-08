package com.guidewire.pstesting.policycenter.submission.pa;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardScreen;
import org.openqa.selenium.By;

public class FormsScreen extends SubmissionWizardScreen<FormsScreen> {
    public static final String BASE_ID       = "SubmissionWizard:FormsScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar:");

    public FormsScreen(ScreenObjectController controller) {
        super(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public By getQuoteLocator() {
        throw new UnsupportedOperationException("Quote operation not available");
    }
}
