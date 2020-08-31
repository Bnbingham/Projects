package com.revature.daoimp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.contracts.Payment;
import com.revature.dao.PaymentDAO;
import com.revature.system.ConnFactory;

public class PaymentDAOImp implements PaymentDAO {
	
	public static ConnFactory cf = ConnFactory.getInstance();
	
	@Override
	public void insertPayment(double value, int contractID) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call INSERTPAYMENT(?,?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setDouble(1, value);
		call.setInt(2, contractID);
		call.execute();
		call.close();
	}

	@Override
	public List<Payment> getPaymentList() throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM PAYMENT_TBL";
		List<Payment> paymentList = new ArrayList<>();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		Payment p = null;
		while(rs.next()) {
			p = new Payment(
					rs.getInt(1),
					rs.getDouble(2),
					rs.getInt(3));
			paymentList.add(p);
		}
		return paymentList;
	}
	
}
