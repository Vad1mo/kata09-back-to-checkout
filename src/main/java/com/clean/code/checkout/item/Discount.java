package com.clean.code.checkout.item;

public class Discount {
	
	private Integer quantity = 0;
	private Integer price = 0;
	
	public Discount(Integer quantity, Integer price) {
		this.quantity = quantity;
		this.price = price;		
	}

	/**
	 * Getter for quantity.
	 * @return Integer
	 */
	public Integer getQuantity() {
		return quantity;
	}

	/**
	 * Getter for price.
	 * @return Integer
	 */
	public Integer getPrice() {
		return price;
	}
}
