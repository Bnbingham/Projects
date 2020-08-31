package com.revature.tests;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.revature.cars.Price;

class Tests {

	@Test
	void testPriceIsValid() {
		Price testPrice = new Price(-1);
		boolean expected = false;
		boolean actual = testPrice.isValid();
		assertEquals(expected, actual);
	}

}
