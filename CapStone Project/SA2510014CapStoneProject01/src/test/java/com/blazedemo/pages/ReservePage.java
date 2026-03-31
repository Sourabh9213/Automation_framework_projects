package com.blazedemo.pages;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.blazedemo.utils.WaitUtils;

public class ReservePage {

	private WebDriver driver;
	private WaitUtils waitUtil;

	public ReservePage(WebDriver driver) {
		this.driver = driver;
		waitUtil = new WaitUtils(driver);
	}

	// locators
	private By FlightRows = By.xpath("//tbody/tr");
	private By chooseFlightBtn = By.xpath("//input[@value='Choose This Flight']");

	// Method to verify if the reserve page is displayed
	public boolean isReservePageDisplayed() {

		try {
			WebElement element = waitUtil.waitUntilVisible(driver.findElement(FlightRows));
			return element.isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	// Method to Flights List Page is Open
	public boolean isFlightListDisplayed() {
		List<WebElement> flights = driver.findElements(chooseFlightBtn);
		return flights.size() > 0;
	}

	// Method to select the first flight
	public void selectFirstFlight() {
		WebElement element = driver.findElement(chooseFlightBtn);
		waitUtil.waitUntilVisible(element).click();
	}

	// Method to select a flight by index
	public void selectFlightByIndex(int index) {
		List<WebElement> flights = driver.findElements(chooseFlightBtn);
		if (index >= 0 && index < flights.size()) {
			flights.get(index).click();
		}
	}

	// Method to select a flight by airline name or price
	public void selectFlightByAirlineOrPrice(String choice) {
		List<WebElement> rows = driver.findElements(FlightRows);
		for (WebElement row : rows) {
			String rowText = row.getText();
			if (rowText.contains(choice)) {
				row.findElement(chooseFlightBtn).click();
				break;
			}
		}
	}
	// Method to get the name of AirLines Available

	public void getDetailsOfAirlineNames() {
		List<WebElement> rows = driver.findElements(FlightRows);
		int i = 1;
		for (WebElement row : rows) {
			String rowText = row.getText();
			System.out.println("Airlines : " + i + " : " + rowText);
			i++;
		}

	}

}
