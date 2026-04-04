package com.RESTAssured;

import static io.restassured.RestAssured.*;

import java.util.Arrays;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.PojoData.Category;
import com.PojoData.PetData;
import com.PojoData.Tags;
import com.PojoData.TestDataStore;

import io.restassured.response.Response;

public class TC02_CreatePetTest {

	@Test
	public void createPet() {
		Tags t = new Tags();
		t.setId(2025);
		t.setName("Gali_Ka_sher");
		Category ct = new Category();
		ct.setId(4545);
		ct.setName("Janwar");
		PetData pd = new PetData();
		pd.setCategory(ct);
		pd.setName("May_be_Dogs");
		pd.setPhotoUrls(Arrays.asList("https://picsum.photos/id/237/200/300"));
		pd.setTags(Arrays.asList(t));

		Response res = given()
				.header("Content-type", "application/json").header("Accept", "application/json").body(pd)
				.when().post("https://petstore.swagger.io/v2/pet");

		res.then().log().body();
		int code = res.getStatusCode();
		Assert.assertEquals(code, 200);
		System.out.println("Status Code is: " + code);		
		Assert.assertEquals(res.jsonPath().getString("name"), "May_be_Dogs");
		Assert.assertEquals(res.jsonPath().getString("category.name"),  "Janwar");
		Assert.assertEquals(res.jsonPath().getString("tags[0].name"),  "Gali_Ka_sher");
		long id = res.jsonPath().getLong("id");
		System.out.println("Generated ID: " + id);
		
		//for next TC or future ref. use i'll store that ID in TestDataStore
		TestDataStore.setPetId(id);
		System.out.println("TestDataStore PetID is: " + TestDataStore.getPetId());		
	}
}
