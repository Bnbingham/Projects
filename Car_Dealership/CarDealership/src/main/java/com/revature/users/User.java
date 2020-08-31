package com.revature.users;

public class User {
	private int userID;
	private Username username;
	private Password password;
	private UserType type;
	
	public User(Username username, Password password) {
		super();
		this.username = username;
		this.password = password;
	}
	public User(int id,String username, String password, String type) {
		super();
		this.userID = id;
		this.username = new Username(username);
		this.password = new Password(password);
		this.type = new UserType(type);
	}
	public Username getUsername() {
		return username;
	}
	public void setUsername(Username username) {
		this.username = username;
	}
	public Password getPassword() {
		return password;
	}
	public void setPassword(Password password) {
		this.password = password;
	}
	public UserType getType() {
		return type;
	}
	public void setType(UserType type) {
		this.type = type;
	}
	@Override
	public String toString() {
		return "User "+userID+" [username=" + username + 
				", password=" + password + 
				", type=" + type + "]\n";
	}
	public int getID() {
		return userID;
	}

	
}
