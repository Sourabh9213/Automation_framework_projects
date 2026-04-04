package com.Bstackdemo.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Bstackdemo.Base.BaseTest;

public class CartTests extends BaseTest {

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

	@Test(groups = { "functional", "regression" })
	public void TC08_verifyCartItems() {

		loginPage.login(config.validUser(), config.validPass());

		productPage.addProductToCart("iPhone 12");
		productPage.addProductToCart("iPhone 12 Mini");
		int actual = cartPage.getCartItemCount();
		Assert.assertEquals(actual, 2, "Cart item count mismatch. Expected 2 but found " + actual);
	}

}
