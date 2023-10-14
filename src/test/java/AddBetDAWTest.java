import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.junit.Test;

import configuration.ConfigXML;
import configuration.UtilDate;
//import dataAccess.DataAccessInterface;
import dataAccess.DataAccess;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import domain.User;
import exceptions.BetOnSameQuote;
import exceptions.EventFinished;
import exceptions.NotEnoughMoney;
import exceptions.QuestionAlreadyExist;
import exceptions.QuoteAlreadyExist;
import test.businessLogic.TestFacadeImplementation;
import test.dataAccess.TestDataAccess;

public class AddBetDAWTest {

	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	private Event ev;
	private String username = "user1";
	private Registered u;
	private Quote quo;
	
	@Test
	//sut.createQuestion:  The event has one question with a queryText. 
	public void test1() {
		try {
			
			//define paramaters
			String eventText="event1";
			String queryText="query1";
			Float betMinimum=new Float(2);
			
			Set<Quote> quotes = new HashSet<>();
			
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;
			Date userDate=null;
			try {
				oneDate = sdf.parse("05/10/2022");
				userDate = sdf.parse("06/11/2002");
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			this.ev = testDA.addEventWithQuestionWithQuoteWithUserWithBet(eventText,oneDate,queryText, betMinimum, "kuota1"
					, 2, username, 10, userDate);
			
			testDA.close();

			quotes.addAll(this.ev.getQuestions().get(0).getQuotes());
			
			//invoke System Under Test (sut)  
			
			sut.addBet(2.0, this.ev, quotes, username, new Vector<Registered>());
			
			
			//if the program continues fail
		    fail();
		   } catch (BetOnSameQuote e) {
			// if the program goes to this point OK  
			   assertTrue(true);
			} catch (NotEnoughMoney e) {
				fail();
			} finally {
				  //Remove the created objects in the database (cascade removing)   
				testDA.open();
		         boolean b=testDA.removeEvent(ev);
		         boolean b2=testDA.removeUserWithName(username);
		          testDA.close();
		         System.out.println("Finally "+b+" "+b2);          
		        }
		   }
	@Test
	//sut.createQuestion:  The event has NOT one question with a queryText. 
	public void test2() {
		try {
			
			//define paramaters
			String eventText="event1";
			String queryText="query1";
			Float betMinimum=new Float(2);
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("05/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEventWithQuestion(eventText,oneDate,"query2", betMinimum);
			testDA.close();			
			
			//invoke System Under Test (sut)  
			Question q=sut.createQuestion(ev, queryText, betMinimum);
			
			
			//verify the results
			assertTrue(q!=null);
			assertEquals(q.getQuestion(),queryText);
			assertEquals(q.getBetMinimum(),betMinimum,0);
			
			//q is DB
			testDA.open();
			boolean exist = testDA.existQuestion(ev,q);
				
			assertTrue(exist);
			testDA.close();
			
		   } catch (QuestionAlreadyExist e) {
			// TODO Auto-generated catch block
			// if the program goes to this point fail  
			fail();
			} finally {
				  //Remove the created objects in the database (cascade removing)   
				testDA.open();
		          boolean b=testDA.removeEvent(ev);
		          testDA.close();
		      //     System.out.println("Finally "+b);          
		        }
		   }
}