package com.guidewire.pstesting;

import org.openqa.selenium.By;

public class SuiteApplication extends BasicApplication {
    /* Resource bundle keys */
    public static final String OK_BUTTON = "button.OK";

    static final By toolsButtonLocator    = By.id(":TabLinkMenuButton-btnEl");
    static final By logoutMenuItemLocator = By.id("TabBar:LogoutTabBarLink-textEl");
    static final By msgDialogLocator = By.id("messagebox-1001");
    static final By msgDialogOKBtnLocator = By.id("button-1005");

    private LoginPage loginPage;

    protected SuiteApplication(ScreenObjectController controller) {
        super(controller);
    }

    public SuiteApplication(ScreenObjectController controller, String host, String port, String folderName) {
        super(controller, host, port, folderName);
    }

    public boolean isVisible() {
        return getController().pageContains(toolsButtonLocator);
    }

    public Application waitUntilVisible() {
        getController().waitUntilPageContains(toolsButtonLocator);
        return this;
    }

    public LoginPage getLoginPage() {
        if (loginPage == null) {
            if (getHost() != null && getPort() != null && getFolderName() != null) {
                loginPage = getLoginPage(getUrl(), toolsButtonLocator);
            } else {
                throw new RuntimeException("Unable to create login page. Host configuration not set.");
            }
        }
        return loginPage;
    }

    public LoginPage getLoginPage(String url, By pageContains) {
        return new SuiteLoginPage(getController(), url, pageContains);
    }

    public LoginPage logout() {
        ScreenObjectController controller = getController();
        if (controller.elementExists(toolsButtonLocator)) {
            controller.clickAndWait(toolsButtonLocator, logoutMenuItemLocator).click(logoutMenuItemLocator);
            // Click the OK button if the dialog is displayed - using try/catch to trap and ignore exception
            try {
                if (controller.elementExists(msgDialogLocator)) {
                    controller.click(msgDialogOKBtnLocator);
                    //controller.click(controller.getElementByResource(OK_BUTTON));  // Click the dialog OK button
                }
            } catch(Exception e) {}
            return getLoginPage().waitUntilVisible();
        }
        return getLoginPage();
    }
}
