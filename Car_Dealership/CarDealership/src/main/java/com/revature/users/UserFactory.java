package com.revature.users;

import java.sql.SQLException;
import java.util.List;

import com.revature.daoimp.UserDAOImp;
import com.revature.system.LogThis;
import com.revature.system.Lot;

public class UserFactory {
	private List<User> users;
	private Username username;
	private Password password;
	private User user;

	public UserFactory() {
		super();
		this.users = Lot.getLotData().getUsers();
	}

	public boolean enterUsername(Username username) {
		if (!username.isValid()) {
			System.out.println("This is not a vaild username");
			return false;
		} else if (!username.isAvailable()) {
			System.out.println("This usernaem is not available");
			return false;
		}
		this.username = username;
		return true;
	}

	public boolean enterPassword(Password password) {
		if (username == null)
			System.out.println("Please enter a valid username first");
		else if (password.isValid()) {
			this.password = password;
			return true;
		}
		return false;
	}

	public void registerUser() {
		if (username != null && password != null) {
			this.user = new User(username,password);
			UserDAOImp udi = new UserDAOImp();
			try {
				udi.insertUser(
						username.getValue(),
						password.getValue()
						);
				LogThis.LogIt("info", user +"created");
			} catch (SQLException e) {
				LogThis.LogIt("debug", "function 'registerUser' SQL exception" );
				e.printStackTrace();
			}
		} else
			System.out.println("Invalid uesername/password");
	}

}
