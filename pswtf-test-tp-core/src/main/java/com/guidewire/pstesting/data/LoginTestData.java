package com.guidewire.pstesting.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * This class represents an application login in a test data XML file.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class LoginTestData {
    @XmlAttribute(name = "application") private String application;
    @XmlAttribute(name = "username") private    String username;
    @XmlAttribute(name = "password") private    String password;
    @XmlAttribute(name = "host") private        String host;
    @XmlAttribute(name = "port") private        String port;
    @XmlAttribute(name = "folder-name") private String folderName;

     public String getApplication() {
        return application;
    }

    public void setApplication(String application) {
        this.application = application;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    @Override
    public String toString() {
        return "LoginTestData{" +
                "application='" + application + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", host='" + host + '\'' +
                ", port='" + port + '\'' +
                ", folderName='" + folderName + '\'' +
                '}';
    }
}

