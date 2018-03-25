package common;

import java.lang.reflect.Type;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

public class JSONParse {
	/**
	 * Convert json string to json object
	 * @param response
	 * @param t
	 * @return
	 */
	public static JsonObject fromReponse(String response, Type t) {
		Gson gson = new Gson();
		return gson.fromJson(response, t);
	}
}
