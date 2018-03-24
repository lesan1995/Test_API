package model;

import com.google.gson.JsonObject;

public class ChangePassModel extends JSONify{
	public String getCurrentPass() {
		return currentPass;
	}

	public void setCurrentPass(String currentPass) {
		this.currentPass = currentPass;
	}

	public String getNewPass() {
		return newPass;
	}

	public void setNewPass(String newPass) {
		this.newPass = newPass;
	}

	private String currentPass;
	public ChangePassModel(String currentPass, String newPass) {
		this.currentPass = currentPass;
		this.newPass = newPass;
	}

	private String newPass;

	@Override
	public JsonObject toJSON() {
		JsonObject json = new JsonObject();
		json.addProperty("current_password", this.getCurrentPass());
		json.addProperty("new_password", this.getNewPass());
		json.addProperty("new_password_confirmation", this.getNewPass());
		return json;
	}

}
