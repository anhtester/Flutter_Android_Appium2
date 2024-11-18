package com.anhtester.listeners;

import com.anhtester.helpers.FileHelpers;
import com.anhtester.helpers.PropertiesHelpers;
import com.anhtester.reports.AllureManager;
import com.anhtester.utils.LogUtils;
import org.testng.*;

public class TestListener implements ITestListener, ISuiteListener, IInvokedMethodListener {

    static int count_totalTCs;
    static int count_passedTCs;
    static int count_skippedTCs;
    static int count_failedTCs;

    public String getTestName(ITestResult result) {
        return result.getTestName() != null ? result.getTestName() : result.getMethod().getConstructorOrMethod().getName();
    }

    public String getTestDescription(ITestResult result) {
        return result.getMethod().getDescription() != null ? result.getMethod().getDescription() : getTestName(result);
    }

    @Override
    public void beforeInvocation(IInvokedMethod method, ITestResult testResult) {
        // Before every method in the Test Class
        //System.out.println(method.getTestMethod().getMethodName());
    }

    @Override
    public void afterInvocation(IInvokedMethod method, ITestResult testResult) {
        // After every method in the Test Class
        //System.out.println(method.getTestMethod().getMethodName());
    }

    @Override
    public void onStart(ISuite iSuite) {
        LogUtils.info("********** RUN STARTED **********");
        LogUtils.info("========= INSTALLING CONFIGURATION DATA =========");

        PropertiesHelpers.loadAllFiles();
        AllureManager.setAllureEnvironmentInformation();
        LogUtils.info("========= INSTALLED CONFIGURATION DATA =========");
        LogUtils.info("=====> Starting Suite: " + iSuite.getName());
    }

    @Override
    public void onFinish(ISuite iSuite) {
        LogUtils.info("********** RUN FINISHED **********");
        LogUtils.info("=====> End Suite: " + iSuite.getName());

        AllureManager.setAllureEnvironmentInformation();

        //FileHelpers.copyFile("src/test/resources/config/allure/environment.xml", "target/allure-results/environment.xml");
        //FileHelpers.copyFile("src/test/resources/config/allure/categories.json", "target/allure-results/categories.json");
        //FileHelpers.copyFile("src/test/resources/config/allure/executor.json", "target/allure-results/executor.json");

    }

    @Override
    public void onTestStart(ITestResult iTestResult) {
        LogUtils.info("Test case: " + getTestName(iTestResult) + " is starting...");
        count_totalTCs = count_totalTCs + 1;
    }

    @Override
    public void onTestSuccess(ITestResult iTestResult) {
        LogUtils.info("Test case: " + getTestName(iTestResult) + " is passed.");
        count_passedTCs = count_passedTCs + 1;
    }

    @Override
    public void onTestFailure(ITestResult iTestResult) {
        LogUtils.error("FAILED !! Test case " + getTestName(iTestResult) + " is failed.");
        LogUtils.error(iTestResult.getThrowable());
        count_failedTCs = count_failedTCs + 1;
    }

    @Override
    public void onTestSkipped(ITestResult iTestResult) {
        LogUtils.warn("WARNING!! Test case: " + getTestName(iTestResult) + " is skipped.");
        count_skippedTCs = count_skippedTCs + 1;
    }
}
