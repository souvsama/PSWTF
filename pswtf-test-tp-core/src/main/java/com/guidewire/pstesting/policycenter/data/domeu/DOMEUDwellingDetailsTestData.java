package com.guidewire.pstesting.policycenter.data.domeu;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class DOMEUDwellingDetailsTestData {
    @XmlAttribute(name = "year-build") private             String yearBuilt;
    @XmlAttribute(name = "residence-type") private         String residenceType;
    @XmlAttribute(name = "listed-building") private        String listedBuilding;
    @XmlAttribute(name = "roof-construction") private      String roofConstruction;
    @XmlAttribute(name = "wall-construction") private      String wallConstruction;
    @XmlAttribute(name = "dwelling-type") private          String dwellingType;
    @XmlAttribute(name = "estimated-rebuild-cost") private String estimatedRebuildCost;

    public String getYearBuilt() {
        return yearBuilt;
    }

    public void setYearBuilt(String yearBuilt) {
        this.yearBuilt = yearBuilt;
    }

    public String getResidenceType() {
        return residenceType;
    }

    public void setResidenceType(String residenceType) {
        this.residenceType = residenceType;
    }

    public String getListedBuilding() {
        return listedBuilding;
    }

    public void setListedBuilding(String listedBuilding) {
        this.listedBuilding = listedBuilding;
    }

    public String getRoofConstruction() {
        return roofConstruction;
    }

    public void setRoofConstruction(String roofConstruction) {
        this.roofConstruction = roofConstruction;
    }

    public String getWallConstruction() {
        return wallConstruction;
    }

    public void setWallConstruction(String wallConstruction) {
        this.wallConstruction = wallConstruction;
    }

    public String getDwellingType() {
        return dwellingType;
    }

    public void setDwellingType(String dwellingType) {
        this.dwellingType = dwellingType;
    }

    public String getEstimatedRebuildCost() {
        return estimatedRebuildCost;
    }

    public void setEstimatedRebuildCost(String estimatedRebuildCost) {
        this.estimatedRebuildCost = estimatedRebuildCost;
    }
}
