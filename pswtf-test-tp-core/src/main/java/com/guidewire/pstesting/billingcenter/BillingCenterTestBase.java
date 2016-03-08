package com.guidewire.pstesting.billingcenter;

import com.guidewire.pstesting.AccountVerificationTestData;
import com.guidewire.pstesting.ApplicationFactory;
import com.guidewire.pstesting.ApplicationTestBase;
import com.guidewire.pstesting.billingcenter.data.BillingCenterTestData;
import com.guidewire.pstesting.billingcenter.data.PolicyVerificationTestData;
import com.guidewire.pstesting.config.ApplicationConfiguration;
import com.guidewire.pstesting.data.TestData;
import com.guidewire.pstesting.data.UserTestData;
import com.guidewire.pstesting.policycenter.data.AccountTestData;
import com.guidewire.pstesting.policycenter.data.PolicyCenterTestData;
import com.guidewire.pstesting.policycenter.data.PolicyTestData;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class BillingCenterTestBase extends ApplicationTestBase {
    public static final String APP_NAME = "BillingCenter";

    protected BillingCenterTestBase() throws JAXBException {
    }

    /**
     * Returns an instance of the <code>BillingCenterApplication</code>.
     *
     * @return an instance of the <code>BillingCenterApplication</code>
     */
    public BillingCenterApplication getApplication() {
        return (BillingCenterApplication)getApplication(APP_NAME);
    }

    /**
     * Returns a list of every policy related to the test.
     *
     * @return a list of every policy related to the test.
     */
    public List<PolicyTestData> getPolicies() {
        List<PolicyTestData> results = new ArrayList<>();
        for (TestData testData : getTestData()) {
            PolicyCenterTestData policyCenterTestData = testData.getPolicyCenter();
            if (policyCenterTestData != null) {
                results.addAll(policyCenterTestData.getPolicies());
            }
        }
        return results;
    }

    public PolicyTestData getPolicy(String id) {
        for (TestData testData : getTestData()) {
            PolicyCenterTestData policyCenterTestData = testData.getPolicyCenter();
            if (policyCenterTestData != null) {
                PolicyTestData policyTestData = policyCenterTestData.getPolicy(id);
                if (policyTestData != null) {
                    return policyTestData;
                }
            }
        }
        return null;
    }

    @DataProvider(name = "Policy-Verification")
    public Iterator<Object[]> policyVerificationDataProvider() {
        List<Object[]> data = new ArrayList<>();
        for (TestData testData : getTestData()) {
            // Process billing center test data
            BillingCenterTestData billingCenterTestData = testData.getBillingCenter();
            if (billingCenterTestData != null) {
                // Get default user to use if not specified on the policy
                UserTestData user = testData.getUser();
                // Create test data iterator
                for (PolicyVerificationTestData verificationData : billingCenterTestData.getPolicyVerifications()) {
                    // Verifying all policies?
                    if (verificationData.getVerifyAll()) {
                        for (PolicyTestData policy : getPolicies()) {
                            PolicyVerificationTestData policyVerification
                                    = new PolicyVerificationTestData(policy.getId(), policy.getPolicyNumber());
                            data.add(new Object[]{createPolicyVerification(policyVerification, user)});
                        }
                    } else {
                        data.add(new Object[]{createPolicyVerification(verificationData, user)});
                    }
                }
            }
        }
        return data.iterator();
    }

    @DataProvider(name = "BC-Account-Verification")
    public Iterator<Object[]> accountVerificationDataProvider() {
        List<Object[]> data = new ArrayList<>();
        for (TestData testData : getTestData()) {
            // Process billing center test data
            BillingCenterTestData billingCenterTestData = testData.getBillingCenter();
            if (billingCenterTestData != null) {
                // Get default user to use if not specified on the policy
                UserTestData user = testData.getUser();
                // Create test data iterator
                for (AccountVerificationTestData verificationData : billingCenterTestData.getAccountVerifications()) {
                    // Verifying all accounts?
                    if (verificationData.getVerifyAll()) {
                        for (AccountTestData account : getAccounts()) {
                            AccountVerificationTestData accountVerification
                                    = new AccountVerificationTestData(account.getId(), account.getAccountNumber());
                            data.add(new Object[]{createAccountVerification(accountVerification, user)});
                        }
                    } else {
                        data.add(new Object[]{createAccountVerification(verificationData, user)});
                    }
                }
            }
        }
        return data.iterator();
    }

    public boolean login(UserTestData userData) {
        log("Logging in: " + userData);
        if (getApplication().login(userData.getUsername(), userData.getPassword()) != null) {
            log("Successfully logged in");
            return true;
        } else {
            log("Login failed");
            return false;
        }
    }

    @AfterMethod(alwaysRun = true)
    public BillingCenterApplication logout() {
        log("Logging out");
        try {
            getApplication().logout();
        } catch (Exception e) {
            Assert.fail("Failed to log out: " + e.getMessage(), e);
        }
        log("************************** Successfully logged out **************************");
        return getApplication();
    }

    public PolicyVerificationTestData createPolicyVerification(PolicyVerificationTestData verificationData,
                                                               UserTestData defaultUser) {
        // If no user is specified on the verification element, add what's set on the test data element
        if (verificationData.getUser() == null) {
            verificationData.setUser(defaultUser);
        }
        // If required, set the policy number to that of the newly created policy
        // associated with the identifier set on the policy test data.
        PolicyTestData policyData = getPolicy(verificationData.getPolicyId());
        if (verificationData.getPolicyId() != null && verificationData.getPolicyNumber() == null) {
            if (policyData == null) {
                throw new RuntimeException("Failed to locate policy: " + verificationData.getPolicyId());
            }
            verificationData.setPolicyNumber(policyData.getPolicyNumber());
        }
        return verificationData;
    }

    /*===============================================================================================*/
    /*========================================== Factories ==========================================*/
    /*===============================================================================================*/

    public static class BillingCenterApplicationFactory implements ApplicationFactory<BillingCenterApplication> {
        private final ApplicationTestBase testBase;

        public BillingCenterApplicationFactory(ApplicationTestBase testBase) {
            this.testBase = testBase;
        }

        public BillingCenterApplication create() {
            ApplicationConfiguration configuration = testBase.getApplicationConfiguration(APP_NAME);
            return new BillingCenterApplication(testBase.getController(),
                                                testBase.getHost(),
                                                configuration.getPort(),
                                                configuration.getFolderName());
        }
    }
}
