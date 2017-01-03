package com.clean.code.checkout;

import static java.util.stream.Collectors.*;
import static java.util.Arrays.asList;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.clean.code.checkout.item.Item;
import com.clean.code.checkout.item.ItemCodeEnum;
import com.clean.code.checkout.price.PricingRules;

public class CheckoutSystem {
	
	private Set<Item> scannedItems;
	private List<PricingRules> pricingRules;
	
	public CheckoutSystem(List<PricingRules> pricingRules){
		this.pricingRules = pricingRules;
		scannedItems = new HashSet<>();
	}
	
	public void scan(Item... itemsForCheckout) {
		List<Item> checkedOutItems = asList(itemsForCheckout);
		checkedOutItems.stream().forEach(item -> item.increaseQuantity());
		scannedItems.addAll(checkedOutItems);
	}
	
	public int calculateTotalPrice() {
		
		Map<ItemCodeEnum, PricingRules> itemPricingRules = pricingRules.stream()
																 .collect(
																	toMap(PricingRules::getItemCode, 
																		  pricingRule -> pricingRule));
		return scannedItems.stream()
				                .collect(summingInt(item -> getItemPrice(itemPricingRules, item)));
	}

	
	private Integer getItemPrice(Map<ItemCodeEnum, PricingRules> itemPricingRules, Item item) {
		return itemPricingRules.get(item.getCode()).getPrice(item.getQuantity());
	}
	
	public int getScannedItemCount(){
		return scannedItems.size();
	}
}
