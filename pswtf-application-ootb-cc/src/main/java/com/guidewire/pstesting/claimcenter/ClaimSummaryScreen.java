package com.guidewire.pstesting.claimcenter;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.WizardScreen;
import com.guidewire.pstesting.WizardScreenFactory;
import com.guidewire.pstesting.claimcenter.claim.ClaimTabMenu;
import com.guidewire.pstesting.claimcenter.claim.FinancialSummaryScreen;
import com.guidewire.pstesting.claimcenter.claim.NewExposureVehicleScreen;
import com.guidewire.pstesting.claimcenter.claim.ReserveScreen;
import org.openqa.selenium.By;

public class ClaimSummaryScreen extends ClaimWizardScreen<ClaimSummaryScreen> {
    public static final String BASE_ID       = "ClaimSummary:ClaimSummaryScreen:";
    public static final String CLAIM_ACTION_BASE_ID = "Claim:ClaimMenuActions:";
    public static final String VEHICLE_BASE_ID = CLAIM_ACTION_BASE_ID + "ClaimMenuActions_NewExposure:NewExposureMenuItemSet:NewExposureMenuItemSet_ByCoverage:";

    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");
    public static final By     actionButtonLocator = By.id("Claim:ClaimMenuActions-btnInnerEl");
    public static final By     newExposureLocator = By.id(CLAIM_ACTION_BASE_ID + "ClaimMenuActions_NewExposure:NewExposureMenuItemSet:NewExposureMenuItemSet_ByCoverage-textEl");
    public static final By     vehicleExposureLocator = By.id(CLAIM_ACTION_BASE_ID + "ClaimMenuActions_NewExposure:NewExposureMenuItemSet:NewExposureMenuItemSet_ByCoverage:0:item-textEl");
    public static final By     newVehicleExposureLocator = By.id(CLAIM_ACTION_BASE_ID + "ClaimMenuActions_NewExposure:NewExposureMenuItemSet:NewExposureMenuItemSet_ByCoverage:0:item:1:item-textEl");
    public static final By     exposureLVLocator = By.id("ClaimExposures:ClaimExposuresScreen:ExposuresLV");
    public static final By     exposureNumberLocator = By.id("ClaimExposures:ClaimExposuresScreen:ExposuresLV:0:Order");
    public static final By     newReserveLocator = By.id(CLAIM_ACTION_BASE_ID + "ClaimMenuActions_NewTransaction:ClaimMenuActions_NewTransaction_ReserveSet-textEl");
    public static final By     newPaymentLocator = By.id(CLAIM_ACTION_BASE_ID + "ClaimMenuActions_NewTransaction:ClaimMenuActions_NewTransaction_CheckSet-textEl");
    public static final By     validateClaimExposureLocator = By.id(CLAIM_ACTION_BASE_ID + "ClaimFileMenuItemSet:ClaimMenuActions_ClaimActions:ClaimMenuActions_ClaimExposureValidation-textEl");
    public static final By     abilityToPayLocator = By.id(CLAIM_ACTION_BASE_ID + "ClaimFileMenuItemSet:ClaimMenuActions_ClaimActions:ClaimMenuActions_ClaimExposureValidation:4:item-itemEl");
    public static final By     financialMenuLocator = By.id("Claim:MenuLinks:Claim_ClaimFinancialsGroup");
    public static final By     financialSummaryLocator = By.id("Claim:MenuLinks:Claim_ClaimFinancialsGroup:ClaimFinancialsGroup_ClaimFinancialsSummary");
    public static final By     claimClosedStatusLocator = By.id(BASE_ID + "ClaimSummaryHeadlinePanelSet:ClaimClosedText-labelEl");

    public static final By     worksheetMessagesLocator = By.id("WebMessageWorksheet:WebMessageWorksheetScreen:grpMsgs");
    public static final By     worksheetClearBtnLocator = By.id("WebMessageWorksheet:WebMessageWorksheetScreen:WebMessageWorksheet_ClearButton");
    public static final String noValidationErrors = "No validation errors.";

    private ClaimTabMenu claimTabMenu;

    public ClaimSummaryScreen(ScreenObjectController controller) {
        super(controller);
        claimTabMenu = new ClaimTabMenu(controller);
    }

    public By getPageContains() {
        return PAGE_CONTAINS;
    }

    /**
     * Returns the claim number.
     *
     * @return the claim number
     */
    public String getClaimNumber() {
        return claimTabMenu.getClaimNumber();
    }

    public NewExposureVehicleScreen createNewVehicleExposure() {
        getController().click(actionButtonLocator);
        getController().hoverOverAndWait(newExposureLocator, vehicleExposureLocator);
        getController().hoverOverAndWait(vehicleExposureLocator, newVehicleExposureLocator);
        getController().click(newVehicleExposureLocator);
        return new NewExposureVehicleScreen(getController()).waitUntilVisible();
    }

    public NewExposureVehicleScreen createNewVehicleExposure(int vehicleIndex, int exposureIndex) {
        By vehLoc = By.id(VEHICLE_BASE_ID + vehicleIndex + ":item-textEl");
        By expLoc = By.id(VEHICLE_BASE_ID + vehicleIndex + ":item:" + exposureIndex + ":item-textEl");
        getController().click(actionButtonLocator);
        getController().hoverOverAndWait(newExposureLocator, vehLoc);
        getController().hoverOverAndWait(vehLoc, expLoc);
        getController().click(expLoc);
        return new NewExposureVehicleScreen(getController()).waitUntilVisible();
    }

    public boolean isAbleToPay(){
        getController()
                .click(actionButtonLocator)
                .hoverOverAndWait(validateClaimExposureLocator,abilityToPayLocator);
        getController().clickAndWait(abilityToPayLocator,worksheetMessagesLocator);
        String result = getController().getText(By.className("message"));
        getController().click(worksheetClearBtnLocator);
        logger.info(result);
        return (result.equalsIgnoreCase(noValidationErrors));
    }

    public boolean hasExposure() {
        getController().waitUntilPageContains(exposureLVLocator);
        return getController().pageContains(exposureNumberLocator);
    }

    public ReserveScreen createNewReserve() {
        getController().click(actionButtonLocator)
                .waitUntilPageContains(newReserveLocator);
        getController().click(newReserveLocator);
        return new ReserveScreen(getController()).waitUntilVisible();
    }

    public FinancialSummaryScreen createNewPayment() {
        getController().click(financialMenuLocator);
        getController().click(financialSummaryLocator);
        return new FinancialSummaryScreen(getController()).waitUntilVisible();
    }

    public boolean isClaimClosed() {
        return getController().elementExists(claimClosedStatusLocator);
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
         * Constructs a <code>ClaimSummaryScreen</code>.
         */
        public WizardScreen create() {
            return new ClaimSummaryScreen(controller);
        }

        /**
         * Constructs a <code>ClaimSummaryScreen</code> and waits until it is visible.
         *
         * @return the new <code>ClaimSummaryScreen</code> instance
         */
        public WizardScreen createAndWait() {
            return new ClaimSummaryScreen(controller).waitUntilVisible();
        }
    }
}
