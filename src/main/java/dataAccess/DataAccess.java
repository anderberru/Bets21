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
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;

	String s1 = "¿Quién ganará el partido?";
	
	ConfigXML c=ConfigXML.getInstance();

     public DataAccess(boolean initializeMode)  {
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);
		
	}

	public DataAccess()  {	
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
	    
			Event ev1=new Event(1, "Atlético-Athletic", UtilDate.newDate(year,month,17));
			Event ev2=new Event(2, "Eibar-Barcelona", UtilDate.newDate(year,month,17));
			Event ev3=new Event(3, "Getafe-Celta", UtilDate.newDate(year,month,17));
			Event ev4=new Event(4, "Alavés-Deportivo", UtilDate.newDate(year,month,17));
			Event ev5=new Event(5, "Español-Villareal", UtilDate.newDate(year,month,17));
			Event ev6=new Event(6, "Las Palmas-Sevilla", UtilDate.newDate(year,month,17));
			Event ev7=new Event(7, "Malaga-Valencia", UtilDate.newDate(year,month,17));
			Event ev8=new Event(8, "Girona-Leganés", UtilDate.newDate(year,month,17));
			Event ev9=new Event(9, "Real Sociedad-Levante", UtilDate.newDate(year,month,17));
			Event ev10=new Event(10, "Betis-Real Madrid", UtilDate.newDate(year,month,17));

			Event ev11=new Event(11, "Atletico-Athletic", UtilDate.newDate(year,month,1));
			Event ev12=new Event(12, "Eibar-Barcelona", UtilDate.newDate(year,month,1));
			Event ev13=new Event(13, "Getafe-Celta", UtilDate.newDate(year,month,1));
			Event ev14=new Event(14, "Alavés-Deportivo", UtilDate.newDate(year,month,1));
			Event ev15=new Event(15, "Español-Villareal", UtilDate.newDate(year,month,1));
			Event ev16=new Event(16, "Las Palmas-Sevilla", UtilDate.newDate(year,month,1));
			

			Event ev17=new Event(17, "Málaga-Valencia", UtilDate.newDate(year,month+1,28));
			Event ev18=new Event(18, "Girona-Leganés", UtilDate.newDate(year,month+1,28));
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
				q2=ev1.addQuestion("¿Quién meterá el primer gol?",2);
				q3=ev11.addQuestion(s1,1);
				q4=ev11.addQuestion("¿Cuántos goles se marcarán?",2);
				q5=ev17.addQuestion(s1,1);
				q6=ev17.addQuestion("¿Habrá goles en la primera parte?",2);
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
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		
			Event ev = db.find(Event.class, event.getEventNumber());
			
			if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("QueryAlreadyExist"));
			
			db.getTransaction().begin();
			Question q = ev.addQuestion(question, betMinimum);
			//db.persist(q);
			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return q;
		
	}
	
	/**
	 * This method creates an event, with a number, a description text and the date of the event
	 * 
	 * @param number of the event
	 * @param description text of the event
	 * @param date of the event
	 * @return the created event, or null, or an exception
	 * @throws EventAlreadyExist if the same event already exists
	 */
	public Event createEvent(String description,Date eventDate) throws EventAlreadyExist {
		
		Event ev = new Event(description, eventDate);
		Vector<Event> events = this.getEvents(eventDate);
		
		if (events.contains(new Event(description, eventDate))) throw new EventAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventAlreadyExist"));
		
		db.getTransaction().begin();
		
		db.persist(ev); 
		db.getTransaction().commit();
		return ev;
	}
	
	/**
	 * This method creates a quote, with a description text, a value and the question of the quote
	 * 
	 * @param description text of the quote
	 * @param value of the quote
	 * @param question of the quote
	 * @return the created quote, or null, or an exception
	 * @throws QuoteAlreadyExist if the same quote already exists for the question
	 */
	public Quote createQuote(String bet_description, double value, Event event, Question question) throws  QuoteAlreadyExist {
		
			Event ev = db.find(Event.class, event.getEventNumber());
			Question ques = db.find(Question.class, question.getQuestionNumber());
			
			
			if (ques.DoesQuoteExists(bet_description, value)) throw new QuoteAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQuoteAlreadyExist"));
			
			db.getTransaction().begin();
						
			Quote q = ques.addQuote(bet_description, value);

			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return q;
		
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
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
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
	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.DoesQuestionExists(question);
	
	}


	public boolean isLogin(String log, String pass) {
		User user =db.find(User.class, log);
		return user != null && user.isCorrectPassword(pass);
	}
	
	public boolean isAdmin(String log, String pass) {
		User user = db.find(User.class, log);
		return (user instanceof Admin);
	}
	
	public boolean doesExistRegistered(String userName) {
		db.getTransaction().begin();
		User user = db.find(User.class, userName);
		db.getTransaction().commit();
		if (user == null) {
			System.out.println(userName + " user does not exist yet");
			return false;
		}
		else {
			System.out.println(userName + " user already exists");
			return true;
		}
	}
	
	public boolean register(String username, String pass, String fullname, String DNI, String payMethod, Date date, String email, int money) {
		try {
			User register;
			db.getTransaction().begin();
			
			register = new Registered(username, pass, fullname, DNI, date, payMethod, email, money);
			
			db.persist(register);
			db.getTransaction().commit();
			System.out.println("Gordeta " + register.getUserName());
			return true;
		} catch (Exception e) {
			return false;
		}
	}
	
	public String getCurrentMoney(String username) {
		
		
		Registered user = db.find(Registered.class, username);
		
		String currentMoney = Double.toString(user.getMoney());
		
		return currentMoney;	

	}
	
	public void deposit(String username, float money) {
		try {

			db.getTransaction().begin();
			Registered user = db.find(Registered.class, username);

			
			user.setMoney(user.getMoney() + money);
			db.getTransaction().commit();

		} catch (Exception e) {

		} 

	}
	
	public void addMovement(float value, String description, String username) {
		
		Registered user = db.find(Registered.class, username);
		db.getTransaction().begin();
		user.addMovement(value, description);
		db.persist(user);
		db.getTransaction().commit();
	}
	
	public Vector<Movement> allMovements(String username) {
		Registered user = db.find(Registered.class, username);
		
		Vector<Movement> list = user.getMovements();

		return list;
		
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
	
	public void putResults(Event evi, String eventResult, Set<Quote> selectedQuotes) throws EventAlreadyRemoved, EventResultsAlreadyIn {
		Event ev=db.find(Event.class, evi.getEventNumber());
		
		if (ev == null) {
			throw new EventAlreadyRemoved();
		} else if (ev.getResult() != null) {
			throw new EventResultsAlreadyIn();
		}
		
		db.getTransaction().begin();
		
		ev.setResult(eventResult);
		
		Vector<Quote> selected = new Vector<Quote>();
		selected.addAll(selectedQuotes);
		
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
					
					if (selected.contains(quote)) {
						double betValue = bet.getValue();
						user.setMoney(user.getMoney() + betValue*quote.getValue() + betValue*user.getBonus());
						user.addMovement(bet.getValue()*quote.getValue(), "Won bet: +"+bet.getValue()*quote.getValue()*user.getBonus());
					} else {
						user.addMovement(0, "Lost bet");
					}

					user.removeBet(bet);
					
					
					db.persist(user);
				}
				quote.removeAllBets();
			}
		}
		
		db.getTransaction().commit();
	}
	
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}

	public Vector<Message> allRecivedMessages(String username) {
		User user = db.find(User.class, username);
		Vector<Message> list = user.getRecivedMessages();
		return list;
	
	}

	public Vector<Message> allSentMessages(String username) {
		User user = db.find(User.class, username);
		Vector<Message> list = user.getSentMessages();
		return list;
	}

	public void follow(String currentUser, String followedUser) throws UserDoesNotExist, AlreadyFollower, AlreadyFollowing {
		
		Registered reg=db.find(Registered.class, currentUser);
		Registered selected=db.find(Registered.class, followedUser);
		
		if(selected==null || currentUser.equals(followedUser)) {throw new UserDoesNotExist();}
		
		db.getTransaction().begin();
		
		if(!selected.isFollower(reg)) {
			selected.addFollower(reg);
			db.persist(selected);
		} else {throw new AlreadyFollower();}
		
		if(!reg.isFollowing(selected)) {
			reg.addFollowing(selected);
			db.persist(reg);
		} else {throw new AlreadyFollowing();}
		
		db.getTransaction().commit();
		
	}

	public Message sendMessage(String message, String username, String reciever) throws IncorrectReciever, IncorrectData {
		
		User from=db.find(User.class, username);
		User to=db.find(User.class, reciever);
		
		if(from instanceof Admin != to instanceof Registered || to == null) throw new IncorrectReciever();
		

		db.getTransaction().begin();
		
		Message m = from.addMessageSent(message,from.getUserName(),to.getUserName());
		to.addMessageRecieved(m);
		//db.persist(m);
		db.persist(from);
		db.persist(to);
		db.getTransaction().commit();
		return m;
	}
	
	public Vector<Registered> getUsers() {
		Vector<Registered> itzuli = new Vector<Registered>();	
		TypedQuery<Registered> query = db.createQuery("SELECT r FROM Registered r", Registered.class);   
		List<Registered> rs = query.getResultList();
		
	 	 for (Registered r: rs){
	 	   System.out.println(r.getUserName());		 
		   itzuli.add(r);
		  }
	 	 
	 	return itzuli;  
	}
	
	public void giveBonus(double bonus, Set<Registered> selectedUsers) {
		
		db.getTransaction().begin();
		
		for (Registered r: selectedUsers) {
			Registered reg=db.find(Registered.class, r.getUserName());
			reg.setBonus(bonus);
			db.persist(reg);
		}
		
		db.getTransaction().commit();
	}
	
	public Vector<Double> getUserEarningsLastMonth(Registered r) {
		Vector<Double> itzuli = new Vector<Double>();	
		
		/*Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   month+=1;
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;} 
		   UtilDate.newDate(year,month,1);*/
		   
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -30);
        Double d = 0.0;
		for (int i = 0; i < 30; i++){
			cal.add(Calendar.DATE, 1); 
			Date date = UtilDate.newDate(cal.get(Calendar.YEAR),cal.get(Calendar.MONTH),cal.get(Calendar.DATE));
			//System.out.println(date);
			TypedQuery<Double> query = db.createQuery("SELECT SUM(m.value) FROM Movement m WHERE m.date=?1 AND m.registered = ?2 AND m.description NOT LIKE 'Deposit%' GROUP BY m.date", Double.class);
			query.setParameter(1, date);
			query.setParameter(2, r);
			if (!query.getResultList().isEmpty()) d += query.getResultList().get(0);
			System.out.println(d + ", "); 
			itzuli.add(d);
		}
		
		return itzuli;
	}
	
	public void duplicateEvent(Date date, Event event) {

		Event ev=db.find(Event.class, event.getEventNumber());


		db.getTransaction().begin();

		Event newEv = new Event(ev.getDescription(), date);
		newEv.setResult(ev.getResult());
		Vector<Question> newQues = ev.duplicateQuestions(newEv);
		newEv.setQuestions(newQues);

		db.persist(newEv);

		db.getTransaction().commit();
	}
	
}
