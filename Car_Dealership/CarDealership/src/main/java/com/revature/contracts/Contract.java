package com.revature.contracts;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import com.revature.cars.Car;
import com.revature.daoimp.PaymentDAOImp;
import com.revature.users.User;

public class Contract {
	private int contractID;
	private int offerID; //car,user,price,length
	private List<Payment> payments = new ArrayList<>();//paid
	
	//for factory
	public Contract(int offerID, double downPayment) {
		super();
		this.offerID = offerID;
	}
	

	public Contract(int contractID, int offerID, ArrayList<Payment> payments) {
		super();
		this.contractID = contractID;
		this.offerID = offerID;
		this.payments = payments;
	}
	

	public int getContractID() {
		return contractID;
	}


	public void setContractID(int contractID) {
		this.contractID = contractID;
	}


	public int getOfferID() {
		return offerID;
	}


	public void setOfferID(int offerID) {
		this.offerID = offerID;
	}


	public List<Payment> getPayments() {
		return payments;
	}


	public void setPayments(List<Payment> payments) {
		this.payments = payments;
	}


	@Override
	public String toString() {
		//fix this to show better
		return 	""+payments;
	}
	
	
	

	

}
