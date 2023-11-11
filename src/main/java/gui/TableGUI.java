package gui;

import java.awt.EventQueue;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import businessLogic.BLFacade;
import domain.Registered;
import domain.UserAdapter;

import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TableGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTable table;
	private JScrollPane scrollPane;
	private String username;
	private Registered user;
	private UserAdapter adapter;
	private DefaultTableModel tableModelUsers;


	/**
	 * Create the frame.
	 */
	public TableGUI(String username) {
		super();
		setTitle("Bet list");
		this.username = username;
		setBounds(100, 100, 576, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		BLFacade facade = MainGUI.getBusinessLogic();
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 542, 242);
		contentPane.add(scrollPane);
		user = facade.getUser(username);
		adapter = new UserAdapter(user);
		table = new JTable(adapter);
		scrollPane.setViewportView(table);
		
		String[] columnNames = new String[adapter.getColumnCount()];
		for (int i = 0; i<adapter.getColumnCount(); i++) {
			columnNames[i] = adapter.getColumnName(i);
		}
		
		tableModelUsers = new DefaultTableModel(null, columnNames) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}
		};
		
		tableModelUsers.setDataVector(null, columnNames);
		tableModelUsers.setColumnCount(adapter.getColumnCount());
		table.setModel(tableModelUsers);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(50); 
		table.getColumnModel().getColumn(2).setPreferredWidth(50);
		table.getColumnModel().getColumn(3).setPreferredWidth(50); 
		this.getContentPane().add(scrollPane, null);
		
		tableModelUsers.setRowCount(0);
		for (int i = 0; i<adapter.getRowCount(); i++){
			Vector<Object> row = new Vector<Object>();
	
			row.add(adapter.getValueAt(i, 0));
			row.add(adapter.getValueAt(i, 1));
			row.add(adapter.getValueAt(i, 2));
			row.add(adapter.getValueAt(i, 3));

			tableModelUsers.addRow(row);
		}
		

	}

}
