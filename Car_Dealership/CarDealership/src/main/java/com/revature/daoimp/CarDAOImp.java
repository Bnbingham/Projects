package com.revature.daoimp;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.cars.Car;
import com.revature.dao.CarDAO;
import com.revature.system.ConnFactory;

public class CarDAOImp implements CarDAO{
	public static ConnFactory cf = ConnFactory.getInstance();
	
	
	@Override
	public void insertCar(String model, int year, String color, double price) throws SQLException {
		Connection conn = cf.getConnection();
		String sql = "{ call INSERTCAR(?,?,?,?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setString(1, model);
		call.setInt(2, year);
		call.setString(3, color);
		call.setDouble(4, price);
		call.execute(); 
		call.close();
	}
//	public void insertCar(Car car) throws SQLException {
//		Connection conn = cf.getConnection();
//		String sql = "{ call INSERTCAR(?,?,?,?)";
//		CallableStatement call = conn.prepareCall(sql);
//		call.setString(1, car.getModel().getValue());
//		call.setInt(2, car.getYear().getValue());
//		call.setString(3, car.getColor().getValue());
//		call.setDouble(4, car.getPrice().getValue());
//		call.execute();
//		call.close();
//	}

	@Override
	public List<Car> getCarList() throws SQLException {
		Connection conn = cf.getConnection();
		Statement stmt = conn.createStatement();
		String sql  = "SELECT * FROM CAR_TBL";
		ResultSet rs = stmt.executeQuery(sql);
		List<Car> carList = new ArrayList<>();
		Car c = null; 
		while(rs.next()) {
			c = new Car(
					rs.getInt(1), //carID
					rs.getInt(2),//ownerID
					rs.getString(3),//carModel
					rs.getInt(4),//year
					rs.getString(5),//carColor
					rs.getDouble(6));//carPrice
			carList.add(c);
		}
		return carList;
	}

	@Override
	public void removeCar(int carID) throws SQLException{
		Connection conn = cf.getConnection();
		String sql = "{ call REMOVECAR(?)";
		CallableStatement call = conn.prepareCall(sql);
		call.setInt(1, carID);
		call.execute(); 
	}

}
