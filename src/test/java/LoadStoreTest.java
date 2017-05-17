import static org.junit.Assert.*;

import java.util.List;

import me.danieleangelucci.commons.AppConfig;
import me.danieleangelucci.shopping.ItemCategory;
import me.danieleangelucci.shopping.Store;
import me.danieleangelucci.shopping.UnloadableStoreException;

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
		List<String> bookItems = store.getItems(ItemCategory.BOOKS);
		assertEquals(bookItems.size(), 1);
		assertEquals(bookItems.get(0), "book");
	}
	
	@Test
	public void loadFoodCategoryTest()
	{
		List<String> foodItems = store.getItems(ItemCategory.FOOD);
		assertEquals(foodItems.size(), 2);
		assertEquals(foodItems.get(0), "chocolate bar");
		assertEquals(foodItems.get(1), "box of chocolates");
	}
	
	@Test
	public void loadMedicalProductsCategoryTest()
	{
		List<String> foodItems = store.getItems(ItemCategory.MEDICAL_PRODUCTS);
		assertEquals(foodItems.size(), 1);
		assertEquals(foodItems.get(0), "packet of headache pills");
	}

}
