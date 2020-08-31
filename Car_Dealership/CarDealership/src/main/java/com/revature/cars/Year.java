package com.revature.cars;

public class Year {
	private int value;

	public Year(int value) {
		super();
		this.value = value;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public boolean isValid() {
		//TODO create regex  for valid year int
		return true;
	}
	
	@Override
	public String toString() {
		return value+"";
	}
	
}
