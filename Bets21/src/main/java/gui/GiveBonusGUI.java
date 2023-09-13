package gui;

import java.util.*;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.*;

import businessLogic.BLFacade;
import domain.*;
import exceptions.*;
import java.awt.Color;
import java.text.SimpleDateFormat;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Day;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;

public class GiveBonusGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private JButton jButtonGiveBonus = new JButton(ResourceBundle.getBundle("Etiquetas").getString("GiveBonus"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();
	
	private JTextField textFieldBonus;

	private JScrollPane scrollPaneUsers;
	private JTable tableUsers = new JTable();
	private DefaultTableModel tableModelUsers;
	private String[] columnNamesUsers = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("UserName"), //User
			ResourceBundle.getBundle("Etiquetas").getString("Money"), //Money
			ResourceBundle.getBundle("Etiquetas").getString("SuccessAmount"), //Success Amount
			ResourceBundle.getBundle("Etiquetas").getString("SuccessRate"), //Success Rate
			ResourceBundle.getBundle("Etiquetas").getString("Bonus") //Bonus
	};

	private JLabel lblUsers;
	
	private JFreeChart chart;

	private ChartPanel chartPanel;
	
	public GiveBonusGUI() {
		try {
			jbInit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit() throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(1000, 600));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("GiveBonus"));

		jButtonGiveBonus.setBounds(new Rectangle(558, 456, 130, 30));
		jButtonGiveBonus.setEnabled(false);
		jButtonGiveBonus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonGiveBonus_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(282, 499, 406, 20));
		// jLabelMsg.setSize(new Dimension(305, 20));

		jLabelError.setBounds(new Rectangle(282, 519, 406, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);
		this.getContentPane().add(jButtonGiveBonus, null);
		
		
		textFieldBonus = new JTextField();
		textFieldBonus.setBounds(new Rectangle(100, 211, 140, 20));
		textFieldBonus.setBounds(364, 462, 166, 20);
		getContentPane().add(textFieldBonus);
		
		scrollPaneUsers = new JScrollPane();
		scrollPaneUsers.setBounds(new Rectangle(282, 330, 406, 116));
		scrollPaneUsers.setViewportView(tableUsers);
		tableModelUsers = new DefaultTableModel(null, columnNamesUsers) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}
		};
		tableModelUsers.setDataVector(null, columnNamesUsers);
		tableModelUsers.setColumnCount(6);
		tableUsers.setModel(tableModelUsers);
		tableUsers.getColumnModel().getColumn(0).setPreferredWidth(75); //User
		tableUsers.getColumnModel().getColumn(1).setPreferredWidth(75); //Money
		tableUsers.getColumnModel().getColumn(2).setPreferredWidth(75); //SuccesAmount
		tableUsers.getColumnModel().getColumn(3).setPreferredWidth(75); //SuccesRate
		tableUsers.getColumnModel().getColumn(4).setPreferredWidth(75); //Bonus
		tableUsers.getColumnModel().removeColumn(tableUsers.getColumnModel().getColumn(5)); // not shown in JTable
		this.getContentPane().add(scrollPaneUsers, null);
		
		tableUsers.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					int[] selectedUsersPos = tableUsers.getSelectedRows();
					Vector<Vector<Double>> datas = new Vector<Vector<Double>>();
					Vector<String> username = new Vector<String>();
					if (selectedUsersPos.length != 0) {
						BLFacade facade = MainGUI.getBusinessLogic();
						for (int i = 0; i < selectedUsersPos.length; i++) {
							Registered r=(Registered)tableModelUsers.getValueAt(selectedUsersPos[i],5);
							datas.add(facade.getUserEarningsLastMonth(r));
							username.add(r.getUserName());
						}
						jButtonGiveBonus.setEnabled(true);
						lblUsers.setText(ResourceBundle.getBundle("Etiquetas").getString("SelectedUsers") + username);
					} 
					else {
						jButtonGiveBonus.setEnabled(false);
						lblUsers.setText(ResourceBundle.getBundle("Etiquetas").getString("ListUsers"));
					}
					chartPanel.setChart(newChart(datas,username));
				}
			}
		});
		
		lblUsers = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ListUsers")); //Selected users:
		lblUsers.setBounds(282, 306, 406, 14);
		getContentPane().add(lblUsers);
		
		JLabel lblBonus = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("BonusValue")); //$NON-NLS-1$ //$NON-NLS-2$
		lblBonus.setBounds(282, 465, 83, 13);
		getContentPane().add(lblBonus);
		
		showUsers();
	}
	 
	private void jButtonGiveBonus_actionPerformed(ActionEvent e) {
		int[] selectedUsersPos = tableUsers.getSelectedRows();
		Set<Registered> selectedUsers = new HashSet<Registered>();
		for (int i: selectedUsersPos) {
			Registered r=(Registered)tableModelUsers.getValueAt(i,5);
			selectedUsers.add(r);
		}

		try {
			jLabelError.setText("");
			jLabelMsg.setText("");

			BLFacade facade = MainGUI.getBusinessLogic();

			facade.giveBonus(textFieldBonus.getText(), selectedUsers);

			jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("BonusSaved"));
			
			showUsers();

		} catch (IncorrectData e1) {
			jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("IncorrectBonus")); //Bonus value must be a double between 0 and 1
		} catch (UserDoesNotExist e1) {
			jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("NoSelectedUsers")); //Must select at least one user
		} catch (Exception e1) {

			e1.printStackTrace();

		}
	}
	
	private void showUsers() {
		
		lblUsers.setText(ResourceBundle.getBundle("Etiquetas").getString("ListUsers"));
		
		BLFacade facade = MainGUI.getBusinessLogic();
		
		Vector<Registered> users = facade.getUsers();
	
		tableModelUsers.setRowCount(0);
		for (Registered r: users){
			Vector<Object> row = new Vector<Object>();
	
			row.add(r.getUserName());
			row.add(r.getMoney());
			row.add(r.getSuccessAmount());
			row.add(r.getSuccessRate());
			row.add(r.getBonus());
			row.add(r);
			tableModelUsers.addRow(row);
		}
		
		chartPanel = new ChartPanel(newChart(new Vector<Vector<Double>>(), new Vector<String>()));
		chartPanel.setBounds(5, 5, 971, 291);
		this.getContentPane().add(chartPanel, null);
	}
	
	    private JFreeChart newChart(Vector<Vector<Double>> datas, Vector<String> username) {
	    	TimeSeriesCollection dataset = new TimeSeriesCollection();
	    	Calendar cal = Calendar.getInstance();
	        for (int j = 0; j<datas.size(); j++) {
	        	cal.add(Calendar.DATE, -30);
	        	TimeSeries series = new TimeSeries(username.get(j));
	        	Vector<Double> data = datas.get(j);
	        	for (int i = 0; i < 30; i++) {
		            cal.add(Calendar.DATE, 1);
		            Date date = cal.getTime();
		            if (i<data.size()) series.add(new Day(date), Math.round(data.get(i)));
		            else series.add(new Day(date), 0);
		        }
	        	dataset.addSeries(series);
	        }

	        JFreeChart chart = ChartFactory.createTimeSeriesChart(
	        		ResourceBundle.getBundle("Etiquetas").getString("LastMonthEarnings") + username, //Aldatu hizkuntzaren arabera
	        		ResourceBundle.getBundle("Etiquetas").getString("Date"),
	        		ResourceBundle.getBundle("Etiquetas").getString("Earnings"),
	                dataset,
	                true,
	                true,
	                false
	        );

	        XYPlot plot = chart.getXYPlot();
	        plot.setBackgroundPaint(new Color(255, 255, 255));
	        plot.setDomainGridlinePaint(new Color(0, 0, 0));

	        //DateAxis axis = (DateAxis) plot.getDomainAxis();
	        //axis.setDateFormatOverride(new SimpleDateFormat("dd-MMM"));
	        //axis.setTimeZone(TimeZone.getTimeZone("GMT+2"));
	        
	        chart.setBackgroundPaint(new Color(220, 220, 220));
	        chart.setBorderPaint(new Color(0,0,0));
	        chart.setBorderVisible(true);
	        return chart;
	    }
}









	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
