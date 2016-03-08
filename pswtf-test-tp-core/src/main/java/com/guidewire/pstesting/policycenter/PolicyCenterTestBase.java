package com.guidewire.pstesting.policycenter;

import com.guidewire.pstesting.*;
import com.guidewire.pstesting.config.ApplicationConfiguration;
import com.guidewire.pstesting.data.DriverTestData;
import com.guidewire.pstesting.data.TestData;
import com.guidewire.pstesting.data.UserTestData;
import com.guidewire.pstesting.policycenter.account.AccountPage;
import com.guidewire.pstesting.policycenter.data.AccountTestData;
import com.guidewire.pstesting.policycenter.data.AutoPolicyTestData;
import com.guidewire.pstesting.policycenter.data.PolicyCenterTestData;
import com.guidewire.pstesting.policycenter.data.PolicyTestData;
import com.guidewire.pstesting.policycenter.data.pa.VehicleTestData;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The base class for all PolicyCenter related tests.
 */
public abstract class PolicyCenterTestBase extends ApplicationTestBase {
    public static final String APP_NAME = "PolicyCenter";
    static WebDriver driver;
    static ScreenObjectController controller;

    private PolicyCenterApplication application;

    protected PolicyCenterTestBase() throws JAXBException {
    }

    /**
     * Returns an instance of the <code>PolicyCenterApplication</code>.
     *
     * @return an instance of the <code>PolicyCenterApplication</code>
     */
    public PolicyCenterApplication getApplication() {
        if (application == null) {
            application = (PolicyCenterApplication)getApplication(APP_NAME);
        }
        return application;
    }

    public boolean login(UserTestData userData) {
        log("Logging in: " + userData);
        ApplicationPage resultingPage = getApplication().login(userData);
        boolean loginSucceeded = !SuiteLoginPage.class.isInstance(resultingPage);
        log("Login " + (loginSucceeded ? "succeeded" : "failed"));
        return loginSucceeded;
    }

    @AfterMethod(alwaysRun = true)
    public PolicyCenterApplication logout() {
        log("Logging out");
        try {
            getApplication().logout();
        } catch (Exception e) {
            Assert.fail("Failed to log out: " + e.getMessage(), e);
        }
        log("************************** Successfully logged out **************************");
        return getApplication();
    }

    public NewSubmissionPage newSubmission(AccountPage accountPage, PolicyTestData policyData) {
        NewSubmissionPage submissionPage = accountPage.newSubmission();
        submissionPage.setOrganization(policyData.getOrganization());
        submissionPage.setProducerCode(policyData.getProducerCode());
        submissionPage.setDefaultBaseState(policyData.getDefaultBaseState());
        return submissionPage;
    }

    /*===============================================================================================*/
    /*====================================== Data Providers =========================================*/
    /*===============================================================================================*/

    @DataProvider(name = "Person-Account")
    public Iterator<Object[]> personAccountDataProvider() {
        List<Object[]> data = new ArrayList<>();
        for (TestData testData : getTestData()) {
            PolicyCenterTestData policyCenterTestData = testData.getPolicyCenter();
            if (policyCenterTestData != null) {
                String timestamp = Long.toString(System.currentTimeMillis());
                // Determine default user to use if not specified on the account
                UserTestData defaultUser = policyCenterTestData.getUser();
                if (defaultUser == null) {
                    defaultUser = testData.getUser();
                }
                // Create test data iterator
                for (AccountTestData account : policyCenterTestData.getAccounts()) {
                    if (account.isPerson()) {
                        // If no user is specified at the account level, add what's set on the test data
                        if (account.getUser() == null) {
                            account.setUser(defaultUser);
                        }
                        account.setFirstName(account.getFirstName().replace(TIME_STAMP_VAR, timestamp));
                        account.setLastName(account.getLastName().replace(TIME_STAMP_VAR, timestamp));
                        data.add(new Object[]{account});
                    }
                }
            }
        }
        return data.iterator();
    }

    @DataProvider(name = "Company-Account")
    public Iterator<Object[]> companyAccountDataProvider() {
        List<Object[]> data = new ArrayList<>();
        for (TestData testData : getTestData()) {
            PolicyCenterTestData policyCenterTestData = testData.getPolicyCenter();
            if (policyCenterTestData != null) {
                String timestamp = Long.toString(System.currentTimeMillis());
                // Determine default user to use if not specified on the account
                UserTestData defaultUser = policyCenterTestData.getUser();
                if (defaultUser == null) {
                    defaultUser = testData.getUser();
                }
                // Create test data iterator
                for (AccountTestData account : policyCenterTestData.getAccounts()) {
                    if (account.isCompany()) {
                        // If no user is specified at the account level, add what's set on the test data
                        if (account.getUser() == null) {
                            account.setUser(defaultUser);
                        }
                        account.setCompanyName(account.getCompanyName().replace(TIME_STAMP_VAR, timestamp));
                        data.add(new Object[]{account});
                    }
                }
            }
        }
        return data.iterator();
    }

    @DataProvider(name = "Personal-Auto")
    public Iterator<Object[]> personalAutoDataProvider() {
        List<Object[]> data = new ArrayList<>();
        for (TestData testData : getTestData()) {
            PolicyCenterTestData policyCenterTestData = testData.getPolicyCenter();
            if (policyCenterTestData != null) {
                updateAutoPolicy(policyCenterTestData.getPersonalAutoPolicies(), data, testData.getUser());
            }
        }
        return data.iterator();
    }

    @DataProvider(name = "Homeowners")
    public Iterator<Object[]> homeownersDataProvider() {
        List<Object[]> data = new ArrayList<>();
        for (TestData testData : getTestData()) {
            PolicyCenterTestData policyCenterTestData = testData.getPolicyCenter();
            if (policyCenterTestData != null) {
                updatePolicyData(testData, policyCenterTestData.getHomeownersPolicies(), data);
            }
        }
        return data.iterator();
    }

    @DataProvider(name = "Domestic-Property")
    public Iterator<Object[]> domesticPropertyDataProvider() {
        List<Object[]> data = new ArrayList<>();
        for (TestData testData : getTestData()) {
            PolicyCenterTestData policyCenterTestData = testData.getPolicyCenter();
            if (policyCenterTestData != null) {
                updatePolicyData(testData, policyCenterTestData.getDomesticPropertyPolicies(), data);
            }
        }
        return data.iterator();
    }

    @DataProvider(name = "Business-Auto")
    public Iterator<Object[]> businessAutoDataProvider() {
        List<Object[]> data = new ArrayList<>();
        for (TestData testData : getTestData()) {
            PolicyCenterTestData policyCenterTestData = testData.getPolicyCenter();
            if (policyCenterTestData != null) {
                updatePolicyData(testData, policyCenterTestData.getBusinessAutoPolicies(), data);
            }
        }
        return data.iterator();
    }

    @DataProvider(name = "Motor")
    public Iterator<Object[]> motorDataProvider() {
        List<Object[]> data = new ArrayList<>();
        for (TestData testData : getTestData()) {
            PolicyCenterTestData policyCenterTestData = testData.getPolicyCenter();
            if (policyCenterTestData != null) {
                updateAutoPolicy(policyCenterTestData.getMotorPolicies(), data, testData.getUser());
            }
        }
        return data.iterator();
    }

    @DataProvider(name = "SME-Shops")
    public Iterator<Object[]> smeShopsDataProvider() {
        List<Object[]> data = new ArrayList<>();
        for (TestData testData : getTestData()) {
            PolicyCenterTestData policyCenterTestData = testData.getPolicyCenter();
            if (policyCenterTestData != null) {
                updatePolicyData(testData, policyCenterTestData.getSMEShopsPolicies(), data);
            }
        }
        return data.iterator();
    }

    /*===============================================================================================*/
    /*====================================== Utility Methods ========================================*/
    /*===============================================================================================*/

    private List<Object[]> updatePolicyData(TestData testData,
                                            List<? extends PolicyTestData> policyTestData,
                                            List<Object[]> data) {
        if (policyTestData != null) {
            // Get default user to use if not specified on the policy
            UserTestData user = testData.getUser();
            // Create test data iterator
            for (PolicyTestData policyData : policyTestData) {
                // If no user is specified at the policy level, add what's set on the test data
                if (policyData.getUser() == null) {
                    policyData.setUser(user);
                }
                updatePolicyAccount(policyData);
                data.add(new Object[]{policyData});
            }
        }
        return data;
    }

    private void updateDriverName(DriverTestData driver, AccountTestData account) {
        // Driver is the account holder?
        if (driver.isAccountHolder()) {
            if (account != null && account.getType().equals("person")) {
                driver.setFirstName(account.getFirstName());
                driver.setLastName(account.getLastName());
            }
        }
    }

    private void updateAutoPolicy(List<AutoPolicyTestData> policies, List<Object[]> data, UserTestData defaultUser) {
        for (AutoPolicyTestData policyData : policies) {
            // If no user is specified at the policy level, add what's set on the test data
            if (policyData.getUser() == null) {
                policyData.setUser(defaultUser);
            }
            AccountTestData account = updatePolicyAccount(policyData);
            // Update drivers if needed
            for (DriverTestData driver : policyData.getDrivers()) {
                updateDriverName(driver, account);
            }
            // Update vehicle drivers if needed
            for (VehicleTestData vehicle : policyData.getVehicles()) {
                for (DriverTestData driver : vehicle.getDrivers()) {
                    updateDriverName(driver, account);
                }
            }
            data.add(new Object[]{policyData});
        }
    }

    private AccountTestData updatePolicyAccount(PolicyTestData policyData) {
        // If required, set the policy account number to that of the newly created account
        // associated with the account identifier set on the policy test data.
        AccountTestData account = getAccountById(policyData.getAccountId());
        if (account != null && policyData.getAccountId() != null && policyData.getAccountNumber() == null) {
            policyData.setAccountNumber(account.getAccountNumber());
            policyData.setOrganization(account.getOrganization());
            policyData.setProducerCode(account.getProducerCode());
        }
        return account;
    }

    /*===============================================================================================*/
    /*========================================== Factories ==========================================*/
    /*===============================================================================================*/

    public static class PolicyCenterApplicationFactory implements ApplicationFactory<PolicyCenterApplication> {
        private final ApplicationTestBase testBase;

        public PolicyCenterApplicationFactory(ApplicationTestBase testBase) {
            this.testBase = testBase;
        }

        public PolicyCenterApplication create() {
            ApplicationConfiguration configuration = testBase.getApplicationConfiguration(APP_NAME);
            return new PolicyCenterApplication(testBase.getController(),
                                               testBase.getHost(),
                                               configuration.getPort(),
                                               configuration.getFolderName());
        }
    }
}