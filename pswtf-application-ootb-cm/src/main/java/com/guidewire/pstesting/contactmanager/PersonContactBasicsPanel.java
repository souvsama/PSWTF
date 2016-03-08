package com.guidewire.pstesting.contactmanager;

import com.guidewire.pstesting.ApplicationComponent;
import com.guidewire.pstesting.ScreenObjectController;
import org.openqa.selenium.By;

public class PersonContactBasicsPanel extends ApplicationComponent {
    public static final String BASE_ID = ContactDetailScreen.BASE_ID + "ContactBasicsDV:";

    static final By editButtonLocator   = By.id(ContactDetailScreen.BASE_ID + "ContactBasicsDV_tb:Edit-btnInnerEl");
    static final By updateButtonLocator = By.id(ContactDetailScreen.BASE_ID + "ContactBasicsDV_tb:Update-btnInnerEl");
    static final By cancelButtonLocator = By.id(ContactDetailScreen.BASE_ID + "ContactBasicsDV_tb:Cancel-btnInnerEl");
    static final By deleteButtonLocator = By.id(ContactDetailScreen.BASE_ID + "ContactBasicsDV_tb:ABContactDetailScreen_DeleteButton-btnInnerEl");

    static final By firstNameLocator          = By.id(BASE_ID + "GlobalPersonNameInputSet:FirstName-inputEl");
    static final By lastNameLocator           = By.id(BASE_ID + "GlobalPersonNameInputSet:LastName-inputEl");
    static final By dataOfBirthLocator        = By.id(BASE_ID + "DateOfBirth-inputEl");
    static final By genderLocator             = By.id(BASE_ID + "Gender-inputEl");
    static final By licenseNumberLocator      = By.id(BASE_ID + "LicenseNumber-inputEl");
    static final By licenseStateLocator       = By.id(BASE_ID + "LicenseState-inputEl");
    static final By homePhoneLocator          = By.id(BASE_ID + "notVendor:Home:GlobalPhoneInputSet:PhoneDisplay-inputEl");
    static final By homePhoneEditingLocator   = By.id(BASE_ID + "notVendor:Home:GlobalPhoneInputSet:NationalSubscriberNumber-inputEl");
    static final By mobilePhoneLocator        = By.id(BASE_ID + "notVendor:Cell:GlobalPhoneInputSet:PhoneDisplay-inputEl");
    static final By mobilePhoneEditingLocator = By.id(BASE_ID + "notVendor:Cell:GlobalPhoneInputSet:NationalSubscriberNumber-inputEl");

    private final ContactDetailScreen parent;

    public PersonContactBasicsPanel(ContactDetailScreen parent, ScreenObjectController controller) {
        super(controller);
        this.parent = parent;
    }

    public By getPageContains() {
        return firstNameLocator;
    }

    public boolean isVisible() {
        return getController().pageContains(firstNameLocator);
    }

    public PersonContactBasicsPanel waitUntilVisible() {
        getController().waitUntilPageContains(firstNameLocator);
        return this;
    }

    public PersonContactBasicsPanel clickEdit() {
        getController().click(editButtonLocator).waitForElement(updateButtonLocator);
        parent.setEditing(true);
        return this;
    }

    public PersonContactBasicsPanel clickUpdate() {
        getController().click(updateButtonLocator).waitForElement(editButtonLocator);
        parent.setEditing(false);
        return this;
    }

    public PersonContactBasicsPanel clickCancel() {
        getController().click(cancelButtonLocator).waitForElement(editButtonLocator);
        parent.setEditing(false);
        return this;
    }

    public String getFirstName() {
        return getValue(firstNameLocator);
    }

    public String getLastName() {
        return getValue(lastNameLocator);
    }

    public String getHomePhone() {
        return getValue(parent.isEditing() ? homePhoneEditingLocator : homePhoneLocator);
    }

    public String getMobilePhone() {
        return getValue(parent.isEditing() ? mobilePhoneEditingLocator : mobilePhoneLocator);
    }

    public String getDataOfBirth() {
        return getValue(dataOfBirthLocator);
    }

    public String getGender() {
        return getValue(genderLocator);
    }

    public String getLicenseNumber() {
        return getValue(licenseNumberLocator);
    }

    public String getLicenseState() {
        return getValue(licenseStateLocator);
    }

    private String getValue(By locator) {
        ScreenObjectController controller = getController();
        if (parent.isEditing()) {
            return controller.getValue(locator);
        } else if (controller.elementExists(locator)) {
            return controller.getText(locator);
        }
        return null;
    }
}