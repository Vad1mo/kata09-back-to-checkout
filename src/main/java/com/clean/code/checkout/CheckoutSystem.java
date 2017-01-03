package com.clean.code.checkout;

import static java.util.stream.Collectors.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.clean.code.checkout.item.Item;
import com.clean.code.checkout.item.ItemCodeEnum;
import com.clean.code.checkout.price.PricingRules;

public class CheckoutSystem {
	
	private List<PricingRules> pricingRules;
	
	public CheckoutSystem(List<PricingRules> pricingRules){
		this.pricingRules = pricingRules;
	}
	
	public int calculateTotalPrice(List<Item> itemsAtCheckout) {
		
		itemsAtCheckout.stream().forEach(item -> item.increaseQuantity());
		
		Set<Item> itemsWithQuantity = new HashSet<>(itemsAtCheckout);
		
		Map<ItemCodeEnum, PricingRules> itemPricingRules = pricingRules.stream()
																 .collect(
																	toMap(PricingRules::getItemCode, 
																		  pricingRule -> pricingRule));
		return itemsWithQuantity.stream()
				                .collect(summingInt(item -> getItemPrice(itemPricingRules, item)));
	}

	private Integer getItemPrice(Map<ItemCodeEnum, PricingRules> itemPricingRules, Item item) {
		return itemPricingRules.get(item.getCode()).getPrice(item.getQuantity());
	}
}
