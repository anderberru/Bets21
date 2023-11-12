package businessLogic;

import java.io.ObjectInputFilter.Config;
import java.net.MalformedURLException;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Event;
import gui.FacadeFactory;

public class MainIterator {

	public static void main(String[] args) {
		 boolean isLocal=true;
		 
		 //Facade objektua lortu lehendabiziko ariketa erabiliz
		 ConfigXML c = ConfigXML.getInstance();
		 FacadeFactory ff = new FacadeFactory();
		 
		 Locale.setDefault(new Locale(c.getLocale()));
		 
		 
		BLFacade facadeInterface = null;
		try {
			facadeInterface = ff.createFacade(c);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		Calendar today = Calendar.getInstance();
		int month = today.get(Calendar.MONTH);
		month += 1;
		int year = today.get(Calendar.YEAR);

		ExtendedIterator<Event> i = facadeInterface.getEvents(UtilDate.newDate(year, month, 17));
		Event ev;
		i.goLast();
		while (i.hasPrevious()) {
			ev = i.previous();
			System.out.println(ev.toString());
		}
		// Nahiz eta suposatu hasierara ailegatu garela, eragiketa egiten dugu.
		i.goFirst();
		while (i.hasNext()) {
			ev = i.next();
			System.out.println(ev.toString());
		}
	}

}
