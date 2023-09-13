package gui;

/**
 * @author Software Engineering teachers */


import javax.swing.*;


import java.awt.Color;
import java.awt.Font;
import java.util.ResourceBundle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class RegisteredGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private JPanel jContentPane = null;
	private JButton jButtonQueryQueries = null;
	
	protected JLabel jLabelSelectOption = null;
	private JButton btnSeeMovements = null;
	private JButton btnDeposit = null;
	private JButton btnBet = null;
	private JButton btnSendMessage = null;

	private JLabel lblUsernameLabel;
	private JButton btnFollow;
	
	/**
	 * This is the default constructor
	 */
	public RegisteredGUI() {
		super();
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				lblUsernameLabel.setText(username);
			}
		});
		
		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	

	public void setUsername(String username) {
		this.username = username;
	}



	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(495, 391);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Registered"));
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
			jContentPane.add(getLblNewLabel());
			jContentPane.add(getBtnQueryQueries());
			jContentPane.add(getBtnSeeMovements());
			jContentPane.add(getBtnDeposit());
			jContentPane.add(getBtnBet());
			jContentPane.add(getBtnSendMessage());
			
			lblUsernameLabel = new JLabel();
			lblUsernameLabel.setBounds(10, 15, 135, 14);
			jContentPane.add(lblUsernameLabel);
			jContentPane.add(getBtnFollow());
			
		}
		return jContentPane;
	}
	
	/**
	 * This method initializes boton2
	 * 
	 * @return javax.swing.JButton
	 */
	private JButton getBtnQueryQueries() {
		if (jButtonQueryQueries == null) {
			jButtonQueryQueries = new JButton();
			jButtonQueryQueries.setBounds(0, 199, 479, 43);
			jButtonQueryQueries.setText(ResourceBundle.getBundle("Etiquetas").getString("QueryQueries"));
			jButtonQueryQueries.addActionListener(new java.awt.event.ActionListener() {
				public void actionPerformed(java.awt.event.ActionEvent e) {
					JFrame a = new FindQuestionsGUI();

					a.setVisible(true);
				}
			});
		}
		return jButtonQueryQueries;
	}
	

	private JLabel getLblNewLabel() {
		if (jLabelSelectOption == null) {
			jLabelSelectOption = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SelectOption"));
			jLabelSelectOption.setBounds(156, 0, 162, 43);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	
	private JButton getBtnSeeMovements() {
		if (btnSeeMovements == null) {
			btnSeeMovements = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
			btnSeeMovements.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					SeeMovementsGUI a = new SeeMovementsGUI();
					a.setUsername(username);
					a.setVisible(true);
				}
			});
			btnSeeMovements.setText(ResourceBundle.getBundle("Etiquetas").getString("SeeMovements")); //$NON-NLS-1$ //$NON-NLS-2$
			btnSeeMovements.setBounds(0, 41, 479, 42);
		}
		return btnSeeMovements;
	}
	
	private JButton getBtnDeposit() {
		if (btnDeposit == null) {
			btnDeposit = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
			btnDeposit.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DepositGUI a = new DepositGUI();
					a.setUsername(username);
					a.setVisible(true);
				}
			});
			btnDeposit.setText(ResourceBundle.getBundle("Etiquetas").getString("Deposit")); //$NON-NLS-1$ //$NON-NLS-2$
			btnDeposit.setBounds(0, 94, 479, 41);
		}
		return btnDeposit;
	}
	
	private JButton getBtnBet() {
		if (btnBet == null) {
			btnBet = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
			btnBet.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					BetGUI a = new BetGUI(null);
					a.setUsername(username);
					a.setVisible(true);
				}
			});
			btnBet.setText(ResourceBundle.getBundle("Etiquetas").getString("Bet")); //$NON-NLS-1$ //$NON-NLS-2$
			btnBet.setBounds(0, 146, 479, 42);
		}
		return btnBet;
	}
	
	private JButton getBtnSendMessage() {
		if (btnSendMessage == null) {
			btnSendMessage = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
			btnSendMessage.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					MessengerGUI a = new MessengerGUI();
					a.setUsername(username);
					a.setVisible(true);
				}
			});
			btnSendMessage.setText(ResourceBundle.getBundle("Etiquetas").getString("SendMessage")); //$NON-NLS-1$ //$NON-NLS-2$
			btnSendMessage.setBounds(0, 253, 479, 43);
		}
		return btnSendMessage;
		
	}
	
	
	
	
	private JButton getBtnFollow() {
		if (btnFollow == null) {
			btnFollow = new JButton(ResourceBundle.getBundle("Etiquetas").getString("FollowUser")); //$NON-NLS-1$ //$NON-NLS-2$
			btnFollow.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					FollowGUI a = new FollowGUI();
					a.setUsername(username);
					a.setVisible(true);
				}
			});
			btnFollow.setBounds(0, 307, 479, 43);
		}
		return btnFollow;
	}
} // @jve:decl-index=0:visual-constraint="0,0"

