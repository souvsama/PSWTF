package com.guidewire.pstesting.policycenter.submission;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardScreenFactory;
import org.openqa.selenium.By;

/**
 * This class represents the policy information wizard step.
 */
public class PolicyInformationScreen<T> extends SubmissionWizardScreen<T> {
    public static final String BASE_ID       = SubmissionWizardScreen.BASE_ID + "LOBWizardStepGroup:SubmissionWizard_PolicyInfoScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By quoteLocator            = By.id(BASE_ID + "JobWizardToolbarButtonSet:QuoteOrReview-btnInnerEl");
    static final By organizationTypeLocator = By.id(BASE_ID + "SubmissionWizard_PolicyInfoDV:AccountInfoInputSet:OrganizationType-inputEl");

    /**
     * Constructs a <code>PolicyInformationScreen</code>.
     *
     * @param controller the <code>WebDriver</code> to use
     */
    protected PolicyInformationScreen(ScreenObjectController controller) {
        super(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public By getQuoteLocator() {
        return quoteLocator;
    }

    @SuppressWarnings("unchecked")
    public T setOrganizationType(String type) {
        getController().clickAndType(organizationTypeLocator, type);
        getController().pressTab().waitUntilUpdateDone();
        return (T)this;
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
         * Constructs a <code>PolicyInformationScreen</code>.
         */
        public SubmissionWizardScreen create() {
            return new PolicyInformationScreen(controller);
        }

        /**
         * Constructs a <code>PolicyInformationScreen</code> and waits until it is visible.
         *
         * @return the new <code>PolicyInformationScreen</code> instance
         */
        public SubmissionWizardScreen createAndWait() {
            return new PolicyInformationScreen<PolicyInformationScreen>(controller).waitUntilVisible();
        }
    }

}
