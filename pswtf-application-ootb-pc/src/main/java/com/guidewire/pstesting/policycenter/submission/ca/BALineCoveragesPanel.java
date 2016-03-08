package com.guidewire.pstesting.policycenter.submission.ca;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardScreen;
import org.openqa.selenium.By;

public class BALineCoveragesPanel extends SubmissionWizardScreen<BALineCoveragesPanel> {
    public static final String BASE_ID                 = SubmissionWizardScreen.BASE_ID + "LOBWizardStepGroup:LineWizardStepSet:BALineScreen:";
    public static final String LIABILITY_GROUP_BASE_ID = BASE_ID + "BALinePanelSet:BALineCoveragePanelSet:";

    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By productLocator = By.id(BASE_ID + "BALinePanelSet:BALineCoveragePanelSet:BALineDV:PolicyType-inputEl");
    static final By fleetLocator   = By.id(BASE_ID + "BALinePanelSet:BALineCoveragePanelSet:BALineDV:Fleet-inputEl");

    static final By liabilityLimitLocator = By.id(LIABILITY_GROUP_BASE_ID + "baLineLiabCatIterator:0:BALiabGroupInputSet:CoverageInputSet:CovPatternInputGroup:0:CovTermInputSet:PackageTermInput-inputEl");

    private final SubmissionWizardScreen parent;

    public BALineCoveragesPanel(SubmissionWizardScreen parent, ScreenObjectController controller) {
        super(controller);
        this.parent = parent;
    }

    /**
     * Returns an object that represents an element that is always present on the page.
     *
     * @return the element contained on the page
     */
    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public BALineCoveragesPanel setProduct(String product) {
        getController().typeAndTab(productLocator, product);
        return this;
    }

    public BALineCoveragesPanel setFleet(String fleet) {
        getController().typeAndTab(fleetLocator, fleet);
        return this;
    }

    public BALineCoveragesPanel setLiabilityLimit(String limit) {
        getController().clickTypeAndTab(liabilityLimitLocator, limit);
        return this;
    }

    public By getQuoteLocator() {
        return parent.getQuoteLocator();
    }
}
