package view;
import javax.swing.*; 
import java.awt.*; 
import java.awt.event.*;
import java.math.BigDecimal;
import controller.AccountController;
import model.Account;
import model.Model;
import model.ModelEvent;

public class AccountView extends JFrameView {

	private final static String Deposit = "Deposit";
	private final static String Withdrawal = "Withdrawal";
	private JPanel mainPanel;
	private JPanel textPanel;
	private JPanel buttonPanel;
	private JLabel balanceLabel;
	private JLabel amountLabel;
	private JTextPane balancePane;
	private JTextPane amountPane;
	private JButton depositButton;
	private JButton withdrawalButton;
	private String initialValue = "$ 0.00";
	private Handler handler = new Handler();
	
	public AccountView(Model model, AccountController controller)
	{
		super(model, controller);
//		this.getContentPane().add(getMainPanel());		
		addWindowListener(new java.awt.event.WindowAdapter()
		{
			public void windowClosing(java.awt.event.WindowEvent evt)
			{
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
			GridLayout layout = new GridLayout(2, 1);
			mainPanel.setLayout(layout);
			GridBagConstraints constraints = new GridBagConstraints();
			constraints.gridx = 0;
			constraints.gridy = 1;
			mainPanel.add(getTextPanel(), null);
			mainPanel.add(getButtonPanel(), null);
		}
		return mainPanel;
	}

	// set up the parameters for JPanel object being used
	private JPanel getTextPanel()
	{
		if(textPanel == null)
		{
			GridBagConstraints balanceLabel = new GridBagConstraints();
			balanceLabel.gridx = 0;
			balanceLabel.gridy = 0;
			GridBagConstraints balancePane = new GridBagConstraints();
			balancePane.gridx = 1;
			balancePane.gridy = 0;
			GridBagConstraints amountLabel = new GridBagConstraints();
			amountLabel.gridx = 0;
			amountLabel.gridy = 1;
			GridBagConstraints amountPane = new GridBagConstraints();
			amountPane.gridx = 1;
			amountPane.gridy = 1;
			
			textPanel = new JPanel();
			textPanel.setLayout(new GridBagLayout());
			textPanel.add(getBalanceLabel(), balanceLabel);
			textPanel.add(getBalancePane(),balancePane);
			textPanel.add(getTransactionAmountLabel(),amountLabel);
			textPanel.add(getTransactionAmountPane(),amountPane);
			
		}
		return textPanel;
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
	private JTextPane getBalancePane()
	{
		if(balancePane == null)
		{
			balancePane = new JTextPane();
			balancePane.setText(initialValue);
			balancePane.setSize(200, 25);
			balancePane.setEditable(false);
		}		
		return balancePane;
	}

	// set up the parameters of JTextPane fields being used
	private JTextPane getTransactionAmountPane()
	{
		if(amountPane == null)
		{
			amountPane = new JTextPane();
			amountPane.setText(initialValue);
			amountPane.setSize(200, 25);
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
			depositButtonSet.gridx = 1;
			depositButtonSet.gridy = 0;
			
			GridBagConstraints withdrawalButtonSet = new GridBagConstraints();
			withdrawalButtonSet.gridx = 2;
			withdrawalButtonSet.gridy = 0;
			
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
