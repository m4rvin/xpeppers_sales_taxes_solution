import static org.junit.Assert.*;
import me.danieleangelucci.commons.AppConfig;
import me.danieleangelucci.shopping.ItemCategory;
import me.danieleangelucci.shopping.controller.StoreHandler;
import me.danieleangelucci.shopping.controller.UnexpectedInputDataFormatException;
import me.danieleangelucci.shopping.model.PurchasedItem;
import me.danieleangelucci.shopping.model.UnloadableStoreException;

import org.junit.Before;
import org.junit.Test;



public class PurchasedItemTest
{
	public StoreHandler sHandler = new StoreHandler();

	@Before
	public void initialize(){
		AppConfig.categoriesFilePath = "categories.json";
		try
		{
			sHandler.initializeStore();
		} catch (UnloadableStoreException e)
		{
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	/**
	 * Test the parsing method correctly consider the two decimal digits.
	 */
	public void testPriceTwoDigitsParsing()
	{
		String testInput = "1 bottle of something at 15.00";
		PurchasedItem parsedPItem = null;
		try
		{
			parsedPItem = PurchasedItem.parseLine(testInput);
		} catch (UnexpectedInputDataFormatException e)
		{
			e.printStackTrace();
			fail();
		}
		
		assertEquals(parsedPItem.getName(), "bottle of something");
		assertEquals(parsedPItem.getCategory(), ItemCategory.OTHERS);
		assertEquals(parsedPItem.getSellingPrice(), 15.00, 0);
	}
	
	@Test
	/**
	 * Test the parsing method correctly parse non imported item in a known 
	 * category.
	 */
	public void testNonImportedKnownCategoryPurchasedItemParsing()
	{
		String testInput = "1 book at 12.49";
		PurchasedItem parsedPItem = null;
		try
		{
			parsedPItem = PurchasedItem.parseLine(testInput);
		} catch (UnexpectedInputDataFormatException e)
		{
			e.printStackTrace();
			fail();
		}
		
		assertEquals(parsedPItem.getQuantity(), 1);
		assertEquals(parsedPItem.getName(), "book");
		assertEquals(parsedPItem.getCategory(), ItemCategory.BOOKS);
		assertEquals(parsedPItem.getSellingPrice(), 12.49, 0);
		assertFalse(parsedPItem.isImported());
	}
	
	@Test
	/**
	 * Test the parsing method correctly parse a non imported item from OTHERS
	 * category.
	 */
	public void testNonImportedUnknownCategoryPurchasedItemParsing()
	{
		String testInput = "1 music CD at 14.99";
		PurchasedItem parsedPItem = null;
		try
		{
			parsedPItem = PurchasedItem.parseLine(testInput);
		} catch (UnexpectedInputDataFormatException e)
		{
			e.printStackTrace();
			fail();
		}
		
		assertEquals(parsedPItem.getQuantity(), 1);
		assertEquals(parsedPItem.getName(), "music CD");
		assertEquals(parsedPItem.getCategory(), ItemCategory.OTHERS);
		assertEquals(parsedPItem.getSellingPrice(), 14.99, 0);
		assertFalse(parsedPItem.isImported());
	}
	
	@Test
	/**
	 * Test the parsing method correctly parse an imported item from a 
	 * category in the store.
	 */
	public void testImportedKnownCategoryPurchasedItemParsing()
	{
		String testInput = "1 box of imported chocolates at 11.25";
		PurchasedItem parsedPItem = null;
		try
		{
			parsedPItem = PurchasedItem.parseLine(testInput);
		} catch (UnexpectedInputDataFormatException e)
		{
			e.printStackTrace();
			fail();
		}
		
		assertEquals(parsedPItem.getQuantity(), 1);
		assertEquals(parsedPItem.getName(), "box of chocolates");
		assertEquals(parsedPItem.getCategory(), ItemCategory.FOOD);
		assertEquals(parsedPItem.getSellingPrice(), 11.25, 0);
		assertTrue(parsedPItem.isImported());
	}
	
	@Test
	/**
	 * Test the parsing method correctly parse an imported item from the OTHERS 
	 * category.
	 */
	public void testImportedUnknownCategoryPurchasedItemParsing()
	{
		String testInput = "1 imported bottle of perfume at 27.99";
		PurchasedItem parsedPItem = null;
		try
		{
			parsedPItem = PurchasedItem.parseLine(testInput);
		} catch (UnexpectedInputDataFormatException e)
		{
			e.printStackTrace();
			fail();
		}
		
		assertEquals(parsedPItem.getQuantity(), 1);
		assertEquals(parsedPItem.getName(), "bottle of perfume");
		assertEquals(parsedPItem.getCategory(), ItemCategory.OTHERS);
		assertEquals(parsedPItem.getSellingPrice(), 27.99, 0);
		assertTrue(parsedPItem.isImported());
	}
}
