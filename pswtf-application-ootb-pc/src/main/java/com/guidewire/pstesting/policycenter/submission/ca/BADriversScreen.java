package com.guidewire.pstesting.policycenter.submission.ca;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.Driver;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import org.openqa.selenium.By;

public class BADriversScreen extends SubmissionWizardScreen<BADriversScreen> {
    public static final String BASE_ID       = SubmissionWizardScreen.BASE_ID + "LOBWizardStepGroup:LineWizardStepSet:BADriversScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By quoteLocator           = By.id(BASE_ID + "JobWizardToolbarButtonSet:QuoteOrReview-btnInnerEl");
    static final By addDriverButtonLocator = By.id(BASE_ID + "BADriversScreenToolbar:AddDriverDirectly-btnInnerEl");

    private final BADriverDetailsPopup driverPopup;

    public BADriversScreen(ScreenObjectController controller) {
        super(controller);
        driverPopup = new BADriverDetailsPopup(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public BADriverDetailsPopup clickAddDriver() {
        getController().click(addDriverButtonLocator);
        return driverPopup.waitUntilVisible();
    }

    public By getQuoteLocator() {
        return quoteLocator;
    }

    public BADriversScreen addDriver(Driver driver) {
        BADriverDetailsPopup driverDetailsPopup = clickAddDriver();
        driverDetailsPopup.setFistName(driver.getFirstName());
        driverDetailsPopup.setLastName(driver.getLastName());
        driverDetailsPopup.setDateOfBirth(driver.getDateOfBirth());
        driverDetailsPopup.setLicenseNumber(driver.getLicenseNumber());
        driverDetailsPopup.setLicenseState(driver.getLicenseState());
        driverDetailsPopup.clickOk();
        waitUntilVisible();  // Wait until this screen becomes visible again
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
         * Constructs a <code>BADriversScreen</code>.
         */
        public SubmissionWizardScreen create() {
            return new BADriversScreen(controller);
        }

        /**
         * Constructs a <code>BADriversScreen</code> and waits until it is visible.
         *
         * @return the new <code>BADriversScreen</code> instance
         */
        public SubmissionWizardScreen createAndWait() {
            return new BADriversScreen(controller).waitUntilVisible();
        }
    }
}
