package com.mycompany.app;

import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AppAPITest extends MyJSON {

	@BeforeSuite
	public void setUpBeforeSuite() {
		System.out.println("@BeforeSuite");

		RequestSpecification request = RestAssured.given();
		Response resp = request.get(URL);

		json = new JSONObject(resp.asString());
	}

	@BeforeMethod
	public void setUpBeforeMethod() {
		System.out.println("@BeforeMethod");

	}

	@AfterMethod
	public void setDownAfterMethod() {
		System.out.println("@AfterMethod");
	}

	@Test
	public void testNo1_Yes() {

		jsonNew = getJSON(json, "y");

		System.out.println("Result: " + jsonNew);

		String act = jsonNew.getJSONObject("id").getString("name");
		String exp = "Indonesia";

		// Assert.assertEquals(actual, expected, message);
		Assert.assertEquals(act, exp, "Error");
	}
}