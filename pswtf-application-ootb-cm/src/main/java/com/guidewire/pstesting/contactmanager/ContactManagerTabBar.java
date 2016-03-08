package com.guidewire.pstesting.contactmanager;

import com.guidewire.pstesting.TabMenu;
import com.guidewire.pstesting.ScreenObjectController;

public class ContactManagerTabBar extends ContactManagerComponent {
    private final ContactsTabMenu     contactsTab;
    private final ContactSearchScreen searchScreen;

    public ContactManagerTabBar(ScreenObjectController controller) {
        super(controller);
        contactsTab = new ContactsTabMenu(controller);
        searchScreen = new ContactSearchScreen(getController());
    }

    public boolean isContactTabSelected() {
        return contactsTab.isSelected();
    }

    public ContactSearchScreen clickContactsTab() {
        // No need to click the tab if it's already selected
        if (!isContactTabSelected()) {
            contactsTab.click();
            searchScreen.waitUntilVisible();
        }
        return searchScreen;
    }

    public ContactsTabMenu clickContactsTabArrow() {
        contactsTab.clickArrow();
        return contactsTab;
    }

    /**
     * Returns the currently selected tab menu.
     *
     * @return the currently selected tab menu
     */
    public TabMenu getSelectedTab() {
        if (contactsTab.isSelected()) {
            return contactsTab;
        }
        return null;
    }
}
