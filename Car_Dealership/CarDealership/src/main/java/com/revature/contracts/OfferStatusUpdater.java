package com.revature.contracts;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import com.revature.daoimp.OfferDAOImp;
import com.revature.system.LogThis;
import com.revature.system.Lot;

public class OfferStatusUpdater {
	private Offer offer;
	private int offerID;
	private int carID;
	private int userID;
	private double downPayment;
	private String newStatus;
	
	public OfferStatusUpdater(int offerID) {
		super();
		Lot lot = Lot.getLotData();
		List<Offer> offerList = lot.getOffers();
		Iterator<Offer> itr = offerList.iterator();
		while(itr.hasNext()) {
			Offer thisOffer = itr.next();
			if(thisOffer.getID() == offerID)
				this.offer = thisOffer;
		}
		this.offerID = offerID;
		this.carID = offer.getCarID();
		this.userID = offer.getUserID();
		this.downPayment = offer.getDownPayment().getValue();
	}
	
	public void enterNewStatus(OfferStatus newStatus) {
		if(newStatus.isValid())
			this.newStatus = newStatus.getValue();
		System.out.println(newStatus.getValue() +" is not a valid status");
	}
	
	public void updateOfferStatus() {
		if(offer != null && newStatus != null) {
			OfferDAOImp odi = new OfferDAOImp();
			try {
				odi.updateOfferStatus(offerID, carID, userID, downPayment, newStatus);
				LogThis.LogIt("info", offer +"updated");
			} catch (SQLException e) {
				LogThis.LogIt("error", "function 'updateOfferStatus' SQL exception" );
				e.printStackTrace();
			}
		}
	}
}
