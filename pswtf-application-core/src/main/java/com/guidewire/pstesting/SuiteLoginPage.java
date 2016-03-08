package com.guidewire.pstesting;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;

/**
 * This class represents the login page of an application.
 */
public class SuiteLoginPage extends ApplicationComponent implements LoginPage {
    static final String BASE_ID = "Login:LoginScreen:LoginDV:";

    public static final By usernameLocator    = By.id(BASE_ID + "username-inputEl");
    public static final By passwordLocator    = By.id(BASE_ID + "password-inputEl");
    public static final By loginButtonLocator = By.id(BASE_ID + "submit-btnInnerEl");

    private String url;
    private By     homePageLocator;   // The locator of an element that exists on the resulting page after login

    public SuiteLoginPage(ScreenObjectController controller, String url, By homePageLocator) {
        super(controller);
        this.url = url;
        this.homePageLocator = homePageLocator;
    }

    @Override
    public SuiteLoginPage maximize() {
        getController().maximize();
        return this;
    }

    public String getUrl() {
        return url;
    }

    public By getHomePageLocator() {
        return homePageLocator;
    }

    public void setHomePageLocator(By homePageLocator) {
        this.homePageLocator = homePageLocator;
    }

    public boolean isVisible() {
        return getController().pageContains(loginButtonLocator);
    }

    public SuiteLoginPage waitUntilVisible() {
        getController().waitUntilPageContains(loginButtonLocator);
        return this;
    }

    public SuiteLoginPage typeUsername(String username) {
        getController().type(usernameLocator, username);
        return this;
    }

    public SuiteLoginPage typePassword(String password) {
        getController().type(passwordLocator, password);
        return this;
    }

    public SuiteLoginPage navigateTo() {
        getController().load(getUrl());
        waitUntilVisible();
        //getController().jQuerify();
        return this;
    }

    public boolean clickLogin() {
        getController().click(loginButtonLocator);
        if (homePageLocator != null) {
            try {
                getController().waitUntilPageContains(homePageLocator);
            } catch (TimeoutException e) {
                logger.warn("Timed out waiting for home page");
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean login(String username, String password) {
        navigateTo();  // Insure that we're on the correct page
        typeUsername(username);
        typePassword(password);
        return clickLogin();
    }
}
