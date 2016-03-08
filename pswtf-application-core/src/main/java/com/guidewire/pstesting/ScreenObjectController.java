package com.guidewire.pstesting;


import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.guidewire.pstesting.utilities.JavascriptHelper;
import org.apache.http.client.fluent.Request;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.openqa.selenium.Keys.ENTER;
import static org.openqa.selenium.Keys.TAB;

public class ScreenObjectController {
    WebDriver driver;
    WebDriverWait wait;
    FluentWait<WebDriver> fluentWait;
    JavascriptHelper jscript;

    public static final String DATEFORMAT = "MM/dd/yyyy";
    public final Logger logger = LoggerFactory.getLogger(getClass());
    private String lastLoggedMessage;
    private String resourceBaseName;
    private String resourceBundleSuffix = "messages";
    private int waitTimeout = 10; // The delay used for wait operations (in seconds)
    private long tryFor           = 5;  // The amount of time to try on a failed operation (in seconds)
    private long waitBetweenTries = 500;  // The amount of time to wait between retries on a failed operation (in milliseconds)
    // Regular expression that matches any characters surrounded by curly braces (e.g. ${state.california})
    public static final Pattern VAR_PATTERN = Pattern.compile("\\{(.*?)}");

    public static final Boolean IGNORE_MISSING_RESOURCE_EXCEPTIONS = true;
    public static final Boolean THROW_MISSING_RESOURCE_EXCEPTIONS  = false;

    private Locale locale = Locale.getDefault();

    /** dynamically load jQuery 1.10.1 */
    public static String getLoadJQuery(){
        String LoadJQuery = "(function(jqueryUrl, callback) {\n" +
                "if (typeof jqueryUrl != 'string') {" +
                "jqueryUrl = 'https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js';\n" +
                "}\n" +
                "if (typeof jQuery == 'undefined') {\n" +
                "var script = document.createElement('script');\n" +
                "var head = document.getElementsByTagName('head')[0];\n" +
                "var done = false;\n" +
                "script.onload = script.onreadystatechange = (function() {\n" +
                "if (!done && (!this.readyState || this.readyState == 'loaded'\n" +
                "|| this.readyState == 'complete')) {\n" +
                "done = true;\n" +
                "script.onload = script.onreadystatechange = null;\n" +
                "head.removeChild(script);\n" +
                "callback();\n" +
                "}\n" +
                "});\n" +
                "script.src = jqueryUrl;\n" +
                "head.appendChild(script);\n" +
                "}\n" +
                "else {\n" +
                "callback();\n" +
                "}\n" +
                "})(arguments[0], arguments[arguments.length - 1]);\n";
        return LoadJQuery;
    }

    public ScreenObjectController(WebDriver driver){
        this.driver = driver;
        wait = new WebDriverWait(driver,10);
        fluentWait = new FluentWait<WebDriver>(driver)
                .withTimeout(10, TimeUnit.SECONDS)
                .pollingEvery(100, TimeUnit.MILLISECONDS)
                .ignoring(java.util.NoSuchElementException.class);
        jscript = new JavascriptHelper(driver);
    }

    /**
     * Injects JQuery javascript into browser
     * @usage Add line to SuiteLoginPage -> navigateTo() method after loading url
     * @return <code>WebDriver</code>
     */
    public ScreenObjectController jQuerify(){
        JavascriptExecutor js = (JavascriptExecutor) driver;
        System.out.println("Converting JS file to a String");
        String jQueryLoader = getLoadJQuery();
        // give jQuery time to load asynchronously
        driver.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        js.executeAsyncScript(jQueryLoader);
        System.out.println("jQuery loaded!");
        return this;
    }

    /**
     * log a message for info
     * @param message
     */
    public void log(String message) {
        logger.info(message);
        lastLoggedMessage = message;
    }

    /**
     * get the current logger
     * @return <code>Logger</code>
     */
    public Logger getLogger(){
        return logger;
    }

    /**
     * Used to invoke screen object methods
     * @return <code>ScreenObjectController</code> instance
     */
    public ScreenObjectController getController(){
        return this;
    }

    /**
     * Get the current webdriver instance
     * @return <code>WebDriver</code>
     */
    public WebDriver getWebDriver(){
        return driver;
    }

    /**
     * Click object using locator
     * @param locator as By
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController click(By locator){
        WebElement element = findScreenObject(locator);
        click(element);
        //focus(element).click(element);
        return this;
    }

    /**
     * Click object using WebElement
     * @param element as WebElement
     * @return  <code>ScreenObjectController</code>
     */
    public ScreenObjectController click(WebElement element){
        element.click();
        //((JavascriptExecutor)driver).executeScript("arguments[0].click();", element);
        return this;
    }

    /**
     * Click WebElement with offset
     * @param element as WebElement
     * @param xOffset as int
     * @param yOffset as int
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController click(WebElement element, int xOffset, int yOffset) {
        Actions builder = new Actions(getWebDriver());
        builder.moveToElement(element, xOffset, yOffset).click().perform();
        return this;
    }

    /**
     * Click WebElement and wait for period of time
     * @param element as WebElement
     * @param timeOutInSeconds as int
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickAndWait(WebElement element, int timeOutInSeconds) {
        return click(element).sleep(timeOutInSeconds * 1000);
    }

    /**
     * Click locator and wait for period of time
     * @param locator as By
     * @param timeOutInSeconds as int
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickAndWait(By locator, int timeOutInSeconds) {
        return click(locator).sleep(timeOutInSeconds * 1000);
    }

    /**
     * Click by locator and wait for presence of text
     * @param locator as By
     * @param pageContains as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickAndWait(By locator, String pageContains) {
        return clickAndWait(locator, pageContains, getWaitTimeout());
    }

    /**
     * Click WebElement and wait for presence of text with timeout
     * @param element as WebElement
     * @param pageContains as String
     * @param timeOutInSeconds as int
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickAndWait(WebElement element, String pageContains, int timeOutInSeconds) {
        click(element);
        // Need to wait?
        if (pageContains != null) {
            waitUntilPageContains(pageContains, timeOutInSeconds);
        }
        return this;
    }

    /**
     * Click By locator and wait for presence of text with timeout
     * @param locator as By
     * @param pageContains as String
     * @param timeOutInSeconds as int
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickAndWait(By locator, String pageContains, int timeOutInSeconds) {
        click(locator);
        // Need to wait?
        if (pageContains != null) {
            waitUntilPageContains(pageContains, timeOutInSeconds);
        }
        return this;
    }

    /**
     * Click By locator and wait for presence of object By locator
     * @param locator as By
     * @param waitForLocator as By
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickAndWait(By locator, By waitForLocator) {
        clickAndWait(locator, waitForLocator, getWaitTimeout());
        return this;
    }

    /**
     * Click By locator and wait for presence of object By locator with timeout
     * @param locator as By
     * @param waitForLocator as By
     * @param timeOutInSeconds as int
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickAndWait(By locator, By waitForLocator, int timeOutInSeconds) {
        click(locator);
        waitUntilPageContains(waitForLocator, timeOutInSeconds);
        return this;
    }

    /**
     * Click WebElement and wait for object By locator
     * @param element as WebElement
     * @param waitForLocator as By
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickAndWait(WebElement element, By waitForLocator) {
        clickAndWait(element, waitForLocator, getWaitTimeout());
        return this;
    }

    /**
     * Click WebElement and wait for object By locator with timeout
     * @param element as WebElement
     * @param waitForLocator as By
     * @param timeOutInSeconds as int
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickAndWait(WebElement element, By waitForLocator, int timeOutInSeconds) {
        click(element).waitUntilPageContains(waitForLocator, timeOutInSeconds);
        return this;
    }

    /**
     * Click WebElement and do fluent wait for object By locator
     * @param element as WebElement
     * @param waitForLocator as By
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickAndWaitFluently(WebElement element, By waitForLocator){
        click(element).fluentWait.until(ExpectedConditions.elementToBeClickable(waitForLocator));
        return this;
    }

    /**
     * Click right edge of WebElement
     * @param element as WebElement
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickRightEdge(WebElement element) {
        Dimension size = element.getSize();
        click(element, size.width - 1, size.height / 2);
        return this;
    }

    /**
     * Click right edge of By locator
     * @param locator as By
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickRightEdge(final By locator) {
        createIgnoreStaleElementWait().
                until(new Predicate<WebDriver>() {
                    public boolean apply(WebDriver d) {
                        WebElement element = findScreenObject(locator);
                        Dimension size = element.getSize();
                        click(element, size.width - 1, size.height / 2);
                        return true;
                    }
                });
        return this;
    }

    /**
     * Click right edge of WebElement and wait for object By locator
     * @param element as WebElement
     * @param waitForLocator as By
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickRightEdgeAndWait(WebElement element, By waitForLocator){
        clickRightEdge(element).wait.until(ExpectedConditions.presenceOfElementLocated(waitForLocator));
        return this;
    }

    /**
     * Click right edge of By locator and wait for object By locator
     * @param locator as By
     * @param waitForLocator as By
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickRightEdgeAndWait(By locator, By waitForLocator) {
        clickRightEdgeAndWait(locator, waitForLocator, getWaitTimeout());
        return this;
    }

    /**
     * Click right edge of By locator and wait until page contains text
     * @param locator as By
     * @param pageContains as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickRightEdgeAndWait(By locator, String pageContains) {
        clickRightEdgeAndWait(locator, pageContains, getWaitTimeout());
        return this;
    }

    /**
     * Click right edge of By locator and wait until page contains text with timeout
     * @param locator as By
     * @param pageContains as String
     * @param timeOutInSeconds as int
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickRightEdgeAndWait(By locator, String pageContains, int timeOutInSeconds) {
        clickRightEdge(locator);
        // Need to wait?
        if (pageContains != null) {
            waitUntilPageContains(pageContains, timeOutInSeconds);
        }
        return this;
    }

    /**
     * Click right edge of By locator and wait for object By locator with timeout
     * @param locator as By
     * @param waitForLocator as By
     * @param timeOutInSeconds as int
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickRightEdgeAndWait(By locator, By waitForLocator, int timeOutInSeconds) {
        clickRightEdge(locator);
        // Need to wait?
        if (waitForLocator != null) {
            waitUntilPageContains(waitForLocator, timeOutInSeconds);
        }
        return this;
    }

    /**
     * Double click on WebElement
     * @param element as WebElement
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController doubleClick(WebElement element){
        new Actions(driver).doubleClick(element);
        return this;
    }

    /**
     * Put focus on WebElement
     * @param element as WebElement
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController focus(WebElement element) {
        if ("input".equals(element.getTagName())) {
            element.sendKeys("");
        } else {
            new Actions(driver).moveToElement(element).perform();
        }
        return this;
    }

    /**
     * Conduct sleep for period of time
     * @param timeInMilliseconds as int
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController sleep(int timeInMilliseconds) {
        if (timeInMilliseconds > 0) {
            try {
                Thread.sleep(timeInMilliseconds);
            } catch (InterruptedException e) {
                // Ignore
            }
        }
        return this;
    }

    /**
     * Put focus on object By locator
     * @param locator as By
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController focus(final By locator) {
        createIgnoreStaleElementWait().
                until(new Predicate<WebDriver>() {
                    public boolean apply(WebDriver d) {
                        focus(findScreenObject(locator));
                        return true;
                    }
                });
        return this;
    }

    /**
     * Clear value in object By locator
     * @param locator as By
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clear(final By locator) {
        createIgnoreStaleElementWait().
                until(new Predicate<WebDriver>() {
                    public boolean apply(WebDriver d) {
                        clear(findScreenObject(locator));
                        return true;
                    }
                });
        return this;
    }

    /**
     * Clear value in WebElement
     * @param element as WebElement
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clear(final WebElement element) {
        createWait(getTryFor()).
                until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver input) {
                        element.clear();
                        String value = getValue(element);
                        return (value == null || value.isEmpty());
                    }
                });
        return this;
    }

    /**
     * Set the value in a dropdown field By locator
     * @param locator as By
     * @param value as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController setDropDownField(By locator, String value){
        WebElement element = findScreenObject(locator);
        return setDropDownField(element,value);
    }

    /**
     * Set the value in a dropdown field WebElement
     * @param element as WebElement
     * @param value as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController setDropDownField(WebElement element, String value){
        element.click();
        element.clear();
        element.sendKeys(replaceResourceVariables(value));
        element.click();
        pressTab();
        return this;
    }

    /**
     * Set value in a text field By locator
     * @param locator as By
     * @param value as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController setTextField(By locator, String value){
        WebElement element = findScreenObject(locator);
        return setTextField(element,value);
    }

    /**
     * Set value in a text field WebElement
     * @param element as WebElement
     * @param value as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController setTextField(WebElement element, String value){
        element.click();
        element.clear();
        element.sendKeys(replaceResourceVariables(value));
        return this;
    }

    /**
     * Set text value in By locator and press tab to invoke update
     * @param locator as By
     * @param value as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController setTextAndTab(By locator, String value){
        WebElement element = findScreenObject(locator);
        if (element.isEnabled()) {
            return setTextAndTab(element,value);
        }
        return this;
    }

    /**
     * Set text value in WebElement and press tab to invoke update
     * @param element as WebElement
     * @param value as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController setTextAndTab(WebElement element, String value){
        element.click();
        element.clear();
        element.sendKeys(replaceResourceVariables(value));
        pressTab();
        waitUntilUpdateDone();
        return this;
    }

    /**
     * Set text in By locator and press enter to invoke update
     * @param locator as By
     * @param value as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController setTextAndEnter(By locator, String value){
        WebElement element = findScreenObject(locator);
        if (element.isEnabled()) {
            return setTextAndEnter(element,value);
        }
        return this;
    }

    /**
     * Set text in WebElement and press enter to invoke update
     * @param element as WebElement
     * @param value as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController setTextAndEnter(WebElement element, String value){
        element.click();
        element.clear();
        element.sendKeys(replaceResourceVariables(value));
        pressEnter();
        waitUntilUpdateDone();
        return this;
    }

    /**
     * Type text as from keyboard
     * @param text as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController typeText(String text){
        Actions builder = new Actions(driver);
        builder.sendKeys(text).perform();
        return this;
    }

    /**
     * Type keyboard text that may contain resource variables
     * @param text as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController type(String text) {
        Actions builder = new Actions(getWebDriver());
        builder.sendKeys(replaceResourceVariables(text)).perform();
        return this;
    }

    /**
     * Type keyboard text in a field BY locator
     * @param locator as By
     * @param text as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController type(final By locator, String text) {
        if (text != null) {
            final String typeText = replaceResourceVariables(text);
            createIgnoreStaleElementWait().
                    until(new Predicate<WebDriver>() {
                        public boolean apply(WebDriver d) {
                            WebElement element = findScreenObject(locator);
                            focus(element).clear(element).type(element, typeText);
                            // Did the value actually get set? If not, try again
                            return (typeText.equals(getValue(element)));
                        }
                    });
        }
        return this;
    }

    /**
     * Type keyboard text that may contain a resource variable in a WebElement field
     * @param element as WebElement
     * @param text as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController type(final WebElement element, String text) {
        element.sendKeys(replaceResourceVariables(text));
        return this;
    }

    /**
     * Type keyboard text in a field By locator then press tab to invoke update
     * @param locator as By
     * @param text as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController typeAndTab(By locator, String text) {
        if (text != null) {
            type(locator, text).pressTab().waitUntilUpdateDone();
        }
        return this;
    }

    /**
     * Type keyboard text and press enter
     * @param text as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController typeAndEnter(String text) {
        type(text).pressEnter().waitUntilUpdateDone();
        return this;
    }

    /**
     * Type keyboard text in a field BY locator and press enter
     * @param locator as By
     * @param text as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController typeAndEnter(final By locator, final String text) {
        if (text != null) {
            type(locator, text).pressEnter().waitUntilUpdateDone();
        }
        return this;
    }

    /**
     * Type keyboard text in a WebElement field and press enter to invoke update
     * @param element as WebElement
     * @param text as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController typeAndEnter(WebElement element, String text) {
        return type(element, text).pressEnter().waitUntilUpdateDone();
    }

    /**
     * Click on a field By locator and type keyboard text
     * @param locator as By
     * @param text as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickAndType(final By locator, final String text) {
        if (text != null) {
            click(locator).type(locator, text).waitUntilUpdateDone();
        }
        return this;
    }

    /**
     * Click on a WebElement field and type keyboard text
     * @param element as WebElement
     * @param text as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickAndType(WebElement element, String text) {
        click(element).type(element, text).waitUntilUpdateDone();
        return this;
    }

    /**
     * Click on a field By locator, type keyboard text and press tab
     * @param locator as By
     * @param text as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickTypeAndTab(By locator, String text) {
        if (text != null) {
            click(locator).type(locator, text).pressTab().waitUntilUpdateDone();
        }
        return this;
    }

    /**
     * Click on a WebElement field, type keyboard text and press tab
     * @param element as WebElement
     * @param text as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickTypeAndTab(WebElement element, String text) {
        if (text != null) {
            click(element).type(element, text).pressTab().waitUntilUpdateDone();
        }
        return this;
    }

    /**
     * Click on a field By locator, type keyboard text and press enter
     * @param locator as By
     * @param text as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickTypeAndEnter(final By locator, final String text) {
        if (text != null) {
            click(locator).type(locator, text).pressEnter().waitUntilUpdateDone();
        }
        return this;
    }

    /**
     * Click on a WebElement field, type keyboard text and press enter
     * @param element as WebElement
     * @param text as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController clickTypeAndEnter(WebElement element, String text) {
        return clickAndType(element, text).pressEnter().waitUntilUpdateDone();
    }

    /**
     * Invoke event by mouse hover over By locator
     * @param locator as By
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController hoverOver(By locator) {
        WebElement targetElement = findScreenObject(locator);
        if (targetElement != null) {
            JavascriptExecutor js = (JavascriptExecutor)getWebDriver();
            String mouseOverScript = "if(document.createEvent){" +
                    "var evObj = document.createEvent('MouseEvents');" +
                    "evObj.initEvent('mouseover', true, false);" +
                    "arguments[0].dispatchEvent(evObj);}" +
                    "else if(document.createEventObject)" +
                    "{arguments[0].fireEvent('onmouseover');}";
            js.executeScript(mouseOverScript, targetElement);
            sleep(250);
        }
        return this;
    }

    /**
     * Mouse hover over an object By locator and wait for presence of text
     * @param locator as By
     * @param pageContains as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController hoverOverAndWait(By locator, String pageContains) {
        return hoverOverAndWait(locator, pageContains, getWaitTimeout());
    }

    /**
     * Mouse hover over an object By locator and wait for presence of text with timeout
     * @param locator as By
     * @param pageContains as String
     * @param timeOutInSeconds as int
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController hoverOverAndWait(By locator, String pageContains, int timeOutInSeconds) {
        hoverOver(locator);
        // Need to wait?
        if (pageContains != null) {
            waitUntilPageContains(pageContains, timeOutInSeconds);
        }
        return this;
    }

    /**
     * Mouse hover over an object By locator and wait for presence of object By locator
     * @param locator as By
     * @param waitForLocator as By
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController hoverOverAndWait(By locator, By waitForLocator) {
        return hoverOverAndWait(locator, waitForLocator, getWaitTimeout());
    }

    /**
     * Mouse hover over an object By locator and wait for presence of object By locator with timeout
     * @param locator as By
     * @param waitForLocator as By
     * @param timeOutInSeconds as int
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController hoverOverAndWait(By locator, By waitForLocator, int timeOutInSeconds) {
        hoverOver(locator).waitUntilPageContains(waitForLocator, timeOutInSeconds);
        return this;
    }

    /**
     * Set text value of a field By locator
     * @param locator as By
     * @param text as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController setValue(final By locator, String text) {
        if (text != null) {
            final String typeText = replaceResourceVariables(text);
            createIgnoreStaleElementWait().
                    until(new Predicate<WebDriver>() {
                        public boolean apply(WebDriver d) {
                            WebElement element = findScreenObject(locator);
                            setValue(findScreenObject(locator), typeText);
                            // Did the value actually get set? If not, try again
                            return (typeText.equals(getValue(element)));
                        }
                    });
        }
        return this;
    }

    /**
     * Set text value of a WebElement field
     * @param element as WebElement
     * @param text as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController setValue(WebElement element, String text) {
        ((JavascriptExecutor)getWebDriver()).executeScript("arguments[0].value='" +
                replaceResourceVariables(text) +
                "';", element);
        return this;
    }

    /**
     * Get the value of a field By locator
     * @param locator as By
     * @return String
     */
    public String getValue(By locator) {
        return getValue(findScreenObject(locator));
    }

    /**
     * Get the value of a field WebElement
     * @param element as WebElement
     * @return String
     */
    public String getValue(WebElement element) {
        return element.getAttribute("value");
    }

    /**
     * Get checked status of a standalone checkbox by locator
     * @param locator as By
     * @return <code>true</code> if checked ; <code>false</code> if not
     */
    public boolean isCboChecked(By locator){
        return isCboChecked(findScreenObject(locator));
    }

    /**
     * Get selected status of a standalone radio button by locator
     * @param locator as By
     * @return <code>true</code> if selected ; <code>false</code> if not
     */
    public boolean isRadioBtnSelected(By locator){
        return isCboChecked(findScreenObject(locator));
    }

    /**
     * Get checked status of a standalone checkbox
     * @param element as WebElement
     * @return <code>true</code> if checked ; <code>false</code> if not
     */
    public boolean isCboChecked(WebElement element){
        WebElement tableElement = element.findElement(By.xpath("ancestor::table[contains(@class, \"x-form-type-checkbox\")]"));
        String tableAttr = tableElement.getAttribute("class");
        return tableAttr.contains("x-form-cb-checked");
    }

    /**
     * Convert resource variable to a string
     * @param value as String
     * @return String
     */
    public String replaceResourceVariables(String value) {
        Matcher matcher = VAR_PATTERN.matcher(value);
        while (matcher.find()) {
            for (int i = 1; i <= matcher.groupCount(); i++) {
                String var = matcher.group(i);
                String resourceString = getString(var);
                if (resourceString != null) {
                    value = value.replace("${" + var + "}", replaceResourceVariables(resourceString));
                }
            }
        }
        return value;
    }

    /**
     * Get the text in an object By locator
     * @param locator as By
     * @return String
     */
    public String getText(final By locator) {
        return createIgnoreStaleElementWait().
                until(new Function<WebDriver, String>() {
                    public String apply(WebDriver driver) {
                        return findScreenObject(locator).getText();
                    }
                });
    }

    /**
     * Is the object By locator displayed
     * @param locator as By
     * @return <code>true</code> if object displayed, <code>false</code> if not
     */
    public boolean isElementAvailable(final By locator){
        return fluentWait.until(ExpectedConditions.presenceOfElementLocated(locator)).isDisplayed();
    }

    /**
     * Simulate pressing the Down arrow key
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController pressDown() {
        Actions builder = new Actions(getWebDriver());
        builder.sendKeys(Keys.ARROW_DOWN).perform();
        return this;
    }

    /**
     * Simulate scrolling to bottom of screen
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController scrollToBottom() {
        Actions builder = new Actions(getWebDriver());
        builder.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
        return this;
    }

    /**
     * Simulate pressing tab key
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController pressTab(){
        Actions builder = new Actions(driver);
        builder.sendKeys(TAB).perform();
        return this;
    }

    /**
     * Simulate pressing enter key
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController pressEnter(){
        Actions builder = new Actions(driver);
        builder.sendKeys(ENTER).perform();
        return this;
    }

    /**
     * Fires the onchange event for the object By locator and waits for update to complete
     * @param locator as By
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController fireOnChangeEvent(By locator){
        String script = "Test.fireChangeEvent('" + locator + "')";
        jscript.executeJavascript(script);
        waitUntilUpdateDone();
        return this;
    }

    /**
     * Validates date format according to configured and enters into field By locator
     * @param locator as By
     * @param date as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController setDateField(By locator, String date){
        WebElement element = findScreenObject(locator);
        if (isValidDateFormat(DATEFORMAT, date)) {
            element.clear();
            element.click();
            element.sendKeys(date);
        } else {
            logger.warn("Incorrect date format used");
        }
        return this;
    }

    /**
     * Validates date format with configured and enters into WebElement field
     * @param element as WebElement
     * @param date as String
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController setDateField(WebElement element, String date){
        if (isValidDateFormat(DATEFORMAT, date)) {
            element.clear();
            element.click();
            element.sendKeys(date);
        } else {
            logger.warn("Incorrect date format used");
        }
        return this;
    }

    /**
     * Verifies the correct date format is being used
     * @param format
     * @param value
     * @return <code>true</code> if the date formatted correctly; <code>false</code> otherwise
     */
    public static boolean isValidDateFormat(String format, String value) {
        Date date = null;
        try {
            date = new SimpleDateFormat(format).parse(value);
        } catch (ParseException ex) {
            // Ignore exception
        }
        return date != null;
    }

    /**
     * Finds a screen object once it becomes present on the screen
     * @param locator     used to locate the object
     * @return     element detected
     */
    public WebElement findScreenObject(By locator){
        return wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Find an element having specific text
     * @param searchLocator search locator criteria
     * @param text item text
     * @return WebElement
     */
    public WebElement findElementHavingText(By searchLocator, String text){
        List <WebElement> items = findElements(searchLocator);
        WebElement result = null;
        for (WebElement item: items){
            if (item.getText() == text){ result = item;}
        }
        return result;
    }

    /**
     * Find all elements within the current page.
     * @param locator the locator of the elements to search for
     * @return A list of all <code>WebElement</code>s, or an empty list if nothing found
     */
    public List<WebElement> findElements(By locator) {
        return getWebDriver().findElements(locator);
    }

    /**
     * Get an element using resource variable key
     * @param key Resource variable name
     * @return WebElement
     */
    public WebElement getElementByResource(String key) {
        String value = getString(key);
        if (value != null) {
            StringBuilder builder = new StringBuilder();
            builder.append("//*[text()=\"").append(value).append("\"]");
            return findScreenObject(By.xpath(builder.toString()));
        }
        return null;
    }


    /**
     * Existence of element by locator using findElements array to suppress 'not found' exception
     * @param locator element locator
     * @return true / false - exists / doesn't exist
     */
    public boolean elementExists(By locator) {
        return findElements(locator).size() > 0;
    }

    /**
     * Indicates if an element exists on the page. An element exists if it
     * can be found on the page.
     * @param key the resource bundle key of the element to check
     * @return <code>true</code> if the element exists; <code>false</code> otherwise
     */
    public boolean elementExistsByResource(String key) {
        String value = getString(key);
        if (value != null) {
            StringBuilder builder = new StringBuilder();
            builder.append("//*[text()=\"").append(value).append("\"]");
            return isElementAvailable(By.xpath(builder.toString()));
        }
        return false;
    }

    /**
     * Get the enabled status of an object By locator
     * @param locator as By
     * @return <code>true</code> if enabled, <code>false</code> if otherwise
     */
    public boolean isEnabled(By locator){
        return findScreenObject(locator).isEnabled();
    }

    /**
     * Checks for the presence of user error messages on the page. Also logs warnings
     * @return String containing message type and text or empty string
     */
    public String checkForErrors() {
        UserMessage userMsg = new UserMessage(driver);
        if (userMsg.isVisible()){
            return(userMsg.messageType + ": " + userMsg.messageText);
        } else {
            return ""; //findScreenObject(By.className("g-title")).getText();
        }
    }

    /**
     * Sends the appropriate key strokes to select all.
     */
    public ScreenObjectController selectAll() {
        if (logger.isDebugEnabled()) { logger.debug("Selecting all"); }
        Actions builder = new Actions(getWebDriver());
        builder.sendKeys(Keys.chord(Keys.CONTROL, "a")).perform();
        return this;
    }

    /**
     * Select all Listview rows via the header row checkbox
     * @param lvLocator  Listview as By locator
     * @return  <code>ScreenObjectController</code>
     */
    public ScreenObjectController selectAllRowsCheckbox(By lvLocator){
        WebElement lvElement = findScreenObject(lvLocator);
        return selectAllRowsCheckbox(lvElement);
    }

    /**
     * Select all Listview rows via the header row checkbox
     * @param lvElement - Listview as WebElement
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController selectAllRowsCheckbox (WebElement lvElement){
        WebElement selectAllElement = lvElement.findElement(By.cssSelector(".x-grid-checkcolumn"));
        clickAndWait(selectAllElement, 1);
        return this;
    }

    /**
     * Returns the table row element that contains the specified text.
     *
     * @param locator the table element's locator
     * @param text    the text to search for
     *
     * @return the <code>WebElement</code> representing the located table row or <tt>null</tt> if not found.
     */
    public WebElement findRow(final By locator, final String text) {
        // Create search locator
        StringBuilder builder = new StringBuilder();
        builder.append("descendant::*[text()=\"").append(replaceResourceVariables(text)).append("\"]");
        final By searchBy = By.xpath(builder.toString());
        // Search the table
        return createIgnoreStaleElementWait().
                until(new Function<WebDriver, WebElement>() {
                    public WebElement apply(WebDriver driver) {
                        WebElement table = findScreenObject(locator);
                        List<WebElement> rows = table.findElements(By.tagName("tr"));
                        for (WebElement element : rows) {
                            if (element.findElements(searchBy).size() > 0) {
                                return element;
                            }
                        }
                        return null;
                    }
                });
    }

    /**
     * Returns the index of the table row that contains the specified text.
     *
     * @param locator the table element's locator
     * @param text    the text to search for
     *
     * @return the row index or -1 if not found.
     */
    public int findRowIndex(final By locator, String text) {
        // Create search locator
        StringBuilder builder = new StringBuilder();
        builder.append("descendant::*[text()=\"").append(replaceResourceVariables(text)).append("\"]");
        final By searchBy = By.xpath(builder.toString());
        // Search the table
        return createIgnoreStaleElementWait().
                until(new Function<WebDriver, Integer>() {
                    public Integer apply(WebDriver driver) {
                        int x = 0;
                        WebElement table = findScreenObject(locator);
                        List<WebElement> rows = table.findElements(By.tagName("tr"));
                        for (WebElement element : rows) {
                            if (element.findElements(searchBy).size() > 0) {
                                return x;
                            }
                            x++;
                        }
                        return -1;
                    }
                });
    }

    /**
     * Returns the index of the table column that contains the specified header text.
     *
     * @param locator the table element's locator
     * @param text    the text to search for
     *
     * @return the column index or -1 if not found.
     */
    public int findColumnIndex(final By locator, String text) {
        // Create search locator
        StringBuilder builder = new StringBuilder();
        builder.append("descendant::*[text()=\"").append(replaceResourceVariables(text)).append("\"]");
        final By searchBy = By.xpath(builder.toString());
        // Search the table
        return createIgnoreStaleElementWait().
                until(new Function<WebDriver, Integer>() {
                    public Integer apply(WebDriver driver) {
                        int x = 0;
                        WebElement table = findScreenObject(locator);
                        //find visible columns
                        List<WebElement> cols = table.findElements(By.cssSelector(".x-column-header:not([style*='display: none']"));
                        for (WebElement element : cols) {
                            if (element.findElements(searchBy).size() > 0) {
                                return x;
                            }
                            x++;
                        }
                        return -1;
                    }
                });
    }


    /**
     * Find an element using its text, in a list object By locator
     * @param locator as By
     * @param text as String
     * @return WebElement containing the list item
     */
    public WebElement findListItem(final By locator, final String text) {
        // Create search locator
        StringBuilder builder = new StringBuilder();
        builder.append("descendant::li[text()=\"").append(replaceResourceVariables(text)).append("\"]");
        final By searchBy = By.xpath(builder.toString());
        // Search the list
        return createIgnoreStaleElementWait().
                until(new Function<WebDriver, WebElement>() {
                    public WebElement apply(WebDriver driver) {
                        WebElement list = findScreenObject(locator);
                        return (list == null ? null : list.findElement(searchBy));
                    }
                });
    }

    /**
     * Checks for completion of partial page update
     * @return     current page (used for chaining methods)
     */
    public ScreenObjectController waitUntilUpdateDone(){
        wait.until(new ExpectedCondition<Boolean>() {
            @Override
            public Boolean apply(WebDriver webDriver) {
                sleep(200);   // Allow change to start
                while (!(jscript.isAjaxDone())){
                    sleep(50);
                }
                return jscript.isAjaxDone();         //loop until true
            }
        });
        return this;
    }

    /**
     * Checks for a specific element By locator on the page
     * @param locator as By
     * @return <code>true</code> if exists on page, <code>false</code> if not
     */
    public boolean pageContains(By locator) {
        return elementExists(locator);
    }


    /**
     * Checks for presence of specific text on a page
     * @param text as String
     * @return <code>true</code> if exists on page, <code>false</code> if not
     */
    public boolean pageContains(String text) {
        return getWebDriver().getPageSource().contains(text);
    }

    /**
     * Wait until the page contains a specific By locator
     * @param locator as By
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController waitUntilPageContains(final By locator) {
        return waitUntilPageContains(locator, getWaitTimeout());
    }

    /**
     * Wait until page contains a specific By locator with timeout
     * @param locator as By
     * @param timeOutInSeconds as int
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController waitUntilPageContains(final By locator, int timeOutInSeconds) {
        createWait(timeOutInSeconds).
                until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver input) {
                        return input.findElements(locator).size() > 0;
                    }
                });
        return this;
    }

    /**
     * Wait until a page contains a collection of objects with timeout
     * @param timeOutInSeconds as int
     * @param locators as Object...
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController waitUntilPageContains(int timeOutInSeconds, final Object... locators) {
        return waitUntilPageContains(locators, timeOutInSeconds);
    }

    /**
     * Wait until page contains specific By locators with timeout
     * @param locators as Object collection
     * @param timeOutInSeconds as int
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController waitUntilPageContains(final Object[] locators, int timeOutInSeconds) {
        createWait(timeOutInSeconds).
                until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver input) {
                        for (Object locator : locators) {
                            if (By.class.isInstance(locator) && input.findElements((By)locator).size() > 0 ||
                                    input.getPageSource().contains(locator.toString())) {
                                return true;
                            }
                        }
                        return false;
                    }
                });
        return this;
    }

    /**
     * Wait until page contains a specific character sequence
     * @param text as CharSequence
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController waitUntilPageContains(final CharSequence text) {
        return waitUntilPageContains(text, getWaitTimeout());
    }

    /**
     * Wait until page contains a specific character sequence with timeout
     * @param text as CharSequence
     * @param timeOutInSeconds as int
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController waitUntilPageContains(final CharSequence text, int timeOutInSeconds) {
        createWait(timeOutInSeconds).
                until(new ExpectedCondition<Boolean>() {
                    public Boolean apply(WebDriver input) {
                        return input.getPageSource().contains(text);
                    }
                });
        return this;
    }

    /**
     * Wait for the presence of a By locator using fluent wait
     * @param locator as By
     */
    public void waitForElement(By locator) {
        createWait(getWaitTimeout()).
                until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    /**
     * Wait for a specified time using fluent wait
     * @param timeOutInSeconds as long
     * @return new WebDriverWait
     */
    private WebDriverWait createWait(long timeOutInSeconds) {
        return new WebDriverWait(getWebDriver(), timeOutInSeconds);
    }

    /**
     * Get configured wait timeout
     * @return int timeout
     */
    public int getWaitTimeout() {
        return waitTimeout;
    }

    /**
     * Create fluent wait that ignores stale element exception
     * @return new FluentWait
     */
    private FluentWait<WebDriver> createIgnoreStaleElementWait() {
        return new FluentWait<>(getWebDriver())
                .withTimeout(getTryFor(), TimeUnit.SECONDS)
                .pollingEvery(getWaitBetweenTries(), TimeUnit.MILLISECONDS)
                .ignoring(StaleElementReferenceException.class);
    }

    /**
     * Create fluent wait that ignores an illegal state exception
     * @return new FluentWait
     */
    private FluentWait<WebDriver> createIgnoreIllegalStateWait(){
        return new FluentWait<>(getWebDriver())
                .withTimeout(getTryFor(), TimeUnit.SECONDS)
                .pollingEvery(getWaitBetweenTries(), TimeUnit.MILLISECONDS)
                .ignoring(IllegalStateException.class);
    }

    /**
     * Returns the amount of time to try on a failed operation.
     *
     * @return the amount of time to try on a failed operation (in seconds)
     */
    public long getTryFor() {
        return tryFor;
    }

    /**
     * Sets the amount of time to try on a failed operation.
     *
     * @param tryForInSeconds the amount of time to try on a failed operation (in seconds)
     */
    public void setTryFor(long tryForInSeconds) {
        this.tryFor = tryForInSeconds;
    }

    /**
     * Returns the amount of time to wait between retries on a failed operation.
     *
     * @return the amount of time to wait between retries on a failed operation (in seconds)
     */
    public long getWaitBetweenTries() {
        return waitBetweenTries;
    }

    /**
     * Sets the amount of time to wait between retries on a failed operation.
     *
     * @param waitInMilliSeconds the amount of time to wait between retries on a failed operation (in milliseconds)
     */
    public void setWaitBetweenTries(long waitInMilliSeconds) {
        this.waitBetweenTries = waitInMilliSeconds;
    }

    /**
     * Load specified page url
     * @param url as String
     */
    public void load(String url) {
        getWebDriver().get(url);
    }

    /**
     * Maximize browser window
     * @return <code>ScreenObjectController</code>
     */
    public ScreenObjectController maximize() {
        getWebDriver().manage().window().maximize();
        return this;
    }

    /**
     * Get browser response code for specified url
     * @param url as String
     * @return int response code
     */
    public int getResponseCode(String url) {
        try {
            return Request.Get(url).execute().returnResponse().getStatusLine().getStatusCode();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Returns the base name of the resource bundle
     *
     * @return the base name of the resource bundle, a fully qualified class name
     */
    public String getResourceBaseName() {
        return resourceBaseName;
    }

    /**
     * Set the base name of the resource bundle
     *
     * @param resourceBaseName a fully qualified class name representing the base
     *                         name of the resource bundle
     */
    public void setResourceBaseName(String resourceBaseName) {
        this.resourceBaseName = resourceBaseName;
    }

    /**
     * Returns the resource bundle base name suffix.
     *
     * @return the resource bundle base name suffix.
     */
    public String getResourceBundleSuffix() {
        return resourceBundleSuffix;
    }

    /**
     * Get configured locale
     * @return Locale
     */
    public Locale getLocale() {
        return locale;
    }

    /**
     * Set locale vale
     * @param locale as Locale
     */
    public void setLocale(Locale locale) {
        this.locale = locale;
    }

    /**
     * Returns a string for the given key from the resource bundle or one of its parents.
     *
     * @param key the key for the desired string
     *
     * @return the string for the given key
     */
    public String getString(String key) {
        return getString(getResourceBaseName(), key);
    }

    /**
     * Returns a string for the given key from the resource bundle or one of its parents.
     *
     * @param baseName the base name of the resource bundle, a fully qualified class name
     * @param key      the key for the desired string
     *
     * @return the string for the given key
     */
    public String getString(String baseName, String key) {
        while (true) {
            String value = getString(baseName, key, IGNORE_MISSING_RESOURCE_EXCEPTIONS);
            if (value != null) {
                return value;
            }
            int lastIndex = baseName.lastIndexOf(".");
            if (lastIndex == -1) {
                break;
            }
            baseName = baseName.substring(0, lastIndex);
        }
        return getString(baseName, key, THROW_MISSING_RESOURCE_EXCEPTIONS);
    }

    /**
     * Returns a string for the given key from the resource bundle or one of its parents.
     *
     * @param baseName                       the base name of the resource bundle, a fully qualified class name
     * @param key                            the key for the desired string
     * @param ignoreMissingResourceException if <code>true</code>, a <code>MissingResourceException</code> will be ignored
     *
     * @return the string for the given key or <code>null</code> if not located
     */
    protected String getString(String baseName, String key, boolean ignoreMissingResourceException) {
        try {
            String bundleSuffix = getResourceBundleSuffix();
            return ResourceBundle.getBundle(baseName + "." + bundleSuffix, getLocale()).getString(key);
        } catch (MissingResourceException e) {
            if (!ignoreMissingResourceException) {
                throw e;
            }
        }
        return null;
    }
}
