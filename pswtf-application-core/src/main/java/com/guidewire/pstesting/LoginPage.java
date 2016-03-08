package com.guidewire.pstesting;

public interface LoginPage extends ApplicationPage {
    LoginPage waitUntilVisible();

    LoginPage maximize();

    boolean login(String username, String password);
}
