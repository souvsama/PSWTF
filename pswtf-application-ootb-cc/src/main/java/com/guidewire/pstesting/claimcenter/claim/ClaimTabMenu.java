package com.guidewire.pstesting.claimcenter.claim;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.TabMenu;
import com.guidewire.pstesting.claimcenter.ClaimSummaryScreen;
import com.guidewire.pstesting.claimcenter.Product;
import org.openqa.selenium.By;

import java.util.HashMap;
import java.util.Map;

public class ClaimTabMenu extends TabMenu {
    public static final String BASE_ID = "TabBar:ClaimTab:";

    static final By claimTabLocator = By.id("TabBar:ClaimTab");
    static final By newClaimLocator = By.id(BASE_ID + "ClaimTab_FNOLWizard");
    static final By findClaimLocator = By.id(BASE_ID + "ClaimTab_FindClaim-inputEl");
    static final By findClaimBtnLocator = By.id(BASE_ID + "ClaimTab_FindClaim_Button");

    /* Map of wizard step sets keyed by product offering name */
    public final Map<String, ClaimWizardStepSet> wizardStepSetMap = new HashMap<>();

    public ClaimTabMenu(ScreenObjectController controller) {
        super(controller, claimTabLocator, newClaimLocator);
    }

    /**
     * Returns the claim number of the currently selected claim.
     *
     * @return the claim number of the currently selected claim or <code>null</code>
     *         if no claim is selected.
     */
    public String getClaimNumber() {
        String claimNumber = getController().getText(claimTabLocator);
        int i = claimNumber.indexOf("(");
        // Is a claim number present?
        if (i >= 0) {
            return claimNumber.substring(i + 1, claimNumber.lastIndexOf(")"));
        }
        return null;
    }

    public void clickArrow() {
        getController().clickRightEdgeAndWait(getLocator(), newClaimLocator);
    }

    public ClaimWizardStepSet clickNewClaim(Product product) {
        getController().click(newClaimLocator);
        return product.getWizardFactory().create(getController());  // Initialize wizard step set
    }

    public ClaimSummaryScreen findClaim(String claimNumber){
        clickArrow();
        getController().type(findClaimLocator, claimNumber).click(findClaimBtnLocator);
        return new ClaimSummaryScreen(getController()).waitUntilVisible();
    }

    public void addWizardStepSet(String offering, ClaimWizardStepSet stepSet) {
        wizardStepSetMap.put(offering.toLowerCase(), stepSet);
    }

    public ClaimWizardStepSet getWizardStepSet(String offering) {
        ClaimWizardStepSet stepSet = wizardStepSetMap.get(offering.toLowerCase());
        stepSet.setCurrentStepIndex(0);
        return stepSet;
    }

}
