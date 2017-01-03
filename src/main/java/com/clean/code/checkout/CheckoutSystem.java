package com.clean.code.checkout;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.stream.Collectors.summingInt;

public class CheckoutSystem {
	
	private Map<String, Integer> itemPrices;
	
	public CheckoutSystem(){
		itemPrices = new HashMap<>();
		itemPrices.put("A", 50);
		itemPrices.put("B", 30);
	}

	public int calculateTotalPrice(List<String> itemsAtCheckout) {
		return itemsAtCheckout.stream()
				              .collect(summingInt(item -> itemPrices.get(item)));
	}
}
