package com.guidewire.pstesting.policycenter.data.ho;

import com.guidewire.pstesting.policycenter.data.PolicyTestData;
import com.guidewire.pstesting.policycenter.data.QualificationsTestData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * This class represents a home owners policy configured in a test data XML file.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class HomeownersPolicyTestData extends PolicyTestData {
    @XmlElement(name = "coverages") private      CoveragesTestData      coverages;
    @XmlElement(name = "dwelling") private       DwellingTestData       dwelling;
    @XmlElement(name = "qualifications") private QualificationsTestData qualifications;

    public QualificationsTestData getQualifications() {
        return qualifications;
    }

    public void setQualifications(QualificationsTestData qualifications) {
        this.qualifications = qualifications;
    }

    public CoveragesTestData getCoverages() {
        return coverages;
    }

    public void setCoverages(CoveragesTestData coverages) {
        this.coverages = coverages;
    }

    public DwellingTestData getDwelling() {
        return dwelling;
    }

    public void setDwelling(DwellingTestData dwelling) {
        this.dwelling = dwelling;
    }
}
