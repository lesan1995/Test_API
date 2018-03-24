package test_case;

import org.testng.annotations.Test;

import common.Action;
import common.Configure;
import model.ChangePassModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class TC_Login extends Configure {
	

	@Parameters({ "APP_URL", "API_PREFIX", "AUTH_EMAIL", "AUTH_PASSWORD" })
	@BeforeMethod
	public void beforeMethod(String APP_URL, String API_PREFIX, String AUTH_EMAIL, String AUTH_PASSWORD) {
		init(APP_URL, API_PREFIX, AUTH_EMAIL, AUTH_PASSWORD);
	}

	@Test()
	public void verifyUserCanLoginWithValidData() {

		ChangePassModel change=new ChangePassModel("123123123", "456456456");
		System.out.println(
				
				Action.getInstance().Method("Post","/auth/change-password",getToken(), change)
				.getStatusLine()
				
	);
		

	}


}