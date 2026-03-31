package com.blazedemo.utils;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class Screenshot {

	// Method 1: Capture/Take a Screenshot
	public static String captureScreenshot(WebDriver driver, String ssName) {

		TakesScreenshot SS = (TakesScreenshot) driver;
		File src = SS.getScreenshotAs(OutputType.FILE);

		String path = System.getProperty("user.dir") +"/Screenshots/" + ssName + ".jpeg";
		File dest = new File(path);

		try {
			FileUtils.copyFile(src, dest);
			 System.out.println(" Screenshot captured: " + path);
		} catch (IOException e) {
			System.out.println(" Screenshot failed: " + e.getMessage());
		
			e.printStackTrace();
		}
		return path;
	}

}