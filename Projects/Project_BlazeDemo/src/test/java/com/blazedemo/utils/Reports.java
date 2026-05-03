package com.blazedemo.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Reports {

	public static ExtentReports report;

	public static ExtentReports getReportInstance() {

		if (report == null) {

			String timeStamp = new SimpleDateFormat("dd-MM-yy_HH.mm").format(new Date());
			String path = "./reports/ExtentReport_" + timeStamp + ".html";
			ExtentSparkReporter reporter = new ExtentSparkReporter(path);
			reporter.config().setReportName("BlazeDemo Report");
			reporter.config().setDocumentTitle("BlazeDemo Test Results");
			reporter.config().setTheme(Theme.DARK);

			report = new ExtentReports();
			report.attachReporter(reporter);
		}

		return report;
	}

}