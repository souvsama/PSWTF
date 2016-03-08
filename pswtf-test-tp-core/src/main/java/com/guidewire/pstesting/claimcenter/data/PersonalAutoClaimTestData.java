package com.guidewire.pstesting.claimcenter.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * This class represents a personal auto policy claim configured in a test data XML file.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PersonalAutoClaimTestData extends ClaimTestData {
    @XmlElement(name = "information") private InformationTestData information;
    @XmlElement(name = "loss-detail") private LossDetailTestData  lossDetail;
    @XmlElement(name = "reserve-detail") private ReserveDetailTestData  reserveDetail;
    @XmlElement(name = "payment-detail") private PaymentDetailTestData paymentDetailTestData;

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

    public ReserveDetailTestData getReserveDetail() {
        return reserveDetail;
    }

    public void setReserveDetail(ReserveDetailTestData reserveDetail) {
        this.reserveDetail = reserveDetail;
    }

    public PaymentDetailTestData getPaymentDetailTestData() {
        return paymentDetailTestData;
    }

    public void setPaymentDetailTestData(PaymentDetailTestData paymentDetailTestData) {
        this.paymentDetailTestData = paymentDetailTestData;
    }
}
