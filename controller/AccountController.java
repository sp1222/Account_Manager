package controller;
import java.math.BigDecimal;
import model.Account;
import model.OverdrawException;
import view.AccountView;
import view.JFrameView;

// this is built to work with only one window, not enough time to look at multiple windows.
// working with multiple windows is what i look at next.


public class AccountController extends AbstractController 
{	private AccountListController controller = null;
	public AccountController(Account account, AccountListController con)
	{
		controller = con;
		setModel(account);
		setView(new AccountView((Account)getModel(), this));
		((JFrameView)getView()).setVisible(true);
		((AccountView)getView()).displayCurrentAccountInformation(((Account)getModel()).getID(), ((Account)getModel()).getNameLast(), ((Account)getModel()).getNameFirst(), ((Account)getModel()).getBalance());
	}

	// IT WOULD BE EASY TO WRITE A TEST CASE HERE
	public void operation(String option)
	{
		// this method of comparison does address comparison
		if(option.equals(AccountView.Deposit))	// option.equals(Deposit) method will compare Strings symbol by symbol
		{				
			BigDecimal amount = ((AccountView)getView()).getTransactionAmount();
			((Account)getModel()).deposit(amount);
		}
		
		else if(option.equals(AccountView.Withdrawal))
		{
			BigDecimal amount = ((AccountView)getView()).getTransactionAmount();
			try
			{
				((Account)getModel()).withdraw(amount);
			}
			catch(OverdrawException ex)
			{
				final String msg = ex.getMessage();
				((AccountView)getView()).showError("Account Overdrawn Exception:\nAmount being removed from balance results in overdrawal of the account.");
			}
		}
		
		else if(option.equals(AccountView.Update))
		{
			// here we ask the AccountListController to update the AccountSelectionView details.
			// this only occurs at closing of the AccountView window.
			controller.operation(AccountView.Update);
		}
	}
}

