package com.Mobile.util;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebElement;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumDriver;

public class ElementUtils {

	public static void getScreenshot(AppiumDriver driver, String fname) {

		TakesScreenshot ts = (TakesScreenshot) driver;
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File dest = new File(
				System.getProperty("user.dir") + "/testdata/screenshots/" + fname + System.currentTimeMillis() + ".png");
		try {
			FileHandler.copy(temp, dest);
			System.out.println("Screenshot captured successfully");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void dragAndDropElement(AppiumDriver driver, WebElement element, int x, int y) {

		// gesture
		driver.executeScript("mobile:dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "endX", x, "endY", y));
		System.out.println("Drag and Drop action performed successfully");

	}

	public static void longClick(AppiumDriver driver, WebElement element) {

		driver.executeScript("mobile:longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 4000));
		System.out.println("Long click action performed successfully");

	}

	public static void scrollDown(AppiumDriver driver, WebElement area) {
		driver.executeScript("mobile:scrollGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) area).getId(), "direction", "down", "percent", 1.0));
		System.out.println("Scroll down action performed");
	}

	public static void scrollDownUpToCount(AppiumDriver driver, WebElement area, int count) {
		for (int i = 1; i <= count; i++) {

			driver.executeScript("mobile:scrollGesture", ImmutableMap.of("elementId", ((RemoteWebElement) area).getId(),
					"direction", "down", "percent", 1.0));
			System.out.println("Scrolling iteration completed: " + i);

		}
	}

	public static void scrollUp(AppiumDriver driver, WebElement area) {
		driver.executeScript("mobile:scrollGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) area).getId(), "direction", "up", "percent", 1.0));
		System.out.println("Scroll up action performed");
	}

	public static void scrollLeft(AppiumDriver driver, WebElement area) {
		driver.executeScript("mobile:scrollGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) area).getId(), "direction", "left", "percent", 1.0));
		System.out.println("Scroll left action performed");
	}

	public static void scrollRight(AppiumDriver driver, WebElement area) {
		driver.executeScript("mobile:scrollGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) area).getId(), "direction", "right", "percent", 1.0));
		System.out.println("Scroll right action performed");
	}

// swipe
	public static void swipeLeft(AppiumDriver driver, WebElement area) {
		driver.executeScript("mobile:swipeGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) area).getId(), "direction", "left", "percent", 1.0));
		System.out.println("Swipe left action performed");
	}

	public static void swipeUp(AppiumDriver driver, WebElement area) {

		driver.executeScript("mobile:swipeGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) area).getId(), "direction", "up", "percent", 1.0));
		System.out.println("Swipe Up action performed");
	}

	public static void swipeDown(AppiumDriver driver, WebElement area) {

		driver.executeScript("mobile:swipeGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) area).getId(), "direction", "down", "percent", 1.0));
		System.out.println("Swipe Down action performed");
	}

	public static void swipeRight(AppiumDriver driver, WebElement area) {
		driver.executeScript("mobile:swipeGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) area).getId(), "direction", "right", "percent", 1.0));
		System.out.println("Swipe Right action performed");
	}

	public static void clickButton(AppiumDriver driver, WebElement ele) {

		driver.executeScript("mobile:clickGesture", ImmutableMap.of("elementId", ((RemoteWebElement) ele).getId()));
		System.out.println("Element clicked using gesture");

	}

}