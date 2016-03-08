package com.guidewire.pstesting.policycenter.account;

import com.guidewire.pstesting.ApplicationComponent;
import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.utilities.JavascriptHelper;
import org.openqa.selenium.By;

public class CreateAccountScreen extends ApplicationComponent {
    private JavascriptHelper jscript = new JavascriptHelper(getController().getWebDriver());
    public static final String BASE_ID                     = "CreateAccount:CreateAccountScreen:";
    public static final String CREATE_ACCOUNT_DV_ID        = BASE_ID + "CreateAccountDV:";
    public static final String GLOBAL_ADDRESS_INPUT_SET_ID = CREATE_ACCOUNT_DV_ID + "AddressInputSet:globalAddressContainer:GlobalAddressInputSet:";

    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By homePhoneFieldLocator    = By.id(CREATE_ACCOUNT_DV_ID + "CreateAccountContactInputSet:HomePhone:GlobalPhoneInputSet:NationalSubscriberNumber-inputEl");
    static final By countryFieldLocator      = By.id(GLOBAL_ADDRESS_INPUT_SET_ID + "Country-inputEl");
    static final By address1FieldLocator     = By.id(GLOBAL_ADDRESS_INPUT_SET_ID + "AddressLine1-inputEl");
    static final By cityFieldLocator         = By.id(GLOBAL_ADDRESS_INPUT_SET_ID + "City-inputEl");
    static final By stateFieldLocator        = By.id(GLOBAL_ADDRESS_INPUT_SET_ID + "State-inputEl");
    static final By countyFieldLocator       = By.id(GLOBAL_ADDRESS_INPUT_SET_ID + "County-inputEl");
    static final By postalCodeFieldLocator   = By.id(GLOBAL_ADDRESS_INPUT_SET_ID + "PostalCode-inputEl");
    static final By addressTypeFieldLocator  = By.id(CREATE_ACCOUNT_DV_ID + "AddressType-inputEl");
    static final By descriptionFieldLocator  = By.id(CREATE_ACCOUNT_DV_ID + "AddressDescription-inputEl");
    static final By organizationFieldLocator = By.id(CREATE_ACCOUNT_DV_ID + "ProducerSelectionInputSet:Producer-inputEl");
    static final By organizationTypeFieldLocator = By.id(CREATE_ACCOUNT_DV_ID + "OrgType-inputEl");
    static final By producerCodeFieldLocator = By.id(CREATE_ACCOUNT_DV_ID + "ProducerSelectionInputSet:ProducerCode-inputEl");

    static final By checkDuplicatesLocator          = By.id(BASE_ID + "CheckForDuplicates-btnInnerEl");
    static final By updateButtonLocator              = By.id(BASE_ID + "Update-btnInnerEl");
    static final By forceDupCheckUpdateButtonLocator = By.id(BASE_ID + "ForceDupCheckUpdate-btnInnerEl");

    public CreateAccountScreen(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public CreateAccountScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public CreateAccountScreen setHomePhone(String phone) {
        getController().setValue(homePhoneFieldLocator, phone);
        return this;
    }

    public CreateAccountScreen setCountry(String country) {
        getController().typeAndEnter(countryFieldLocator, country);
        return this;
    }

    public CreateAccountScreen setAddress1(String address) {
        getController().type(address1FieldLocator, address).waitUntilUpdateDone();
        return this;
    }

    public CreateAccountScreen setCity(String city) {
        getController().clickTypeAndTab(cityFieldLocator, city);
        return this;
    }

    public CreateAccountScreen setCounty(String county) {
        getController().typeAndEnter(countyFieldLocator, county);
        return this;
    }

    public CreateAccountScreen setState(String state) {
        getController().clickTypeAndTab(stateFieldLocator, state);
        return this;
    }

    public CreateAccountScreen setPostalCode(String zip) {
        getController().typeAndTab(postalCodeFieldLocator, zip);
        return this;
    }

    public CreateAccountScreen setAddressType(String type) {
        getController().typeAndTab(addressTypeFieldLocator, type);
        return this;
    }

    public CreateAccountScreen setDescription(String description) {
        getController().type(descriptionFieldLocator, description).waitUntilUpdateDone();
        return this;
    }

    public CreateAccountScreen setOrganization(String organization) {
        getController().clickTypeAndTab(organizationFieldLocator, organization)
                .waitUntilPageContains(producerCodeFieldLocator);
        return this;
    }

    public CreateAccountScreen setOrgType(String orgType) {
        getController().typeAndEnter(organizationTypeFieldLocator, orgType).waitUntilUpdateDone();
        return this;
    }

    public CreateAccountScreen setProducerCode(String producerCode) {
        getController().typeAndEnter(producerCodeFieldLocator, producerCode);
        getController().pressTab();
        return this;
    }

    public AccountFileSummaryScreen clickUpdate() {
        if (getController().elementExists(checkDuplicatesLocator)){
            getController().click(forceDupCheckUpdateButtonLocator);
        }
        else {
            if (getController().elementExists(updateButtonLocator))
                    getController().click(updateButtonLocator);
        }
        getController().waitUntilUpdateDone();
        String errors = getController().checkForErrors();
        if (!errors.isEmpty()){logger.warn(errors);}
        return new AccountFileSummaryScreen(getController()).waitUntilVisible();
    }

}
