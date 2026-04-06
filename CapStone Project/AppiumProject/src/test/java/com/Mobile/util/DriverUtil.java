package com.Mobile.util;

import java.net.MalformedURLException;
import java.net.URL;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;

public class DriverUtil {
	
public static AndroidDriver getAndroidDriver(String appPath) throws MalformedURLException {		
		
		UiAutomator2Options options = new UiAutomator2Options();
		options.setCapability("platformName", "Android");
		options.setCapability("appium:automationName", "UiAutomator2");
		options.setCapability("appium:deviceName", "emulator-5554");
		options.setCapability("appium:app", appPath);		
		
		URL url = new URL("http://127.0.0.1:4723/");
		
		AndroidDriver driver = new AndroidDriver(url, options);
		
		
		System.out.println("The driver session id is : " + driver.getSessionId());
		
		return driver;
		
	}

}