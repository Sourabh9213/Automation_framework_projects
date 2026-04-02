package com.DockerTests;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class ParallelTests {

	public WebDriver driver;

	@Parameters({ "bname" })
	@Test
	public void setup(String bname) throws MalformedURLException {

		if (bname.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
			driver.get("https://www.amazon.in");
			System.out.println("Launching Chrome");

		} else if (bname.equalsIgnoreCase("firefox")) {
			FirefoxOptions options = new FirefoxOptions();
			driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
			driver.get("https://www.amazon.in");
			System.out.println("Launching Firefox");

		} else if (bname.equalsIgnoreCase("edge")) {
			EdgeOptions options = new EdgeOptions();
			driver = new RemoteWebDriver(new URL("http://localhost:4444"), options);
			driver.get("https://www.amazon.in");
			System.out.println("Launching MicrosoftEdge");
		}
	}

	@AfterMethod
	public void teardown() throws InterruptedException {
		Thread.sleep(20000);
		driver.quit();
	}

}
