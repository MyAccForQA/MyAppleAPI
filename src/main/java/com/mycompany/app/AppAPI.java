package com.mycompany.app;

import java.util.Iterator;

import org.json.JSONObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AppAPI {

	public static JSONObject getJSON(JSONObject json, String val) {

		JSONObject jsonTmp = new JSONObject();

		Iterator<String> itr = json.keys();
		while (itr.hasNext()) {

			String key = itr.next();
			JSONObject value = json.getJSONObject(key);

			if (value.get("status").equals(val))
				jsonTmp.put(key, value);
		}

		return jsonTmp;
	}

	public static void main(String[] args) {

		String URL = "https://istheapplestoredown.com/api/v1/status/worldwide";

		RequestSpecification request = RestAssured.given();
		Response resp = request.get(URL);

		JSONObject json = new JSONObject(resp.asString());

		JSONObject jsonNew = getJSON(json, "y");

		System.out.println("Yes");
		System.out.println(jsonNew);

		// OR

		// jsonNew = getJSON(json, "n");
		// System.out.println("No");
		// System.out.println(jsonNew);
	}
}