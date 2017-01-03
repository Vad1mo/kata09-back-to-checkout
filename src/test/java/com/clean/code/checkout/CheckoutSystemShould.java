package com.clean.code.checkout;

import static org.junit.Assert.*;
import static java.util.Arrays.asList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.clean.code.checkout.item.Discount;
import com.clean.code.checkout.item.Item;
import com.clean.code.checkout.item.ItemCodeEnum;
import com.clean.code.checkout.price.PricingRules;

public class CheckoutSystemShould {
	
	private CheckoutSystem checkoutSystem;
	private Item itemA;
	private Item itemB;
	private Item itemC;
	
	@Before
	public void setUp(){				
		itemA = new Item(ItemCodeEnum.A);
		itemB = new Item(ItemCodeEnum.B);
		itemC = new Item(ItemCodeEnum.C);
		
		Discount discount = new Discount(3, 130);
		PricingRules pricingForItemA = new PricingRules(ItemCodeEnum.A, 50, discount);
		
		discount = new Discount(2, 45);
		PricingRules pricingForItemB = new PricingRules(ItemCodeEnum.B, 30, discount);
		
		PricingRules pricingForItemC = new PricingRules(ItemCodeEnum.C, 20, null);
		
		checkoutSystem = new CheckoutSystem(asList(pricingForItemA, pricingForItemB, pricingForItemC));		
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
	
	@Test
	public void return_195_for_3_item_A_and_2_item_B_and_1_item_C(){
		assertEquals(195, checkoutSystem.calculateTotalPrice(asList(itemA, itemC, itemA, itemB, itemA, itemB)));
	}
}
