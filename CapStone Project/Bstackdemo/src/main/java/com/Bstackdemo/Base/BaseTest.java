package com.Bstackdemo.Base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import com.Bstackdemo.Utils.ConfigReader;
import com.Bstackdemo.Utils.WebDriverFactory;
import com.Bstackdemo.pages.CartPage;
import com.Bstackdemo.pages.CheckoutPage;
import com.Bstackdemo.pages.LoginPage;
import com.Bstackdemo.pages.ProductPage;

public class BaseTest {

	protected WebDriver driver;
	ConfigReader config;
	public LoginPage loginPage;
	public ProductPage productPage;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;

	@BeforeMethod
	public void setup() {
		config = new ConfigReader();
		loginPage = new LoginPage(driver);
		productPage = new ProductPage(driver);
		cartPage = new CartPage(driver);
		checkoutPage = new CheckoutPage(driver);
		String browser = config.getBrowser();
		driver = WebDriverFactory.getDriver(browser);
		driver.get(config.getBaseUrl());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterMethod
	public void teardown() throws InterruptedException {
		Thread.sleep(500);			
		driver.quit();
	}



}
