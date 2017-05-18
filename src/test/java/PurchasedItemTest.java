import static org.junit.Assert.*;
import me.danieleangelucci.commons.AppConfig;
import me.danieleangelucci.commons.PurchasedItemParser;
import me.danieleangelucci.commons.UnexpectedInputDataFormatException;
import me.danieleangelucci.shopping.ItemCategory;
import me.danieleangelucci.shopping.PurchasedItem;
import me.danieleangelucci.shopping.Store;
import me.danieleangelucci.shopping.UnloadableStoreException;

import org.junit.Before;
import org.junit.Test;

import static org.mockito.Mockito.*;



public class PurchasedItemTest
{
	@Before
	public void initialize(){
		try
		{
			AppConfig.categoriesFilePath = "categories.json";
			Store store = Store.getStore();
		} catch (UnloadableStoreException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	
	@Test
	public void testNonImportedKnownCategoryPurchasedItemParsing()
	{
		String testInput = "1 book at 12.49";
		PurchasedItem parsedPItem = null;
		try
		{
			parsedPItem = PurchasedItemParser.parseLine(testInput);
		} catch (UnexpectedInputDataFormatException e)
		{
			e.printStackTrace();
			fail();
		}
		
		
		assertEquals(parsedPItem.getQuantity(), 1);
		assertEquals(parsedPItem.getCategory(), ItemCategory.BOOKS);
		assertEquals(parsedPItem.getSellingPrice(), 12.49, 0);
	}
	
	@Test
	public void testImportedKnownCategoryPurchasedItemParsing()
	{
		String testInput = "1 box of imported chocolates at 11.25";
		PurchasedItem parsedPItem = null;
		try
		{
			parsedPItem = PurchasedItemParser.parseLine(testInput);
		} catch (UnexpectedInputDataFormatException e)
		{
			e.printStackTrace();
			fail();
		}
		
		assertEquals(parsedPItem.getQuantity(), 1);
		assertEquals(parsedPItem.getCategory(), ItemCategory.FOOD);
		assertEquals(parsedPItem.getSellingPrice(), 11.25, 0);
		assertTrue(parsedPItem.isImported());
	}
	
	@Test
	public void testImportedUnknownCategoryPurchasedItemParsing()
	{
		String testInput = "1 imported bottle of perfume at 27.99";
		PurchasedItem parsedPItem = null;
		try
		{
			parsedPItem = PurchasedItemParser.parseLine(testInput);
		} catch (UnexpectedInputDataFormatException e)
		{
			e.printStackTrace();
			fail();
		}
		
		assertEquals(parsedPItem.getQuantity(), 1);
		assertEquals(parsedPItem.getCategory(), ItemCategory.OTHERS);
		assertEquals(parsedPItem.getSellingPrice(), 27.99, 0);
		assertTrue(parsedPItem.isImported());
	}
	

}
