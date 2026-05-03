package com.Mobile.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.Mobile.util.DriverUtil;
import com.Mobile.util.ElementUtils;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.AppiumDriver;

public class DragDropTest {

    public AppiumDriver driver;

    @BeforeTest
    public void setup() throws Exception {

        String appPkg = "io.appium.android.apis";
        String appActvt = ".ApiDemos";
        driver = DriverUtil.launchInstalledApp(appPkg, appActvt);
        System.out.println("Session ID: " + driver.getSessionId());
    }

    @Test
    public void TC03_dragandDropTest() {

        // Click Views
        driver.findElement(AppiumBy.accessibilityId("Views")).click();
        
        // Click Drag and Drop
        driver.findElement(AppiumBy.accessibilityId("Drag and Drop")).click();
        ElementUtils.getScreenshot(driver, "Before_");
        
        // Drag
        WebElement red1 = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_3"));       
        ElementUtils.dragAndDropElement(driver, red1, 800, 1000);
        System.out.println("First RED Ball Drag and Drop executed");
        ElementUtils.getScreenshot(driver, "Red1_"); // After Drag capture screen shot         

        WebElement red2 = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));       
        ElementUtils.dragAndDropElement(driver, red2, 800, 1000);
        System.out.println("Second RED Ball Drag and Drop executed"); // After Drag capture screen shot
        ElementUtils.getScreenshot(driver, "Red2_");
                
    }
}