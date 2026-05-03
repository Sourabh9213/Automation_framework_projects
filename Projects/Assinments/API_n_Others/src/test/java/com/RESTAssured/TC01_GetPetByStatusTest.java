package com.RESTAssured;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

import org.testng.Assert;
import org.testng.annotations.Test;





public class TC01_GetPetByStatusTest {
	@Test
	public void getPetByStatus() {

		Response res = given()

				.header("Content-type", "application/json").header("Accept", "application/json")

				.when().get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");

		res.then().log().body();

		int code = res.getStatusCode();
		Assert.assertEquals(code, 200);
		System.out.println("Status Code is: " + code);

		String status = res.jsonPath().getString("[0].status");
		System.out.println("First Pet Status: " + status);
		Assert.assertEquals(status, "available");

		String name = res.jsonPath().getString("[0].name");
		System.out.println("Pet Name: " + name);

	}
}
