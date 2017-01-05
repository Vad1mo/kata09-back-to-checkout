package com.clean.code.checkout.item;

import static java.util.stream.Collectors.toMap;
import static java.util.Collections.min;
import static java.util.Comparator.comparingInt;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Offer {
	
	private List<ItemCodeEnum> itemCodes = new ArrayList<>();
	private Integer price;

	public Offer(List<ItemCodeEnum> itemCodes, Integer price) {
		this.itemCodes = itemCodes;
		this.price = price;
	}


	public Integer getPrice(Set<Item> itemsAtCheckout) {		
		Map<ItemCodeEnum, Integer> offerItemsWithQuantity = itemsAtCheckout.stream()
														    .filter(item -> itemCodes.contains(item.getCode()))
														    .collect(toMap(Item::getCode, Item::getQuantity));
		
		return areAllOfferItemsPresent(offerItemsWithQuantity) 
				? calculatePrice(itemsAtCheckout, offerItemsWithQuantity) : 0;		
	}

	
	protected Integer calculatePrice(Set<Item> itemsAtCheckout, Map<ItemCodeEnum, Integer> offerItemsWithQuantity) {
		
		Map<ItemCodeEnum, Item> itemCodeToItems = itemsAtCheckout.stream()
																 .filter(item -> itemCodes.contains(item.getCode()))
																 .collect(toMap(Item::getCode, item -> item));
		
		Item itemWithLeastQuantity = itemCodeToItems.get(itemCodeWithLeastQuantity(offerItemsWithQuantity));
		
		Integer calculatedPrice = itemWithLeastQuantity.getQuantity() * price;
		
		updateQuantityAfterOfferApplication(itemsAtCheckout, itemWithLeastQuantity.getQuantity());
		itemWithLeastQuantity.resetQuantity(); //To avoid reprocessing of item for price calculation.
		
		return calculatedPrice;
	}

	
	private boolean areAllOfferItemsPresent(Map<ItemCodeEnum, Integer> offerItemsWithQuantity) {
		return offerItemsWithQuantity.size() == itemCodes.size();
	}

	
	private void updateQuantityAfterOfferApplication(Set<Item> scannedItems, Integer quantity) {
		scannedItems.stream()
		  			.filter(item -> checkForOfferItems(item.getCode()))
		  			.forEach(item -> item.adjustQuantity(-quantity));		
	}

	protected boolean checkForOfferItems(ItemCodeEnum itemCodeEnum) {
		return itemCodes.contains(itemCodeEnum);
	}	
	
	private ItemCodeEnum itemCodeWithLeastQuantity(Map<ItemCodeEnum, Integer> itemCodeToQuantity) {
		return min(itemCodeToQuantity.entrySet(), comparingInt(Entry::getValue)).getKey();
	}
}
