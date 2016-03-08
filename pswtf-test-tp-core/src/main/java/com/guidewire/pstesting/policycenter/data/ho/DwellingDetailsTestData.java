package com.guidewire.pstesting.policycenter.data.ho;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class DwellingDetailsTestData {
    @XmlAttribute(name = "distance-to-fire-hydrant") private   String  distanceToFireHydrant;
    @XmlAttribute(name = "distance-to-fire-station") private   String  distanceToFireStation;
    @XmlAttribute(name = "flood-fire-hazard") private          Boolean floodFireHazard;
    @XmlAttribute(name = "within-commercial-property") private Boolean withinCommercialProperty;
    @XmlAttribute(name = "roomers-boarders") private           Boolean roomersBoarders;
    @XmlAttribute(name = "fire-wood-stove") private            Boolean fireWoodStove;
    @XmlAttribute(name = "swimming-pool") private              Boolean swimmingPool;
    @XmlAttribute(name = "trampoline") private                 Boolean trampoline;
    @XmlAttribute(name = "water-leakage") private              Boolean waterLeakage;
    @XmlAttribute(name = "any-animals") private                Boolean anyAnimals;

    public String getDistanceToFireHydrant() {
        return distanceToFireHydrant;
    }

    public void setDistanceToFireHydrant(String distanceToFireHydrant) {
        this.distanceToFireHydrant = distanceToFireHydrant;
    }

    public String getDistanceToFireStation() {
        return distanceToFireStation;
    }

    public void setDistanceToFireStation(String distanceToFireStation) {
        this.distanceToFireStation = distanceToFireStation;
    }

    public Boolean getFloodFireHazard() {
        return floodFireHazard;
    }

    public void setFloodFireHazard(Boolean floodFireHazard) {
        this.floodFireHazard = floodFireHazard;
    }

    public Boolean getWithinCommercialProperty() {
        return withinCommercialProperty;
    }

    public void setWithinCommercialProperty(Boolean withinCommercialProperty) {
        this.withinCommercialProperty = withinCommercialProperty;
    }

    public Boolean getRoomersBoarders() {
        return roomersBoarders;
    }

    public void setRoomersBoarders(Boolean roomersBoarders) {
        this.roomersBoarders = roomersBoarders;
    }

    public Boolean getFireWoodStove() {
        return fireWoodStove;
    }

    public void setFireWoodStove(Boolean fireWoodStove) {
        this.fireWoodStove = fireWoodStove;
    }

    public Boolean getSwimmingPool() {
        return swimmingPool;
    }

    public void setSwimmingPool(Boolean swimmingPool) {
        this.swimmingPool = swimmingPool;
    }

    public Boolean getAnyAnimals() {
        return anyAnimals;
    }

    public void setAnyAnimals(Boolean anyAnimals) {
        this.anyAnimals = anyAnimals;
    }

    public Boolean getTrampoline() {
        return trampoline;
    }

    public void setTrampoline(Boolean trampoline) {
        this.trampoline = trampoline;
    }

    public Boolean getWaterLeakage() {
        return waterLeakage;
    }

    public void setWaterLeakage(Boolean waterLeakage) {
        this.waterLeakage = waterLeakage;
    }
}
