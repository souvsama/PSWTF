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
import com.guidewire.pstesting.policycenter.submission.gbpa.*;
import com.guidewire.pstesting.policycenter.submission.pa.ModifiersScreen;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;

public class MotorLOBTest extends PolicyCenterTestBase {
    protected MotorLOBTest() throws JAXBException {
    }

    @Test(description = "Test simple motor policy submission",
          groups = {"smoke", "pc-smoke", "policy", "motor"},
          dependsOnGroups = "person-account",
          dataProvider = "Motor",
          enabled = false)
    public void testSimpleSubmission(AutoPolicyTestData policyData) {
        log("Test simple motor policy submission");

        final PolicyCenterApplication application = getApplication();

        // Log in to PolicyCenter
        log("Application URL: " + application.getUrl());
        Assert.assertTrue(login(policyData.getUser()), "Login failed");

        // Select account
        final String accountNumber = policyData.getAccountNumber();
        log("Select an account: " + accountNumber);
        AccountPage accountPage = application.searchForAccount(accountNumber);

        // Initiate new motor submission
        log("Initiate new motor submission");
        NewSubmissionPage submissionPage = newSubmission(accountPage, policyData);

        // Start submission wizard
        log("Select motor product offering");
        WizardStepSet stepSet = submissionPage.selectProduct(GBPersonalAutoSubmissionWizardStepSet.MOTOR_PRODUCT);

        // Select offering
        OfferingsScreen offerings = (OfferingsScreen)stepSet.getCurrentStepScreen();
        offerings.setOffering(policyData.getOffering().getName());

        PreQualificationScreen qualificationScreen = (PreQualificationScreen)stepSet.clickNext();
        PolicyInformationScreen policyInformationScreen = (PolicyInformationScreen)stepSet.clickNext();

        // Add drivers
        log("Adding drivers");
        GBDriversScreen driversScreen = (GBDriversScreen)stepSet.clickNext();
        for (DriverTestData driverData : policyData.getDrivers()) {
            log("Adding driver: " + driverData);
            driversScreen.addDriver(driverData);
            // If driver is the account holder, update the account with any policy related
            // account values set on the driver
            if (driverData.isAccountHolder()) {
                application.updateAccount(getAccountByAccountNumber(accountNumber), driverData);
            }
        }

        // Add vehicles
        log("Adding vehicles");
        GBVehiclesScreen vehiclesScreen = (GBVehiclesScreen)stepSet.clickNext();
        for (VehicleTestData vehicleData : policyData.getVehicles()) {
            log("Adding vehicle: " + vehicleData);
            GBVehicleDetailsPanel vehicleDetailsPanel = vehiclesScreen.clickCreateVehicle();
            vehicleDetailsPanel.setLicensePlate(vehicleData.getLicensePlate());
            if (vehicleData.getLookupVehicle()) {
                log("Looking up vehicle: " + vehicleData.getLicensePlate());
                vehicleDetailsPanel.clickVehicleLookup();
            }
            vehicleDetailsPanel.setVin(vehicleData.getVin());
            vehicleDetailsPanel.setVehicleGroup(vehicleData.getVehicleGroup());
            vehicleDetailsPanel.setGarageType(vehicleData.getGarageType());
            vehicleDetailsPanel.setCostNew(vehicleData.getCostNew());
            vehicleDetailsPanel.setEquipmentValue(vehicleData.getEquipmentValue());
            vehicleDetailsPanel.setAnnualMileage(vehicleData.getAnnualMileage());
            vehicleDetailsPanel.setPrimaryUse(vehicleData.getPrimaryUse());

            // Add vehicle drivers
            for (DriverTestData driverData : vehicleData.getDrivers()) {
                log("Adding vehicle driver: " + driverData);
                vehicleDetailsPanel.addDriver(driverData);
            }
        }

        GBCoveragesScreen coveragesScreen = (GBCoveragesScreen)stepSet.clickNext();

        ModifiersScreen modifiersScreen = (ModifiersScreen)stepSet.clickNext();
        RiskAnalysisScreen riskAnalysisScreen = (RiskAnalysisScreen)stepSet.clickNext();
        PolicyReviewScreen policyReviewScreen = (PolicyReviewScreen)stepSet.clickNext();

        // Quote and issue the policy
        log("Quoting and issuing policy");
        JobCompleteScreen jobCompleteScreen = policyReviewScreen.clickQuote().issuePolicy();
        PolicySummaryScreen policySummaryScreen = jobCompleteScreen.clickViewPolicy();
        policyData.setPolicyNumber(policySummaryScreen.getPolicyNumber());
        log("Motor policy submission succeeded: " + policyData.getPolicyNumber());
    }
}
