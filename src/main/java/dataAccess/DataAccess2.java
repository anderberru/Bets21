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
public class DataAccess2  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;

	String s1 = "�Qui�n ganar� el partido?";
	
	ConfigXML c=ConfigXML.getInstance();

     public DataAccess2(boolean initializeMode)  {
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);
		
	}

	public DataAccess2()  {	
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
	

/**
 * This method retrieves from the database the events of a given date 
 * 
 * @param date in which events are retrieved
 * @return collection of events
 */
public Vector<Event> getEvents(Date date) {
	System.out.println(">> DataAccess: getEvents");
	Vector<Event> res = new Vector<Event>();	
	TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
	query.setParameter(1, date);
	List<Event> events = query.getResultList();
 	 for (Event ev:events){
 	   System.out.println(ev.toString());		 
	   res.add(ev);
	  }
 	return res;}


	public void removeEvent(Event event) {
		Event ev=db.find(Event.class, event.getEventNumber());
		
		db.getTransaction().begin();
		
		Vector<Question> questions = ev.getQuestions();
		Vector<Quote> quotes;
		Vector<Bet> bets;
		
		for (Question q : questions) {
			
			Question question = db.find(Question.class, q.getQuestionNumber());
			quotes=question.getQuotes();
			for (Quote quo : quotes) {
				
				Quote quote = db.find(Quote.class, quo.getQuoteNumber());
				bets=quote.getBets();
				for (Bet b : bets) {
					
					Bet bet = db.find(Bet.class, b.getBetNumber());
					String username = bet.getRegistered().getUserName();
					Registered user = db.find(Registered.class, username);
					user.setMoney(user.getMoney() + bet.getValue());
					user.removeBet(bet);

					user.addMovement(bet.getValue(), "Bet removed: +"+bet.getValue());
					db.persist(user);
				}
			}
		}
		
		db.remove(ev);
		
		db.getTransaction().commit();
	}
	
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	
}
