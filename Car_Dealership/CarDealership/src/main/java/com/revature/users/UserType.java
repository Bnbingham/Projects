package com.revature.users;

public class UserType {
	private String value;

	public UserType() {
		super();
		this.value = "Customer";
	}
	public UserType(String type) {
		super();
		this.value = type;
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
	
}
