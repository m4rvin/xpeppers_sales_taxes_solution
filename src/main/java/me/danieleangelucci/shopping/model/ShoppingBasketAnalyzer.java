package me.danieleangelucci.shopping.model;

import java.util.ArrayList;
import java.util.List;

import me.danieleangelucci.shopping.ItemCategory;

public class ShoppingBasketAnalyzer
{
	public ShoppingBasketAnalyzer() {
		this.purchasedItems = new ArrayList<PurchasedItem>();
	}
	
	public void computeFinalPrice() {
		for(PurchasedItem pi : this.purchasedItems) {
			pi.setFinalPrice(pi.getSellingPrice());

			//Apply 10% sales tax: Basic sales tax
			if(pi.getCategory().equals(ItemCategory.OTHERS))
			{
				double salesTax = pi.getSellingPrice() * 10/100;
				salesTax = roundSalesTax(salesTax);
				pi.setFinalPrice(pi.getFinalPrice() + salesTax);
			}
			//Apply 5% sales tax: Import duty 
			if(pi.isImported())
			{
				double salesTax = pi.getSellingPrice() * 5/100;
				salesTax = roundSalesTax(salesTax);
				pi.setFinalPrice(pi.getFinalPrice() + salesTax);
			}
		}
	}
	
	public List<PurchasedItem> getShoppingBasketItems() throws EmptyShoppingBasketException {
		return this.purchasedItems;
	}
	
	public void put(PurchasedItem pItem) {
		this.purchasedItems.add(pItem);
	}
	
	private double roundSalesTax(double salesTax) {
		return salesTax = Math.ceil(salesTax / 0.05) *0.05;
	}
	
	private List<PurchasedItem> purchasedItems;
}
