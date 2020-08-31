package com.revature.contracts;

import com.revature.cars.Car;
import com.revature.users.User;

public class Offer {
	private int offerID;
	private int carID;
	private int userID;
	private DownPayment downPayment;
	private TermLoanLength termLoanLength;
	private OfferStatus offerStatus;

	//for offerFactory to sql
	public Offer(Car car,User user,DownPayment downPayment, TermLoanLength termLoanLength) {
		super();
		this.carID = car.getID();
		this.userID = user.getID();
		this.downPayment = downPayment;
		this.termLoanLength = termLoanLength;
		this.offerStatus = new OfferStatus("Pending");
	}
	//from SQL
	public Offer(int offerID, int carID, int userID, double downPayment, int termLength, String status) {
		super();
		this.carID = carID;
		this.userID = userID;
		this.offerID = offerID;
		this.downPayment = new DownPayment(downPayment);
		this.termLoanLength = new TermLoanLength(termLength);
		this.offerStatus = new OfferStatus(status);
	}

	public DownPayment getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(DownPayment downPayment) {
		this.downPayment = downPayment;
	}

	public TermLoanLength getTermLoanLength() {
		return termLoanLength;
	}

	public void setTermLoanLength(TermLoanLength termLoanLength) {
		this.termLoanLength = termLoanLength;
	}

	public OfferStatus getOfferStatus() {
		return offerStatus;
	}

	public void setOfferStatus(OfferStatus offerStatus) {
		this.offerStatus = offerStatus;
	}

	public int getOfferID() {
		return offerID;
	}
	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}
	public int getCarID() {
		return carID;
	}
	public void setCarID(int carID) {
		this.carID = carID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	@Override
	public String toString() {
		return "Offer "+offerID+
				"[downPayment= " + downPayment.getValue() +
				", termLoanLength =" + termLoanLength.getLength()+
				", offerStatus= "+ offerStatus.getValue() +
				" ]\n";
	}
	public int getID() {
		return offerID;
	}

}
