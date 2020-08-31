package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.contracts.Payment;

public interface PaymentDAO {
	
	public void insertPayment(double value, int contractID) throws SQLException;

	public List<Payment> getPaymentList() throws SQLException;
}
