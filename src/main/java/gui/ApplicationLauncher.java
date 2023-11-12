package gui;

import java.awt.Color;
import java.net.URL;
import java.util.Calendar;
import java.util.Locale;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import configuration.ConfigXML;
import configuration.UtilDate;
import dataAccess.DataAccess;
import domain.Event;
import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import businessLogic.ExtendedIterator;

public class ApplicationLauncher {

	public static void main(String[] args) {

		ConfigXML c = ConfigXML.getInstance();

		FacadeFactory ff = new FacadeFactory(); // faktoria hasieratu

		System.out.println(c.getLocale());

		Locale.setDefault(new Locale(c.getLocale()));

		System.out.println("Locale: " + Locale.getDefault());

		MainGUI a = new MainGUI();
		a.setVisible(true);

		try {

			BLFacade appFacadeInterface = ff.createFacade(c); // faktoria deitu

//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsClassicLookAndFeel");
//			UIManager.setLookAndFeel("com.sun.java.swing.plaf.motif.MotifLookAndFeel");
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

			/*
			 * if (c.getDataBaseOpenMode().equals("initialize"))
			 * appFacadeInterface.initializeBD();
			 */
			MainGUI.setBussinessLogic(appFacadeInterface);

			

		} catch (Exception e) {
			a.jLabelSelectOption.setText("Error: " + e.toString());
			a.jLabelSelectOption.setForeground(Color.RED);

			System.out.println("Error in ApplicationLauncher: " + e.toString());
		}
		// a.pack();

	}

}
