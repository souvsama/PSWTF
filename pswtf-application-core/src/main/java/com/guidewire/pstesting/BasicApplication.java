package com.guidewire.pstesting;

import org.apache.http.HttpStatus;

public abstract class BasicApplication extends ApplicationComponent implements Application {
    private String host;
    private String port;
    private String folderName;

    public BasicApplication(ScreenObjectController controller) {
        super(controller);
    }

    protected BasicApplication(ScreenObjectController controller, String host, String port, String folderName) {
        super(controller);
        this.host = host;
        this.port = port;
        this.folderName = folderName;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
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

    public boolean isAvailable() {
        try {
            return (getController().getResponseCode(getUrl()) == HttpStatus.SC_OK);
        } catch (Exception e) {
            logger.debug("Application not available: " + e);
            return false;
        }
    }

    public String getUrl() {
        StringBuilder builder = new StringBuilder();
        // Schema name specified?
        if (!host.contains("://")) { builder.append("http://"); }
        builder.append(host);
        // Port number specified?
        if (port != null) { builder.append(":").append(port); }
        // Add the path
        builder.append("/").append(folderName);

        return builder.toString();
    }

    public ApplicationPage login(User user) {
        return login(user.getUsername(), user.getPassword());
    }

    public ApplicationPage login(String username, String password) {
        LoginPage loginPage = getLoginPage();
        loginPage.maximize();
        return (loginPage.login(username, password) ? this : loginPage);
    }

    public String replaceResourceVariables(String value) {
        return getController().replaceResourceVariables(value);
    }
}
