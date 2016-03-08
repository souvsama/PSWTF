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
import com.guidewire.pstesting.policycenter.data.ho.*;
import com.guidewire.pstesting.policycenter.submission.JobCompleteScreen;
import com.guidewire.pstesting.policycenter.submission.PolicyInformationScreen;
import com.guidewire.pstesting.policycenter.submission.PolicySummaryScreen;
import com.guidewire.pstesting.policycenter.submission.ho.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;

public class HomeownersLOBTest extends PolicyCenterTestBase {
    protected HomeownersLOBTest() throws JAXBException {
    }

    @Test(description = "Test simple homeowners policy submission",
          groups = {"smoke", "pc-smoke", "policy", "homeowners"},
          dependsOnGroups = "person-account",
          dataProvider = "Homeowners",
          enabled = false)
    public void testSimpleSubmission(HomeownersPolicyTestData policyData) {
        log("Test simple homeowners policy submission");

        // Log in to PolicyCenter
        log("Application URL: " + getApplication().getUrl());
        Assert.assertTrue(login(policyData.getUser()), "Login failed");

        final PolicyCenterApplication application = getApplication();

        // Select account
        log("Select an account: " + policyData.getAccountNumber());
        AccountPage accountPage = application.searchForAccount(policyData.getAccountNumber());

        // Initiate new personal auto submission
        log("Initiate new homeowners submission");
        NewSubmissionPage submissionPage = newSubmission(accountPage, policyData);

        // Start submission wizard
        log("Select homeowners product offering");
        WizardStepSet stepSet = submissionPage.selectProduct(HomeownersSubmissionWizardStepSet.HOMEOWNERS_PRODUCT);

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

        PolicyInformationScreen policyInfoScreen = (PolicyInformationScreen)stepSet.clickNext();

        // Dwelling
        DwellingScreen dwellingScreen = (DwellingScreen)stepSet.clickNext();
        DwellingTestData dwellingTestData = policyData.getDwelling();

        // Dwelling details
        log("Specifying dwelling details");
        DwellingDetailsTestData detailsTestData = dwellingTestData.getDetails();
        DwellingDetailsPanel detailsPanel = dwellingScreen.selectDetailsTab();
        detailsPanel.setDistanceToFireHydrant(detailsTestData.getDistanceToFireHydrant());
        detailsPanel.setDistanceToFireStation(detailsTestData.getDistanceToFireStation());
        detailsPanel.setFloodFireHazard(detailsTestData.getFloodFireHazard());
        detailsPanel.setWithinCommercialProperty(detailsTestData.getWithinCommercialProperty());
        detailsPanel.setRoomersBoarders(detailsTestData.getRoomersBoarders());
        detailsPanel.setFireWoodStove(detailsTestData.getFireWoodStove());
        detailsPanel.setSwimmingPool(detailsTestData.getSwimmingPool());
        detailsPanel.setTrampoline(detailsTestData.getTrampoline());
        detailsPanel.setWaterLeakage(detailsTestData.getWaterLeakage());
        detailsPanel.setAnyAnimals(detailsTestData.getAnyAnimals());

        // Dwelling protection details
        log("Specifying dwelling protection details");
        DwellingProtectionTestData protectionTestData = dwellingTestData.getProtection();
        DwellingProtectionDetailsPanel protectionDetailsPanel = dwellingScreen.selectProtectionDetailsTab();
        protectionDetailsPanel.setLocationType(protectionTestData.getLocationType());
        protectionDetailsPanel.setFireExtinguisher(protectionTestData.getFireExtinguisher());
        protectionDetailsPanel.setBurglarAlarm(protectionTestData.getBurglarAlarm());
        protectionDetailsPanel.setBurglarAlarmType(protectionTestData.getBurglarAlarmType());
        protectionDetailsPanel.setFireAlarmReportingCenter(protectionTestData.getFireAlarmReportingCenter());
        protectionDetailsPanel.setSmokeAlarms(protectionTestData.getSmokeAlarms());
        protectionDetailsPanel.setSmokeAlarmAllFloors(protectionTestData.getSmokeAlarmsAllFloors());
        protectionDetailsPanel.setSprinklerSystemType(protectionTestData.getSprinklerSystemType());
        protectionDetailsPanel.setDeadbolts(protectionTestData.getDeadbolts());
        protectionDetailsPanel.setNumberOfDeadbolts(protectionTestData.getNumberOfDeadbolts());
        protectionDetailsPanel.setResidenceVisible(protectionTestData.getResidenceVisible());

        // Dwelling construction
        log("Specifying dwelling construction details");
        DwellingConstructionTestData constructionTestData = dwellingTestData.getConstruction();
        DwellingConstructionScreen dwellingConstructionScreen = (DwellingConstructionScreen)stepSet.clickNext();
        dwellingConstructionScreen.setYearBuilt(constructionTestData.getYearBuilt());
        dwellingConstructionScreen.setConstructionType(constructionTestData.getConstructionType());
        dwellingConstructionScreen.setNumberOfStories(constructionTestData.getNumberOfStories());
        dwellingConstructionScreen.setSquareFootage(constructionTestData.getSquareFootage());
        dwellingConstructionScreen.setGarage(constructionTestData.getGarage());
        dwellingConstructionScreen.setFoundationType(constructionTestData.getFoundationType());
        dwellingConstructionScreen.setRoofType(constructionTestData.getRoofType());
        dwellingConstructionScreen.setPrimaryHeatingType(constructionTestData.getPrimaryHeatingType());
        dwellingConstructionScreen.setSecondaryHeating(constructionTestData.getSecondaryHeating());
        dwellingConstructionScreen.setPlumbing(constructionTestData.getPlumbing());
        dwellingConstructionScreen.setWiring(constructionTestData.getWiring());
        dwellingConstructionScreen.setElectricalSystem(constructionTestData.getElectricalSystem());
        dwellingConstructionScreen.setNumberOfAmps(constructionTestData.getNumberOfAmps());
        dwellingConstructionScreen.setWindClass(constructionTestData.getWindClass());
        dwellingConstructionScreen.setConstructionClassCode(constructionTestData.getConstructionClassCode());

        // Coverages
        log("Specifying dwelling coverages");
        CoveragesTestData coveragesTestData = policyData.getCoverages();
        CoveragesScreen coveragesScreen = (CoveragesScreen)stepSet.clickNext();
        if (coveragesTestData != null) {
            CoveragesPanel coveragesPanel = coveragesScreen.selectCoveragesTab();
            // Homeowners dwelling coverage
            HomeownersDwellingCoverageTestData dwellingCoverageTestData = coveragesTestData.getHomeownersDwellingCoverage();
            if (dwellingCoverageTestData != null) {
                log("Specifying homeowners dwelling coverages: " + dwellingCoverageTestData);
                coveragesPanel.setHomeownersDwellingLimit(dwellingCoverageTestData.getLimit());
            }
            // Fire dwelling coverage
            FireDwellingCoverageTestData fireDwellingCoverage = coveragesTestData.getFireDwellingCoverage();
            if (fireDwellingCoverage != null) {
                log("Specifying fire dwelling coverages: " + fireDwellingCoverage);
                coveragesPanel.setFireDwellingLimit(fireDwellingCoverage.getLimit());
            }
        }

        // Quote and issue the policy
        log("Quoting and issuing policy");
        JobCompleteScreen jobCompleteScreen = coveragesScreen.clickQuote().issuePolicy();
        PolicySummaryScreen policySummaryScreen = jobCompleteScreen.clickViewPolicy();
        policyData.setPolicyNumber(policySummaryScreen.getPolicyNumber());
        log("Homeowners policy submission succeeded: " + policyData.getPolicyNumber());
    }
}
