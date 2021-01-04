package model;

public class OverdrawException extends Exception
{

	public OverdrawException()
	{
		super("Account Overdrawn Exception: Amount being removed from balance results in overdrawal of the account.");
	}
}