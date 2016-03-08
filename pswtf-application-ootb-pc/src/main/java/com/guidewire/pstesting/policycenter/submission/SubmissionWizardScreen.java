package com.guidewire.pstesting.policycenter.submission;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.submission.pa.QuoteScreen;
import com.guidewire.pstesting.WizardScreen;
import org.openqa.selenium.By;

public abstract class SubmissionWizardScreen<T> extends WizardScreen<T> {
    public static final String RESOURCE_BASE_NAME = "locale.pc";

    public static final String BASE_ID = "SubmissionWizard:";

    /**
     * Constructs a <code>SubmissionWizardScreen</code>.
     *
     * @param controller the <code>WebDriver</code> to use
     */
    protected SubmissionWizardScreen(ScreenObjectController controller) {
        super(controller);
    }

    /**
     * Returns the base name of the resource bundle
     *
     * @return the base name of the resource bundle, a fully qualified class name
     */
    public String getResourceBaseName() {
        return RESOURCE_BASE_NAME;
    }

    public abstract By getQuoteLocator();

    public QuoteScreen clickQuote() {
        getController().click(getQuoteLocator());
        return new QuoteScreen(getController()).waitUntilVisible();
    }
}
