package com.RESTAssured;


import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.PojoData.TestDataStore;

import io.restassured.response.Response;

public class TC05_DeletePetTest {
	
	@Test
	public void TC05DeledeletePet() {
		
		Long petID = TestDataStore.getPetId();
	    System.out.println("Using Pet ID: " + petID);
		
		Response res = given()
						.header("Accept","application/json")
		
						.when().delete("https://petstore.swagger.io/v2/pet/" +petID);
		
		res.then().log().body();
		
		int code = res.getStatusCode();
		Assert.assertEquals(code, 200);
		
		Assert.assertEquals(res.jsonPath().getString("message"), String.valueOf(petID));
		
		
	}

	
	
}
