package gui;


import java.awt.event.WindowAdapter;

import java.awt.event.WindowEvent;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.swing.JFrame;
import businessLogic.BLFacade;
import domain.Message;
import domain.Movement;
import exceptions.IncorrectData;
import exceptions.IncorrectReciever;

import javax.swing.JScrollPane;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.JComboBox;

public class MessengerGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JFrame nirePantaila;
	private String username;
	private JList<Message> list1 = new JList<Message>();
	private DefaultListModel<Message> moveModel = new DefaultListModel<Message>();
	private JList<Message> list2 = new JList<Message>();
	private DefaultListModel<Message> moveMode2 = new DefaultListModel<Message>();
	private JTextField textSendMessage;
	private JTextField textReciever;

	/**
	 * Launch the application.
	 */
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public MessengerGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				Vector<Message> receivedMessages = facade.allRecivedMessages(username);
				for(Message move :receivedMessages) {
					move.getMessage();
					moveModel.addElement(move);
				}
				
				Vector<Message> sentMessages = facade.allSentMessages(username);
				for(Message move :sentMessages) {
					move.getMessage();
					moveMode2.addElement(move);
				}
			}
		});
		//initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 498, 475);
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("SendMessage"));
		//nirePantaila=this;
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		list1.setModel(moveModel);
		list1.setBounds(46, 30, 377, 130);
		contentPane.add(list1);
		
		JScrollPane scrollPane = new JScrollPane(list1);
		scrollPane.setBounds(46, 30, 377, 130);
		contentPane.add(scrollPane);
		
		list2.setModel(moveMode2);
		list2.setBounds(46, 190, 377, 130);
		contentPane.add(list2);
		
		JScrollPane scrollPane_1 = new JScrollPane(list2);
		scrollPane_1.setBounds(46, 190, 377, 130);
		contentPane.add(scrollPane_1);
		
		
		JLabel lblRecievedMessages = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ReceivedMessages")); //$NON-NLS-1$ //$NON-NLS-2$
		lblRecievedMessages.setBounds(46, 11, 377, 23);
		contentPane.add(lblRecievedMessages);
		
		JLabel lblSentMessages = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SentMessages")); //$NON-NLS-1$ //$NON-NLS-2$
		lblSentMessages.setBounds(46, 175, 377, 14);
		contentPane.add(lblSentMessages);
		
		JLabel lblSendMessages = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SendMessage") + ": "); //$NON-NLS-1$ //$NON-NLS-2$
		lblSendMessages.setBounds(46, 384, 178, 14);
		contentPane.add(lblSendMessages);
		
		JLabel lblError = new JLabel(""); //$NON-NLS-1$ //$NON-NLS-2$
		lblError.setBounds(234, 339, 206, 44);
		contentPane.add(lblError);
		
		JButton btnSendMessage = new JButton(ResourceBundle.getBundle("Etiquetas").getString("SendMessage")); //$NON-NLS-1$ //$NON-NLS-2$
		btnSendMessage.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				String reciever = textReciever.getText();
				String message = textSendMessage.getText();
				
				BLFacade facade = MainGUI.getBusinessLogic();
				
				try {
					Message m = facade.sendMessage(message, username, reciever);
					
					moveMode2.addElement(m);
					
				} catch (IncorrectReciever e2) {
					lblError.setText("Choose other reciever");
				} catch (IncorrectData e2) {
					lblError.setText("Incorrect data");
				}
				
				
			}
		});
		btnSendMessage.setBounds(324, 404, 127, 23);
		contentPane.add(btnSendMessage);
		
		textSendMessage = new JTextField();
		textSendMessage.setBounds(46, 404, 263, 20);
		contentPane.add(textSendMessage);
		textSendMessage.setColumns(10);
		
		
		JLabel lblChooseReciever = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("SendTo")); //$NON-NLS-1$ //$NON-NLS-2$
		lblChooseReciever.setBounds(46, 326, 139, 14);
		contentPane.add(lblChooseReciever);
		
		textReciever = new JTextField();
		textReciever.setBounds(46, 351, 151, 20);
		contentPane.add(textReciever);
		textReciever.setColumns(10);
		
		
		
		
		
	}
	
	
}
