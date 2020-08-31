package com.revature.users;

public class Password {
	private String value;
	
	public Password(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}
	
	public boolean isValid() {
		//TODO regex to validate password
		return true;
	}
	
	@Override
	public String toString() {
		return value;
	}
	
}
