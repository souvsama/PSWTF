package com.guidewire.pstesting.data;


import com.guidewire.pstesting.billingcenter.data.BillingCenterTestData;
import com.guidewire.pstesting.claimcenter.data.ClaimCenterTestData;
import com.guidewire.pstesting.contactmanager.data.ContactManagerTestData;
import com.guidewire.pstesting.policycenter.data.PolicyCenterTestData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 * This class represents the root element of all test data configured in a test data XML file.
 */
@XmlRootElement(name = "test-data")
@XmlAccessorType(XmlAccessType.FIELD)
public class TestData {
    @XmlElement(name = "user") private            UserTestData           user;
    @XmlElement(name = "suite") private           List<SuiteTestData>    suites;
    @XmlElement(name = "claim-center") private ClaimCenterTestData claimCenter;

    @XmlElement(name = "policy-center") private PolicyCenterTestData policyCenter;

    @XmlElement(name = "billing-center") private  BillingCenterTestData  billingCenter;
    @XmlElement(name = "contact-manager") private ContactManagerTestData contactManager;


    public UserTestData getUser() {
        return user;
    }

    public void setUser(UserTestData user) {
        this.user = user;
    }

    public boolean suitesExist() {
        return (suites != null && suites.size() > 0);
    }

    public List<SuiteTestData> getSuites() {
        return suites;
    }

    public void setSuites(List<SuiteTestData> suites) {
        this.suites = suites;
    }

    public ClaimCenterTestData getClaimCenter() {
        return claimCenter;
    }

    public void setClaimCenter(ClaimCenterTestData claimCenter) {
        this.claimCenter = claimCenter;
    }

    public PolicyCenterTestData getPolicyCenter() {
        return policyCenter;
    }

    public void setPolicyCenter(PolicyCenterTestData policyCenter) {
        this.policyCenter = policyCenter;
    }

    public BillingCenterTestData getBillingCenter() {
        return billingCenter;
    }

    public void setBillingCenter(BillingCenterTestData billingCenter) {
        this.billingCenter = billingCenter;
    }

    public ContactManagerTestData getContactManager() {
        return contactManager;
    }

    public void setContactManager(ContactManagerTestData contactManager) {
        this.contactManager = contactManager;
    }

}