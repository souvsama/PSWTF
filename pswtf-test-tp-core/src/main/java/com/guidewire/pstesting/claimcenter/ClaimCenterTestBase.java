package com.guidewire.pstesting.claimcenter;

import com.guidewire.pstesting.ApplicationFactory;
import com.guidewire.pstesting.ApplicationTestBase;
import com.guidewire.pstesting.claimcenter.data.*;
import com.guidewire.pstesting.config.ApplicationConfiguration;
import com.guidewire.pstesting.data.DriverTestData;
import com.guidewire.pstesting.data.TestData;
import com.guidewire.pstesting.data.UserTestData;
import com.guidewire.pstesting.policycenter.data.AccountTestData;
import com.guidewire.pstesting.policycenter.data.PolicyCenterTestData;
import com.guidewire.pstesting.policycenter.data.PolicyTestData;
import com.guidewire.pstesting.policycenter.data.ca.BusinessAutoPolicyTestData;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.DataProvider;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

public abstract class ClaimCenterTestBase extends ApplicationTestBase {
    public static final String APP_NAME = "ClaimCenter";
    private ClaimCenterApplication application;

    protected ClaimCenterTestBase() throws JAXBException {}

    public void log(String message) {
        logger.info(message);
 //       lastLoggedMessage = message;
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

    /**
     * Returns an instance of the <code>ClaimCenterApplication</code>.
     *
     * @return an instance of the <code>ClaimCenterApplication</code>
     */
    public ClaimCenterApplication getApplication() {
        if (application == null){
            application = (ClaimCenterApplication)getApplication(APP_NAME);
        }
        return application;
    }

    @AfterMethod(alwaysRun = true)
    public ClaimCenterApplication logout() {
        log("Logging out");
        try {
            getApplication().logout();
        } catch (Exception e) {
            Assert.fail("Failed to log out: " + e.getMessage(), e);
        }
        log("************************** Successfully logged out **************************");
        return getApplication();
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

    @DataProvider(name = "Personal-Auto-Claim")
    public Iterator<Object[]> personalAutoClaimDataProvider() {
        List<Object[]> data = new ArrayList<>();
        for (TestData testData : getTestData()) {
            // Process claim center test data
            ClaimCenterTestData claimCenterTestData = testData.getClaimCenter();
            if (claimCenterTestData != null && claimCenterTestData.personalAutoClaimsExist()) {
                // Get default user to use if not specified on the policy
                UserTestData user = testData.getUser();
                // Create test data iterator
                for (PersonalAutoClaimTestData claimData : claimCenterTestData.getPersonalAutoClaims()) {
                    // If no user is specified at the policy level, add what's set on the test data
                    if (claimData.getUser() == null) {
                        claimData.setUser(user);
                    }
                    // Need to set loss date to today's date?
                    String lossDate = claimData.getLossDate();
                    if (lossDate == null || lossDate.contains(TODAYS_DATE_VAR)) {
                        lossDate = createDateFormatter(lossDate).format(Calendar.getInstance().getTime());
                        claimData.setLossDate(lossDate);
                    }
                    // If required, set the policy number to that of the newly created policy
                    // associated with the identifier set on the policy test data.

                    PolicyTestData policyData = getPolicy(claimData.getPolicyId());
                    if (claimData.getPolicyId() != null && claimData.getPolicyNumber() == null) {
                        claimData.setPolicyNumber(policyData.getPolicyNumber());
                    }
                    // Update name on claim if needed
                    if (policyData != null) {
                        updateReportedByOnClaim(claimData.getInformation(), policyData);
                    }

                    data.add(new Object[]{claimData});
                }
            }
        }
        return data.iterator();
    }

    @DataProvider(name = "Business-Auto-Claim")
    public Iterator<Object[]> businessAutoClaimDataProvider() {
        List<Object[]> data = new ArrayList<>();
        for (TestData testData : getTestData()) {
            // Process claim center test data
            ClaimCenterTestData claimCenterTestData = testData.getClaimCenter();
            if (claimCenterTestData != null && claimCenterTestData.businessAutoClaimsExist()) {
                // Get default user to use if not specified on the policy
                UserTestData user = testData.getUser();
                // Create test data iterator
                for (BusinessAutoClaimTestData claimData : claimCenterTestData.getBusinessAutoClaims()) {
                    // If no user is specified at the policy level, add what's set on the test data
                    if (claimData.getUser() == null) {
                        claimData.setUser(user);
                    }
                    // Need to set loss date to today's date?
                    String lossDate = claimData.getLossDate();
                    if (lossDate == null || lossDate.contains(TODAYS_DATE_VAR)) {
                        lossDate = createDateFormatter(lossDate).format(Calendar.getInstance().getTime());
                        claimData.setLossDate(lossDate);
                    }
                    // If required, set the policy number to that of the newly created policy
                    // associated with the identifier set on the policy test data.

                    BusinessAutoPolicyTestData policyData = (BusinessAutoPolicyTestData)getPolicy(claimData.getPolicyId());
                    if (claimData.getPolicyId() != null && claimData.getPolicyNumber() == null) {
                        claimData.setPolicyNumber(policyData.getPolicyNumber());
                    }
                    // Update name on claim if needed
                    if (policyData != null) {
                        updateReportedByOnClaim(claimData.getInformation(), policyData);
                    }

                    data.add(new Object[]{claimData});
                }
            }
        }
        return data.iterator();
    }

    @DataProvider(name = "Homeowners-Claim")
    public Iterator<Object[]> homeownersClaimDataProvider() {
        List<Object[]> data = new ArrayList<>();
        for (TestData testData : getTestData()) {
            // Process claim center test data
            ClaimCenterTestData claimCenterTestData = testData.getClaimCenter();
            if (claimCenterTestData != null && claimCenterTestData.homeownersClaimsExist()) {
                // Get default user to use if not specified on the policy
                UserTestData user = testData.getUser();
                // Create test data iterator
                for (HomeownersClaimTestData claimData : claimCenterTestData.getHomeownersClaims()) {
                    // If no user is specified at the policy level, add what's set on the test data
                    if (claimData.getUser() == null) {
                        claimData.setUser(user);
                    }
                    // Need to set loss date to today's date?
                    String lossDate = claimData.getLossDate();
                    if (lossDate == null || lossDate.contains(TODAYS_DATE_VAR)) {
                        lossDate = createDateFormatter(lossDate).format(Calendar.getInstance().getTime());
                        claimData.setLossDate(lossDate);
                    }
                    // If required, set the policy number to that of the newly created policy
                    // associated with the identifier set on the policy test data.

                    PolicyTestData policyData = getPolicy(claimData.getPolicyId());
                    if (claimData.getPolicyId() != null && claimData.getPolicyNumber() == null) {
                        claimData.setPolicyNumber(policyData.getPolicyNumber());
                    }
                    // Update name on claim if needed
                    if (policyData != null) {
                        updateReportedByOnClaim(claimData.getInformation(), policyData);
                    }

                    data.add(new Object[]{claimData});
                }
            }
        }
        return data.iterator();
    }

    private void updateReportedByOnClaim(InformationTestData informationData, PolicyTestData policyData) {
        if (informationData != null) {
            String id = informationData.getReportedById();
            if (id != null) {
                // Commercial auto policy? If so, reported by identifier is a driver on the policy.
                if (policyData instanceof BusinessAutoPolicyTestData) {
                    DriverTestData driver = ((BusinessAutoPolicyTestData)policyData).getDriver(id);
                    // Find the driver?
                    if (driver != null) {
                        informationData.setReportedBy(driver.getFirstName() + " " + driver.getLastName());
                    } else {
                        throw new RuntimeException("Unable to locate reported by driver: " + id);
                    }
                }
            } else {
                String name = informationData.getReportedBy();
                if (name != null && name.contains(ACCOUNT_HOLDER_VAR)) {
                    AccountTestData account = getAccountById(policyData.getAccountId());
                    if (account != null) {
                        if (account.getType().equals("person")) {
                            informationData.setReportedBy(account.getFirstName() + " " + account.getLastName());
                        } else {
                            throw new RuntimeException("Unsupported reported by account holder type: " + account.getType());
                        }
                    } else {
                        throw new RuntimeException("Unable to locate reported by account: " + policyData.getAccountId());
                    }
                }
            }
        }
    }

        /*===============================================================================================*/
    /*========================================== Factories ==========================================*/
    /*===============================================================================================*/

    public static class ClaimCenterApplicationFactory implements ApplicationFactory<ClaimCenterApplication> {
        private final ApplicationTestBase testBase;

        public ClaimCenterApplicationFactory(ApplicationTestBase testBase) {
            this.testBase = testBase;
        }

        public ClaimCenterApplication create() {
            ApplicationConfiguration configuration = testBase.getApplicationConfiguration(APP_NAME);
            return new ClaimCenterApplication(testBase.getController(),
                    testBase.getHost(),
                    configuration.getPort(),
                    configuration.getFolderName());
        }
    }

}
