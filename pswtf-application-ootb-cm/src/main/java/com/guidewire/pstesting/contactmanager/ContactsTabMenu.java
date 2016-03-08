package com.guidewire.pstesting.contactmanager;

import com.guidewire.pstesting.TabMenu;
import com.guidewire.pstesting.ScreenObjectController;
import org.openqa.selenium.By;

public class ContactsTabMenu extends TabMenu {
    public static final String BASE_ID = "TabBar:ContactsTab:";

    static final By contactsTabLocator = By.id("TabBar:ContactsTab");
    static final By searchTabLocator   = By.id("ABContacts_ABContactSearch-textEl");

    public ContactsTabMenu(ScreenObjectController controller) {
        super(controller, contactsTabLocator, searchTabLocator);
    }

    public void clickArrow() {
        getController().clickRightEdgeAndWait(getLocator(), searchTabLocator);
    }
}
