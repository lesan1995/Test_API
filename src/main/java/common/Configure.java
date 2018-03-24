package common;

import java.util.logging.Logger;
import org.testng.Assert;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import model.LoginModel;
import model.ModelFactory;

public class Configure {
	public static Logger Log = Logger.getLogger(Logger.class.getName());
	private String token;
	/**
	 * Init configure to get api
	 * @param APP_URL
	 * @param API_PREFIX
	 * @param AUTH_EMAIL
	 * @param AUTH_PASSWORD
	 */
	public void init(String APP_URL, String API_PREFIX, String AUTH_EMAIL, String AUTH_PASSWORD) {
		RestAssured.baseURI = APP_URL + "/" + API_PREFIX;
		LoginModel login=(LoginModel) ModelFactory.getInstance().getModel("LOGIN");
		login.Email(AUTH_EMAIL);login.Password(AUTH_PASSWORD);
		Response repon=Action.getInstance().Method("Post","/auth/login","", login);
		this.token=Action.getInstance().getJsonObject(
				repon).get("access_token").getAsString();
		if(repon.getStatusCode()==200) System.out.println("Login success");
		
	}
	/**
	 * Get token
	 * @return
	 */
	public String getToken() {
		return token;
	}

	/**
	 * Verify condition true with assert TestNG
	 * @param condition
	 * @return
	 */
	public boolean verifyTrue(Boolean condition) {
		try {
			Assert.assertTrue(condition);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	/**
	 * Verify condition false with assert TestNG
	 * @param condition
	 * @return
	 */
	public boolean verifyFalse(Boolean condition) {
		try {
			Assert.assertFalse(condition);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
