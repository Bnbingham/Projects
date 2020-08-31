package com.revature.users;

import java.util.List;

import com.revature.system.Lot;

public class Username {
	private String value;

	public Username(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public boolean isValid() {
		// TODO regex for validating username
		return true;
	}

	public boolean isAvailable() {
		List<User> users = Lot.getLotData().getUsers();
		for (User user : users) {
			if (user.getUsername().value.equals(this.value))
				return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return value;
	}

	
}
