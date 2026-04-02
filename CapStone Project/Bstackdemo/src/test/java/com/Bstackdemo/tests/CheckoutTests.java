package com.Bstackdemo.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Bstackdemo.Base.BaseTest;

public class CheckoutTests extends BaseTest {

	@Test(groups = "functional")
	public void TC09_validCheckout() {

		loginPage.login(config.validUser(), config.validPass());
		productPage.addProductToCart("iPhone 12");
//		cartPage.openCart(); // Not needed for this UI
		cartPage.clickCheckout();
		checkoutPage.fillCheckoutForm("Sourabh", "Patil", "Mumbai", "MH", "400001");
		Assert.assertTrue(checkoutPage.isCheckoutPageLoaded(), "Checkout page did not load as expected.");

	}

	@Test(groups = "negative")
	public void TC10_checkoutWithoutItems() {

		loginPage.login(config.validUser(), config.validPass());
		cartPage.openCart();
		Assert.assertTrue(cartPage.isCheckoutBtnAvailable(),
				"Checkout should not proceed without items or should show an informative message.");
	}

}
