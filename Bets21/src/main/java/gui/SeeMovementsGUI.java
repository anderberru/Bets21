package gui;

import java.util.ResourceBundle;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import businessLogic.BLFacade;
import domain.Movement;
import javax.swing.JList;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SeeMovementsGUI extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame nirePantaila;
	private String username;
	private JList<Movement> list = new JList<Movement>();
	private DefaultListModel<Movement> moveModel = new DefaultListModel<Movement>();
	private JScrollPane scrollPane;
	
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Create the frame.
	 */
	public SeeMovementsGUI() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowActivated(WindowEvent e) {
				BLFacade facade = MainGUI.getBusinessLogic();
				Vector<Movement> movements = facade.allMovements(username);
				for(Movement move :movements) {
					move.getDescription();
					move.getValue();
					moveModel.addElement(move);
				}
			}
		});
		initialize();
		//this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		nirePantaila=this;
		getContentPane().setLayout(null);
		
		list.setModel(moveModel);
		list.setBounds(29, 11, 428, 291);
		getContentPane().add(list);

		
		scrollPane = new JScrollPane(list);
		scrollPane.setBounds(29, 11, 428, 291);
		getContentPane().add(scrollPane);
	}
	
	/**
	 * This method initializes this
	 * 
	 * @return void
	 */
	private void initialize() {
		this.setSize(498, 350);
		this.setTitle(ResourceBundle.getBundle("Etiquetas").getString("SeeMovements"));
		
	}
}
