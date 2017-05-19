package me.danieleangelucci.shopping.view;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import me.danieleangelucci.shopping.model.PurchasedItem;

public class ShoppingBasketViewer
{
	public void formatReceipt(List<PurchasedItem> purchasedItems) {
		List<String> itemList = new ArrayList<String>();
		double totalSalestaxes = 0.0;
		double totalPrice = 0.0;
		
		for (PurchasedItem pi : purchasedItems) {
			String output = Integer.toString(pi.getQuantity()).concat(" ");
			if(pi.isImported())
				output = output.concat("imported ");
			output = output.concat(pi.getName()).concat(": ");
			output = output.concat(String.valueOf(
					new DecimalFormat("0.00").format(pi.getFinalPrice())));
			itemList.add(output);
			
			totalSalestaxes += pi.getFinalPrice() - pi.getSellingPrice();
			totalPrice += pi.getFinalPrice();
		}
		
		for(String item : itemList) {
			System.out.println(item);
		}
		System.out.println("Sales Taxes: " 
				+ new DecimalFormat("0.00").format(totalSalestaxes));
		System.out.println("Total: " 
				+ new DecimalFormat("0.00").format(totalPrice));
	}
}
