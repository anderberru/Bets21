package gui;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;

import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField textField;
	private JPasswordField passwordField;
	private JFrame nirePantaila;

	public LoginGUI() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void jbInit() throws Exception {
		nirePantaila = this;
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(240, 32, 125, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel lblNewLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("UserName"));
		lblNewLabel.setBounds(69, 35, 131, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblPasahitzaSartu = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("Password"));
		lblPasahitzaSartu.setBounds(69, 76, 131, 14);
		contentPane.add(lblPasahitzaSartu);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(240, 73, 125, 20);
		contentPane.add(passwordField);
		
		JButton btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Login"));
		btnNewButton.setBounds(154, 169, 134, 23);
		contentPane.add(btnNewButton);
		
		JLabel lblError = new JLabel();
		lblError.setBounds(41, 216, 357, 20);
		contentPane.add(lblError);
		btnNewButton.addActionListener(new ActionListener() {
			

			public void actionPerformed(ActionEvent e) {
				String izena=textField.getText();
				String pass=new String(passwordField.getPassword());
				BLFacade facade = MainGUI.getBusinessLogic();
				if (izena.isBlank() && pass.isEmpty()) {
					lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("IncorrectDataNull"));
				} else {
					if (facade.isLogin(izena, pass)) {
						if (facade.isAdmin(izena, pass)) {
							AdminGUI frame1 = new AdminGUI();
							frame1.setUsername(izena);
							frame1.setVisible(true);
						} else {
							RegisteredGUI frame2 = new RegisteredGUI();
							frame2.setUsername(izena);
							frame2.setVisible(true);
						}
						nirePantaila.setVisible(false);
					}
					else {
						lblError.setText(ResourceBundle.getBundle("Etiquetas").getString("WrongUserOrPassword"));
					}
				}
				 
			}
		});
		
	}
}
