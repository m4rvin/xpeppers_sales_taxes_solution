package me.danieleangelucci.shopping.controller;

import me.danieleangelucci.shopping.model.Store;
import me.danieleangelucci.shopping.model.UnloadableStoreException;

public class StoreHandler
{
	public void initializeStore() throws UnloadableStoreException {
		Store.getStore();
	}
	
	public Store getStore() throws UnloadableStoreException {
		Store store = Store.getStore();
		return store;
	}
}
