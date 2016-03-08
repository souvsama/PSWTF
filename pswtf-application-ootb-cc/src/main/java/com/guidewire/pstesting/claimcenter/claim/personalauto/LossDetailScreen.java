package com.guidewire.pstesting.claimcenter.claim.personalauto;


import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import com.guidewire.pstesting.claimcenter.ClaimWizardScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LossDetailScreen extends ClaimWizardScreen<LossDetailScreen> {
    WebDriver driver;
    WebDriverWait wait;

    public static final String BASE_ID = "FNOLWizard:AutoWorkersCompWizardStepSet:FNOLWizard_NewLossDetailsScreen:";
    public static final String DESC_DETAILS_ID = BASE_ID + "LossDetailsAddressDV:";
    public static final String ADDRESS_SET = DESC_DETAILS_ID + "AddressDetailInputSetRef:CCAddressInputSet:globalAddressContainer:";

    public By lossDescriptionLocator = By.id(DESC_DETAILS_ID + "Description-inputEl");
    public By lossCauseLocator = By.id(DESC_DETAILS_ID + "Claim_LossCause-inputEl");
    public By lossLocationLocator = By.id(ADDRESS_SET + "Address_Picker-inputEl");
    public By lossCityLocator = By.id(ADDRESS_SET + "globalAddress:GlobalAddressInputSet:City-inputEl");
    public By lossStateLocator = By.id(ADDRESS_SET + "globalAddress:GlobalAddressInputSet:State-inputEl");
    public By faultRatingLocator = By.id(BASE_ID + "CategorizationDV:Notification_Fault-inputEl");
    static final By vehicleIncidentLocation = By.id(BASE_ID + "VehicleIncidentIterator:0:VehicleIncidentDV:VehicleName-inputEl");
    public By lossJurisdictionLocator = By.id(ADDRESS_SET + "Claim_JurisdictionState-inputEl");

    public LossDetailScreen(ScreenObjectController controller) {
        super(controller);
        this.driver = controller.getWebDriver();
        wait = new WebDriverWait(driver,5);
        PageFactory.initElements(driver, this);
    }

    @Override
    public By getPageContains() {
        return lossDescriptionLocator;
    }

    public LossDetailScreen setDescription(String lossDesc){
        getController().setTextField(lossDescriptionLocator, lossDesc);
        return this;
    }

    /**
     * Required field - Set the cause of loss
     * @param lossCause
     * @return
     */
    public LossDetailScreen setLossCause(String lossCause){
        getController().setDropDownField(lossCauseLocator, lossCause).waitUntilUpdateDone();
        return this;
    }

    /**
     * Required field - Set location of loss, as insured address or New...
     * @param lossLocation
     * @return
     */
    public LossDetailScreen setLossLocation(String lossLocation){
        getController().setDropDownField(lossLocationLocator, lossLocation).waitUntilUpdateDone();
        return this;
    }

    /**
     * Required field - auto-filled and non-editable when using insured address
     * @param lossCity
     * @return
     */
    public LossDetailScreen setLossCity(String lossCity){
        getController().setDropDownField(lossCityLocator, lossCity).waitUntilUpdateDone();
        return this;
    }

    /**
     * Required field - auto-filled and non-editable when using insured address
     * @param lossState
     * @return
     */
    public LossDetailScreen setLossState(String lossState){
        getController().setDropDownField(lossStateLocator, lossState).waitUntilUpdateDone();
        return this;
    }

    /**
     * Not required but may result in claim unsaved
     * @param faultRating
     * @return
     */
    public LossDetailScreen setFaultRating(String faultRating){
        getController().scrollToBottom().setDropDownField(faultRatingLocator, faultRating);
        return this;
    }

    public VehicleIncidentPopupScreen clickVehicleIncidentLink() {
        getController().click(vehicleIncidentLocation);
        return new VehicleIncidentPopupScreen(getController()).waitUntilVisible();
    }

    public String getLossJurisdiction(){
        return getController().getValue(lossJurisdictionLocator);
    }

    //================================================================================
    // Factory
    //================================================================================

    public static class Factory implements WizardScreenFactory {
        private final ScreenObjectController controller;

        public Factory(ScreenObjectController controller) {
            this.controller = controller;
        }

        /*      Constructs a <code>LossDetailScreen</code>.
        */
        public WizardScreen create() {
            return new LossDetailScreen(controller);
        }

/*
         * Constructs a <code>LossDetailScreen</code> and waits until it is visible.
         *
         * @return the new <code>LossDetailScreen</code> instance
*/

        public WizardScreen createAndWait() {
            return new LossDetailScreen(controller).waitUntilVisible();
        }
    }

}
