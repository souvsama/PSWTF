package com.guidewire.pstesting.policycenter.testcase;

import com.guidewire.pstesting.WizardStepSet;
import com.guidewire.pstesting.policycenter.NewSubmissionPage;
import com.guidewire.pstesting.policycenter.PolicyCenterTestBase;
import com.guidewire.pstesting.policycenter.PreQualificationScreen;
import com.guidewire.pstesting.policycenter.account.AccountPage;
import com.guidewire.pstesting.policycenter.data.ca.BAPolicyInfoTestData;
import com.guidewire.pstesting.policycenter.data.pa.OfferingTestData;
import com.guidewire.pstesting.policycenter.data.smeshops.SMEEUBuildingCoverageTestData;
import com.guidewire.pstesting.policycenter.data.smeshops.SMEEUCoveragesTestData;
import com.guidewire.pstesting.policycenter.data.smeshops.SMEEULocationTestData;
import com.guidewire.pstesting.policycenter.data.smeshops.SMEEUPolicyTestData;
import com.guidewire.pstesting.policycenter.submission.*;
import com.guidewire.pstesting.policycenter.submission.smeshops.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;

public class SMEShopsLOBTest extends PolicyCenterTestBase {
    protected SMEShopsLOBTest() throws JAXBException {
    }

    @Test(description = "Test simple SME Shops policy submission",
          groups = {"smoke", "pc-smoke", "policy", "sme-shops"},
          dependsOnGroups = "company-account",
          dataProvider = "SME-Shops",
          enabled = false)
    public void testSimpleSubmission(SMEEUPolicyTestData policyData) {
        log("Test simple SME Shops policy submission");

        // Log in to PolicyCenter
        log("Application URL: " + getApplication().getUrl());
        Assert.assertTrue(login(policyData.getUser()), "Login failed");

        // Select account
        log("Select an account: " + policyData.getAccountNumber());
        AccountPage accountPage = getApplication().searchForAccount(policyData.getAccountNumber());

        // Initiate new SME shops submission
        log("Initiate new SME Shops submission");
        NewSubmissionPage submissionPage = newSubmission(accountPage, policyData);

        // Start submission wizard
        log("Select SME Shops product offering");
        WizardStepSet stepSet = submissionPage.selectProduct(SMEEUSubmissionWizardStepSet.SME_SHOPS_PRODUCT);

        // Specify offering
        OfferingTestData offeringData = policyData.getOffering();
        OfferingsScreen offeringsScreen = (OfferingsScreen)stepSet.getCurrentStepScreen();
        if (offeringData != null) {
            offeringsScreen.setOffering(offeringData.getName());
        }

        // Specify qualifications
        PreQualificationScreen qualificationScreen = (PreQualificationScreen)stepSet.clickNext();

        // Specify policy info
        log("Specifying policy information");
        BAPolicyInfoTestData policyInfoData = policyData.getPolicyInfo();
        PolicyInformationScreen policyInformationScreen = (PolicyInformationScreen)stepSet.clickNext();
        if (policyInfoData != null) {
            policyInformationScreen.setOrganizationType(policyInfoData.getOrganizationType());
        }

        // Specify premises
        log("Adding locations");
        SMEEUPremisesScreen premisesScreen = (SMEEUPremisesScreen)stepSet.clickNext();
        for (SMEEULocationTestData locationData : policyData.getLocations()) {
            log("Adding location: " + locationData);
            SMEEULocationPopup locationPopup = premisesScreen.clickNewLocation();
            // Specify premises details
            log("Specifying location details");
            SMEUPremisesDetailPanel premisesDetailPanel = locationPopup.selectPremisesDetailsTab();
            premisesDetailPanel.setAddress1(locationData.getAddress1());
            premisesDetailPanel.setCity(locationData.getCity());
            premisesDetailPanel.setPostalCode(locationData.getPostcode());
            premisesDetailPanel.setAnyAsbestos(locationData.getAsbestos());
            // Select trade code
            log("Selecting trade code: " + locationData.getTradeCode());
            SMEEUTradeCodeSearchPopup tradeCodeSearchPopup = premisesDetailPanel.clickSelectTradeCode();
            tradeCodeSearchPopup.setTradeCode(locationData.getTradeCode());
            tradeCodeSearchPopup.clickSearchButton().selectFirstRow();
            // Specify base cover and security
            log("Specifying location coverages");
            SMEEUCoveragesTestData coveragesData = locationData.getCoverages();
            SMEEUBasicCoverPanel basicCoverPanel = locationPopup.selectBasicCoverTab();
            SMEEUBuildingCoverageTestData buildingCoverageData = coveragesData.getBuildingCoverages();
            if (buildingCoverageData != null) {
                basicCoverPanel.enableBuildingCoverage(true);
                basicCoverPanel.setBuildingAmount(buildingCoverageData.getAmount());
                basicCoverPanel.setRentalIncomeLoss(buildingCoverageData.getRentalIncomeLoss());
            }
            locationPopup.clickOk().waitUntilVisible();
        }

        SMEEUAdditionalCoveragesScreen additionalCoveragesScreen = (SMEEUAdditionalCoveragesScreen)stepSet.clickNext();
        RiskAnalysisScreen riskAnalysisScreen = (RiskAnalysisScreen)stepSet.clickNext();
        PolicyReviewScreen policyReviewScreen = (PolicyReviewScreen)stepSet.clickNext();

        // Quote and issue the policy
        log("Quoting and issuing policy");
        JobCompleteScreen jobCompleteScreen = policyReviewScreen.clickQuote().issuePolicy();
        PolicySummaryScreen policySummaryScreen = jobCompleteScreen.clickViewPolicy();
        policyData.setPolicyNumber(policySummaryScreen.getPolicyNumber());
        log("SME Shops policy submission succeeded: " + policyData.getPolicyNumber());
    }
}