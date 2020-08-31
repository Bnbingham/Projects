package com.revature.cars;

public class Price {
	private double value;

	public Price(double value) {
		super();
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	public boolean isValid() {
		if(value > 0)
			return true;
		return false;
	}

	@Override
	public String toString() {
		return value+"";
	}

}
