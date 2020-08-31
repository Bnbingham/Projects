package com.revature.contracts;

import java.sql.SQLException;

import com.revature.cars.Car;
import com.revature.daoimp.OfferDAOImp;
import com.revature.system.LogThis;
import com.revature.users.User;

public class OfferFactory {
	private Car car;
	private User user;
	private DownPayment downPayment;
	private TermLoanLength termLoanLength;
	private Offer offer;

	public OfferFactory(Car car, User user) {
		super();
		this.car = car;
		this.user = user;
	}

	public void enterDownPayment(double input) {
		DownPayment tempDownPayment = new DownPayment(input);
		if (downPayment.isValid())
			this.downPayment = tempDownPayment;
		else {
			System.out.println(downPayment.getValue() + " is not a valid amount");
		}
	}

	public void enterTermLoanLength(int input) {
		TermLoanLength tempTermLoanLength = new TermLoanLength(input);
		if (termLoanLength.isValid())
			this.termLoanLength = tempTermLoanLength;
		else {
			System.out.println(termLoanLength.getLength() + " is not a valid length");
		}
	}

	public void registerThisOffer() {
		if (car != null && user != null && downPayment != null && termLoanLength != null) {
			OfferDAOImp odi = new OfferDAOImp();
			this.offer = new Offer(car, user, downPayment, termLoanLength);
			try {
				odi.insertOffer(
						car.getID(),
						user.getID(),
						downPayment.getValue(),
						termLoanLength.getLength());
				LogThis.LogIt("info", offer +"created");
			} catch (SQLException e) {
				LogThis.LogIt("debug", "function 'registerThisOffer' SQL exception" );
				e.printStackTrace();
			}
		}
			
		else {
			System.out.println("Please enter offer information");
		}
	}
	
	public Offer thisNewOffer() {
		if(offer == null)
			System.out.println("Contract not yet created");
		else {
			return offer;
		}
		return null;
	}

}
