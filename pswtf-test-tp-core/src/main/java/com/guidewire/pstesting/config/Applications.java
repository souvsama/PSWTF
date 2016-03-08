package com.guidewire.pstesting.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "applications")
@XmlAccessorType(XmlAccessType.FIELD)
public class Applications {
    @XmlElement(name = "application") private List<ApplicationConfiguration> configurations;

    public List<ApplicationConfiguration> getConfigurations() {
        return configurations;
    }

    public void setConfigurations(List<ApplicationConfiguration> configurations) {
        this.configurations = configurations;
    }
}
