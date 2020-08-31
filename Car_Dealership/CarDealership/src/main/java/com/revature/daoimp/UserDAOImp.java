package com.revature.daoimp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.dao.UserDAO;
import com.revature.system.ConnFactory;
import com.revature.users.User;

public class UserDAOImp implements UserDAO {
	public static ConnFactory cf = ConnFactory.getInstance();
	@Override
	public void insertUser(String username, String password) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call INSERTUSER(?,?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setString(1, username);
		call.setString(2, password);
		call.execute();
		call.close();
	}

	@Override
	public List<User> getUserList() throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM USER_TBL";
		List<User> userList = new ArrayList<>();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		User u;
		while(rs.next()) {
			u = new User(
					rs.getInt(1),
					rs.getString(2),
					rs.getString(3),
					rs.getString(4)
					);
			userList.add(u);
		}
		return userList;
	}

}
