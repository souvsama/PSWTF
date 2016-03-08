package com.guidewire.pstesting.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

/**
 * This class represents suite level test data configured in a test data XML file.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class SuiteTestData {
    @XmlAttribute(name = "host") private        String host;
    @XmlAttribute(name = "description") private String description;

    @XmlElement(name = "logins") private LoginsTestData logins;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean hostSpecified() {
        return (host != null);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public boolean loginsExist() {
        return (logins != null && logins.loginsExist());
    }

    public LoginsTestData getLogins() {
        return logins;
    }

    public void setLogins(LoginsTestData logins) {
        this.logins = logins;
    }

    @Override
    public String toString() {
        return "SuiteTestData{" +
                "description='" + description + '\'' +
                ", host='" + host + '\'' +
                '}';
    }
}
