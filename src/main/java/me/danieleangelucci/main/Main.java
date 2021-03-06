package me.danieleangelucci.main;

import java.util.List;

import me.danieleangelucci.commons.AppConfig;
import me.danieleangelucci.shopping.controller.ShoppingBasketHandler;
import me.danieleangelucci.shopping.controller.StoreHandler;
import me.danieleangelucci.shopping.model.EmptyShoppingBasketException;
import me.danieleangelucci.shopping.model.Item;
import me.danieleangelucci.shopping.model.ShoppingBasket;
import me.danieleangelucci.shopping.model.UnloadableStoreException;
import me.danieleangelucci.shopping.model.UnreadableInputFileException;
import me.danieleangelucci.shopping.view.ShoppingBasketViewer;

public class Main
{

	public static void main(String[] args)
	{
		System.out.println("\nWelcome to \"Sales taxes problem\"!\n");
		readConfiguration(args);

		//Load the store (i.e. categories and products in the store)
		StoreHandler storeHandler = new StoreHandler();
		try
		{
			storeHandler.initializeStore();
		} catch (UnloadableStoreException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		//Create a controller and parse the shopping basket from the input file.
		ShoppingBasketHandler sbHandler = new ShoppingBasketHandler(new ShoppingBasket(), new ShoppingBasketViewer());
		try
		{
			sbHandler.parseShoppingBasketItemsFromInputFile();
		} catch (UnreadableInputFileException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		//Apply sales taxes to the shopping basket items.
		sbHandler.computeFinalPriceOnShoppingBasketItems();
		
		//Retrieve the shopping basket items and produce the receipt.
		List<? extends Item> itemList = null;
		try
		{
			itemList = sbHandler.getShoppingBasketItems();
		} catch (EmptyShoppingBasketException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		System.out.println("\nRECEIPT:\n");
		sbHandler.showReceipt(itemList);
	}
	
	private static void readConfiguration(String[] args) {
		checkCommandlineArgs(args);
		AppConfig.categoriesFilePath = args[0];
		AppConfig.inputFilePath = args[1];
		
		System.out.println("Loading categories file: " + AppConfig.categoriesFilePath);
		System.out.println("Using input file: " + AppConfig.inputFilePath + "\n");
	}
	
	private static void checkCommandlineArgs(String[] args) {
		
		if(args.length == 2)
			return;

		if(args.length < 2)
	        System.err.println("Too few arguments.");
		if(args.length > 2)
	        System.err.println("Too many arguments.");
        System.err.println("Commandline should be:\njava -jar \"path_to_jar\" \"categories_filepath\" \"input_filepath\"");
		System.exit(1);
	}

}
