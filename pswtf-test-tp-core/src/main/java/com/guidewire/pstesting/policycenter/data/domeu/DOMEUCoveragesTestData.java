package com.guidewire.pstesting.policycenter.data.domeu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class DOMEUCoveragesTestData {
    @XmlElement(name = "dwelling") private DOMEUDwellingCoveragesTestData dwellingCoverage;

    public DOMEUDwellingCoveragesTestData getDwellingCoverage() {
        return dwellingCoverage;
    }

    public void setDwellingCoverage(DOMEUDwellingCoveragesTestData dwellingCoverage) {
        this.dwellingCoverage = dwellingCoverage;
    }

    @Override
    public String toString() {
        return "DOMEUCoveragesTestData{" +
                "dwellingCoverage=" + dwellingCoverage +
                '}';
    }
}
