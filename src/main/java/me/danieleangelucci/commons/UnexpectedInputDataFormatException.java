package me.danieleangelucci.commons;

public class UnexpectedInputDataFormatException extends Exception
{

	/**
	 * 
	 */
	private static final long serialVersionUID = -750038138463072273L;

	public UnexpectedInputDataFormatException()
	{
		super("Unexpected format of the input line.");
	}
	
}
