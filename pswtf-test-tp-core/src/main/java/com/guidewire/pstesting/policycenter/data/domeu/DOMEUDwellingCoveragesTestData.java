package com.guidewire.pstesting.policycenter.data.domeu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class DOMEUDwellingCoveragesTestData {
    @XmlAttribute(name = "sum-insured") private String sumInsured;

    public String getSumInsured() {
        return sumInsured;
    }

    public void setSumInsured(String sumInsured) {
        this.sumInsured = sumInsured;
    }

    @Override
    public String toString() {
        return "DOMEUDwellingCoveragesTestData{" +
                "sumInsured='" + sumInsured + '\'' +
                '}';
    }
}
