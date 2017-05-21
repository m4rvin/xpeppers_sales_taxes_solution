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
import me.danieleangelucci.shopping.model.Item;
import me.danieleangelucci.shopping.model.PurchasedItem;
import me.danieleangelucci.shopping.model.ShoppingBasketInterface;
import me.danieleangelucci.shopping.model.UnreadableInputFileException;
import me.danieleangelucci.shopping.view.ShoppingBasketViewerInterface;

public class ShoppingBasketHandler
{

	public ShoppingBasketHandler(
			ShoppingBasketInterface shoppingBasket,
			ShoppingBasketViewerInterface sbViewer) {
		this.shoppingBasket = shoppingBasket;
		this.sbViewer = sbViewer;
	}
	
	/**
	 * Update the shopping basket items computing their final price.
	 */
	public void computeFinalPriceOnShoppingBasketItems() {
		this.shoppingBasket.computeFinalPrice();
	}
	
	/**
	 * Retrieve the items contained in the shopping basket.
	 * @return The list of items in the shopping basket.
	 * @throws EmptyShoppingBasketException if the shopping basket is empty.
	 */
	public List<? extends Item> getShoppingBasketItems() throws EmptyShoppingBasketException {
		return this.shoppingBasket.getShoppingBasketItems();
	} 
	
	/**
	 * Load the shopping basket items from the data file.
	 * @throws UnreadableInputFileException
	 */
	public void parseShoppingBasketItemsFromInputFile() 
			throws UnreadableInputFileException {
		Path inputFilepath = Paths.get(AppConfig.inputFilePath);
		Charset charset = Charset.forName("US-ASCII");
		
		try 
		(BufferedReader reader = 
		Files.newBufferedReader(inputFilepath, charset)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        Item item = PurchasedItem.parseLine(line);
		        shoppingBasket.put(item);
		    }
		} catch (IOException | UnexpectedInputDataFormatException e) {
			e.printStackTrace();
			throw new UnreadableInputFileException();
		}
	}
	
	/**
	 * Invoke the method to render the receipt of the shopping basket.
	 * @param shoppingBasketItems
	 */
	public void showReceipt(List<? extends Item> shoppingBasketItems) {
		this.sbViewer.formatReceipt(shoppingBasketItems);
	}

	
	private ShoppingBasketInterface shoppingBasket;
	private ShoppingBasketViewerInterface sbViewer;
}
