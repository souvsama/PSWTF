package com.guidewire.pstesting.policycenter;

import com.guidewire.pstesting.suite.Contact;

public interface Driver extends Contact {
    String getId();

    void setId(String id);

    String getName();

    boolean isAccountHolder();

    void setAccountHolder(boolean accountHolder);

    String getMenuIndex();

    void setMenuIndex(String menuIndex);

    void setParentageUsed(String parentageUsed);

    String getParentageUsed();

    String getMembershipLevel();

    void setMembershipLevel(String membershipLevel);

    String getYearFirstLicensed();

    void setYearFirstLicensed(String yearFirstLicensed);

    String getLicenseStartDate();

    void setLicenseStartDate(String licenseStartDate);

    String getNumberAccidentsPolicyLevel();

    void setNumberAccidentsPolicyLevel(String numberAccidentsPolicyLevel);

    String getNumberAccidentsAccountLevel();

    void setNumberAccidentsAccountLevel(String numberAccidentsAccountLevel);

    String getNumberViolationsPolicyLevel();

    void setNumberViolationsPolicyLevel(String numberViolationsPolicyLevel);

    String getNumberViolationsAccountLevel();

    void setNumberViolationsAccountLevel(String numberViolationsAccountLevel);
}
