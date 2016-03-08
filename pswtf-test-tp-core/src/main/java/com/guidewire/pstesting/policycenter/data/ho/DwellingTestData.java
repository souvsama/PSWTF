package com.guidewire.pstesting.policycenter.data.ho;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class DwellingTestData {
    @XmlElement(name = "details") private      DwellingDetailsTestData      details;
    @XmlElement(name = "protection") private   DwellingProtectionTestData   protection;
    @XmlElement(name = "construction") private DwellingConstructionTestData construction;

    public DwellingDetailsTestData getDetails() {
        return details;
    }

    public void setDetails(DwellingDetailsTestData details) {
        this.details = details;
    }

    public DwellingProtectionTestData getProtection() {
        return protection;
    }

    public void setProtection(DwellingProtectionTestData protection) {
        this.protection = protection;
    }

    public DwellingConstructionTestData getConstruction() {
        return construction;
    }

    public void setConstruction(DwellingConstructionTestData construction) {
        this.construction = construction;
    }
}
