package com.guidewire.pstesting.claimcenter.claim;


import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import com.guidewire.pstesting.claimcenter.ClaimWizardScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class BasicInformationScreen extends ClaimWizardScreen<BasicInformationScreen> {
    WebDriver driver;
    WebDriverWait wait;
    public static final String BASE_ID = "FNOLWizard:AutoWorkersCompWizardStepSet:FNOLWizard_BasicInfoScreen:";
    public static final String INFO_DV = BASE_ID + "PanelRow:BasicInfoDetailViewPanelDV:";
    public static final String RH_PANEL = BASE_ID + "PanelRow:RightPanel:FNOLWizard_BasicInfoRightPanelSet:";
    @FindBy(id = INFO_DV + "HowReported-inputEl") WebElement howReported;
    @FindBy(id = INFO_DV + "ReportedBy_Name-inputEl") WebElement nameReportedBy;
    @FindBy(id = INFO_DV + "EditContact-btnInnerEl") WebElement editContactButton;
    @FindBy(css = "input[role=checkbox]") List<WebElement> listVehicles;

    private static String rowNum;
    static final By howReportedLocator = By.id(INFO_DV + "HowReported-inputEl");
    static final By relationToInsuredLocator = By.id(INFO_DV + "Claim_ReportedByType-inputEl");
    static final String insuredVehicleGroup = ":InsuredVehicleDV:InsuredVehicleInputGroup:";
    static final String insuredVehicleLabel = ":InsuredVehicleDV:InsuredVehicleInputGroup-legendTitle";
    static final String involvedVehicleChkBoxIDSuffix = insuredVehicleGroup + "_checkbox";
    static final String insuredVehicleCoverageGroup = ":coverage-inputEl";
    static final By insuredNameLocator = By.id(INFO_DV + "Insured_Name-inputEl");
    static final By insuredAddressLocator = By.id(INFO_DV + "Insured_Address-inputEl");
    static final By contactAddressLocator = By.id(INFO_DV + "reporter_Address-inputEl");

    public BasicInformationScreen(ScreenObjectController controller) {
        super(controller);
        this.driver = controller.getWebDriver();
        wait = new WebDriverWait(driver,5);
        PageFactory.initElements(driver, this);
    }

    public By getPageContains() {
        return howReportedLocator;
    }

    public boolean isVisible(){
        return howReported.isDisplayed();
    }

    public BasicInformationScreen waitUntilVisible(){
        wait.until(ExpectedConditions.visibilityOf(nameReportedBy));
        return this;
    }

    public BasicInformationScreen setHowReported(String reportMethod){
        getController().setTextField(howReported, reportMethod).pressEnter();
        return this;
    }

    public BasicInformationScreen setReportedBy(String name){
        getController().setDropDownField(nameReportedBy, name);
        wait.until(ExpectedConditions.presenceOfElementLocated(contactAddressLocator));
        return this;
    }

    public BasicInformationScreen setRelationToInsured(String relationship){
        if (getController().getValue(relationToInsuredLocator) != relationship){
            getController().setDropDownField(relationToInsuredLocator, relationship).waitUntilUpdateDone();
        }
        return this;
    }

    public BasicInformationScreen selectVehicleInvolved(int index){
        By coverageLocator = By.id(RH_PANEL + index + insuredVehicleGroup + index + insuredVehicleCoverageGroup);
        By cbInvolvedVehicle = By.id(RH_PANEL + index + involvedVehicleChkBoxIDSuffix);
        String selectedVehicle = getController().getText(By.id(RH_PANEL + index + insuredVehicleLabel));
        getController().clickAndWait(cbInvolvedVehicle,coverageLocator);
        logger.info("Vehicle selected: " + selectedVehicle);
        return this;
    }

    /**
     * Discover the number of selectable involved vehicles
     * @return     Number of vehicles, as <code>Integer</code>
     */
    public Integer getNumberOfInvolvedVehicles(){
        Integer result = 0;
        result = listVehicles.size();
        return result;
    }

    public String getInsuredName(){
        return getController().getText(insuredNameLocator);
    }

    public String getInsuredAddress(){
        return getController().getText(insuredAddressLocator);
    }

    public String getContactAddress(){
        return getController().getText(contactAddressLocator);
    }

    /**
     * Check for user messages displayed on the page
     * @return    <code>String</code> containing number of messages, otherwise empty <code>String</code>
     */
    public String checkErrorStatus(){
        String result = "";
        if (!getController().elementExists(By.id(BASE_ID + "_msgs"))){
            List<WebElement> errorList = driver.findElements(By.className("message"));
            result = errorList.size() + " user messages displayed";
        }
        return result;
    }

    //================================================================================
    // Factory
    //================================================================================

    public static class Factory implements WizardScreenFactory {
        private final ScreenObjectController controller;

        public Factory(ScreenObjectController controller) {
            this.controller = controller;
        }

/*      Constructs a <code>BasicInformationScreen</code>.
*/
        public WizardScreen create() {
            return new BasicInformationScreen(controller);
        }

/*
         * Constructs a <code>BasicInformationScreen</code> and waits until it is visible.
         *
         * @return the new <code>BasicInformationScreen</code> instance
*/

        public WizardScreen createAndWait() {
            return new BasicInformationScreen(controller).waitUntilVisible();
        }
    }
}
