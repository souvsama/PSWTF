package com.guidewire.pstesting.policycenter.submission.ho;

import com.guidewire.pstesting.BooleanRadioInput;
import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;

/**
 * This class represents the dwelling details card of the homeowners policy
 * submission dwellings wizard step.
 */
public class DwellingDetailsPanel extends PolicyCenterComponent {
    public static final String BASE_ID = DwellingScreen.BASE_ID + "HODwellingSingleHOEPanelSet:HODwellingDetailsHOEDV:";

    static final By locationLocator              = By.id(BASE_ID + "HODwellingLocationHOEInputSet:HODwellingLocationInput-inputEl");
    static final By distanceToFireHydrantLocator = By.id(BASE_ID + "DistToFireHydrant-inputEl");
    static final By distanceToFireStationLocator = By.id(BASE_ID + "DistToFireStation-inputEl");

    static final String floodFireHazardLocatorPrefix          = BASE_ID + "FloodFireHazard_";
    static final String withinCommercialPropertyLocatorPrefix = BASE_ID + "WithinCommericalProperty_";
    static final String roomersBoardersLocatorPrefix          = BASE_ID + "RoomersBoarders_";
    static final String fireWoodStoveLocatorPrefix            = BASE_ID + "FireWoodStove_";
    static final String swimmingPoolLocatorPrefix             = BASE_ID + "SwimmingPool_";
    static final String trampolineLocatorPrefix               = BASE_ID + "Trampoline_";
    static final String waterLeakageLocatorPrefix             = BASE_ID + "WaterLeakage_";
    static final String anyAnimalsLocatorPrefix               = BASE_ID + "AnyAnimals_";

    private BooleanRadioInput floodFireHazardInput;
    private BooleanRadioInput withinCommercialPropertyInput;
    private BooleanRadioInput fireWoodStoveInput;
    private BooleanRadioInput swimmingPoolInput;
    private BooleanRadioInput roomersBoardersInput;
    private BooleanRadioInput trampolineInput;
    private BooleanRadioInput waterLeakageInput;
    private BooleanRadioInput anyAnimalsInput;

    /**
     * Constructs a <code>DwellingScreen</code>.
     *
     * @param controller the <code>WebDriver</code> to use
     */
    public DwellingDetailsPanel(ScreenObjectController controller) {
        super(controller);
        initialize();
    }

    public Boolean isVisible() {
        return getController().pageContains(locationLocator);
    }

    public DwellingDetailsPanel waitUntilVisible() {
        getController().waitUntilPageContains(locationLocator);
        return this;
    }

    public DwellingDetailsPanel setLocation(String location) {
        getController().type(locationLocator, location).waitUntilUpdateDone();
        return this;
    }

    public DwellingDetailsPanel setDistanceToFireHydrant(String dist) {
        getController().type(distanceToFireHydrantLocator, dist).waitUntilUpdateDone();
        return this;
    }

    public DwellingDetailsPanel setDistanceToFireStation(String dist) {
        getController().type(distanceToFireStationLocator, dist).waitUntilUpdateDone();
        return this;
    }

    public DwellingDetailsPanel setFloodFireHazard(Boolean value) {
        floodFireHazardInput.setValue(value);
        return this;
    }

    public DwellingDetailsPanel setWithinCommercialProperty(Boolean value) {
        withinCommercialPropertyInput.setValue(value);
        return this;
    }

    public DwellingDetailsPanel setRoomersBoarders(Boolean value) {
        roomersBoardersInput.setValue(value);
        return this;
    }

    public DwellingDetailsPanel setFireWoodStove(Boolean value) {
        fireWoodStoveInput.setValue(value);
        return this;
    }

    public DwellingDetailsPanel setSwimmingPool(Boolean value) {
        swimmingPoolInput.setValue(value);
        return this;
    }

    public DwellingDetailsPanel setTrampoline(Boolean value) {
        trampolineInput.setValue(value);
        return this;
    }

    public DwellingDetailsPanel setWaterLeakage(Boolean value) {
        waterLeakageInput.setValue(value);
        return this;
    }

    public DwellingDetailsPanel setAnyAnimals(Boolean value) {
        anyAnimalsInput.setValue(value);
        return this;
    }

    private void initialize() {
        ScreenObjectController controller = getController();
        floodFireHazardInput = new BooleanRadioInput(controller, floodFireHazardLocatorPrefix);
        withinCommercialPropertyInput = new BooleanRadioInput(controller, withinCommercialPropertyLocatorPrefix);
        roomersBoardersInput = new BooleanRadioInput(controller, roomersBoardersLocatorPrefix);
        fireWoodStoveInput = new BooleanRadioInput(controller, fireWoodStoveLocatorPrefix);
        swimmingPoolInput = new BooleanRadioInput(controller, swimmingPoolLocatorPrefix);
        trampolineInput = new BooleanRadioInput(controller, trampolineLocatorPrefix);
        waterLeakageInput = new BooleanRadioInput(controller, waterLeakageLocatorPrefix);
        anyAnimalsInput = new BooleanRadioInput(controller, anyAnimalsLocatorPrefix);
    }
}
