package com.guidewire.pstesting;

public interface Application extends ApplicationPage {
    boolean isAvailable();

    boolean isVisible();

    Application waitUntilVisible();

    String getUrl();

    String getHost();

    void setHost(String host);

    String getPort();

    void setPort(String port);

    String getFolderName();

    void setFolderName(String folderName);

    LoginPage getLoginPage();

    LoginPage logout();

    String replaceResourceVariables(String value);
}

