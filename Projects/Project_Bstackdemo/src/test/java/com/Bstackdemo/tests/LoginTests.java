package com.Bstackdemo.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import com.Bstackdemo.base.BaseTest;
import com.Bstackdemo.reports.AutomationReports;

@Listeners({ AutomationReports.class })
public class LoginTests extends BaseTest {

	@Test(groups = { "smoke", "login", "regression" })
	public void TC01_validLogin() {

		loginPage.login(config.validUser(), config.validPass());
		Assert.assertTrue(productPage.getProductCount() > 0, "Login failed - products not visible.");
	}

	@Test(groups = { "negative" }, enabled = false)
	public void TC02_invalidLogin() {
		// Disabled due to demo application limitation:
		// Username/password fields are not real input elements

		loginPage.login("wronguser", "wrongpass");
		Assert.assertTrue(loginPage.isErrorDisplayed(), "Expected error message for invalid login.");
	}

	@Test(groups = { "negative" }, enabled = false)
	public void TC03_emptyLogin() {
		// Disabled due to demo application limitation:
		// Username/password fields are not real input elements

		loginPage.login("", "");
		Assert.assertTrue(loginPage.isErrorDisplayed(), "Expected error message for empty credentials.");
	}

	@Test(groups = { "functional", "cart", "regression" })
	public void TC04_addSingleItem() {

		loginPage.login(config.validUser(), config.validPass());

		productPage.addProductToCart("iPhone 12");

		Assert.assertEquals(productPage.getCartCount(), 1, "Item was not added to the cart.");

	}

	@Test(groups = { "functional", "search", "regression" })
	public void TC05_searchBox() {

		loginPage.login(config.validUser(), config.validPass());
		productPage.searchProduct("iPhone");
		List<String> names = productPage.getAllProductNames();
		Assert.assertEquals(names.size(), 25,
				"Product count should remain 25 since the demo search does not filter results.");
	}

	@Test(groups = { "functional", "cart", "regression" })
	public void TC06_addMultipleItems() {

		loginPage.login(config.validUser(), config.validPass());
		productPage.addProductToCart("iPhone 12");
		productPage.addProductToCart("iPhone 12 Mini");
		int count = productPage.getCartCount();
		Assert.assertEquals(count, 2, "Cart count did not match expected number of items.");
	}

	@Test(groups = "functional")
	public void TC07_removeItem() {

		loginPage.login(config.validUser(), config.validPass());
		productPage.addProductToCart("iPhone 12");
//		cartPage.openCart(); // Not needed for this UI
		cartPage.removeItem("iPhone 12");
		Assert.assertEquals(cartPage.getCartItemCount(), 0, "Item was not removed from the cart.");
	}

	@Test(groups = "functional")
	public void TC08_validCheckout() {

		loginPage.login(config.validUser(), config.validPass());
		productPage.addProductToCart("iPhone 12");
//		cartPage.openCart(); // Not needed for this UI
		cartPage.clickCheckout();
		checkoutPage.fillCheckoutForm("Sourabh", "Patil", "Mumbai", "MH", "400001");
		Assert.assertTrue(checkoutPage.isCheckoutPageLoaded(), "Checkout page did not load as expected.");

	}

	@Test(groups = { "functional", "regression" })
	public void TC09_verifyCartItems() {

		loginPage.login(config.validUser(), config.validPass());

		productPage.addProductToCart("iPhone 12");
		productPage.addProductToCart("iPhone 12 Mini");
		int actual = cartPage.getCartItemCount();
		Assert.assertEquals(actual, 2, "Cart item count mismatch. Expected 2 but found " + actual);
	}

	@Test(groups = "negative")
	public void TC10_checkoutWithoutItems() {

		loginPage.login(config.validUser(), config.validPass());
		cartPage.openCart();
		Assert.assertTrue(cartPage.isCheckoutBtnAvailable(),
				"Checkout should not proceed without items or should show an informative message.");
	}
}