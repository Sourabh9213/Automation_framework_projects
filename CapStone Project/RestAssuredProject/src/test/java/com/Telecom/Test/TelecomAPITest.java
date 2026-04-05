package com.Telecom.Test;

import static io.restassured.RestAssured.*;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.Telecom.Base.BaseTest;
import com.Telecom.Pojo.ContactData;
import com.Telecom.Pojo.EmailUtil;
import com.Telecom.Pojo.UserPojo;
import com.Telecom.Util.TokenManager;

import io.restassured.response.Response;

public class TelecomAPITest extends BaseTest {
	
	public String Email;
	public String Pass;
	public String Contact_ID;

	@Test(priority = 1)
	public void TC01_addUser() {
		
		Email = EmailUtil.getDynamicEmail("Sourabh");
		Pass = "test@9213";	

		UserPojo up = new UserPojo();
		up.setFirstName("Sourabh");
		up.setLastName("Matade");
		up.setEmail(Email);
		up.setPassword(Pass);

		Response res = given()
				.header("Accept", "application/json")
				.header("Content-type", "application/json")
				.body(up)

				.when().post("/users");

		System.out.println("Status Line: " + res.getStatusLine());
		System.out.println("Response Body:\n" + res.asPrettyString());

		int statusCode = res.getStatusCode();
		Assert.assertEquals(statusCode, 201);
		System.out.println("Status Code: " + res.getStatusCode());
		// Saving Token For future TC
		String token = res.jsonPath().getString("token");
		TokenManager.token = token;
		System.out.println("========== TC01_AddUser PASSED ==========");

	}

	@Test(priority = 2, dependsOnMethods = "TC01_addUser")
	public void TC02_getUserdetails() {

		Response res = given()
				.header("Authorization", "Bearer " + TokenManager.token)
				.header("Content-type", "application/json")

				.when().get("/users/me");
		System.out.println("Status Line: " + res.getStatusLine());
		System.out.println("Response Body:\n" + res.asPrettyString());

		int statusCode = res.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		Assert.assertFalse(res.jsonPath().getString("firstName").isEmpty());
		Assert.assertTrue(res.jsonPath().getString("email").contains("@"));
		System.out.println("Status Code: " + res.getStatusCode());
		System.out.println("User ID     : " + res.jsonPath().getString("_id"));
		System.out.println("Email       : " + res.jsonPath().getString("email"));
		System.out.println("========== TC02_GetUserProfile PASSED ==========");

	}

	@Test(priority = 3, dependsOnMethods = "TC01_addUser")
	public void TC03_UpdateUser() {
		
		Email = EmailUtil.getDynamicEmail("Test");
		Pass = "Update@00022@9213";
		
		UserPojo up = new UserPojo();		
		up.setFirstName("UpdatedSourabh");
		up.setLastName("Staragile");
		up.setEmail(Email);
		up.setPassword(Pass);		

		Response res = given()
				.header("Authorization", "Bearer " + TokenManager.token)
				.header("Content-type", "application/json")
				.body(up)

				.when().patch("/users/me");
		System.out.println("Status Line: " + res.getStatusLine());
		System.out.println("Response Body:\n" + res.asPrettyString());

		int statusCode = res.getStatusCode();
		Assert.assertEquals(statusCode, 200);
		Assert.assertEquals(res.jsonPath().getString("firstName"),up.getFirstName());	
		System.out.println("Status Code : " + res.getStatusCode());
		System.out.println("User ID     : " + res.jsonPath().getString("_id"));
		System.out.println("Email       : " + res.jsonPath().getString("email"));
		System.out.println("========== TC03_UpdateUser PASSED ==========");

	}
	
	@Test(priority = 4, dependsOnMethods = "TC03_UpdateUser")
	public void TC04_loginviaNewUser() {
		UserPojo up = new UserPojo();
		up.setEmail(Email);
		up.setPassword(Pass);
		Response res = given()
				.header("Authorization", "Bearer " + TokenManager.token)
				.header("Content-type", "application/json")
				.body(up)

				.when().post("/users/login");
		System.out.println("Status Line: " + res.getStatusLine());
		System.out.println("Response Body:\n" + res.asPrettyString());

		int statusCode = res.getStatusCode();
		Assert.assertEquals(statusCode, 200);			
		System.out.println("Status Code: " + res.getStatusCode());
		System.out.println("========== TC04_LoginUser PASSED ==========");		
	}
	
	@Test(priority = 5, dependsOnMethods = "TC04_loginviaNewUser")
	public void TC05_addContact() {
		ContactData cd = new ContactData();
		cd.setFirstName("Saba");
		cd.setLastName("Shaikh");
		cd.setEmail(EmailUtil.getDynamicEmail(cd.getFirstName()));
		cd.setBirthdate("1990-11-13");
		cd.setPhone("8989898989");
		cd.setStreet1("Wadala Rto");
		cd.setStreet2("Wadala truck ternminal");
		cd.setCity("Mumbai");
		cd.setPostalCode("400037");
		cd.setStateProvince("MH");
		cd.setCountry("India");
		
		Response res = given()
				.header("Authorization", "Bearer " + TokenManager.token)
				.header("Accept", "application/json")
				.header("Content-type", "application/json")
				.cookie("token="+TokenManager.token)
				.body(cd)

				.when().post("/contacts");
		System.out.println("Status Line: " + res.getStatusLine());
		System.out.println("Response Body:\n" + res.asPrettyString());

		int statusCode = res.getStatusCode();
		Assert.assertEquals(statusCode, 201);			
		System.out.println("Status Code: " + res.getStatusCode());
		Contact_ID = res.jsonPath().getString("_id");
		Assert.assertEquals(res.jsonPath().getString("firstName"), "Saba");
		System.out.println("========== TC05_AddContact PASSED ==========");
		
	}
	
	@Test(priority = 6, dependsOnMethods = "TC05_addContact")
	public void TC06_getContactList() {
		
		Response res = given()
				.header("Authorization", "Bearer " + TokenManager.token)
				.header("Accept", "application/json")
				.header("Content-type", "application/json")
				.cookie("token="+TokenManager.token)
				

				.when().get("/contacts");
		System.out.println("Status Line: " + res.getStatusLine());
		System.out.println("Response Body:\n" + res.asPrettyString());

		int statusCode = res.getStatusCode();
		Assert.assertEquals(statusCode, 200);			
		System.out.println("Status Code: " + res.getStatusCode());
		System.out.println("========== TC06_getContactList PASSED ==========");
		
	}
	
	@Test(priority = 7, dependsOnMethods = "TC05_addContact")
	public void TC07_getContact() {
		
		Response res = given()
				.header("Authorization", "Bearer " + TokenManager.token)
				.header("Accept", "application/json")
				.header("Content-type", "application/json")
				.cookie("token="+TokenManager.token)
				

				.when().get("/contacts/"+Contact_ID);
		System.out.println("Status Line: " + res.getStatusLine());
		System.out.println("Response Body:\n" + res.asPrettyString());

		int statusCode = res.getStatusCode();
		Assert.assertEquals(statusCode, 200);			
		System.out.println("Status Code: " + res.getStatusCode());
		System.out.println("========== TC07_getContact PASSED ==========");
		
	}
	
	@Test(priority = 8, dependsOnMethods = "TC05_addContact")
	public void TC08_updateContact() {
		
		ContactData cd = new ContactData();
		cd.setFirstName("Rohit");
		cd.setLastName("Sharma");
		cd.setEmail(EmailUtil.getDynamicEmail(cd.getFirstName()));
		cd.setBirthdate("1987-04-30");
		cd.setPhone("4545454545");
		cd.setStreet1("Marin Drive");
		cd.setStreet2("Near Wankhede Statidum");
		cd.setCity("Mumbai");
		cd.setPostalCode("400020");
		cd.setStateProvince("MH");
		cd.setCountry("India");
		
		Response res = given()
				.header("Authorization", "Bearer " + TokenManager.token)
				.header("Accept", "application/json")
				.header("Content-type", "application/json")
				.cookie("token="+TokenManager.token)
				.body(cd)
				

				.when().put("/contacts/"+Contact_ID);
		System.out.println("Status Line: " + res.getStatusLine());
		System.out.println("Response Body:\n" + res.asPrettyString());

		int statusCode = res.getStatusCode();
		Assert.assertEquals(statusCode, 200);			
		System.out.println("Status Code: " + res.getStatusCode());
		System.out.println("========== TC08_updateContact PASSED ==========");
		
	}
	
	@Test(priority = 9, dependsOnMethods = "TC05_addContact")
	public void TC09_PatchContact() {
		
// Using HashMap to avoid null fields issue from POJO in PATCH request
		HashMap<String, Object> Data = new HashMap<>();
		Data.put("firstName", "Virat");
		Data.put("lastName", "Kolhli");	
		
		Response res = given()
				.header("Authorization", "Bearer " + TokenManager.token)
				.header("Accept", "application/json")
				.header("Content-type", "application/json")
				.cookie("token="+TokenManager.token)
				.body(Data)				

				.when().patch("/contacts/"+Contact_ID);
		System.out.println("Status Line: " + res.getStatusLine());
		System.out.println("Response Body:\n" + res.asPrettyString());

		int statusCode = res.getStatusCode();
		Assert.assertEquals(statusCode, 200);			
		System.out.println("Status Code: " + res.getStatusCode());
		Assert.assertEquals(res.jsonPath().getString("firstName"), Data.get("firstName").toString());
		System.out.println("========== TC09_UpdateContactByPatch PASSED ==========");
		
	}
	
	@Test(priority = 10, dependsOnMethods = "TC01_addUser")
	public void TC10_LogOutUser() {
		
		Response res = given()
				.header("Authorization", "Bearer " + TokenManager.token)
				.header("Accept", "application/json")
				.header("Content-type", "application/json")
				.cookie("token="+TokenManager.token)
						

				.when().post("/users/logout");
		System.out.println("Status Line: " + res.getStatusLine());
		System.out.println("Response Body:\n" + res.asPrettyString());

		int statusCode = res.getStatusCode();
		Assert.assertEquals(statusCode, 200);			
		System.out.println("Status Code: " + res.getStatusCode());		
		System.out.println("========== TC10_LogOutUser PASSED ==========");
		
	}
	

}
