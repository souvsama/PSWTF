package com.guidewire.pstesting.claimcenter.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class ReserveDetailTestData {
    @XmlAttribute(name = "exposure")        private String exposure;
    @XmlAttribute(name = "cost-type")       private String costType;
    @XmlAttribute(name = "cost-category")   private String costCategory;
    @XmlAttribute(name = "reserve-amount")  private String reserveAmount;

    public String getExposure() {
        return exposure;
    }

    public void setExposure(String exposure) {
        this.exposure = exposure;
    }

    public String getCostType() {
        return costType;
    }

    public void setCostType(String costType) {
        this.costType = costType;
    }

    public String getCostCategory() {
        return costCategory;
    }

    public void setCostCategory(String costCategory) {
        this.costCategory = costCategory;
    }

    public String getReserveAmount() {
        return reserveAmount;
    }

    public void setReserveAmount(String reserveAmount) {
        this.reserveAmount = reserveAmount;
    }

    @Override
    public String toString() {
        return "ReserveDetailTestData{" +
                "exposure='" + exposure + '\'' +
                ", cost-type='" + costType + '\'' +
                ", cost-category='" + costCategory + '\'' +
                ", reserve-amount='" + reserveAmount + '\'' +
                '}';
    }
}