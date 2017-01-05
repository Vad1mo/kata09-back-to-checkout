package com.clean.code.checkout;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.summingInt;
import static java.util.stream.Collectors.toMap;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.clean.code.checkout.item.Item;
import com.clean.code.checkout.item.ItemCodeEnum;
import com.clean.code.checkout.item.Offer;
import com.clean.code.checkout.price.PricingRules;

public class CheckoutSystem {
	
	private Set<Item> scannedItems;
	private List<PricingRules> pricingRules;
	private List<Offer> offers;
	
	public CheckoutSystem(List<PricingRules> pricingRules, List<Offer> offers){
		this.pricingRules = pricingRules;
		this.offers = offers;
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
		
		return (scannedItems.size() > 1) ? getTotalPriceWithOffers(itemPricingRules) : getPriceWIthItemDiscounts(itemPricingRules);

	}	

	protected Integer getTotalPriceWithOffers(Map<ItemCodeEnum, PricingRules> itemPricingRules) {
		return applyOffers() + getPriceWIthItemDiscounts(itemPricingRules);
	}
	
	protected Integer getPriceWIthItemDiscounts(Map<ItemCodeEnum, PricingRules> itemPricingRules) {
		return scannedItems.stream().collect(summingInt(item -> getItemPrice(itemPricingRules, item)));
	}

	protected Integer applyOffers() {		
		return offers.stream().collect(summingInt(offer -> offer.getPriceAfterOffers(scannedItems)));
	}

	protected Integer getItemPrice(Map<ItemCodeEnum, PricingRules> itemPricingRules, Item item) {
		return itemPricingRules.get(item.getCode()).getPrice(item.getQuantity());
	}
	
	public int getScannedItemCount(){
		return scannedItems.size();
	}
}
