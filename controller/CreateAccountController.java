package controller;
import java.io.IOException;
import java.math.BigDecimal;
import model.Account;
import model.AccountList;
import model.DuplicateIDException;
import model.OverdrawException;
import view.AccountSelectionView;
import view.AccountView;
import view.CreateAccountView;
import view.JFrameView;

public class CreateAccountController extends AbstractController{	
	public final static String AddToDropdownList = "Add To Dropdown List";
	private AccountListController controller = null;
	public CreateAccountController(AccountList model, AccountListController con)
	{
		controller = con;
		setModel(model);
		setView(new CreateAccountView(model, this));
		((JFrameView)getView()).setVisible(true);

	}
	
	// IT WOULD BE EASY TO WRITE A TEST CASE HERE
	public void operation(String option)
	{
		
		if(option.equals(CreateAccountView.FinalizeNewAccount))
		{			
			/******************************************************************************************
			 * AccountListController must also be notified to update the drop down 
			 * list after account creation is complete
			*******************************************************************************************/
			
			String newID = ((CreateAccountView)getView()).getID();			
			String newLast = ((CreateAccountView)getView()).getNameLast();
			String newFirst = ((CreateAccountView)getView()).getNameFirst();
			int index = -1;
			
			try {
				((AccountList)getModel()).addAccount(newID, newLast, newFirst, BigDecimal.ZERO);
				((CreateAccountView)getView()).dispose();
				controller.operation(AddToDropdownList);
				// save accountList to file			
				try {
					((AccountList)getModel()).saveAccountList();
					
				} catch (IOException ex) {
					((CreateAccountView)getView()).showError("Save Account List Error!\nUnable to save list of accounts to text file after adding account!");
				}
			} catch (DuplicateIDException ex) {
				((CreateAccountView)getView()).showError("Duplicate Account Exception:\nA duplicate account was almost created!");
			} 		
		}
	}	
}
