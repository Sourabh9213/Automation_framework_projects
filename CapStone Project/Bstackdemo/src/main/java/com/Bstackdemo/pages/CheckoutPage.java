package com.Bstackdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Bstackdemo.Utils.WaitUtil;

public class CheckoutPage {

	private WebDriver driver;
	private WaitUtil WaitU;

	public CheckoutPage(WebDriver driver) {
		this.driver = driver;
		this.WaitU = new WaitUtil(driver);
	}

	private By firstName = By.cssSelector("#firstNameInput");
	private By lastName = By.cssSelector("#lastNameInput");
	private By address = By.cssSelector("#addressLine1Input");
	private By state = By.cssSelector("#provinceInput");
	private By postalCode = By.cssSelector("#postCodeInput");
	private By submitBtn = By.cssSelector("button[type='submit']");
	private By totalAmount = By.cssSelector(".cart-priceItem-value");
	private By orderNumber = By.cssSelector("div[class='checkout-form'] div strong");

	public boolean isCheckoutPageLoaded() {
		return WaitU.waitUntilVisible(firstName).isDisplayed();
	}

	public void enterFirstName(String fname) {
		WebElement element = WaitU.waitUntilVisible(firstName);
		element.clear();
		element.sendKeys(fname);

	}

	public void enterLastName(String lname) {
		WebElement element = WaitU.waitUntilVisible(lastName);
		element.clear();
		element.sendKeys(lname);
	}

	public void enterAddress(String addr) {
		WebElement element = WaitU.waitUntilVisible(address);
		element.clear();
		element.sendKeys(addr);
	}

	public void enterState(String st) {
		WebElement element = WaitU.waitUntilVisible(state);
		element.clear();
		element.sendKeys(st);
	}

	public void enterPostalCode(String code) {
		WebElement element = WaitU.waitUntilVisible(postalCode);
		element.clear();
		element.sendKeys(code);
	}

	public void clickSubmit() {
		WaitU.waitUntilClickable(submitBtn).click();
	}
	
	public Boolean Order() {
		return driver.findElement(orderNumber).isDisplayed();
	}

	public void fillCheckoutForm(String fname, String lname, String addr, String st, String code) {

		enterFirstName(fname);
		enterLastName(lname);
		enterAddress(addr);
		enterState(st);
		enterPostalCode(code);
		clickSubmit();
	}

	public String getTotalAmount() {
		return WaitU.getText(totalAmount);
	}
}