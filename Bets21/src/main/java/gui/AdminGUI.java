package gui;

/**
 * @author Software Engineering teachers */


import javax.swing.*;


import java.awt.Color;
import java.awt.Font;
import java.util.ResourceBundle;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class AdminGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private String username;
	
	private JPanel jContentPane = null;
	private JButton jButtonQueryQueries = null;
	
	protected JLabel jLabelSelectOption = null;
	private JButton btnCreateEvent = null;
	private JButton btnCreateQuestion = null;
	private JButton btnCreateQuote = null;
	private JButton btnPutResult = null;
	private JButton btnRemoveEvent = null;
	private JButton btnDuplicateEvent = null;
	private JButton btnSendMessage = null;
	private JButton btnGiveBonus = null;
	
	/**
	 * This is the default constructor
	 */
	public AdminGUI() {
		super();
		
		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void setUsername(String izena) {
		this.username = izena;
		
	}

	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(495, 541);
		this.setContentPane(getJContentPane());
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("AdminTitle"));
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
			jContentPane.add(getBtnCreateEvent());
			jContentPane.add(getBtnCreateQuestion());
			jContentPane.add(getBtnCreateQuote());
			jContentPane.add(getBtnPutResult());
			jContentPane.add(getBtnRemoveEvent());
			jContentPane.add(getBtnSendMessage());
			jContentPane.add(getBtnDuplicateEvent());
			jContentPane.add(getBtnGiveBonus());
			
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
			jButtonQueryQueries.setBounds(0, 186, 479, 43);
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
			jLabelSelectOption.setBounds(0, 0, 479, 43);
			jLabelSelectOption.setFont(new Font("Tahoma", Font.BOLD, 13));
			jLabelSelectOption.setForeground(Color.BLACK);
			jLabelSelectOption.setHorizontalAlignment(SwingConstants.CENTER);
		}
		return jLabelSelectOption;
	}
	
	private JButton getBtnCreateEvent() {
		if (btnCreateEvent == null) {
			btnCreateEvent = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
			btnCreateEvent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new CreateEventGUI(null);
					a.setVisible(true);
				}
			});
			btnCreateEvent.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateEvent")); //$NON-NLS-1$ //$NON-NLS-2$
			btnCreateEvent.setBounds(0, 32, 479, 42);
		}
		return btnCreateEvent;
	}
	
	private JButton getBtnCreateQuestion() {
		if (btnCreateQuestion == null) {
			btnCreateQuestion = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
			btnCreateQuestion.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new CreateQuestionGUI(null);
					a.setVisible(true);
				}
			});
			btnCreateQuestion.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuestion")); //$NON-NLS-1$ //$NON-NLS-2$
			btnCreateQuestion.setBounds(0, 81, 479, 41);
		}
		return btnCreateQuestion;
	}
	
	private JButton getBtnCreateQuote() {
		if (btnCreateQuote == null) {
			btnCreateQuote = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
			btnCreateQuote.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new CreateQuoteGUI(null);
					a.setVisible(true);
				}
			});
			btnCreateQuote.setText(ResourceBundle.getBundle("Etiquetas").getString("CreateQuote")); //$NON-NLS-1$ //$NON-NLS-2$
			btnCreateQuote.setBounds(0, 133, 479, 42);
		}
		return btnCreateQuote;
	}
	
	private JButton getBtnPutResult() {
		if (btnPutResult == null) {
			btnPutResult = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
			btnPutResult.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new ResultsGUI(null);
					a.setVisible(true);
				}
			});
			btnPutResult.setText(ResourceBundle.getBundle("Etiquetas").getString("PutResults")); //$NON-NLS-1$ //$NON-NLS-2$
			btnPutResult.setBounds(0, 240, 479, 42);
		}
		return btnPutResult;
	}
	
	private JButton getBtnRemoveEvent() {
		if (btnRemoveEvent == null) {
			btnRemoveEvent = new JButton(); //$NON-NLS-1$ //$NON-NLS-2$
			btnRemoveEvent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					JFrame a = new RemoveEventGUI(null);
					a.setVisible(true);
				}
			});
			btnRemoveEvent.setText(ResourceBundle.getBundle("Etiquetas").getString("RemoveEvent")); //$NON-NLS-1$ //$NON-NLS-2$
			btnRemoveEvent.setBounds(0, 294, 479, 38);
		}
		return btnRemoveEvent;
	}
	
		
	
	private JButton getBtnDuplicateEvent() {
		if (btnDuplicateEvent == null) {
			btnDuplicateEvent = new JButton(ResourceBundle.getBundle("Etiquetas").getString("DuplicateEvent")); //$NON-NLS-1$ //$NON-NLS-2$
			btnDuplicateEvent.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					DuplicateEventGUI a = new DuplicateEventGUI(null);
					a.setVisible(true);
				}
			});
			btnDuplicateEvent.setBounds(0, 343, 479, 38);
		}
		return btnDuplicateEvent;
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
			btnSendMessage.setBounds(0, 390, 479, 43);
		}
		return btnSendMessage;
		
	}
	private JButton getBtnGiveBonus() {
		if (btnGiveBonus == null) {
			btnGiveBonus = new JButton(ResourceBundle.getBundle("Etiquetas").getString("GiveBonus")); //$NON-NLS-1$ //$NON-NLS-2$
			btnGiveBonus.setBounds(0, 444, 479, 38);
			btnGiveBonus.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					GiveBonusGUI a = new GiveBonusGUI();
					a.setVisible(true);
				}
			});
		}
		return btnGiveBonus;
	}



	
} // @jve:decl-index=0:visual-constraint="0,0"

