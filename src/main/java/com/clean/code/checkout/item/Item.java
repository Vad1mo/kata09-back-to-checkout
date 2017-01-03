package com.clean.code.checkout.item;

public class Item {
	
	private String code;	
	private Integer price;	
	private Integer quantity = 0;
	private Integer discountableQuantity = 0;
	private Integer discountPrice = 0;
	
	
	public Item(String code, Integer price){
		this.code = code;
		this.price = price;
	}
	
	public Item(String code, Integer price, Integer discountableQuantity, Integer discountPrice){
		this.code = code;
		this.price = price;
		this.discountableQuantity = discountableQuantity;
		this.discountPrice = discountPrice;
	}
	
	public Integer getPrice(){
		if(discountableQuantity == 0 || quantity < discountableQuantity){
			return quantity * price;
		}else{
			return calculatePriceWithApplicableDiscount();
		}		
	}
	
	private Integer calculatePriceWithApplicableDiscount() {				
		int quantityApplicableForDiscount = quantity / discountableQuantity;
		int quantityNotApplicableForDiscount = quantity % discountableQuantity;		
		return quantityApplicableForDiscount * discountPrice + quantityNotApplicableForDiscount * price;
	}
	
	public void increaseQuantity(){
		quantity = quantity + 1;
	}
}
