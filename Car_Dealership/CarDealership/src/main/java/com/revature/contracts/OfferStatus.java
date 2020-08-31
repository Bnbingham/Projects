package com.revature.contracts;

public class OfferStatus {
	private String value;

	public OfferStatus(String value) {
		super();
		this.value = value;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return "OfferStatus [value=" + value + "]";
	}

	public boolean isValid() {
		// TODO create regex logic
		return false;
	}
}
