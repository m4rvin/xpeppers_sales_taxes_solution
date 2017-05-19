import static org.junit.Assert.*;

import java.util.Set;

import me.danieleangelucci.commons.AppConfig;
import me.danieleangelucci.shopping.ItemCategory;
import me.danieleangelucci.shopping.model.Store;
import me.danieleangelucci.shopping.model.UnloadableStoreException;

import org.junit.Before;
import org.junit.Test;


public class LoadStoreTest
{
	public Store store = null;
	
	@Before
	public void initialize(){
		AppConfig.categoriesFilePath = "categories.json";
		try
		{
			store = Store.getStore();
		} catch (UnloadableStoreException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail();
		}
	}
	@Test
	public void loadBookCategoryTest()
	{
		Set<String> bookItems = store.getItems(ItemCategory.BOOKS);
		assertEquals(bookItems.size(), 1);
		assertTrue(bookItems.contains("book"));
	}
	
	@Test
	public void loadFoodCategoryTest()
	{
		Set<String> foodItems = store.getItems(ItemCategory.FOOD);
		assertEquals(foodItems.size(), 2);
		assertTrue(foodItems.contains("chocolate bar"));
		assertTrue(foodItems.contains("box of chocolates"));
	}
	
	@Test
	public void loadMedicalProductsCategoryTest()
	{
		Set<String> foodItems = store.getItems(ItemCategory.MEDICAL_PRODUCTS);
		assertEquals(foodItems.size(), 1);
		assertTrue(foodItems.contains("packet of headache pills"));
	}

}
