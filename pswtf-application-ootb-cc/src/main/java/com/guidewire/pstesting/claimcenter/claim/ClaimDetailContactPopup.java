package com.guidewire.pstesting.claimcenter.claim;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.claimcenter.ClaimCenterComponent;
import org.openqa.selenium.By;


public class ClaimDetailContactPopup extends ClaimCenterComponent {
    public static final String BASE_ID       = "ClaimContactDetailPopup:ContactDetailScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By premisesDetailsTabLocator = By.id(BASE_ID + "ContactBasicsCardTab-btnInnerEl");

    private final ContactBasicsPanel contactBasicsPanel;

    public ClaimDetailContactPopup(BasicInformationScreen parent, ScreenObjectController controller) {
        super(controller);
        this.contactBasicsPanel = new ContactBasicsPanel(parent, controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public boolean isVisible() {
        return getController().pageContains(getPageContains());
    }

    @SuppressWarnings("unchecked")
    public ClaimDetailContactPopup waitUntilVisible() {
        getController().waitUntilPageContains(getPageContains());
        return this;
    }

    public ContactBasicsPanel selectBasicsTab() {
        if (!contactBasicsPanel.isVisible()) {
            getController().click(premisesDetailsTabLocator);
            contactBasicsPanel.waitUntilVisible();
        }
        return contactBasicsPanel;
    }
}
