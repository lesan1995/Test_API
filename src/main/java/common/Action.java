package common;

import static io.restassured.RestAssured.given;
import java.lang.reflect.Method;
import java.util.Map;

import com.google.gson.JsonObject;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import io.restassured.response.Response;

public class Action {
	private static Action action=null;
	private Action() {
	}
	public static Action getInstance() {
		if(action==null) {
			action=new Action();
		}
		return action;
	}
	/**
	 * Get json object from reponse
	 * @param response
	 * @return json object
	 */
	public JsonObject getJsonObject(Response response) {
		JsonObject jsonObj = JSONParse.fromReponse(response.asString(), JsonObject.class);
		return jsonObj;
	}
	/**
	 * Excute method request with name methoad, path, header, and model to return
	 * @param nameMethod
	 * @param path of request
	 * @param header of request
	 * @param model to return
	 * @return response
	 */
	public Response Method(String nameMethod,String path,String token,Object model) {
		Object o=null;
		Object oo=null;
		try {
			Class<?> classBy = Class.forName("common.Action");
			Method method = classBy.getMethod("getInstance");
			Method method2=classBy.getMethod(nameMethod, String.class,String.class,Object.class);
			o=method.invoke(null);
			oo=method2.invoke(o, path,token,model);
			
			
			//Method method = classBy.getMethod(nameMethod, String.class,Map.class,Object.class);
			//method.invoke(null, path,header,model);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (Response) oo;
	}
	/**
	 * Method post to server
	 * @param path
	 * @param header
	 * @param model
	 * @return response
	 */
	public Response Post(String path,String token,Object model) {
		Response response = given()
				.when()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+token)
				.body(model, ObjectMapperType.GSON)
				.post(path)
				.thenReturn();
		return response;
	}
	/**
	 * Method get to server
	 * @param path
	 * @param header
	 * @param model
	 * @return
	 */
	public Response Get(String path,String token,Object model) {
		Response response = given()
				.when()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+token)
				.body(model, ObjectMapperType.GSON)
				.get(path)
				.thenReturn();
		return response;
	}
	/**
	 * Method push to server
	 * @param path
	 * @param header
	 * @param model
	 * @return response
	 */
	public Response Push(String path,String token,Object model) {
		Response response = given()
				.when()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+token)
				.body(model, ObjectMapperType.GSON)
				.put(path)
				.thenReturn();
		return response;
	}
	/**
	 * Method delete to server
	 * @param path
	 * @param header
	 * @param model
	 * @return response
	 */
	public Response Delete(String path,String token,Object model) {
		Response response = given()
				.when()
				.contentType(ContentType.JSON)
				.accept(ContentType.JSON)
				.headers("Authorization","Bearer "+token)
				.body(model, ObjectMapperType.GSON)
				.delete(path)
				.thenReturn();
		return response;
	}
	
}
