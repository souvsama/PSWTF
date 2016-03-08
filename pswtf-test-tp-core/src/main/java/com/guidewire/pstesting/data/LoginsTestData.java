package com.guidewire.pstesting.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class LoginsTestData {
    @XmlElement(name = "login") private List<LoginTestData> logins;

    public boolean loginsExist() {
        return (logins != null && logins.size() > 0);
    }

    public List<LoginTestData> getLogins() {
        return logins;
    }

    public void setLogins(List<LoginTestData> logins) {
        this.logins = logins;
    }
}
