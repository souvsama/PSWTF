package com.guidewire.pstesting.policycenter.data.pa;

import com.guidewire.pstesting.data.DriverTestData;

import javax.xml.bind.annotation.*;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class VehicleTestData {
    @XmlAttribute(name = "license-plate") private        String  licensePlate;
    @XmlAttribute(name = "manufacturer-key") private     String  manufacturerKey;
    @XmlAttribute(name = "vehicle-key") private          String  vehicleKey;
    @XmlAttribute(name = "vehicle-group") private        String  vehicleGroup;
    @XmlAttribute(name = "model-year") private           String  modelYear;
    @XmlAttribute(name = "equipment-value") private      String  equipmentValue;
    @XmlAttribute(name = "annual-mileage") private       String  annualMileage;
    @XmlAttribute(name = "garage-type") private          String  garageType;
    @XmlAttribute(name = "primary-use") private          String  primaryUse;
    @XmlAttribute(name = "license-state") private        String  licenseState;
    @XmlAttribute(name = "license-jurisdiction") private String  licenseJurisdiction;
    @XmlAttribute(name = "license-county") private       String  licenseCounty;
    @XmlAttribute(name = "cost-new") private             String  costNew;
    @XmlAttribute(name = "vin") private                  String  vin;
    @XmlAttribute(name = "lookup") private               Boolean lookup;

    @XmlElement(name = "driver") @XmlElementWrapper(name = "drivers") private List<DriverTestData> drivers;

    public Boolean getLookupVehicle() {
        return (lookup == null ? false : lookup);
    }

    public void setLookupVehicle(Boolean lookup) {
        this.lookup = lookup;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getVehicleGroup() {
        return vehicleGroup;
    }

    public void setVehicleGroup(String vehicleGroup) {
        this.vehicleGroup = vehicleGroup;
    }

    public String getGarageType() {
        return garageType;
    }

    public void setGarageType(String garageType) {
        this.garageType = garageType;
    }

    public String getEquipmentValue() {
        return equipmentValue;
    }

    public void setEquipmentValue(String equipmentValue) {
        this.equipmentValue = equipmentValue;
    }

    public String getAnnualMileage() {
        return annualMileage;
    }

    public void setAnnualMileage(String annualMileage) {
        this.annualMileage = annualMileage;
    }

    public String getPrimaryUse() {
        return primaryUse;
    }

    public void setPrimaryUse(String primaryUse) {
        this.primaryUse = primaryUse;
    }

    public String getLicenseJurisdiction() {
        return licenseJurisdiction;
    }

    public void setLicenseJurisdiction(String licenseJurisdiction) {
        this.licenseJurisdiction = licenseJurisdiction;
    }

    public void setLicenseState(String licenseState) {
        this.licenseState = licenseState;
    }

    public void setCostNew(String costNew) {
        this.costNew = costNew;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public void setDrivers(List<DriverTestData> drivers) {
        this.drivers = drivers;
    }

    public String getLicenseState() {
        return licenseState;
    }

    public String getCostNew() {
        return costNew;
    }

    public String getVin() {
        return vin;
    }

    public List<DriverTestData> getDrivers() {
        return drivers;
    }

    public String getManufacturerKey() {
        return manufacturerKey;
    }

    public void setManufacturerKey(String manufacturerKey) {
        this.manufacturerKey = manufacturerKey;
    }

    public String getVehicleKey() {
        return vehicleKey;
    }

    public void setVehicleKey(String vehicleKey) {
        this.vehicleKey = vehicleKey;
    }

    public String getModelYear() {
        return modelYear;
    }

    public void setModelYear(String modelYear) {
        this.modelYear = modelYear;
    }

    public String getLicenseCounty() {
        return licenseCounty;
    }

    public void setLicenseCounty(String licenseCounty) {
        this.licenseCounty = licenseCounty;
    }

    @Override
    public String toString() {
        return "VehicleTestData{" +
                "vin='" + vin + '\'' +
                ", licensePlate='" + licensePlate + '\'' +
                ", vehicleKey='" + vehicleKey + '\'' +
                ", manufacturerKey='" + manufacturerKey + '\'' +
                ", lookup=" + lookup +
                '}';
    }
}
