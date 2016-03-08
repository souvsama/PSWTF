package com.guidewire.pstesting.contactmanager;

import com.guidewire.pstesting.ApplicationPage;
import com.guidewire.pstesting.ScreenObjectController;
import org.openqa.selenium.By;

public class ContactSearchScreen extends ContactManagerComponent implements ApplicationPage {
    /* Resource bundle keys */
    public static final String ZERO_RESULTS_MESSAGE = "contactSearch.message.zeroResults";

    public static final String BASE_ID       = "ABContactSearch:ABContactSearchScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By contactTypeLocator  = By.id(BASE_ID + "ContactSearchDV:ContactSubtype-inputEl");
    static final By nameLocator         = By.id(BASE_ID + "ContactSearchDV:GlobalContactNameInputSet:Name-inputEl");
    static final By firstNameLocator    = By.id(BASE_ID + "ContactSearchDV:GlobalPersonNameInputSet:FirstName-inputEl");
    static final By lastNameLocator     = By.id(BASE_ID + "ContactSearchDV:GlobalPersonNameInputSet:LastName-inputEl");
    static final By searchButtonLocator = By.id(BASE_ID + "ContactSearchDV:SearchAndResetInputSet:SearchLinksInputSet:Search");
    static final By resetButtonLocator  = By.id(BASE_ID + "ContactSearchDV:SearchAndResetInputSet:SearchLinksInputSet:Reset");
    static final By recordCountLocator  = By.id(BASE_ID + "ContactSearchResultsLV:_RecordCount");

    private final ContactSearchResultsTable contactSearchResultsTable;
    private final ContactDetailScreen       contactDetailScreen;

    public ContactSearchScreen(ScreenObjectController controller) {
        super(controller);
        contactSearchResultsTable = new ContactSearchResultsTable(controller);
        contactDetailScreen = new ContactDetailScreen(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public ContactSearchScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    public ContactSearchResultsTable getContactSearchResultsTable() {
        return contactSearchResultsTable;
    }

    public ContactSearchScreen setContactType(String type) {
        getController().clickTypeAndTab(contactTypeLocator, type).sleep(1000);
        return this;
    }

    public ContactSearchScreen setName(String name) {
        getController().type(nameLocator, name);
        return this;
    }

    public ContactSearchScreen setFirstName(String firstName) {
        getController().type(firstNameLocator, firstName);
        return this;
    }

    public ContactSearchScreen setLastName(String lastName) {
        getController().type(lastNameLocator, lastName);
        return this;
    }

    public boolean searchReturnedResults() {
        return !searchReturnedZeroResults();
    }

    public boolean searchContainsResult(String name) {
        return getContactSearchResultsTable().rowExists(name);
    }

    public boolean searchReturnedZeroResults() {
        return getController().pageContains(getController().getString(getResourceBaseName(), ZERO_RESULTS_MESSAGE));
    }

    public ContactSearchScreen clickSearch() {
        getController().click(searchButtonLocator).sleep(1000);
        return this;
    }

    public ContactSearchScreen clickReset() {
        getController().click(resetButtonLocator).sleep(1000);
        return this;
    }

    public ContactDetailScreen selectContactFromSearchResults(String name) {
        getContactSearchResultsTable().selectRowWithText(name);
        contactDetailScreen.waitUntilVisible();
        return contactDetailScreen;
    }
}
