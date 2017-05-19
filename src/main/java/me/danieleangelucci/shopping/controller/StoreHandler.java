package me.danieleangelucci.shopping.controller;

import java.util.Set;

import me.danieleangelucci.shopping.ItemCategory;
import me.danieleangelucci.shopping.model.Store;
import me.danieleangelucci.shopping.model.UnloadableStoreException;

public class StoreHandler
{
	public void initializeStore() throws UnloadableStoreException {
		Store.getStore();
	}
	
	public Store getStore() throws UnloadableStoreException {
		return Store.getStore();
	}
	
	public Set<String> getItems(ItemCategory category) throws UnloadableStoreException {
		Store store = Store.getStore();
		return store.getItems(category);
	} 
}
