import static org.junit.Assert.*;

import java.util.Set;

import me.danieleangelucci.commons.AppConfig;
import me.danieleangelucci.shopping.ItemCategory;
import me.danieleangelucci.shopping.controller.StoreHandler;
import me.danieleangelucci.shopping.model.UnloadableStoreException;

import org.junit.Before;
import org.junit.Test;


public class LoadStoreTest
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
	public void loadBookCategoryTest()
	{
		Set<String> bookItems = null;
		try
		{
			bookItems = sHandler.getItems(ItemCategory.BOOKS);
		} catch (UnloadableStoreException e)
		{
			e.printStackTrace();
			fail();
		}
		assertEquals(bookItems.size(), 1);
		assertTrue(bookItems.contains("book"));
	}
	
	@Test
	public void loadFoodCategoryTest()
	{
		Set<String> foodItems = null;
		try
		{
			foodItems = sHandler.getItems(ItemCategory.FOOD);
		} catch (UnloadableStoreException e)
		{
			e.printStackTrace();
			fail();
		}
		assertEquals(foodItems.size(), 2);
		assertTrue(foodItems.contains("chocolate bar"));
		assertTrue(foodItems.contains("box of chocolates"));
	}
	
	@Test
	public void loadMedicalProductsCategoryTest()
	{
		Set<String> foodItems = null;
		try
		{
			foodItems = sHandler.getItems(ItemCategory.MEDICAL_PRODUCTS);
		} catch (UnloadableStoreException e)
		{
			e.printStackTrace();
			fail();
		}
		assertEquals(foodItems.size(), 1);
		assertTrue(foodItems.contains("packet of headache pills"));
	}

}
