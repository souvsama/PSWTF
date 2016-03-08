package com.guidewire.pstesting.billingcenter.data;

import com.guidewire.pstesting.data.UserTestData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class PolicyVerificationTestData {
    @XmlAttribute(name = "policy-id") private     String  policyId;
    @XmlAttribute(name = "policy-number") private String  policyNumber;
    @XmlAttribute(name = "all") private           Boolean verifyAll;

    @XmlElement(name = "user") private  UserTestData  user;

    public PolicyVerificationTestData() {
    }

    public PolicyVerificationTestData(String policyId, String policyNumber) {
        this.policyId = policyId;
        this.policyNumber = policyNumber;
    }

    public String getPolicyId() {
        return policyId;
    }

    public void setPolicyId(String policyId) {
        this.policyId = policyId;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public Boolean getVerifyAll() {
        return verifyAll;
    }

    public void setVerifyAll(Boolean verifyAll) {
        this.verifyAll = verifyAll;
    }

    public UserTestData getUser() {
        return user;
    }

    public void setUser(UserTestData user) {
        this.user = user;
    }


    @Override
    public String toString() {
        return "PolicyVerificationTestData{" +
                "policyId='" + policyId + '\'' +
                ", policyNumber='" + policyNumber + '\'' +
                ", user=" + user +
                '}';
    }
}
