package com.guidewire.pstesting.config;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class ApplicationConfiguration {
    @XmlAttribute(name = "name") private        String name;
    @XmlAttribute(name = "port") private        String port;
    @XmlAttribute(name = "folder-name") private String folderName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "ApplicationConfiguration{" +
                "name='" + name + '\'' +
                ", port='" + port + '\'' +
                ", folderName='" + folderName + '\'' +
                '}';
    }
}
