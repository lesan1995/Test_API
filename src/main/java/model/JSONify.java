package model;

import com.google.gson.JsonObject;

public abstract class JSONify {
	public abstract JsonObject toJSON();
	
	public String toJSONString() {
		return this.toJSON().toString();
	}
}
