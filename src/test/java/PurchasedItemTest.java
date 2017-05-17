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
	public void testPurchasedItemParsing()
	{
		String testInput = "1 book at 12.49";
		PurchasedItem parsedPItem = null;
		try
		{
			parsedPItem = PurchasedItemParser.parseLine(testInput);
		} catch (UnexpectedInputDataFormatException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
		
		PurchasedItem pItem = mock(PurchasedItem.class);
		pItem.setQuantity(1);
		pItem.setCategory(ItemCategory.BOOKS);
		pItem.setSellingPrice(12.49);
		
		//assertEquals(parsedPItem.getQuantity(), pItem.getQuantity());
		//assertEquals(parsedPItem.getCategory(), pItem.getCategory());
		//assertEquals(parsedPItem.getSellingPrice(), pItem.getSellingPrice(), 0);


	}

}
