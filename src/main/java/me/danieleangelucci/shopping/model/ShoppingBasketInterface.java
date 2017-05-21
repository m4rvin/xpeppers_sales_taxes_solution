package me.danieleangelucci.shopping.model;

import java.util.List;


public interface ShoppingBasketInterface
{
	/**
	 * Compute the final price of each item in the shopping basket, applying
	 * the taxes on it and saving the resulting value.
	 */
	public void computeFinalPrice();
	
	/**
	 * Retrieve all the shopping basket items.
	 * @return A list of the shopping basket items.
	 * @throws EmptyShoppingBasketException
	 */
	public List<? extends Item> getShoppingBasketItems() throws EmptyShoppingBasketException;
	
	/**
	 * Add an item to the shopping basket.
	 * @param pItem
	 */
	public void put(Item pItem);
}
