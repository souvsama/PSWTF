package com.guidewire.pstesting.policycenter.submission.ho;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import org.openqa.selenium.By;

/**
 * This class represents the homeowners policy submission dwelling wizard step.
 */
public class DwellingScreen extends SubmissionWizardScreen<DwellingScreen> {
    public static final String BASE_ID       = SubmissionWizardScreen.BASE_ID + "LOBWizardStepGroup:LineWizardStepSet:HODwellingHOEScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By quoteLocator                = By.id(BASE_ID + "JobWizardToolbarButtonSet:QuoteOrReview-btnInnerEl");
    static final By detailsTabLocator           = By.id(BASE_ID + "HODwellingSingleHOEPanelSet:DwellingDetailsSingleIDTab-btnInnerEl");
    static final By protectionDetailsTabLocator = By.id(BASE_ID + "HODwellingSingleHOEPanelSet:DwellingSingleProtectionIdTab-btnInnerEl");

    private final DwellingDetailsPanel           dwellingDetailsPanel;
    private final DwellingProtectionDetailsPanel dwellingProtectionDetailsPanel;

    /**
     * Constructs a <code>DwellingScreen</code>.
     *
     * @param controller the <code>WebDriver</code> to use
     */
    public DwellingScreen(ScreenObjectController controller) {
        super(controller);
        dwellingDetailsPanel = new DwellingDetailsPanel(controller);
        dwellingProtectionDetailsPanel = new DwellingProtectionDetailsPanel(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public DwellingDetailsPanel selectDetailsTab() {
        if (!dwellingDetailsPanel.isVisible()) {
            getController().click(detailsTabLocator);
            dwellingDetailsPanel.waitUntilVisible();
        }
        return dwellingDetailsPanel;
    }

    public DwellingProtectionDetailsPanel selectProtectionDetailsTab() {
        if (!dwellingProtectionDetailsPanel.isVisible()) {
            getController().click(protectionDetailsTabLocator);
            dwellingProtectionDetailsPanel.waitUntilVisible();
        }
        return dwellingProtectionDetailsPanel;
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
         * Constructs a <code>DwellingScreen</code>.
         */
        public SubmissionWizardScreen create() {
            return new DwellingScreen(controller);
        }

        /**
         * Constructs a <code>DwellingScreen</code> and waits until it is visible.
         *
         * @return the new <code>DwellingScreen</code> instance
         */
        public SubmissionWizardScreen createAndWait() {
            return new DwellingScreen(controller).waitUntilVisible();
        }
    }
}
