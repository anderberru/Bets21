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
//import dataAccess.DataAccessInterface;
import dataAccess.DataAccess;
import domain.Bet;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import exceptions.BetOnSameQuote;
import exceptions.EventFinished;
import exceptions.NotEnoughMoney;
import exceptions.QuestionAlreadyExist;
import test.businessLogic.TestFacadeImplementation;
import test.dataAccess.TestDataAccess;

public class RemoveEventDABTest {

	 //sut:system under test
	 static DataAccess sut=new DataAccess();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	private Event ev;
	
	@Test
	public void test1() {
		try {

			//define paramaters
			Event ev;
			String eventText="A vs B";


			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;
			Date userDate=null;
			try {
				oneDate = sdf.parse("09/10/2023");
				userDate = sdf.parse("06/11/2002");

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEvent(eventText,oneDate);

			testDA.close();


			//invoke System Under Test (sut)  

			sut.removeEvent(ev);

			if(sut.getEvents(userDate).contains(ev)) fail();
			else assertTrue(true);
			//if the program continues fail
			//fail();
		} catch (Exception e) {
			fail();}
	}
	
	@Test
	public void test2() {
		try {

			//define paramaters
			Event ev;
			String eventText="A vs B";
			String quText="question1";
			Float qty=new Float(2);



			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;
			Date userDate=null;
			try {
				oneDate = sdf.parse("09/10/2023");
				userDate = sdf.parse("06/11/2002");

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEventWithQuestion(eventText,oneDate, quText, qty);

			testDA.close();


			//invoke System Under Test (sut)  

			sut.removeEvent(ev);

			if(sut.getEvents(userDate).contains(ev)) fail();
			else assertTrue(true);
			//if the program continues fail
			//fail();
		} catch (Exception e) {
			fail();}
	}
	
	@Test
	public void test3() {
		try {

			//define paramaters
			Event ev;
			String eventText="A vs B";
			String quText="question1";
			Float qty=new Float(2);
			Quote q1 = new Quote("quote1", 5);


			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;
			Date userDate=null;
			try {
				oneDate = sdf.parse("09/10/2023");
				userDate = sdf.parse("06/11/2002");

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEventWithQuestionWithQuote(eventText,oneDate, quText, qty, q1);

			testDA.close();


			//invoke System Under Test (sut)  

			sut.removeEvent(ev);

			if(sut.getEvents(userDate).contains(ev)) fail();
			else assertTrue(true);
			//if the program continues fail
			//fail();
		} catch (Exception e) {
			fail();}
	}
	
	@Test
	public void test4() {
		try {

			//define paramaters
			Event ev;
			String eventText="A vs B";
			String quText="question1";
			String user = "Damian";
			Float qty=new Float(2);
			Quote q1 = new Quote("quote1", 5);


			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;
			Date userDate=null;
			try {
				oneDate = sdf.parse("09/10/2023");
				userDate = sdf.parse("06/11/2002");

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			//configure the state of the system (create object in the dabatase)
			testDA.open();
			ev = testDA.addEventWithQuestionWithQuoteWithUserWithBet(eventText, userDate, quText, qty, "quote1", 5, user, 5, oneDate);

			testDA.close();


			//invoke System Under Test (sut)  

			sut.removeEvent(ev);

			if(sut.getEvents(userDate).contains(ev)) fail();
			else assertTrue(true);
			//if the program continues fail
			//fail();
		} catch (Exception e) {
			fail();}
	}
	
	@Test
	public void test5() {
		try {

			//define paramaters
			Event ev;
			String eventText="A vs B";


			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;
			Date userDate=null;
			try {
				oneDate = sdf.parse("09/10/2023");
				userDate = sdf.parse("06/11/2002");

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			//configure the state of the system (create object in the dabatase)
			testDA.open();
			this.ev = testDA.addEvent(eventText,oneDate);
			testDA.close();


			//invoke System Under Test (sut)  

			sut.removeEvent(null);

			fail();
		} catch (Exception e) {
			assertTrue(true);}
		finally {
			testDA.removeEvent(ev);
		}
	}
	
	@Test
	public void test6() {
		try {

			//define paramaters
			Event ev;
			String eventText="A vs B";


			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;
			Date userDate=null;
			try {
				oneDate = sdf.parse("09/10/2023");
				userDate = sdf.parse("06/11/2002");

			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			//configure the state of the system (create object in the dabatase)
			ev = new Event(eventText, oneDate);

			//invoke System Under Test (sut)  

			sut.removeEvent(ev);

			fail();
		} catch (Exception e) {
			assertTrue(true);}
	}
	
}

