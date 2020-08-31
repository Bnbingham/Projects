package com.revature.system;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnFactory {
	private static ConnFactory cf;
	
	private ConnFactory() {
		super();
	}
	public static synchronized ConnFactory getInstance() {
		if(cf == null)
			cf = new ConnFactory();
		return cf;
	}
	public Connection getConnection() {
		String url = "jdbc:oracle:thin:@java2004usf.c3ze8kvqgwxn.us-east-2.rds.amazonaws.com:1521:ORCL";
		String user = "cardealership";
		String password = "Pa55word";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
