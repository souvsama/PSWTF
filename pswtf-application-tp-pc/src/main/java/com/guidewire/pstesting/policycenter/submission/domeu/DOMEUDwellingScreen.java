package com.guidewire.pstesting.policycenter.submission.domeu;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import org.openqa.selenium.By;
// SubmissionWizard:LOBWizardStepGroup:LineWizardStepSet:DOMEUDwellingScreen:DOMEUMultiDwellingPanelSet:DwellingsListDetailPanel_tb:Add-btnInnerEl

/**
 * This class represents the domestic property policy submission dwelling wizard step.
 */
public class DOMEUDwellingScreen extends SubmissionWizardScreen<DOMEUDwellingScreen> {
    public static final String BASE_ID       = SubmissionWizardScreen.BASE_ID + "LOBWizardStepGroup:LineWizardStepSet:DOMEUDwellingScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By quoteLocator                = By.id(BASE_ID + "JobWizardToolbarButtonSet:QuoteOrReview-btnInnerEl");
    static final By detailsTabLocator           = By.id(BASE_ID + "DOMEUMultiDwellingPanelSet:DwellingsListDetailPanel:DwellingInfoTab-btnInnerEl");
    static final By createDwellingButtonLocator = By.id(BASE_ID + "DOMEUMultiDwellingPanelSet:DwellingsListDetailPanel_tb:Add-btnInnerEl");

    private final DOMEUDwellingDetailsPanel dwellingDetailsPanel;

    /**
     * Constructs a <code>DwellingScreen</code>.
     *
     * @param controller the <code>WebDriver</code> to use
     */
    public DOMEUDwellingScreen(ScreenObjectController controller) {
        super(controller);
        dwellingDetailsPanel = new DOMEUDwellingDetailsPanel(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public DOMEUDwellingDetailsPanel clickCreateDwelling() {
        getController().click(createDwellingButtonLocator);
        return dwellingDetailsPanel.waitUntilVisible();
    }

    public DOMEUDwellingDetailsPanel selectDetailsTab() {
        if (!dwellingDetailsPanel.isVisible()) {
            getController().click(detailsTabLocator);
            dwellingDetailsPanel.waitUntilVisible();
        }
        return dwellingDetailsPanel;
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
         * Constructs a <code>DOMEUDwellingScreen</code>.
         */
        public SubmissionWizardScreen create() {
            return new DOMEUDwellingScreen(controller);
        }

        /**
         * Constructs a <code>DOMEUDwellingScreen</code> and waits until it is visible.
         *
         * @return the new <code>DOMEUDwellingScreen</code> instance
         */
        public SubmissionWizardScreen createAndWait() {
            return new DOMEUDwellingScreen(controller).waitUntilVisible();
        }
    }
}
