package com.Mobile.tests;

import java.time.Duration;

import org.testng.annotations.*;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.appmanagement.ApplicationState;
import com.Mobile.util.DriverUtil;

public class LaunchApplication {

    private AndroidDriver driver;
    private ApplicationState appState;

    String appPath = System.getProperty("user.dir") + "/testdata/ApiDemos-debug.apk";
    String appPkg = "io.appium.android.apis"; 

    @BeforeTest
    public void launchApp() throws Exception {

        driver = DriverUtil.getAndroidDriver(appPath);
        System.out.println("App launched. Session ID: " + driver.getSessionId());
    }

    @Test(priority = 1)
    public void checkApplicationState() {

        appState = driver.queryAppState(appPkg);
        System.out.println("Current App State: " + appState);
        if (appState == ApplicationState.RUNNING_IN_FOREGROUND) {
            System.out.println("App is running in foreground ");
        } else {
            System.out.println("App is NOT in foreground ");
        }
    }

    @Test(priority = 2)
    public void sendAppToBackgroundAndCheckState() throws Exception {

        driver.runAppInBackground(Duration.ofSeconds(5));// this code not work sometimes so i have new solution 
        driver.pressKey(new KeyEvent(AndroidKey.HOME)); // and this is the solution
        Thread.sleep(2000);
        appState = driver.queryAppState(appPkg);
        System.out.println("App State after background: " + appState);
        if (appState == ApplicationState.RUNNING_IN_BACKGROUND) {
            System.out.println("App moved to background ");
        } else {
            System.out.println("App NOT in background ");
        }
    }

    @Test(priority = 3)
    public void uninstallApp() {

        if (driver.isAppInstalled(appPkg)) {
            boolean removed = driver.removeApp(appPkg);
            System.out.println("App Uninstalled Successfully: " + removed);
        } else {
            System.out.println("App already not installed");
        }
    }

    
    
    
    @AfterTest
    public void tearDown() {

        if (driver != null) {
            driver.quit();
        }
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @AfterMethod
    public void breakforSS() throws InterruptedException {
    	Thread.sleep(10000);
    }
}