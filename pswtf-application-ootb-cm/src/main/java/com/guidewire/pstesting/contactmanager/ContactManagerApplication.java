package com.guidewire.pstesting.contactmanager;

import com.guidewire.pstesting.ApplicationPage;
import com.guidewire.pstesting.SuiteApplication;
import com.guidewire.pstesting.TabMenu;
import com.guidewire.pstesting.ScreenObjectController;

public class ContactManagerApplication extends SuiteApplication implements ApplicationPage {
    public static final String RESOURCE_BASE_NAME = "locale.cm";

    /* Resource bundle keys */
    public static final String CONTACT_TYPE_PERSON  = "contactSearch.contactType.person";
    public static final String CONTACT_TYPE_COMPANY = "contactSearch.contactType.company";

    private final ContactManagerTabBar tabBar;

    protected ContactManagerApplication(ScreenObjectController controller) {
        super(controller);
        tabBar = new ContactManagerTabBar(controller);
    }

    public ContactManagerApplication(ScreenObjectController controller, String host, String port, String folderName) {
        super(controller, host, port, folderName);
        tabBar = new ContactManagerTabBar(controller);
        controller.setResourceBaseName(RESOURCE_BASE_NAME);
    }

    public ContactManagerTabBar getTabBar() {
        return tabBar;
    }

    /**
     * Returns the currently selected page.
     *
     * @return the currently selected <code>ApplicationPage</code>. If unknown,
     *         <code>null</code> is returned.
     */
    public ApplicationPage getSelectedPage() {
        // Determine current page based on which menu tab is selected
        TabMenu tabMenu = tabBar.getSelectedTab();
        if (ContactsTabMenu.class.isInstance(tabMenu)) {
            return new ContactSearchScreen(getController());
        }
        return null;
    }

    public ContactSearchScreen searchForPerson(String firstName, String lastName) {
        ContactSearchScreen searchScreen = tabBar.clickContactsTab();
        return searchScreen.clickReset()
                .setContactType(getController().getString(CONTACT_TYPE_PERSON))
                .setFirstName(firstName)
                .setLastName(lastName)
                .clickSearch();
    }

    public ContactSearchScreen searchForCompany(String name) {
        ContactSearchScreen searchScreen = tabBar.clickContactsTab();
        return searchScreen.clickReset()
                .setContactType(getController().getString(CONTACT_TYPE_COMPANY))
                .setName(name)
                .clickSearch();
    }
}