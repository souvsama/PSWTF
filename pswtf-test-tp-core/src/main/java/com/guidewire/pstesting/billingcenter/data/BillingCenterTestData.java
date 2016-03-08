package com.guidewire.pstesting.billingcenter.data;

import com.guidewire.pstesting.AccountVerificationTestData;
import com.guidewire.pstesting.data.UserTestData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.Collections;
import java.util.List;

/**
 * Base class for billing center test data configured in a test data XML file.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class BillingCenterTestData {
    @XmlElement(name = "user") private  UserTestData  user;

    @XmlElement(name = "verify-policy") private  List<PolicyVerificationTestData>  policyVerifications;
    @XmlElement(name = "verify-account") private List<AccountVerificationTestData> accountVerifications;

    public UserTestData getUser() {
        return user;
    }

    public void setUser(UserTestData user) {
        this.user = user;
    }

    public List<PolicyVerificationTestData> getPolicyVerifications() {
        return (policyVerifications == null ? Collections.<PolicyVerificationTestData>emptyList() : policyVerifications);
    }

    public void setPolicyVerifications(List<PolicyVerificationTestData> policyVerifications) {
        this.policyVerifications = policyVerifications;
    }

    public List<AccountVerificationTestData> getAccountVerifications() {
        return (accountVerifications == null ? Collections.<AccountVerificationTestData>emptyList() : accountVerifications);
    }

    public void setAccountVerifications(List<AccountVerificationTestData> accountVerifications) {
        this.accountVerifications = accountVerifications;
    }
}
