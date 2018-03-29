package common;

import static io.restassured.RestAssured.given;
import java.lang.reflect.Method;
import com.google.gson.JsonObject;
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
	 * Excute method request with name method, path, header, and model to return
	 * @param nameMethod
	 * @param path of request
	 * @param header of request
	 * @param model to return
	 * @return response
	 */
	public Response Method(String nameMethod,String path,String header,String token,String body) {
		Object o=null;
		Object oo=null;
		String contentType="application/json";
		try {
			Class<?> classBy = Class.forName("common.Action");
			Method method = classBy.getMethod("getInstance");
			Method method2=classBy.getMethod(nameMethod, String.class,String.class,String.class,String.class);
			o=method.invoke(null);
			oo=method2.invoke(o, path,contentType.trim(),token,body);
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
	public Response POST(String path,String contentType,String token,String body) {
		try {
			Response response = given()
					.when()
					.headers("Authorization","bearer "+token,"Content-Type", contentType)
					.body(body)
					.post(path)
					.thenReturn();
			return response;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
		
	}
	/**
	 * Method get to server
	 * @param path
	 * @param header
	 * @param model
	 * @return
	 */
	public Response GET(String path,String contentType,String token,String body) {
		try {
			Response response = given()
					.when()
					.headers("Authorization","bearer "+token,"Content-Type", contentType)
					.body(body)
					.get(path)
					.thenReturn();
			return response;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
		
	}
	/**
	 * Method push to server
	 * @param path
	 * @param header
	 * @param model
	 * @return response
	 */
	public Response PUT(String path,String contentType,String token,String body) {
		try {
			Response response = given()
					.when()
					.headers("Authorization","bearer "+token,"Content-Type", contentType)
					.body(body)
					.put(path)
					.thenReturn();
			return response;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
		
	}
	/**
	 * Method delete to server
	 * @param path
	 * @param header
	 * @param model
	 * @return response
	 */
	public Response DELETE(String path,String contentType,String token,String body) {
		try {
			Response response = given()
					.when()
					.headers("Authorization","bearer "+token,"Content-Type", contentType)
					.body(body)
					.delete(path)
					.thenReturn();
			return response;
		}
		catch (Exception e) {
			e.printStackTrace();
			return null;
			// TODO: handle exception
		}
		
	}
	/**
	 * Get data from response
	 * @param respo
	 * @return
	 */
	public String getData(Response respo) {
		String data="";
		try {
			data=respo.getBody().asString();
			return data;
		}
		catch(Exception e) {
			return "";
		}
	}
	/**
	 * Get message from response
	 * @param respo
	 * @return
	 */
	public String getMessage(Response respo) {
		String message="";
		try {
			message=getJsonObject(respo).get("message").getAsString();
			return message;
		}
		catch(Exception e) {
			return "";
		}
	}
	/**
	 * Get code from response
	 * @param respo
	 * @return
	 */
	public String getCode(Response respo) {
		String code="";
		try {
			code=respo.getStatusCode()+"";
			return code;
		}
		catch(Exception e) {
			return "";
		}
	}
	
	
}
