package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class Account extends AbstractModel
{
	// private variables
	private String id;
	private String nameLast;
	private String nameFirst;
	private BigDecimal balance;
	
	// constant variables.
	private final static int DECIMALS = 2;
	private final static RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
	private final static int MAX_ID_LENGTH = 4;

	// constructors
	public Account()
	{
		this.id = "";
		this.nameLast = "";
		this.nameFirst = "";
		this.balance = BigDecimal.ZERO;
	}
	
	public Account(String id, String last, String first, BigDecimal balance)
	{
		this.id = formatID(id);
		this.nameLast = last;
		this.nameFirst = first;
		this.balance = balance;
		this.balance.setScale(DECIMALS, ROUNDING_MODE);
	}
	
	// Getters	
	public String getNameLast(){ return this.nameLast; }
	public String getNameFirst(){ return this.nameFirst; }
	public String getID(){ return this.id; }
	public BigDecimal getBalance(){	return this.balance; }
	
	// function to make the id string format appropriate for the system.
	private String formatID(String id)
	{
		// if id is greater than MAX_ID_LENGTH
		// then we get the last MAX_ID_LENGTH characters in the string id
		// and make the newID that value.
		if(id.length() > MAX_ID_LENGTH)
		{
			char[] temp = new char[MAX_ID_LENGTH];
			id.getChars((id.length() - MAX_ID_LENGTH), id.length(), temp, 0);
			id = new String(temp);
		}
		
		// if id is less than MAX_ID_LENGTH
		// then we add zeros to the front of the string id
		else if(id.length() < MAX_ID_LENGTH)
		{
			int zeroCount = MAX_ID_LENGTH - id.length();
			char[] temp = new char[zeroCount];
			for(int i = 0; i < zeroCount; i++)
			{
				temp[i] = '0';
			}
			id = new String(temp) + id;
		}
		return id;
	}
	
	// function to deposit amount to the account.
	public synchronized void deposit(BigDecimal amount)
	{
		balance = balance.add(amount);
		balance = balance.setScale(DECIMALS, ROUNDING_MODE);
		final ModelEvent me = new ModelEvent(balance);
		notifyChanged(me);
		// we will need to update AccountSelectionView simultaneously?
	}
	
	public synchronized void withdraw(BigDecimal amount) throws OverdrawException
	{
		BigDecimal newBalance = balance.add(BigDecimal.ZERO);
		// an exception is thrown if the withdrawal results in
		// the account balance being overdrawn or less than 0.
		newBalance = balance.subtract(amount);
		if(newBalance.compareTo(BigDecimal.ZERO) < 0)
			throw new OverdrawException();
		balance = newBalance;
		balance = balance.setScale(DECIMALS, ROUNDING_MODE);
		final ModelEvent me = new ModelEvent(balance);
		notifyChanged(me);
		// we will need to update AccountSelectionView simultaneously?
	}
	
	public static RoundingMode getRoundingMode()
	{
		return ROUNDING_MODE;
	}
	
	public static int getDecimals()
	{
		return DECIMALS;
	}
}