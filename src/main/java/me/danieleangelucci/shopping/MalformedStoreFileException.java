package me.danieleangelucci.shopping;

public class MalformedStoreFileException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6525905058518236170L;

	public MalformedStoreFileException()
	{
		super("Malformed file for the store.");
	}
}
