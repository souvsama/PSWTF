package com.guidewire.pstesting.policycenter.data.domeu;

import com.guidewire.pstesting.policycenter.data.PolicyTestData;
import com.guidewire.pstesting.policycenter.data.QualificationsTestData;
import com.guidewire.pstesting.policycenter.data.pa.OfferingTestData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * This class represents a domestic property policy configured in a test data XML file.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class DOMEUPolicyTestData extends PolicyTestData {
    @XmlElement(name = "offering") private         OfferingTestData             offering;
    @XmlElement(name = "qualifications") private   QualificationsTestData       qualifications;
    @XmlElement(name = "dwelling-details") private DOMEUDwellingDetailsTestData dwellingDetails;
    @XmlElement(name = "coverages") private        DOMEUCoveragesTestData       coverages;

    public OfferingTestData getOffering() {
        return offering;
    }

    public void setOffering(OfferingTestData offering) {
        this.offering = offering;
    }

    public QualificationsTestData getQualifications() {
        return qualifications;
    }

    public void setQualifications(QualificationsTestData qualifications) {
        this.qualifications = qualifications;
    }

    public DOMEUDwellingDetailsTestData getDwellingDetails() {
        return dwellingDetails;
    }

    public void setDwellingDetails(DOMEUDwellingDetailsTestData dwellingDetails) {
        this.dwellingDetails = dwellingDetails;
    }

    public DOMEUCoveragesTestData getCoverages() {
        return coverages;
    }

    public void setCoverages(DOMEUCoveragesTestData coverages) {
        this.coverages = coverages;
    }
}
