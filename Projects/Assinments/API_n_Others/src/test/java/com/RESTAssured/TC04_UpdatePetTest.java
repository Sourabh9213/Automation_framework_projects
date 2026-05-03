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
public class TC04_UpdatePetTest {
	@Test
	public void updatePet() {
		// NOTE:
		// This test case also depends on TC02 (Add Pet API).
		// Ensure both test cases are executed in the same TestNG suite to maintain data continuity.
		Tags t = new Tags();
		t.setId(6969);
		t.setName("Abhi_mood_nhi");
		Category ct = new Category();
		ct.setId(9696);
		ct.setName("Cats");
		PetData pd = new PetData();
		pd.setPetID(TestDataStore.getPetId());
		pd.setCategory(ct);
		pd.setName("Manimau");
		pd.setPhotoUrls(Arrays.asList("https://picsum.photos/id/237/200/300"));
		pd.setTags(Arrays.asList(t));		
		Long petID = TestDataStore.getPetId();
		System.out.println("Using Pet ID: " + petID);

		Response res = given()
				.header("Content-type", "application/json")
				.header("Accept", "application/json")
				.body(pd)
				.when().put("https://petstore.swagger.io/v2/pet");		
		
		res.then().log().body();		
		int code = res.getStatusCode();
		Assert.assertEquals(code, 200);
		Assert.assertEquals(res.jsonPath().getString("name"), "Manimau");
		Assert.assertEquals(res.jsonPath().getString("category.name"),  "Cats");
		Assert.assertEquals(res.jsonPath().getString("tags[0].name"),  "Abhi_mood_nhi");
		System.out.println("Status Code is: " + code);		
		System.out.println("Name: " + res.jsonPath().getString("name"));
		System.out.println("Catogory_Name: " + res.jsonPath().getString("category.name"));
		System.out.println("Tags_Name: " + res.jsonPath().getString("tags.name"));
		}
}