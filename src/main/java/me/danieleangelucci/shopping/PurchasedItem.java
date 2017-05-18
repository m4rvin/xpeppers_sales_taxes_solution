package me.danieleangelucci.shopping;

import java.text.DecimalFormat;

public class PurchasedItem extends Item
{
	public PurchasedItem() {}
	
	public PurchasedItem(
			String name, 
			ItemCategory category, 
			double sellingPrice,
			int quantity,
			boolean imported) {
		super(name, category, sellingPrice);
		this.quantity = quantity;
		this.imported = imported;
	}
	
	public PurchasedItem(
			String name, 
			ItemCategory category, 
			double sellingPrice,
			double finalPrice,
			int quantity,
			boolean imported) {
		super(name, category, sellingPrice);
		this.finalPrice = finalPrice;
		this.quantity = quantity;
		this.imported = imported;
	}

	public double getFinalPrice()
	{
		return finalPrice;
	}
	
	public int getQuantity()
	{
		return quantity;
	}

	public boolean isImported()
	{
		return imported;
	}

	public void setFinalPrice(double finalPrice)
	{
		this.finalPrice = finalPrice;
	}
	
	public void setImported(boolean imported)
	{
		this.imported = imported;
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	@Override
	public String toString()
	{
		return "PurchasedItem [finalPrice=" + String.valueOf(
				new DecimalFormat("0.00").format(finalPrice)) + ", quantity="
				+ quantity + ", imported=" + imported + ", name="
				+ getName() + ", category=" + getCategory()
				+ ", sellingPrice=" + 
				String.valueOf(
						new DecimalFormat("0.00").format(getSellingPrice()))
				+ "]";
	}


	private double finalPrice = 0.0;
	private int quantity = 0;
	private boolean imported = false;
}
