package com.revature.dao;

import java.sql.SQLException;
import java.util.List;

import com.revature.cars.Car;

public interface CarDAO {

	
	public void insertCar(String model, int year, String color, double price) throws SQLException;
	
	public List<Car> getCarList() throws SQLException;
	
	public void removeCar(int carID) throws SQLException;
}
