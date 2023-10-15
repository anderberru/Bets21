package test.dataAccess;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Set;
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
import exceptions.QuoteAlreadyExist;

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
	
	public boolean removeUser(User user) {
		System.out.println(">> DataAccessTest: removeUser");
		User u = db.find(User.class, user.getUserName());
		if (u!=null) {
			db.getTransaction().begin();
			db.remove(u);
			db.getTransaction().commit();
			return true;
		} else 
		return false;
    }
		
	
	public boolean removeUserWithName(String username) {
		System.out.println(">> DataAccessTest: removeUser");
		User u = db.find(User.class, username);
		if (u!=null) {
			db.getTransaction().begin();
			db.remove(u);
			db.getTransaction().commit();
			return true;
		} else 
		return false;
    }
	
	public Event addEvent(String desc, Date d) {
		System.out.println(">> DataAccessTest: addEvent");
		Event ev=null;
			db.getTransaction().begin();
			try {
			    ev=new Event(desc,d);
				db.persist(ev);
				db.getTransaction().commit();
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return ev;
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
		
		public Quote addQuote(String bet_description, double value, Event event) throws  QuoteAlreadyExist {
			
			Event ev = db.find(Event.class, event.getEventNumber());
			Question ques = db.find(Question.class, ev.getQuestions().get(0));
			
			
			if (ques.DoesQuoteExists(bet_description, value)) throw new QuoteAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQuoteAlreadyExist"));
			
			db.getTransaction().begin();
						
			Quote q = ques.addQuote(bet_description, value);

			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return q;
		
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
		
		public Registered addUserWithBet(String username, String pass, String fullname, String DNI, String payMethod, Date date, String email, int money, double value, Event ev) {
			try {
				Event e = db.find(Event.class, ev.getEventNumber());
				Quote qu = e.getQuestions().get(0).getQuotes().get(0);
				Quote quo = db.find(Quote.class, qu.getQuoteNumber());
				
				db.getTransaction().begin();
				Vector<Quote> quotes = new Vector<>();
				quotes.add(quo);
				Registered register = new Registered(username, pass, fullname, DNI, date, payMethod, email, money);
				Bet newBet = ((Registered) register).addBet(value, quotes);
				quo.addBet(newBet);
				
				db.persist(quo);
				db.persist(register);
				
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
		
		public Event addEventWithQuestionWithQuoteWithUser(String desc, Date d, String question, float qty, String quotedesc, double Qvalue, String username, Date userdate) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev=null;
				db.getTransaction().begin();
				try {
				    ev=new Event(desc,d);
				    Question ques = ev.addQuestion(question, qty);
				    ques.addQuote(quotedesc, Qvalue);
				    Registered reg = new Registered(username, "123", "", "", userdate, "", "", 50);
					db.persist(ev);
					db.persist(reg);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return ev;
	    }
		
		public Event addEventWithQuestionWithQuoteWithUserWithFollowers(String desc, Date d, String question, float qty, String quotedesc, double Qvalue, String username, Date userdate, String follower) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev=null;
				db.getTransaction().begin();
				try {
				    ev=new Event(desc,d);
				    Question ques = ev.addQuestion(question, qty);
				    ques.addQuote(quotedesc, Qvalue);
				    Registered reg = new Registered(username, "123", "", "", userdate, "", "", 50);
				    reg.addFollower(new Registered(follower, "123", "", "", userdate, "", "", 50));
					db.persist(ev);
					db.persist(reg);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return ev;
	    }
		
		
		public Event addEventWithQuestionWithQuoteWithUserWithBet(String desc, Date d, String question, float qty, String quotedesc, double Qvalue, String username, double betValue, Date date) {
			System.out.println(">> DataAccessTest: addEvent");
			Event ev=null;
				db.getTransaction().begin();
				try {
				    ev=new Event(desc,d);
				    Question ques = ev.addQuestion(question, qty);
				    ques.addQuote(quotedesc, Qvalue);
				    Registered reg = new Registered(username, "123", "", "", date, "", "", 50);
				    Bet b= reg.addBet(betValue, ques.getQuotes());
				    Quote quo = ques.getQuotes().get(0);
				    quo.addBet(b);
				    Vector<Quote> quotes = new Vector<>();
				    quotes.add(quo);
				    ques.setQuotes(quotes);
				    Vector<Question> questions = new Vector<>();
				    questions.add(ques);
				    ev.setQuestions(questions);
					db.persist(ev);
					db.persist(reg);
					db.getTransaction().commit();
				}
				catch (Exception e){
					e.printStackTrace();
				}
				return ev;
	    }
		
		
		public Bet addBet(String username, double value, Event ev) {
			Bet b=null;
			Event e = db.find(Event.class, ev.getEventNumber());
			Question q = db.find(Question.class, e.getQuestions().get(0).getQuestionNumber());
			Quote quo = db.find(Quote.class, q.getQuotes().get(0).getQuoteNumber());
			Registered user = db.find(Registered.class, username);
			db.getTransaction().begin();
			try {
				b = user.addBet(value, q.getQuotes());
				quo.addBet(b);
			   
				db.persist(e);
				db.persist(user);
				db.getTransaction().commit();
			}
			catch (Exception ex){
				return null;
			}
			return b;
		}
		
		
		
}

