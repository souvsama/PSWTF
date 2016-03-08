package com.guidewire.pstesting.utilities;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

import java.util.concurrent.TimeUnit;

public class JavascriptHelper {
    private WebDriver driver;
    private String script;
    private String result;
    private static final Long SCRIPTTIMEOUT = 10L;

    public JavascriptHelper(WebDriver driver){
        this.driver = driver;
        driver.manage().timeouts().setScriptTimeout(SCRIPTTIMEOUT, TimeUnit.SECONDS);
    }

    public boolean isAjaxDone(){
        //script = "return jQuery.active==0";  //returns true when complete
        script = "return window.getComputedStyle(document.body).cursor";   //returns auto when complete
        result = executeJavascript(script).toString();
        return result.contains("auto");
    }

    public String getPageName(){
        script = "return document.getElementsByClassName(\"g-title\")[0].textContent";
        result = executeJavascript(script).toString();
        return result;
    }

    public String getWorksheetName(){
        script = "var wsheets = document.getElementsByClassName(\"g-title\"); return wsheets[wsheets.length-1].textContent";
        result = executeJavascript(script).toString();
        return result;
    }

    public boolean isLVPagerVisible(String locatorId){
        //look for the input that selects page number
        script = "var obj = document.getElementsByName(\"inputItem\"); return obj[0].disabled;";
        return executeJavascript(script).toString().contains("false");
    }

    public Object executeAsyncJavascript(String script){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeAsyncScript("var callback = arguments[arguments.length - 1];" + script);
    }

    public Object executeJavascript(String script){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        return js.executeScript(script);
    }
}
