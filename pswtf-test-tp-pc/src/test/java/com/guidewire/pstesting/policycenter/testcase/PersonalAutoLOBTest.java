package com.guidewire.pstesting.policycenter.testcase;

import com.guidewire.pstesting.WizardStepSet;
import com.guidewire.pstesting.data.DriverTestData;
import com.guidewire.pstesting.policycenter.NewSubmissionPage;
import com.guidewire.pstesting.policycenter.PolicyCenterApplication;
import com.guidewire.pstesting.policycenter.PolicyCenterTestBase;
import com.guidewire.pstesting.policycenter.PreQualificationScreen;
import com.guidewire.pstesting.policycenter.account.AccountPage;
import com.guidewire.pstesting.policycenter.data.AutoPolicyTestData;
import com.guidewire.pstesting.policycenter.data.pa.VehicleTestData;
import com.guidewire.pstesting.policycenter.submission.*;
import com.guidewire.pstesting.policycenter.submission.pa.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;

public class PersonalAutoLOBTest extends PolicyCenterTestBase {
    protected PersonalAutoLOBTest() throws JAXBException {
    }

    @Test(description = "Test simple personal auto policy submission",
          groups = {"smoke", "pc-smoke", "policy", "personal-auto"},
          dependsOnGroups = "person-account",
          dataProvider = "Personal-Auto",
          enabled = true)
    public void testSimpleSubmission(AutoPolicyTestData policyData) {
        log("Test simple personal auto policy submission");

        final PolicyCenterApplication application = getApplication();

        // Log in to PolicyCenter
        log("Application URL: " + application.getUrl());
        Assert.assertTrue(login(policyData.getUser()), "Login failed");

        // Select account
        final String accountNumber = policyData.getAccountNumber();
        log("Select an account: " + accountNumber);
        AccountPage accountPage = application.searchForAccount(accountNumber);

        // Initiate new personal auto submission
        log("Initiate new personal auto submission");
        NewSubmissionPage submissionPage = newSubmission(accountPage, policyData);

        // Start submission wizard
        log("Select personal auto product offering");
        WizardStepSet stepSet = submissionPage.selectProduct(PersonalAutoSubmissionWizardStepSet.PERSONAL_AUTO_PRODUCT);

        OfferingsScreen offerings = (OfferingsScreen)stepSet.getCurrentStepScreen();
        PreQualificationScreen qualificationScreen = (PreQualificationScreen)stepSet.clickNext();
        PolicyInformationScreen policyInformationScreen = (PolicyInformationScreen)stepSet.clickNext();

        // Add drivers
        log("Adding drivers");
        DriversScreen driversScreen = (DriversScreen)stepSet.clickNext();
        for (DriverTestData driverData : policyData.getDrivers()) {
            log("Adding driver: " + driverData);
            driversScreen.addDriver(driverData);
        }

        // Add vehicles
        log("Adding vehicles");
        VehiclesScreen vehiclesScreen = (VehiclesScreen)stepSet.clickNext();
        for (VehicleTestData vehicleData : policyData.getVehicles()) {
            log("Adding vehicle: " + vehicleData);
            VehicleDetailsPanel vehicleDetailsPanel = vehiclesScreen.clickCreateVehicle();
            vehicleDetailsPanel.setVin(vehicleData.getVin());
            vehicleDetailsPanel.setLicenseState(vehicleData.getLicenseState());
            vehicleDetailsPanel.setCostNew(vehicleData.getCostNew());

            // Add vehicle drivers
            for (DriverTestData driverData : vehicleData.getDrivers()) {
                log("Adding vehicle driver: " + driverData);
                vehicleDetailsPanel.clickAddVehicleDriver();
                vehicleDetailsPanel.selectVehicleDriverToAdd(driverData.getName());
            }
        }

        CoveragesScreen coveragesScreen = (CoveragesScreen)stepSet.clickNext();
        //ModifiersScreen modifiersScreen = (ModifiersScreen)stepSet.clickNext();
        RiskAnalysisScreen riskAnalysisScreen = (RiskAnalysisScreen)stepSet.clickNext();
        PolicyReviewScreen policyReviewScreen = (PolicyReviewScreen)stepSet.clickNext();

        // Quote and issue the policy
        log("Quoting and issuing policy");
        JobCompleteScreen jobCompleteScreen = policyReviewScreen.clickQuote().issuePolicy();
        PolicySummaryScreen policySummaryScreen = jobCompleteScreen.clickViewPolicy();
        policyData.setPolicyNumber(policySummaryScreen.getPolicyNumber());
        log("Personal auto policy submission succeeded: " + policyData.getPolicyNumber());
    }
}
