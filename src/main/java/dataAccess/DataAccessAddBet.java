package dataAccess;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.jfree.data.time.TimeSeries;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Admin;
import domain.Bet;
import domain.Event;
import domain.Message;
import domain.Movement;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.User;
import exceptions.*;

/**
 * It implements the data access to the objectDb database.
 */
public class DataAccessAddBet  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;

	String s1 = "�Qui�n ganar� el partido?";
	
	ConfigXML c=ConfigXML.getInstance();

     public DataAccessAddBet(boolean initializeMode)  {
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);
		
	}

	public DataAccessAddBet()  {	
		 this(false);
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();
		try {

	       User admin = new Admin("admin", "admin"); //ADMINISTRADOREA SORTU
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
			Event ev1=new Event(1, "Atl�tico-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "Alav�s-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "Espa�ol-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Legan�s", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alav�s-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Espa�ol-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			

			Event ev17=new Event(17, "M�laga-Valencia", UtilDate.newDate(year,month+1,28));
			Event ev18=new Event(18, "Girona-Legan�s", UtilDate.newDate(year,month+1,28));
			Event ev19=new Event(19, "Real Sociedad-Levante", UtilDate.newDate(year,month+1,28));
			Event ev20=new Event(20, "Betis-Real Madrid", UtilDate.newDate(year,month+1,28));
			
			Question q1;
			Question q2;
			Question q3;
			Question q4;
			Question q5;
			Question q6;
					
			
			if (Locale.getDefault().equals(new Locale("es"))) {
				q1=ev1.addQuestion(s1,1);
				q2=ev1.addQuestion("�Qui�n meter� el primer gol?",2);
				q3=ev11.addQuestion(s1,1);
				q4=ev11.addQuestion("�Cu�ntos goles se marcar�n?",2);
				q5=ev17.addQuestion(s1,1);
				q6=ev17.addQuestion("�Habr� goles en la primera parte?",2);
			}
			else if (Locale.getDefault().equals(new Locale("en"))) {
				q1=ev1.addQuestion("Who will win the match?",1);
				q2=ev1.addQuestion("Who will score first?",2);
				q3=ev11.addQuestion("Who will win the match?",1);
				q4=ev11.addQuestion("How many goals will be scored in the match?",2);
				q5=ev17.addQuestion("Who will win the match?",1);
				q6=ev17.addQuestion("Will there be goals in the first half?",2);
			}			
			else {
				q1=ev1.addQuestion("Zeinek irabaziko du partidua?",1);
				q2=ev1.addQuestion("Zeinek sartuko du lehenengo gola?",2);
				q3=ev11.addQuestion("Zeinek irabaziko du partidua?",1);
				q4=ev11.addQuestion("Zenbat gol sartuko dira?",2);
				q5=ev17.addQuestion("Zeinek irabaziko du partidua?",1);
				q6=ev17.addQuestion("Golak sartuko dira lehenengo zatian?",2);
				
			}
			
			db.persist(admin); //ADMINISTRADOREA DATU-BASEAN SARTU
			
			db.persist(q1);
			db.persist(q2);
			db.persist(q3);
			db.persist(q4);
			db.persist(q5);
			db.persist(q6); 
	
	        
			db.persist(ev1);
			db.persist(ev2);
			db.persist(ev3);
			db.persist(ev4);
			db.persist(ev5);
			db.persist(ev6);
			db.persist(ev7);
			db.persist(ev8);
			db.persist(ev9);
			db.persist(ev10);
			db.persist(ev11);
			db.persist(ev12);
			db.persist(ev13);
			db.persist(ev14);
			db.persist(ev15);
			db.persist(ev16);
			db.persist(ev17);
			db.persist(ev18);
			db.persist(ev19);
			db.persist(ev20);			
			
			db.getTransaction().commit();
			System.out.println("Db initialized");
		}
		catch (Exception e){
			e.printStackTrace();
		}
	}
	
	public void open(boolean initializeMode){
		
		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
	
	
	public Bet addBet(double value, Event event, Set<Quote> selectedQuotes, String username, Vector<Registered> alreadyBet) throws BetOnSameQuote, NotEnoughMoney {
		String quoNums="";
		Event ev = db.find(Event.class, event.getEventNumber());
		
		Registered user = db.find(Registered.class, username);
		
		Bet bet=null;
		
		alreadyBet.add(user); //bisitatua bezala markatu
		
		Vector<Quote> quotes = new Vector<Quote>();
		quotes.addAll(selectedQuotes);
		
		Vector<Quote> foundQuotes = new Vector<Quote>();
		
		
		
		if (user.betOnSameQuote(quotes)) throw new BetOnSameQuote();
		
		
		
		double newMoney = user.getMoney() - value;
		if (newMoney < 0 && alreadyBet.size()==1) {
			throw new NotEnoughMoney();
		} else if (newMoney>=0) {
		
			db.getTransaction().begin();
			
			for (Quote quo : quotes) {
				Quote quote = db.find(Quote.class, quo.getQuoteNumber());
				foundQuotes.add(quote);
				quoNums+=quote.getQuoteNumber()+", ";
			}
			
			bet = user.addBet(value, foundQuotes); //apostua erabiltzailean sartu
			
			user.setMoney(newMoney);
			
			for(Quote quo: quotes) { //apostua kuotetan sartu
				Quote quote = db.find(Quote.class, quo.getQuoteNumber());
				quote.addBet(bet);
			}
			
			
			user.addMovement(value, "Bet: -"+value+" on quotes: "+quoNums);
		
			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.persist(user);
			
			db.getTransaction().commit();
			
			Vector<Registered> followers = user.getFollowers();
			
			for(Registered fol: followers) {
				if(!alreadyBet.contains(fol)) {
					addBet(value, event, selectedQuotes, fol.getUserName(), alreadyBet);
				}
			}
		}
		return bet;
	}
	
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	
}
