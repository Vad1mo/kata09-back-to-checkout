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
	
	public Integer getPrice(String itemCode, Integer quantity){
		if(this.itemCode.equals(itemCode)){
			if(Objects.isNull(discount) || quantity < discount.getQuantity()){
				return quantity * price;
			}else{
				return calculatePriceWithApplicableDiscount(quantity);
			}
		}
		return null;
	}
	
	private Integer calculatePriceWithApplicableDiscount(Integer itemQuantity) {				
		int quantityApplicableForDiscount = itemQuantity / discount.getQuantity();
		int quantityNotApplicableForDiscount = itemQuantity % discount.getQuantity();		
		return quantityApplicableForDiscount * discount.getPrice() + quantityNotApplicableForDiscount * price;
	}
}
