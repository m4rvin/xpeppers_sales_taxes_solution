import static org.junit.Assert.*;

import java.util.List;

import me.danieleangelucci.commons.AppConfig;
import me.danieleangelucci.shopping.ItemCategory;
import me.danieleangelucci.shopping.controller.StoreHandler;
import me.danieleangelucci.shopping.controller.UnexpectedInputDataFormatException;
import me.danieleangelucci.shopping.model.EmptyShoppingBasketException;
import me.danieleangelucci.shopping.model.PurchasedItem;
import me.danieleangelucci.shopping.model.ShoppingBasket;
import me.danieleangelucci.shopping.model.UnloadableStoreException;

import org.junit.Before;
import org.junit.Test;



public class TestInput1
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
	public void testInput1()
	{
		ShoppingBasket sb = new ShoppingBasket();
		
		try
		{
			String testInput;
			testInput = "1 book at 12.49";
			sb.put(PurchasedItem.parseLine(testInput));
			testInput = "1 music CD at 14.99";
			sb.put(PurchasedItem.parseLine(testInput));
			testInput = "1 chocolate bar at 0.85";
			sb.put(PurchasedItem.parseLine(testInput));
		} catch (UnexpectedInputDataFormatException e)
		{
			e.printStackTrace();
			fail();
		}
		
		sb.computeFinalPrice();
		List<PurchasedItem> items = null;
		try
		{
			items = (List<PurchasedItem>) sb.getShoppingBasketItems();
		} catch (EmptyShoppingBasketException e)
		{
			e.printStackTrace();
			fail();
		}
		
		//Check first entry
		assertEquals(items.get(0).getName(), "book");
		assertEquals(items.get(0).getQuantity(), 1);
		assertFalse(items.get(0).isImported());
		assertEquals(items.get(0).getCategory(), ItemCategory.BOOKS);
		assertEquals(items.get(0).getSellingPrice(), 12.49, 0);
		assertEquals(items.get(0).getFinalPrice(), 12.49, 0.001);
		
		//Check second entry
		assertEquals(items.get(1).getName(), "music CD");
		assertEquals(items.get(1).getQuantity(), 1);
		assertFalse(items.get(1).isImported());
		assertEquals(items.get(1).getCategory(), ItemCategory.OTHERS);
		assertEquals(items.get(1).getSellingPrice(), 14.99, 0);
		assertEquals(items.get(1).getFinalPrice(), 16.49, 0.001);
		
		//Check third entry
		assertEquals(items.get(2).getName(), "chocolate bar");
		assertEquals(items.get(2).getQuantity(), 1);
		assertFalse(items.get(2).isImported());
		assertEquals(items.get(2).getCategory(), ItemCategory.FOOD);
		assertEquals(items.get(2).getSellingPrice(), 0.85, 0);
		assertEquals(items.get(2).getFinalPrice(), 0.85, 0.001);
	}
	
	
}
