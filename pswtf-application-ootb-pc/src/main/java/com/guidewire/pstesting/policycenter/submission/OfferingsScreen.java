package com.guidewire.pstesting.policycenter.submission;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardScreenFactory;
import org.openqa.selenium.By;

public class OfferingsScreen extends SubmissionWizardScreen<OfferingsScreen> {
    public static final String BASE_ID       = SubmissionWizardScreen.BASE_ID + "OfferingScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By quoteLocator    = By.id(BASE_ID + "JobWizardToolbarButtonSet:QuoteOrReview-btnInnerEl");
    static final By offeringLocator = By.id(BASE_ID + "OfferingSelection-inputEl");

    public OfferingsScreen(ScreenObjectController controller) {
        super(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public By getQuoteLocator() {
        return quoteLocator;
    }

    public OfferingsScreen setOffering(String offering) {
        getController().clickAndType(offeringLocator, offering);
        return this;
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
         * Constructs a <code>OfferingsScreen</code>.
         */
        public SubmissionWizardScreen create() {
            return new OfferingsScreen(controller);
        }

        /**
         * Constructs a <code>OfferingsScreen</code> and waits until it is visible.
         *
         * @return the new <code>OfferingsScreen</code> instance
         */
        public SubmissionWizardScreen createAndWait() {
            return new OfferingsScreen(controller).waitUntilVisible();
        }
    }
}
