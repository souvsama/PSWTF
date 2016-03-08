package com.guidewire.pstesting.policycenter.submission.pa;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;

public class DriverContactDetailsPanel extends PolicyCenterComponent {
    public static final String BASE_ID = DriversScreen.BASE_ID +
            "PADriversPanelSet:DriversListDetailPanel:DriverDetailsCV:PolicyContactDetailsDV:";

    static final By dobFieldLocator           = By.id(BASE_ID + "PolicyContactRoleNameInputSet:DateOfBirth-inputEl");
    static final By licenseNumberFieldLocator = By.id(BASE_ID + "LicenseInputSet:LicenseNumber-inputEl");
    static final By licenseStateFieldLocator  = By.id(BASE_ID + "LicenseInputSet:LicenseState-inputEl");
    static final By mobilePhoneFieldLocator   = By.id(BASE_ID + "PolicyContactRoleNameInputSet:CellPhone:GlobalPhoneInputSet:NationalSubscriberNumber-inputEl");

    public DriverContactDetailsPanel(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(dobFieldLocator);
    }

    public DriverContactDetailsPanel waitUntilVisible() {
        getController().waitUntilPageContains(dobFieldLocator);
        return this;
    }

    public DriverContactDetailsPanel setDateOfBirth(String dataOfBirth) {
        getController().typeAndTab(dobFieldLocator, dataOfBirth);
        return this;
    }

    public DriverContactDetailsPanel setMobilePhone(String mobileNumber){
        getController().type(mobilePhoneFieldLocator, mobileNumber);
        return this;
    }

    public DriverContactDetailsPanel setLicenseNumber(String licenseNumber) {
        getController().type(licenseNumberFieldLocator, licenseNumber).waitUntilUpdateDone();
        return this;
    }

    public DriverContactDetailsPanel setLicenseState(String licenseState) {
        getController().clickAndType(licenseStateFieldLocator, licenseState);
        return this;
    }
}
