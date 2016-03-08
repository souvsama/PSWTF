package com.guidewire.pstesting.policycenter.submission.gbpa;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;

public class GBDriverContactDetailsPanel extends PolicyCenterComponent {
    public static final String BASE_ID = GBDriversScreen.BASE_ID +
            "GBPADriversPanelSet:DriversListDetailPanel:DriverDetailsCV:PolicyContactDetailsDV:";

    static final By dobFieldLocator              = By.id(BASE_ID + "PolicyContactRoleNameInputSet:DateOfBirth-inputEl");
    static final By sexFieldLocator              = By.id(BASE_ID + "PolicyContactRoleNameInputSet:Gender-inputEl");
    static final By licenseStartDateFieldLocator = By.id(BASE_ID + "LicenseInputSet:LicenseDate-inputEl");
    static final By licenseNumberFieldLocator    = By.id(BASE_ID + "LicenseInputSet:LicenseNumber-inputEl");

    public GBDriverContactDetailsPanel(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(dobFieldLocator);
    }

    public GBDriverContactDetailsPanel waitUntilVisible() {
        getController().waitUntilPageContains(dobFieldLocator);
        return this;
    }

    public GBDriverContactDetailsPanel setDateOfBirth(String dataOfBirth) {
        getController().typeAndTab(dobFieldLocator, dataOfBirth).waitUntilUpdateDone();
        return this;
    }

    public GBDriverContactDetailsPanel setSex(String sex) {
        getController().typeAndTab(sexFieldLocator, sex).waitUntilUpdateDone();
        return this;
    }

    public GBDriverContactDetailsPanel setLicenseStartDate(String licenseStart) {
        getController().typeAndTab(licenseStartDateFieldLocator, licenseStart).waitUntilUpdateDone();
        return this;
    }

    public GBDriverContactDetailsPanel setLicenseNumber(String licenseNumber) {
        getController().type(licenseNumberFieldLocator, licenseNumber).waitUntilUpdateDone();
        return this;
    }
}
