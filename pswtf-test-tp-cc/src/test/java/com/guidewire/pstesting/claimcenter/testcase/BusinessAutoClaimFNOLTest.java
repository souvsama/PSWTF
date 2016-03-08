package com.guidewire.pstesting.claimcenter.testcase;

import com.guidewire.pstesting.claimcenter.ClaimCenterTestBase;
import com.guidewire.pstesting.claimcenter.claim.*;
import com.guidewire.pstesting.claimcenter.claim.businessauto.BusinessAutoClaimWizardStepSet;
import com.guidewire.pstesting.claimcenter.claim.businessauto.LossDetailScreen;
import com.guidewire.pstesting.claimcenter.data.BusinessAutoClaimTestData;
import com.guidewire.pstesting.claimcenter.data.InformationTestData;
import com.guidewire.pstesting.claimcenter.data.LossDetailTestData;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;

public class BusinessAutoClaimFNOLTest extends ClaimCenterTestBase {
    protected BusinessAutoClaimFNOLTest() throws JAXBException {
    }

    @Test(description = "Test simple business auto claim",
            groups = {"smoke", "cc-smoke", "business-auto-claim"},
            dataProvider = "Business-Auto-Claim",
            enabled = true)
    public void testSimpleClaim(BusinessAutoClaimTestData claimData) {
        log("Test simple commercial auto claim");

        // Log in to ClaimCenter
        log("Application URL: " + getApplication().getUrl());
        Assert.assertTrue(login(claimData.getUser()), "Login failed");

        // Start a new claim
        log("Starting new commercial auto claim");
        ClaimWizardStepSet stepSet = getApplication().newClaim(BusinessAutoClaimWizardStepSet.COMMERCIAL_AUTO_PRODUCT);

        log("Searching for policy: " + claimData.getPolicyNumber());
        SearchOrCreatePolicyScreen findPolicyScreen = (SearchOrCreatePolicyScreen)stepSet.getCurrentStepScreen();
        findPolicyScreen.setPolicyNumber(claimData.getPolicyNumber()).clickSearch();
        log("Policy found");
        findPolicyScreen.setLossDate(claimData.getLossDate());

        log("Selecting involved vehicles");
        InformationTestData informationData = claimData.getInformation();
        InvolvedPolicyVehiclesScreen involvedVehiclesScreen = (InvolvedPolicyVehiclesScreen)stepSet.clickNext();
        involvedVehiclesScreen.selectVehicleRowWithText(informationData.getInvolvedVehicle());

        log("Specifying basic information");
        BasicInformationScreen basicInformationScreen = (BasicInformationScreen)stepSet.clickNext();
        //basicInformationScreen.setHowReported("Phone");
        basicInformationScreen.setReportedBy(informationData.getReportedBy());
        basicInformationScreen.setRelationToInsured(informationData.getRelationToInsured());

        log("Specifying loss detail");
        LossDetailTestData lossDetailData = claimData.getLossDetail();
        LossDetailScreen lossDetailScreen = (LossDetailScreen)stepSet.clickNext();
        lossDetailScreen.setDescription(lossDetailData.getDescription());
        lossDetailScreen.setCause(lossDetailData.getCause());
        lossDetailScreen.setLocation(lossDetailData.getLocation());

        ServicesScreen servicesScreen = (ServicesScreen)stepSet.clickNext();
        AssignSaveScreen assignSaveScreen = (AssignSaveScreen)stepSet.clickNext();

        // Submit the claim
        log("Submitting the claim");
        String claimNumber = stepSet.clickFinish().clickViewClaim().getClaimNumber();
        claimData.setClaimNumber(claimNumber);
        log("Commercial auto policy claim successfully created: " + claimNumber);
    }
}
