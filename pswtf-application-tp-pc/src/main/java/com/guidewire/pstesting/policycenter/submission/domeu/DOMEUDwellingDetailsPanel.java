package com.guidewire.pstesting.policycenter.submission.domeu;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;

/**
 * This class represents the dwelling details card of the domestic property policy
 * submission dwellings wizard step.
 */
public class DOMEUDwellingDetailsPanel extends PolicyCenterComponent {
    public static final String BASE_ID = DOMEUDwellingScreen.BASE_ID + "DOMEUMultiDwellingPanelSet:DwellingsListDetailPanel:";

    static final By locationLocator         = By.id(BASE_ID + "LocationDV:DOMEUDwellingLocationInputSet:DOMEUDwellingLocationInput-inputEl");
    static final By yearBuiltLocator        = By.id(BASE_ID + "DOMEUDwellingDetailsDV:CommonQuestions:DwellingYear-inputEl");
    static final By residenceTypesLocator   = By.id(BASE_ID + "DOMEUDwellingDetailsDV:CommonQuestions:ResidenceType-inputEl");
    static final By listedStatusLocator     = By.id(BASE_ID + "DOMEUDwellingDetailsDV:CommonQuestions:ListedStatus-inputEl");
    static final By roofConstructionLocator = By.id(BASE_ID + "DOMEUDwellingDetailsDV:CommonQuestions:RoofConstruction-inputEl");
    static final By wallConstructionLocator = By.id(BASE_ID + "DOMEUDwellingDetailsDV:CommonQuestions:WallConstruction-inputEl");
    static final By rebuildCostLocator      = By.id(BASE_ID + "DOMEUDwellingDetailsDV:CommonQuestions:RebuildCost-inputEl");

    static final String dwellingTypeLocatorPrefix = BASE_ID + "DOMEUDwellingDetailsDV:CommonQuestions:DwellingType_option";

    /**
     * Constructs a <code>DwellingScreen</code>.
     *
     * @param controller the <code>WebDriver</code> to use
     */
    public DOMEUDwellingDetailsPanel(ScreenObjectController controller) {
        super(controller);
    }

    public Boolean isVisible() {
        return getController().pageContains(locationLocator);
    }

    public DOMEUDwellingDetailsPanel waitUntilVisible() {
        getController().waitUntilPageContains(locationLocator);
        return this;
    }

    public DOMEUDwellingDetailsPanel setLocation(String location) {
        getController().type(locationLocator, location).waitUntilUpdateDone();
        return this;
    }

    public DOMEUDwellingDetailsPanel setYearBuilt(String location) {
        getController().type(yearBuiltLocator, location).waitUntilUpdateDone();
        return this;
    }

    public DOMEUDwellingDetailsPanel setResidenceType(String residenceType) {
        getController().typeAndTab(residenceTypesLocator, residenceType).waitUntilUpdateDone();
        return this;
    }

    public DOMEUDwellingDetailsPanel setListedBuilding(String listedStatus) {
        getController().typeAndTab(listedStatusLocator, listedStatus).waitUntilUpdateDone();
        return this;
    }

    public DOMEUDwellingDetailsPanel setRoofConstruction(String roofConstruction) {
        getController().typeAndTab(roofConstructionLocator, roofConstruction).waitUntilUpdateDone();
        return this;
    }

    public DOMEUDwellingDetailsPanel setWallConstruction(String wallConstruction) {
        getController().typeAndTab(wallConstructionLocator, wallConstruction).waitUntilUpdateDone();
        return this;
    }

    public DOMEUDwellingDetailsPanel setDwellingType(String type) {
        getController()
                .click(By.xpath("//label[text()=\"" + getController().replaceResourceVariables(type) + "\"]"))
                .waitUntilUpdateDone();
        return this;
    }

    public DOMEUDwellingDetailsPanel setEstimatedRebuildCost(String rebuildCost) {
        getController().type(rebuildCostLocator, rebuildCost).waitUntilUpdateDone();
        return this;
    }
}
