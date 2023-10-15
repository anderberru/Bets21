package businessLogic;

import java.util.Vector;

import java.util.Date;

import java.util.Set;

import domain.*;
import exceptions.*;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  

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
	@WebMethod Question createQuestion(Event event, String question, float betMinimum, Date date) throws EventFinished, QuestionAlreadyExist;
	
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
	   @WebMethod public Event createEvent(String description,Date eventDate) throws EventAlreadyExist, EventFinished;
	
	   
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
	   	@WebMethod public Quote createQuote(String betDescription, double value, Event event, Question question, Date date) throws  QuoteAlreadyExist, EventFinished;
	   
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	
	/**
	 * This method calls the data access to analyze if user and password are already logged.
	 * @param log userName
	 * @param pass password
	 * @return true if is loged, false if not
	 */	
	@WebMethod public boolean isLogin(String log, String pass);
	
	/**
	 * This method returns if userName and password are assigned to an admin user.
	 * @param log userName
	 * @param pass password
	 * @return true if is admin, false if not
	 */	
	@WebMethod public boolean isAdmin(String log, String pass);
	
	/**
	 * This method calls the data access to add a registered user in the database.
	 * @param userName user's name
	 * @param pass user's password
	 * @param fullName user's full name
	 * @param DNI user's DNI
	 * @param payMethod user's pay method
	 * @param year user's birth year
	 * @param month user's birth month
	 * @param day user's birth day
	 * @param email user's email
	 * @throws IncorrectData if data is wrongly entered
	 * @throws AgeTooLow if user is too young
	 * @throws NumberFormatException if year or day are not an parseable to integer.
	 */	
	@WebMethod public void register(String username, String pass, String fullName, String DNI, String payMethod, String year, int month, String day, String email, int money)  throws IncorrectData, AgeTooLow, NumberFormatException, UserAlreadyExist, java.time.format.DateTimeParseException, com.sun.xml.ws.fault.ServerSOAPFaultException;
	
	@WebMethod public String getCurrentMoney(String username);
	
	@WebMethod public Movement addMovement(float value, String description, String username);
	
	/**
	 * This method updates the current money of the registered.
	 * @param money insert money
	 */	
	@WebMethod public void deposit(String username, float money) throws IncorrectData;
	
	@WebMethod public Vector<Movement> allMovements(String username);
	
	@WebMethod public Bet addBet(float value, Event event, Set<Question> selectedQuestions, Set<Quote> selectedQuotes, String username, Date date) throws BetOnSameQuote, EventFinished, NotEnoughMoney, LessThanMinBet, EventHasResults;

	@WebMethod public void removeEvent(Event event);
	
	@WebMethod public void putResults(Event evi, String eventResult, Set<Quote> quotes) throws EventNotFinished, EventAlreadyRemoved, EventResultsAlreadyIn, BlankEventResult;

	@WebMethod public Vector<Message> allRecivedMessages(String username);

	@WebMethod public Vector<Message> allSentMessages(String username);
	
	@WebMethod public void follow(String currentUser, String followedUser) throws UserDoesNotExist, AlreadyFollower, AlreadyFollowing;

	@WebMethod public Message sendMessage(String message, String username, String reciever) throws IncorrectReciever, IncorrectData;
	
	@WebMethod public Vector<Registered> getUsers();
	
	@WebMethod public void giveBonus(String bonus, Set<Registered> selectedUsers) throws IncorrectData, UserDoesNotExist;

	@WebMethod public Vector<Double> getUserEarningsLastMonth(Registered r);
	
	@WebMethod public void duplicateEvent(Date date, Event event) throws EventFinished;
	
}
