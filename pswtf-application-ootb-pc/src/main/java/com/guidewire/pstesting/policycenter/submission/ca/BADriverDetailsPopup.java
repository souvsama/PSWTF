package com.guidewire.pstesting.policycenter.submission.ca;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;

public class BADriverDetailsPopup extends PolicyCenterComponent {
    public static final String BASE_ID       = "BADriverPopup:BADriverScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By okButtonLocator      = By.id(BASE_ID + "Update-btnInnerEl");
    static final By firstNameLocator     = By.id(BASE_ID + "BADriversDV:GlobalPersonNameInputSet:FirstName-inputEl");
    static final By lastNameLocator      = By.id(BASE_ID + "BADriversDV:GlobalPersonNameInputSet:LastName-inputEl");
    static final By dobLocator           = By.id(BASE_ID + "BADriversDV:DateOfBirth-inputEl");
    static final By licenseNumberLocator = By.id(BASE_ID + "BADriversDV:LicenseNumber-inputEl");
    static final By licenseStateLocator  = By.id(BASE_ID + "BADriversDV:LicenseState-inputEl");
    static final By licenseYearLocator   = By.id(BASE_ID + "BADriversDV:YearLicensed-inputEl");

    public BADriverDetailsPopup(ScreenObjectController controller) {
        super(controller);
    }

    public By getPageContains() {
        return firstNameLocator;
    }

    public boolean isVisible() {
        return getController().pageContains(firstNameLocator);
    }

    public BADriverDetailsPopup waitUntilVisible() {
        getController().waitUntilPageContains(getPageContains());
        return this;
    }

    public void clickOk() {
        getController().click(okButtonLocator);
    }

    public BADriverDetailsPopup setFistName(String firstName) {
        getController().type(firstNameLocator, firstName).waitUntilUpdateDone();
        return this;
    }

    public BADriverDetailsPopup setLastName(String lastName) {
        getController().type(lastNameLocator, lastName).waitUntilUpdateDone();
        return this;
    }

    public BADriverDetailsPopup setDateOfBirth(String dob) {
        getController().type(dobLocator, dob).waitUntilUpdateDone();
        return this;
    }

    public BADriverDetailsPopup setLicenseNumber(String license) {
        getController().type(licenseNumberLocator, license).waitUntilUpdateDone();
        return this;
    }

    public BADriverDetailsPopup setLicenseState(String licenseState) {
        getController().typeAndTab(licenseStateLocator, licenseState);
        return this;
    }

    public BADriverDetailsPopup setLicenseYear(String licenseYear) {
        getController().type(licenseYearLocator, licenseYear).waitUntilUpdateDone();
        return this;
    }
}
