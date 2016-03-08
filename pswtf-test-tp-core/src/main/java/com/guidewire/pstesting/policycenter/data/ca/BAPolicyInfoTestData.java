package com.guidewire.pstesting.policycenter.data.ca;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class BAPolicyInfoTestData {
    @XmlAttribute(name = "organization-type") private String organizationType;

    public String getOrganizationType() {
        return organizationType;
    }

    public void setOrganizationType(String organizationType) {
        this.organizationType = organizationType;
    }

    @Override
    public String toString() {
        return "BAPolicyInfoTestData{" +
                "organizationType='" + organizationType + '\'' +
                '}';
    }
}
