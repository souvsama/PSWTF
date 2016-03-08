package com.guidewire.pstesting.policycenter.testcase;

import com.guidewire.pstesting.policycenter.PolicyCenterTestBase;
import com.guidewire.pstesting.policycenter.account.AccountFileSummaryScreen;
import com.guidewire.pstesting.policycenter.account.AccountInformationPage;
import com.guidewire.pstesting.policycenter.account.CreateAccountScreen;
import com.guidewire.pstesting.policycenter.data.AccountTestData;
import org.testng.Assert;
import org.testng.annotations.Test;

import javax.xml.bind.JAXBException;

public class AccountTest extends PolicyCenterTestBase {
    protected AccountTest() throws JAXBException {
    }

    @Test(description = "Test new person account creation",
          groups = {"smoke", "pc-smoke", "account", "person-account"},
          dataProvider = "Person-Account",
          enabled = true)
    public void testNewPersonAccount(AccountTestData accountData) {
        log("Test new person account creation");
        // Is an account number already specified? If so, no need to create it.
        if (accountData.getAccountNumber() == null) {
            login(accountData); // Log in to PolicyCenter

            log("Initiating new account creation");
            AccountInformationPage accountInformationPage
                    = getApplication().initiateNewPersonAccountCreation(accountData.getFirstName(),
                                                                        accountData.getLastName());

            log("Creating new account: " + accountData);
            CreateAccountScreen createAccountScreen = accountInformationPage.clickCreateNewPersonAccount();
            AccountFileSummaryScreen accountFileSummaryScreen = createAccount(createAccountScreen, accountData);

            log("Account successfully created: accountNumber=" + accountFileSummaryScreen.getAccountNumber() +
                        ", name=" + accountData.getName());
        }
    }

    @Test(description = "Test new company account creation",
          groups = {"smoke", "pc-smoke", "account", "company-account"},
          dataProvider = "Company-Account",
          enabled = true)
    public void testNewCompanyAccount(AccountTestData accountData) {
        log("Test new company account creation");
        // Is an account number already specified? If so, no need to create it.
        if (accountData.getAccountNumber() == null) {
            login(accountData); // Login to PolicyCenter

            log("Initiating new account creation");
            AccountInformationPage accountInformationPage
                    = getApplication().initiateNewCompanyAccountCreation(accountData.getCompanyName());

            // Initiate account creation
            log("Creating new account: " + accountData);
            CreateAccountScreen createAccountScreen = accountInformationPage.clickCreateNewCompanyAccount();
            AccountFileSummaryScreen accountFileSummaryScreen = createAccount(createAccountScreen, accountData);

            log("Account successfully created: accountNumber=" + accountFileSummaryScreen.getAccountNumber() +
                        ", name=" + accountData.getName());
        }
    }

    private void login(AccountTestData accountData) {
        // Login to PolicyCenter
        log("Application URL: " + getApplication().getUrl());
        Assert.assertTrue(login(accountData.getUser()), "Login failed");
    }

    private AccountFileSummaryScreen createAccount(CreateAccountScreen screen, AccountTestData accountData) {
        screen.setHomePhone(accountData.getHomePhone());
        screen.setCountry(accountData.getCountry());
        screen.setAddressType(accountData.getAddressType());
        screen.setAddress1(accountData.getAddress1());
        screen.setCity(accountData.getCity());
        screen.setState(accountData.getState());
        screen.setPostalCode(accountData.getPostalCode());
        screen.setOrganization(accountData.getOrganization());
        screen.setOrgType(accountData.getOrganizationType());
        screen.setProducerCode(accountData.getProducerCode());

        AccountFileSummaryScreen accountFileSummaryScreen = screen.clickUpdate();
        accountData.setAccountNumber(accountFileSummaryScreen.getAccountNumber());

        return accountFileSummaryScreen;
    }
}