package me.danieleangelucci.main;

import me.danieleangelucci.commons.AppConfig;
import me.danieleangelucci.shopping.ShoppingBasketAnalyzer;
import me.danieleangelucci.shopping.Store;
import me.danieleangelucci.shopping.UnloadableStoreException;

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
		
		try
		{
			Store store = Store.getStore();
		} catch (UnloadableStoreException e)
		{
			e.printStackTrace();
			System.exit(1);
		}
		
		ShoppingBasketAnalyzer sbAnalyzer = new ShoppingBasketAnalyzer();
		sbAnalyzer.printPurchasedItemFromInputFile();
		//TODO

		
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
