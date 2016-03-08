package com.guidewire.pstesting.policycenter.data.ca;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class BAVehicleTestData {
    @XmlAttribute(name = "garaged-at") private String garagedAt;
    @XmlAttribute(name = "type") private       String type;
    @XmlAttribute(name = "vin") private        String vin;
    @XmlAttribute(name = "cost") private       String cost;
    @XmlAttribute(name = "class-code") private String classCode;

    public String getGaragedAt() {
        return garagedAt;
    }

    public void setGaragedAt(String garagedAt) {
        this.garagedAt = garagedAt;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    @Override
    public String toString() {
        return "BAVehicleTestData{" +
                "type='" + type + '\'' +
                ", vin='" + vin + '\'' +
                ", cost='" + cost + '\'' +
                ", classCode='" + classCode + '\'' +
                '}';
    }
}
