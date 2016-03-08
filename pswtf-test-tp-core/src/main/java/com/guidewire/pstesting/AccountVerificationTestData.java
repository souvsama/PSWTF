package com.guidewire.pstesting;

import com.guidewire.pstesting.data.UserTestData;
import com.guidewire.pstesting.policycenter.data.AccountTestData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class AccountVerificationTestData {
    @XmlAttribute(name = "account-id") private     String  accountId;
    @XmlAttribute(name = "account-number") private String  accountNumber;
    @XmlAttribute(name = "check-details") private  Boolean checkDetails;  // Defaults to true
    @XmlAttribute(name = "all") private            Boolean verifyAll;  // Defaults to false

    @XmlElement(name = "user") private  UserTestData  user;

    AccountTestData account;

    public AccountVerificationTestData() {
    }

    public AccountVerificationTestData(String accountId, String accountNumber) {
        this.accountId = accountId;
        this.accountNumber = accountNumber;
    }

    public AccountTestData getAccount() {
        return account;
    }

    public void setAccount(AccountTestData account) {
        this.account = account;
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

    public Boolean getCheckDetails() {
        return (checkDetails == null ? true : checkDetails);
    }

    public void setCheckDetails(Boolean checkDetails) {
        this.checkDetails = checkDetails;
    }

    public Boolean getVerifyAll() {
        return (verifyAll == null ? false : verifyAll);
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
        return "AccountVerificationTestData{" +
                "accountId='" + accountId + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", user=" + user +
                '}';
    }
}
