package com.revature.daoimp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.contracts.Offer;
import com.revature.dao.OfferDAO;
import com.revature.system.ConnFactory;

public class OfferDAOImp implements OfferDAO{
	public static ConnFactory cf = ConnFactory.getInstance();
	
	@Override
	public void insertOffer(int carID, int userID,double downpayment, int termLength) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call INSERTOFFER(?,?,?,?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setInt(1, carID);
		call.setInt(2, userID);
		call.setDouble(3, downpayment);
		call.setInt(4, termLength);
		call.execute();
		call.close();
	}

	@Override
	public List<Offer> getOfferList() throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "SELECT * FROM OFFER_TBL";
		List<Offer> offerList = new ArrayList<>();
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		Offer o = null;
		while(rs.next()) {
			o = new Offer(rs.getInt(1),
					rs.getInt(2),
					rs.getInt(3),
					rs.getDouble(4),
					rs.getInt(5),
					rs.getString(6)
					);
			offerList.add(o);
		}
		return offerList;
	}

	@Override
	public void updateOfferStatus(int offerID, int carID, int userID, double downPayment, String newStatus) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call UPDATEOFFER_STATUS(?,?,?,?,?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setInt(1, offerID);
		call.setInt(2, carID);
		call.setInt(3, userID);
		call.setDouble(4, downPayment);
		call.setString(5, newStatus);
		call.execute();
		call.close();
	}

}
