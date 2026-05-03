package com.Bstackdemo.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.Bstackdemo.Utils.WaitUtil;

public class ProductPage {

	private WebDriver driver;
	private WaitUtil WaitU;

	public ProductPage(WebDriver driver) {
		this.driver = driver;
		WaitU = new WaitUtil(driver);
	}

	private By products = By.cssSelector(".shelf-item");
	private By productTitles = By.cssSelector(".shelf-item__title");
	private By cartBadge = By.cssSelector(".bag__quantity");
	private By cartIcon = By.cssSelector("span[class*='cart']");
	private By searchBox = By.cssSelector("input[placeholder='Search']");

	public boolean isProductPageLoaded() {
		return driver.findElements(products).size() > 0;
	}

	public int getProductCount() {
		return driver.findElements(products).size();
	}

	public int getCartCount() {
		try {
			WaitU.waitUntilVisible(cartBadge);
			String count = driver.findElement(cartBadge).getText();
			return Integer.parseInt(count);
		} catch (Exception e) {
			return 0;
		}
	}

	public void clickCart() {
		WebElement element = driver.findElement(cartIcon);
		((JavascriptExecutor) driver).executeScript("arguments[0].click();", element);
	}

	public List<String> getAllProductNames() {
		List<WebElement> elements = driver.findElements(productTitles);
		List<String> names = new ArrayList<>();
		for (WebElement e : elements) {
			names.add(e.getText());
		}
		return names;
	}

	public void searchProduct(String productName) {
		WebElement element = WaitU.waitUntilVisible(searchBox);
		element.clear();
		element.sendKeys(productName);
	}

	public void addProductToCart(String productName) {

		By addBtn = By.xpath("//p[text()='" + productName + "']/following::div[text()='Add to cart'][1]");

		WaitU.clickElement(addBtn);
	}

}