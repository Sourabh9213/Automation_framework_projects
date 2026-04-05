package com.Telecom.Base;

import org.testng.annotations.BeforeTest;

import io.restassured.RestAssured;

public class BaseTest {
	 

	@BeforeTest
	public void setup() {
		RestAssured.baseURI = "https://thinking-tester-contact-list.herokuapp.com";

	}
}
