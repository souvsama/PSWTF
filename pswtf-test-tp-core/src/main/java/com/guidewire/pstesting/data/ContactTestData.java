package com.guidewire.pstesting.data;

import com.guidewire.pstesting.suite.Contact;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * This class represents a contact configured in the test data XML file.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ContactTestData implements Contact {
    @XmlAttribute(name = "country") private        String country;
    @XmlAttribute(name = "company-name") private   String companyName;
    @XmlAttribute(name = "first-name") private     String firstName;
    @XmlAttribute(name = "last-name") private      String lastName;
    @XmlAttribute(name = "dob") private            String dataOfBirth;
    @XmlAttribute(name = "sex") private            String gender;
    @XmlAttribute(name = "address1") private       String address1;
    @XmlAttribute(name = "suburb") private         String suburb;
    @XmlAttribute(name = "city") private           String city;
    @XmlAttribute(name = "state") private          String state;
    @XmlAttribute(name = "home-phone") private     String homePhone;
    @XmlAttribute(name = "mobile-phone") private   String mobilePhone;
    @XmlAttribute(name = "postalcode") private     String postalCode;
    @XmlAttribute(name = "address-type") private   String addressType;
    @XmlAttribute(name = "license-number") private String licenseNumber;
    @XmlAttribute(name = "license-state") private  String licenseState;

    @Override
    public String getCountry() {
        return country;
    }

    @Override
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String getCompanyName() {
        return companyName;
    }

    @Override
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getDateOfBirth() {
        return dataOfBirth;
    }

    @Override
    public void setDataOfBirth(String dataOfBirth) {
        this.dataOfBirth = dataOfBirth;
    }

    @Override
    public String getGender() {
        return gender;
    }

    @Override
    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String getAddress1() {
        return address1;
    }

    @Override
    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    @Override
    public String getSuburb() {
        return suburb;
    }

    @Override
    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public String getHomePhone() {
        return homePhone;
    }

    @Override
    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }

    @Override
    public String getMobilePhone() {
        return mobilePhone;
    }

    @Override
    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    @Override
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String getAddressType() {
        return addressType;
    }

    @Override
    public void setAddressType(String addressType) {
        this.addressType = addressType;
    }

    @Override
    public String getLicenseNumber() {
        return licenseNumber;
    }

    @Override
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    @Override
    public String getLicenseState() {
        return licenseState;
    }

    @Override
    public void setLicenseState(String licenseState) {
        this.licenseState = licenseState;
    }

    @Override
    public String toString() {
        return "ContactTestData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", companyName='" + companyName + '\'' +
                '}';
    }
}

