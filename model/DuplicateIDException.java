package model;

/* DuplicateIDException
 *  
 * Exception condition when the user input of an id for
 * a new account to the accountList already exists
 * in the account list.
 */

public class DuplicateIDException extends Exception
{
	public DuplicateIDException()
	{
		super("Duplicate ID Exception: ID entered for a new account already exists in the account list.");
	}
}