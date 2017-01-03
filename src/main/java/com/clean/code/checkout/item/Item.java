package com.clean.code.checkout.item;

import java.util.Objects;

public class Item {
	
	private String code;	
	private Integer price;	
	private Integer quantity = 0;
	private Discount discount;
	
	
	public Item(String code, Integer price){
		this.code = code;
		this.price = price;
	}
	
	public Item(String code, Integer price, Discount discount){
		this.code = code;
		this.price = price;
		this.discount = discount;
	}
	
	public Integer getPrice(){
		if(Objects.isNull(discount) || quantity < discount.getQuantity()){
			return quantity * price;
		}else{
			return calculatePriceWithApplicableDiscount();
		}		
	}
	
	private Integer calculatePriceWithApplicableDiscount() {				
		int quantityApplicableForDiscount = quantity / discount.getQuantity();
		int quantityNotApplicableForDiscount = quantity % discount.getQuantity();		
		return quantityApplicableForDiscount * discount.getPrice() + quantityNotApplicableForDiscount * price;
	}
	
	public void increaseQuantity(){
		quantity = quantity + 1;
	}
}
