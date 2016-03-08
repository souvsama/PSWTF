package com.guidewire.pstesting.contactmanager;

import com.guidewire.pstesting.*;
import com.guidewire.pstesting.contactmanager.data.ContactManagerTestData;
import com.guidewire.pstesting.config.ApplicationConfiguration;
import com.guidewire.pstesting.data.TestData;
import com.guidewire.pstesting.data.UserTestData;
import com.guidewire.pstesting.policycenter.data.AccountTestData;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class ContactManagerTestBase extends ApplicationTestBase {
    public static final String APP_NAME = "ContactManager";

    protected ContactManagerTestBase() throws JAXBException {
    }

    /**
     * Returns an instance of the <code>ContactManagerApplication</code>.
     *
     * @return an instance of the <code>ContactManagerApplication</code>
     */
    public ContactManagerApplication getApplication() {
        return (ContactManagerApplication)getApplication(APP_NAME);
    }

    @DataProvider(name = "CM-Account-Verification")
    public Iterator<Object[]> accountVerificationDataProvider() {
        List<Object[]> data = new ArrayList<>();
        for (TestData testData : getTestData()) {
            // Process contact manager test data
            ContactManagerTestData contactManagerTestData = testData.getContactManager();
            if (contactManagerTestData != null && contactManagerTestData.accountVerificationsExist()) {
                // Get default user to use if not specified on the policy
                UserTestData user = testData.getUser();
                // Create test data iterator
                for (AccountVerificationTestData verificationData : contactManagerTestData.getAccountVerifications()) {
                    // Verifying all accounts?
                    if (verificationData.getVerifyAll()) {
                        for (AccountTestData account : getAccounts()) {
                            AccountVerificationTestData accountVerification
                                    = new AccountVerificationTestData(account.getId(),
                                                                      account.getAccountNumber());
                            accountVerification.setCheckDetails(verificationData.getCheckDetails());
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
        ApplicationPage resultingPage = getApplication().login(userData.getUsername(), userData.getPassword());
        boolean loginSucceeded = !SuiteLoginPage.class.isInstance(resultingPage);
        log("Login " + (loginSucceeded ? "succeeded" : "failed"));
        return loginSucceeded;
    }

    @AfterMethod(alwaysRun = true)
    public ContactManagerApplication logout() {
        log("Logging out");
        try {
            getApplication().logout();
        } catch (Exception e) {
            Assert.fail("Failed to log out: " + e.getMessage(), e);
        }
        log("************************** Successfully logged out **************************");
        return getApplication();
    }

    /*===============================================================================================*/
    /*========================================== Factories ==========================================*/
    /*===============================================================================================*/

    public static class ContactManagerApplicationFactory implements ApplicationFactory<ContactManagerApplication> {
        private final ApplicationTestBase testBase;

        public ContactManagerApplicationFactory(ApplicationTestBase testBase) {
            this.testBase = testBase;
        }

        public ContactManagerApplication create() {
            ApplicationConfiguration configuration = testBase.getApplicationConfiguration(APP_NAME);
            return new ContactManagerApplication(testBase.getController(),
                                                 testBase.getHost(),
                                                 configuration.getPort(),
                                                 configuration.getFolderName());
        }
    }
}
