package com.guidewire.pstesting.policycenter.data;

import com.guidewire.pstesting.data.UserTestData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CancellationTestData extends PolicyTestData {
    @XmlAttribute(name = "id") private                  String cancelId;
    @XmlAttribute(name = "account-name") private        String accountName;
    @XmlAttribute(name = "policy-number") private       String policyNumber;
    @XmlAttribute(name = "cancellation-source") private String source;
    @XmlAttribute(name = "cancellation-reason") private String reason;
    @XmlAttribute(name = "refund-method") private       String refundMethod;
    @XmlAttribute(name = "effective-date") private      String effectiveDate;
    @XmlAttribute(name = "reason-description") private  String reasonDescription;

    @XmlElement(name = "user") private UserTestData user;

    private String cancellationNumber;

    public String getCancellationNumber(){return cancellationNumber;}

    public void setCancellationNumber(String cancellationNumber){this.cancellationNumber = cancellationNumber;}

    public String getCancelId() {
        return cancelId;
    }

    public void setId(String cancelId) {
        this.cancelId = cancelId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

   public String getPolicyNumber() {
        return policyNumber;
    }

    public void setPolicyNumber(String policyNumber) {
        this.policyNumber = policyNumber;
    }

    public String getSource(){return source;}

    public void setSource(String source){this.source = source;}

    public String getReason(){return reason;}

    public void setReason(String reason){this.reason = reason;}

    public String getRefundMethod(){return refundMethod;}

    public void setRefundMethod(String refundMethod){this.refundMethod = refundMethod;}

    public String getEffectiveDate(){return effectiveDate;}

    public void setEffectiveDate(String effectiveDate){this.effectiveDate = effectiveDate;}

    public String getReasonDescription(){return reasonDescription;}

    public void setReasonDescription(String reasonDescription){this.reasonDescription = reasonDescription;}

    public UserTestData getUser() {
        return user;
    }

    public void setUser(UserTestData user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "CancellationTestData{" +
                "id='" + cancelId + '\'' +
                ", accountName='" + accountName + '\'' +
                ", policyNumber='" + policyNumber + '\'' +
                ", cancellation-source='" + source + '\'' +
                ", cancellation-reason='" + reason + '\'' +
                ", reasonDesc='" + reasonDescription + '\'' +
                ", refundMethod='" + refundMethod + '\'' +
                ", effectiveDate='" + effectiveDate + '\'' +
                ", user=" + user +
                "} " + super.toString();
    }

}
