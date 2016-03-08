package com.guidewire.pstesting.policycenter.data;

import com.guidewire.pstesting.data.UserTestData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public abstract class PolicyTestData {
    @XmlAttribute(name = "id") private                 String id;
    @XmlAttribute(name = "account-id") private         String accountId;
    @XmlAttribute(name = "account-number") private     String accountNumber;
    @XmlAttribute(name = "organization") private       String organization;
    @XmlAttribute(name = "producer-code") private      String producerCode;
    @XmlAttribute(name = "default-base-state") private String defaultBaseState;
    @XmlElement(name = "cancellation") private         CancellationTestData cancellation;

    @XmlElement(name = "user") private UserTestData user;

    private String policyNumber;

    public CancellationTestData getCancellation(){return cancellation;}

    public void setCancellation(CancellationTestData cancellation){this.cancellation = cancellation;}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getProducerCode() {
        return producerCode;
    }

    public void setProducerCode(String producerCode) {
        this.producerCode = producerCode;
    }

    public String getDefaultBaseState() {
        return defaultBaseState;
    }

    public void setDefaultBaseState(String defaultBaseState) {
        this.defaultBaseState = defaultBaseState;
    }

    public UserTestData getUser() {
        return user;
    }

    public void setUser(UserTestData user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "PolicyTestData{" +
                "id='" + id + '\'' +
                ", accountId='" + accountId + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", organization='" + organization + '\'' +
                ", producerCode='" + producerCode + '\'' +
                ", defaultBaseState='" + defaultBaseState + '\'' +
                ", policyNumber='" + policyNumber + '\'' +
                ", user=" + user +
                "} " + super.toString();
    }
}
