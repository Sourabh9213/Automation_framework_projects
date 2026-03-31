package com.blazedemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.blazedemo.base.BaseClass;

public class ReservePageTest extends BaseClass {

    @Test(groups = { "functional", "reserve" }, priority = 4)
    public void TC04_selectFirstFlight() {

    	homePage.selectCities("Boston", "New York");
    	homePage.clickFindFlights();

        Assert.assertTrue(reservePage.isReservePageDisplayed(), "Reserve page not loaded");
        reservePage.selectFirstFlight();
        Assert.assertTrue(purchasePage.isPurchasePageDisplayed(), "Purchase page not opened");
    }

    @Test(groups = { "functional", "reserve" }, priority = 5)
    public void TC05_selectFlightByIndex() {

    	homePage.selectCities("Boston", "New York");
    	homePage.clickFindFlights();
    	reservePage.selectFlightByIndex(2);

        Assert.assertTrue(purchasePage.isPurchasePageDisplayed(), "Flight selection by index failed");
    }

    @Test(groups = { "functional", "reserve" }, priority = 6)
    public void TC06_selectFlightByAirline() {

    	homePage.selectCities("Boston", "New York");
    	homePage.clickFindFlights();

    	reservePage.selectFlightByAirlineOrPrice("Virgin America");

        Assert.assertTrue(purchasePage.isPurchasePageDisplayed(), "Flight selection by airline failed");
    }
}