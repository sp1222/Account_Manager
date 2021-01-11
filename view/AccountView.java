package view;
import javax.swing.*;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import java.awt.*; 
import java.awt.event.*;
import java.math.BigDecimal;
import controller.AccountController;
import model.Account;
import model.Model;
import model.ModelEvent;

public class AccountView extends JFrameView {

	public final static String Deposit = "Deposit";
	public final static String Withdrawal = "Withdrawal";
	public final static String Update = "Account Selection";
	private JPanel mainPanel;
	private JPanel textPanel;
	private JPanel buttonPanel;
	private JLabel idLabel;
	private JTextPane idPane;
	private JLabel nameLastLabel;
	private JTextPane nameLastPane;
	private JLabel nameFirstLabel;
	private JTextPane nameFirstPane;
	private JLabel balanceLabel;
	private JTextPane balancePane;
	private JLabel amountLabel;
	private JTextPane amountPane;
	private JButton depositButton;
	private JButton withdrawalButton;
	private String initialValue = "$ 0.00";
	private Handler handler = new Handler();
	
	public AccountView(Model model, AccountController controller)
	{
		super(model, controller);
		addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(java.awt.event.WindowEvent evt)
			{
				// update the AccountSelectionView dropdown menu through AccountListController.
				controller.operation(Update);
				dispose();
			}
		});
		
		setTitle("Edit Account");
		setContentPane(getMainPanel());
		setLocation(400, 300);
		pack();
		setVisible(true);
	}
	
	// set up the parameters for JPanel object being used
	private JPanel getMainPanel()
	{
		if(mainPanel == null)
		{
			mainPanel = new JPanel();
			GridLayout layout = new GridLayout(1, 2);
			mainPanel.setLayout(layout);
			GridBagConstraints textPanel = new GridBagConstraints();
			textPanel.gridx = 0;
			textPanel.gridy = 0;
			GridBagConstraints buttonsPanel = new GridBagConstraints();
			buttonsPanel.gridx = 0;
			buttonsPanel.gridy = 1;
			mainPanel.add(getTextPanel(), textPanel);
			mainPanel.add(getButtonPanel(), buttonsPanel);
		}
		return mainPanel;
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
			GridBagConstraints amountL = new GridBagConstraints();
			amountL.gridx = 0;
			amountL.gridy = 5;
			GridBagConstraints amountP = new GridBagConstraints();
			amountP.gridx = 1;
			amountP.gridy = 5;
			
			textPanel = new JPanel();
			textPanel.setLayout(new GridBagLayout());
			textPanel.add(getIDLabel(), idL);
			textPanel.add(getIDPane(),idP);	
			textPanel.add(getNameLastLabel(),nameLastL);
			textPanel.add(getNameLastPane(),nameLastP);
			textPanel.add(getNameFirstLabel(),nameFirstL);
			textPanel.add(getNameFirstPane(),nameFirstP);
			textPanel.add(getBalanceLabel(), balanceL);
			textPanel.add(getBalancePane(),balanceP);
			textPanel.add(getTransactionAmountLabel(),amountL);
			textPanel.add(getTransactionAmountPane(),amountP);
			
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
			idPane.setBackground(Color.lightGray);
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
			nameLastPane.setBackground(Color.lightGray);
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
			nameFirstPane.setBackground(Color.lightGray);
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
	
	// set up the parameters for JLabels being used
	private JLabel getBalanceLabel()
	{
		if(balanceLabel == null)
		{
			balanceLabel = new JLabel();
			balanceLabel.setText("Current Balance : ");
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
			balancePane.setBackground(Color.lightGray);
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

	// set up the parameters for JLabels being used
	private JLabel getTransactionAmountLabel()
	{
		if(amountLabel == null)
		{
			amountLabel = new JLabel();
			amountLabel.setText("Transaction Amount : ");
			amountLabel.setSize(200, 25);
		}
		return amountLabel;
	}

	// set up the parameters of JTextPane fields being used
	private JTextPane getTransactionAmountPane()
	{
		if(amountPane == null)
		{
			amountPane = new JTextPane();
			amountPane.setText(initialValue);
			amountPane.setPreferredSize(new Dimension(200, 20));
			amountPane.setEditable(true);
		}		
		return amountPane;
	}
	
	// set up the parameters for JPanel object being used
	private JPanel getButtonPanel()
	{
		if(buttonPanel == null)
		{
			// cell positions within a table
			GridBagConstraints depositButtonSet = new GridBagConstraints();
			depositButtonSet.gridx = 0;
			depositButtonSet.gridy = 0;
			
			GridBagConstraints withdrawalButtonSet = new GridBagConstraints();
			withdrawalButtonSet.gridx = 0;
			withdrawalButtonSet.gridy = 1;
			
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridBagLayout());
			buttonPanel.add(getDepositButton(), depositButtonSet);
			buttonPanel.add(getWithdrawalButton(), withdrawalButtonSet);
		}
		return buttonPanel;
	}
	
	// set up the parameters for JButton being used
	private JButton getDepositButton()
	{
		if(depositButton == null)
		{
			depositButton = new JButton(Deposit);
			depositButton.addActionListener(handler);
			depositButton.setPreferredSize(new Dimension(200, 20));
		}
		return depositButton;
	}

	// set up the parameters for JButton being used
	private JButton getWithdrawalButton()
	{
		if(withdrawalButton == null)
		{
			withdrawalButton = new JButton(Withdrawal);
			withdrawalButton.addActionListener(handler);
			withdrawalButton.setPreferredSize(new Dimension(200, 20));
		}
		return withdrawalButton;
	}
	
	// handles what happens when a button is pressed
	private class Handler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			((AccountController)getController()).operation(event.getActionCommand());
		}
	}
	
	// gets the bigDecimal value amount in the amountField
	// used to withdrawal and deposit from the active account.
	public BigDecimal getTransactionAmount()
	{
		BigDecimal amount = BigDecimal.ZERO;
		if(amount.compareTo(BigDecimal.ZERO) < 0)
		{
			amountPane.setText(initialValue);
			throw new NumberFormatException("Transaction Amount field only accepts decimal values greater than zero.");
		}
		try 
		{
			amount = new BigDecimal(amountPane.getText());
		}
		catch(NumberFormatException ex)
		{
			amountPane.setText(initialValue);
			showError("Transaction Amount field only accepts decimal values greater than zero.");
		}
		
		return amount;
	}
	
	public void displayCurrentAccountInformation(String id, String last, String first, BigDecimal balance)
	{
		idPane.setText(id);
		nameLastPane.setText(last);
		nameFirstPane.setText(first);
		balancePane.setText("$ " + balance.toString());
	}
	
	// presents a pop up window, this one specifically for errors.
	public void showError(String msg)
	{
		JOptionPane.showMessageDialog(this,  msg);
	}

	// this is very important!  for updating model
	public void modelChanged(ModelEvent me) 
	{
		balancePane.setText((me.getBalance()).toString());		
	}
	
	//		for testing purposes
/*	
	public static void main(String[] args)
	{
		final Account account = new Account("54321", "Billy Bob Thorton", new BigDecimal("22.00"));
		final AccountController controller = new AccountController();
		controller.setModel(account);
		SwingUtilities.invokeLater(new Runnable()	{	public void run()	
		{
			AccountView app = new AccountView(account, controller);
			controller.setView(app);
			app.setVisible(true);
		}	});
	}
	*/	
}
