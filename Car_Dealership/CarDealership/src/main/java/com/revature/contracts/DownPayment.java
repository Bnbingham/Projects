package com.revature.contracts;

public class DownPayment {
	private double value;

	public DownPayment(double value) {
		super();
		this.value = value;
	}

	public double getValue() {
		return value;
	}

	public void setValue(double value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "DownPayment [value=" + value + "]";
	}

	public boolean isValid() {
		if (value > 0)
			return true;
		return false;
	}
}
