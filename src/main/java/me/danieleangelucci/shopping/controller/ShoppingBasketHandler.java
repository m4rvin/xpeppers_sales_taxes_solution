package me.danieleangelucci.shopping.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import me.danieleangelucci.commons.AppConfig;
import me.danieleangelucci.shopping.model.EmptyShoppingBasketException;
import me.danieleangelucci.shopping.model.PurchasedItem;
import me.danieleangelucci.shopping.model.ShoppingBasket;
import me.danieleangelucci.shopping.model.UnreadableInputFileException;
import me.danieleangelucci.shopping.view.ShoppingBasketViewer;

public class ShoppingBasketHandler
{

	public ShoppingBasketHandler(
			ShoppingBasket shoppingBasket,
			ShoppingBasketViewer sbViewer) {
		this.shoppingBasket = shoppingBasket;
		this.sbViewer = sbViewer;
	}
	
	public void computeFinalPriceOnShoppingBasketItems() {
		this.shoppingBasket.computeFinalPrice();
	}
	
	public List<PurchasedItem> getShoppingBasketItems() throws EmptyShoppingBasketException {
		return this.shoppingBasket.getShoppingBasketItems();
	} 
	
	public void parsePurchasedItemFromInputFile() 
			throws UnreadableInputFileException {
		/*//Avoid parsing the same input file more than once.
		if(purchasedItems.size() != 0)
			return;*/
		
		Path inputFilepath = Paths.get(AppConfig.inputFilePath);
		
		Charset charset = Charset.forName("US-ASCII");
		try 
		(BufferedReader reader = 
		Files.newBufferedReader(inputFilepath, charset)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        PurchasedItem pItem = PurchasedItem.parseLine(line);
		        shoppingBasket.put(pItem);
		    }
		} catch (IOException | UnexpectedInputDataFormatException e) {
			e.printStackTrace();
			throw new UnreadableInputFileException();
		}
	}
	
	public void showReceipt(List<PurchasedItem> purchasedItems) {
		this.sbViewer.formatReceipt(purchasedItems);
	}

	
	private ShoppingBasket shoppingBasket;
	private ShoppingBasketViewer sbViewer;
}
