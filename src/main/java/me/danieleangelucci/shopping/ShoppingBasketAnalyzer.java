package me.danieleangelucci.shopping;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import me.danieleangelucci.commons.AppConfig;
import me.danieleangelucci.commons.PurchasedItemParser;
import me.danieleangelucci.commons.UnexpectedInputDataFormatException;

public class ShoppingBasketAnalyzer
{
	public ShoppingBasketAnalyzer() {
		this.purchasedItems = new ArrayList<PurchasedItem>();
	}
	
	public void computeFinalPriceOfPurchasedItem() {
		for(PurchasedItem pi : this.purchasedItems) {
			pi.setFinalPrice(pi.getSellingPrice());

			//Apply 10% sales tax: Basic sales tax
			if(pi.getCategory().equals(ItemCategory.OTHERS))
			{
				double salesTax = pi.getSellingPrice() * 10/100;
				pi.setFinalPrice(pi.getFinalPrice() + salesTax);
			}
			//Apply 5% sales tax: Import duty 
			if(pi.isImported())
			{
				double salesTax = pi.getSellingPrice() * 5/100;
				pi.setFinalPrice(pi.getFinalPrice() + salesTax);
			}
		}
	}
	
	

	public void parsePurchasedItemFromInputFile() 
			throws UnreadableInputFileException {
		//Avoid parsing the same input file more than once.
		if(this.purchasedItems.size() != 0)
			return;
		
		Path inputFilepath = Paths.get(AppConfig.inputFilePath);
		
		Charset charset = Charset.forName("US-ASCII");
		try 
		(BufferedReader reader = 
		Files.newBufferedReader(inputFilepath, charset)) {
		    String line = null;
		    while ((line = reader.readLine()) != null) {
		        PurchasedItem pItem = PurchasedItemParser.parseLine(line);
		        this.purchasedItems.add(pItem);
		    }
		} catch (IOException | UnexpectedInputDataFormatException e) {
			e.printStackTrace();
			throw new UnreadableInputFileException();
		}
	}
	
	public void printPurchasedItemFromInputFile() {
		try
		{
			this.parsePurchasedItemFromInputFile();
		} catch (UnreadableInputFileException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for(PurchasedItem pi : this.purchasedItems) {
			System.out.println(pi);
		}
	}
	
	private List<PurchasedItem> purchasedItems;
}
