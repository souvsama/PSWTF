package com.guidewire.pstesting.data;

import com.guidewire.pstesting.User;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * This class represents an application user in a test data XML file that
 * tests are performed against.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class UserTestData implements User {
    @XmlAttribute(name = "username") private String username;
    @XmlAttribute(name = "password") private String password;

    public UserTestData() {
    }

    public UserTestData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "UserData{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
