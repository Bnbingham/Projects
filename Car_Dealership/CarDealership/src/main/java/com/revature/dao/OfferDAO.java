package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.contracts.Offer;

public interface OfferDAO {
	
	public void insertOffer(int carID, int userID,double downpayment,int termLength)throws SQLException;
	
	public List<Offer> getOfferList() throws SQLException;
	
	public void updateOfferStatus(int offerID,int carID,int userID,double downPayment,String newStatus) throws SQLException;
}
