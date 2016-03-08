package com.guidewire.pstesting.policycenter.data.ho;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class FireDwellingCoverageTestData {
    @XmlAttribute(name = "limit") private String limit;

    public String getLimit() {
        return limit;
    }

    public void setLimit(String limit) {
        this.limit = limit;
    }

    @Override
    public String toString() {
        return "FireDwellingCoverageTestData{" +
                "limit='" + limit + '\'' +
                '}';
    }
}
