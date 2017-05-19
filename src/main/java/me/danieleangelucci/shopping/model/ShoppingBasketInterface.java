package me.danieleangelucci.shopping.model;

import java.util.List;


public interface ShoppingBasketInterface
{
	public void computeFinalPrice();
	
	public List<? extends Item> getShoppingBasketItems() throws EmptyShoppingBasketException;
	
	public void put(Item pItem);
}
