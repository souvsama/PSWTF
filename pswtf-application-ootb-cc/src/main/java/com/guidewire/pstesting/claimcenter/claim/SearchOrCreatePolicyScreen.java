package com.guidewire.pstesting.claimcenter.claim;


import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import com.guidewire.pstesting.claimcenter.ClaimWizardScreen;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SearchOrCreatePolicyScreen extends ClaimWizardScreen<SearchOrCreatePolicyScreen> {
    private final WebDriver driver;
    private WebDriverWait wait;
    private PolicySearchResultsTable lvresults;

    // Init visible elements
    static final String BASE_ID = "FNOLWizard:FNOLWizard_FindPolicyScreen:";
    static final String SEARCHPANEL_ID = BASE_ID + "FNOLWizardFindPolicyPanelSet:";
    @FindBy(id = BASE_ID + "0") WebElement pageTitle;
    @FindBy(id = SEARCHPANEL_ID + "policyNumber-inputEl") WebElement policyNumber;
    @FindBy(id = SEARCHPANEL_ID + "GlobalPersonNameInputSet:FirstName-inputEl") WebElement inputFirstName;
    @FindBy(id = SEARCHPANEL_ID + "GlobalPersonNameInputSet:LastName-inputEl") WebElement inputLastName;
    @FindBy(id = SEARCHPANEL_ID + "GlobalContactNameInputSet:Name-inputEl") WebElement inputCompanyName;
    @FindBy(id = SEARCHPANEL_ID + "PolicyType-inputEl") WebElement editPolicyType;
    @FindBy(id = SEARCHPANEL_ID + "date-inputEl") WebElement searchLossDate;
    @FindBy(id = SEARCHPANEL_ID + "Search") WebElement btnSearch;
    @FindBy(id = SEARCHPANEL_ID + "Reset") WebElement btnReset;
    @FindBy(id = SEARCHPANEL_ID + "PolicyResultLV-body") WebElement resultsListView;
    static final By policyNumberLocator = By.id(SEARCHPANEL_ID + "policyNumber-inputEl");

    // Locators for elements not initially visible
    static final By claimLossDateLocator = By.id(SEARCHPANEL_ID + "Claim_LossDate-inputEl");
    static final By resultRow1Locator = By.id(SEARCHPANEL_ID + "PolicyResultLV:0:PolicyNumber");
    static final By claimTypeLocator = By.id(SEARCHPANEL_ID + "ClaimMode_option0-boxLabelEl");
    static final By nextLocator   = By.id("FNOLWizard:Next-btnInnerEl");
    static final By cancelLocator = By.id("FNOLWizard:Cancel-btnInnerEl");


    public SearchOrCreatePolicyScreen(ScreenObjectController controller){
        super(controller);
        this.driver = controller.getWebDriver();
        wait = new WebDriverWait(driver,5);
        PageFactory.initElements(driver, this);
    }

    public SearchOrCreatePolicyScreen waitUntilVisible(){
        wait.until(ExpectedConditions.presenceOfElementLocated(cancelLocator));
        return this;
    }

    public By getPageContains() {
        return policyNumberLocator;
    }

    public boolean isVisible(){
        return btnSearch.isDisplayed();
    }


    public void searchByPerson(String firstname, String lastname){
        getController().click(btnReset);
        setFirstName(firstname).setLastName(lastname);
        clickSearch();
    }

    public SearchOrCreatePolicyScreen setFirstName(String firstname){
        getController().setTextField(inputFirstName, firstname);
        return this;
    }

    public SearchOrCreatePolicyScreen setLastName(String lastname){
        getController().setTextField(inputLastName, lastname);
        return this;
    }

    public SearchOrCreatePolicyScreen setPolicyNumber(String policyNumber){
        getController().setTextField(policyNumberLocator,policyNumber);
        return this;
    }

    public void setCompanyName(String busName){
        getController().click(btnReset);
        getController().setTextField(inputCompanyName,busName);
        clickSearch();
    }

    public void setLossDate(String lossDate){
        getController().setDateField(claimLossDateLocator, lossDate);
        wait.until(ExpectedConditions.elementToBeClickable(nextLocator));
    }

    /**
     * Checks for the existence of at least one result row
     * @return    <code>true</code> if one row exists; <code>false</code> otherwise
     */
    public boolean isPolicyFound(){
        return driver.findElement(resultRow1Locator).isDisplayed();
    }

    /**
     * Gets the type of claim being created
     * @return   Claim type
     */
    public String getClaimType(){
        return getController().findScreenObject(claimTypeLocator).getText();
    }

    public String submitPage(){
        return submitToNextStep(claimTypeLocator);
    }

    public void CancelSearch(){
        //getController().click(cancelLocator);
        cancelClaim();
    }

    /**
     * Invokes the search with the entered criteria, checks for a result or outputs any errors
     * @return    Current search page
     */
    public SearchOrCreatePolicyScreen clickSearch() {
        btnSearch.click();
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(resultRow1Locator));
            lvresults = new PolicySearchResultsTable(getController());
        } catch (WebDriverException e) {
            logger.warn(getController().checkForErrors());
        }
        return this;
    }

    public SearchOrCreatePolicyScreen selectResultWithText(String text){
        lvresults.selectRowWithText(text);
        wait.until(ExpectedConditions.presenceOfElementLocated(claimTypeLocator));
        return this;
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
         * Constructs a <code>FindPolicyScreen</code>.
         */
        public WizardScreen create() {
            return new SearchOrCreatePolicyScreen(controller);
        }

        /**
         * Constructs a <code>FindPolicyScreen</code> and waits until it is visible.
         *
         * @return the new <code>FindPolicyScreen</code> instance
         */
        public WizardScreen createAndWait() {
            return new SearchOrCreatePolicyScreen(controller).waitUntilVisible();
        }
    }
}
