package com.blazedemo.utils;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WaitUtils {

    private WebDriverWait wait;

    public WaitUtils(WebDriver driver) {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public WebElement waitUntilVisible(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public WebElement waitUntilClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void click(WebElement element) {
        waitUntilClickable(element).click();
    }

    public String getText(WebElement element) {
        return waitUntilVisible(element).getText();
    }
}