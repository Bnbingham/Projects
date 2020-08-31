package com.revature.cars;

public class Color {
	private String value;

	public Color(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	public boolean isValid() {
		// TODO color validation
		return true;
	}

}
