package com.blazedemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import com.blazedemo.utils.WaitUtils;

public class PurchasePage {
	private WebDriver driver;
	private WaitUtils wait;

	public PurchasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WaitUtils(driver);
	}

	// Locators
	private By Name = By.id("inputName");
	private By Address = By.id("address");
	private By City = By.id("city");
	private By State = By.id("state");
	private By ZipCode = By.id("zipCode");
	private By CardType = By.id("cardType");
	private By CreditCardNumber = By.id("creditCardNumber");
	private By CreditCardMonth = By.id("creditCardMonth");
	private By CreditCardYear = By.id("creditCardYear");
	private By NameOnCard = By.id("nameOnCard");
	private By RememberMe = By.id("rememberMe");
	private By PurchaseFlightButton = By.xpath("//input[@value='Purchase Flight']");

	// Method verify purchase page is displayed
	public boolean isPurchasePageDisplayed() {
		return driver.findElement(PurchaseFlightButton).isDisplayed();
	}

	// Method 1 to fill Person details
	public void fillPurchaseForm(String nameVal, String addVal, String cityVal, String stateVal, String zipVal) {
		WebElement element = driver.findElement(Name);
		wait.waitUntilVisible(element);
		driver.findElement(Name).sendKeys(nameVal);
		driver.findElement(Address).sendKeys(addVal);
		driver.findElement(City).sendKeys(cityVal);
		driver.findElement(State).sendKeys(stateVal);
		driver.findElement(ZipCode).clear();
		driver.findElement(ZipCode).sendKeys(zipVal);

	}

	// Method 2 fill card details
	public void fillCardDetails(String cardTypeVal, String cardNumVal, String MonthVal, String YearVal,
			String nameOnCardVal) {
		Select cardTypeSelect = new Select(driver.findElement(CardType));
		cardTypeSelect.selectByVisibleText(cardTypeVal);

		driver.findElement(CreditCardNumber).sendKeys(cardNumVal);
		driver.findElement(CreditCardMonth).clear();
		driver.findElement(CreditCardMonth).sendKeys(MonthVal);
		driver.findElement(CreditCardYear).clear();
		driver.findElement(CreditCardYear).sendKeys(YearVal);
		driver.findElement(NameOnCard).sendKeys(nameOnCardVal);
	}

	// Method 3 Set remembeMe
	public void setRememberMe(boolean value) {
		WebElement checkbox = driver.findElement(RememberMe);
		if (checkbox.isSelected() != value) {
			checkbox.click();
		}
	}

	// Method to click on purchase flight button
	public void clickPurchaseFlight() {
		WebElement element = driver.findElement(PurchaseFlightButton);
		wait.waitUntilClickable(element).click();
	}
}
