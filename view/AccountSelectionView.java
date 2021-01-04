package view;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.*; 
import java.awt.event.*;
import java.io.*;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Iterator;

//import controller.AccountController;
import controller.AccountListController;
import model.AccountList;
import model.DuplicateIDException;
import model.Model;
import model.ModelEvent;

public class AccountSelectionView extends JFrameView{
	
	// change all of this to enumeration types?
	public final static String CreateAccount = "Create Account";
	public final static String EditAccount = "Edit Account";
	public final static String RemoveAccount = "Remove Account";
	public final static String AccountSelection = "Account Selection";
	public final static String UpdateAccountList = "Update Account List";
	
	// main panel to manage all existing accounts.
	private JPanel accountGroupPanel;
	private JPanel textPanel;
	private JLabel idLabel;
	private JTextPane idPane;
	private JLabel nameLastLabel;
	private JTextPane nameLastPane;
	private JLabel nameFirstLabel;
	private JTextPane nameFirstPane;
	private JLabel balanceLabel;
	private JTextPane balancePane;
	private JPanel buttonPanel;
	private JButton addAccountButton;
	private JButton editAccountButton;
	private JButton removeAccountButton;
	private JPanel dropdownPanel;
	private String initialValue = "$ 0.00";
	private JComboBox<String> accountListComboBox;
	private ButtonHandler buttonHandler = new ButtonHandler();
	private AccountListHandler accountListHandler = new AccountListHandler();
	
	public AccountSelectionView(AccountList model, AccountListController controller)
	{
		super(model, controller);
		
		addWindowListener(new java.awt.event.WindowAdapter() 
		{
		    public void windowClosing(java.awt.event.WindowEvent evt)
		    {
		        dispose();
		        System.exit(0);
		    }
		});
		
		setTitle("Account Group Overview");
		
		setContentPane(getMainPanel());
		setLocation(400, 300);
		pack();
		setVisible(true);		
	}
	
	// set up the parameters for JPanel object being used
	private JPanel getMainPanel()
	{
		if(accountGroupPanel == null)
		{
			accountGroupPanel = new JPanel();
			GridLayout layout = new GridLayout(2, 1);
			accountGroupPanel.setLayout(layout);
			GridBagConstraints labels = new GridBagConstraints();
			labels.gridx = 0;
			labels.gridy = 0;
			GridBagConstraints buttons = new GridBagConstraints();
			buttons.gridx = 0;
			buttons.gridy = 1;
			GridBagConstraints dropdown = new GridBagConstraints();
			dropdown.gridx = 1;
			labels.gridy = 0;
			accountGroupPanel.add(getTextPanel(), labels);
			accountGroupPanel.add(getButtonPanel(), buttons);
			accountGroupPanel.add(getDropdownPanel(), dropdown);
			accountGroupPanel.setOpaque(true);
		}
		return accountGroupPanel;
	}

	// set up the parameters for JPanel object being used
	private JPanel getTextPanel()
	{
		if(textPanel == null)
		{
			GridBagConstraints idL = new GridBagConstraints();
			idL.gridx = 0;
			idL.gridy = 1;
			GridBagConstraints idP = new GridBagConstraints();
			idP.gridx = 1;
			idP.gridy = 1;
			GridBagConstraints nameLastL = new GridBagConstraints();
			nameLastL.gridx = 0;
			nameLastL.gridy = 2;
			GridBagConstraints nameLastP = new GridBagConstraints();
			nameLastP.gridx = 1;
			nameLastP.gridy = 2;
			GridBagConstraints nameFirstL = new GridBagConstraints();
			nameFirstL.gridx = 0;
			nameFirstL.gridy = 3;
			GridBagConstraints nameFirstP = new GridBagConstraints();
			nameFirstP.gridx = 1;
			nameFirstP.gridy = 3;
			GridBagConstraints balanceL = new GridBagConstraints();
			balanceL.gridx = 0;
			balanceL.gridy = 4;
			GridBagConstraints balanceP = new GridBagConstraints();
			balanceP.gridx = 1;
			balanceP.gridy = 4;
			
			textPanel = new JPanel();
			textPanel.setLayout(new GridBagLayout());
			textPanel.add(getIDLabel(), idL);
			textPanel.add(getIDPane(),idP);	
			textPanel.add(getNameLastLabel(),nameLastL);
			textPanel.add(getNameLastPane(),nameLastP);
			textPanel.add(getNameFirstLabel(),nameFirstL);
			textPanel.add(getNameFirstPane(),nameFirstP);			
			textPanel.add(getBalanceLabel(),balanceL);
			textPanel.add(getBalancePane(),balanceP);
			
		}
		return textPanel;
	}
	
	// set up the parameters for JLabels being used
	private JLabel getIDLabel()
	{
		if(idLabel == null)
		{
			idLabel = new JLabel();
			idLabel.setText("Account ID");
			idLabel.setSize(200, 25);
		}
		return idLabel;
	}
	
	// set up the parameters for JLabels being used
	private JTextPane getIDPane()
	{
		if(idPane == null)
		{
			idPane = new JTextPane();
			StyledDocument doc = idPane.getStyledDocument();
			SimpleAttributeSet right = new SimpleAttributeSet();
			StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);    
			try {
				doc.insertString(doc.getLength(), "", right );
			    doc.setParagraphAttributes(doc.getLength(), 1, right, false);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			idPane.setText("");
			idPane.setPreferredSize(new Dimension(200, 20));
			idPane.setEditable(false);
		}
		return idPane;
	}

	// set up the parameters for JLabels being used
	private JLabel getNameLastLabel()
	{
		if(nameLastLabel == null)
		{
			nameLastLabel = new JLabel();
			nameLastLabel.setText("Last Name");
			nameLastLabel.setSize(200, 25);
		}
		return nameLastLabel;
	}

	// set up the parameters of JTextPane fields being used
	private JTextPane getNameLastPane()
	{
		if(nameLastPane == null)
		{
			nameLastPane = new JTextPane();
			StyledDocument doc = nameLastPane.getStyledDocument();
			SimpleAttributeSet right = new SimpleAttributeSet();
			StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);    
			try {
				doc.insertString(doc.getLength(), "", right );
			    doc.setParagraphAttributes(doc.getLength(), 1, right, false);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			nameLastPane.setText("");
			nameLastPane.setPreferredSize(new Dimension(200, 20));
			nameLastPane.setEditable(false);
		}		
		return nameLastPane;
	}

	// set up the parameters for JLabels being used
	private JLabel getNameFirstLabel()
	{
		if(nameFirstLabel == null)
		{
			nameFirstLabel = new JLabel();
			nameFirstLabel.setText("First Name");
			nameFirstLabel.setSize(200, 25);
		}
		return nameFirstLabel;
	}

	// set up the parameters of JTextPane fields being used
	private JTextPane getNameFirstPane()
	{
		if(nameFirstPane == null)
		{
			nameFirstPane = new JTextPane();
			StyledDocument doc = nameFirstPane.getStyledDocument();
			SimpleAttributeSet right = new SimpleAttributeSet();
			StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);    
			try {
				doc.insertString(doc.getLength(), "", right );
			    doc.setParagraphAttributes(doc.getLength(), 1, right, false);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			nameFirstPane.setText("");
			nameFirstPane.setPreferredSize(new Dimension(200, 20));
			nameFirstPane.setEditable(false);
		}		
		return nameFirstPane;
	}

	// set up the parameters of JTextPane fields being used
	private JLabel getBalanceLabel()
	{
		if(balanceLabel == null)
		{
			balanceLabel = new JLabel();
			balanceLabel.setText("Current Balance");
			balanceLabel.setSize(200, 25);
		}		
		return balanceLabel;
	}

	// set up the parameters of JTextPane fields being used
	private JTextPane getBalancePane()
	{
		if(balancePane == null)
		{
			balancePane = new JTextPane();
			StyledDocument doc = balancePane.getStyledDocument();
			SimpleAttributeSet right = new SimpleAttributeSet();
			StyleConstants.setAlignment(right, StyleConstants.ALIGN_RIGHT);    
			try {
				doc.insertString(doc.getLength(), "", right );
			    doc.setParagraphAttributes(doc.getLength(), 1, right, false);
			} catch (BadLocationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			balancePane.setText(initialValue);
			balancePane.setPreferredSize(new Dimension(200, 20));
			balancePane.setEditable(false);
		}		
		return balancePane;
	}
	

	// set up the parameters for JPanel object being used
	private JPanel getButtonPanel()
	{
		if(buttonPanel == null)
		{
			// cell positions within a table
			GridBagConstraints addAcctButtonSet = new GridBagConstraints();
			addAcctButtonSet.gridx = 0;
			addAcctButtonSet.gridy = 0;

			// cell positions within a table
			GridBagConstraints editAcctButtonSet = new GridBagConstraints();
			editAcctButtonSet.gridx = 0;
			editAcctButtonSet.gridy = 1;
			// cell positions within a table
			
			GridBagConstraints removeAcctButtonSet = new GridBagConstraints();
			removeAcctButtonSet.gridx = 0;
			removeAcctButtonSet.gridy = 2;
			
			
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridBagLayout());
			buttonPanel.add(getAddAccountButton(), addAcctButtonSet);
			buttonPanel.add(getEditAccountButton(), editAcctButtonSet);
			buttonPanel.add(getRemoveAccountButton(), removeAcctButtonSet);
		}
		return buttonPanel;
	}
	
	// set up the parameters for JButton being used
	private JButton getAddAccountButton()
	{
		if(addAccountButton == null)
		{
			addAccountButton = new JButton(CreateAccount);
			addAccountButton.addActionListener(buttonHandler);
			addAccountButton.setPreferredSize(new Dimension(200, 20));
		}
		return addAccountButton;
	}
	
	// set up the parameters for JButton being used
	private JButton getEditAccountButton()
	{
		if(editAccountButton == null)
		{
			editAccountButton = new JButton(EditAccount);
			editAccountButton.addActionListener(buttonHandler);
			editAccountButton.setPreferredSize(new Dimension(200, 20));
		}
		return editAccountButton;
	}

	// set up the parameters for JButton being used
	private JButton getRemoveAccountButton()
	{
		if(removeAccountButton == null)
		{
			removeAccountButton = new JButton(RemoveAccount);
			removeAccountButton.addActionListener(buttonHandler);
			removeAccountButton.setPreferredSize(new Dimension(200, 20));
		}
		return removeAccountButton;
	}
	
	// set up the parameters for JPanel object being used
	private JPanel getDropdownPanel()
	{
		if(dropdownPanel == null)
		{
			
			dropdownPanel = new JPanel();
			dropdownPanel.setLayout(new GridBagLayout());
			
			/******************************************************************************************
			// here i want to also add a search box that displays account information for selection.
			*******************************************************************************************/
			
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			dropdownPanel.add(getAccountListDropDown(), constraints);
		}
		return dropdownPanel;
	}
	
	private JComboBox<String> getAccountListDropDown()
	{
		if(accountListComboBox == null)
		{
			accountListComboBox = new JComboBox<String>(((AccountList)getModel()).getListNames());
			accountListComboBox.addActionListener(accountListHandler);
			accountListComboBox.setPreferredSize(new Dimension(350, 25));
		}
		return accountListComboBox;
	}
	
	public void addToAccountListComboBox(String name, int index)
	{
		accountListComboBox.insertItemAt(name, index);
	}
	
	public void removeFromAccountListComboBox(int index)
	{
		accountListComboBox.removeItemAt(index);
	}
	
	public int getCurrentlySelectedAccountIndex()
	{
		return accountListComboBox.getSelectedIndex();
	}
	
	public void displayCurrentAccountInformation(String id, String last, String first, BigDecimal balance)
	{
		idPane.setText(id);
		nameLastPane.setText(last);
		nameFirstPane.setText(first);
		balancePane.setText("$ " + balance.toString());
	}

	//**************************************************************************************
	// public functions to return the amounts in the fields for creating a new account

	public String getID()
	{	
		return idPane.getText();
	}
	
	public String getNameLast()
	{	
		return nameLastPane.getText();
	}
	
	public String getNameFirst()
	{	
		return nameFirstPane.getText();
	}
	
	public BigDecimal getNBalance()
	{
		BigDecimal nBalance = BigDecimal.ZERO;
		if(nBalance.compareTo(BigDecimal.ZERO) < 0)
		{
			balancePane.setText(initialValue);
			throw new NumberFormatException("Amount field only accepts decimal values greater than zero.");
		}
		try 
		{
			nBalance = new BigDecimal(balancePane.getText());
		}
		catch(NumberFormatException ex)
		{
			balancePane.setText(initialValue);
			showError("Amount field only accepts decimal values greater than zero.");
		}
		return nBalance;
	}
	
	
	//******************************************************************************************
	
	// handles what happens when a button is pressed
	private class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			((AccountListController)getController()).operation(event.getActionCommand());
		}
	}
	
	// handling account selection from accountListComboBox.
	private class AccountListHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			((AccountListController)getController()).operation(AccountSelection);
		}
	}
	
	// presents a pop up window, this one specifically for errors.
	public void showError(String msg)
	{
		JOptionPane.showMessageDialog(this,  msg);
	}
	
	public static void main(String[] args)
	{
		String accountFile = "ListOfAccounts.txt";
		try
		{
		final AccountList model = new AccountList(accountFile);
		final AccountListController controller = new AccountListController(model);
		SwingUtilities.invokeLater(new Runnable()	{	public void run()	
		{
			JFrame.setDefaultLookAndFeelDecorated(true);
			JDialog.setDefaultLookAndFeelDecorated(true);
			AccountSelectionView app = new AccountSelectionView((AccountList)controller.getModel(), controller);
			controller.setView(app);
		}	});
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}

	@Override
	public void modelChanged(ModelEvent me) {
		// TODO Auto-generated method stub
		
	}
}
