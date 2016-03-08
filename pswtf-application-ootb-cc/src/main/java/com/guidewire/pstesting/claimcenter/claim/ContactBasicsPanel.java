package com.guidewire.pstesting.claimcenter.claim;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.claimcenter.ClaimCenterComponent;
import org.openqa.selenium.By;


public class ContactBasicsPanel extends ClaimCenterComponent {
    public static final String BASE_ID         = ClaimDetailContactPopup.BASE_ID + "ContactBasicsDV:";
    public static final String TOOLBAR_BASE_ID = ClaimDetailContactPopup.BASE_ID + "ContactBasicsDV_tb:ContactDetailToolbarButtonSet:";

    static final By okButtonLocator     = By.id(TOOLBAR_BASE_ID + "Update-btnInnerEl");
    static final By cancelButtonLocator = By.id(TOOLBAR_BASE_ID + "Cancel-btnInnerEl");
    static final By homePhoneLocator    = By.id(BASE_ID + "PersonContactInfoInputSet:Home:GlobalPhoneInputSet:NationalSubscriberNumber-inputEl");
    static final By mobilePhoneLocator  = By.id(BASE_ID + "PersonContactInfoInputSet:Cell:GlobalPhoneInputSet:NationalSubscriberNumber-inputEl");

    private final BasicInformationScreen parent;

    public ContactBasicsPanel(BasicInformationScreen parent, ScreenObjectController controller) {
        super(controller);
        this.parent = parent;
    }

    /**
     * Returns an object that represents an element that is always present on the page.
     *
     * @return the element contained on the page
     */
    public By getPageContains() {
        return okButtonLocator;
    }

    public boolean isVisible() {
        return getController().pageContains(okButtonLocator);
    }

    public ContactBasicsPanel waitUntilVisible() {
        getController().waitUntilPageContains(getPageContains());
        return this;
    }

    public BasicInformationScreen clickOk() {
        getController().click(okButtonLocator);
        return parent.waitUntilVisible();
    }

    public BasicInformationScreen clickCancel() {
        getController().click(cancelButtonLocator);
        return parent.waitUntilVisible();
    }

    public ContactBasicsPanel setHomePhone(String phone) {
        getController().setValue(homePhoneLocator, phone);
        return this;
    }

    public ContactBasicsPanel setMobilePhone(String phone) {
        getController().setValue(mobilePhoneLocator, phone);
        return this;
    }
}
