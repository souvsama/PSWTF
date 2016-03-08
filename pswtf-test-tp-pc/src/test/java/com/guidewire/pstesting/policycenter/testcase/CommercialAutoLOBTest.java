package com.guidewire.pstesting.policycenter.testcase;

import com.guidewire.pstesting.WizardStepSet;
import com.guidewire.pstesting.data.DriverTestData;
import com.guidewire.pstesting.policycenter.NewSubmissionPage;
import com.guidewire.pstesting.policycenter.PolicyCenterTestBase;
import com.guidewire.pstesting.policycenter.PreQualificationScreen;
import com.guidewire.pstesting.policycenter.account.AccountPage;
import com.guidewire.pstesting.policycenter.data.ca.BACoveragesTestData;
import com.guidewire.pstesting.policycenter.data.ca.BAPolicyInfoTestData;
import com.guidewire.pstesting.policycenter.data.ca.BAVehicleTestData;
import com.guidewire.pstesting.policycenter.data.ca.BusinessAutoPolicyTestData;
import com.guidewire.pstesting.policycenter.submission.*;
import com.guidewire.pstesting.policycenter.submission.ca.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;

public class CommercialAutoLOBTest extends PolicyCenterTestBase {
    protected CommercialAutoLOBTest() throws JAXBException {
    }

    @Test(description = "Test simple business auto policy submission",
          groups = {"smoke", "pc-smoke", "policy", "business-auto", "policy-cancel"},
          dependsOnGroups = "company-account",
          dataProvider = "Business-Auto",
          enabled = true)
    public void testSimpleSubmission(BusinessAutoPolicyTestData policyData) {
        log("Test simple business auto policy submission");

        // Log in to PolicyCenter
        log("Application URL: " + getApplication().getUrl());
        Assert.assertTrue(login(policyData.getUser()), "Login failed");

        // Select account
        log("Select an account: " + policyData.getAccountNumber());
        AccountPage accountPage = getApplication().searchForAccount(policyData.getAccountNumber());

        // Initiate new business auto submission
        log("Initiate new business auto submission");
        NewSubmissionPage submissionPage = newSubmission(accountPage, policyData);

        // Start submission wizard
        log("Select business auto product offering");
        WizardStepSet stepSet = submissionPage.selectProduct(BASubmissionWizardStepSet.COMMERCIAL_AUTO_PRODUCT);

        OfferingsScreen offerings = (OfferingsScreen)stepSet.getCurrentStepScreen();
        offerings.setOffering("Standard");
        PreQualificationScreen qualificationScreen = (PreQualificationScreen)stepSet.clickNext();

        // Policy information
        log("Specifying policy information");
        BAPolicyInfoTestData infoData = policyData.getPolicyInfo();
        BAPolicyInfoScreen policyInfoScreen = (BAPolicyInfoScreen)stepSet.clickNext();
        policyInfoScreen.setOrganizationType(infoData.getOrganizationType());

        // Line coverage
        log("Specifying line coverage");
        BACoveragesTestData coveragesData = policyData.getCoverages();
        BALineScreen lineScreen = (BALineScreen)stepSet.clickNext();
        BALineCoveragesPanel coveragesPanel = lineScreen.selectCoveragesTab();
        coveragesPanel.setProduct(coveragesData.getProduct());
        coveragesPanel.setFleet(coveragesData.getFleet());

        LocationsScreen locationsScreen = (LocationsScreen)stepSet.clickNext();

        // Add vehicles
        log("Adding vehicles");
        BAVehiclesScreen vehiclesScreen = (BAVehiclesScreen)stepSet.clickNext();
        for (BAVehicleTestData vehicleData : policyData.getVehicles()) {
            log("Adding vehicle: " + vehicleData);
            BAVehiclePopup vehiclePopup = vehiclesScreen.clickCreateVehicle();
            BAVehicleDetailPanel vehicleDetailPanel = vehiclePopup.selectVehicleDetailsTab();
            vehicleDetailPanel.setGaragedAt(vehicleData.getGaragedAt());
            vehicleDetailPanel.setVehicleType(vehicleData.getType());
            vehicleDetailPanel.setVin(vehicleData.getVin());
            vehicleDetailPanel.setCost(vehicleData.getCost());
            vehicleDetailPanel.setClassCode(vehicleData.getClassCode());
            vehiclePopup.clickOk();
            vehiclesScreen.waitUntilVisible();
        }

        // Go back to line coverages step to accept default auto owned liability limit
        log("Specifying line coverage liability limit: " + coveragesData.getLiabilityLimit());
        lineScreen = (BALineScreen)stepSet.clickBack(2);
        coveragesPanel = lineScreen.selectCoveragesTab();
        coveragesPanel.setLiabilityLimit(coveragesData.getLiabilityLimit());
        stepSet.clickNext();
        stepSet.clickNext();

        BAStateInfoScreen stateInfoScreen = (BAStateInfoScreen)stepSet.clickNext();

        // Add drivers
        log("Adding drivers");
        BADriversScreen driversScreen = (BADriversScreen)stepSet.clickNext();
        for (DriverTestData driverData : policyData.getDrivers()) {
            log("Adding driver: " + driverData);
            driversScreen.addDriver(driverData);
        }

        CoveredVehiclesScreen coveredVehiclesScreen = (CoveredVehiclesScreen)stepSet.clickNext();
        ModifiersScreen modifiersScreen = (ModifiersScreen)stepSet.clickNext();
        RiskAnalysisScreen riskAnalysisScreen = (RiskAnalysisScreen)stepSet.clickNext();
        PolicyReviewScreen policyReviewScreen = (PolicyReviewScreen)stepSet.clickNext();

        // Quote and issue the policy
        log("Quoting and issuing policy");
        JobCompleteScreen jobCompleteScreen = policyReviewScreen.clickQuote().issuePolicy();
        PolicySummaryScreen policySummaryScreen = jobCompleteScreen.clickViewPolicy();
        policyData.setPolicyNumber(policySummaryScreen.getPolicyNumber());
        log("Business auto policy submission succeeded: " + policyData.getPolicyNumber());
    }
}
