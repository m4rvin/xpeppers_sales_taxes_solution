package me.danieleangelucci.main;

import java.util.List;

import me.danieleangelucci.commons.AppConfig;
import me.danieleangelucci.shopping.controller.ShoppingBasketHandler;
import me.danieleangelucci.shopping.controller.StoreHandler;
import me.danieleangelucci.shopping.model.EmptyShoppingBasketException;
import me.danieleangelucci.shopping.model.PurchasedItem;
import me.danieleangelucci.shopping.model.ShoppingBasketAnalyzer;
import me.danieleangelucci.shopping.model.UnloadableStoreException;
import me.danieleangelucci.shopping.model.UnreadableInputFileException;
import me.danieleangelucci.shopping.view.ShoppingBasketViewer;

public class Main
{

	public static void main(String[] args)
	{
		
		checkCommandlineArgs(args);
		
		AppConfig.categoriesFilePath = args[0];
		AppConfig.inputFilePath = args[1];

		System.out.println("Welcome to \"Sales taxes problem\"!");
		System.out.println("Loading categories file: " + AppConfig.categoriesFilePath);
		System.out.println("Using input file: " + AppConfig.inputFilePath);
		
		StoreHandler storeHandler = new StoreHandler();
		try
		{
			storeHandler.initializeStore();
		} catch (UnloadableStoreException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		ShoppingBasketAnalyzer sbAnalyzer = new ShoppingBasketAnalyzer();
		ShoppingBasketViewer sbViewer = new ShoppingBasketViewer();
		ShoppingBasketHandler sbHandler = new ShoppingBasketHandler(sbAnalyzer, sbViewer);
		try
		{
			sbHandler.parsePurchasedItemFromInputFile();
		} catch (UnreadableInputFileException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		sbHandler.computeFinalPriceOnShoppingBasketItems();
		
		//sbAnalyzer.printPurchasedItemFromInputFile();
		//TODO
		
		List<PurchasedItem> itemList = null;
		try
		{
			itemList = sbHandler.getShoppingBasketItems();
		} catch (EmptyShoppingBasketException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		sbHandler.showReceipt(itemList);
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
