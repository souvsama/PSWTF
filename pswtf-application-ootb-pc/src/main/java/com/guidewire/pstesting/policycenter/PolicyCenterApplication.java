package com.guidewire.pstesting.policycenter;

import com.guidewire.pstesting.*;
import com.guidewire.pstesting.policycenter.account.AccountInformationPage;
import com.guidewire.pstesting.policycenter.account.AccountPage;
import com.guidewire.pstesting.policycenter.account.AccountTabMenu;
import com.guidewire.pstesting.policycenter.desktop.DesktopPage;
import com.guidewire.pstesting.policycenter.desktop.DesktopTabMenu;
import com.guidewire.pstesting.policycenter.policy.PolicyTabMenu;
import com.guidewire.pstesting.policycenter.submission.PolicySummaryScreen;

public class PolicyCenterApplication extends SuiteApplication implements ApplicationPage {
    public static final String RESOURCE_BASE_NAME = "locale.pc";

    private final PolicyCenterTabBar tabBar;

    protected PolicyCenterApplication(ScreenObjectController controller) {
        this(controller, null, null, null);
    }

    public PolicyCenterApplication(ScreenObjectController controller, String host, String port, String folderName) {
        super(controller, host, port, folderName);
        tabBar = new PolicyCenterTabBar(controller);
        controller.setResourceBaseName(RESOURCE_BASE_NAME);
    }

    public PolicyCenterTabBar getTabBar() {
        return tabBar;
    }

    public AccountInformationPage initiateNewPersonAccountCreation(String firstName, String lastName) {
        AccountTabMenu accountTabMenu = getTabBar().clickAccountTabArrow();
        AccountInformationPage accountInformationPage = accountTabMenu.clickNewAccount();
        // Searching for account
        accountInformationPage.setFirstName(firstName);
        accountInformationPage.setLastName(lastName);
        return accountInformationPage.clickSearch();
    }

    public AccountInformationPage initiateNewCompanyAccountCreation(String companyName) {
        AccountTabMenu accountTabMenu = getTabBar().clickAccountTabArrow();
        AccountInformationPage accountInformationPage = accountTabMenu.clickNewAccount();
        // Searching for account
        accountInformationPage.setCompanyName(companyName);
        return accountInformationPage.clickSearch();
    }

    /**
     * Initiates a new submission. This will locate the account, start the new submission and
     * select the specified product.
     *
     * @param product          the product to create a submission for
     * @param accountNumber    the number of the account to search for
     * @param organization     the organization
     * @param producerCode     the producer code
     * @param defaultBaseState the default state
     *
     * @return the wizard step set associated with the specified product
     */
    public WizardStepSet newSubmission(Product product, String accountNumber,
                                       String organization, String producerCode,
                                       String defaultBaseState) {
        AccountPage accountPage = searchForAccount(accountNumber);

        NewSubmissionPage submissionPage = accountPage.newSubmission();
        submissionPage.setOrganization(organization);
        submissionPage.setProducerCode(producerCode);
        submissionPage.setDefaultBaseState(defaultBaseState);

        return submissionPage.selectProduct(product);
    }

    /**
     * Searches for an account.
     *
     * @param accountNumber the number of the account to search for
     *
     * @return the resulting account page
     */
    public AccountPage searchForAccount(String accountNumber) {
        AccountTabMenu accountTabMenu = getTabBar().clickAccountTabArrow();
        return accountTabMenu.search(accountNumber);
    }

    /**
     * Searches for a policy
     * @param policyNumber  the policy number to search for
     * @return the resulting Policy Summary page
     */
    public PolicySummaryScreen searchForPolicy(String policyNumber){
        PolicyTabMenu policyTabMenu = getTabBar().clickPolicyTabArrow();
        return policyTabMenu.search(policyNumber);
    }

    /**
     * Returns the currently selected page.
     *
     * @return the currently selected <code>ApplicationPage</code>. If unknown,
     *         <code>null</code> is returned.
     */
    public ApplicationPage getSelectedPage() {
        // Determine current page based on which menu tab is selected
        TabMenu tabMenu = tabBar.getSelectedTab();
        if (DesktopTabMenu.class.isInstance(tabMenu)) {
            return new DesktopPage(getController());
        } else if (AccountTabMenu.class.isInstance(tabMenu)) {
            return new AccountPage(getController());
        }
        return null;
    }

    /**
     * Updates the account data with the equivalent data specified on the driver.
     *
     * @param accountData the account data to update
     * @param driver      the driver to base the updates on
     */
    public void updateAccount(Account accountData, Driver driver) {
        // Home phone
        if (driver.getHomePhone() != null) {
            accountData.setHomePhone(driver.getHomePhone());
        }
        // Mobile phone
        if (driver.getMobilePhone() != null) {
            accountData.setMobilePhone(driver.getMobilePhone());
        }
        // Birth date
        if (driver.getDateOfBirth() != null) {
            accountData.setDataOfBirth(driver.getDateOfBirth());
        }
        // Gender
        if (driver.getGender() != null) {
            accountData.setGender(replaceResourceVariables(driver.getGender()));
        }
        // Drivers license number
        if (driver.getLicenseNumber() != null) {
            accountData.setLicenseNumber(driver.getLicenseNumber());
        }
        // Drivers license state
        if (driver.getLicenseState() != null) {
            accountData.setLicenseState(replaceResourceVariables(driver.getLicenseState()));
        }
    }
}