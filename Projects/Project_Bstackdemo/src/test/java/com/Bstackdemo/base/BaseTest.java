package com.Bstackdemo.base;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
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
	public ConfigReader config;
	public LoginPage loginPage;
	public ProductPage productPage;
	public CartPage cartPage;
	public CheckoutPage checkoutPage;

	@BeforeMethod(alwaysRun = true)
	public void setup(ITestContext context) {

		config = new ConfigReader();

		String browser = config.getBrowser();
		driver = WebDriverFactory.getDriver(browser);

		driver.get(config.getBaseUrl());
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		context.setAttribute("driver", driver);
		loginPage = new LoginPage(driver);
		productPage = new ProductPage(driver);
		cartPage = new CartPage(driver);
		checkoutPage = new CheckoutPage(driver);
		System.out.println("Browser Launched: " + config.getBrowser());
	}

	@AfterMethod(alwaysRun = true)
	public void teardown() {
		if (driver != null) {
			driver.quit();
		}
	}
}
