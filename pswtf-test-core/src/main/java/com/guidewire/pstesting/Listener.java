package com.guidewire.pstesting;

import org.testng.*;

public class Listener implements ITestListener, ISuiteListener {
    // This belongs to ISuiteListener and will execute before the Suite start
    @Override
    public void onStart(ISuite arg0) {
        Reporter.log("Executing Suite - " + arg0.getName(), true);
    }
    // This belongs to ISuiteListener and will execute, once the Suite is finished
    @Override
    public void onFinish(ISuite arg0) {
        Reporter.log("Completed executing Suite - " + arg0.getName(), true);
    }
    // This belongs to ITestListener and will execute before starting of Test set/batch
    public void onStart(ITestContext arg0) {
        Reporter.log("Executing Test - " + arg0.getName(), true);
    }
    // This belongs to ITestListener and will execute, once the Test set/batch is finished
    public void onFinish(ITestContext arg0) {
        Reporter.log("Completed executing test - " + arg0.getName(), true);
    }
    // This belongs to ITestListener and will execute only when the test is pass
    public void onTestSuccess(ITestResult arg0) {
        // This is calling the printTestResults method
        printTestResults(arg0);
    }
    // This belongs to ITestListener and will execute only on the event of fail test
    public void onTestFailure(ITestResult arg0) {
        // This is calling the printTestResults method
        printTestResults(arg0);
    }
    // This belongs to ITestListener and will execute before the main test start (@Test)
    public void onTestStart(ITestResult arg0) {
        System.out.println("Executing the main test");
    }
    // This belongs to ITestListener and will execute only if any of the main test(@Test) get skipped
    public void onTestSkipped(ITestResult arg0) {
        printTestResults(arg0);
    }
    // This is mandatory, but can ignore
    public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
    }
    // This is the method which will be executed in case of test pass or fail
    // This will provide the information on the test
    private void printTestResults(ITestResult result) {
        Reporter.log("Test Method resides in: " + result.getTestClass().getName(), true);
        if (result.getParameters().length != 0) {
            String params = "";
            for (Object parameter : result.getParameters()) {
                params += parameter.toString() + ",";
            }
            Reporter.log("Test Method had the following parameters: " + params, true);
        }
        String status = null;
        switch (result.getStatus()) {
            case ITestResult.SUCCESS:
                status = "PASS";
                break;
            case ITestResult.FAILURE:
                status = "FAIL";
                break;
            case ITestResult.SKIP:
                status = "Skipped";
        }
        Reporter.log("Test Status: " + status, true);
    }


    // This belongs to IInvokedMethodListener and will execute before every method including @Before @After @Test
    public void beforeInvocation(IInvokedMethod arg0, ITestResult arg1) {
        String textMsg = "Executing method: " + returnMethodName(arg0.getTestMethod());
        Reporter.log(textMsg, true);
    }
    // This belongs to IInvokedMethodListener and will execute after every method including @Before @After @Test
    public void afterInvocation(IInvokedMethod arg0, ITestResult arg1) {
        String textMsg = "Completed executing method: " + returnMethodName(arg0.getTestMethod());
        Reporter.log(textMsg, true);
    }
    // This will return method names to the calling function
    private String returnMethodName(ITestNGMethod method) {
        return method.getRealClass().getSimpleName() + "." + method.getMethodName();
    }

}
