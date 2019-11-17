package com.praca_inzynierska_server.app;

import com.google.gson.Gson;
import spark.ResponseTransformer;
/**
 * Klasa odpowiadaj¹ca za wysy³anie informacji do kienta
 */
public class JsonUtil {
	/**
	 * Metoda konweryj¹ca obiekt na JSON
	 * @param object - obiekt do konwersji
	 * @return String - wysy³a zamieniony obiekt na JSON
	 */
	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}
	/**
	 * Metoda odpowiadaj¹ca za konwersje wys³anego obiektu na JSON
	 * @return Wysy³a JSON
	 */
	public static ResponseTransformer json() {
		return JsonUtil::toJson;
	}
}