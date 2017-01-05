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
		Map<ItemCodeEnum, PricingRules> itemPricingRules = 
				pricingRules.stream().collect(toMap(PricingRules::getItemCode, 
													 pricingRule -> pricingRule));
		
		return (scannedItems.size() > 1) ? pricingWithOffersAndDiscounts(itemPricingRules) 
										 : itemLevelPricingWithDiscounts(itemPricingRules);
	}	

	protected Integer pricingWithOffersAndDiscounts(Map<ItemCodeEnum, PricingRules> itemPricingRules) {
		return pricingWithOffers() + itemLevelPricingWithDiscounts(itemPricingRules);
	}
	
	protected Integer pricingWithOffers() {
		return offers.stream().collect(summingInt(offer -> offer.getPrice(scannedItems)));
	}
	
	
	protected Integer itemLevelPricingWithDiscounts(Map<ItemCodeEnum, PricingRules> itemPricingRules) {
		return scannedItems.stream().collect(summingInt(item -> getItemPrice(itemPricingRules, item)));
	}
	
	protected Integer getItemPrice(Map<ItemCodeEnum, PricingRules> itemPricingRules, Item item) {
		return itemPricingRules.get(item.getCode()).getPrice(item.getQuantity());
	}
	
	
	public int getScannedItemCount(){
		return scannedItems.size();
	}
}
