package com.guidewire.pstesting.claimcenter.testcase;

import com.guidewire.pstesting.claimcenter.ClaimCenterTestBase;
import com.guidewire.pstesting.claimcenter.claim.*;
import com.guidewire.pstesting.claimcenter.claim.personalauto.LossDetailScreen;
import com.guidewire.pstesting.claimcenter.claim.personalauto.PersonalAutoClaimWizardStepSet;
import com.guidewire.pstesting.claimcenter.claim.personalauto.VehicleIncidentPopupScreen;
import com.guidewire.pstesting.claimcenter.data.InformationTestData;
import com.guidewire.pstesting.claimcenter.data.LossDetailTestData;
import com.guidewire.pstesting.claimcenter.data.PersonalAutoClaimTestData;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class PersonalAutoClaimFNOLTest extends ClaimCenterTestBase {

    protected PersonalAutoClaimFNOLTest() throws JAXBException {
    }

    @Test(description = "Test new personal auto claim",
            groups = {"smoke", "cc-smoke", "create-claim", "personal-auto-claim"},
            dataProvider = "Personal-Auto-Claim",
            enabled = true)
    public void testSimpleClaim(PersonalAutoClaimTestData claimData){
        // Log in to ClaimCenter
        log("Application URL: " + getApplication().getUrl());
        Assert.assertTrue(login(claimData.getUser()), "Login failed");

        // Start a new claim
        log("Starting new personal auto claim");
        ClaimWizardStepSet stepSet = getApplication().newClaim(PersonalAutoClaimWizardStepSet.PERSONAL_AUTO_PRODUCT);

        SearchOrCreatePolicyScreen findPolicyScreen = (SearchOrCreatePolicyScreen)stepSet.getCurrentStepScreen();
        findPolicyScreen.setLastName(claimData.getLastName()).clickSearch();
        log("Policy found");
        findPolicyScreen.setLossDate(claimData.getLossDate());

        log("Specifying basic information");
        InformationTestData informationData = claimData.getInformation();
        BasicInformationScreen basicInformationScreen = (BasicInformationScreen)stepSet.clickNext();
        basicInformationScreen.setReportedBy(informationData.getReportedBy());
        basicInformationScreen.selectVehicleInvolved(0);
        String insuredAddress = basicInformationScreen.getInsuredAddress();

        log("Specifying loss detail");
        LossDetailTestData lossDetailData = claimData.getLossDetail();
        LossDetailScreen lossDetailScreen = (LossDetailScreen)stepSet.clickNext();
        lossDetailScreen.setDescription(lossDetailData.getDescription());
        lossDetailScreen.setLossCause(lossDetailData.getCause());
        lossDetailScreen.setLossLocation(insuredAddress);
        lossDetailScreen.setFaultRating(lossDetailData.getFaultRating());
        VehicleIncidentPopupScreen vehicleIncidentPopupScreen = lossDetailScreen.clickVehicleIncidentLink();
        vehicleIncidentPopupScreen.setDescription(lossDetailData.getDescription());
        vehicleIncidentPopupScreen.clickOkButton();

        log("No Services selected");
        ServicesScreen servicesScreen = (ServicesScreen)stepSet.clickNext();
        log("Assign claim");
        AssignSaveScreen assignSaveScreen = (AssignSaveScreen)stepSet.clickNext();

        // Submit the claim
        log("Submitting the claim");
        String claimNumber = stepSet.clickFinish().clickViewClaim().getClaimNumber();
        claimData.setClaimNumber(claimNumber);
        assertThat(claimNumber, notNullValue());
        log("Viewed - Claim Summary: " + claimNumber);
    }

}
