package gui;



import java.util.ResourceBundle;


import javax.swing.JFrame;
import javax.swing.JPanel;

import businessLogic.BLFacade;
import exceptions.IncorrectData;
import exceptions.NegativeDeposit;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.Color;
import java.awt.Font;


public class DepositGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel jContentPane = null;
	private JTextField insert;
	private JFrame nirePantaila;
	private String username;
	private JLabel lblMoney;
	private JLabel lblError;
	


	public void setUsername(String username) {
		this.username = username;
	}



	public DepositGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				String currentMoney = facade.getCurrentMoney(username);
				lblMoney.setText(currentMoney);
			}
		});
		
		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nirePantaila=this;
	}



	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(495, 228);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Deposit"));
		
	}
	
	/**
	 * This method initializes jContentPane
	 * 
	 * @return javax.swing.JPanel
	 */
	private JPanel getJContentPane() {
		if (jContentPane == null) {
			jContentPane = new JPanel();
			jContentPane.setLayout(null);
			
			
			
			JButton btnInsertMoneyBottom = new JButton(ResourceBundle.getBundle("Etiquetas").getString("InsertMoney")); //$NON-NLS-1$ //$NON-NLS-2$
			btnInsertMoneyBottom.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					BLFacade facade = MainGUI.getBusinessLogic();
					String money = insert.getText();
					try {
						float moneyNum = Float.parseFloat(money);
						if (moneyNum<=0.0) {
							lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("NegativeDeposit"));
						} else {
							
							facade.deposit(username, moneyNum);
							facade.addMovement(Float.parseFloat(money), "Deposit: +"+money, username);
						}
						
					}catch (IncorrectData e1) {

						lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("IncorrectDataNull"));
						System.out.println(e1.getMessage());
						System.out.println(e1.getStackTrace());
					} catch (NumberFormatException e2) {

						lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("IncorrectDataFormat"));
						System.out.println(e2.getMessage());
						System.out.println(e2.getStackTrace());
					}


					String currentMoney = facade.getCurrentMoney(username);
					lblMoney.setText(currentMoney);

					insert.setText("");
					
				}
			});
			
			btnInsertMoneyBottom.setBounds(174, 134, 116, 31);
			jContentPane.add(btnInsertMoneyBottom);
			
			JLabel lblInsertMoney = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("InsertMoney")); //$NON-NLS-1$ //$NON-NLS-2$
			lblInsertMoney.setBounds(100, 81, 101, 31);
			jContentPane.add(lblInsertMoney);
			
			insert = new JTextField();
			insert.setColumns(10);
			insert.setBounds(229, 81, 96, 31);
			jContentPane.add(insert);
			
			JLabel lblCurrentMoney = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("CurrentMoney")); //$NON-NLS-1$ //$NON-NLS-2$
			lblCurrentMoney.setBounds(100, 39, 101, 31);
			jContentPane.add(lblCurrentMoney);
			
			lblMoney = new JLabel();
			lblMoney.setBounds(229, 39, 96, 31);
			jContentPane.add(lblMoney);
			
			lblError = new JLabel("");
			lblError.setFont(new Font("Tahoma", Font.BOLD, 11));
			lblError.setForeground(new Color(255, 0, 0));
			lblError.setBounds(335, 89, 124, 14);
			jContentPane.add(lblError);
			
			
		
			
			
			
			
		}
		return jContentPane;
	}
	
	public void showMoney() {
		
	}
}// @jve:decl-index=0:visual-constraint="0,0"
