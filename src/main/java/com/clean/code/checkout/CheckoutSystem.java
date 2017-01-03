package com.clean.code.checkout;

import java.util.HashMap;
import java.util.Map;

public class CheckoutSystem {
	
	private Map<String, Integer> itemPrices;
	
	public CheckoutSystem(){
		itemPrices = new HashMap<>();
		itemPrices.put("A", 50);
		itemPrices.put("B", 30);
	}

	public int calculateTotalPrice(String itemAtCheckout) {
		return itemPrices.getOrDefault(itemAtCheckout, 0);
	}
}
