package com.mycompany.app;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Iterator;

import org.json.JSONException;
import org.json.JSONObject;

public class AppReader {

	public static JSONObject readJsonFromUrl(String url) throws IOException, JSONException {
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

	private static String readAll(Reader rd) throws IOException {
		StringBuilder sb = new StringBuilder();
		int cp;
		while ((cp = rd.read()) != -1) {
			sb.append((char) cp);
		}
		return sb.toString();
	}

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

	public static void main(String[] args) throws JSONException, IOException {

		String URL = "https://istheapplestoredown.com/api/v1/status/worldwide";

		JSONObject json = readJsonFromUrl(URL);

		JSONObject jsonNew = getJSON(json, "y");

		System.out.println("Yes");
		System.out.println(jsonNew);

		// OR

		// jsonNew = getJSON(json, "n");
		// System.out.println("No");
		// System.out.println(jsonNew);
	}
}