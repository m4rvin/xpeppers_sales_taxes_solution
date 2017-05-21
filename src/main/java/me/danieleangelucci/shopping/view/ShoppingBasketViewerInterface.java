package me.danieleangelucci.shopping.view;

import java.util.List;

import me.danieleangelucci.shopping.model.Item;

public interface ShoppingBasketViewerInterface
{
	public void formatReceipt(List<? extends Item> purchasedItems);
}
