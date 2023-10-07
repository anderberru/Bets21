package businessLogic;
import java.time.LocalDate;
import java.time.ZoneId;
//import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
//import java.time.format.ResolverStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.User;
import domain.Bet;
import domain.Event;
import domain.Message;
import domain.Movement;
import exceptions.*;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;
	static final String INIT = "initialize";
	
	public BLFacadeImplementation()  {		
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals(INIT)) {
		    dbManager=new DataAccess(c.getDataBaseOpenMode().equals(INIT));
		    dbManager.initializeDB();
		    } else
		     dbManager=new DataAccess();
		dbManager.close();

		
	}
	
    public BLFacadeImplementation(DataAccess da)  {
		
		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();
		
		if (c.getDataBaseOpenMode().equals(INIT)) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		dbManager=da;		
	}
	

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
   @WebMethod
   public Question createQuestion(Event event, String question, float betMinimum, Date eventDate) throws EventFinished, QuestionAlreadyExist{
	   
	    //The minimum bed must be greater than 0
		dbManager.open(false);
		Question qry=null;
		
	    
		if(new Date().compareTo(eventDate)>0)
			throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
				
		
		 qry=dbManager.createQuestion(event,question,betMinimum);		

		dbManager.close();
		
		return qry;
   }
   
   /**
	 * This method creates an event, with a number, a description text and the date of the event
	 * 
	 * @param number of the event
	 * @param description text of the event
	 * @param date of the event
	 * @return the created event, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
	 * @throws EventAlreadyExist if the same event already exists
	 */
   @WebMethod
   public Event createEvent(String description,Date eventDate) throws EventAlreadyExist, EventFinished {

	   //The minimum bed must be greater than 0
	   dbManager.open(false);
	   Event qry=null;


	   if(new Date().compareTo(eventDate)>0)
		   throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));


	   qry=dbManager.createEvent(description, eventDate);		

	   dbManager.close();

	   return qry;
   }
  
   /**
	 * This method creates a quote, with a description text, a value and the question of the quote
	 * 
	 * @param description text of the quote
	 * @param value of the quote
	 * @param question of the quote
	 * @return the created quote, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
	 * @throws QuoteAlreadyExist if the same quote already exists for the question
	 */
   	@WebMethod
  	public Quote createQuote(String bet_description, double value, Event event, Question question, Date date) throws  QuoteAlreadyExist, EventFinished {
	
   		if(new Date().compareTo(date)>0)
				throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
   		
  			//The minimum bed must be greater than 0
  			dbManager.open(false);
  			Quote qry=null;
  			
  			 qry=dbManager.createQuote(bet_description, value, event, question);		

  			dbManager.close();
  			
  			return qry;
  	}
	/**
	 * This method invokes the data access to retrieve the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
    @WebMethod	
	public Vector<Event> getEvents(Date date)  {
		dbManager.open(false);
		Vector<Event>  events=dbManager.getEvents(date);
		dbManager.close();
		return events;
	}

    
	/**
	 * This method invokes the data access to retrieve the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date) {
		dbManager.open(false);
		Vector<Date>  dates=dbManager.getEventsMonth(date);
		dbManager.close();
		return dates;
	}
	
	
	public void close() {
		DataAccess dB4oManager=new DataAccess(false);

		dB4oManager.close();

	}
	
	@WebMethod
	public boolean isLogin(String log, String pass) {
		dbManager.open(false);
		boolean ret = dbManager.isLogin(log, pass);
		dbManager.close();
		return ret;
	}
	
	@WebMethod
	public boolean isAdmin(String log, String pass) {
		dbManager.open(false);
		boolean ret = dbManager.isAdmin(log, pass);
		dbManager.close();
		return ret;
	}
	
	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
    @WebMethod	
	 public void initializeBD(){
    	dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}
    
    private Date newDate(int year,int month,int day) {

	     Calendar calendar = Calendar.getInstance();
	     calendar.set(year, month, day,0,0,0);
	     calendar.set(Calendar.MILLISECOND, 0);

	     return calendar.getTime();
	}
    
    @WebMethod
    public void register(String user, String pswd, String name, String dni, String pay, String year, int month, String day, String email, int money) throws IncorrectData, AgeTooLow, NumberFormatException, UserAlreadyExist, java.time.format.DateTimeParseException, com.sun.xml.ws.fault.ServerSOAPFaultException {
    	boolean correctData = isCorrectData(user, pswd, name, dni, pay, year, month, day, email);
    	if (correctData) {
    		Date userDate = newDate(Integer.parseInt(year), month, Integer.parseInt(day));
    		dbManager.open(false);
    		boolean exists = dbManager.doesExistRegistered(user);
    		if (!exists) {
    			dbManager.register(user, pswd, name, dni, pay, userDate, email, money);
    		}
    		dbManager.close();
    		if (exists) {
    			throw new UserAlreadyExist("User already exists");
    		}
    	}
    }
    
    @WebMethod
    public boolean isCorrectData(String user, String pswd, String name, String dni, String pay, String year, int month, String day, String email) throws IncorrectData, AgeTooLow, NumberFormatException, DateTimeParseException, com.sun.xml.ws.fault.ServerSOAPFaultException {
		if (user.isBlank() || pswd.isEmpty() || name.isBlank() || dni.isBlank() || pay.isBlank() || year.isBlank() || month < 1 || month > 12 || day.isBlank() || email.isBlank()) {
			throw new IncorrectData("Null atribute");
		}
		
		int yearInt = Integer.parseInt(year);
		int dayInt = Integer.parseInt(day);
		
		//String dateString = year + "-" + month + "-" + day;
		//LocalDate.parse(dateString, DateTimeFormatter.ofPattern("uuuu-M-d").withResolverStyle(ResolverStyle.STRICT));
		//bug hemen
		
		LocalDate localDate = LocalDate.now();
		ZoneId defaultZoneId = ZoneId.systemDefault();
		Date currentDate = Date.from(localDate.atStartOfDay(defaultZoneId).toInstant());
		Date userDate = newDate(yearInt+18, month, dayInt);
		if (userDate.after(currentDate)) {
			throw new AgeTooLow("Not over 18 year old");
		}
		return true;
	}
    
    @WebMethod
    public String getCurrentMoney(String username) {
    	
    	dbManager.open(false);
	    
		String money = dbManager.getCurrentMoney(username);
		
		dbManager.close();
    	
    	return money;
    }
    
    @WebMethod
    public void deposit(String username, float money) {
    	
    	    	
    	dbManager.open(false);
		
	    dbManager.deposit(username, money);
		
		dbManager.close();
    	
    }
    
    @WebMethod
    public Movement addMovement(float value, String description, String username) {
		dbManager.open(false);
		
		dbManager.addMovement(value, description, username);
		dbManager.close();
    	return null;
    	
    }
    
    @WebMethod
    public Vector<Movement> allMovements(String username){
		
    	dbManager.open(false);
    	Vector<Movement> list = dbManager.allMovements(username);
    	dbManager.close();
    	return list;
    	
    }
    
    @WebMethod
    public Bet addBet(float value, Event event, Set<Question> selectedQuestions, Set<Quote> selectedQuotes, String username, Date date) throws BetOnSameQuote, EventFinished, NotEnoughMoney, LessThanMinBet, EventHasResults {
    	
    	Bet bet = null;
		
		if(event.getResult() != null) {
			throw new EventHasResults();
		}
    	
		if(new Date().compareTo(date)>0)
				throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
		
		for(Question question: selectedQuestions) {
			if (value < question.getBetMinimum()) {
				throw new LessThanMinBet();
			}
		}
    	
    	dbManager.open(false);
    	
    	bet = dbManager.addBet(value, event, selectedQuotes, username, new Vector<Registered>());
		
		dbManager.close();
		
		return bet;
    }
    
    @WebMethod
    public void removeEvent(Event event) {
    	dbManager.open(false);
    	
    	dbManager.removeEvent(event);
    	
    	dbManager.close();
    }

    @WebMethod
    public void putResults(Event evi, String eventResult, Set<Quote> quotes) throws EventNotFinished, EventAlreadyRemoved, EventResultsAlreadyIn, BlankEventResult {
    	
		if (eventResult == null || eventResult.isBlank()) {
			throw new BlankEventResult();
		}
    	
    	dbManager.open(false);
    	
    	dbManager.putResults(evi, eventResult, quotes);
    	
    	dbManager.close();
    }

	@WebMethod
	public Vector<Message> allRecivedMessages(String username) {
		dbManager.open(false);
    	
		Vector<Message> recivedMessages = dbManager.allRecivedMessages(username);
    	
    	dbManager.close();
    	
		return recivedMessages;
	}

	@WebMethod
	public Vector<Message> allSentMessages(String username) {
		dbManager.open(false);
    	
		Vector<Message> sentMessages = dbManager.allSentMessages(username);
    	
    	dbManager.close();
    	
		return sentMessages;
	}
	
	@WebMethod
	public void follow(String currentUser, String followedUser)throws UserDoesNotExist, AlreadyFollower, AlreadyFollowing {
		dbManager.open(false);
		
		dbManager.follow(currentUser, followedUser);
		
		dbManager.close();
	}

	@WebMethod
	public Message sendMessage(String message, String username, String reciever) throws IncorrectReciever, IncorrectData {
		
		dbManager.open(false);
		
		Message m = dbManager.sendMessage(message, username, reciever);
		
		dbManager.close();
		
		return m;
	}
	
	@WebMethod 
	public Vector<Registered> getUsers() {
		
		dbManager.open(false);
		
		Vector<Registered> users = dbManager.getUsers();
		
		dbManager.close();
		
		return users;
	}
	
	@WebMethod 
	public void giveBonus(String bonus, Set<Registered> selectedUsers) throws IncorrectData, UserDoesNotExist {
		
		if (selectedUsers.isEmpty()) throw new UserDoesNotExist();
		
		try {
			
			double value = Double.parseDouble(bonus);
			
			if (value < 0 || value > 1) throw new IncorrectData();
			
			dbManager.open(false);
			
			dbManager.giveBonus(value, selectedUsers);
			
			dbManager.close();
			
		} catch (NullPointerException | NumberFormatException e) {
			throw new IncorrectData();
		}
	}
	
	@WebMethod 
	public Vector<Double> getUserEarningsLastMonth(Registered r) {
			
			dbManager.open(false);
			
			Vector<Double> data = dbManager.getUserEarningsLastMonth(r);
			
			dbManager.close();
			
		return data;
	}
	
	@WebMethod
	public void duplicateEvent(Date date, Event event) throws EventFinished {
		
		if(new Date().compareTo(date)>0)
			   throw new EventFinished(ResourceBundle.getBundle("Etiquetas").getString("ErrorEventHasFinished"));
		
		dbManager.open(false);
		
		dbManager.duplicateEvent(date, event);
		
		dbManager.close();
		
	}
	
}

