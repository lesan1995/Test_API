package common;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.google.gson.JsonObject;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Configure {
	private String token="";
	private String username="";
	private String password="";
	/**
	 * Init configure to get api
	 * @param APP_URL
	 * @param API_PREFIX
	 * @param AUTH_EMAIL
	 * @param AUTH_PASSWORD
	 */
	public void init(String APP_URL, String API_PREFIX) {
		RestAssured.baseURI = APP_URL + "/" + API_PREFIX;
	}
	/**
	 * Login to api server
	 */
	public void Login() {
		Login(this.username,this.password);
	}
	public void Login(String username,String password) {

		String body= "{\"email\":\""+username+"\",\"password\":\""+password+"\"}";
		String header="\"Content-Type: application/json\r\n" + 
				"Authorization: Bearer {{ACCESS_TOKEN}}\"\r\n" + 
				"";

		Response repon=Action.getInstance().Method("POST","/auth/login",header,"", body);
		
		if(repon.getStatusCode()==200) {
			this.username=username;
			this.password=password;
			System.out.println("Login success");
			this.token=Action.getInstance().getJsonObject(
					repon).get("access_token").getAsString();
			System.out.println(token);
		}
		else {
			System.out.println("Login Fail");
		}
	}
	/**
	 * Get token
	 * @return
	 */
	public String getToken() {
		return token;
	}
	/**
	 * Test Api with
	 * @param method
	 * @param path
	 * @param header
	 * @param token
	 * @param body
	 * @param expected
	 * @param row
	 */
	public void TestAPI(String method,String path,String header,String token,String body,String messageExpect,String codeExpect,String dataExpect,int row) {
		Response respo=Action.getInstance().Method(method.trim(),path.trim(),header,token, body);
		if(respo==null) {
			ExcelUtils.WriteResult(row,"","", "", "SKIP");
			System.out.println("SKIP");
			return;
		}
		String messageActual=Action.getInstance().getMessage(respo);
		String codeActual=Action.getInstance().getCode(respo);
		
		//So sanh data
		boolean resultDataEqual=true;
		JsonObject jsDataActualNew=new JsonObject();
		JsonObject jsDataActual=Action.getInstance().getData(respo);
		JsonObject jsDataExpect;
		try {
			
			jsDataExpect=JSONParse.fromReponse(dataExpect, JsonObject.class);
			
		}
		catch (Exception e) {
			jsDataExpect=JSONParse.fromReponse("", JsonObject.class);
			// TODO: handle exception
		}
		Pattern pattern = Pattern.compile("\"(.*?)\":");
		Matcher matcher = pattern.matcher(dataExpect);
		while (matcher.find())
		{
			String tmpMemberName=matcher.group(1);
			String tmpValueExpect=jsDataExpect.get(tmpMemberName).getAsString().toString();
			String tmpValueActual;
			
			try {
				tmpValueActual=jsDataActual.get(tmpMemberName).getAsString();
				jsDataActualNew.addProperty(tmpMemberName, tmpValueActual);
			}
			catch (Exception e) {
				tmpValueActual="";
				// TODO: handle exception
			}
			resultDataEqual=resultDataEqual&&(tmpValueActual.equals(tmpValueExpect));
			System.out.println("tmpValueExpect:"+tmpValueExpect+",tmpValueActua:"+tmpValueActual+",result:"+resultDataEqual);
		}
		String dataActual=jsDataActualNew.getAsJsonObject().toString();
		
		String result="";
		if(messageActual.equals(messageExpect.trim())&&codeActual.equals(codeExpect.trim())&&resultDataEqual)
			result="PASS";
		else result="FAIL";
		System.out.println(result);
		ExcelUtils.WriteResult(row, messageActual,codeActual,dataActual, result);
	}
}
