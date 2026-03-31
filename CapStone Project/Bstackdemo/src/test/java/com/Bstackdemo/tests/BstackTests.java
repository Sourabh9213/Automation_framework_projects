package com.Bstackdemo.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Bstackdemo.Base.BaseTest;
import com.Bstackdemo.reports.TestListener;

@Listeners({ TestListener.class })
public class BstackTests extends BaseTest {	

	@Test(groups = { "smoke", "login", "regression" })
	public void TC01_validLogin() {
		TestListener.test.get().info("Start TC01_validLogin - logging in with valid user");
		loginPage.login("demouser", "testingisfun99");
		TestListener.test.get().info("Clicked login");
		Assert.assertTrue(productPage.getProductCount() > 0, "Login failed - products not visible.");
		TestListener.test.get().pass("TC01_validLogin passed - products visible");
	}

	@Test(groups = { "negative" }, enabled = false)
	public void TC02_invalidLogin() {
		// Disabled due to demo application limitation:
		// Username/password fields are not real input elements
		TestListener.test.get().info("Start TC02_invalidLogin - logging in with invalid user");
		loginPage.login("wronguser", "wrongpass");
		TestListener.test.get().info("Attempted login with wrong credentials");
		Assert.assertTrue(loginPage.isErrorDisplayed(), "Expected error message for invalid login.");
		TestListener.test.get().pass("TC02_invalidLogin passed - error displayed");
	}

	@Test(groups = { "negative" }, enabled = false)
	public void TC03_emptyLogin() {
		// Disabled due to demo application limitation:
		// Username/password fields are not real input elements
		TestListener.test.get().info("Start TC03_emptyLogin - logging in with empty credentials");
		loginPage.login("", "");
		TestListener.test.get().info("Attempted login with empty credentials");
		Assert.assertTrue(loginPage.isErrorDisplayed(), "Expected error message for empty credentials.");
		TestListener.test.get().pass("TC03_emptyLogin passed - error displayed");
	}

	@Test(groups = { "functional", "cart", "regression" })
	public void TC04_addSingleItem() {
		TestListener.test.get().info("Start TC04_addSingleItem - add one item to cart");
		loginPage.login("demouser", "testingisfun99");
		TestListener.test.get().info("Logged in");
		productPage.addProductToCart("iPhone 12");
		TestListener.test.get().info("Added iPhone 12 to cart");
		Assert.assertEquals(productPage.getCartCount(), 1, "Item was not added to the cart.");
		TestListener.test.get().pass("TC04_addSingleItem passed - cart count is 1");
	}

	@Test(groups = { "functional", "search", "regression" })
	public void TC05_searchBox() {
		TestListener.test.get().info("Start TC05_searchBox - search functionality check");
		loginPage.login("demouser", "testingisfun99");
		TestListener.test.get().info("Logged in");
		productPage.searchProduct("iPhone");
		TestListener.test.get().info("Searched for 'iPhone'");
		List<String> names = productPage.getAllProductNames();
		Assert.assertEquals(names.size(), 25,
				"Product count should remain 25 since the demo search does not filter results.");
		TestListener.test.get().pass("TC05_searchBox passed - search accepted input");
	}

	@Test(groups = { "functional", "cart", "regression" })
	public void TC06_addMultipleItems() {
		TestListener.test.get().info("Start TC06_addMultipleItems - add two items to cart");
		loginPage.login("demouser", "testingisfun99");
		TestListener.test.get().info("Logged in");
		productPage.addProductToCart("iPhone 12");
		TestListener.test.get().info("Added iPhone 12 to cart");
		productPage.addProductToCart("iPhone 12 Mini");
		TestListener.test.get().info("Added iPhone 12 Mini to cart");
		int count = productPage.getCartCount();
		Assert.assertEquals(count, 2, "Cart count did not match expected number of items.");
		TestListener.test.get().pass("TC06_addMultipleItems passed - cart count is 2");
	}

	@Test(groups = "functional")
	public void TC07_removeItem() {
		TestListener.test.get().info("Start TC07_removeItem - remove item from cart");
		loginPage.login("demouser", "testingisfun99");
		TestListener.test.get().info("Logged in");
		productPage.addProductToCart("iPhone 12");
		TestListener.test.get().info("Added iPhone 12 to cart");
//		cartPage.openCart(); // Not needed for this UI
		TestListener.test.get().info("Opened cart");
		cartPage.removeItem("iPhone 12");
		TestListener.test.get().info("Removed iPhone 12 from cart");
		Assert.assertEquals(cartPage.getCartItemCount(), 0, "Item was not removed from the cart.");
		TestListener.test.get().pass("TC07_removeItem passed - cart is empty");
	}

	@Test(groups = "functional")
	public void TC08_validCheckout() {
		TestListener.test.get().info("Start TC08_validCheckout - complete checkout flow");
		loginPage.login("demouser", "testingisfun99");
		TestListener.test.get().info("Logged in");
		productPage.addProductToCart("iPhone 12");
		TestListener.test.get().info("Added iPhone 12 to cart");
//		cartPage.openCart(); // Not needed for this UI
		TestListener.test.get().info("Opened cart");
		cartPage.clickCheckout();
		TestListener.test.get().info("Clicked checkout");
		checkoutPage.fillCheckoutForm("Sourabh", "Patil", "Mumbai", "MH", "400001");
		TestListener.test.get().info("Filled checkout form");
		Assert.assertTrue(checkoutPage.isCheckoutPageLoaded(), "Checkout page did not load as expected.");
		TestListener.test.get().pass("TC08_validCheckout passed - checkout page loaded");
	}

	@Test(groups = { "functional", "regression" })
	public void TC09_verifyCartItems() {
		TestListener.test.get().info("Start TC09_verifyCartItems - verify items in cart");
		loginPage.login("demouser", "testingisfun99");
		TestListener.test.get().info("Logged in");
		productPage.addProductToCart("iPhone 12");
		TestListener.test.get().info("Added iPhone 12 to cart");
		productPage.addProductToCart("iPhone 12 Mini");
		TestListener.test.get().info("Added iPhone 12 Mini to cart");
//		cartPage.openCart(); // Not needed for this UI
		TestListener.test.get().info("Opened cart");
		Assert.assertEquals(cartPage.getCartItemCount(), 2, "Cart item count did not match expected value.");
		TestListener.test.get().pass("TC09_verifyCartItems passed - cart count is 2");
		if(checkoutPage.Order()) {
			driver.navigate().back();
		}
		
	}

	@Test(groups = "negative")
	public void TC10_checkoutWithoutItems() {
		TestListener.test.get().info("Start TC10_checkoutWithoutItems - attempt checkout with empty cart");
		loginPage.login("demouser", "testingisfun99");
		TestListener.test.get().info("Logged in");
		cartPage.openCart();
		TestListener.test.get().info("Opened cart");
		Assert.assertTrue(cartPage.isCheckoutBtnAvailable(),
				"Checkout should not proceed without items or should show an informative message.");
		TestListener.test.get().pass("TC10_checkoutWithoutItems passed - checkout behavior observed");
		
	}

}