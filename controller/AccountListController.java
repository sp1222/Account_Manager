package controller;

import java.io.IOException;
import java.math.BigDecimal;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

import model.Account;
import model.AccountList;
import model.DuplicateIDException;
import view.AccountSelectionView;
import view.AccountView;
import view.CreateAccountView;

public class AccountListController extends AbstractController {
	private boolean isRemoving = false;	// temporary override for account selection when removing an account.

	public AccountListController(AccountList model)
	{
		setModel(model);
	}	
	
	public void operation (String option)
	{
		// if else statements for each option we can run from Account Selection View
		// ie, buttons we will be able to click.

		
		if(option.equals(AccountSelectionView.CreateAccount))
		{			
			/******************************************************************************************
			// here make a pop up window with fields to fill in to create a new account
			*******************************************************************************************/
//			public CreateAccountView(AccountList model, AccountListController controller)
			
//			final AccountListController controller = new AccountListController(AccountList);
//			final CreateAccountView create = new CreateAccountView((AccountListController)getModel(), controller);
			
			final AccountList model = (AccountList)getModel();	// new AccountList(accountFile);	
			final AccountListController controller = new AccountListController(model);
			SwingUtilities.invokeLater(new Runnable()	{	public void run()	
			{
				JFrame.setDefaultLookAndFeelDecorated(true);
				JDialog.setDefaultLookAndFeelDecorated(true);
				CreateAccountView app = new CreateAccountView((AccountList)controller.getModel(), controller);
				controller.setView(app);
			}	});
		}

		else if(option.equals(AccountSelectionView.EditAccount))
		{			
			/******************************************************************************************
			// here make a pop up window with fields to fill in to create a new account
			*******************************************************************************************/
//			First, we need to ensure that the current account selected is what will be displayed.
			
			if(((AccountList)getModel()).doesAccountExist(((AccountSelectionView)getView()).getID()) == true)
			{
				final Account model = ((AccountList)getModel()).getCurrentAccount();
				final AccountController controller = new AccountController(model);
				SwingUtilities.invokeLater(new Runnable()	{	public void run()	
				{
					JFrame.setDefaultLookAndFeelDecorated(true);
					JDialog.setDefaultLookAndFeelDecorated(true);
					AccountView app = new AccountView((Account)controller.getModel(), controller);
					controller.setView(app);
				}	});
			}
			else
			{
				((AccountSelectionView)getView()).showError("Account Selection Error:\nCannot edit an account that does not exist!");
			}
		}
		
		else if(option.equals(AccountSelectionView.RemoveAccount))
		{
			/******************************************************************************************
			// here add a pop up window asking if we are sure we want to delete selected account
			*******************************************************************************************/
			isRemoving = true;
			int index = ((AccountList)getModel()).removeAccount(((AccountSelectionView)getView()).getID());
			// index value of -1 will indicate that there does not exist an account with the id that was entered
			// this includes blank field and fields with no matching id number.
			// the exception will yield an index out of array error, thus indicating that the id entered (or blank field) does not exist in the accountList.
			try {
				((AccountSelectionView)getView()).removeFromAccountListComboBox(index);
			} catch(ArrayIndexOutOfBoundsException ex)
			{
				((AccountSelectionView)getView()).showError("Account Selection Error:\nCannot remove an account that does not exist!");
			}				
			try {
				((AccountList)getModel()).saveAccountList();
				
			} catch (IOException ex) {
				((AccountSelectionView)getView()).showError("Save Account List Error!\nUnable to save list of accounts to text file after removing account!");
			}
			((AccountSelectionView)getView()).displayCurrentAccountInformation("", "", "", BigDecimal.ZERO);
			((AccountList)getModel()).setCurrentAccount(0);
			isRemoving = false;
		}
		
		else if(option.equals(AccountSelectionView.AccountSelection) && isRemoving == false)
		{
			int index = ((AccountSelectionView)getView()).getCurrentlySelectedAccountIndex();
			// we make the currently selected account in view our currentAccount in AccountList.
			((AccountList)getModel()).setCurrentAccount(index);
			// then we update the fields with the currentAccount information in view
			((AccountSelectionView)getView()).displayCurrentAccountInformation(((AccountList)getModel()).getCurrentAccountID(), ((AccountList)getModel()).getCurrentAccountNameLast(), ((AccountList)getModel()).getCurrentAccountNameFirst(), ((AccountList)getModel()).getCurrentAccountBalance());

		}
		
		else if(option.equals(CreateAccountView.FinalizeNewAccount))
		{			
			/******************************************************************************************
			// make a pop up window with fields to fill in to create a new account
			 * AccountListController must also be notified to update the drop down 
			 * list after account creation is complete
			*******************************************************************************************/
			
			String newID = ((CreateAccountView)getView()).getID();			
			String newLast = ((CreateAccountView)getView()).getNameLast();
			String newFirst = ((CreateAccountView)getView()).getNameFirst();
			try {
				((AccountList)getModel()).addAccount(newID, newLast, newFirst, BigDecimal.ZERO);
			} catch (DuplicateIDException ex) {
				((CreateAccountView)getView()).showError("Duplicate Account Exception: A duplicate account was almost created!");
			} 
			// save accountList to file			
			try {
				((AccountList)getModel()).saveAccountList();
				
			} catch (IOException ex) {
				((CreateAccountView)getView()).showError("Save Account List Error!\nUnable to save list of accounts to text file after adding account!");
			}
		}
		
		else if(option.equals(CreateAccountView.TerminateProcess))
		{
			/******************************************************************************************
			// end create account process without making any changes.
			*******************************************************************************************/

		}
	}
}
