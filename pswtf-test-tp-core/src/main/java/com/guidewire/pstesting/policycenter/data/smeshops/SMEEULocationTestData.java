package com.guidewire.pstesting.policycenter.data.smeshops;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class SMEEULocationTestData {
    @XmlAttribute(name = "address1") private   String  address1;
    @XmlAttribute(name = "city") private       String  city;
    @XmlAttribute(name = "postcode") private   String  postcode;
    @XmlAttribute(name = "asbestos") private   Boolean asbestos;
    @XmlAttribute(name = "trade-code") private String  tradeCode;

    @XmlElement(name = "coverages") private SMEEUCoveragesTestData coverages;

    public String getAddress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public Boolean getAsbestos() {
        return asbestos;
    }

    public void setAsbestos(Boolean asbestos) {
        this.asbestos = asbestos;
    }

    public String getTradeCode() {
        return tradeCode;
    }

    public void setTradeCode(String tradeCode) {
        this.tradeCode = tradeCode;
    }

    public SMEEUCoveragesTestData getCoverages() {
        return coverages;
    }

    public void setCoverages(SMEEUCoveragesTestData coverages) {
        this.coverages = coverages;
    }

    @Override
    public String toString() {
        return "SMEEULocationTestData{" +
                "address1='" + address1 + '\'' +
                ", city='" + city + '\'' +
                ", postcode='" + postcode + '\'' +
                ", asbestos=" + asbestos +
                ", tradeCode='" + tradeCode + '\'' +
                "} " + super.toString();
    }
}
