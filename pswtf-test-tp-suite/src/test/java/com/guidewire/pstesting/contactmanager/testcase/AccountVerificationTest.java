package com.guidewire.pstesting.contactmanager.testcase;

import com.guidewire.pstesting.AccountVerificationTestData;
import com.guidewire.pstesting.contactmanager.*;
import com.guidewire.pstesting.policycenter.data.AccountTestData;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;

public class AccountVerificationTest extends ContactManagerTestBase {
    protected AccountVerificationTest() throws JAXBException {
    }

    @Test(description = "Verify new account creation",
          groups = {"smoke", "cm-smoke", "cm-account-verification"},
          dependsOnGroups = "policy",
          dataProvider = "CM-Account-Verification",
          enabled = true)
    public void testAccountVerification(final AccountVerificationTestData accountData) {
        log("Verify account (ContactManager)");
        final ContactManagerApplication application = getApplication();

        // Log in to ContactManager
        log("Application URL: " + getApplication().getUrl());
        Assert.assertTrue(login(accountData.getUser()), "Login failed");

        final AccountTestData account = accountData.getAccount();

/*
        TimeAmount tryFor;
        TimeAmount waitBetweenTries;
        RetryTestData retryData = accountData.getRetry();
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
            final ContactManagerApplication application = getApplication();

            @Override
            public void attempt() {
*/
                log("Searching for account: " + account.getName());
                ContactSearchScreen searchScreen;
                if (account.isPerson()) {
                    searchScreen = application.searchForPerson(account.getFirstName(), account.getLastName());
                } else if (account.isCompany()) {
                    searchScreen = application.searchForCompany(account.getCompanyName());
                } else {
                    throw new RuntimeException("Unknown account type: " + account);
                }
                // Account located?
                if (searchScreen.searchReturnedResults()) {
                    log("Account located");
                } else {
                    throw new RuntimeException("Account not found: " + account);
                }
                if (accountData.getCheckDetails()) {
                    log("Checking account synchronization");
                    ContactDetailScreen detailScreen = searchScreen.selectContactFromSearchResults(account.getName());
                    if (!accountSynchronized(account, detailScreen)) {
                        log("Account not synchronized: " + account);
                        throw new RuntimeException("Account not synchronized: " + account);
                    }
                }
                log("Account verified: " + account.getName());
 /*           }
        }.start();
*/
    }

    /**
     * Verifies if the account data is as expected.
     *
     * @param account      the account to check
     * @param detailScreen the contact detail screen to validate against
     *
     * @return <code>true</code> the the account is in sync; <code>false</code> otherwise
     */
    private boolean accountSynchronized(AccountTestData account, ContactDetailScreen detailScreen) {
        if (account.isPerson()) {
            // Verify the information on the contact basics panel
            PersonContactBasicsPanel basicsPanel = detailScreen.getPersonContactBasicsPanel();
            return equalValues(nullToEmpty(account.getFirstName()), nullToEmpty(basicsPanel.getFirstName())) &&
                    equalValues(nullToEmpty(account.getLastName()), nullToEmpty(basicsPanel.getLastName())) &&
                    equalValues(nullToEmpty(account.getHomePhone()), nullToEmpty(basicsPanel.getHomePhone())) &&
                    equalValues(nullToEmpty(account.getMobilePhone()), nullToEmpty(basicsPanel.getMobilePhone())) &&
                    equalValues(nullToEmpty(account.getDateOfBirth()), nullToEmpty(basicsPanel.getDataOfBirth())) &&
                    equalValues(nullToEmpty(account.getGender()), nullToEmpty(basicsPanel.getGender())) &&
                    equalValues(nullToEmpty(account.getLicenseNumber()), nullToEmpty(basicsPanel.getLicenseNumber())) &&
                    equalValues(nullToEmpty(account.getLicenseState()), nullToEmpty(basicsPanel.getLicenseState()));
        }
        return true;
    }
}
