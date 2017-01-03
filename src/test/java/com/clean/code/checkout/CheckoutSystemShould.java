package com.clean.code.checkout;

import static org.junit.Assert.*;
import static java.util.Arrays.asList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.clean.code.checkout.item.Item;

public class CheckoutSystemShould {
	
	private CheckoutSystem checkoutSystem;
	private Item itemA;
	private Item itemB;
	
	@Before
	public void setUp(){
		checkoutSystem = new CheckoutSystem();
		itemA = new Item("A", 50);
		itemB = new Item("B", 30);
	}
	
	@After
	public void tearDown(){
		itemA = null;
		itemB = null;
	}
	
	@Test
	public void return_50_for_item_A(){		
		assertEquals(50, checkoutSystem.calculateTotalPrice(asList(itemA)));
	}

	@Test
	public void return_30_for_item_B(){
		assertEquals(30, checkoutSystem.calculateTotalPrice(asList(itemB)));
	}
	
	@Test
	public void return_80_for_items_A_and_B(){
		assertEquals(80, checkoutSystem.calculateTotalPrice(asList(itemA, itemB)));
	}
}
