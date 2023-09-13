package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import businessLogic.BLFacade;
import exceptions.AlreadyFollower;
import exceptions.AlreadyFollowing;
import exceptions.UserDoesNotExist;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

public class FollowGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField UsertextField;
	private JLabel lblUserLabel;
	private JButton btnNewButton;
	private String username;
	private JLabel lblErrorLabel;


	/**
	 * Create the frame.
	 */
	public FollowGUI() {
		setTitle(ResourceBundle.getBundle("Etiquetas").getString("FollowUser"));
		setBounds(100, 100, 450, 141);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		lblUserLabel = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("FollowLabel"));
		lblUserLabel.setBounds(10, 40, 135, 14);
		contentPane.add(lblUserLabel);
		
		UsertextField = new JTextField();
		UsertextField.setBounds(155, 37, 172, 20);
		contentPane.add(UsertextField);
		UsertextField.setColumns(10);
		
		btnNewButton = new JButton(ResourceBundle.getBundle("Etiquetas").getString("Follow"));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				BLFacade facade = MainGUI.getBusinessLogic();
				String followedUser=UsertextField.getText();
				
				try {
					facade.follow(username, followedUser);
					lblErrorLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("Following")+followedUser);
				} catch (UserDoesNotExist e1) {
					lblErrorLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("UserDoesNotExist"));
				} catch (AlreadyFollower e2) {
					lblErrorLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("AlreadyFollower"));
				} catch (AlreadyFollowing e3) {
					lblErrorLabel.setText(ResourceBundle.getBundle("Etiquetas").getString("AlreadyFollowing"));
				}
			}
		});
		btnNewButton.setBounds(337, 36, 89, 23);
		contentPane.add(btnNewButton);
		
		lblErrorLabel = new JLabel("");
		lblErrorLabel.setBounds(110, 79, 302, 14);
		contentPane.add(lblErrorLabel);
	}
	
	public void setUsername(String username) {
		this.username=username;
	}
}
