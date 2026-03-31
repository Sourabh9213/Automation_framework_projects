package com.Bstackdemo.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.Bstackdemo.Utils.WaitUtil;

public class LoginPage {

	private WebDriver driver;
	private WaitUtil WaitU;

	public LoginPage(WebDriver driver) {
		this.driver = driver;
		this.WaitU = new WaitUtil(driver);
	}

	private By signInLink = By.id("signin");
	private By usernameDropdown = By.id("username");
	private By passwordDropdown = By.id("password");
	private By loginBtn = By.id("login-btn");
	private By errorMsg = By.cssSelector(".api-error");
	private By logoutBtn = By.id("signin");

	// Dynamic Locators
	private By getUserOption(String username) {
		return By.xpath("//div[text()='" + username + "']");
	}

	private By getPasswordOption(String password) {
		return By.xpath("//div[text()='" + password + "']");
	}

	public void clickSignIn() {
		WaitU.waitUntilVisible(signInLink).click();
	}
	public void LogOut() {
		// Defensive logout: if the logout/signin element is visible, click it; otherwise no-op.
		try {
			// The demo app uses the same element id for sign-in toggle; try to click if present and clickable
			if (WaitU.waitUntilVisible(logoutBtn) != null) {
				try {
					WaitU.clickElement(logoutBtn);
				} catch (Exception e) {
					// If click fails (e.g., not clickable), attempt a fallback: no-op
				}
			}
		} catch (Exception e) {
			// element not present/visible - user likely already logged out; ignore
		}
	}

	public void selectUsername(String username) {
		WaitU.clickElement(usernameDropdown);
		driver.findElement(getUserOption(username)).click();
	}

	public void selectPassword(String password) {
		WaitU.clickElement(passwordDropdown);
		WaitU.waitUntilVisible(getPasswordOption(password)).click();
	}

	public void clickLogin() {
		WaitU.clickElement(loginBtn);
	}

	public void login(String username, String password) {
		clickSignIn();
		selectUsername(username);
		selectPassword(password);
		clickLogin();
	}

	public boolean isErrorDisplayed() {
		try {
			return WaitU.waitUntilVisible(errorMsg).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}

	public boolean isLoginSuccessful() {
		try {
			return WaitU.waitUntilVisible(logoutBtn).isDisplayed();
		} catch (Exception e) {
			return false;
		}
	}
}