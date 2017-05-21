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



public class TestInput2
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
	public void testInput2()
	{
		ShoppingBasket sb = new ShoppingBasket();
		
		try
		{
			String testInput;
			testInput = "1 imported box of chocolates at 10.00";
			sb.put(PurchasedItem.parseLine(testInput));
			testInput = "1 imported bottle of perfume at 47.50";
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
		assertEquals(items.get(0).getName(), "box of chocolates");
		assertEquals(items.get(0).getQuantity(), 1);
		assertTrue(items.get(0).isImported());
		assertEquals(items.get(0).getCategory(), ItemCategory.FOOD);
		assertEquals(items.get(0).getSellingPrice(), 10.00, 0);
		assertEquals(items.get(0).getFinalPrice(), 10.50, 0.001);
		
		//Check second entry
		assertEquals(items.get(1).getName(), "bottle of perfume");
		assertEquals(items.get(1).getQuantity(), 1);
		assertTrue(items.get(1).isImported());
		assertEquals(items.get(1).getCategory(), ItemCategory.OTHERS);
		assertEquals(items.get(1).getSellingPrice(), 47.50, 0);
		assertEquals(items.get(1).getFinalPrice(), 54.65, 0.001);
	}
	
	
}
