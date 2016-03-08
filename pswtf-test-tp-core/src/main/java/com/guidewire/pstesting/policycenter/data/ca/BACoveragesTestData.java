package com.guidewire.pstesting.policycenter.data.ca;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class BACoveragesTestData {
    @XmlAttribute(name = "product") private         String product;
    @XmlAttribute(name = "fleet") private           String fleet;
    @XmlAttribute(name = "liability-limit") private String liabilityLimit;

    public String getProduct() {
        return product;
    }

    public void setProduct(String product) {
        this.product = product;
    }

    public String getFleet() {
        return fleet;
    }

    public void setFleet(String fleet) {
        this.fleet = fleet;
    }

    public String getLiabilityLimit() {
        return liabilityLimit;
    }

    public void setLiabilityLimit(String liabilityLimit) {
        this.liabilityLimit = liabilityLimit;
    }

    @Override
    public String toString() {
        return "BACoveragesTestData{" +
                "product='" + product + '\'' +
                ", fleet='" + fleet + '\'' +
                ", liabilityLimit='" + liabilityLimit + '\'' +
                '}';
    }
}
