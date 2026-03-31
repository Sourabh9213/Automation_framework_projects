package com.Bstackdemo.reports;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;

public class TestListener implements ITestListener {

	ExtentReports extent = ExtentManager.getReporter();
	public static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

	public void onTestStart(ITestResult result) {
		ExtentTest extentTest = extent.createTest(result.getName());
		test.set(extentTest);
		// optional: add test description or groups
	}

	public void onTestSuccess(ITestResult result) {
		test.get().pass("Test Passed");
	}

	public void onTestFailure(ITestResult result) {
		Throwable throwable = result.getThrowable();
		if (throwable != null) {
			test.get().fail(throwable);
		} else {
			test.get().fail("Test failed");
		}
	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}
}