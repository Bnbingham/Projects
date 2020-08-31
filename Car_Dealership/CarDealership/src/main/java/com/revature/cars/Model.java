package com.revature.cars;

public class Model {
	private String value;

	public Model(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
	public boolean isValid() {
		//TODO create regex validator for model
		return true;
	}

	@Override
	public String toString() {
		return value;
	}

}
