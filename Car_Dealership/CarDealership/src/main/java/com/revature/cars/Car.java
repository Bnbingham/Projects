package com.revature.cars;

public class Car {
	private int carID;
	private int ownerID; // 0 == available for sale
	private Model model;
	private Year year;
	private Color color;
	private Price price;
	

	public Car(Model model, Year year, Color color, Price price) {
		super();
		this.model = model;
		this.year = year;
		this.color = color;
		this.price = price;
	}
	public Car(int carID, int ownerID,String model, int year, String color, double price) {
		this.carID = carID;
		this.ownerID = ownerID;
		this.model = new Model(model);
		this.year = new Year(year);
		this.color = new Color(color);
		this.price = new Price(price);
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}
	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
	}

	public Year getYear() {
		return year;
	}

	public void setYear(Year year) {
		this.year = year;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}


	@Override
	public String toString() {
		//fix this to show more
		return "Car "+carID+" [model=" + model.getValue()
		+ ", year=" + year.getValue()
		+ ", color=" + color.getValue()
		+ ", price=" + price.getValue() + "]\n";
	}
	public int getID() {
		return carID;
	}
	public int getOwnerID() {
		return ownerID;
	}

	

}
