package com.guidewire.pstesting.policycenter.data.ho;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class DwellingConstructionTestData {
    @XmlAttribute(name = "year-built") private              String  yearBuilt;
    @XmlAttribute(name = "construction-type") private       String  constructionType;
    @XmlAttribute(name = "number-of-stories") private       String  numberOfStories;
    @XmlAttribute(name = "square-footage") private          String  squareFootage;
    @XmlAttribute(name = "garage") private                  String  garage;
    @XmlAttribute(name = "foundation-type") private         String  foundationType;
    @XmlAttribute(name = "roof-type") private               String  roofType;
    @XmlAttribute(name = "primary-heating-type") private    String  primaryHeatingType;
    @XmlAttribute(name = "plumbing") private                String  plumbing;
    @XmlAttribute(name = "wiring") private                  String  wiring;
    @XmlAttribute(name = "electrical-system") private       String  electricalSystem;
    @XmlAttribute(name = "number-of-amps") private          String  numberOfAmps;
    @XmlAttribute(name = "wind-class") private              String  windClass;
    @XmlAttribute(name = "construction-class-code") private String  constructionClassCode;
    @XmlAttribute(name = "secondary-heating") private       Boolean secondaryHeating;

    public String getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(String yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public String getConstructionType() {
        return constructionType;
    }

    public void setConstructionType(String constructionType) {
        this.constructionType = constructionType;
    }

    public String getNumberOfStories() {
        return numberOfStories;
    }

    public void setNumberOfStories(String numberOfStories) {
        this.numberOfStories = numberOfStories;
    }

    public String getSquareFootage() {
        return squareFootage;
    }

    public void setSquareFootage(String squareFootage) {
        this.squareFootage = squareFootage;
    }

    public String getGarage() {
        return garage;
    }

    public void setGarage(String garage) {
        this.garage = garage;
    }

    public String getFoundationType() {
        return foundationType;
    }

    public void setFoundationType(String foundationType) {
        this.foundationType = foundationType;
    }

    public String getRoofType() {
        return roofType;
    }

    public void setRoofType(String roofType) {
        this.roofType = roofType;
    }

    public String getPrimaryHeatingType() {
        return primaryHeatingType;
    }

    public void setPrimaryHeatingType(String primaryHeatingType) {
        this.primaryHeatingType = primaryHeatingType;
    }

    public String getPlumbing() {
        return plumbing;
    }

    public void setPlumbing(String plumbing) {
        this.plumbing = plumbing;
    }

    public String getWiring() {
        return wiring;
    }

    public void setWiring(String wiring) {
        this.wiring = wiring;
    }

    public String getElectricalSystem() {
        return electricalSystem;
    }

    public void setElectricalSystem(String electricalSystem) {
        this.electricalSystem = electricalSystem;
    }

    public String getNumberOfAmps() {
        return numberOfAmps;
    }

    public void setNumberOfAmps(String numberOfAmps) {
        this.numberOfAmps = numberOfAmps;
    }

    public String getWindClass() {
        return windClass;
    }

    public void setWindClass(String windClass) {
        this.windClass = windClass;
    }

    public String getConstructionClassCode() {
        return constructionClassCode;
    }

    public void setConstructionClassCode(String constructionClassCode) {
        this.constructionClassCode = constructionClassCode;
    }

    public Boolean getSecondaryHeating() {
        return secondaryHeating;
    }

    public void setSecondaryHeating(Boolean secondaryHeating) {
        this.secondaryHeating = secondaryHeating;
    }
}
