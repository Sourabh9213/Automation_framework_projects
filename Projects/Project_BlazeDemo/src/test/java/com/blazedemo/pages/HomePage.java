package com.blazedemo.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.blazedemo.utils.WaitUtils;

public class HomePage {

	private WebDriver driver;
	private WaitUtils wait;

	public HomePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WaitUtils(driver);
	}

//Locators
	private By HomePageHeader = By.xpath("//h1[text()='Welcome to the Simple Travel Agency!']");
	private By FromCity = By.name("fromPort");
	private By ToCity = By.name("toPort");
	private By FindFlightsBtn = By.xpath("//input[@type='submit']");

//Load HomePage	
	public void loadHomePage() {

		WebElement element = driver.findElement(HomePageHeader);
		try {
			wait.waitUntilVisible(element);
		} catch (Exception e) {
			System.out.println("Retry loading home page...");
			driver.get("https://blazedemo.com/");
			wait.waitUntilVisible(element);
		}
	}

// IS HomePage Loaded	
	public boolean isHomePageLoaded() {
		WebElement element = driver.findElement(FindFlightsBtn);
		return wait.waitUntilVisible(element).isDisplayed();
	}

// HomePage Title verify	
	public String getHomePageTitle() {

		return driver.getTitle();
	}

// HomePage Get Header	
	public boolean isFromCityDisplayed() {
		WebElement element = driver.findElement(FromCity);
		return wait.waitUntilVisible(element).isDisplayed();
	}

// HomePage loaded verify
	public boolean isToCityDisplayed() {
		WebElement element = driver.findElement(ToCity);
		return wait.waitUntilVisible(element).isDisplayed();
	}

// Method for Select Cities	
	public void selectCities(String from, String to) {

		Select FromCityDropdown = new Select(driver.findElement(FromCity));
		FromCityDropdown.selectByVisibleText(from);

		Select ToCityDropdown = new Select(driver.findElement(ToCity));
		ToCityDropdown.selectByVisibleText(to);
	}

// Method for click on FlightBTN	
	public void clickFindFlights() {
		driver.findElement(FindFlightsBtn).click();
	}

// Method to get all available cities in FromCity dropdown
	public List<String> getAllFromCities() {
		Select FromCityDropdown = new Select(driver.findElement(FromCity));
		List<WebElement> options = FromCityDropdown.getOptions();
		List<String> fromcities = new ArrayList<>();
		for (WebElement option : options) {
			fromcities.add(option.getText());
		}
		return fromcities;
	}

// Method to get all available cities in ToCity dropdown
	public List<String> getAllToCities() {
		Select ToCityDropdown = new Select(driver.findElement(ToCity));
		List<WebElement> options = ToCityDropdown.getOptions();
		List<String> tocities = new ArrayList<>();
		for (WebElement option : options) {
			tocities.add(option.getText());
		}
		return tocities;
	}
}
