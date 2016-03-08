package com.guidewire.pstesting.policycenter.data;

import com.guidewire.pstesting.data.ContactTestData;
import com.guidewire.pstesting.data.UserTestData;
import com.guidewire.pstesting.policycenter.Account;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * This class represents an account configured in the test data XML file.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountTestData extends ContactTestData implements Account {
    @XmlAttribute(name = "id") private            String id;
    @XmlAttribute(name = "number") private        String accountNumber;
    @XmlAttribute(name = "type") private          String type;
    @XmlAttribute(name = "organization") private  String organization;
    @XmlAttribute(name = "organization-type") private  String organizationType;
    @XmlAttribute(name = "producer-code") private String producerCode;

    @XmlElement(name = "user") private UserTestData user;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public UserTestData getUser() {
        return user;
    }

    public void setUser(UserTestData user) {
        this.user = user;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getName() {
        StringBuilder builder = new StringBuilder();
        if (isPerson()) {
            String firstName = getFirstName();
            String lastName = getLastName();
            if (firstName != null) {
                builder.append(firstName);
                // Need to add a space?
                if (lastName != null) {
                    builder.append(" ");
                }
            }
            if (lastName != null) {
                builder.append(getLastName());
            }
        } else {
            builder.append(getCompanyName());
        }
        return builder.toString();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isPerson() {
        return (PERSON.equalsIgnoreCase(type));
    }

    public boolean isCompany() {
        return (COMPANY.equalsIgnoreCase(type));
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    public String getProducerCode() {
        return producerCode;
    }

    public void setProducerCode(String producerCode) {
        this.producerCode = producerCode;
    }

    @Override
    public String toString() {
        return "AccountTestData{" +
                "id='" + id + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", type='" + type + '\'' +
                "} " + super.toString();
    }
}
