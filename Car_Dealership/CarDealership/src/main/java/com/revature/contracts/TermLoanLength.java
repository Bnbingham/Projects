package com.revature.contracts;

public class TermLoanLength {
	private int length;

	public TermLoanLength(int length) {
		super();
		this.length = length;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return "TermLoan [length=" + length + "]";
	}

	public boolean isValid() {
		if (length > 0)
			return true;
		return false;
	}
}
