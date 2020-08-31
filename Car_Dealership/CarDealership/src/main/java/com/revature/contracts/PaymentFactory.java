package com.revature.contracts;

import java.sql.SQLException;

import com.revature.daoimp.PaymentDAOImp;
import com.revature.system.ContractDetail;
import com.revature.system.LogThis;

public class PaymentFactory {
	private int contractID;
	private ContractDetail details;
	boolean isValidPayment;
	private Payment payment;
	
	public PaymentFactory() {
		super();
		this.contractID = 0;
		this.details = null;
		this.isValidPayment = false;
		this.payment = null;
	}
	
	public boolean checkPayment(Payment payment) {
		if(payment.isValid()) {
			this.payment = payment;
			this.contractID = payment.getContractID();
			this.details = new ContractDetail(contractID);
			isValidPayment = true;
			return true;
		}
		System.out.println("Amount must be between 1 and "+details.getRemaingBalance()+".");
		return false;
	}
	
	public void makePayment() {
		if(isValidPayment) {
			PaymentDAOImp pdi = new PaymentDAOImp();
			try {
				pdi.insertPayment(payment.getValue(), this.contractID);
				LogThis.LogIt("info", payment +"created");
			} catch (SQLException e) {
				LogThis.LogIt("debug", "function 'makePayment' SQL exception" );
				e.printStackTrace();
			}
		}
			
	}
}
