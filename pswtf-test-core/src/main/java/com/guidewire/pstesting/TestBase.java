package com.guidewire.pstesting;


import com.google.common.base.Strings;
import com.guidewire.pstesting.testng.ScreenShotOnFailureTestListener;
import com.guidewire.pstesting.webdriver.Driver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;

import javax.xml.bind.JAXBException;
import java.text.SimpleDateFormat;
import java.util.Locale;

@Listeners(ScreenShotOnFailureTestListener.class)
public abstract class TestBase {
    public static final String DEFAULT_DATE_FORMAT = "MM/dd/yyyy";

    /* System properties */
    public static final String URL_SYS_PROPERTY                 = "url";
    public static final String HOST_SYS_PROPERTY                = "host";
    public static final String LOCALE_SYS_PROPERTY              = "locale";
    public static final String BROWSER_TYPE_SYS_PROPERTY        = "browser";
    public static final String TEST_LOCAL_SYS_PROPERTY          = "testLocal";
    public static final String REMOTE_GRID_SYS_PROPERTY         = "remoteAddress";
    public static final String USERNAME_SYS_PROPERTY            = "username";
    public static final String PASSWORD_SYS_PROPERTY            = "password";
    public static final String TEST_DATA_FILE_SYS_PROPERTY      = "testDataFile";
    public static final String CAPTURE_SCREENSHOTS_SYS_PROPERTY = "captureScreenshots";
    public static final String WAIT_TIMEOUT_SYS_PROPERTY        = "waitTimeout";
    public static final String TRY_FOR_SYS_PROPERTY             = "tryFor";
    public static final String WAIT_BETWEEN_TRIES_SYS_PROPERTY  = "waitBetweenTries";
    public static final String PAGE_LOAD_TIMEOUT_SYS_PROPERTY   = "pageLoadTimeout";

    public static final String BROWSER_TYPE_IE      = "ie";
    public static final String BROWSER_TYPE_CHROME  = "chrome";
    public static final String BROWSER_TYPE_FIREFOX = "firefox";
    public static final String BROWSER_TYPE_DEFAULT = "firefox";

    public static final String LOCAL_HOST = "localhost";

    /* Configuration files */
    public static final String APPLICATION_CONFIGURATION_FILE = "applications.xml";

    public static final String UNDERSCORE         = "_";
    public static final String TIME_STAMP_VAR     = "${timestamp}";
    public static final String TODAYS_DATE_VAR    = "${todaysdate}";
    public static final String ACCOUNT_HOLDER_VAR = "${accountholder}";

    private static WebDriver driver;
    private static ScreenObjectController controller;

    private       String   lastLoggedMessage;

    public final Logger logger = LoggerFactory.getLogger(getClass());

    protected TestBase() throws JAXBException {
    }

    /**
     * Returns the configure locale.
     *
     * @return the configure locale. If not specified, the system's
     *         default locale will be returned
     */
    public Locale getLocale() {
        String locale = getProperty(LOCALE_SYS_PROPERTY);
        return (locale == null ? Locale.getDefault() : new Locale(locale));
    }

    /**
     * Returns the URL to execute tests on.
     */
    public String getUrl() {
        return getProperty(URL_SYS_PROPERTY);
    }

    /**
     * Returns the configured host.
     *
     * @return the host
     */
    public String getHost() {
        String value = getProperty(HOST_SYS_PROPERTY);
        return (value == null ? LOCAL_HOST : value);
    }

    /**
     * Returns the web controller to use for guidewire.
     *
     * @return the configured web controller
     */
    public ScreenObjectController getController() {
        if (controller == null) {
            controller = new ScreenObjectController(new BasicWebDriverFactory(this).create());
        }
        return controller;
    }

    /**
     * Returns <code>true</code> if tests are to run local; <code>false</code> if
     * remote. If no environment or system property is set, defaults to <code>true</code>.
     */
    public boolean testLocal() {
        String value = getProperty(TEST_LOCAL_SYS_PROPERTY);
        return (LOCAL_HOST.equals(getHost()) || value == null || value.equals("true"));
    }

    public String getRemoteGridSysProperty(){
        String value = getProperty(REMOTE_GRID_SYS_PROPERTY);
        return (value==null ? "http://"+System.getenv("SELENIUM_GRID_URL") : value);
    }

    /**
     * Returns the web driver to use for testing.
     *
     * @return the configured web driver
     */
    public WebDriver getWebDriver(){
        return controller.getWebDriver();
    }

    /**
     * Returns the default timeout used for wait operations.
     *
     * @return the default timeout (in seconds) used for wait operations
     */
    public Integer getWaitTimeout() {
        String value = getProperty(WAIT_TIMEOUT_SYS_PROPERTY);
        return (value == null ? null : Integer.valueOf(value));
    }

    /**
     * Returns the amount of time to try on a failed operation.
     *
     * @return the amount of time to try on a failed operation (in seconds)
     */
    public Integer getTryFor() {
        String value = getProperty(TRY_FOR_SYS_PROPERTY);
        return (value == null ? null : Integer.valueOf(value));
    }

    /**
     * Returns the amount of time to wait between retries on a failed operation.
     *
     * @return the amount of time to wait between retries on a failed operation (in seconds)
     */
    public Integer getWaitBetweenTries() {
        String value = getProperty(WAIT_BETWEEN_TRIES_SYS_PROPERTY);
        return (value == null ? null : Integer.valueOf(value));
    }

    /**
     * Returns the application username to use for tests.
     */
    public String getUsername() {
        return getProperty(USERNAME_SYS_PROPERTY);
    }

    /**
     * Returns the application password to use for tests.
     */
    public String getPassword() {
        return getProperty(PASSWORD_SYS_PROPERTY);
    }

    public void closeCurrentWindow() {
        getWebDriver().close();
    }

    public void quitDriver() {
        getWebDriver().quit();
    }

    /**
     * Returns <code>true</code> if a screenshot should be captured on a
     * failed tests; <code>false</code> if not. If no environment or system
     * property is set, defaults to <code>true</code>.
     */
    public boolean isCaptureScreenshots() {
        String value = getProperty(CAPTURE_SCREENSHOTS_SYS_PROPERTY);
        return (value == null || value.equals("true"));
    }

    /**
     * Returns the browser type to execute the tests on.
     */
    public String getBrowserType() {
        return getProperty(BROWSER_TYPE_SYS_PROPERTY);
    }

    public String getProperty(String systemPropertyName) {
        // Check environment variable first
        String value = System.getenv(toEnvironmentVariableStyle(systemPropertyName));
        return (value == null ? System.getProperty(systemPropertyName) : value);
    }

    public void sleep(int timeInMilliseconds) {
        if (timeInMilliseconds > 0) {
            try {
                Thread.sleep(timeInMilliseconds);
            } catch (InterruptedException e) {
                // Ignore
            }
        }
    }

    public void log(String message) {
        logger.info(message);
        lastLoggedMessage = message;
    }

    public String getLastLoggedMessage() {
        return lastLoggedMessage;
    }

    @AfterSuite(alwaysRun = true)
    public void shutdown() {
        logger.info("Shutting down");
        try {
            driver.close();
            driver.quit();
            //quitDriver();
        } catch (Exception e) {
            // Ignore
        }
    }

    /**
     * Checks if two objects are equal. Handles <code>null</code> values.
     *
     * @return <code>true</code> if the objects are equal.
     */
    public static boolean equalValues(Object obj1, Object obj2) {
        return (obj1 == null && obj2 == null) || (obj1 != null && obj1.equals(obj2));
    }

    /**
     * Returns an empty string if the value is <code>null</code>.
     *
     * @param s1 the <code>String</code> to check
     *
     * @return an empty string if the value is <code>null</code>.
     */
    public static String nullToEmpty(String s1) {
        return Strings.nullToEmpty(s1);
    }

    protected boolean isValidDriver(WebDriver driver) {
        if (driver != null) {
            try {
                if (driver.getWindowHandles().isEmpty()) {
                    return false;
                }
            } catch (WebDriverException e) {
                return false;
            }
            if (((RemoteWebDriver)driver).getSessionId() != null) {
                return true;
            }
        }
        return false;
    }

    /**
     * Converts a string to system environment variable style. All capitals with hyphens
     * separating each word. A word is denoted by a capital letter (e.g. testLocal becomes TEST_LOCAL).
     *
     * @param value the string to convert
     *
     * @return the string in system environment variable style
     */
    private static String toEnvironmentVariableStyle(String value) {
        StringBuilder builder = new StringBuilder();
        String[] tokens = value.split("(?=\\p{Upper})");
        for (int i = 0; i < tokens.length; i++) {
            if (i != 0) {
                builder.append(UNDERSCORE);
            }
            builder.append(tokens[i].toUpperCase());
        }
        return builder.toString();
    }

    protected SimpleDateFormat createDateFormatter(String date) {
        SimpleDateFormat dateFormatter = new SimpleDateFormat();
        if (date != null && date.contains(":")) {
            dateFormatter.applyPattern(date.substring(date.lastIndexOf(":") + 1));
        } else {
            dateFormatter.applyPattern(DEFAULT_DATE_FORMAT);
        }
        return dateFormatter;
    }

    @BeforeSuite(alwaysRun = true)
    protected void initializeController() {
        log("Initializing test framework");
        System.setProperty(Driver.BROWSER_PROPERTY_NAME,getBrowserType().toUpperCase());
        System.setProperty(Driver.REMOTE_ADDRESS_URL, getRemoteGridSysProperty());
        driver = Driver.get(testLocal());
        controller = new ScreenObjectController(driver).getController();

        // Set locale if configured
        Locale locale = getLocale();
        if (locale != null) { controller.setLocale(locale); }
        log("Locale: " + controller.getLocale());

    }


    /*===============================================================================================*/
    /*========================================== Factories ==========================================*/
    /*===============================================================================================*/

    public static class BasicWebDriverFactory extends Driver {
        private final TestBase testBase;

        public BasicWebDriverFactory(TestBase testBase) {
            this.testBase = testBase;
        }

        public WebDriver create() {
            return testBase.getWebDriver();
        }
    }
}