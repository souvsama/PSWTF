package com.guidewire.pstesting.policycenter.data.pa;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class OfferingTestData {
    @XmlAttribute(name = "name") private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "OfferingTestData{" +
                "name='" + name + '\'' +
                '}';
    }
}
