package com.guidewire.pstesting.policycenter.testcase;

import com.guidewire.pstesting.policycenter.PolicyCenterApplication;
import com.guidewire.pstesting.policycenter.PolicyCenterTestBase;
import com.guidewire.pstesting.policycenter.data.CancellationTestData;
import com.guidewire.pstesting.policycenter.data.ca.BusinessAutoPolicyTestData;
import com.guidewire.pstesting.policycenter.policy.CancellationWizardScreen;
import com.guidewire.pstesting.policycenter.policy.StartCancellationScreen;
import com.guidewire.pstesting.policycenter.submission.PolicySummaryScreen;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class PolicyCancelManualTest extends PolicyCenterTestBase {
    protected PolicyCancelManualTest() throws JAXBException {
    }

    @Test(description = "Test manual cancellation of auto policy",
            groups = {"policy", "policy-cancel"},
            dependsOnGroups = "business-auto",
            dataProvider = "Business-Auto",
            enabled = true)
    public void testCancelAutoPolicy(BusinessAutoPolicyTestData policyData) {
        log("Test manual cancellation of auto policy");

        final PolicyCenterApplication application = getApplication();

        // Log in to PolicyCenter
        log("Application URL: " + application.getUrl());
        Assert.assertTrue(login(policyData.getUser()), "Login failed");

        // Select policy
        // Use policyData.getPolicyNumber if part of a policy lifecycle test
        CancellationTestData cancellationData = policyData.getCancellation();
        final String policyNumber = policyData.getPolicyNumber();
        log("Select a policy: " + policyNumber);
        PolicySummaryScreen policyPage = application.searchForPolicy(policyNumber);

        // Initiate manual cancellation on policy
        log("Initiate manual cancellation");
        StartCancellationScreen initiateCancelPage = policyPage.cancelPolicy();

        // Cancellation information
        log("Specify cancellation information");
        initiateCancelPage.selectSource(cancellationData.getSource());
        initiateCancelPage.selectReason(cancellationData.getReason());
        String refundMethod = initiateCancelPage.getRefundMethod();
        log("Refund method chosen: " + refundMethod);
        assertThat(refundMethod,is(cancellationData.getRefundMethod()));
        log("Effective date: " + initiateCancelPage.getEffectiveDate());
        assertThat(initiateCancelPage.getEffectiveDate(), is(notNullValue()));

        // Initiate cancellation
        log("Initiating cancellation");
        CancellationWizardScreen confirmationPage = initiateCancelPage.startCancellation();
        log("Cancellation transaction: " + confirmationPage.getCancellationId());

        // Withdraw cancellation
        log("Withdraw cancellation");
        policyPage = confirmationPage.withdrawCancellation();
        log("Cancellation withdrawn");
    }
    }
