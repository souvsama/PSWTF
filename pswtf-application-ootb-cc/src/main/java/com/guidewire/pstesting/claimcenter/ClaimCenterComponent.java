package com.guidewire.pstesting.claimcenter;


import com.guidewire.pstesting.ApplicationComponent;
import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.claimcenter.claim.ClaimWorkplanScreen;
import com.guidewire.pstesting.claimcenter.claim.CloseClaimPopupScreen;
import com.guidewire.pstesting.claimcenter.claim.ExposuresScreen;
import com.guidewire.pstesting.claimcenter.claim.FinancialSummaryScreen;
import org.openqa.selenium.By;

public abstract class ClaimCenterComponent extends ApplicationComponent {
    private ScreenObjectController controller;

    public static final String RESOURCE_BASE_NAME = "cc.messages";
    public static final String CLAIM_MENU_LINKS_BASE_ID = "Claim:MenuLinks:";
    public static final String CLAIM_ACTION_BASE_ID = "Claim:ClaimMenuActions:";

    public static final By actionButtonLocator          = By.id("Claim:ClaimMenuActions-btnInnerEl");
    public static final By closeClaimActionLocator      = By.id(CLAIM_ACTION_BASE_ID + "ClaimFileMenuItemSet:ClaimMenuActions_ClaimActions:ClaimMenuActions_CloseClaim");

    public static final By summaryMenuLocator           = By.id(CLAIM_MENU_LINKS_BASE_ID + "Claim_ClaimSummaryGroup");
    public static final By workplanMenuLocator          = By.id(CLAIM_MENU_LINKS_BASE_ID + "Claim_ClaimWorkplan");
    public static final By lossDetailsMenuLocator       = By.id(CLAIM_MENU_LINKS_BASE_ID + "Claim_ClaimLossDetailsGroup");
    public static final By exposuresMenuLocator         = By.id(CLAIM_MENU_LINKS_BASE_ID + "Claim_ClaimExposures");
    public static final By partiesInvolvedMenuLocator   = By.id(CLAIM_MENU_LINKS_BASE_ID + "Claim_ClaimPartiesGroup");
    public static final By policyMenuLocator            = By.id(CLAIM_MENU_LINKS_BASE_ID + "Claim_ClaimPolicyGroup");
    public static final By financialsMenuLocator        = By.id(CLAIM_MENU_LINKS_BASE_ID + "Claim_ClaimFinancialsGroup");
    public static final By notesMenuLocator             = By.id(CLAIM_MENU_LINKS_BASE_ID + "Claim_ClaimNotes");
    public static final By documentsMenuLocator         = By.id(CLAIM_MENU_LINKS_BASE_ID + "Claim_ClaimDocuments");
    public static final By planOfActionMenuLocator      = By.id(CLAIM_MENU_LINKS_BASE_ID + "Claim_ClaimPlanOfActionGroup");
    public static final By servicesMenuLocator          = By.id(CLAIM_MENU_LINKS_BASE_ID + "Claim_ClaimServiceRequests");
    public static final By litigationMenuLocator        = By.id(CLAIM_MENU_LINKS_BASE_ID + "Claim_ClaimMatters");
    public static final By historyMenuLocator           = By.id(CLAIM_MENU_LINKS_BASE_ID + "Claim_ClaimHistory");
    public static final By fnolSnapshotMenuLocator      = By.id(CLAIM_MENU_LINKS_BASE_ID + "Claim_ClaimSnapshotGroup");
    public static final By calendarMenuLocator          = By.id(CLAIM_MENU_LINKS_BASE_ID + "Claim_ClaimCalendarGroup");
    public static final By complaintsMenuLocator        = By.id(CLAIM_MENU_LINKS_BASE_ID + "Claim_ClaimComplaintsGroup");


    protected ClaimCenterComponent(ScreenObjectController controller){
        super(controller);
        this.controller = controller;
    }

    /**
     * Returns the base name of the resource bundle
     *
     * @return the base name of the resource bundle, a fully qualified class name
     */
    public String getResourceBaseName() {
        return RESOURCE_BASE_NAME;
    }

    public ClaimSummaryScreen clickSummaryMenuLink() {
        getController().click(summaryMenuLocator);
        return new ClaimSummaryScreen(getController()).waitUntilVisible();
    }

    public ClaimWorkplanScreen clickClaimWorkplanMenuLink() {
        getController().click(workplanMenuLocator);
        return new ClaimWorkplanScreen(getController()).waitUntilVisible();
    }

    public FinancialSummaryScreen clickFinancialsMenuLink() {
        getController().click(financialsMenuLocator);
        return new FinancialSummaryScreen(getController()).waitUntilVisible();
    }

    public ExposuresScreen clickExposuresMenuLink() {
        getController().click(exposuresMenuLocator);
        return new ExposuresScreen(getController()).waitUntilVisible();
    }

    public CloseClaimPopupScreen clickCloseClaimAction() {
        getController().click(actionButtonLocator)
                .click(closeClaimActionLocator);
        return new CloseClaimPopupScreen(getController()).waitUntilVisible();
    }

}
