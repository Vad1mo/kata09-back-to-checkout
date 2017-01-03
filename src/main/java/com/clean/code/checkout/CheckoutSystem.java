package com.clean.code.checkout;

import static java.util.stream.Collectors.summingInt;

import java.util.List;

import com.clean.code.checkout.item.Item;

public class CheckoutSystem {
	
	public int calculateTotalPrice(List<Item> itemsAtCheckout) {
		return itemsAtCheckout.stream()
				              .collect(summingInt(item -> item.getPrice()));
	}
}
