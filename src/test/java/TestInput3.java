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



public class TestInput3
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
	public void testInput3()
	{
		ShoppingBasket sb = new ShoppingBasket();
		
		try
		{
			String testInput;
			testInput = "1 imported bottle of perfume at 27.99";
			sb.put(PurchasedItem.parseLine(testInput));
			testInput = "1 bottle of perfume at 18.99";
			sb.put(PurchasedItem.parseLine(testInput));
			testInput = "1 packet of headache pills at 9.75";
			sb.put(PurchasedItem.parseLine(testInput));
			testInput = "1 box of imported chocolates at 11.25";
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
		assertEquals(items.get(0).getName(), "bottle of perfume");
		assertEquals(items.get(0).getQuantity(), 1);
		assertTrue(items.get(0).isImported());
		assertEquals(items.get(0).getCategory(), ItemCategory.OTHERS);
		assertEquals(items.get(0).getSellingPrice(), 27.99, 0);
		assertEquals(items.get(0).getFinalPrice(), 32.19, 0.001);
		
		//Check second entry
		assertEquals(items.get(1).getName(), "bottle of perfume");
		assertEquals(items.get(1).getQuantity(), 1);
		assertFalse(items.get(1).isImported());
		assertEquals(items.get(1).getCategory(), ItemCategory.OTHERS);
		assertEquals(items.get(1).getSellingPrice(), 18.99, 0);
		assertEquals(items.get(1).getFinalPrice(), 20.89, 0.001);
		
		//Check third entry
		assertEquals(items.get(2).getName(), "packet of headache pills");
		assertEquals(items.get(2).getQuantity(), 1);
		assertFalse(items.get(2).isImported());
		assertEquals(items.get(2).getCategory(), ItemCategory.MEDICAL_PRODUCTS);
		assertEquals(items.get(2).getSellingPrice(), 9.75, 0);
		assertEquals(items.get(2).getFinalPrice(), 9.75, 0.001);
		
		//Check fourth entry
		assertEquals(items.get(3).getName(), "box of chocolates");
		assertEquals(items.get(3).getQuantity(), 1);
		assertTrue(items.get(3).isImported());
		assertEquals(items.get(3).getCategory(), ItemCategory.FOOD);
		assertEquals(items.get(3).getSellingPrice(), 11.25, 0);
		assertEquals(items.get(3).getFinalPrice(), 11.85, 0.001);
	}
	
	
}
