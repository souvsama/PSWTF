package com.guidewire.pstesting.contactmanager;

import com.guidewire.pstesting.ApplicationComponent;
import com.guidewire.pstesting.ApplicationPage;
import com.guidewire.pstesting.ScreenObjectController;
import org.openqa.selenium.By;

public class ContactDetailScreen extends ApplicationComponent implements ApplicationPage {
    public static final String BASE_ID       = "ContactDetail:ABContactDetailScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By basicsTabLocator = By.id(BASE_ID + "ContactBasicsCardTab-btnInnerEl");

    private boolean editing = false; // Flag indicating if the screen is currently in edit mode

    private final PersonContactBasicsPanel personContactBasicsPanel;

    public ContactDetailScreen(ScreenObjectController controller) {
        super(controller);
        personContactBasicsPanel = new PersonContactBasicsPanel(this, controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public ContactDetailScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    /**
     * Indicates if the details screen is currently in edit mode. The default
     * value is <code>false</code>.
     *
     * @return <code>true</code> if the details screen is in edit mode; <code>false</code>
     *         otherwise.
     */
    public boolean isEditing() {
        return editing;
    }

    protected void setEditing(boolean editing) {
        this.editing = editing;
    }

    /**
     * Returns the <code>PersonContactBasicsPanel</code>. The panel will be selected, if not already,
     * before being returned.
     *
     * @return the <code>PersonContactBasicsPanel</code> associated with the screen
     */
    public PersonContactBasicsPanel getPersonContactBasicsPanel() {
        if (!personContactBasicsPanel.isVisible()) {
            getController().click(basicsTabLocator);
            personContactBasicsPanel.waitUntilVisible();
        }
        return personContactBasicsPanel;
    }
}
