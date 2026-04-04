package com.Bstackdemo.reports;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.Bstackdemo.Utils.ScreenshotUtil;
import com.aventstack.extentreports.*;

public class AutomationReports implements ITestListener {

	ExtentReports extent = ExtentManager.getReporter();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	@Override
	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getName());
		test.set(extentTest);
		test.get().info("Test Started: " + result.getName());
	}

	@Override
	public void onTestSuccess(ITestResult result) {
		test.get().pass("Test Passed: " + result.getName());
	}

	@Override
	public void onTestFailure(ITestResult result) {
		test.get().fail("Test Failed: " + result.getName());
		test.get().fail(result.getThrowable());

		try {
			WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");
			String path = ScreenshotUtil.captureScreenshot(driver, result.getName());
			test.get().addScreenCaptureFromPath(path);
		} catch (Exception e) {
			test.get().fail("Screenshot capture failed");
		}
	}

	@Override
	public void onTestSkipped(ITestResult result) {
		test.get().skip("Test Skipped: " + result.getName());
	}

	@Override
	public void onFinish(ITestContext context) {
		extent.flush();
	}
}