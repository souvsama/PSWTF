package com.guidewire.pstesting.claimcenter.testcase;

import com.guidewire.pstesting.claimcenter.ClaimCenterTestBase;
import com.guidewire.pstesting.claimcenter.ClaimSummaryScreen;
import com.guidewire.pstesting.claimcenter.claim.*;
import com.guidewire.pstesting.claimcenter.claim.payment.ClaimPaymentWizardStepSet;
import com.guidewire.pstesting.claimcenter.claim.payment.PayeeInformationScreen;
import com.guidewire.pstesting.claimcenter.claim.payment.PaymentInformationScreen;
import com.guidewire.pstesting.claimcenter.data.InformationTestData;
import com.guidewire.pstesting.claimcenter.data.PaymentDetailTestData;
import com.guidewire.pstesting.claimcenter.data.PersonalAutoClaimTestData;
import com.guidewire.pstesting.claimcenter.data.ReserveDetailTestData;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;


public class PersonalAutoClaimSettleAndCompleteTest extends ClaimCenterTestBase {
    protected PersonalAutoClaimSettleAndCompleteTest() throws JAXBException {
    }

    @Test(description = "Test payment on personal auto claim",
            groups = {"smoke", "cc-smoke", "cc-claim-settlement","personal-auto-claim"},
            dependsOnGroups = "create-claim",
            dataProvider = "Personal-Auto-Claim",
            enabled = true)
    public void TestSimpleClaimPayment(PersonalAutoClaimTestData claimData){
        // Log in to ClaimCenter
        log("Application URL: " + getApplication().getUrl());
        Assert.assertTrue(login(claimData.getUser()), "Login failed");

        //Open existing claim
        log("Open existing claim");
        ClaimTabMenu tabMenu = new ClaimTabMenu(getController());
        ClaimSummaryScreen claimSummaryScreen = tabMenu.findClaim(claimData.getClaimNumber());

        // Create new exposure - vehicle screen
        log("Creating new exposure");
        InformationTestData informationData = claimData.getInformation();
        NewExposureVehicleScreen newExposureVehicleScreen = claimSummaryScreen.createNewVehicleExposure();
        newExposureVehicleScreen.setClaimant(informationData.getReportedBy());
        claimSummaryScreen = newExposureVehicleScreen.clickUpdateButton();
        Assert.assertTrue(claimSummaryScreen.hasExposure(), "Claim has no exposure.");

        // Make a reserve
        log("Creating new reserve");
        ReserveDetailTestData reserveDetailData = claimData.getReserveDetail();
        ReserveScreen reserveScreen = claimSummaryScreen.createNewReserve();
        reserveScreen.setExposure(reserveDetailData.getExposure());
        reserveScreen.setCostType(reserveDetailData.getCostType());
        reserveScreen.setCostCategory(reserveDetailData.getCostCategory());
        reserveScreen.setReserveAmount(reserveDetailData.getReserveAmount());
        FinancialTransactionsScreen financialTransactionsScreen = reserveScreen.clickSaveButton();

        // Test for Ability to Pay
        log("Validate Claim + Exposure Ability to Pay");// demo data does not have 17-digit VIN - Ability to pay will fail
        Assert.assertTrue(claimSummaryScreen.isAbleToPay(), "Claim is not in state: Ability To Pay");

        // Change a reserve
/*
        log("Updating original reserve");
        ReserveDetailsScreen reserveDetailsScreen = financialTransactionsScreen.selectReserveWithAmount(reserveDetailData.getReserveAmount());
        //ReserveDetailsScreen reserveDetailsScreen = financialTransactionsScreen.selectFirstReserve();
        reserveScreen = reserveDetailsScreen.clickEditButton();
        reserveScreen.setReserveAmount(new BigDecimal(reserveDetailData.getReserveAmount()).add(new BigDecimal(50))); // Add an extra 50 to the reserve amount defined in the claim data XML.
        financialTransactionsScreen = reserveScreen.clickSaveButton();
*/

        // Make Payment
        log("Creating new payment");
        PaymentDetailTestData paymentDetailTestData = claimData.getPaymentDetailTestData();
        FinancialSummaryScreen financialSummaryScreen = claimSummaryScreen.createNewPayment();
        ClaimPaymentWizardStepSet claimPaymentWizardStepSet = financialSummaryScreen.createPayment();
        PayeeInformationScreen payeeInformationScreen = (PayeeInformationScreen) claimPaymentWizardStepSet.getCurrentStepScreen();
        PaymentInformationScreen paymentInformationScreen = (PaymentInformationScreen) claimPaymentWizardStepSet.clickNext();
        paymentInformationScreen.setCategory(paymentDetailTestData.getCategory());
        paymentInformationScreen.setAmount(paymentDetailTestData.getAmount());
        FinancialPaymentsScreen financialPaymentsScreen = claimPaymentWizardStepSet.clickFinish();

        //Complete Activities
        log("Completing all activities");
        ClaimWorkplanScreen claimWorkplanScreen = financialPaymentsScreen.clickClaimWorkplanMenuLink();
        claimWorkplanScreen.selectAllActivities();
        AssignActivitiesScreen assignActivitiesScreen = claimWorkplanScreen.clickAssignButton();
        assignActivitiesScreen.clickSearchRadioButton();
        assignActivitiesScreen.setUsernameSearchCriteria(claimData.getUser().getUsername());
        assignActivitiesScreen.clickSearch();
        claimWorkplanScreen = assignActivitiesScreen.clickAssignButton();
        claimWorkplanScreen.selectAllActivities();
        claimWorkplanScreen.clickCompleteButton();

        //Close Exposures
        log("Closing all exposures");
        ExposuresScreen exposuresScreen = claimWorkplanScreen.clickExposuresMenuLink();
        exposuresScreen.selectAllExposures();
        CloseExposuresPopupScreen closeExposuresPopupScreen = exposuresScreen.clickCloseExposureButton();
        exposuresScreen = closeExposuresPopupScreen.clickCloseExposureButtonAndConfirm();

        //Close Claim
        log("Closing claim");
        CloseClaimPopupScreen closeClaimPopupScreen = exposuresScreen.clickCloseClaimAction();
        claimSummaryScreen = closeClaimPopupScreen.clickCloseClaimButton();
        Assert.assertTrue(claimSummaryScreen.isClaimClosed(), "Claim is not closed.");

    }
}
