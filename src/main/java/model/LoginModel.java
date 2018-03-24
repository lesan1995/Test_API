package model;

import com.google.gson.JsonObject;

public class LoginModel extends JSONify {

	private String email;
	private String password;
	public LoginModel Email(String email) {
		this.email = email;
		return this;
	}

	public LoginModel Password(String password) {
		this.password = password;
		return this;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}

	@Override
	public JsonObject toJSON() {
		JsonObject json = new JsonObject();
		json.addProperty("email", this.getEmail());
		json.addProperty("password", this.getPassword());
		return json;
	}
}
