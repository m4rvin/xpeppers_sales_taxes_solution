package me.danieleangelucci.shopping;

public class UnloadableStoreException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -670904696534494119L;

	public UnloadableStoreException()
	{
		super("Store is unloadable due to a missing file or malformed file.\n"
				+ "Please check the store file.");
	}
	
}
