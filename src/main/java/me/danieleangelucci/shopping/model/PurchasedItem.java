package me.danieleangelucci.shopping.model;

import java.text.DecimalFormat;

import me.danieleangelucci.shopping.ItemCategory;
import me.danieleangelucci.shopping.controller.UnexpectedInputDataFormatException;

public class PurchasedItem extends Item
{
	public PurchasedItem() {}
	
	public PurchasedItem(
			String name, 
			ItemCategory category, 
			double sellingPrice,
			int quantity,
			boolean imported) {
		super(name, category, sellingPrice);
		this.quantity = quantity;
		this.imported = imported;
	}
	
	public PurchasedItem(
			String name, 
			ItemCategory category, 
			double sellingPrice,
			double finalPrice,
			int quantity,
			boolean imported) {
		super(name, category, sellingPrice);
		this.finalPrice = finalPrice;
		this.quantity = quantity;
		this.imported = imported;
	}

	public double getFinalPrice()
	{
		return finalPrice;
	}
	
	public int getQuantity()
	{
		return quantity;
	}

	public boolean isImported()
	{
		return imported;
	}

	public void setFinalPrice(double finalPrice)
	{
		this.finalPrice = finalPrice;
	}
	
	public void setImported(boolean imported)
	{
		this.imported = imported;
	}
	
	public void setQuantity(int quantity)
	{
		this.quantity = quantity;
	}

	@Override
	public String toString()
	{
		return "PurchasedItem [finalPrice=" + String.valueOf(
				new DecimalFormat("0.00").format(finalPrice)) + ", quantity="
				+ quantity + ", imported=" + imported + ", name="
				+ getName() + ", category=" + getCategory()
				+ ", sellingPrice=" + 
				String.valueOf(
						new DecimalFormat("0.00").format(getSellingPrice()))
				+ "]";
	}

	/**
	 * This method parse a single input data entry (i.e. an item on the list)
	 * and build the corresponding
	 * PurchasedItem object. It works removing the keywords and placeholder
	 * once a time, starting from "imported", then removing the unuseful "at",
	 * then the quantity of items and their price. The final step only contains
	 * the item name, useful to match the category against which apply the 
	 * taxes.
	 * @param shoppingBasketEntry The input line of an item.
	 * @return The PurchasedItem object representing the input line.
	 * @throws UnexpectedInputDataFormatException
	 */
	public static PurchasedItem parseLine(String shoppingBasketEntry) 
			throws UnexpectedInputDataFormatException {
		boolean importedItem = false;
		
		//Check and remove "imported" keyword
		String inputLine = shoppingBasketEntry;
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
		inputLine = removeSubstring(
				inputLine, 
				String.valueOf(new DecimalFormat("0.00").format(sellingPrice)));
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
			for (ItemCategory ic : ItemCategory.values()) {
				//The OTHERS category has no elements. Do not analyze it.
				if(ic.equals(ItemCategory.OTHERS))
					continue;
				if(store.getItems(ic).contains(itemName))
					return ic;
			}
			//The item is not in the stored categories, so it is in OTHERS.
			return ItemCategory.OTHERS;
		} catch (UnloadableStoreException e)
		{
			e.printStackTrace();
			System.exit(1);
			return null; //Unuseful. Added to avoid compile error
		}
	}

	private double finalPrice = 0.0;
	private int quantity = 0;
	private boolean imported = false;
}
