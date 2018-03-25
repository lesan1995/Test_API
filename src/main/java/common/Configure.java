package common;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class Configure {
	private String token="";
	private String username="rohan.ana@example.com";
	private String password="123123123";
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
	public void TestAPI(String method,String path,String header,String token,String body,String expected,int row) {
		String actual="";
		String result="";
		
		String codeExpect,messageExpect;
		codeExpect=expected.substring(expected.indexOf("Code:")+5).trim();
		
		try {
			
			messageExpect=expected.substring(expected.indexOf("\"")+1,expected.lastIndexOf("\""));
		}
		catch (Exception e) {
			// TODO: handle exception
			messageExpect="";
		}
		
		Response respo=Action.getInstance().Method(method.trim(),path.trim(),header,token, body);
		
		String codeActual=respo.getStatusCode()+"";
		String messageActual;
		try {
			messageActual=Action.getInstance().getJsonObject(respo).get("message").getAsString();
		}
		catch (Exception e) {
			messageActual="";
			// TODO: handle exception
		}
		actual="Message: \""+messageActual+"\"\nCode: "+codeActual;
		
		if(codeExpect.equals(codeActual)&&messageExpect.equals(messageActual))
			result="PASS";
		else result="FAIL";
		System.out.println(result);
		
		ExcelUtils.WriteResult(row, actual, result);
		
	}
}
