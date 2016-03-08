package com.guidewire.pstesting.policycenter.data.smeshops;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class SMEEUBuildingCoverageTestData {
    @XmlAttribute(name = "amount") private             String amount;
    @XmlAttribute(name = "rental-income-loss") private String rentalIncomeLoss;

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getRentalIncomeLoss() {
        return rentalIncomeLoss;
    }

    public void setRentalIncomeLoss(String rentalIncomeLoss) {
        this.rentalIncomeLoss = rentalIncomeLoss;
    }

    @Override
    public String toString() {
        return "SMEEUBuildingCoverageTestData{" +
                "amount='" + amount + '\'' +
                ", rentalIncomeLoss='" + rentalIncomeLoss + '\'' +
                '}';
    }
}
