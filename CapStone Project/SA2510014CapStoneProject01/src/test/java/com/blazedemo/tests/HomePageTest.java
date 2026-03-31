package com.blazedemo.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.blazedemo.base.BaseClass;
import com.blazedemo.utils.Screenshot;

public class HomePageTest extends BaseClass {

	@Test(groups = { "smoke", "homepage" }, priority = 1)
	public void TC01_verifyHomePageLoaded() {

		Assert.assertTrue(homePage.isHomePageLoaded(), "Home page not loaded");
	}

	@Test(groups = { "smoke", "homepage" }, priority = 2)
	public void TC02_verifyCitiesDropdown() {

		List<String> fromCities = homePage.getAllFromCities();
		List<String> toCities = homePage.getAllToCities();

		Assert.assertTrue(fromCities.size() > 0, "From cities not loaded");
		Assert.assertTrue(toCities.size() > 0, "To cities not loaded");
	}

	@Test(groups = { "functional", "homepage" }, priority = 3)
	public void TC03_searchFlights() {

		homePage.selectCities("Boston", "New York");
		homePage.clickFindFlights();
		if (reservePage.isReservePageDisplayed()) {
			Screenshot.captureScreenshot(driver, "TC03_searchFlights_Fail");

			Assert.assertTrue(reservePage.isReservePageDisplayed(), "Flights page not displayed");
		}

	}
}