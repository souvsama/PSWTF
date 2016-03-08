package com.guidewire.pstesting.billingcenter.testcase;

import com.guidewire.pstesting.ApplicationPage;
import com.guidewire.pstesting.billingcenter.BillingCenterTestBase;
import com.guidewire.pstesting.billingcenter.PolicyDetailSummaryScreen;
import com.guidewire.pstesting.billingcenter.data.PolicyVerificationTestData;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;

public class PolicyVerificationTest extends BillingCenterTestBase {
    protected PolicyVerificationTest() throws JAXBException {
    }

    @Test(description = "Verify new policy submission",
          groups = {"smoke", "bc-smoke", "policy-verification"},
          //dependsOnGroups = "policy",
          dataProvider = "Policy-Verification",
          enabled = true)
    public void testPolicyVerification(final PolicyVerificationTestData policyData) {
        log("Verify policy");

        // Log in to BillingCenter
        log("Application URL: " + getApplication().getUrl());
        Assert.assertTrue(login(policyData.getUser()), "Login failed");

        final String policyNumber = policyData.getPolicyNumber();

 /*       TimeAmount tryFor;
        TimeAmount waitBetweenTries;
        RetryTestData retryData = policyData.getRetry();
        if (retryData == null) {
            tryFor = TimeAmount.create("0");
            waitBetweenTries = TimeAmount.create("0");
        } else {
            tryFor = TimeAmount.create(retryData.getTryFor());
            waitBetweenTries = TimeAmount.create(retryData.getWaitBetweenTries());
            log("Retry configuration: tryFor=" + tryFor.toDescriptiveString() +
                        ", waitBetweenTries=" + waitBetweenTries.toDescriptiveString());
        }

        // Perform the verification
        new Retryable(tryFor, waitBetweenTries) {
            @Override
            public void attempt() {
*/                log("Searching for policy: " + policyNumber);
                // Find the policy?
                ApplicationPage resultingPage = getApplication().searchForPolicy(policyNumber);
                if (!PolicyDetailSummaryScreen.class.isInstance(resultingPage)) {
                    throw new RuntimeException("Policy not found: " + policyNumber);
                }
                log("Policy verified: " + policyNumber);
/*
            }

            @Override
            public void attemptFailed() {
                logger.info("Attempt failed");
            }

            @Override
            public void beforeWait() {
                logger.info("Waiting: " + getWaitBetweenTries().toDescriptiveString());
            }

            @Override
            public void beforeRetry() {
                logger.info("Retrying. Remaining time: " + getTryFor().toDescriptiveString());
            }
        }.start();
*/
    }
}
