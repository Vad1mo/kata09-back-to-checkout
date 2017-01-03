package com.clean.code.checkout.item;

public class Item {
	
	private String code;	
	private Integer price;
	
	public Item(String code, Integer price){
		this.code = code;
		this.price = price;
	}
	
	public Integer getPrice(){
		return price;
	}
}
