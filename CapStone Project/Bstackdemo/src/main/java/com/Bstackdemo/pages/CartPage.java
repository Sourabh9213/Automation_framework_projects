package com.Bstackdemo.pages;

import java.util.List;
import java.util.ArrayList;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Bstackdemo.Utils.WaitUtil;

public class CartPage {

	private WebDriver driver;
	private WaitUtil WaitU;

	public CartPage(WebDriver driver) {
		this.driver = driver;
		WaitU = new WaitUtil(driver);
	}

	private By cartItems = By.cssSelector(".float-cart__content .shelf-item");
	private By itemNames = By.cssSelector(".float-cart__content .shelf-item__title");
	private By cartIcon = By.cssSelector("span[class*='cart']");
	private By removeBtn = By.cssSelector(".float-cart__content .shelf-item__del");
	private By checkoutBtn = By.cssSelector(".buy-btn");
	private By subtotal = By.cssSelector(".sub-price__val");
	private By closeCart = By.cssSelector(".float-cart__close-btn");

	public boolean isCartDisplayed() {
		return driver.findElements(cartItems).size() > 0;
	}

	public void openCart() {
		driver.findElement(cartIcon).click();
	}

	public int getCartItemCount() {
		return driver.findElements(cartItems).size();
	}

	public List<String> getCartItemNames() {
		List<WebElement> elements = driver.findElements(itemNames);
		List<String> names = new ArrayList<>();
		for (WebElement e : elements) {
			names.add(e.getText());
		}
		return names;
	}

	public void removeFirstItem() {
		driver.findElements(removeBtn).get(0).click();
	}

	public void removeItem(String productName) {
		By deleteBtn = By.xpath("//p[text()='" + productName + "']/following::div[@class='shelf-item__del']");
		WaitU.waitUntilClickable(deleteBtn).click();
	}

	public String getSubtotal() {
		return WaitU.getText(subtotal);
	}

	// Correctly spelled method
	public boolean isCheckoutBtnAvailable() {
		try {
			return WaitU.waitUntilVisible(checkoutBtn).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	// Backwards-compatible delegating method (keeps existing callers working)
	public boolean isChekoutBtnAvailable() {
		return isCheckoutBtnAvailable();
	}
	public void clickCheckout() {
		WaitU.waitUntilClickable(checkoutBtn).click();
	}

	public void closeCart() {
		driver.findElement(closeCart).click();
	}
}