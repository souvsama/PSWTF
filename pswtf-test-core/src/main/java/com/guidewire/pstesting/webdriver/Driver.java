package com.guidewire.pstesting.webdriver;

import org.openqa.selenium.Platform;
import org.openqa.selenium.UnsupportedCommandException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * A singleton style manager to maintain Drivers to prevent
 * test slowdown for creating a browser for each class with tests.
 *
 * Also counts time to start a browser and extrapolates from that how much
 * time you have saved using such code.
 */
public class Driver extends Thread{
    private static WebDriver aDriver=null;
    private static long browserStartTime = 0L;
    private static long savedTimecount = 0L;
    public static final long DEFAULT_TIMEOUT_SECONDS = 10;
    private static boolean avoidRecursiveCall=false;
    public static final String BROWSER_PROPERTY_NAME = "webdriver";
    public static final String REMOTE_ADDRESS_URL = "http://localhost:4444/wd/hub";
    public static boolean TEST_LOCAL = true;
    // PhantomJS Currently only compatible with Selenium 2.34
/*
    public static final File PHANTOMJS_EXE =
            new File(System.getProperty("user.dir"), "../tools/phantomjs-1.9.7/phantomjs.exe");
*/

    private static final  String DEFAULT_BROWSER = "FIREFOX";

    public enum BrowserName{FIREFOX, GOOGLECHROME, QCSERVERFF, QCSERVERCHROME, SAUCELABS, IE, HTMLUNIT, PHANTOMJS}

    public static BrowserName currentDriver;
    public static DesiredCapabilities capabilities;
    public static ChromeOptions options;

    private static BrowserName useThisDriver = null;

    // default for browsermob localhost:8080
    // default for fiddler: localhost:8888
    public static String PROXYHOST="localhost";
    public static String PROXYPORT="8888";
    public static String PROXY=PROXYHOST+":"+PROXYPORT;

    public static void set(BrowserName aBrowser){
        useThisDriver = aBrowser;

        // close any existing driver
        if(aDriver != null){
            aDriver.quit();
            aDriver = null;
        }
    }

    /**
     * Get the Selenium Grid url from environment variable to prevent distribution with code,
     * Or use remote address set in the run configuration, in CI tool etc
     * @return  url of the remote grid hub
     */
    public static String getGridUrl(){
        String value = System.getenv("SELENIUM_GRID_URL");
        return (value==null ? REMOTE_ADDRESS_URL : "http://" + value);
    }

    /**
     * Get the webdriver for the selected browser
     * @param testLocal derived from the run configuration, <code>true</code> if executed locally <code>false</code>
     *                  if remote webdriver is required
     * @return webdriver for chosen browser
     */
    public static WebDriver get(boolean testLocal) {
        TEST_LOCAL = testLocal;
        if(useThisDriver == null){

            String defaultBrowser = System.getProperty(BROWSER_PROPERTY_NAME, DEFAULT_BROWSER);
            System.out.println("Selected browser: " + defaultBrowser);
            switch (defaultBrowser){
                case "FIREFOX":
                    useThisDriver = BrowserName.FIREFOX;
                    break;
                case "CHROME":
                    useThisDriver = BrowserName.GOOGLECHROME;
                    break;
                case "IE":
                    useThisDriver = BrowserName.IE;
                    break;
                case "SAUCELABS":
                    useThisDriver = BrowserName.SAUCELABS;
                    break;
                case "HTMLUNIT":
                    useThisDriver = BrowserName.HTMLUNIT;
                    break;
                case "PHANTOMJS":
                    //useThisDriver = BrowserName.PHANTOMJS;
                    System.out.println("Driver not available for this version of Selenium");
                    break;
                default:
                    throw new RuntimeException("Unknown Browser in " + BROWSER_PROPERTY_NAME + ": " + defaultBrowser);
            }
        }


        if(aDriver==null){

            long startBrowserTime = System.currentTimeMillis();

            switch (useThisDriver) {
                case FIREFOX:
                    FirefoxProfile profile = new FirefoxProfile();
                    profile.setEnableNativeEvents(true);
                    if (TEST_LOCAL) {
                        aDriver = new FirefoxDriver();//profile);
                    }  else {
                        capabilities = DesiredCapabilities.firefox();
                        capabilities.setCapability("version", "");
                        try {
                            // add url to environment variables to avoid releasing with source
                            //String gwURL = "http://" + System.getenv("SELENIUM_GRID_URL");
                            aDriver = new RemoteWebDriver(
                                    new URL(getGridUrl()),
                                    capabilities);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                    currentDriver = BrowserName.FIREFOX;
                    break;

                case HTMLUNIT:

                    aDriver = new HtmlUnitDriver(true);
                    currentDriver = BrowserName.HTMLUNIT;
                    break;

/*
                case PHANTOMJS:

                    DesiredCapabilities caps = new DesiredCapabilities();
                    caps.setJavascriptEnabled(true);
                    caps.setCapability("phantomjs.binary.path", PHANTOMJS_EXE.getAbsolutePath());
                    aDriver = new PhantomJSDriver(caps);
                    currentDriver = BrowserName.PHANTOMJS;
                    break;
*/

                case IE:

                    setDriverPropertyIfNecessary("webdriver.ie.driver",
                            "/../tools/IEDriverServer.exe",
                            "C://webdrivers/IEDriverServer.exe");
                    DesiredCapabilities ieCapabilities = DesiredCapabilities.internetExplorer();
                    ieCapabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
                    if (testLocal) {
                        aDriver = new InternetExplorerDriver(ieCapabilities);
                    } else {
                        try {
                            // add url to environment variables to avoid releasing with source
                            //String gwURL = "http://" + System.getenv("SELENIUM_GRID_URL");
                            aDriver = new RemoteWebDriver(
                                    new URL(getGridUrl()),
                                    ieCapabilities);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                    currentDriver = BrowserName.IE;
                    break;

                case GOOGLECHROME:

                    setDriverPropertyIfNecessary("webdriver.chrome.driver",
                            "/../tools/chromedriver.exe",
                            "C://webdrivers/chromedriver.exe");

                    options = new ChromeOptions();
                    options.addArguments("disable-plugins");
                    options.addArguments("disable-extensions");
                    // with Chrome v35 it now reports an error on --ignore-certificate-errors
                    // so call with args "test-type"
                    // https://code.google.com/p/chromedriver/issues/detail?id=799
                    options.addArguments("test-type");
                    if (testLocal) {
                        aDriver = new ChromeDriver(options);
                    } else {
                        capabilities = DesiredCapabilities.firefox();
                        capabilities.setCapability("version", "");
                        try {
                            // add url to environment variables to avoid releasing with source
                            //String gwURL = "http://" + System.getenv("SELENIUM_GRID_URL");
                            aDriver = new RemoteWebDriver(
                                    new URL(getGridUrl()),
                                    capabilities);
                        } catch (MalformedURLException e) {
                            e.printStackTrace();
                        }
                    }
                    currentDriver = BrowserName.GOOGLECHROME;
                    break;

                case SAUCELABS:
                    //Set capabilities as appropriate for test
                    capabilities = DesiredCapabilities.firefox();
                    capabilities.setCapability("version", "5");
                    capabilities.setCapability("platform", Platform.XP);
                    try {
                        // add url to environment variables to avoid releasing with source
                        String sauceURL = System.getenv("SAUCELABS_URL");
                        aDriver = new RemoteWebDriver(
                                new URL(sauceURL),
                                capabilities);
                    } catch (MalformedURLException e) {
                        e.printStackTrace();
                    }
                    currentDriver = BrowserName.SAUCELABS;
                    break;
            }


            long browserStartedTime = System.currentTimeMillis();
            browserStartTime = browserStartedTime - startBrowserTime;

            // we want to shutdown the shared browser when the tests finish
            Runtime.getRuntime().addShutdownHook(
                    new Thread(){
                        public void run(){
                            Driver.quit();
                        }
                    }
            );

        }else{

            try{
                // is browser still alive
                if(aDriver.getWindowHandle()!=null){
                    // assume it is still alive
                }
            }catch(Exception e){
                if(avoidRecursiveCall){
                    // something has gone wrong as we have been here already
                    throw new RuntimeException();
                }

                quit();
                aDriver=null;
                avoidRecursiveCall = true;
                return get(TEST_LOCAL);
            }

            savedTimecount += browserStartTime;
            System.out.println("Saved another " + browserStartTime + "ms : total saved " + savedTimecount + "ms");
        }

        avoidRecursiveCall = false;
        return aDriver;
    }

    private static void setDriverPropertyIfNecessary(String propertyKey, String relativeToUserPath, String absolutePath) {
        // http://docs.oracle.com/javase/tutorial/essential/environment/sysprop.html

        if(!System.getProperties().containsKey(propertyKey)){

            String currentDir = System.getProperty("user.dir");
            String chromeDriverLocation = currentDir + relativeToUserPath;
            File driverExe = new File(chromeDriverLocation);
            if(driverExe.exists()){
                System.setProperty(propertyKey, chromeDriverLocation);
            }else{
                driverExe = new File(absolutePath);
                if(driverExe.exists()){
                    System.setProperty(propertyKey, absolutePath);
                }else{
                    // expect an error on the follow through when we try to use the driver
                }
            }
        }
    }

    public static WebDriver get(String aURL, boolean maximize){
        get(TEST_LOCAL);
        aDriver.get(aURL);
        if(maximize){
            try{
                aDriver.manage().window().maximize();
            }catch(UnsupportedCommandException e){
                System.out.println("Remote Driver does not support maximise");
            }catch(UnsupportedOperationException e){
                System.out.println("Opera driver does not support maximize yet");
            }
        }
        return aDriver;
    }

    public static WebDriver get(String aURL){
        return get(aURL,true);
    }

    public static void quit(){
        if(aDriver!=null){
            System.out.println("browser reuse saved " + savedTimecount + "ms");
            try{
                aDriver.quit();
                aDriver=null;
            }catch(Exception e){
                // I don't care about errors at this point
            }

        }
    }
}
