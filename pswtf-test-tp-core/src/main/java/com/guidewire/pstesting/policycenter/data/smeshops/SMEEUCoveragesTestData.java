package com.guidewire.pstesting.policycenter.data.smeshops;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SMEEUCoveragesTestData {
    @XmlElement(name = "building") private SMEEUBuildingCoverageTestData building;

    public SMEEUBuildingCoverageTestData getBuildingCoverages() {
        return building;
    }

    public void setBuildingCoverages(SMEEUBuildingCoverageTestData building) {
        this.building = building;
    }
}
