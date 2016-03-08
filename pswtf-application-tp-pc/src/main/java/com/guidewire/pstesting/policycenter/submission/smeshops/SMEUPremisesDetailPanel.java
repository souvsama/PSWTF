package com.guidewire.pstesting.policycenter.submission.smeshops;

import com.guidewire.pstesting.BooleanRadioInput;
import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;

public class SMEUPremisesDetailPanel extends PolicyCenterComponent {
    public static final String BASE_ID                = SMEEULocationPopup.BASE_ID + "LocationDetailPanelSet:SMEEUPremiseDetailCV:LocationDetailDV:";
    public static final String GLOBAL_ADDRESS_BASE_ID = BASE_ID + "LocationDetailInputSet:TargetedAddressInputSet:globalAddressContainer:GlobalAddressInputSet:";

    static final By locationCodeLocator    = By.id(BASE_ID + "LocationDetailInputSet:LocationCode-inputEl");
    static final By selectTradeCodeLocator = By.id(BASE_ID + "tradeCode:SelecttradeCode");

    static final By address1Locator   = By.id(GLOBAL_ADDRESS_BASE_ID + "AddressLine1-inputEl");
    static final By cityLocator       = By.id(GLOBAL_ADDRESS_BASE_ID + "City-inputEl");
    static final By stateLocator      = By.id(GLOBAL_ADDRESS_BASE_ID + "State-inputEl");
    static final By postalCodeLocator = By.id(GLOBAL_ADDRESS_BASE_ID + "PostalCode-inputEl");

    static final String anyAsbestosLocatorPrefix = BASE_ID + "anyAsbestos_";

    private BooleanRadioInput         anyAsbestosInput;
    private SMEEUTradeCodeSearchPopup tradeCodeSearchPopup;

    public SMEUPremisesDetailPanel(ScreenObjectController controller) {
        super(controller);
        tradeCodeSearchPopup = new SMEEUTradeCodeSearchPopup(controller);
        initialize();
    }

    /**
     * Returns an object that represents an element that is always present on the page.
     *
     * @return the element contained on the page
     */
    public By getPageContains() {
        return locationCodeLocator;
    }

    public boolean isVisible() {
        return getController().pageContains(locationCodeLocator);
    }

    public SMEUPremisesDetailPanel waitUntilVisible() {
        getController().waitUntilPageContains(getPageContains());
        return this;
    }

    public SMEUPremisesDetailPanel setLocationCode(String locationCode) {
        getController().type(locationCodeLocator, locationCode).waitUntilUpdateDone();
        return this;
    }

    public SMEUPremisesDetailPanel setAddress1(String address1) {
        getController().type(address1Locator, address1).waitUntilUpdateDone();
        return this;
    }

    public SMEUPremisesDetailPanel setCity(String city) {
        getController().type(cityLocator, city).waitUntilUpdateDone();
        return this;
    }

    public SMEUPremisesDetailPanel setState(String state) {
        getController().typeAndEnter(stateLocator, state);
        return this;
    }

    public SMEUPremisesDetailPanel setPostalCode(String postalCode) {
        getController().type(postalCodeLocator, postalCode).waitUntilUpdateDone();
        return this;
    }

    public SMEUPremisesDetailPanel setAnyAsbestos(Boolean value) {
        anyAsbestosInput.setValue(value);
        return this;
    }

    public SMEEUTradeCodeSearchPopup clickSelectTradeCode() {
        getController().click(selectTradeCodeLocator);
        return tradeCodeSearchPopup.waitUntilVisible();
    }

    private void initialize() {
        ScreenObjectController controller = getController();
        anyAsbestosInput = new BooleanRadioInput(controller, anyAsbestosLocatorPrefix);
    }

}
