package com.guidewire.pstesting.policycenter.submission.ho;

import com.guidewire.pstesting.BooleanRadioInput;
import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.PolicyCenterComponent;
import org.openqa.selenium.By;

/**
 * This class represents the protections detail card of the homeowners policy
 * submission dwellings wizard step.
 */
public class DwellingProtectionDetailsPanel extends PolicyCenterComponent {
    public static final String BASE_ID = DwellingScreen.BASE_ID + "HODwellingSingleHOEPanelSet:HODwellingProtectionDetailsHOEDV:";

    static final By dwellingLocationLocator         = By.id(BASE_ID + "DwellingLocation-inputEl");
    static final By burglarAlarmTypeLocator         = By.id(BASE_ID + "BurglarAlarmType-inputEl");
    static final By sprinklerSystemTypeLocator      = By.id(BASE_ID + "SprinklerSystemType-inputEl");
    static final By smokeAlarmAllFloorsLabelLocator = By.id(BASE_ID + "SmokeAlarmOnAllFloors-labelEl");
    static final By numberOfDeadboltsLocator        = By.id(BASE_ID + "NumberOfDeadbolt-inputEl");

    static final String fireExtinguisherLocatorPrefix  = BASE_ID + "FireExtinguisher_";
    static final String burglarAlarmLocatorPrefix      = BASE_ID + "BurglarAlarm_";
    static final String fireAlarmReportingCenterPrefix = BASE_ID + "FireAlarmReportingStn_";
    static final String smokeAlarmPrefix               = BASE_ID + "SmokeAlarm_";
    static final String smokeAlarmAllFloorsPrefix      = BASE_ID + "SmokeAlarmOnAllFloors_";
    static final String deadboltPrefix                 = BASE_ID + "Deadbolt_";
    static final String residenceVisiblePrefix         = BASE_ID + "ResidenceVisible_";

    private BooleanRadioInput fireExtinguisherInput;
    private BooleanRadioInput burglarAlarmInput;
    private BooleanRadioInput fireAlarmReportingCenterInput;
    private BooleanRadioInput smokeAlarmAllFloorsInput;
    private BooleanRadioInput smokeAlarmInput;
    private BooleanRadioInput deadboltInput;
    private BooleanRadioInput residenceVisibleInput;

    /**
     * Constructs a <code>DwellingScreen</code>.
     *
     * @param controller the <code>WebDriver</code> to use
     */
    public DwellingProtectionDetailsPanel(ScreenObjectController controller) {
        super(controller);
        initialize();
    }

    public boolean isVisible() {
        return getController().pageContains(dwellingLocationLocator);
    }

    public DwellingProtectionDetailsPanel waitUntilVisible() {
        getController().waitUntilPageContains(dwellingLocationLocator);
        return this;
    }

    public DwellingProtectionDetailsPanel setLocationType(String location) {
        getController().typeAndEnter(dwellingLocationLocator, location);
        return this;
    }

    public DwellingProtectionDetailsPanel setSprinklerSystemType(String location) {
        getController().typeAndEnter(sprinklerSystemTypeLocator, location);
        return this;
    }

    public DwellingProtectionDetailsPanel setFireExtinguisher(boolean value) {
        fireExtinguisherInput.setValue(value);
        return this;
    }

    public DwellingProtectionDetailsPanel setBurglarAlarm(boolean value) {
        burglarAlarmInput.setValue(value);
        getController().waitUntilPageContains(burglarAlarmTypeLocator);
        return this;
    }

    public DwellingProtectionDetailsPanel setBurglarAlarmType(String alarmType) {
        if (getController().elementExists(burglarAlarmTypeLocator)) {
            getController().typeAndEnter(burglarAlarmTypeLocator, alarmType);
        }
        return this;
    }

    public DwellingProtectionDetailsPanel setFireAlarmReportingCenter(boolean value) {
        fireAlarmReportingCenterInput.setValue(value);
        return this;
    }

    public DwellingProtectionDetailsPanel setSmokeAlarms(boolean value) {
        smokeAlarmInput.setValue(value);
        getController().waitUntilPageContains(smokeAlarmAllFloorsLabelLocator);
        return this;
    }

    public DwellingProtectionDetailsPanel setSmokeAlarmAllFloors(boolean value) {
        if (getController().elementExists(smokeAlarmAllFloorsLabelLocator)) {
            smokeAlarmAllFloorsInput.setValue(value);
        }
        return this;
    }

    public DwellingProtectionDetailsPanel setDeadbolts(boolean value) {
        deadboltInput.setValue(value);
        getController().waitUntilPageContains(numberOfDeadboltsLocator);
        return this;
    }

    public DwellingProtectionDetailsPanel setNumberOfDeadbolts(String count) {
        if (getController().elementExists(numberOfDeadboltsLocator)) {
            getController().type(numberOfDeadboltsLocator, count).waitUntilUpdateDone();
        }
        return this;
    }

    public DwellingProtectionDetailsPanel setResidenceVisible(boolean value) {
        residenceVisibleInput.setValue(value);
        return this;
    }

    private void initialize() {
        ScreenObjectController controller = getController();
        fireExtinguisherInput = new BooleanRadioInput(controller, fireExtinguisherLocatorPrefix);
        burglarAlarmInput = new BooleanRadioInput(controller, burglarAlarmLocatorPrefix);
        fireAlarmReportingCenterInput = new BooleanRadioInput(controller, fireAlarmReportingCenterPrefix);
        smokeAlarmInput = new BooleanRadioInput(controller, smokeAlarmPrefix);
        smokeAlarmAllFloorsInput = new BooleanRadioInput(controller, smokeAlarmAllFloorsPrefix);
        deadboltInput = new BooleanRadioInput(controller, deadboltPrefix);
        residenceVisibleInput = new BooleanRadioInput(controller, residenceVisiblePrefix);
    }
}
