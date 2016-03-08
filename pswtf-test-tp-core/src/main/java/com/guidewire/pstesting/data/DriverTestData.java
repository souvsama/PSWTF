package com.guidewire.pstesting.data;

import com.guidewire.pstesting.policycenter.Driver;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * This class represents a driver configured in the test data XML file.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DriverTestData extends ContactTestData implements Driver {
    @XmlAttribute(name = "id") private                  String  id;
    @XmlAttribute(name = "usage") private               String  parentageUsed;
    @XmlAttribute(name = "menu-index") private          String  menuIndex;
    @XmlAttribute(name = "account-holder") private      boolean accountHolder;
    @XmlAttribute(name = "year-first-licensed") private String  yearFirstLicensed;
    @XmlAttribute(name = "license-start-date") private  String  licenseStartDate;
    @XmlAttribute(name = "membership-level") private    String  membershipLevel;

    @XmlAttribute(name = "number-accidents-policy-level") private   String numberAccidentsPolicyLevel;
    @XmlAttribute(name = "number-accidents-account-level") private  String numberAccidentsAccountLevel;
    @XmlAttribute(name = "number-violations-policy-level") private  String numberViolationsPolicyLevel;
    @XmlAttribute(name = "number-violations-account-level") private String numberViolationsAccountLevel;

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        StringBuilder builder = new StringBuilder();
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
        return builder.toString();
    }

    @Override
    public boolean isAccountHolder() {
        return accountHolder;
    }

    @Override
    public void setAccountHolder(boolean accountHolder) {
        this.accountHolder = accountHolder;
    }

    @Override
    public String getMenuIndex() {
        return menuIndex;
    }

    @Override
    public void setMenuIndex(String menuIndex) {
        this.menuIndex = menuIndex;
    }

    @Override
    public void setParentageUsed(String parentageUsed) {
        this.parentageUsed = parentageUsed;
    }

    @Override
    public String getParentageUsed() {
        return parentageUsed;
    }

    public String getMembershipLevel() {
        return membershipLevel;
    }

    public void setMembershipLevel(String membershipLevel) {
        this.membershipLevel = membershipLevel;
    }

    @Override
    public String getYearFirstLicensed() {
        return yearFirstLicensed;
    }

    @Override
    public void setYearFirstLicensed(String yearFirstLicensed) {
        this.yearFirstLicensed = yearFirstLicensed;
    }

    @Override
    public String getLicenseStartDate() {
        return licenseStartDate;
    }

    @Override
    public void setLicenseStartDate(String licenseStartDate) {
        this.licenseStartDate = licenseStartDate;
    }

    @Override
    public String getNumberAccidentsPolicyLevel() {
        return numberAccidentsPolicyLevel;
    }

    @Override
    public void setNumberAccidentsPolicyLevel(String numberAccidentsPolicyLevel) {
        this.numberAccidentsPolicyLevel = numberAccidentsPolicyLevel;
    }

    @Override
    public String getNumberAccidentsAccountLevel() {
        return numberAccidentsAccountLevel;
    }

    @Override
    public void setNumberAccidentsAccountLevel(String numberAccidentsAccountLevel) {
        this.numberAccidentsAccountLevel = numberAccidentsAccountLevel;
    }

    @Override
    public String getNumberViolationsPolicyLevel() {
        return numberViolationsPolicyLevel;
    }

    @Override
    public void setNumberViolationsPolicyLevel(String numberViolationsPolicyLevel) {
        this.numberViolationsPolicyLevel = numberViolationsPolicyLevel;
    }

    @Override
    public String getNumberViolationsAccountLevel() {
        return numberViolationsAccountLevel;
    }

    @Override
    public void setNumberViolationsAccountLevel(String numberViolationsAccountLevel) {
        this.numberViolationsAccountLevel = numberViolationsAccountLevel;
    }

    @Override
    public String toString() {
        return "DriverTestData{" +
                "id='" + id + '\'' +
                ", accountHolder=" + accountHolder +
                ", yearFirstLicensed='" + yearFirstLicensed + '\'' +
                ", licenseStartDate='" + licenseStartDate + '\'' +
                ", parentageUsed='" + parentageUsed + '\'' +
                '}';
    }
}