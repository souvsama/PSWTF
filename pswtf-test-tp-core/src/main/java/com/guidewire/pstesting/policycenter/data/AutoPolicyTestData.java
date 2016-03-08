package com.guidewire.pstesting.policycenter.data;

import com.guidewire.pstesting.data.DriverTestData;
import com.guidewire.pstesting.policycenter.data.pa.OfferingTestData;
import com.guidewire.pstesting.policycenter.data.pa.VehicleTestData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class AutoPolicyTestData extends PolicyTestData {
    @XmlElement(name = "offering") private OfferingTestData offering;

    @XmlElement(name = "driver") @XmlElementWrapper(name = "drivers") private   List<DriverTestData>  drivers;
    @XmlElement(name = "vehicle") @XmlElementWrapper(name = "vehicles") private List<VehicleTestData> vehicles;

    public OfferingTestData getOffering() {
        return offering;
    }

    public void setOffering(OfferingTestData offering) {
        this.offering = offering;
    }

    public List<DriverTestData> getDrivers() {
        return drivers;
    }

    public void setDrivers(List<DriverTestData> drivers) {
        this.drivers = drivers;
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

    public List<VehicleTestData> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<VehicleTestData> vehicles) {
        this.vehicles = vehicles;
    }

    @Override
    public String toString() {
        return "AutoPolicyTestData{} " + super.toString();
    }
}
