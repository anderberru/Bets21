package test.dataAccess;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import configuration.ConfigXML;
import domain.Bet;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.User;

public class TestDataAccess {
	protected  EntityManager  db;
	protected  EntityManagerFactory emf;

	ConfigXML  c=ConfigXML.getInstance();


	public TestDataAccess()  {
		
		System.out.println("Creating TestDataAccess instance");

		open();
		
	}

	
	public void open(){
		
		System.out.println("Opening TestDataAccess instance ");

		String fileName=c.getDbFilename();
		
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
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public boolean removeEvent(Event ev) {
		System.out.println(">> DataAccessTest: removeEvent");
		Event e = db.find(Event.class, ev.getEventNumber());
		if (e!=null) {
			db.getTransaction().begin();
			db.remove(e);
			db.getTransaction().commit();
			return true;
		} else 
		return false;
    }
	
	public boolean removeUser(User us) {
		System.out.println(">> DataAccessTest: removeUser");
		User u = db.find(User.class, us.getUserName());
		if (u!=null) {
			db.getTransaction().begin();
			db.remove(u);
			db.getTransaction().commit();
			return true;
		} else 
		return false;
    }
		
		public Event addEventWithQuestion(String desc, Date d, String question, float qty) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev=null;
				db.getTransaction().begin();
				try {
				    ev=new Event(desc,d);
				    ev.addQuestion(question, qty);
					db.persist(ev);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return ev;
	    }
		public boolean existQuestion(Event ev,Question q) {
			System.out.println(">> DataAccessTest: existQuestion");
			Event e = db.find(Event.class, ev.getEventNumber());
			if (e!=null) {
				return e.DoesQuestionExists(q.getQuestion());
			} else 
			return false;
			
		}
		
		public Registered addUser(String username, String pass, String fullname, String DNI, String payMethod, Date date, String email, int money) {
			try {
				Registered register;
				db.getTransaction().begin();
				
				register = new Registered(username, pass, fullname, DNI, date, payMethod, email, money);
				
				db.persist(register);
				db.getTransaction().commit();
				System.out.println("Gordeta " + register.getUserName());
				return register;
			} catch (Exception e) {
				return null;
			}
		}
		
		public User addUserWithBet(String username, String pass, String fullname, String DNI, String payMethod, Date date, String email, int money, double value, Event ev) {
			try {
				Event e = db.find(Event.class, ev.getEventNumber());
				Quote qu = e.getQuestions().get(0).getQuotes().get(0);
				Quote quo = db.find(Quote.class, qu.getQuoteNumber());
				User register;
				db.getTransaction().begin();
				Vector<Quote> quotes = new Vector<>();
				quotes.add(quo);
				register = new Registered(username, pass, fullname, DNI, date, payMethod, email, money);
				Bet newBet = ((Registered) register).addBet(value, quotes);
				quo.addBet(newBet);
				
				db.persist(register);
				db.persist(e);
				db.getTransaction().commit();
				System.out.println("Gordeta " + register.getUserName());
				return register;
			} catch (Exception e) {
				return null;
			}
		}
		
		public Event addEventWithQuestionWithQuote(String desc, Date d, String question, float qty, Quote quo) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev=null;
				db.getTransaction().begin();
				try {
				    ev=new Event(desc,d);
				    Question ques = ev.addQuestion(question, qty);
				    ques.addQuote(quo.getBet_description(), quo.getValue());
					db.persist(ev);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return ev;
	    }
		
		
		
}

