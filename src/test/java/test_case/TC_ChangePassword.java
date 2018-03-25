package test_case;

import org.testng.annotations.Test;
import common.Configure;
import common.ExcelUtils;

import org.testng.annotations.BeforeClass;
//import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

public class TC_ChangePassword extends Configure {
	
	int row=1;
	@Parameters({ "APP_URL", "API_PREFIX", "AUTH_EMAIL", "AUTH_PASSWORD" })
	@BeforeClass
	public void beforeClass(String APP_URL, String API_PREFIX, String AUTH_EMAIL, String AUTH_PASSWORD) {
		init(APP_URL, API_PREFIX);
		Login(AUTH_EMAIL, AUTH_PASSWORD);
	}
	

	@Test(dataProvider="Data")
	public void ChangePassWord(String method,String path,String header,String body,String expected) {
		if(!getToken().equals("")) {
			TestAPI(method, path, header, getToken(), body, expected,row);
			row++;
		}
		
	}
	@DataProvider(name = "Data")
	public Object[][] validData() throws Exception {
		Object[][] testObjArray = null;
		try {
			testObjArray = ExcelUtils.getTableArray("src/main/java/data/API.xlsx", "Logout");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return (testObjArray);

	}


}