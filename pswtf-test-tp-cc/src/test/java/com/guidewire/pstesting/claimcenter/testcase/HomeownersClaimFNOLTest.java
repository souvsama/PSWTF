package com.guidewire.pstesting.claimcenter.testcase;

import com.guidewire.pstesting.claimcenter.ClaimCenterTestBase;
import com.guidewire.pstesting.claimcenter.claim.*;
import com.guidewire.pstesting.claimcenter.claim.homeowners.HomeownersClaimWizardStepSet;
import com.guidewire.pstesting.claimcenter.claim.homeowners.LossDetailScreen;
import com.guidewire.pstesting.claimcenter.data.HomeownersClaimTestData;
import com.guidewire.pstesting.claimcenter.data.InformationTestData;
import com.guidewire.pstesting.claimcenter.data.LossDetailTestData;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;

public class HomeownersClaimFNOLTest extends ClaimCenterTestBase {
    protected HomeownersClaimFNOLTest() throws JAXBException {
    }

    @Test(description = "Test simple homeowners claim",
            groups = {"smoke", "cc-smoke", "homeowners-claim"},
            dataProvider = "Homeowners-Claim",
            enabled = false)
    public void testSimpleClaim(HomeownersClaimTestData claimData) {
        log("Test simple homeowners claim");

        // Log in to ClaimCenter
        log("Application URL: " + getApplication().getUrl());
        Assert.assertTrue(login(claimData.getUser()), "Login failed");

        // Start a new claim
        log("Starting new homeowners claim");
        ClaimWizardStepSet stepSet = getApplication().newClaim(HomeownersClaimWizardStepSet.HOMEOWNERS_AUTO_PRODUCT);

        log("Searching for policy: " + claimData.getPolicyNumber());
        SearchOrCreatePolicyScreen findPolicyScreen = (SearchOrCreatePolicyScreen)stepSet.getCurrentStepScreen();
        findPolicyScreen.setPolicyNumber(claimData.getPolicyNumber()).clickSearch();
        log("Policy found");
        findPolicyScreen.setLossDate(claimData.getLossDate());

        log("Specifying basic information");
        InformationTestData informationData = claimData.getInformation();
        BasicInformationScreen basicInformationScreen = (BasicInformationScreen)stepSet.clickNext();
        basicInformationScreen.setReportedBy(informationData.getReportedBy());
        //basicInformationScreen.setRelationToInsured(informationData.getRelationToInsured());

        log("Specifying loss detail");
        LossDetailTestData lossDetailData = claimData.getLossDetail();
        LossDetailScreen lossDetailScreen = (LossDetailScreen)stepSet.clickNext();
        lossDetailScreen.setDescription(lossDetailData.getDescription());
        lossDetailScreen.setLossCause(lossDetailData.getCause());

        ServicesScreen servicesScreen = (ServicesScreen)stepSet.clickNext();
        AssignSaveScreen assignSaveScreen = (AssignSaveScreen)stepSet.clickNext();

        // Submit the claim
        log("Submitting the claim");
        String claimNumber = stepSet.clickFinish().clickViewClaim().getClaimNumber();
        claimData.setClaimNumber(claimNumber);
        log("Homeowners claim successfully created: " + claimNumber);
    }
}
