package gui;

import java.text.DateFormat;
import java.util.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JCalendar;

import java.awt.*;
import java.awt.event.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import businessLogic.BLFacade;
import businessLogic.ExtendedIterator;
import configuration.UtilDate;
import domain.Event;
import domain.Question;
import domain.Quote;
import exceptions.*;

public class ResultsGUI extends JFrame {
	private static final long serialVersionUID = 1L;

	private JComboBox<Event> jComboBoxEvents = new JComboBox<Event>();
	DefaultComboBoxModel<Event> modelEvents = new DefaultComboBoxModel<Event>();

	private JLabel jLabelListOfEvents = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ListEvents"));
	private JLabel jLabelEventDate = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventDate"));
	private JCalendar jCalendar = new JCalendar();
	private Calendar calendarAct = null;
	private Calendar calendarAnt = null;

	private JScrollPane scrollPaneEvents = new JScrollPane();

	private JButton jButtonPutResults = new JButton(ResourceBundle.getBundle("Etiquetas").getString("PutResults"));
	private JLabel jLabelMsg = new JLabel();
	private JLabel jLabelError = new JLabel();
	
	private Vector<Date> datesWithEventsCurrentMonth = new Vector<Date>();

	private JComboBox<Question> comboBoxQuery;
	DefaultComboBoxModel<Question> modelQuery = new DefaultComboBoxModel<Question>();
	private JTextField textFieldEventResult;

	private JScrollPane scrollPaneQuotes;
	private JTable tableQuotes = new JTable();
	private DefaultTableModel tableModelQuotes;
	private String[] columnNamesQuotes = new String[] {
			ResourceBundle.getBundle("Etiquetas").getString("QuoteN"), 
			ResourceBundle.getBundle("Etiquetas").getString("Quote"),
			ResourceBundle.getBundle("Etiquetas").getString("QuoteValue")
	};

	private JLabel lblQuotes;
	
	private Set<Quote> selectedQos = new HashSet<Quote>();

	public ResultsGUI(Vector<domain.Event> v) {
		try {
			jbInit(v);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void jbInit(Vector<domain.Event> v) throws Exception {

		this.getContentPane().setLayout(null);
		this.setSize(new Dimension(719, 448));
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("Put Result"));
		jComboBoxEvents.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Event selectedEv = (Event) jComboBoxEvents.getSelectedItem();
				
				modelQuery.removeAllElements();
				selectedQos.clear();
				textFieldEventResult.setText("");
				
				if (selectedEv != null) {
					for (domain.Question q : selectedEv.getQuestions()) {
						modelQuery.addElement(q);
					}
				}
			}
		});

		jComboBoxEvents.setModel(modelEvents);
		jComboBoxEvents.setBounds(new Rectangle(275, 73, 403, 20));
		jLabelListOfEvents.setBounds(new Rectangle(275, 50, 277, 20));

		jCalendar.setBounds(new Rectangle(40, 50, 225, 150));
		scrollPaneEvents.setBounds(new Rectangle(25, 44, 346, 116));

		jButtonPutResults.setBounds(new Rectangle(548, 370, 130, 30));
		jButtonPutResults.setEnabled(false);
		jButtonPutResults.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jButtonCreate_actionPerformed(e);
			}
		});

		jLabelMsg.setBounds(new Rectangle(188, 370, 305, 20));
		// jLabelMsg.setSize(new Dimension(305, 20));

		jLabelError.setBounds(new Rectangle(188, 370, 305, 20));
		jLabelError.setForeground(Color.red);

		this.getContentPane().add(jLabelMsg, null);
		this.getContentPane().add(jLabelError, null);
		this.getContentPane().add(jButtonPutResults, null);
		this.getContentPane().add(jLabelListOfEvents, null);
		this.getContentPane().add(jComboBoxEvents, null);

		this.getContentPane().add(jCalendar, null);
		
		
		BLFacade facade = MainGUI.getBusinessLogic();
		datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar.getDate());
		paintDaysWithEvents(jCalendar,datesWithEventsCurrentMonth);
		
		

		jLabelEventDate.setBounds(new Rectangle(40, 15, 140, 25));
		jLabelEventDate.setBounds(40, 23, 140, 25);
		getContentPane().add(jLabelEventDate);
		
		comboBoxQuery = new JComboBox<Question>();
		
		comboBoxQuery.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Hartu hautatutakoak
				int[] selectedQosPos = tableQuotes.getSelectedRows();
				int len = tableModelQuotes.getRowCount();
				for (int i = 0; i < len; i++) {
					Quote qo=(Quote)tableModelQuotes.getValueAt(i,3);
					
					boolean selected = false;
					for (int j = 0; j < selectedQosPos.length && !selected; j++) {
						if (selectedQosPos[j] == i) selected = true;
					}
					
					if (selected) {
						selectedQos.add(qo);
					}
					else {
						selectedQos.remove(qo);
					}
				}
				
				//Kargatu kuotak
				Question selectedQ = (Question) comboBoxQuery.getSelectedItem();
				Vector<Quote> quotes;
				if (selectedQ!=null) {
					quotes = selectedQ.getQuotes();
					if (quotes.isEmpty())
						lblQuotes.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQuotes")+": "+selectedQ.getQuestion());
					else 
						lblQuotes.setText(ResourceBundle.getBundle("Etiquetas").getString("ListQuotes")+" "+selectedQ.getQuestion());
				} else {
					quotes = new Vector<Quote>();
					lblQuotes.setText(ResourceBundle.getBundle("Etiquetas").getString("NoQuotes"));
				}
				
				tableModelQuotes.setDataVector(null, columnNamesQuotes);
				tableModelQuotes.setColumnCount(4);

				int i = 0;
				for (Quote qoi:quotes){
					Vector<Object> row = new Vector<Object>();

					row.add(qoi.getQuoteNumber());
					row.add(qoi.getBet_description());
					row.add(Math.round(qoi.getValue()*1000)/1000.0);
					row.add(qoi);
					tableModelQuotes.addRow(row);	
					
					if (selectedQos.contains(qoi)) {
						tableQuotes.getSelectionModel().addSelectionInterval(i, i);
					}
					i++;
				}
				tableQuotes.getColumnModel().getColumn(0).setPreferredWidth(25);
				tableQuotes.getColumnModel().getColumn(1).setPreferredWidth(238);
				tableQuotes.getColumnModel().getColumn(2).setPreferredWidth(30);
				tableQuotes.getColumnModel().removeColumn(tableQuotes.getColumnModel().getColumn(3)); // not shown in JTable
			}
			
		});
		comboBoxQuery.setModel(modelQuery);
		comboBoxQuery.setBounds(275, 165, 403, 22);
		getContentPane().add(comboBoxQuery);
		
		JLabel lblListOfQueries = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ListQuestions")); //$NON-NLS-1$ //$NON-NLS-2$
		lblListOfQueries.setBounds(275, 146, 202, 14);
		getContentPane().add(lblListOfQueries);
		
		JLabel lblEventResult = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("EventResult")); //$NON-NLS-1$ //$NON-NLS-2$
		lblEventResult.setBounds(new Rectangle(25, 211, 75, 20));
		lblEventResult.setBounds(275, 97, 169, 20);
		getContentPane().add(lblEventResult);
		
		textFieldEventResult = new JTextField();
		textFieldEventResult.setBounds(new Rectangle(100, 211, 140, 20));
		textFieldEventResult.setBounds(454, 98, 224, 20);
		getContentPane().add(textFieldEventResult);
		
		scrollPaneQuotes = new JScrollPane();
		scrollPaneQuotes.setBounds(new Rectangle(134, 240, 406, 116));
		scrollPaneQuotes.setViewportView(tableQuotes);
		tableModelQuotes = new DefaultTableModel(null, columnNamesQuotes) {
			private static final long serialVersionUID = 1L;
			@Override
			public boolean isCellEditable(int row, int column) {
				//all cells false
				return false;
			}
		};
		tableModelQuotes.setDataVector(null, columnNamesQuotes);
		tableModelQuotes.setColumnCount(4);
		tableQuotes.setModel(tableModelQuotes);
		tableQuotes.getColumnModel().getColumn(0).setPreferredWidth(25);
		tableQuotes.getColumnModel().getColumn(1).setPreferredWidth(238);
		tableQuotes.getColumnModel().getColumn(2).setPreferredWidth(30);
		tableQuotes.getColumnModel().removeColumn(tableQuotes.getColumnModel().getColumn(3)); // not shown in JTable
		this.getContentPane().add(scrollPaneQuotes, null);
		
		lblQuotes = new JLabel(ResourceBundle.getBundle("Etiquetas").getString("ListQuotes"));
		lblQuotes.setBounds(93, 216, 508, 14);
		getContentPane().add(lblQuotes);

		
		// Code for JCalendar
		this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent propertychangeevent) {
//				this.jCalendar.addPropertyChangeListener(new PropertyChangeListener() {
//					public void propertyChange(PropertyChangeEvent propertychangeevent) {
				if (propertychangeevent.getPropertyName().equals("locale")) {
					jCalendar.setLocale((Locale) propertychangeevent.getNewValue());
				} else if (propertychangeevent.getPropertyName().equals("calendar")) {
					calendarAnt = (Calendar) propertychangeevent.getOldValue();
					calendarAct = (Calendar) propertychangeevent.getNewValue();
					System.out.println("calendarAnt: "+calendarAnt.getTime());
					System.out.println("calendarAct: "+calendarAct.getTime());
					DateFormat dateformat1 = DateFormat.getDateInstance(1, jCalendar.getLocale());
					
					int monthAnt = calendarAnt.get(Calendar.MONTH);
					int monthAct = calendarAct.get(Calendar.MONTH);
					if (monthAct!=monthAnt) {
						if (monthAct==monthAnt+2) { 
							// Si en JCalendar estÃ¡ 30 de enero y se avanza al mes siguiente, devolverÃ­a 2 de marzo (se toma como equivalente a 30 de febrero)
							// Con este cÃ³digo se dejarÃ¡ como 1 de febrero en el JCalendar
							calendarAct.set(Calendar.MONTH, monthAnt+1);
							calendarAct.set(Calendar.DAY_OF_MONTH, 1);
						}
						
						jCalendar.setCalendar(calendarAct);
						
						BLFacade facade = MainGUI.getBusinessLogic();

						datesWithEventsCurrentMonth=facade.getEventsMonth(jCalendar.getDate());
					}



					paintDaysWithEvents(jCalendar,datesWithEventsCurrentMonth);

					//	Date firstDay = UtilDate.trim(new Date(jCalendar.getCalendar().getTime().getTime()));
					Date firstDay = UtilDate.trim(calendarAct.getTime());
					

					try {
						BLFacade facade = MainGUI.getBusinessLogic();

						ExtendedIterator<Event> events = facade.getEvents(firstDay);

						if (events.isEmpty())
							jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("NoEvents")
									+ ": " + dateformat1.format(calendarAct.getTime()));
						else
							jLabelListOfEvents.setText(ResourceBundle.getBundle("Etiquetas").getString("Events") + ": "
									+ dateformat1.format(calendarAct.getTime()));
						jComboBoxEvents.removeAllItems();
						System.out.println("Events " + events);

						while (events.hasNext()) {
					        Event ev = events.next();
					        modelEvents.addElement(ev);
					    }
						jComboBoxEvents.repaint();
						
						
						if (events.isEmpty()) {
							jButtonPutResults.setEnabled(false);
							selectedQos.clear();
							textFieldEventResult.setText("");
						} else {
							jButtonPutResults.setEnabled(true);
						}
					} catch (Exception e1) {

						jLabelError.setText(e1.getMessage());
					}

				}
			}
		});
	}

	
public static void paintDaysWithEvents(JCalendar jCalendar,Vector<Date> datesWithEventsCurrentMonth) {
		// For each day with events in current month, the background color for that day is changed.

		
		Calendar calendar = jCalendar.getCalendar();
		
		int month = calendar.get(Calendar.MONTH);
		int today=calendar.get(Calendar.DAY_OF_MONTH);
		int year=calendar.get(Calendar.YEAR);
		
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		int offset = calendar.get(Calendar.DAY_OF_WEEK);

		if (Locale.getDefault().equals(new Locale("es")))
			offset += 4;
		else
			offset += 5;
		
		
	 	for (Date d:datesWithEventsCurrentMonth){

	 		calendar.setTime(d);
	 		System.out.println(d);
	 		

			
			// Obtain the component of the day in the panel of the DayChooser of the
			// JCalendar.
			// The component is located after the decorator buttons of "Sun", "Mon",... or
			// "Lun", "Mar"...,
			// the empty days before day 1 of month, and all the days previous to each day.
			// That number of components is calculated with "offset" and is different in
			// English and Spanish
//			    		  Component o=(Component) jCalendar.getDayChooser().getDayPanel().getComponent(i+offset);; 
			Component o = (Component) jCalendar.getDayChooser().getDayPanel()
					.getComponent(calendar.get(Calendar.DAY_OF_MONTH) + offset);
			o.setBackground(Color.CYAN);
	 	}
	 	
 			calendar.set(Calendar.DAY_OF_MONTH, today);
	 		calendar.set(Calendar.MONTH, month);
	 		calendar.set(Calendar.YEAR, year);

	 	
	}
	
	 
	private void jButtonCreate_actionPerformed(ActionEvent e) {
		domain.Event event = ((domain.Event) jComboBoxEvents.getSelectedItem());
		
		//Hartu hautatutakoak
		int[] selectedQosPos = tableQuotes.getSelectedRows();
		int len = tableModelQuotes.getRowCount();
		for (int i = 0; i < len; i++) {
			Quote qo=(Quote)tableModelQuotes.getValueAt(i,3);
			
			boolean selected = false;
			for (int j = 0; j < selectedQosPos.length && !selected; j++) {
				if (selectedQosPos[j] == i) selected = true;
			}
			
			if (selected) {
				selectedQos.add(qo);
			}
			else {
				selectedQos.remove(qo);
			}
		}

		try {
			jLabelError.setText("");
			jLabelMsg.setText("");

			BLFacade facade = MainGUI.getBusinessLogic();

			facade.putResults(event, textFieldEventResult.getText(), selectedQos);

			jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("EventResultsSaved"));

		} catch (EventNotFinished e1) {
			jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("EventNotFinished"));
		} catch (EventAlreadyRemoved e1) {
			jLabelMsg.setText(ResourceBundle.getBundle("Etiquetas").getString("EventAlreadyRemoved"));
		} catch (EventResultsAlreadyIn e1) {
			jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("EventResultsAlreadyIn"));
		} catch (BlankEventResult e1) {
			jLabelError.setText(ResourceBundle.getBundle("Etiquetas").getString("BlankEventResult"));
		} catch (Exception e1) {

			e1.printStackTrace();

		}
	}
}