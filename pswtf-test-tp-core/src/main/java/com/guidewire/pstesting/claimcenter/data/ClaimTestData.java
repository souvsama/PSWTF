package com.guidewire.pstesting.claimcenter.data;

import com.guidewire.pstesting.data.UserTestData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class ClaimTestData {
    @XmlAttribute(name = "policy-id") private     String policyId;
    @XmlAttribute(name = "policy-number") private String policyNumber;
    @XmlAttribute(name = "last-name") private     String lastName;
    @XmlAttribute(name = "loss-date") private     String lossDate;

    @XmlElement(name = "user") private UserTestData user;

    private String claimNumber;

    public String getClaimNumber() {
        return claimNumber;
    }

    public void setClaimNumber(String claimNumber) {
        this.claimNumber = claimNumber;
    }

    public String getLossDate() {
        return lossDate;
    }

    public void setLossDate(String lossDate) {
        this.lossDate = lossDate;
    }

    public void setLastName(String lastName){this.lastName = lastName;}

    public String getLastName(){return lastName;}

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

    public UserTestData getUser() {
        return user;
    }

    public void setUser(UserTestData user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "ClaimTestData{" +
                "policyId='" + policyId + '\'' +
                ", policyNumber='" + policyNumber + '\'' +
                ", lastName='" + lastName + '\'' +
                ", lossDate='" + lossDate + '\'' +
                ", user=" + user +
                '}';
    }
}