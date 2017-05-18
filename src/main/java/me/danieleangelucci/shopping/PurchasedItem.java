package me.danieleangelucci.shopping;

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

	private double finalPrice = 0.0;
	private int quantity = 0;
	private boolean imported = false;
}