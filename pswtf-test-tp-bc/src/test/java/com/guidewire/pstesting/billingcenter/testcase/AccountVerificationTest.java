package com.guidewire.pstesting.billingcenter.testcase;

import com.guidewire.pstesting.AccountVerificationTestData;
import com.guidewire.pstesting.ApplicationPage;
import com.guidewire.pstesting.billingcenter.AccountDetailSummaryScreen;
import com.guidewire.pstesting.billingcenter.BillingCenterTestBase;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;

public class AccountVerificationTest extends BillingCenterTestBase {
    protected AccountVerificationTest() throws JAXBException {
    }

    @Test(description = "Verify new account creation",
          groups = {"smoke", "bc-smoke", "bc-account-verification"},
          dataProvider = "BC-Account-Verification",
          enabled = true)
    public void testAccountVerification(final AccountVerificationTestData accountData) {
        log("Verify account (BillingCenter)");

        // Log in to BillingCenter
        log("Application URL: " + getApplication().getUrl());
        Assert.assertTrue(login(accountData.getUser()), "Login failed");

        final String accountNumber = accountData.getAccountNumber();

                log("Searching for account: " + accountNumber);
                // Find the account?
                ApplicationPage resultingPage = getApplication().searchForAccount(accountNumber);
                if (!AccountDetailSummaryScreen.class.isInstance(resultingPage)) {
                    throw new RuntimeException("Account not found: " + accountNumber);
                }
                log("Account verified: " + accountNumber);
    }
}
