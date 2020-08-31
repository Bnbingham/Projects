package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.users.User;

public interface UserDAO {

	public void insertUser(String username,String password)throws SQLException;
	
	public List<User> getUserList() throws SQLException;
}
