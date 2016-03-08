package com.guidewire.pstesting;


import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class UserMessage {
    private WebDriver driver;
    public String messageType = "Unknown type";
    public String messageText = "[none]";
    static final By iconLocator = By.cssSelector(".message img:only-child");
    static final By messageLocator = By.className("message");

    public UserMessage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isVisible(){
        WebElement msg = null;
        try {
            msg = driver.findElement(messageLocator);
            getMsgType();
            messageText = msg.getText();
        } catch (NoSuchElementException e) {
            // Ignore this
        }
        return (msg !=null);
    }

    private void getMsgType(){
        String iconType = driver.findElement(iconLocator).getAttribute("class");
        messageType = iconType.substring(0,iconType.indexOf("_")).toUpperCase();
    }

}
