package view;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextPane;
import javax.swing.SwingUtilities;
import javax.swing.text.BadLocationException;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import controller.AccountListController;
import controller.CreateAccountController;
import model.AccountList;
import model.Model;
import model.ModelEvent;

public class CreateAccountView extends JFrameView {

	public final static String FinalizeNewAccount = "Finalize New Account";
	
	private JPanel createAccountPanel;
	private JPanel textPanel;
	private JLabel idLabel;
	private JTextPane idPane;
	private JLabel nameLastLabel;
	private JTextPane nameLastPane;
	private JLabel nameFirstLabel;
	private JTextPane nameFirstPane;
	private JPanel buttonPanel;
	private JButton finalizeAccountButton;
//	private JButton terminateProcessButton;
	private ButtonHandler buttonHandler = new ButtonHandler();
	
	public CreateAccountView(Model model, CreateAccountController controller)
	{
		super(model, controller);
		
		addWindowListener(new java.awt.event.WindowAdapter() 
		{
		    public void windowClosing(java.awt.event.WindowEvent evt)
		    {
				// update the AccountSelectionView dropdown menu through AccountListController.
		        dispose();
		    }
		});
		
		setContentPane(getMainPanel());
		setLocation(400, 300);
		pack();
		setVisible(true);		
	}

	// set up the parameters for JPanel object being used
	private JPanel getMainPanel()
	{
		if(createAccountPanel == null)
		{
			createAccountPanel = new JPanel();
			GridLayout layout = new GridLayout(2, 1);
			createAccountPanel.setLayout(layout);
			GridBagConstraints labels = new GridBagConstraints();
			labels.gridx = 0;
			labels.gridy = 0;
			GridBagConstraints buttons = new GridBagConstraints();
			buttons.gridx = 0;
			buttons.gridy = 1;
			GridBagConstraints dropdown = new GridBagConstraints();
			dropdown.gridx = 1;
			labels.gridy = 0;
			createAccountPanel.add(getTextPanel(), labels);
			createAccountPanel.add(getButtonPanel(), buttons);
			createAccountPanel.setOpaque(true);
		}
		return createAccountPanel;
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
			
			textPanel = new JPanel();
			textPanel.setLayout(new GridBagLayout());
			textPanel.add(getIDLabel(), idL);
			textPanel.add(getIDPane(),idP);	
			textPanel.add(getNameLastLabel(),nameLastL);
			textPanel.add(getNameLastPane(),nameLastP);
			textPanel.add(getNameFirstLabel(),nameFirstL);
			textPanel.add(getNameFirstPane(),nameFirstP);
			
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
			idPane.setEditable(true);
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
			nameLastPane.setEditable(true);
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
			nameFirstPane.setEditable(true);
		}		
		return nameFirstPane;
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
			GridBagConstraints removeAcctButtonSet = new GridBagConstraints();
			removeAcctButtonSet.gridx = 0;
			removeAcctButtonSet.gridy = 1;
			
			
			buttonPanel = new JPanel();
			buttonPanel.setLayout(new GridBagLayout());
			buttonPanel.add(getAddAccountButton(), addAcctButtonSet);
//			buttonPanel.add(getRemoveAccountButton(), removeAcctButtonSet);
		}
		return buttonPanel;
	}
	
	// set up the parameters for JButton being used
	private JButton getAddAccountButton()
	{
		if(finalizeAccountButton == null)
		{
			finalizeAccountButton = new JButton(FinalizeNewAccount);
			finalizeAccountButton.addActionListener(buttonHandler);
		}
		return finalizeAccountButton;
	}

	/*
	// set up the parameters for JButton being used
	private JButton getRemoveAccountButton()
	{
		if(terminateProcessButton == null)
		{
			terminateProcessButton = new JButton(TerminateProcess);
			terminateProcessButton.addActionListener(buttonHandler);
		}
		return terminateProcessButton;
	}
	*/	
	
	//******************************************************************************************
	
	// handles what happens when a button is pressed
	private class ButtonHandler implements ActionListener
	{
		public void actionPerformed(ActionEvent event)
		{
			((CreateAccountController)getController()).operation(event.getActionCommand());
		}
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
	
	// presents a pop up window, this one specifically for errors.
	public void showError(String msg)
	{
		JOptionPane.showMessageDialog(this,  msg);
	}

	@Override
	public void modelChanged(ModelEvent me) {
		// TODO Auto-generated method stub
		
	}
}
