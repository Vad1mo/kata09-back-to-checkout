package com.clean.code.checkout;

import static org.junit.Assert.*;
import static java.util.Arrays.asList;

import org.junit.Test;

public class CheckoutSystemShould {
	
	@Test
	public void return_50_for_item_A(){
		CheckoutSystem checkoutSystem = new CheckoutSystem();
		assertEquals(50, checkoutSystem.calculateTotalPrice("A"));
	}
	
	@Test
	public void return_30_for_item_B(){
		CheckoutSystem checkoutSystem = new CheckoutSystem();
		assertEquals(30, checkoutSystem.calculateTotalPrice("B"));
	}
	
	@Test
	public void return_80_for_items_A_and_B(){
		CheckoutSystem checkoutSystem = new CheckoutSystem();
		assertEquals(80, checkoutSystem.calculateTotalPrice(asList("A","B")));
	}
}
