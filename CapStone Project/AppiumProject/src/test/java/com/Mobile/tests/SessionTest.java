package com.Mobile.tests;

import java.net.URL;

import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class SessionTest {

	public AppiumDriver driver;
	public URL url;

	@BeforeTest
	public void setup() throws Exception {

		UiAutomator2Options options = new UiAutomator2Options();
		options.setPlatformName("Android");
		options.setDeviceName("emulator-5554");
		options.setAutomationName("UiAutomator2");
		url = new URL("http://127.0.0.1:4723");

		driver = new AndroidDriver(url, options);

	}

	@Test
	public void captureSessionID() {
		System.out.println("Session ID: " + driver.getSessionId());
	}

	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}