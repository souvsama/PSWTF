package com.guidewire.pstesting.policycenter.submission.smeshops;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;

public class SMEEULocationPopup extends PolicyCenterComponent {
    public static final String BASE_ID       = "SMEEUPremisePopup:LocationScreen:";
    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By okButtonLocator           = By.id(BASE_ID + "Update-btnInnerEl");
    static final By premisesDetailsTabLocator = By.id(BASE_ID + "LocationDetailPanelSet:SMEEUPremiseDetailCV:GeneralInfoCardTab-btnInnerEl");
    static final By basicCoverTabLocator      = By.id(BASE_ID + "LocationDetailPanelSet:SMEEUPremiseDetailCV:AdditionalCoveragesCardTab-btnInnerEl");

    private final SMEEUPremisesScreen     parent;
    private final SMEUPremisesDetailPanel premisesDetailPanel;
    private final SMEEUBasicCoverPanel    basicCoverPanel;

    public SMEEULocationPopup(SMEEUPremisesScreen parent, ScreenObjectController controller) {
        super(controller);
        this.parent = parent;
        this.premisesDetailPanel = new SMEUPremisesDetailPanel(controller);
        this.basicCoverPanel = new SMEEUBasicCoverPanel(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public boolean isVisible() {
        return getController().pageContains(getPageContains());
    }

    @SuppressWarnings("unchecked")
    public SMEEULocationPopup waitUntilVisible() {
        getController().waitUntilPageContains(getPageContains());
        return this;
    }

    public SMEEUPremisesScreen clickOk() {
        getController().click(okButtonLocator);
        return parent;
    }

    public SMEUPremisesDetailPanel selectPremisesDetailsTab() {
        if (!premisesDetailPanel.isVisible()) {
            getController().click(premisesDetailsTabLocator);
            premisesDetailPanel.waitUntilVisible();
        }
        return premisesDetailPanel;
    }

    public SMEEUBasicCoverPanel selectBasicCoverTab() {
        if (!basicCoverPanel.isVisible()) {
            getController().click(basicCoverTabLocator);
            basicCoverPanel.waitUntilVisible();
        }
        return basicCoverPanel;
    }
}
