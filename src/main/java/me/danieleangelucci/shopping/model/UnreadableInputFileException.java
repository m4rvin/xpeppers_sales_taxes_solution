package me.danieleangelucci.shopping.model;

public class UnreadableInputFileException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5808011189570650153L;

	public UnreadableInputFileException() {
		super("The file containing the input data could not be correctly read."
				+ "Please check it.");
	}
}
