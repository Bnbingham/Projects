package com.revature.cars;

import java.sql.SQLException;

import com.revature.daoimp.CarDAOImp;

public class CarFactory {
	private Model model;
	private Year year;
	private Color color;
	private Price price;
	
	public CarFactory() {
		super();
	}
	public boolean enterModel(Model model) {
		if(model.isValid()) {
			this.model = model;
			return true;
		}
		System.out.println("Not a vaild input for model");
		return false;
	}
	public boolean enterYear(Year year) {
		if(year.isValid()) {
			this.year = year;
			return true;
		}
		System.out.println("Not a valid input for year");
		return false;
	}
	public boolean enterColor(Color color) {
		if(color.isValid()) {
			this.color = color;
			return true;
		}
		return false;
	}
	public boolean enterPrice(Price price) {
		if(price.isValid()) {
			this.price = price;
			return true;
		}
		System.out.println("Not a vaild price");
		return false;
	}
	public void registerCar() {
		if(model != null && year != null && color != null && price != null) {
			CarDAOImp cdi = new CarDAOImp();
			try {
				cdi.insertCar(
						model.getValue(), 
						year.getValue(),
						color.getValue(), 
						price.getValue());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public Model getModel() {
		return model;
	}
	public Year getYear() {
		return year;
	}
	public Color getColor() {
		return color;
	}
	public Price getPrice() {
		return price;
	}

}
