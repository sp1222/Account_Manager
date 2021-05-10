package model;

import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


import java.util.Map;
import java.util.HashMap;

import model.Account;
import view.AccountSelectionView;
import view.CreateAccountView;

public class AccountList extends AbstractModel
{
	private List<Account> accountList = new ArrayList<Account>();
	private Account currentAccount;
	private String accountFile;
	
	public AccountList() {super();}
	
	public AccountList(String fileName)// throws Exception
	{
		accountFile = fileName;
		loadAccountList();
		if(accountList.size() > 0)
			currentAccount = accountList.get(0);
		else
			System.out.println("Error, account File Empty!");
	}
		
	// add account to the accountList using id, name, and balance as parameters
	public synchronized void addAccount(String id, String last, String first, BigDecimal balance) throws DuplicateIDException
	{
		int newAcctIndex = 0;		// index tracker
		boolean isValidID = true;	// isValidID flag
		
		// adjust search to something more efficient
		for(Account acct : accountList)
		{
			if(id.compareTo(acct.getID()) == 0)
			{
				isValidID = false;
				// if id == acct.getID(), we have a Duplicate ID Exception.
				throw new DuplicateIDException();
			}
			else if(id.compareTo(acct.getID()) < 0)
				break;

			// if id == accountList(i).getID(), then throw new DuplicateIDException			
			// if id is less than current iteration of id in accountList, we keep going.
			newAcctIndex++;
		}

		if(isValidID)
		{
			// this is where we insert the newAccount at newAcctIndex.
			accountList.add(newAcctIndex, new Account(id, last, first, balance));
			currentAccount = accountList.get(newAcctIndex);
		}
	}
	
	
	// add account to the accountList using Account object as parameter	
	public synchronized void addAccount(Account newAccount) throws DuplicateIDException
	{
		int newAcctIndex = 0;		// index tracker
		boolean isValidID = true;	// isValidID flag

		// adjust search to something more efficient
		
		for(Account acct : accountList)
		{
			if(newAccount.getID().compareTo(acct.getID()) == 0)
			{
				isValidID = false;
				// if id == acct.getID(), we have a Duplicate ID Exception.
				throw new DuplicateIDException();
			}
			else if(newAccount.getID().compareTo(acct.getID()) < 0)
				break;
			// if id is less than current iteration of id in accountList, we keep going.
			newAcctIndex++;
		}
		if(isValidID)
		{
			// this is where we insert the newAccount at newAcctIndex.
			accountList.add(newAcctIndex, newAccount);
			currentAccount = newAccount;
		}
	}	
	
	public int getListNumberOfAccounts()
	{
		return accountList.size();
	}
	
	public String[] getListNames()
	{
		String[] names = new String[getListNumberOfAccounts()];
		int i = 0;
		for(Account acct : accountList)
		{
			names[i] = acct.getID() + "  " + acct.getNameLast() + ", " + acct.getNameFirst();
			i++;
		}
		return names;
	}
	
	// remove an account from the accountList using an id of an account as a parameter
	public synchronized void removeAccount(String id)
	{
		int index = -1;
		for(Account acct : accountList)
		{
			if(id.compareTo(acct.getID()) == 0)
			{
				// remove acct. if it exists.
				index = accountList.indexOf(acct);
				accountList.remove(acct);
				break;
			}
		}
	}
		
	// remove an account from the accountList using an Account object as a parameter
	public synchronized void removeAccount(Account remove)
	{
		int index = -1;
		for(Account acct : accountList)
		{
			if(remove.getID().compareTo(acct.getID()) == 0)
			{
				// remove acct.
				index = accountList.indexOf(acct);
				accountList.remove(acct);
				break;
			}
		}
	}		
	
	// function used to determine if an account exists.
	public boolean doesAccountExist(String id)
	{
		boolean exists = false;

		for(Account acct : accountList)
		{
			if(id.compareTo(acct.getID()) == 0)
			{
				exists = true;
				break;
			}
		}		
		return exists;
	}
	
	// function to return the index of an account ID.
	public int getAccountIndex(String id)
	{
		int i = 0;
		for(Account acct : accountList)
		{
			if(id.compareTo(acct.getID()) == 0)
			{
				i = accountList.indexOf(acct);
				break;
			}
		}
		return i;
	}
	
	// saves the accountList to file
	public synchronized void saveAccountList() throws IOException
	{
		
		File file;
		file = new File(accountFile);
		FileWriter outFile = new FileWriter(accountFile);
		try
		{
			for(Account acct : accountList)
			{
				outFile.write(String.format("%-20s %-20s %-8s %s", acct.getNameLast(), acct.getNameFirst(), acct.getID(), acct.getBalance() + "\n"));
			}
			outFile.close();
		}
		catch(IOException ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	// loads the accountList from file.
	public synchronized void loadAccountList()
	{
		File file;
		file = new File(accountFile);
		try(Scanner inFile = new Scanner(file))
		{
			while(inFile.hasNext())
			{
				String id = "";
				String b = "0.00";
				String last = "";
				String first = "";
				if(inFile.hasNext())
					last = inFile.next();
				if(inFile.hasNext())
					first = inFile.next();
				if(inFile.hasNext())
					id = inFile.next();
				if(inFile.hasNext())
					b = inFile.next();
				BigDecimal balance = new BigDecimal(b);
				Account acct = new Account(id, last, first, balance);
				accountList.add(acct);
			}
			inFile.close();
		}
		catch(FileNotFoundException ex)
		{
			System.out.println("File Read Error: File " + accountFile + " does not exist!");
			System.out.println(ex.getMessage());
		}
	}

	public int getCurrentAccountIndex() { return accountList.indexOf(currentAccount); }
	
	// update current account to the currently selected account in the dropdown box.
	public void setCurrentAccount(int index)
	{
		currentAccount = accountList.get(index);
	}
	
	public Account getCurrentAccount()
	{
		return currentAccount;
	}

	public String getCurrentAccountID() { return currentAccount.getID(); }
	public String getCurrentAccountNameLast() { return currentAccount.getNameLast(); }
	public String getCurrentAccountNameFirst() { return currentAccount.getNameFirst(); }
	public BigDecimal getCurrentAccountBalance() { return currentAccount.getBalance(); }
	// get the display name for the dropdown list.

	// get the display name for the dropdown list.
	public String getCurrentAccountDisplayName() 
	{ 
		return currentAccount.getID() + "  " + currentAccount.getNameLast() + ", " + currentAccount.getNameFirst();
	}
	
	public void exit()
	{
		try {
			saveAccountList();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // save current state after all the threads shutdown
		System.exit(0);
	}
	
	// print the list to the console
	// primarily for testing purposes
	public void printList()
	{			
		System.out.println(String.format("\n%-20s: %-20s: %-6s: %s", "Last Name", "First Name", "ID", "Balance"));
		for(Account acct : accountList)
		{
			System.out.println(String.format("%-20s: %-20s: %-6s: $ %s", acct.getNameLast(), acct.getNameFirst(), acct.getID(), acct.getBalance()));
		}
	}
}