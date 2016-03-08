package com.guidewire.pstesting.policycenter.submission.pa;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardScreen;
import org.openqa.selenium.By;

public class PaymentScreen extends SubmissionWizardScreen<PaymentScreen> {
    public static final String BASE_ID       = SubmissionWizardScreen.BASE_ID + "SubmissionWizard_PaymentScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar:");

    public PaymentScreen(ScreenObjectController controller) {
        super(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public By getQuoteLocator() {
        return null;
    }

    public SubmissionWizardScreen clickNext() {
        throw new UnsupportedOperationException("Next operation not supported");
    }
}
