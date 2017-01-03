package com.clean.code.checkout;

public class CheckoutSystem {

	public int calculateTotalPrice(String item) {
		return "B".equals(item) ? 30 : 50;
	}
}
