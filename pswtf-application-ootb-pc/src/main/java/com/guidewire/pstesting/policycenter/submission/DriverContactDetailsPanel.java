package com.guidewire.pstesting.policycenter.submission;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.Driver;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;

public class DriverContactDetailsPanel extends PolicyCenterComponent {
    public static final String BASE_ID = "DriversListDetailPanel:DriverDetailsCV:PolicyContactDetailsDV:";

    static final String dobFieldLocator               = BASE_ID + "PolicyContactRoleNameInputSet:DateOfBirth-inputEl";
    static final String licenseNumberFieldLocator     = BASE_ID + "LicenseInputSet:LicenseNumber-inputEl";
    static final String licenseStateFieldLocator      = BASE_ID + "LicenseInputSet:LicenseState-inputEl";
    static final String membershipLevelFieldLocator   = BASE_ID + "LicenseInputSet:MembershipLevel-inputEl";
    static final String yearFirstLicensedFieldLocator = BASE_ID + "LicenseInputSet:yearlicensed-inputEl";

    public DriverContactDetailsPanel(ScreenObjectController controller, String baseIdentifier) {
        super(controller, baseIdentifier);
    }

    public boolean isVisible() {
        return getController().pageContains(createLocator(dobFieldLocator));
    }

    public DriverContactDetailsPanel waitUntilVisible() {
        getController().waitUntilPageContains(createLocator(dobFieldLocator));
        return this;
    }

    public DriverContactDetailsPanel setDateOfBirth(String dataOfBirth) {
        getController().typeAndTab(createLocator(dobFieldLocator), dataOfBirth);
        return this;
    }

    public DriverContactDetailsPanel setLicenseNumber(String licenseNumber) {
        getController().type(createLocator(licenseNumberFieldLocator), licenseNumber);
        return this;
    }

    public DriverContactDetailsPanel setLicenseState(String licenseState) {
        getController().typeAndTab(createLocator(licenseStateFieldLocator), licenseState);
        return this;
    }

    public DriverContactDetailsPanel setMembershipLevel(String membershipLevel) {
        getController().typeAndTab(createLocator(membershipLevelFieldLocator), membershipLevel);
        return this;
    }

    public DriverContactDetailsPanel setYearFirstLicensed(String firstLicensed) {
        getController().typeAndTab(createLocator(yearFirstLicensedFieldLocator), firstLicensed);
        return this;
    }

    public DriverContactDetailsPanel setContactDetails(Driver driver) {
        setDateOfBirth(driver.getDateOfBirth());
        setLicenseNumber(driver.getLicenseNumber());
        setLicenseState(driver.getLicenseState());
        setMembershipLevel(driver.getMembershipLevel());
        setYearFirstLicensed(driver.getYearFirstLicensed());
        return this;
    }
}
