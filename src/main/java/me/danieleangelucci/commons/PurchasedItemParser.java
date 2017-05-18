package me.danieleangelucci.commons;

import me.danieleangelucci.shopping.ItemCategory;
import me.danieleangelucci.shopping.PurchasedItem;
import me.danieleangelucci.shopping.Store;
import me.danieleangelucci.shopping.UnloadableStoreException;

public class PurchasedItemParser
{
	/**
	 * This method parse a single input data line and build the corresponding
	 * PurchasedItem object. It works removing the keywords and placeholder
	 * once a time, starting from "imported", then removing the unuseful "at",
	 * then the quantity of items and their price. The final step only contains
	 * the item name, useful to match the category against which apply the 
	 * taxes.
	 * @param shoppingBasketLine The input line of an item.
	 * @return The PurchasedItem object representing the input line.
	 * @throws UnexpectedInputDataFormatException
	 */
	public static PurchasedItem parseLine(String shoppingBasketLine) 
			throws UnexpectedInputDataFormatException {
		boolean importedItem = false;
		
		//Check and remove "imported" keyword
		String inputLine = shoppingBasketLine;
		String tmpLine = removeSubstring(inputLine, " imported ");
		if (tmpLine.length() != inputLine.length()) {
			importedItem = true;
			inputLine = tmpLine;
		}
		
		//Check and remove "at" keyword
		tmpLine = removeSubstring(inputLine, " at ");
		if (tmpLine.length() == inputLine.length())
			throw new UnexpectedInputDataFormatException();
		inputLine = tmpLine;

		//Look for quantity and selling price
		String[] splittedLine = inputLine.split(" ");
		int quantity = Integer.parseInt(splittedLine[0]);
		double sellingPrice = Double.parseDouble(
				splittedLine[splittedLine.length -1]);
		
		//Remove quantity and selling price, keeping monospaces
		inputLine = removeSubstring(inputLine, String.valueOf(quantity));
		inputLine = removeSubstring(inputLine, String.valueOf(sellingPrice));
		inputLine = inputLine.trim();

		//Now inputLine only contains the item name. Use it to match 
		//the category.
		ItemCategory category = matchCategory(inputLine);
		PurchasedItem pItem = new PurchasedItem(
				inputLine,
				category,
				sellingPrice,
				quantity,
				importedItem);

		return pItem;
	}
	
	/**
	 * Remove "substring" from "from" and keeping the monospaced input string
	 * format.
	 * @param from
	 * @param substring
	 * @return The same input string if "substring" is not found. The new string
	 * otherwise.
	 */
	private static String removeSubstring(String from, String substring) {
		String firstReplace = from.replaceFirst(substring, " ");
		return firstReplace;
	}
	
	private static ItemCategory matchCategory(String itemName) {
		try
		{
			Store store = Store.getStore();
			for (ItemCategory ic : ItemCategory.values())
				if(store.getItems(ic).contains(itemName))
					return ic;
			return ItemCategory.OTHERS;
		} catch (UnloadableStoreException e)
		{
			e.printStackTrace();
			System.exit(1);
			return null; //Unuseful. Added to avoid compile error
		}
	}
}
