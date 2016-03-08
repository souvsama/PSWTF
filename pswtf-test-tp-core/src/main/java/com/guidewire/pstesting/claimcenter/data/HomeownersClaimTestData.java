package com.guidewire.pstesting.claimcenter.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * This class represents a homeowners policy claim configured in a test data XML file.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class HomeownersClaimTestData extends ClaimTestData {
    @XmlElement(name = "information") private InformationTestData information;
    @XmlElement(name = "loss-detail") private LossDetailTestData  lossDetail;

    public InformationTestData getInformation() {
        return information;
    }

    public void setInformation(InformationTestData information) {
        this.information = information;
    }

    public LossDetailTestData getLossDetail() {
        return lossDetail;
    }

    public void setLossDetail(LossDetailTestData lossDetail) {
        this.lossDetail = lossDetail;
    }
}
