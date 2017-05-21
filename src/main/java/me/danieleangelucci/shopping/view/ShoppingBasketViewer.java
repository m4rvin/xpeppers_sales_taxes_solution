package me.danieleangelucci.shopping.view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import me.danieleangelucci.shopping.model.Item;
import me.danieleangelucci.shopping.model.PurchasedItem;

public class ShoppingBasketViewer implements ShoppingBasketViewerInterface
{
	public void formatReceipt(List<? extends Item> purchasedItems) {
		List<String> itemList = new ArrayList<String>();
		double totalSalestaxes = 0.0;
		double totalPrice = 0.0;
		
		for (PurchasedItem pi : (List<PurchasedItem>)purchasedItems) {
			String output = Integer.toString(pi.getQuantity()).concat(" ");
			if(pi.isImported())
				output = output.concat("imported ");
			output = output.concat(pi.getName()).concat(": ");
			output = output.concat(String.valueOf(
					DECIMAL_FORMAT.format(pi.getFinalPrice())));
			itemList.add(output);
			
			totalSalestaxes += pi.getFinalPrice() - pi.getSellingPrice();
			totalPrice += pi.getFinalPrice();
		}
		
		for(String item : itemList) {
			System.out.println(item);
		}
		System.out.println("Sales Taxes: " 
				+ DECIMAL_FORMAT.format(totalSalestaxes));
		System.out.println("Total: " 
				+ DECIMAL_FORMAT.format(totalPrice));
	}
	
	private static final DecimalFormat DECIMAL_FORMAT = new DecimalFormat("0.00");
}
