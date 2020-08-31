package com.revature.cars;

import java.sql.SQLException;

import com.revature.daoimp.CarDAOImp;

public class CarRemover {
	int carID;
	boolean isValidToRemove;
	
	public CarRemover() {
		super();
	}
	
	public boolean enterCarID(int carID) {
		//TODO check if car exists and if it is owned
		this.carID = carID;
		this.isValidToRemove = true;
		return true;
	}
	
	public void removeCar() {
		if(isValidToRemove) {
			CarDAOImp cdi = new CarDAOImp();
			try {
				cdi.removeCar(carID);
				System.out.println("car "+carID+ " succesfullly removed");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
