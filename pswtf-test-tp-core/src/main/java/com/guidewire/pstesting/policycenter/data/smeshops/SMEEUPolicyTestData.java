package com.guidewire.pstesting.policycenter.data.smeshops;

import com.guidewire.pstesting.policycenter.data.PolicyTestData;
import com.guidewire.pstesting.policycenter.data.ca.BAPolicyInfoTestData;
import com.guidewire.pstesting.policycenter.data.pa.OfferingTestData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import java.util.List;

/**
 * This class represents a SME Shops policy configured in a test data XML file.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SMEEUPolicyTestData extends PolicyTestData {
    @XmlElement(name = "offering") private    OfferingTestData     offering;
    @XmlElement(name = "policy-info") private BAPolicyInfoTestData policyInfo;

    @XmlElement(name = "location") @XmlElementWrapper(name = "locations")
    private List<SMEEULocationTestData> locations;

    public OfferingTestData getOffering() {
        return offering;
    }

    public void setOffering(OfferingTestData offering) {
        this.offering = offering;
    }

    public BAPolicyInfoTestData getPolicyInfo() {
        return policyInfo;
    }

    public void setPolicyInfo(BAPolicyInfoTestData policyInfo) {
        this.policyInfo = policyInfo;
    }

    public List<SMEEULocationTestData> getLocations() {
        return locations;
    }

    public void setLocations(List<SMEEULocationTestData> locations) {
        this.locations = locations;
    }
}
