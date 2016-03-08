package com.guidewire.pstesting.policycenter.data.ho;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class CoveragesTestData {
    @XmlElement(name = "fire-dwelling") private       FireDwellingCoverageTestData       fireDwellingCoverage;
    @XmlElement(name = "homeowners-dwelling") private HomeownersDwellingCoverageTestData homeownersDwellingCoverage;

    public HomeownersDwellingCoverageTestData getHomeownersDwellingCoverage() {
        return homeownersDwellingCoverage;
    }

    public void setHomeownersDwellingCoverage(HomeownersDwellingCoverageTestData homeownersDwellingCoverage) {
        this.homeownersDwellingCoverage = homeownersDwellingCoverage;
    }

    public FireDwellingCoverageTestData getFireDwellingCoverage() {
        return fireDwellingCoverage;
    }

    public void setFireDwellingCoverage(FireDwellingCoverageTestData fireDwellingCoverage) {
        this.fireDwellingCoverage = fireDwellingCoverage;
    }
}
