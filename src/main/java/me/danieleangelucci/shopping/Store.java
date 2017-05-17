package me.danieleangelucci.shopping;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonIOException;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;

import me.danieleangelucci.commons.AppConfig;

public class Store
{
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
	
	public Set<String> getItems(ItemCategory category) {
		return categories.get(category);
	}
	
	
	
	private static Store store = null;
	
	private Map<ItemCategory, Set<String>> categories;
	
	private Store(){
		this.categories = new HashMap<ItemCategory, Set<String>>();
	}
	
	private static void loadStore() 
			throws FileNotFoundException, MalformedStoreFileException{
		store = new Store();
		try {
			Object root = loadStructuredFile();
			loadCategory("books", ItemCategory.BOOKS, store, root);
			loadCategory("food", ItemCategory.FOOD, store, root);
			loadCategory(
					"medical products", 
					ItemCategory.MEDICAL_PRODUCTS, 
					store,
					root);
		}
		catch(JsonIOException e) {
			e.printStackTrace();
			throw new MalformedStoreFileException();
		}
	}
	
	private static Object loadStructuredFile() 
			throws JsonSyntaxException, 
				   FileNotFoundException, 
				   MalformedStoreFileException{
		JsonParser parser = new JsonParser();
		try {
			JsonElement jsontree = parser.parse(
				new FileReader(AppConfig.categoriesFilePath));
			JsonObject jo = jsontree.getAsJsonObject();
			return jo;
		}
		catch(JsonIOException e) {
			e.printStackTrace();
			throw new MalformedStoreFileException();
		}
	}
	
	private static void loadCategory(
			String categoryNameInFile, 
			ItemCategory categoryId, 
			Store store, 
			Object root){
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
