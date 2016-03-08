package com.guidewire.pstesting.testng;

import com.guidewire.pstesting.TestBase;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.Reporter;
import org.testng.TestListenerAdapter;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ScreenShotOnFailureTestListener extends TestListenerAdapter {
    final String SCREENSHOT_FOLDER  = "screenshots";
    final String BASE_REPORT_FOLDER = "target/surefire-reports/html";  // TODO: These should not be hardcoded (SLC)

    final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSS");

    final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onTestFailure(ITestResult testResult) {
        super.onTestFailure(testResult);

        logger.info("Test failed");

        TestBase testDriver = (TestBase)testResult.getInstance();
        WebDriver driver = testDriver.getWebDriver();

        addLastLoggedMessageToReport(testResult, testDriver);

        // Unexpected alert causing the issue?
        if (UnhandledAlertException.class.isInstance(testResult.getThrowable())) {
            Alert alert = driver.switchTo().alert();
            logger.error("Unexpected alert displayed: " + alert.getText());
        }

        // Screenshot capture enabled?
        if (testDriver.isCaptureScreenshots()) {
            // If remote driver, need to augment?
            if (RemoteWebDriver.class.isInstance(driver) && !TakesScreenshot.class.isInstance(driver)) {
                driver = new Augmenter().augment(driver);
            }

            try {
                if ("about:blank".equals(driver.getCurrentUrl())) {
                    logger.info("No screenshot taken. Currently on \"about.blank\" page");
                    addMessageToReport(testResult, "Currently on \"about.blank\" page");
                    return;
                }
            } catch (Exception e) {
                logger.error("Failed capturing screenshot: " + e.getMessage());
                return;
            }

            // Is the driver capable of taking a screenshot?
            if (TakesScreenshot.class.isInstance(driver)) {
                String testClassName = testResult.getTestClass().getName();
                int startName = testClassName.lastIndexOf(".");
                String testMethodAndTestClass = testClassName.substring(startName+1) + "-" + testResult.getMethod().getMethodName();

                File file = new File("");
                String folName = file.getAbsolutePath() + "/" + BASE_REPORT_FOLDER + "/" + SCREENSHOT_FOLDER + "/";
                String fileName = testMethodAndTestClass + "-" + dateFormat.format(Calendar.getInstance().getTime()) + ".png";

                String filePath = folName + fileName;
                try {
                    File scrShotFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
                    String screenshotBase64 = ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
                    try {
                        FileUtils.copyFile(scrShotFile, new File(filePath));
                        // Insert screenshot link in test results report
                        Reporter.setCurrentTestResult(testResult);
                        int startPath = filePath.lastIndexOf("\\");
                        String modulePath = filePath.substring(startPath+1);
                        Reporter.log("<a href='../../" + modulePath + "'>Screenshot</a>");
                        //Reporter.log("<a href='../../../" + BASE_REPORT_FOLDER + "/" + SCREENSHOT_FOLDER + "/" + fileName + "'>Failure Screenshot</a>");
                        Reporter.setCurrentTestResult(null);
                    } catch (IOException e) {
                        logger.error("Failed copying screenshot file: " + scrShotFile, e);
                    }
                } catch (Exception e) {
                    logger.error("Failed capturing screenshot: " + e.getMessage(), e);
                }
            } else {
                logger.warn("Web driver not capable of taking a screenshot: " + driver);
            }
        }
    }

    private void addLastLoggedMessageToReport(ITestResult testResult, TestBase testDriver) {
        // Only add log message to report if there is something to log
        String lastLogMessage = testDriver.getLastLoggedMessage();
        if (lastLogMessage != null) {
            addMessageToReport(testResult, lastLogMessage);
        }
    }

    private void addMessageToReport(ITestResult testResult, String message) {
        Reporter.setCurrentTestResult(testResult);
        Reporter.log("<div>" + message + "</div>");
        Reporter.setCurrentTestResult(null);
    }
}
