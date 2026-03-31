package com.blazedemo.tests;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.blazedemo.base.BaseClass;
import com.blazedemo.utils.ExcelDataUtil;

public class PurchasePageTest extends BaseClass {

	@DataProvider(name = "cardData")
	public Object[][] getData() {
		excel = new ExcelDataUtil("src/test/resources/testData.xlsx", "Sheet1");
		return excel.getCardData();
	}

//	@Test(dataProvider = "cardData") for debugging 
	public void printExcelData(String fromCity, String toCity, String nameVal, String addVal, String cityVal,
			String stateVal, String zipVal, String cardTypeVal, String cardNumVal, String MonthVal, String YearVal,
			String nameOnCardVal) {
		System.out.println(fromCity + toCity + nameVal + addVal + cityVal + stateVal + zipVal + cardTypeVal + cardNumVal
				+ MonthVal + YearVal + nameOnCardVal);

	}

	@Test(dataProvider = "cardData", groups = { "functional", "purchase" }, priority = 7)
	public void TC07_fillBookingDetails(String fromCity, String toCity, String nameVal, String addVal, String cityVal,
			String stateVal, String zipVal, String cardTypeVal, String cardNumVal, String MonthVal, String YearVal,
			String nameOnCardVal) {

		homePage.selectCities(fromCity, toCity);
		homePage.clickFindFlights();
		reservePage.selectFirstFlight();

		Assert.assertTrue(purchasePage.isPurchasePageDisplayed(), "Purchase Page not displayed");

		purchasePage.fillPurchaseForm(nameVal, addVal, cityVal, stateVal, zipVal);

		purchasePage.fillCardDetails(cardTypeVal, cardNumVal, MonthVal, YearVal, nameOnCardVal);
		purchasePage.setRememberMe(true);
		purchasePage.clickPurchaseFlight();

		String bookingId = confirmationPage.getBookingId();
		AssertJUnit.assertNotNull(bookingId, "Booking ID is null");

		String status = confirmationPage.getStatus();
		Assert.assertTrue(status.contains("Pending") || status.contains("Confirmed"), "Invalid booking status");

		String amount = confirmationPage.getAmount();
		System.out.println("Your Flight Booked Amount is : " +amount);
		Assert.assertTrue(amount.contains("USD"), "Amount not displayed correctly");
	}
}