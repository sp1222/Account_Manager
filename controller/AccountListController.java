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

			new CreateAccountController(((AccountList)getModel()), this);
		}

		else if(option.equals(AccountSelectionView.EditAccount))
		{			
			/******************************************************************************************
			// here make a pop up window with fields to fill in to create a new account
			*******************************************************************************************/
//			First, we need to ensure that the current account selected is what will be displayed.
			
			if(((AccountList)getModel()).doesAccountExist(((AccountSelectionView)getView()).getID()) == true)
			{
				new AccountController(((AccountList)getModel()).getCurrentAccount(), this);
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
			int index = -1;
			String id = ((AccountSelectionView)getView()).getID();
			if(((AccountList)getModel()).doesAccountExist(id))
			{
				index = ((AccountList)getModel()).getAccountIndex(id);
				((AccountList)getModel()).removeAccount(id);
				// index value of -1 will indicate that there does not exist an account with the id that was entered
				// this includes blank field and fields with no matching id number.
				// the exception will yield an index out of array error, thus indicating that the id entered (or blank field) does not exist in the accountList.
				try {
					((AccountSelectionView)getView()).removeFromAccountListComboBox(index);		
					((AccountList)getModel()).setCurrentAccount(0);			
					((AccountSelectionView)getView()).displayCurrentAccountInformation(((AccountList)getModel()).getCurrentAccountID(), ((AccountList)getModel()).getCurrentAccountNameLast(), ((AccountList)getModel()).getCurrentAccountNameFirst(), ((AccountList)getModel()).getCurrentAccountBalance());
					((AccountSelectionView)getView()).setAccountSelected(((AccountList)getModel()).getCurrentAccountIndex());

					try {
						((AccountList)getModel()).saveAccountList();
						
					} catch (IOException ex) {
						((AccountSelectionView)getView()).showError("Save Account List Error!\nUnable to save list of accounts to text file after removing account!");
					}
				} catch(ArrayIndexOutOfBoundsException ex)
				{
					((AccountSelectionView)getView()).showError("Account Selection Error:\nCannot remove an account that does not exist!");
				}	
			}
			else
			{
				((AccountSelectionView)getView()).showError("Account Selection Error:\nCannot edit an account that does not exist!");
			}

			// if the accountList is empty after removing the last account
			// we need to add a dummy account into place for currentAccount
			// and make sure the dropdown list is empty().
			
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
		
		else if(option.equals(CreateAccountController.AddToDropdownList))
		{			
			((AccountSelectionView)getView()).addToAccountListComboBox(((AccountList)getModel()).getCurrentAccountDisplayName() ,((AccountList)getModel()).getCurrentAccountIndex());	
			// we make the currently selected account in view our currentAccount in AccountList.
			// then we update the fields with the currentAccount information in view
			((AccountSelectionView)getView()).displayCurrentAccountInformation(((AccountList)getModel()).getCurrentAccountID(), ((AccountList)getModel()).getCurrentAccountNameLast(), ((AccountList)getModel()).getCurrentAccountNameFirst(), ((AccountList)getModel()).getCurrentAccountBalance());
			((AccountSelectionView)getView()).setAccountSelected(((AccountList)getModel()).getCurrentAccountIndex());
		}
	}
}
