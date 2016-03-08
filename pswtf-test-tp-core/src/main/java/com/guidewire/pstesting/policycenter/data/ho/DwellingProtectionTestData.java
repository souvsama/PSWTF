package com.guidewire.pstesting.policycenter.data.ho;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class DwellingProtectionTestData {
    @XmlAttribute(name = "location-type") private               String  locationType;
    @XmlAttribute(name = "fire-extinguisher") private           Boolean fireExtinguisher;
    @XmlAttribute(name = "burglar-alarm") private               Boolean burglarAlarm;
    @XmlAttribute(name = "burglar-alarm-type") private          String  burglarAlarmType;
    @XmlAttribute(name = "fire-alarm-reporting-center") private Boolean fireAlarmReportingCenter;
    @XmlAttribute(name = "smoke-alarms") private                Boolean smokeAlarms;
    @XmlAttribute(name = "smoke-alarms-all-floors") private     Boolean smokeAlarmsAllFloors;
    @XmlAttribute(name = "sprinkler-system-type") private       String  sprinklerSystemType;
    @XmlAttribute(name = "deadbolts") private                   Boolean deadbolts;
    @XmlAttribute(name = "number-of-deadbolts") private         String  numberOfDeadbolts;
    @XmlAttribute(name = "residence-visible") private           Boolean residenceVisible;

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public Boolean getFireExtinguisher() {
        return fireExtinguisher;
    }

    public void setFireExtinguisher(Boolean fireExtinguisher) {
        this.fireExtinguisher = fireExtinguisher;
    }

    public Boolean getBurglarAlarm() {
        return burglarAlarm;
    }

    public void setBurglarAlarm(Boolean burglarAlarm) {
        this.burglarAlarm = burglarAlarm;
    }

    public String getBurglarAlarmType() {
        return burglarAlarmType;
    }

    public void setBurglarAlarmType(String burglarAlarmType) {
        this.burglarAlarmType = burglarAlarmType;
    }

    public Boolean getFireAlarmReportingCenter() {
        return fireAlarmReportingCenter;
    }

    public void setFireAlarmReportingCenter(Boolean fireAlarmReportingCenter) {
        this.fireAlarmReportingCenter = fireAlarmReportingCenter;
    }

    public Boolean getSmokeAlarms() {
        return smokeAlarms;
    }

    public void setSmokeAlarms(Boolean smokeAlarms) {
        this.smokeAlarms = smokeAlarms;
    }

    public Boolean getSmokeAlarmsAllFloors() {
        return smokeAlarmsAllFloors;
    }

    public void setSmokeAlarmsAllFloors(Boolean smokeAlarmsAllFloors) {
        this.smokeAlarmsAllFloors = smokeAlarmsAllFloors;
    }

    public String getSprinklerSystemType() {
        return sprinklerSystemType;
    }

    public void setSprinklerSystemType(String sprinklerSystemType) {
        this.sprinklerSystemType = sprinklerSystemType;
    }

    public Boolean getDeadbolts() {
        return deadbolts;
    }

    public void setDeadbolts(Boolean deadbolts) {
        this.deadbolts = deadbolts;
    }

    public String getNumberOfDeadbolts() {
        return numberOfDeadbolts;
    }

    public void setNumberOfDeadbolts(String numberOfDeadbolts) {
        this.numberOfDeadbolts = numberOfDeadbolts;
    }

    public Boolean getResidenceVisible() {
        return residenceVisible;
    }

    public void setResidenceVisible(Boolean residenceVisible) {
        this.residenceVisible = residenceVisible;
    }
}
