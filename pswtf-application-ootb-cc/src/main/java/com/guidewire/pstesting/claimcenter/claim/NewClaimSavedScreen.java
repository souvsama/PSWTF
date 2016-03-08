package com.guidewire.pstesting.claimcenter.claim;

import com.guidewire.pstesting.ScreenObjectController;
import com.guidewire.pstesting.claimcenter.ClaimCenterComponent;
import com.guidewire.pstesting.claimcenter.ClaimSummaryScreen;
import org.openqa.selenium.By;

public class NewClaimSavedScreen extends ClaimCenterComponent {
    public static final String BASE_ID       = "NewClaimSaved:NewClaimSavedScreen:";
    public static final String INFO_ID = BASE_ID + "NewClaimSavedDV:";

    public static final By PAGE_CONTAINS = By.id(BASE_ID + "ttlBar");

    static final By viewClaimLinkLocator = By.id(INFO_ID + "GoToClaim-inputEl");
    public static By claimResultLocator = By.id(INFO_ID + "Header");
    public static By assignedGroupLocator = By.id(INFO_ID + "AssignedGroup-inputEl");
    public static By assignedUserLocator = By.id(INFO_ID + "AssignedUser-inputEl");

    public NewClaimSavedScreen(ScreenObjectController controller) {
        super(controller);
    }

    public boolean isVisible() {
        return getController().pageContains(PAGE_CONTAINS);
    }

    public NewClaimSavedScreen waitUntilVisible() {
        getController().waitUntilPageContains(PAGE_CONTAINS);
        return this;
    }

    /**
     * Claim saved screen indicates a successfully saved claim
     * @return  true/false
     */
    public boolean isClaimSuccessful(){
        String result = getController().getText(claimResultLocator);
        return result.contains("successfully saved");
    }

    /**
     * Get the created claim number where the size is customized (not 13)
     * @param claimNumberSize  Length of claim number string
     * @return   Claim Number
     */
    public String getSavedClaimNumber(int claimNumberSize){
        String result = getController().getText(claimResultLocator);
        int start = 6;
        int end = start + claimNumberSize;
        result = result.substring(start, end);
        logger.info("Saved claim: " + result);
        return result;
    }

    /**
     * Get the created claim number where the size is unknown, bordered by spaces
     * @return   Claim Number
     */
    public String getSavedClaimNumber(){
        String result = getController().getText(claimResultLocator);
        int start = 6;
        int end = result.indexOf(" ", start + 1);
        result = result.substring(start, end);
        logger.info("Saved claim: " + result);
        return result;
    }

    /**
     * Extract the assigned group information from the screen
     * @return   String - Assigned Group
     */
    public String getAssignedGroup(){
        return getStringAfterColon(assignedGroupLocator);
    }

    /**
     * Extract the assigned user information from the screen
     * @return   String - Assigned User
     */
    public String getAssignedUser(){
        return getStringAfterColon(assignedUserLocator);
    }

    /**
     * Extract the text that comes after a colon-terminated label
     * @param locator
     * @return   String
     */
    private String getStringAfterColon(By locator){
        String result = getController().getText(locator);
        logger.info("Saved Claim info: " + result);
        int idxSplit = result.indexOf(":")+1;
        result = result.substring(idxSplit);
        return result;
    }

    public ClaimSummaryScreen clickViewClaim() {
        getController().click(viewClaimLinkLocator);
        return new ClaimSummaryScreen(getController()).waitUntilVisible();
    }
}
