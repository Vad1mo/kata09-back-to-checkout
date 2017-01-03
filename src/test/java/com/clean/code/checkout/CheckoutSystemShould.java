package com.clean.code.checkout;

import static org.junit.Assert.*;
import static java.util.Arrays.asList;

import org.junit.Before;
import org.junit.Test;

import com.clean.code.checkout.item.Item;

public class CheckoutSystemShould {
	
	private CheckoutSystem checkoutSystem;
	
	@Before
	public void setUp(){
		checkoutSystem = new CheckoutSystem();
	}
	
	@Test
	public void return_50_for_item_A(){		
		assertEquals(50, checkoutSystem.calculateTotalPrice(asList(new Item("A", 50))));
	}
	
	@Test
	public void return_30_for_item_B(){
		assertEquals(30, checkoutSystem.calculateTotalPrice(asList(new Item("B", 30))));
	}
	
	@Test
	public void return_80_for_items_A_and_B(){
		assertEquals(80, checkoutSystem.calculateTotalPrice(asList(new Item("A", 50) , new Item("B", 30))));
	}
}
