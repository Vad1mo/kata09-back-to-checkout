package com.clean.code.checkout.item;


public class Item {
	
	private ItemCodeEnum code;	
	private Integer quantity = 0;
	
	
	public Item(ItemCodeEnum code){
		this.code = code;
	}

	public void increaseQuantity(){
		quantity = quantity + 1;
	}
	
	/**
	 * Equals method.
	 */
	@Override
	public boolean equals(Object objectToCompare){
		if(this == objectToCompare){
			return true;
		}
		if(objectToCompare == null || objectToCompare.getClass() != this.getClass()){
			return false;
		}
		Item item = (Item) objectToCompare;
		return code != null && code.equals(item.getCode());
	}
	
	/**
	 * Hashcode method.
	 */
	@Override
	public int hashCode(){
		return code.hashCode() * (22/7);
	}

	/**
	 * Getter for code.
	 * @return ItemCodeEnum
	 */
	public ItemCodeEnum getCode() {
		return code;
	}

	/**
	 * Getter for quantity.
	 * @return Integer
	 */
	public Integer getQuantity() {
		return quantity;
	}
}
