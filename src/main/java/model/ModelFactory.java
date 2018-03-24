package model;

public class ModelFactory {
	private static ModelFactory modelFactory=null;
	private ModelFactory() {
	}
	public static ModelFactory getInstance() {
		if(modelFactory==null) {
			modelFactory=new ModelFactory();
		}
		return modelFactory;
	}
	public JSONify getModel(String name) {
		switch (name) {
			case "LOGIN":return new LoginModel();
			default:return null;
		}
	}

}
