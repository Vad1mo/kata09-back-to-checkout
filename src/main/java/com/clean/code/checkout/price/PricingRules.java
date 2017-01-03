package com.clean.code.checkout.price;

import java.util.Objects;

import com.clean.code.checkout.item.Discount;

public class PricingRules {
	
	private String itemCode;	
	private Integer price = 0;	
	private Discount discount;
	
	public PricingRules(String itemCode, Integer price, Discount discount) {
		this.itemCode = itemCode;
		this.price = price;
		this.discount = discount;		
	}
	
	public Integer getPrice(Integer quantity){
		if(Objects.isNull(discount) || quantity < discount.getQuantity()){
			return quantity * price;
		}else{
			return calculatePriceWithApplicableDiscount(quantity);
		}
	}
	
	private Integer calculatePriceWithApplicableDiscount(Integer itemQuantity) {				
		int quantityApplicableForDiscount = itemQuantity / discount.getQuantity();
		int quantityNotApplicableForDiscount = itemQuantity % discount.getQuantity();		
		return quantityApplicableForDiscount * discount.getPrice() + quantityNotApplicableForDiscount * price;
	}

	/**
	 * Getter for itemCode.
	 * @return String
	 */
	public String getItemCode() {
		return itemCode;
	}
}
