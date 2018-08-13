package com.mycompany.app;

import java.util.Iterator;
import org.json.JSONObject;

public class MyJSON {

	public final String URL = "https://istheapplestoredown.com/api/v1/status/worldwide";

	public JSONObject json;
	public JSONObject jsonNew;

	public JSONObject getJSON(JSONObject json, String val) {

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
}