package com.guidewire.pstesting.claimcenter.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class LossDetailTestData {
    @XmlAttribute(name = "description") private String description;
    @XmlAttribute(name = "cause") private String cause;
    @XmlAttribute(name = "location") private String location;
    @XmlAttribute(name = "city") private String city;
    @XmlAttribute(name = "fault-rating")  private String faultRating;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCause() {
        return cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }

    public String getLocation(){return location;}

    public void setLocation(String address){this.location = address;}

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getFaultRating() {
        return faultRating;
    }

    public void setFaultRating(String faultRating) {
        this.faultRating = faultRating;
    }


    @Override
    public String toString() {
        return "LossDetailTestData{" +
                "description='" + description + '\'' +
                ", cause='" + cause + '\'' +
                ", city='" + city + '\'' +
                ", faultRating='" + faultRating + '\'' +
                '}';
    }
}