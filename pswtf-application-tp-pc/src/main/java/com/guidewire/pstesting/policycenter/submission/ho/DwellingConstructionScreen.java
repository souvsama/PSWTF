package com.guidewire.pstesting.policycenter.submission.ho;

import com.guidewire.pstesting.BooleanRadioInput;
import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.policycenter.submission.SubmissionWizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import org.openqa.selenium.By;

/**
 * This class represents the homeowners policy submission dwelling construction wizard step.
 */
public class DwellingConstructionScreen extends SubmissionWizardScreen<DwellingConstructionScreen> {
    public static final String BASE_ID         = SubmissionWizardScreen.BASE_ID + "LOBWizardStepGroup:LineWizardStepSet:HODwellingConstructionHOEScreen:";
    public static final String DETAILS_BASE_ID = BASE_ID + "HODwellingConstructionDetailsHOEDV:";
    public static final By PAGE_CONTAINS   = By.id(BASE_ID + "ttlBar");

    static final By quoteLocator                 = By.id(BASE_ID + "JobWizardToolbarButtonSet:QuoteOrReview-btnInnerEl");
    static final By yearBuiltLocator             = By.id(DETAILS_BASE_ID + "YearBuilt-inputEl");
    static final By constructionTypeLocator      = By.id(DETAILS_BASE_ID + "ConstructionType-inputEl");
    static final By numberOfStoresLocator        = By.id(DETAILS_BASE_ID + "NumStories-inputEl");
    static final By garageLocator                = By.id(DETAILS_BASE_ID + "Garage-inputEl");
    static final By squareFootageLocator         = By.id(DETAILS_BASE_ID + "ApproxSqFoot-inputEl");
    static final By foundationTypeLocator        = By.id(DETAILS_BASE_ID + "FoundationType-inputEl");
    static final By roofTypeLocator              = By.id(DETAILS_BASE_ID + "RoofType-inputEl");
    static final By primaryHeatingTypeLocator    = By.id(DETAILS_BASE_ID + "PrimaryHeating-inputEl");
    static final By plumbingLocator              = By.id(DETAILS_BASE_ID + "PlumbingSystem-inputEl");
    static final By wiringLocator                = By.id(DETAILS_BASE_ID + "Wiring-inputEl");
    static final By electricalSystemLocator      = By.id(DETAILS_BASE_ID + "ElectricalSystem-inputEl");
    static final By numberOfAmpsLocator          = By.id(DETAILS_BASE_ID + "NumberofAmps-inputEl");
    static final By windClassLocator             = By.id(DETAILS_BASE_ID + "WindClass-inputEl");
    static final By constructionClassCodeLocator = By.id(DETAILS_BASE_ID + "ConstructionClass-inputEl");

    static final String secondaryHeatingLocatorPrefix = DETAILS_BASE_ID + "SecondaryHeatingExists_";

    private BooleanRadioInput secondaryHeatingInput;

    /**
     * Constructs a <code>DwellingConstructionScreen</code>.
     *
     * @param controller the <code>WebDriver</code> to use
     */
    public DwellingConstructionScreen(ScreenObjectController controller) {
        super(controller);
        initialize();
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    public DwellingConstructionScreen setYearBuilt(String year) {
        getController().type(yearBuiltLocator, year).waitUntilUpdateDone();
        return this;
    }

    public DwellingConstructionScreen setConstructionType(String type) {
        getController().typeAndTab(constructionTypeLocator, type);
        return this;
    }

    public DwellingConstructionScreen setNumberOfStories(String numberOfStories) {
        getController().typeAndTab(numberOfStoresLocator, numberOfStories);
        return this;
    }

    public DwellingConstructionScreen setSquareFootage(String squareFootage) {
        getController().type(squareFootageLocator, squareFootage).waitUntilUpdateDone();
        return this;
    }

    public DwellingConstructionScreen setGarage(String garage) {
        getController().typeAndTab(garageLocator, garage);
        return this;
    }

    public DwellingConstructionScreen setFoundationType(String foundation) {
        getController().typeAndTab(foundationTypeLocator, foundation);
        return this;
    }

    public DwellingConstructionScreen setRoofType(String roof) {
        getController().typeAndTab(roofTypeLocator, roof);
        return this;
    }

    public DwellingConstructionScreen setSecondaryHeating(Boolean value) {
        secondaryHeatingInput.setValue(value);
        return this;
    }

    public DwellingConstructionScreen setPrimaryHeatingType(String heating) {
        getController().typeAndTab(primaryHeatingTypeLocator, heating);
        return this;
    }

    public DwellingConstructionScreen setPlumbing(String plumbing) {
        getController().clickTypeAndTab(plumbingLocator, plumbing);
        return this;
    }

    public DwellingConstructionScreen setWiring(String wiring) {
        getController().typeAndTab(wiringLocator, wiring);
        return this;
    }

    public DwellingConstructionScreen setElectricalSystem(String electricalSystem) {
        getController().typeAndTab(electricalSystemLocator, electricalSystem);
        return this;
    }

    public DwellingConstructionScreen setNumberOfAmps(String amps) {
        getController().type(numberOfAmpsLocator, amps).waitUntilUpdateDone();
        return this;
    }

    public DwellingConstructionScreen setWindClass(String windClass) {
        getController().typeAndTab(windClassLocator, windClass);
        return this;
    }

    public DwellingConstructionScreen setConstructionClassCode(String year) {
        getController().type(constructionClassCodeLocator, year).waitUntilUpdateDone();
        return this;
    }

    public By getQuoteLocator() {
        return quoteLocator;
    }

    private void initialize() {
        ScreenObjectController controller = getController();
        secondaryHeatingInput = new BooleanRadioInput(controller, secondaryHeatingLocatorPrefix);
    }

    //================================================================================
    // Factory
    //================================================================================

    public static class Factory implements WizardScreenFactory {
        private final ScreenObjectController controller;

        public Factory(ScreenObjectController controller) {
            this.controller = controller;
        }

        /**
         * Constructs a <code>DwellingConstructionScreen</code>.
         */
        public SubmissionWizardScreen create() {
            return new DwellingConstructionScreen(controller);
        }

        /**
         * Constructs a <code>DwellingConstructionScreen</code> and waits until it is visible.
         *
         * @return the new <code>DwellingConstructionScreen</code> instance
         */
        public SubmissionWizardScreen createAndWait() {
            return new DwellingConstructionScreen(controller).waitUntilVisible();
        }
    }
}
