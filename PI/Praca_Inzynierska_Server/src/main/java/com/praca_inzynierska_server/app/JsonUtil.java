package com.praca_inzynierska_server.app;

import com.google.gson.Gson;
import spark.ResponseTransformer;
/**
 * Klasa odpowiadaj�ca za wysy�anie informacji do kienta
 */
public class JsonUtil {
	/**
	 * Metoda konweryj�ca obiekt na JSON
	 * @param object - obiekt do konwersji
	 * @return String - wysy�a zamieniony obiekt na JSON
	 */
	public static String toJson(Object object) {
		return new Gson().toJson(object);
	}
	/**
	 * Metoda odpowiadaj�ca za konwersje wys�anego obiektu na JSON
	 * @return Wysy�a JSON
	 */
	public static ResponseTransformer json() {
		return JsonUtil::toJson;
	}
}