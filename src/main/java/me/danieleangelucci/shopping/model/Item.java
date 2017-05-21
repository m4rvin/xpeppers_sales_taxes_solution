package me.danieleangelucci.shopping.model;

import me.danieleangelucci.shopping.ItemCategory;

public class Item
{
	public Item(){}
	
	public Item(String name, ItemCategory category, double sellingPrice) {
		this.name = name;
		this.category = category;
		this.sellingPrice = sellingPrice;
	}
	
	public String getName()
	{
		return name;
	}
	
	public ItemCategory getCategory()
	{
		return category;
	}
	
	public double getSellingPrice()
	{
		return sellingPrice;
	}
	
	public void setName(String name)
	{
		this.name = name;
	}
	
	public void setCategory(ItemCategory category)
	{
		this.category = category;
	}
	
	public void setSellingPrice(double sellingPrice)
	{
		this.sellingPrice = sellingPrice;
	}


	private String name = null;
	private ItemCategory category = null;
	private double sellingPrice = 0.0;
}
