package com.Bstackdemo.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class ExtentManager {

	public static ExtentReports extent;

	public static ExtentReports getReporter() {

		if (extent == null) {
			String path = "reports/ExReport_" + System.currentTimeMillis() + ".html";
			ExtentSparkReporter spark = new ExtentSparkReporter(path);			
			spark.config().setReportName("Automation Report");
			spark.config().setDocumentTitle("Test Results");
			spark.config().setTheme(Theme.DARK);
			spark.config().setTimeStampFormat("yyyy-MM-dd HH:mm:ss");

			extent = new ExtentReports();
			extent.attachReporter(spark);
			extent.setSystemInfo("OS", System.getProperty("os.name"));
			extent.setSystemInfo("Java Version", System.getProperty("java.version"));
			
		}

		return extent;
	}
}