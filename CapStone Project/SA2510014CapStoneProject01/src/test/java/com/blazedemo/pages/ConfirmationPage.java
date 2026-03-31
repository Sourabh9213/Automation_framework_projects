package com.blazedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.blazedemo.utils.WaitUtils;

public class ConfirmationPage {

	private WebDriver driver;
	private WaitUtils waitUtil;

	public ConfirmationPage(WebDriver driver) {
		this.driver = driver;
		waitUtil = new WaitUtils(driver);
	}

//locators
	private By confirmationMsg = By.xpath("//h1");
	private By BookingId = By.cssSelector("tbody tr:nth-child(1) td:nth-child(2)");
	private By status = By.cssSelector("tbody tr:nth-child(2) td:nth-child(2)");
	private By amount = By.cssSelector("tbody tr:nth-child(3) td:nth-child(2)");

// Method to Confirm page is loaded
	public boolean isConfirmationPageLoaded() {
		try {
			WebElement element = waitUtil.waitUntilVisible(driver.findElement(confirmationMsg));
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}

	}

//Method to get Booking ID
	public String getBookingId() {
		return driver.findElement(BookingId).getText();
	}

//Method to get Status
	public String getStatus() {
		return driver.findElement(status).getText();
	}

//Method to get amount
	public String getAmount() {
		WebElement element = waitUtil.waitUntilVisible(driver.findElement(amount));
		
		return element.getText();
	}

}
