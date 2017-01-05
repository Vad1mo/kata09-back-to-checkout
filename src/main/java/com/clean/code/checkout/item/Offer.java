package com.clean.code.checkout.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import static java.util.stream.Collectors.*;

public class Offer {
	
	private List<ItemCodeEnum> itemCodes = new ArrayList<>();
	private Integer price;

	public Offer(List<ItemCodeEnum> itemCodes, Integer price) {
		this.itemCodes = itemCodes;
		this.price = price;
	}

	public Integer getPriceAfterOffers(Set<Item> scannedItems) {
		
		Map<ItemCodeEnum, Item> items = scannedItems.stream().collect(toMap(Item::getCode, item -> item));
		
		Item itemWithLeastQuantity = items.get(getItemCodeWithLeastQuantity(items));
		
		adjustQuantityForOfferItems(scannedItems, itemWithLeastQuantity);
		
		scannedItems.remove(itemWithLeastQuantity);
		return itemWithLeastQuantity.getQuantity() * price;
	}

	protected ItemCodeEnum getItemCodeWithLeastQuantity(Map<ItemCodeEnum, Item> items) {
		Map<ItemCodeEnum, Integer> sortedMap = new HashMap<>();
		Map<ItemCodeEnum, Integer> itemToQuantity = items.entrySet().stream()
				.filter(itemEntry -> itemCodes.contains(itemEntry.getKey())).collect(toMap(itemEntry -> itemEntry.getKey(), itemEntry -> itemEntry.getValue().getQuantity()));
		
		itemToQuantity.entrySet().stream().sorted(Map.Entry.<ItemCodeEnum, Integer> comparingByValue()).forEachOrdered(
				itemQuantityEntry -> sortedMap.put(itemQuantityEntry.getKey(), itemQuantityEntry.getValue()));
		
		return getFirstKey(sortedMap);
	}
	
	protected void adjustQuantityForOfferItems(Set<Item> scannedItems, Item itemWithLeastQuantity) {
		List<Item> otherApplicableItems = scannedItems.stream()
													  .filter(item -> checkForOtherOfferItems(itemWithLeastQuantity, item))
													  .collect(toList());
		
		otherApplicableItems.stream().forEach(item -> item.adjustQuantity(-itemWithLeastQuantity.getQuantity()));
	}

	protected boolean checkForOtherOfferItems(Item itemWithLeastQuantity, Item item) {
		return item.getCode() != itemWithLeastQuantity.getCode() && itemCodes.contains(item.getCode());
	}
	
	
	private ItemCodeEnum getFirstKey(Map<ItemCodeEnum, Integer> sortedMap) {
		return (ItemCodeEnum) sortedMap.keySet().toArray()[0];
	}
}
