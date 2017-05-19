package me.danieleangelucci.shopping.model;

import java.util.ArrayList;
import java.util.List;

import me.danieleangelucci.shopping.ItemCategory;

public class ShoppingBasket implements ShoppingBasketInterface
{
	public ShoppingBasket() {
		this.purchasedItems = new ArrayList<PurchasedItem>();
	}
	
	public void computeFinalPrice() {
		for(PurchasedItem pi : this.purchasedItems) {
			pi.setFinalPrice(pi.getSellingPrice());

			//Apply 10% sales tax: Basic sales tax
			if(pi.getCategory().equals(ItemCategory.OTHERS))
				applyTax(pi, ShoppingBasket.BASIC_SALES_TAX_RATE);
			//Apply 5% sales tax: Import duty 
			if(pi.isImported())
				applyTax(pi, ShoppingBasket.IMPORT_DUTY_RATE);
		}
	}
	
	public List<? extends Item> getShoppingBasketItems() throws EmptyShoppingBasketException {
		return this.purchasedItems;
	}
	
	public void put(Item pItem) {
		this.purchasedItems.add((PurchasedItem)pItem);
	}
	
	
	private void applyTax(PurchasedItem pi, double tax_rate) {
		double salesTax = pi.getSellingPrice() * tax_rate;
		salesTax = roundSalesTax(salesTax);
		pi.setFinalPrice(pi.getFinalPrice() + salesTax);
	}
	
	private double roundSalesTax(double salesTax) {
		return salesTax = Math.ceil(salesTax / ShoppingBasket.TAX_ROUNDING_VALUE) * ShoppingBasket.TAX_ROUNDING_VALUE;
	}
	
	private List<PurchasedItem> purchasedItems;
	
	private static final double BASIC_SALES_TAX_RATE = 10.0/100.0;
	private static final double IMPORT_DUTY_RATE = 5.0/100.0;
	private static final double TAX_ROUNDING_VALUE= 0.05;


}
