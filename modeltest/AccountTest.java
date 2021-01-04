package modeltest;

import model.OverdrawException;
import model.Account;

import java.math.BigDecimal;
import org.junit.*;
import static org.junit.Assert.*;

public class AccountTest
{
	Account accountTest;
	
	@Before
	public void setup() throws Exception
	{
		String id = "1234";
		String last = "Carter";
		String first = "John";
		BigDecimal balance = new BigDecimal("4567.89");
		accountTest = new Account(id, last, first, balance);
		System.out.println("At AccountTest.setup():");
		System.out.println(String.format("%-20s: %-20s: %-6s: $ %s", accountTest.getNameLast(), accountTest.getNameFirst(), accountTest.getID(), accountTest.getBalance()));
	}
	
	// test for valid deposit.
	@Test
	public void testValidDeposit()
	{
		accountTest.deposit(new BigDecimal("1000.01"));
		assertEquals(accountTest.getBalance().toString(), "5567.90");
		System.out.println("At AccountTest.testValidDeposit():");
		System.out.println(String.format("%-20s: %-20s: %-6s: $ %s", accountTest.getNameLast(), accountTest.getNameFirst(), accountTest.getID(), accountTest.getBalance()));
	}

	// test for valid withdrawal.
	@Test
	public void testValidWithdrawal() throws Exception
	{
		accountTest.withdraw(new BigDecimal("1000.01"));
		assertEquals(accountTest.getBalance().toString(), "3567.88");
		System.out.println("At AccountTest.testValidWithdrawal():");
		System.out.println(String.format("%-20s: %-20s: %-6s: $ %s", accountTest.getNameLast(), accountTest.getNameFirst(), accountTest.getID(), accountTest.getBalance()));
	}

	// test for invalid withdrawal, resulting in OverdrawException
	@Test
	public void testOverdrawException()
	{
		try
		{
			accountTest.withdraw(new BigDecimal("100000.00"));
			System.out.println("At AccountTest.testOverdrawException() try:");
			fail("Overdrawal occurred, OverdrawException was not thrown!");
		}
		catch(OverdrawException ex)
		{
			assertTrue(true);
			ex.getMessage();
			System.out.println("At AccountTest.testOverdrawException() catch:");
			System.out.println(String.format("%-20s: %-20s: %-6s: $ %s", accountTest.getNameLast(), accountTest.getNameFirst(), accountTest.getID(), accountTest.getBalance()));	
		}
	}
	
	// garbage collector.
	@After
	public void tearDown() throws Exception
	{
		System.gc();
	}
}