package common;

import java.util.HashMap;
import java.util.Map;
public class Header {
	public static Map<String,String> getHeader(String token) {
		Map<String,String> headers=new HashMap<String,String>();
		headers.put("Authorization","Bearer "+ token);
		return headers;
		
	}
}
