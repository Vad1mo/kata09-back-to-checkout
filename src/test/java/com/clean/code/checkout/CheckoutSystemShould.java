package com.clean.code.checkout;

import static org.junit.Assert.*;
import static java.util.Arrays.asList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.clean.code.checkout.item.Discount;
import com.clean.code.checkout.item.Item;
import com.clean.code.checkout.price.PricingRules;

public class CheckoutSystemShould {
	
	private CheckoutSystem checkoutSystem;
	private Item itemA;
	private Item itemB;
	
	@Before
	public void setUp(){				
		itemA = new Item("A");
		itemB = new Item("B");
		
		Discount discount = new Discount(3, 130);
		PricingRules pricingForItemA = new PricingRules("A", 50, discount);
		
		discount = new Discount(2, 45);
		PricingRules pricingForItemB = new PricingRules("B", 30, discount);
		
		checkoutSystem = new CheckoutSystem(asList(pricingForItemA, pricingForItemB));		
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
	
	@Test
	public void return_130_for_three_items_A(){
		assertEquals(130, checkoutSystem.calculateTotalPrice(asList(itemA, itemA, itemA)));
	}
	
	@Test
	public void return_45_for_two_items_B(){
		assertEquals(45, checkoutSystem.calculateTotalPrice(asList(itemB, itemB)));
	}
	
	@Test
	public void return_225_for_four_item_A_and_2_item_B(){
		assertEquals(225, checkoutSystem.calculateTotalPrice(asList(itemA, itemA, itemA, itemA, itemB, itemB)));
	}
	
	@Test
	public void return_255_for_four_item_A_and_3_item_B(){
		assertEquals(255, checkoutSystem.calculateTotalPrice(asList(itemA, itemA, itemA, itemA, itemB, itemB, itemB)));
	}
}
