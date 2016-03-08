package com.guidewire.pstesting.policycenter.account;

import com.guidewire.pstesting.ApplicationComponent;
import com.guidewire.pstesting.ScreenObjectController;
import org.openqa.selenium.By;

public class AccountInformationPage extends ApplicationComponent {
    static final String BASE_ID       = "NewAccount:NewAccountScreen:";
    static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By searchButtonLocator             = By.id(BASE_ID + "NewAccountSearchDV:SearchAndResetInputSet:SearchLinksInputSet:Search");
    static final By companyNameFieldLocator         = By.id(BASE_ID + "NewAccountSearchDV:GlobalContactNameInputSet:Name-inputEl");
    static final By firstNameNameFieldLocator       = By.id(BASE_ID + "NewAccountSearchDV:GlobalPersonNameInputSet:FirstName-inputEl");
    static final By lastNameNameFieldLocator        = By.id(BASE_ID + "NewAccountSearchDV:GlobalPersonNameInputSet:LastName-inputEl");
    static final By createNewAccountButtonLocator   = By.id(BASE_ID + "NewAccountButton-btnInnerEl");
    static final By createNewCompanyMenuItemLocator = By.id(BASE_ID + "NewAccountButton:NewAccount_Company-textEl");
    static final By createNewPersonMenuItemLocator  = By.id(BASE_ID + "NewAccountButton:NewAccount_Person-textEl");

    public AccountInformationPage(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public AccountInformationPage waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public AccountInformationPage setCompanyName(String name) {
        getController().type(companyNameFieldLocator, name).waitUntilUpdateDone();
        return this;
    }

    public AccountInformationPage setFirstName(String firstName) {
        getController().type(firstNameNameFieldLocator, firstName).waitUntilUpdateDone();
        return this;
    }

    public AccountInformationPage setLastName(String lastName) {
        getController().type(lastNameNameFieldLocator, lastName).waitUntilUpdateDone();
        return this;
    }

    public AccountInformationPage clickSearch() {
        // TODO: Does this need to be smarter? What if the search results in match? (SLC)
        getController().clickAndWait(searchButtonLocator, createNewAccountButtonLocator);  // Click search and wait for "Create New Account" button
        return this;
    }

    public CreateAccountScreen clickCreateNewCompanyAccount() {
        getController().clickAndWait(createNewAccountButtonLocator, createNewCompanyMenuItemLocator);
        getController().click(createNewCompanyMenuItemLocator);
        return new CreateAccountScreen(getController()).waitUntilVisible();
    }

    public CreateAccountScreen clickCreateNewPersonAccount() {
        getController().clickAndWait(createNewAccountButtonLocator, createNewCompanyMenuItemLocator);
        getController().click(createNewPersonMenuItemLocator);
        return new CreateAccountScreen(getController()).waitUntilVisible();
    }
}
