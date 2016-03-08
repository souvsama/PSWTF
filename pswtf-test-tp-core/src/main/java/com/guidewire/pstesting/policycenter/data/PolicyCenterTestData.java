package com.guidewire.pstesting.policycenter.data;

import com.guidewire.pstesting.data.UserTestData;
import com.guidewire.pstesting.policycenter.data.ca.BusinessAutoPolicyTestData;
import com.guidewire.pstesting.policycenter.data.domeu.DOMEUPolicyTestData;
import com.guidewire.pstesting.policycenter.data.ho.HomeownersPolicyTestData;
import com.guidewire.pstesting.policycenter.data.smeshops.SMEEUPolicyTestData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class PolicyCenterTestData {
    @XmlElement(name = "user") private UserTestData user;

    @XmlElement(name = "account") @XmlElementWrapper(name = "accounts") private List<AccountTestData> accounts;

    @XmlElement(name = "motor") private             List<AutoPolicyTestData>         motorPolicies;
    @XmlElement(name = "personal-auto") private     List<AutoPolicyTestData>         personalAutoPolicies;
    @XmlElement(name = "homeowners") private        List<HomeownersPolicyTestData>   homeownersPolicies;
    @XmlElement(name = "business-auto") private     List<BusinessAutoPolicyTestData> businessAutoPolicies;
    @XmlElement(name = "domestic-property") private List<DOMEUPolicyTestData>        domPolicies;
    @XmlElement(name = "sme-shops") private         List<SMEEUPolicyTestData>        smeShopsPolicies;

    public UserTestData getUser() {
        return user;
    }

    public void setUser(UserTestData user) {
        this.user = user;
    }

    public List<AccountTestData> getAccounts() {
        return (accounts == null ? Collections.<AccountTestData>emptyList() : accounts);
    }

    public AccountTestData getAccountById(String id) {
        // TODO: Brut force approach to locating account. Should improve this (SLC)
        if (accounts != null) {
            for (AccountTestData account : accounts) {
                if (id.equals(account.getId())) {
                    return account;
                }
            }
        }
        return null;
    }

    public AccountTestData getAccountByAccountNumber(String accountNumber) {
        // TODO: Brut force approach to locating account. Should improve this (SLC)
        if (accounts != null) {
            for (AccountTestData account : accounts) {
                if (accountNumber.equals(account.getAccountNumber())) {
                    return account;
                }
            }
        }
        return null;
    }

    public void setSetAccounts(List<AccountTestData> accounts) {
        this.accounts = accounts;
    }

    /**
     * Finds the policy with the specified identifier.
     *
     * @param id the identifier of the policy to retrieve
     *
     * @return the policy associated with the identifier
     */
    public PolicyTestData getPolicy(String id) {
        return findPolicy(id,
                          personalAutoPolicies, businessAutoPolicies, homeownersPolicies,
                          motorPolicies, domPolicies, smeShopsPolicies);
    }

    /**
     * Returns a list of every policy created during the test run.
     *
     * @return a <code>List</code> of <code>PolicyTestData</code> objects representing
     *         every policy created during the current test run.
     */
    public List<PolicyTestData> getPolicies() {
        List<PolicyTestData> polices = new ArrayList<>();
        polices.addAll(getHomeownersPolicies());
        polices.addAll(getPersonalAutoPolicies());
        polices.addAll(getBusinessAutoPolicies());
        polices.addAll(getMotorPolicies());
        polices.addAll(getDomesticPropertyPolicies());
        polices.addAll(getSMEShopsPolicies());
        return polices;
    }

    public List<HomeownersPolicyTestData> getHomeownersPolicies() {
        return (homeownersPolicies == null ? Collections.<HomeownersPolicyTestData>emptyList() : homeownersPolicies);
    }

    public void setHomeownersPolicies(List<HomeownersPolicyTestData> homeownersPolicies) {
        this.homeownersPolicies = homeownersPolicies;
    }

    public List<AutoPolicyTestData> getPersonalAutoPolicies() {
        return (personalAutoPolicies == null ? Collections.<AutoPolicyTestData>emptyList() : personalAutoPolicies);
    }

    public void setPersonalAutoPolicies(List<AutoPolicyTestData> personalAutoPolicies) {
        this.personalAutoPolicies = personalAutoPolicies;
    }

    public List<BusinessAutoPolicyTestData> getBusinessAutoPolicies() {
        return (businessAutoPolicies == null ? Collections.<BusinessAutoPolicyTestData>emptyList() : businessAutoPolicies);
    }

    public void setBusinessAutoPolicies(List<BusinessAutoPolicyTestData> businessAutoPolicies) {
        this.businessAutoPolicies = businessAutoPolicies;
    }

    public List<AutoPolicyTestData> getMotorPolicies() {
        return (motorPolicies == null ? Collections.<AutoPolicyTestData>emptyList() : motorPolicies);
    }

    public void setMotorPolicies(List<AutoPolicyTestData> motorPolicies) {
        this.motorPolicies = motorPolicies;
    }

    public List<DOMEUPolicyTestData> getDomesticPropertyPolicies() {
        return (domPolicies == null ? Collections.<DOMEUPolicyTestData>emptyList() : domPolicies);
    }

    public void setDomesticPropertyPolicies(List<DOMEUPolicyTestData> domPolicies) {
        this.domPolicies = domPolicies;
    }

    public void setSMEShopsPolicies(List<SMEEUPolicyTestData> smeShopsPolicies) {
        this.smeShopsPolicies = smeShopsPolicies;
    }

    public List<SMEEUPolicyTestData> getSMEShopsPolicies() {
        return (smeShopsPolicies == null ? Collections.<SMEEUPolicyTestData>emptyList() : smeShopsPolicies);
    }

    @SafeVarargs
    private final PolicyTestData findPolicy(String id, List<? extends PolicyTestData>... policyLists) {
        // TODO: Brut force approach to locating a policy. Should improve this (SLC)
        if (policyLists != null && policyLists.length > 0) {
            for (List<? extends PolicyTestData> policies : policyLists) {
                if (policies != null) {
                    for (PolicyTestData policyData : policies) {
                        if (id.equals(policyData.getId())) {
                            return policyData;
                        }
                    }
                }
            }
        }
        return null;
    }
}
