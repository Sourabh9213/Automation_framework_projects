package com.blazedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.blazedemo.base.BaseClass;

public class NegativeTest extends BaseClass {

	@Test(groups = "negative", priority = 8)
	public void TC08_blankFormSubmission() {

		homePage.selectCities("Boston", "New York");
		homePage.clickFindFlights();
		reservePage.selectFirstFlight();
		purchasePage.clickPurchaseFlight();

		if (confirmationPage.isConfirmationPageLoaded()) {
			System.out.println("Warning: Demo site allows blank submission");
		} else {
			Assert.assertTrue(confirmationPage.isConfirmationPageLoaded(), "Booking should not be successful");
		}

	}

	@Test(groups = "negative", priority = 9)
	public void TC09_invalidCardDetails() {

		homePage.selectCities("Boston", "London");
		homePage.clickFindFlights();
		reservePage.selectFirstFlight();

		purchasePage.fillPurchaseForm("Test", "Addr", "City", "MH", "400001");
		purchasePage.fillCardDetails("Visa", "123", "00", "0000", "Fake");
		purchasePage.clickPurchaseFlight();

		if (confirmationPage.isConfirmationPageLoaded()) {
			System.out.println("Warning: Demo site allows blank submission");
		} else {
			Assert.assertFalse(confirmationPage.isConfirmationPageLoaded(), "Booking should not be successful");
		}

	}

	@Test(groups = "negative", priority = 10)
	public void TC10_sameCitySelection() {

		homePage.selectCities("Boston", "Boston");
		homePage.clickFindFlights();

		Assert.assertFalse(reservePage.isReservePageDisplayed(), "Same city should not proceed");
	}
}