package me.danieleangelucci.shopping.model;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import me.danieleangelucci.commons.AppConfig;
import me.danieleangelucci.shopping.ItemCategory;

public class Store
{
	/**
	 * Singleton method to load the store with its categories and items.
	 * @return The instance of store.
	 * @throws UnloadableStoreException
	 */
	public static Store getStore() throws UnloadableStoreException {
		if(store == null) {
			try
			{
				loadStore();
			} catch (FileNotFoundException | MalformedStoreFileException e)
			{
				e.printStackTrace();
				throw new UnloadableStoreException();
			}
		}
		return store;
	}
	
	/**
	 * Retrieve the items of a specific store category.
	 * @param category The category from which to retrieve the items.
	 * @return
	 */
	public Set<String> getItems(ItemCategory category) {
		return categories.get(category);
	}
	
	
	private static Store store = null;
	
	private Map<ItemCategory, Set<String>> categories;
	
	private Store(){
		this.categories = new HashMap<ItemCategory, Set<String>>();
	}
	
	/**
	 * Load the store from a structured file, looking for categories and their
	 * items.
	 * @throws FileNotFoundException
	 * @throws MalformedStoreFileException
	 */
	private static void loadStore() throws FileNotFoundException, MalformedStoreFileException{
		store = new Store();
		try {
			Object root = loadStructuredFile();
			loadCategory("books", ItemCategory.BOOKS, store, root);
			loadCategory("food", ItemCategory.FOOD, store, root);
			loadCategory("medical products", ItemCategory.MEDICAL_PRODUCTS, store, root);
		}
		catch(JsonIOException e) {
			e.printStackTrace();
			throw new MalformedStoreFileException();
		}
	}
	
	/**
	 * Read a structured file. The current implementation parse a JSON file.
	 * @return The object representing the structured file to navigate.
	 * @throws JsonSyntaxException
	 * @throws FileNotFoundException
	 * @throws MalformedStoreFileException
	 */
	private static Object loadStructuredFile() throws JsonSyntaxException, FileNotFoundException, MalformedStoreFileException{
		JsonParser parser = new JsonParser();
		try {
			JsonElement jsontree = parser.parse(new FileReader(AppConfig.categoriesFilePath));
			JsonObject jo = jsontree.getAsJsonObject();
			return jo;
		}
		catch(JsonIOException e) {
			e.printStackTrace();
			throw new MalformedStoreFileException();
		}
	}
	
	/**
	 * Load the categories on the structured file into the store, within their 
	 * items.
	 * @param categoryNameInFile
	 * @param categoryId
	 * @param store
	 * @param root
	 */
	private static void loadCategory(String categoryNameInFile, ItemCategory categoryId, Store store, Object root){
		JsonObject jo = (JsonObject)root;
		JsonArray catJson = jo.getAsJsonArray(categoryNameInFile);

		Set<String> catItems = new HashSet<String>();
		for (JsonElement je : catJson) {
			String itemName = je.getAsString();
			catItems.add(itemName);
		}
		store.categories.put(categoryId, catItems);
	}
}
