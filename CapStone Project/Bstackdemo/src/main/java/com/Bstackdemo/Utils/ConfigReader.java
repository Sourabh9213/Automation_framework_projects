package com.Bstackdemo.Utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

	Properties prop;

	public ConfigReader() {
		try {
			FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
			prop = new Properties();
			prop.load(fis);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public String getBrowser() {
		return prop.getProperty("browser");
	}

	public String getBaseUrl() {
		return prop.getProperty("url");
	}

	public String validUser() {
		return prop.getProperty("username");
	}

	public String validPass() {
		return prop.getProperty("password");
	}

}
