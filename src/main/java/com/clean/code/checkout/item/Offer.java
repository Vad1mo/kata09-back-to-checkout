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

	public Integer getPrice(){		
		return price;		
	}

	public Integer getPriceAfterOffers(Set<Item> scannedItems) {
		Map<ItemCodeEnum, Item> items = scannedItems.stream().collect(toMap(Item::getCode, item -> item));

		ItemCodeEnum itemCodeHavingLeastQuantity = getItemWIthLeastQuantity(items);		
		Item itemWithLeastQuantity = items.get(itemCodeHavingLeastQuantity);
		List<Item> otherApplicableItems = scannedItems.stream()
													  .filter(item -> item.getCode() != itemCodeHavingLeastQuantity 
													  				&& itemCodes.contains(item.getCode()))
													  .collect(toList());
		
		otherApplicableItems.stream().forEach(item -> item.adjustQuantity(-itemWithLeastQuantity.getQuantity()));
		
		scannedItems.remove(itemWithLeastQuantity);
		return itemWithLeastQuantity.getQuantity() * price;
	}

	
	protected ItemCodeEnum getItemWIthLeastQuantity(Map<ItemCodeEnum, Item> items) {
		Map<ItemCodeEnum, Integer> sortedMap = new HashMap<>();
		Map<ItemCodeEnum, Integer> itemToQuantity = items.entrySet().stream()
				.filter(itemEntry -> itemCodes.contains(itemEntry.getKey())).collect(toMap(itemEntry -> itemEntry.getKey(), itemEntry -> itemEntry.getValue().getQuantity()));
		
		itemToQuantity.entrySet().stream().sorted(Map.Entry.<ItemCodeEnum, Integer> comparingByValue()).forEachOrdered(
				itemQuantityEntry -> sortedMap.put(itemQuantityEntry.getKey(), itemQuantityEntry.getValue()));
		
		return getFirstKey(sortedMap);
	}

	private ItemCodeEnum getFirstKey(Map<ItemCodeEnum, Integer> sortedMap) {
		return (ItemCodeEnum) sortedMap.keySet().toArray()[0];
	}
}
