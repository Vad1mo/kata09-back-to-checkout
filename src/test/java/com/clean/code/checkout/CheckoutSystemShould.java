package com.clean.code.checkout;

import static org.junit.Assert.*;
import static java.util.Arrays.asList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.clean.code.checkout.item.Discount;
import com.clean.code.checkout.item.Item;
import com.clean.code.checkout.item.ItemCodeEnum;
import com.clean.code.checkout.item.Offer;
import com.clean.code.checkout.price.PricingRules;

public class CheckoutSystemShould {
	
	private CheckoutSystem checkoutSystem;
	private Item itemA;
	private Item itemB;
	private Item itemC;
	private Item itemD;
	
	@Before
	public void setUp(){				
		itemA = new Item(ItemCodeEnum.A);
		itemB = new Item(ItemCodeEnum.B);
		itemC = new Item(ItemCodeEnum.C);
		itemD = new Item(ItemCodeEnum.D);
		
		Discount discount = new Discount(3, 130);
		PricingRules pricingForItemA = new PricingRules(ItemCodeEnum.A, 50, discount);
		
		discount = new Discount(2, 45);
		PricingRules pricingForItemB = new PricingRules(ItemCodeEnum.B, 30, discount);
		
		PricingRules pricingForItemC = new PricingRules(ItemCodeEnum.C, 20, null);
		PricingRules pricingForItemD = new PricingRules(ItemCodeEnum.D, 15, null);
		
		Offer offer = new Offer(asList(ItemCodeEnum.A, ItemCodeEnum.B), 70);
		checkoutSystem = new CheckoutSystem(asList(pricingForItemA, pricingForItemB, 
												   pricingForItemC, pricingForItemD), asList(offer));
		
		
	}
	
	@After
	public void tearDown(){
		itemA = null;
		itemB = null;
		checkoutSystem = null;
	}
	
	@Test
	public void return_50_for_item_A(){	
		checkoutSystem.scan(itemA);
		assertEquals(50, checkoutSystem.calculateTotalPrice());
	}

	@Test
	public void return_30_for_item_B(){
		checkoutSystem.scan(itemB);
		assertEquals(30, checkoutSystem.calculateTotalPrice());
	}
	
	@Test
	public void return_130_for_three_items_A(){
		checkoutSystem.scan(itemA, itemA, itemA);
		assertEquals(130, checkoutSystem.calculateTotalPrice());
	}
	
	@Test
	public void return_45_for_two_items_B(){
		checkoutSystem.scan(itemB, itemB);
		assertEquals(45, checkoutSystem.calculateTotalPrice());
	}
	
	@Test
	public void return_240_for_four_item_A_and_2_item_B(){
		checkoutSystem.scan(itemA, itemA, itemA, itemA, itemB, itemB);
		assertEquals(240, checkoutSystem.calculateTotalPrice());
	}
	
	@Test
	public void return_260_for_four_item_A_and_3_item_B(){
		checkoutSystem.scan(itemA, itemA, itemA, itemA, itemB, itemB, itemB);
		assertEquals(260, checkoutSystem.calculateTotalPrice());
	}
	
	@Test
	public void return_210_for_3_item_A_and_2_item_B_and_1_item_C(){
		checkoutSystem.scan(itemA, itemC, itemA, itemB, itemA, itemB);
		assertEquals(210, checkoutSystem.calculateTotalPrice());
	}
	
	@Test
	public void return_205_for_3_item_A_and_1_item_each_of_B_C_and_D(){
		checkoutSystem.scan(itemC, itemA, itemD, itemA, itemA, itemB);
		assertEquals(205, checkoutSystem.calculateTotalPrice());
	}
	
	@Test
	public void return_1_whens_only_1_item_is_scanned(){
		checkoutSystem.scan(itemA);
		assertEquals(1, checkoutSystem.getScannedItemCount());
	}
	
	@Test
	public void return_2_whens_only_2_items_are_scanned(){
		
		checkoutSystem.scan(itemA, itemB);
		assertEquals(2, checkoutSystem.getScannedItemCount());
	}
	
	@Test
	public void return_70_for_item_A_and_item_B(){
		checkoutSystem.scan(itemA, itemB);
		assertEquals(70, checkoutSystem.calculateTotalPrice());
	}
}
