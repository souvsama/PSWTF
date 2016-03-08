package com.guidewire.pstesting.claimcenter.data;

import com.guidewire.pstesting.data.UserTestData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * Base class for claim center test data configured in a test data XML file.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class ClaimCenterTestData {
    @XmlElement(name = "user") private UserTestData user;

    @XmlElement(name = "homeowners-claim") private    List<HomeownersClaimTestData>   homeownersClaims;
    @XmlElement(name = "business-auto-claim") private List<BusinessAutoClaimTestData> businessAutoClaims;
    @XmlElement(name = "personal-auto-claim") private List<PersonalAutoClaimTestData> personalAutoClaims;

    public UserTestData getUser() {
        return user;
    }

    public void setUser(UserTestData user) {
        this.user = user;
    }

    public boolean personalAutoClaimsExist() {
        return (personalAutoClaims != null && personalAutoClaims.size() > 0);
    }

    public List<PersonalAutoClaimTestData> getPersonalAutoClaims() {
        return personalAutoClaims;
    }

    public void setPersonalAutoClaims(List<PersonalAutoClaimTestData> personalAutoClaims) {
        this.personalAutoClaims = personalAutoClaims;
    }

    public boolean businessAutoClaimsExist() {
        return (businessAutoClaims != null && businessAutoClaims.size() > 0);
    }

    public List<BusinessAutoClaimTestData> getBusinessAutoClaims() {
        return businessAutoClaims;
    }

    public void setBusinessAutoClaims(List<BusinessAutoClaimTestData> businessAutoClaims) {
        this.businessAutoClaims = businessAutoClaims;
    }

    public boolean homeownersClaimsExist() {
        return (homeownersClaims != null && homeownersClaims.size() > 0);
    }

    public List<HomeownersClaimTestData> getHomeownersClaims() {
        return homeownersClaims;
    }

    public void setHomeownersClaims(List<HomeownersClaimTestData> homeownersClaims) {
        this.homeownersClaims = homeownersClaims;
    }

}
