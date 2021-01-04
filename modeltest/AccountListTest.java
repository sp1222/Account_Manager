package modeltest;

import java.math.BigDecimal;

import model.AccountList;
import model.DuplicateIDException;
import model.Account;

import org.junit.*;
import static org.junit.Assert.*;

public class AccountListTest
{
	AccountList wonderBank = new AccountList();					// AccountList object containing accountListTest
	
	// setup accountListTest with test accounts
	@Before
	public void setup() throws Exception
	{
		wonderBank.addAccount("1234", "Baker", "John", new BigDecimal("456.78"));
		wonderBank.addAccount("4567", "Black", "Jack", new BigDecimal("52.44"));
		wonderBank.addAccount("6789", "Blocker", "Jacob", new BigDecimal("496.88"));
	}
	
	// test adding accounts to accountListTest in AccountsTest object.
	@Test
	public void testAddAccount() throws Exception
	{
		int listCount = wonderBank.getListNumberOfAccounts();
		Account newAccount = new Account("9634", "Polo", "Marco", new BigDecimal("492.57"));
		wonderBank.addAccount(newAccount);
		System.out.println("At AccountTest.testAddAccount() pt 1:");
		assertEquals(listCount + 1, wonderBank.getListNumberOfAccounts());
		
		listCount++;
		
		wonderBank.addAccount(new Account("7634", "James", "Jessie", new BigDecimal("678.14")));
		System.out.println("At AccountTest.testAddAccount() pt2:");
		assertEquals(listCount + 1, wonderBank.getListNumberOfAccounts());
		
		// ??? What else do I need to do here???
	}
	
	// test adding account of a duplicate ID for DuplicateIDException
	@Test
	public void testAddAccountDuplicateIDException() throws Exception
	{
		try
		{
		// duplicate ID here.
			wonderBank.addAccount(new Account("1234", "James", "Jessie", new BigDecimal("678.14")));
			System.out.println("At AccountTest.testAddAccountDuplicateIDException() try:");
			fail("DuplicateIDException was not thrown!");
		}
		catch(DuplicateIDException ex)
		{
			assertTrue(true);
			System.out.println("At AccountTest.testAddAccountDuplicateIDException() catch:");
			wonderBank.printList();
		}
	}

	// test for removing account that exists
	// ??? Do I need an exception for an account that DNE???
	@Test
	public void testRemoveAccount() throws Exception
	{
		int listCount = wonderBank.getListNumberOfAccounts();
		System.out.println("At AccountTest.testRemoveAccount():");
		wonderBank.removeAccount("4567");
		System.out.println("At AccountTest.testAddAccount() pt2:");
		assertEquals(listCount - 1, wonderBank.getListNumberOfAccounts());
		// ??? What else do I need to do here???
	}
	
/*

	// test for removing account that exists
	// ??? Do I need an exception for an account that DNE???
	@Test
	public void testSaveAccount() throws Exception
	{

		// ??? What else do I need to do here???
	}

	// test for removing account that exists
	// ??? Do I need an exception for an account that DNE???
	@Test
	public void testLoadAccount() throws Exception
	{
		
		// ??? What else do I need to do here???
	}
	
*/
	
	// garbage collector.
	@After
	public void tearDown() throws Exception
	{
		System.gc();
	}
}