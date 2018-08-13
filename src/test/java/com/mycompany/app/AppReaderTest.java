package com.mycompany.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

public class AppReaderTest extends MyJSON{

	public final String URL = "https://istheapplestoredown.com/api/v1/status/worldwide";

	public JSONObject json;
	public JSONObject jsonNew;

	public JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
		InputStream is = new URL(url).openStream();
		try {
			BufferedReader rd = new BufferedReader(new InputStreamReader(is, Charset.forName("UTF-8")));
			String jsonText = readAll(rd);
			JSONObject json = new JSONObject(jsonText);
			return json;
		} finally {
			is.close();
		}
	}

	private String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

	@BeforeSuite
	public void setUpBeforeSuite() throws IOException, JSONException {
		System.out.println("@BeforeSuite - AppReaderTest");

		json = readJsonFromUrl(URL);
	}

	@BeforeMethod
	public void setUpBeforeMethod() {
		System.out.println("@BeforeMethod - AppReaderTest");
	}

	@AfterMethod
	public void setDownAfterMethod() {
		System.out.println("@AfterMethod - AppReaderTest");
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