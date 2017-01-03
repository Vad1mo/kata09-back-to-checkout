package com.clean.code.checkout;

import static org.junit.Assert.*;

import org.junit.Test;

public class CheckoutSystemShould {
	
	@Test
	public void return_50_for_item_A(){
		CheckoutSystem checkoutSystem = new CheckoutSystem();
		assertEquals(50, checkoutSystem.calculateTotalPrice("A"));
	}
}
