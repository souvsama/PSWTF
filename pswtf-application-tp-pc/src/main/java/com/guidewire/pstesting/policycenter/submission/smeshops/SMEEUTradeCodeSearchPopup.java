package com.guidewire.pstesting.policycenter.submission.smeshops;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;

public class SMEEUTradeCodeSearchPopup extends PolicyCenterComponent {
    public static final String BASE_ID = "SMEEUTradeCodeSearchPopup:";

    static final By tradeTypeLocator      = By.id(BASE_ID + "TradeType-inputEl");
    static final By tradeCodeLocator      = By.id(BASE_ID + "TradeCode-inputEl");
    static final By searchButtonLocator   = By.id(BASE_ID + "SearchAndResetInputSet:SearchLinksInputSet:Search");
    static final By resetButtonLocator    = By.id(BASE_ID + "SearchAndResetInputSet:SearchLinksInputSet:Reset");
    static final By selectFirstRowLocator = By.id(BASE_ID + "0:_Select");

    public SMEEUTradeCodeSearchPopup(ScreenObjectController controller) {
        super(controller);
    }

    public By getPageContains() {
        return tradeTypeLocator;
    }

    public boolean isVisible() {
        return getController().pageContains(getPageContains());
    }

    @SuppressWarnings("unchecked")
    public SMEEUTradeCodeSearchPopup waitUntilVisible() {
        getController().waitUntilPageContains(getPageContains());
        return this;
    }

    public SMEEUTradeCodeSearchPopup setTradeType(String tradeType) {
        getController().typeAndEnter(tradeTypeLocator, tradeType);
        return this;
    }

    public SMEEUTradeCodeSearchPopup setTradeCode(String tradeCode) {
        getController().type(tradeCodeLocator, tradeCode).waitUntilUpdateDone();
        return this;
    }

    public SMEEUTradeCodeSearchPopup clickSearchButton() {
        getController().click(searchButtonLocator);
        return this;
    }

    public SMEEUTradeCodeSearchPopup clickResetButton() {
        getController().click(resetButtonLocator);
        return this;
    }

    public SMEEUTradeCodeSearchPopup selectFirstRow() {
        getController().click(selectFirstRowLocator);
        return this;
    }
}
