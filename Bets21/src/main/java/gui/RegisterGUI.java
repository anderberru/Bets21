package gui;

import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import exceptions.AgeTooLow;
import exceptions.IncorrectData;
import exceptions.UserAlreadyExist;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import javax.swing.DefaultComboBoxModel;

import java.awt.event.ActionListener;
import java.time.format.DateTimeParseException;
import java.util.ResourceBundle;

import javax.swing.JComboBox;

public class RegisterGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField userNameTF;
	private JPasswordField passwordF;
	private JTextField fullNameTF;
	private JTextField dniTF;
	private JTextField payMethodTF;
	private JTextField emailTF;
	private JTextField yearTF;
	private JTextField dayTF;
	private JFrame nirePantaila;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterGUI frame = new RegisterGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public RegisterGUI() {
		nirePantaila = this;
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		userNameTF = new JTextField();
		userNameTF.setBounds(213, 19, 174, 20);
		contentPane.add(userNameTF);
		userNameTF.setColumns(10);
		
		JLabel lbluserName = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("UserName"));
		lbluserName.setBounds(51, 20, 152, 17);
		contentPane.add(lbluserName);
		
		JLabel lblPasahitzaSartu = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		lblPasahitzaSartu.setBounds(51, 39, 152, 14);
		contentPane.add(lblPasahitzaSartu);
		
		passwordF = new JPasswordField();
		passwordF.setBounds(213, 37, 174, 20);
		contentPane.add(passwordF);
		
		JButton registerButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Register"));
		registerButton.setBounds(166, 171, 134, 23);
		contentPane.add(registerButton);
		
		JLabel lblFullName = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("FullName"));
		lblFullName.setBounds(51, 60, 152, 20);
		contentPane.add(lblFullName);
		
		JLabel lblDni = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("DNI"));
		lblDni.setBounds(51, 79, 152, 14);
		contentPane.add(lblDni);
		
		JLabel lblAdina = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BirthDate"));
		lblAdina.setBounds(51, 122, 104, 14);
		contentPane.add(lblAdina);
		
		JLabel lblPayMethod = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("PayMethod"));
		lblPayMethod.setBounds(51, 99, 152, 14);
		contentPane.add(lblPayMethod);
		
		JLabel lblEmail = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Email"));
		lblEmail.setBounds(51, 146, 104, 14);
		contentPane.add(lblEmail);
		
		JLabel lblError = new JLabel("");
		lblError.setBounds(24, 190, 390, 23);
		contentPane.add(lblError);
		
		JLabel lblYear = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Year"));
		lblYear.setBounds(154, 122, 38, 14);
		contentPane.add(lblYear);
		
		JLabel lblMonth = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Month"));
		lblMonth.setBounds(238, 122, 36, 14);
		contentPane.add(lblMonth);
		
		JLabel lblDay = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Day"));
		lblDay.setBounds(348, 123, 30, 14);
		contentPane.add(lblDay);
		
		fullNameTF = new JTextField();
		fullNameTF.setColumns(10);
		fullNameTF.setBounds(213, 59, 174, 20);
		contentPane.add(fullNameTF);
		
		dniTF = new JTextField();
		dniTF.setColumns(10);
		dniTF.setBounds(213, 77, 174, 20);
		contentPane.add(dniTF);
		
		payMethodTF = new JTextField();
		payMethodTF.setColumns(10);
		payMethodTF.setBounds(213, 97, 174, 20);
		contentPane.add(payMethodTF);
		
		emailTF = new JTextField();
		emailTF.setColumns(10);
		emailTF.setBounds(213, 140, 174, 20);
		contentPane.add(emailTF);
		
		yearTF = new JTextField();
		yearTF.setColumns(10);
		yearTF.setBounds(191, 119, 39, 20);
		contentPane.add(yearTF);
		
		dayTF = new JTextField();
		dayTF.setColumns(10);
		dayTF.setBounds(378, 120, 36, 20);
		contentPane.add(dayTF);
		
		JComboBox<String> monthCB = new JComboBox<String>();
		monthCB.setBounds(273, 118, 65, 22);
		contentPane.add(monthCB);
		
		DefaultComboBoxModel<String> monthNames = new DefaultComboBoxModel<String>();
		monthCB.setModel(monthNames);
		
		monthNames.addElement("January");
		monthNames.addElement("February");
		monthNames.addElement("March");
		monthNames.addElement("April");
		monthNames.addElement("May");
		monthNames.addElement("June");
		monthNames.addElement("July");
		monthNames.addElement("August");
		monthNames.addElement("September");
		monthNames.addElement("October");
		monthNames.addElement("November");
		monthNames.addElement("December");
		monthCB.setSelectedIndex(0);
		
		
		registerButton.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				String userName=userNameTF.getText();
				String password=new String(passwordF.getPassword());
				String fullName=fullNameTF.getText();
				String dni=dniTF.getText();
				String payMethod=payMethodTF.getText();
				String year=yearTF.getText();
				int month=monthCB.getSelectedIndex() + 1;
				String day=dayTF.getText();
				String email = emailTF.getText();
				int money = 0;
				
				BLFacade facade = MainGUI.getBusinessLogic();
				
				try {
					facade.register(userName, password, fullName, dni, payMethod, year, month, day, email, money);
					nirePantaila.setVisible(false);
				}
				catch (IncorrectData e1) {
					lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("IncorrectDataNull"));
				}
				catch (AgeTooLow e2) {
					lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("AgeTooLow"));
				}
				catch (NumberFormatException e3) {
					lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("IncorrectDataFormat"));
				}
				catch (UserAlreadyExist e4) {
					lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("UsernameAlreadyUsed"));
				}
				catch (java.time.format.DateTimeParseException | com.sun.xml.ws.fault.ServerSOAPFaultException e5) {
					lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("IncorrectDataFormat"));
				}
			}
		});
		
	}
}
