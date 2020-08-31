package com.revature.contracts;

import java.util.Iterator;
import java.util.List;

import com.revature.cars.Car;
import com.revature.system.ContractDetail;
import com.revature.system.Lot;

public class Payment {
	private int paymentID;
	private int contractID;
	private double value;
	
	//create in factory
	public Payment(double value,int contractID) {
		super();
		this.value = value;
		this.contractID = contractID;
	}
	//create from sql
	public Payment(int paymentID,double value,int contractID) {
		super();
		this.paymentID = paymentID;
		this.value = value;
		this.contractID = contractID;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "\tPayment "+paymentID+
				" [Amount=" + value + "]\n";
	}

	public boolean isValid() {
		ContractDetail details = new ContractDetail(contractID);
		boolean lessThanBalance = details.getRemaingBalance() > value;
		boolean lessThanZero = value >= 1;
		if (lessThanZero && lessThanBalance) {
			return true;
		}
		return false;
	}
	
	
	
	public int getContractID() {
		return contractID;
	}

}
