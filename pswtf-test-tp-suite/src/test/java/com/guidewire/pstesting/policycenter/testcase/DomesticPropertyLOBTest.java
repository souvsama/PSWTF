package com.guidewire.pstesting.policycenter.testcase;

import com.guidewire.pstesting.WizardStepSet;
import com.guidewire.pstesting.data.QuestionSetTestData;
import com.guidewire.pstesting.data.QuestionTestData;
import com.guidewire.pstesting.policycenter.NewSubmissionPage;
import com.guidewire.pstesting.policycenter.PolicyCenterApplication;
import com.guidewire.pstesting.policycenter.PolicyCenterTestBase;
import com.guidewire.pstesting.policycenter.PreQualificationScreen;
import com.guidewire.pstesting.policycenter.account.AccountPage;
import com.guidewire.pstesting.policycenter.data.QualificationsTestData;
import com.guidewire.pstesting.policycenter.data.domeu.DOMEUCoveragesTestData;
import com.guidewire.pstesting.policycenter.data.domeu.DOMEUDwellingCoveragesTestData;
import com.guidewire.pstesting.policycenter.data.domeu.DOMEUDwellingDetailsTestData;
import com.guidewire.pstesting.policycenter.data.domeu.DOMEUPolicyTestData;
import com.guidewire.pstesting.policycenter.submission.JobCompleteScreen;
import com.guidewire.pstesting.policycenter.submission.OfferingsScreen;
import com.guidewire.pstesting.policycenter.submission.PolicyInformationScreen;
import com.guidewire.pstesting.policycenter.submission.PolicySummaryScreen;
import com.guidewire.pstesting.policycenter.submission.domeu.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;

public class DomesticPropertyLOBTest extends PolicyCenterTestBase {
    protected DomesticPropertyLOBTest() throws JAXBException {
    }

    @Test(description = "Test simple domestic property policy submission",
          groups = {"smoke", "pc-smoke", "policy", "domestic-property"},
          dependsOnGroups = "person-account",
          dataProvider = "Domestic-Property",
          enabled = false)
    public void testSimpleSubmission(DOMEUPolicyTestData policyData) {
        log("Test simple domestic property policy submission");

        // Log in to PolicyCenter
        log("Application URL: " + getApplication().getUrl());
        Assert.assertTrue(login(policyData.getUser()), "Login failed");

        final PolicyCenterApplication application = getApplication();

        // Select account
        log("Select an account: " + policyData.getAccountNumber());
        AccountPage accountPage = application.searchForAccount(policyData.getAccountNumber());

        // Initiate new personal auto submission
        log("Initiate new domestic property submission");
        NewSubmissionPage submissionPage = newSubmission(accountPage, policyData);

        // Start submission wizard
        log("Select domestic property product offering");
        WizardStepSet stepSet = submissionPage.selectProduct(DOMEUSubmissionWizardStepSet.DOMESTIC_PROPERTY_PRODUCT);

        PreQualificationScreen qualificationScreen = (PreQualificationScreen)stepSet.getCurrentStepScreen();

        // Qualifications
        log("Specifying qualifications");
        QualificationsTestData qualifications = policyData.getQualifications();
        String policyType = application.replaceResourceVariables(qualifications.getPolicyType());
        log("Policy type: " + policyType);
        qualificationScreen.setPolicyType(policyType);
        log("Answering qualification questions");
        for (QuestionSetTestData questionSet : qualifications.getQuestionSets()) {
            for (QuestionTestData question : questionSet.getQuestions()) {
                qualificationScreen.answerQuestion(questionSet.getQuestionSetId(),
                                                   question.getMessage(),
                                                   question.getAnswer());
            }
        }

        // Select offering
        OfferingsScreen offeringsScreen = (OfferingsScreen)stepSet.clickNext();
        offeringsScreen.setOffering(policyData.getOffering().getName());

        PolicyInformationScreen policyInfoScreen = (PolicyInformationScreen)stepSet.clickNext();

        // Dwelling
        DOMEUDwellingScreen dwellingScreen = (DOMEUDwellingScreen)stepSet.clickNext();
        DOMEUDwellingDetailsPanel detailsPanel = dwellingScreen.clickCreateDwelling();

        // Dwelling details
        log("Specifying dwelling details");
        DOMEUDwellingDetailsTestData detailsTestData = policyData.getDwellingDetails();
        detailsPanel.setYearBuilt(detailsTestData.getYearBuilt());
        detailsPanel.setResidenceType(detailsTestData.getResidenceType());
        detailsPanel.setListedBuilding(detailsTestData.getListedBuilding());
        detailsPanel.setRoofConstruction(detailsTestData.getRoofConstruction());
        detailsPanel.setWallConstruction(detailsTestData.getWallConstruction());
        detailsPanel.setDwellingType(detailsTestData.getDwellingType());
        detailsPanel.setEstimatedRebuildCost(detailsTestData.getEstimatedRebuildCost());

        // Coverages
        log("Specifying dwelling coverages");
        DOMEUCoveragesTestData coveragesTestData = policyData.getCoverages();
        DOMEUCoveragesScreen coveragesScreen = (DOMEUCoveragesScreen)stepSet.clickNext();
        DOMEUDwellingCoveragesPanel coveragesPanel = coveragesScreen.getDwellingCoveragesPanel();
        DOMEUDwellingCoveragesTestData dwellingCoverages = coveragesTestData.getDwellingCoverage();
        coveragesPanel.setSumInsured(dwellingCoverages.getSumInsured());

        // Quote and issue the policy
        log("Quoting and issuing policy");
        JobCompleteScreen jobCompleteScreen = coveragesScreen.clickQuote().issuePolicy();
        PolicySummaryScreen policySummaryScreen = jobCompleteScreen.clickViewPolicy();
        policyData.setPolicyNumber(policySummaryScreen.getPolicyNumber());
        log("Domestic property policy submission succeeded: " + policyData.getPolicyNumber());
    }
}
