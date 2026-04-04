package com.RESTAssured;

import static io.restassured.RestAssured.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.PojoData.TestDataStore;

import io.restassured.response.Response;

public class TC03_GetPetByIdTest {
	
	@Test
	public void getPetById() {
		
		// NOTE:
		// This test case depends on TC02 (Add Pet API).
		// Ensure both test cases are executed in the same TestNG suite 
		// to maintain data continuity.
	    Long petID = TestDataStore.getPetId();

	    System.out.println("Using Pet ID: " + petID);

	    Response res = given()
	    		.when()
	            .get("https://petstore.swagger.io/v2/pet/" + petID);

	    res.then().log().body();

	    Assert.assertEquals(res.getStatusCode(), 200);	  
	    Assert.assertEquals(res.jsonPath().getString("name"), "May_be_Dogs");
	    Assert.assertEquals(res.jsonPath().getString("category.name"), "Janwar");		
		
	}
}
