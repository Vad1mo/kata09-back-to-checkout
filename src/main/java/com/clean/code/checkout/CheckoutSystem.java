package com.clean.code.checkout;

import static java.util.stream.Collectors.summingInt;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.clean.code.checkout.item.Item;

public class CheckoutSystem {
	
	public int calculateTotalPrice(List<Item> itemsAtCheckout) {
		itemsAtCheckout.stream()
					   .forEach(item -> item.increaseQuantity());
		
		Set<Item> distinctItemsWithQuantity = new HashSet<>(itemsAtCheckout);
		
		return distinctItemsWithQuantity.stream()
				              .collect(summingInt(item -> item.getPrice()));
	}
}
