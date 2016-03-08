package com.guidewire.pstesting;


import com.guidewire.pstesting.billingcenter.BillingCenterTestBase;
import com.guidewire.pstesting.claimcenter.ClaimCenterTestBase;
import com.guidewire.pstesting.config.ApplicationConfiguration;
import com.guidewire.pstesting.config.Applications;
import com.guidewire.pstesting.contactmanager.ContactManagerTestBase;
import com.guidewire.pstesting.data.TestData;
import com.guidewire.pstesting.data.UserTestData;
import com.guidewire.pstesting.policycenter.Account;
import com.guidewire.pstesting.policycenter.PolicyCenterTestBase;
import com.guidewire.pstesting.policycenter.data.AccountTestData;
import com.guidewire.pstesting.policycenter.data.PolicyCenterTestData;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ApplicationTestBase extends TestBase {
    private static Map<String, ApplicationConfiguration> applicationConfiguration;
    private static Map<String, ApplicationFactory> applicationFactories = new HashMap<>();
    private static List<Object> graphs;
    private static List<TestData> testData;
    public final JAXBContext context;

    protected ApplicationTestBase() throws JAXBException {
        context = JAXBContext.newInstance(TestData.class, Applications.class);
        initializeApplicationFactories();
    }

    public JAXBContext getContext(){
        return this.context;
    }

    public Unmarshaller getUnmarshaller() throws JAXBException {
        return (Unmarshaller)context.createUnmarshaller();
    }

    public Application getApplication(String name) {
        return applicationFactories.get(name).create();
    }

    public ApplicationConfiguration getApplicationConfiguration(String name) {
        // Need to load application configuration?
        if (applicationConfiguration == null) {
            applicationConfiguration = new HashMap<>();
            try {
                // Unmarshall files
                Unmarshaller unmarshaller = getUnmarshaller();
                InputStream in = getClass().getClassLoader().getResourceAsStream(APPLICATION_CONFIGURATION_FILE);
                Applications applications = (Applications)unmarshaller.unmarshal(new StreamSource(in));
                for (ApplicationConfiguration configuration : applications.getConfigurations()) {
                    applicationConfiguration.put(configuration.getName().toLowerCase(), configuration);
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed loading application configuration file", e);
            }
        }
        return applicationConfiguration.get(name.toLowerCase());
    }

    public List<TestData> getTestData() {
        // Need to load test data?
        if (testData == null) {
            testData = new ArrayList<>();
            for (Object graph : getTestDataObjectGraphs()) {
                if (TestData.class.isInstance(graph)) {
                    TestData data = (TestData)graph;
                    // If a user is not set, create one based on the username and password properties (if set)
                    if (data.getUser() == null) {
                        String username = getUsername();
                        String password = getPassword();
                        if (username != null || password != null) {
                            data.setUser(new UserTestData(username, password));
                        }
                    }
                    testData.add(data);
                }
            }
        }
        return testData;
    }

    /**
     * Returns a list of every account related to the test.
     *
     * @return a list of every account related to the test.
     */
    public List<AccountTestData> getAccounts() {
        List<AccountTestData> results = new ArrayList<>();
        for (TestData testData : getTestData()) {
            PolicyCenterTestData policyCenterTestData = testData.getPolicyCenter();
            if (policyCenterTestData != null) {
                results.addAll(policyCenterTestData.getAccounts());
            }
        }
        return results;
    }

    public AccountTestData getAccountById(String id) {
        for (TestData testData : getTestData()) {
            PolicyCenterTestData policyCenterTestData = testData.getPolicyCenter();
            if (policyCenterTestData != null) {
                AccountTestData accountTestData = policyCenterTestData.getAccountById(id);
                if (accountTestData != null) {
                    return accountTestData;
                }
            }
        }
        return null;
    }

    public Account getAccountByAccountNumber(String accountNumber) {
        for (TestData testData : getTestData()) {
            PolicyCenterTestData policyCenterTestData = testData.getPolicyCenter();
            if (policyCenterTestData != null) {
                AccountTestData accountTestData = policyCenterTestData.getAccountByAccountNumber(accountNumber);
                if (accountTestData != null) {
                    return accountTestData;
                }
            }
        }
        return null;
    }

    public AccountVerificationTestData createAccountVerification(AccountVerificationTestData verificationData,
                                                                 UserTestData defaultUser) {
        // If no user is specified on the verification element, add what's set on the test data element
        if (verificationData.getUser() == null) {
            verificationData.setUser(defaultUser);
        }
        // If required, set the account number to that of the newly created account
        // associated with the identifier set on the account test data.
        AccountTestData accountData = getAccountById(verificationData.getAccountId());
        if (verificationData.getAccountId() != null && verificationData.getAccountNumber() == null) {
            if (accountData == null) {
                throw new RuntimeException("Failed to locate account: " + verificationData.getAccountId());
            }
            verificationData.setAccountNumber(accountData.getAccountNumber());
        }
        verificationData.setAccount(accountData);
        return verificationData;
    }

    private List<Object> getTestDataObjectGraphs() {
        // Need to load the test data files?
        if (graphs == null) {
            graphs = new ArrayList<>();
            try {
                // Unmarshall files
                Unmarshaller unmarshaller = getUnmarshaller();
                for (String fileName : listTestDataFiles()) {
                    // Only process XML files
                    if (fileName.endsWith(".xml")) {
                        // Is the file on the class path?
                        InputStream in = getClass().getClassLoader().getResourceAsStream(fileName);
                        // If not, just try to open it
                        if (in == null) {
                            in = new FileInputStream(fileName);
                        }
                        log("Loading test data: " + fileName);
                        graphs.add(unmarshaller.unmarshal(new StreamSource(in)));
                    }
                }
            } catch (Exception e) {
                throw new RuntimeException("Failed loading test data", e);
            }
        }
        return graphs;
    }

    private List<String> listTestDataFiles() {
        return listTestDataFiles(getProperty(TEST_DATA_FILE_SYS_PROPERTY), new ArrayList<String>());
    }

    @SuppressWarnings("ConstantConditions")
    private List<String> listTestDataFiles(String fileName, List<String> files) {
        File file = new File(fileName);
        // Is it a directory?
        if (file.isDirectory()) {
            // Ignore folders beginning with a period
            if (!file.getName().startsWith(".")) {
                for (File f : file.listFiles()) {
                    if (f.isFile()) {
                        files.add(f.getAbsolutePath());
                    } else {
                        listTestDataFiles(f.getAbsolutePath(), files);
                    }
                }
            }
        } else {
            files.add(fileName);  // If not a directory, assume it's a file
        }
        return files;
    }

    private void initializeApplicationFactories() {
        applicationFactories.put(PolicyCenterTestBase.APP_NAME, new PolicyCenterTestBase.PolicyCenterApplicationFactory(this));
        applicationFactories.put(ClaimCenterTestBase.APP_NAME, new ClaimCenterTestBase.ClaimCenterApplicationFactory(this));
        applicationFactories.put(BillingCenterTestBase.APP_NAME, new BillingCenterTestBase.BillingCenterApplicationFactory(this));
        applicationFactories.put(ContactManagerTestBase.APP_NAME, new ContactManagerTestBase.ContactManagerApplicationFactory(this));
    }

}
