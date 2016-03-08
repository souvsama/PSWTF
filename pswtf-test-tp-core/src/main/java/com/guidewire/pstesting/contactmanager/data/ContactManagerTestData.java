package com.guidewire.pstesting.contactmanager.data;

import com.guidewire.pstesting.AccountVerificationTestData;
import com.guidewire.pstesting.data.UserTestData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Base class for contact manager test data configured in a test data XML file.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ContactManagerTestData {
    @XmlElement(name = "user") private  UserTestData  user;

    @XmlElement(name = "verify-account") private List<AccountVerificationTestData> accountVerifications;

    public UserTestData getUser() {
        return user;
    }

    public void setUser(UserTestData user) {
        this.user = user;
    }

    public List<AccountVerificationTestData> getAccountVerifications() {
        return accountVerifications;
    }

    public void setAccountVerifications(List<AccountVerificationTestData> accountVerifications) {
        this.accountVerifications = accountVerifications;
    }

    public boolean accountVerificationsExist() {
        return (accountVerifications != null && accountVerifications.size() > 0);
    }
}
