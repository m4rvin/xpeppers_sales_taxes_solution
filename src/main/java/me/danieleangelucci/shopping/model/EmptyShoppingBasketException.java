package me.danieleangelucci.shopping.model;

public class EmptyShoppingBasketException extends Exception
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -9065494972098059457L;

	public EmptyShoppingBasketException() {
		super("Shopping basket is empty. Please scan the items first.");
	}

}
