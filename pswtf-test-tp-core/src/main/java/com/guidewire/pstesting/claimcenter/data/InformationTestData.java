package com.guidewire.pstesting.claimcenter.data;


import com.guidewire.pstesting.data.ContactTestData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

@XmlAccessorType(XmlAccessType.FIELD)
public class InformationTestData {
    @XmlAttribute(name = "involved-vehicle") private    String involvedPolicyVehicle;
    @XmlAttribute(name = "reported-by") private         String reportedBy;
    @XmlAttribute(name = "reported-by-id") private      String reportedById;
    @XmlAttribute(name = "relation-to-insured") private String relationToInsured;

    @XmlElement(name = "contact") private ContactTestData contact;

    public String getInvolvedVehicle(){return involvedPolicyVehicle;}

    public void setInvolvedVehicle(String vehicle){this.involvedPolicyVehicle = vehicle;}

    public String getReportedBy() {
        return reportedBy;
    }

    public void setReportedBy(String reportedBy) {
        this.reportedBy = reportedBy;
    }

    public String getReportedById() {
        return reportedById;
    }

    public void setReportedById(String reportedById) {
        this.reportedById = reportedById;
    }

    public String getRelationToInsured() {
        return relationToInsured;
    }

    public void setRelationToInsured(String relationToInsured) {
        this.relationToInsured = relationToInsured;
    }

    public ContactTestData getContact() {
        return contact;
    }

    public void setContact(ContactTestData contact) {
        this.contact = contact;
    }

    @Override
    public String toString() {
        return "InformationTestData{" +
                "reportedBy='" + reportedBy + '\'' +
                ", reportedById='" + reportedById + '\'' +
                ", relationToInsured='" + relationToInsured + '\'' +
                '}';
    }
}
