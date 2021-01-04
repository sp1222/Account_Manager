

package controller;
import java.math.BigDecimal;
import java.math.RoundingMode;
import model.Account;
import model.OverdrawException;
import view.AccountView;
import view.JFrameView;

// this is built to work with only one window, not enough time to look at multiple windows.
// working with multiple windows is what i look at next.


public class AccountController extends AbstractController 
{	
	private final static int DECIMALS = 2;
	private final static RoundingMode ROUNDING_MODE = RoundingMode.HALF_EVEN;
	private final BigDecimal amountDefault = new BigDecimal("20.00");	
	private final static String Deposit = "Deposit";
	private final static String Withdrawal = "Withdrawal";
	
	public AccountController(Account account)
	{
		setModel(account);
		setView(new AccountView((Account)getModel(), this));
		((JFrameView)getView()).setVisible(true);
	}

	// IT WOULD BE EASY TO WRITE A TEST CASE HERE
	public void operation(String option)
	{
		// this method of comparison does address comparison
		if(option.equals(Deposit))	// option.equals(Deposit) method will compare Strings symbol by symbol
		{	
			amountDefault.setScale(DECIMALS, ROUNDING_MODE);			
			BigDecimal amount = ((AccountView)getView()).getTransactionAmount();
			((Account)getModel()).deposit(amount);
		}
		else if(option.equals(Withdrawal))
		{
			amountDefault.setScale(DECIMALS, ROUNDING_MODE);
			BigDecimal amount = ((AccountView)getView()).getTransactionAmount();
			try
			{
				((Account)getModel()).withdraw(amount);
			}
			catch(OverdrawException ex)
			{
				final String msg = ex.getMessage();
				((AccountView)getView()).showError(msg);
				System.out.println("Account Overdrawn Exception: Amount being removed from balance results in overdrawal of the account.");
			}
		}
	}
}

