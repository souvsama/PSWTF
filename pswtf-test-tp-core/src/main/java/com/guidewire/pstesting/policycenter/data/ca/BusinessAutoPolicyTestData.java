package com.guidewire.pstesting.policycenter.data.ca;

import com.guidewire.pstesting.data.DriverTestData;
import com.guidewire.pstesting.policycenter.data.PolicyTestData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class BusinessAutoPolicyTestData extends PolicyTestData {
    @XmlElement(name = "coverages") private                                     BACoveragesTestData     coverages;
    @XmlElement(name = "policy-info") private                                   BAPolicyInfoTestData    policyInfo;
    @XmlElement(name = "driver") @XmlElementWrapper(name = "drivers") private   List<DriverTestData>    drivers;
    @XmlElement(name = "vehicle") @XmlElementWrapper(name = "vehicles") private List<BAVehicleTestData> vehicles;

    public BACoveragesTestData getCoverages() {
        return coverages;
    }

    public BAPolicyInfoTestData getPolicyInfo() {
        return policyInfo;
    }

    public void setPolicyInfo(BAPolicyInfoTestData policyInfo) {
        this.policyInfo = policyInfo;
    }

    public void setCoverages(BACoveragesTestData coverages) {
        this.coverages = coverages;
    }

    public List<DriverTestData> getDrivers() {
        return drivers;
    }

    public DriverTestData getDriver(String id) {
        // TODO: Brut force approach to locating driver. Should improve this (SLC)
        if (drivers != null) {
            for (DriverTestData driver : drivers) {
                if (driver.getId().equals(id)) {
                    return driver;
                }
            }
        }
        return null;
    }

    public void setDrivers(List<DriverTestData> drivers) {
        this.drivers = drivers;
    }

    public List<BAVehicleTestData> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<BAVehicleTestData> vehicles) {
        this.vehicles = vehicles;
    }
}
