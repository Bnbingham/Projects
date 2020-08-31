package com.revature.menu;

import java.util.List;

import com.revature.system.Lot;
import com.revature.users.Password;
import com.revature.users.User;
import com.revature.users.Username;

public class LogInChecker {
	private List<User> users;
	private boolean locatedUsername;
	private boolean matchingPassword;
	private User user;

	public LogInChecker() {
		super();
		this.users = Lot.getLotData().getUsers();
		this.locatedUsername = false;
		this.matchingPassword = false;
		this.user = null;
	}

	public boolean enterUsername(Username username) {
		for (User user : users) {
			if (user.getUsername().equals(username)) {
				this.user = user;
				this.locatedUsername = true;
				return true;
			}
		}
		return false;
	}

	public boolean enterPassword(Password password) {
		if(user == null || locatedUsername == false)
			System.out.println("Please enter a valid username first");
		else if(user.getPassword().equals(password)) {
			this.matchingPassword = true;
			return true;
		}
		return  false;
	}

	public void logIn() {
		if(locatedUsername = matchingPassword = true) {
			System.out.println("logged in");
			Lot.setCurrentUser(user);
		}
		else
			System.out.println("Invalid uesername/password");
	}
	public boolean isLocatedUsername() {
		return locatedUsername;
	}

	public boolean isMatchingPassword() {
		return matchingPassword;
	}
}
