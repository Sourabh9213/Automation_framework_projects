package com.blazedemo.base;

import java.lang.reflect.Method;
import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.blazedemo.pages.ConfirmationPage;
import com.blazedemo.pages.HomePage;
import com.blazedemo.pages.PurchasePage;
import com.blazedemo.pages.ReservePage;
import com.blazedemo.utils.ConfigReader;
import com.blazedemo.utils.ExcelDataUtil;
import com.blazedemo.utils.Reports;
import com.blazedemo.utils.Screenshot;

public class BaseClass {

	public WebDriver driver;
	public ConfigReader config = new ConfigReader();
	public HomePage homePage;
	public ReservePage reservePage;
	public PurchasePage purchasePage;
	public ConfirmationPage confirmationPage;
	public ExcelDataUtil excel;
	public ExtentReports report;
	public ExtentTest test;

	@BeforeMethod
	public void setup(Method method) {

		config = new ConfigReader();

		String browser = config.getBrowser();

		if (browser.equalsIgnoreCase("chrome")) {
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			driver = new EdgeDriver();
		} else {
			throw new RuntimeException("Browser not supported");
		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

		driver.get(config.getBaseUrl());

		homePage = new HomePage(driver);
		reservePage = new ReservePage(driver);
		purchasePage = new PurchasePage(driver);
		confirmationPage = new ConfirmationPage(driver);		
		report = Reports.getReportInstance();

		System.out.println("Driver initialized and application opened");

		// i found this on chatgpt
		test = report.createTest(method.getName());
		System.out.println("Running Test: " + method.getName());
	}

	@AfterMethod
	public void tearDown(ITestResult result) {

		if (result.getStatus() == ITestResult.SUCCESS) {
			test.pass("Test Passed");
		}

		else if (result.getStatus() == ITestResult.FAILURE) {

			String path = Screenshot.captureScreenshot(driver, result.getName());

			test.fail("Test Failed: " + result.getThrowable()).addScreenCaptureFromPath(path);
		}

		
		else if (result.getStatus() == ITestResult.SKIP) {
			test.skip("Test Skipped");		}

		
		if (driver != null) {
			driver.quit();
		}
	}

	@AfterTest
	public void flushReport() {

		if (report != null) {
			report.flush();
			System.out.println("Extent report flushed");
		}
	}
}