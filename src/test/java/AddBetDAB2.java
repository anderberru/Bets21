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
import dataAccess.DataAccessAddBet;
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

public class AddBetDAB2 {

	 //sut:system under test
	 static DataAccessAddBet sut=new DataAccessAddBet();
	 
	 //additional operations needed to execute the test 
	 static TestDataAccess testDA=new TestDataAccess();

	private Event ev;
	
	/*
	@Test
	public void test1() {
		try {

			//define paramaters
			String eventText="A vs B";
			String queryText="query1";
			Float betMinimum=new Float(2);

			Set<Quote> quotes = new HashSet<>();


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
			this.ev = testDA.addEventWithQuestionWithQuoteWithUserWithBet(eventText,oneDate,queryText, betMinimum, "kuota1"
					, 2, "Ander", 3, userDate);

			testDA.close();

			quotes.addAll(this.ev.getQuestions().get(0).getQuotes());

			//invoke System Under Test (sut)  

			sut.addBet(3.0, this.ev, quotes, "Ander", new Vector<Registered>());


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
			boolean b2=testDA.removeUserWithName("Ander");
			testDA.close();
			System.out.println("Finally "+b+" "+b2);          
		}
	}
	*/
	
	@Test
	public void test2() {
		try {

			//define paramaters
			String eventText="A vs B";
			String queryText="query1";
			Float betMinimum=new Float(2);

			Set<Quote> quotes = new HashSet<>();


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
			this.ev = new Event("A vs F", oneDate);
			
			quotes.addAll(this.ev.getQuestions().get(0).getQuotes());

			//invoke System Under Test (sut)  

			sut.addBet(3.0, this.ev, quotes, "Pepe", new Vector<Registered>());


			//if the program continues fail
			fail();
		} catch (BetOnSameQuote e) {
			// if the program goes to this point OK  
			fail();
		} catch (NotEnoughMoney e) {
			fail();
		} catch (Exception e){
			assertTrue(true);
		} 
	}
	
	
	
	@Test
	public void test3() {
		try {

			//define paramaters
			String eventText="A vs B";
			String queryText="query1";
			Float betMinimum=new Float(2);

			Set<Quote> quotes = new HashSet<>();


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
			this.ev = null;
			
			quotes.addAll(this.ev.getQuestions().get(0).getQuotes());

			//invoke System Under Test (sut)  

			sut.addBet(3.0, this.ev, quotes, "Ander", new Vector<Registered>());


			//if the program continues fail
			fail();
		} catch (BetOnSameQuote e) {
			// if the program goes to this point OK  
			fail();
		} catch (NotEnoughMoney e) {
			fail();
		} catch (Exception e){
			assertTrue(true);
		} 
	}
	
	
	/*
	@Test
	public void test4() {
		try {

			//define paramaters
			String eventText="A vs B";
			String queryText="query1";
			Float betMinimum=new Float(2);

			Set<Quote> quotes = new HashSet<>();


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
			this.ev = testDA.addEventWithQuestionWithQuoteWithUser(eventText,oneDate,queryText, betMinimum, "kuota1"
					, 2, "Lander", userDate);

			testDA.close();

			quotes.addAll(this.ev.getQuestions().get(0).getQuotes());

			//invoke System Under Test (sut)  
			Vector<Registered> alreadyBet = new Vector<>();
			alreadyBet.add(new Registered("fol1", "123", "", "", userDate, "", "", 50));
			Bet bet = sut.addBet(3.0, this.ev, quotes, "Lander", alreadyBet);


			//check results
			assertTrue(bet.getValue()==3.0);
			assertTrue(bet.getQuotes().get(0).getQuoteNumber().equals(this.ev.getQuestions().get(0).getQuotes().get(0).getQuoteNumber()));
			assertTrue(bet.getRegistered().getUserName().equals("Lander"));
			
		} catch (BetOnSameQuote e) {
			// if the program goes to this point OK  
			fail();
		} catch (NotEnoughMoney e) {
			fail();
		} finally {
			//Remove the created objects in the database (cascade removing)   
			testDA.open();
			boolean b=testDA.removeEvent(ev);
			boolean b2=testDA.removeUserWithName("Lander");
			testDA.close();
			System.out.println("Finally "+b+" "+b2);          
		}
	}
	*/
	
	
	@Test
	public void test5() {
		try {

			//define paramaters
			String eventText="A vs B";
			String queryText="query1";
			Float betMinimum=new Float(2);

			Set<Quote> quotes = new HashSet<>();


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
			this.ev = testDA.addEventWithQuestionWithQuoteWithUser(eventText,oneDate,queryText, betMinimum, "kuota1"
					, 2, "Damian", userDate);

			testDA.close();

			quotes.addAll(this.ev.getQuestions().get(0).getQuotes());

			//invoke System Under Test (sut)  
			Vector<Registered> alreadyBet = new Vector<>();
			
			Bet bet = sut.addBet(52.0, this.ev, quotes, "Damian", alreadyBet);


			// if the program goes to this point fail  
			fail();
		} catch (BetOnSameQuote e) {
			// if the program goes to this point OK  
			fail();
		} catch (NotEnoughMoney e) {
			assertTrue(true);
		} finally {
			//Remove the created objects in the database (cascade removing)   
			testDA.open();
			boolean b=testDA.removeEvent(ev);
			boolean b2=testDA.removeUserWithName("Damian");
			testDA.close();
			System.out.println("Finally "+b+" "+b2);          
		}
	}
	
	
	/*
	@Test
	public void testMuga1() {
		try {

			//define paramaters
			String eventText="A vs B";
			String queryText="query1";
			Float betMinimum=new Float(2);

			Set<Quote> quotes = new HashSet<>();


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
			this.ev = testDA.addEventWithQuestionWithQuoteWithUser(eventText,oneDate,queryText, betMinimum, "kuota1"
					, 2, "user50", userDate);

			testDA.close();

			quotes.addAll(this.ev.getQuestions().get(0).getQuotes());

			//invoke System Under Test (sut)  
			Vector<Registered> alreadyBet = new Vector<>();
			alreadyBet.add(new Registered("fol1", "123", "", "", userDate, "", "", 50));
			Bet bet = sut.addBet(50, this.ev, quotes, "user50", alreadyBet);


			//check results
			assertTrue(bet.getValue()==50.0);
			assertTrue(bet.getQuotes().get(0).getQuoteNumber().equals(this.ev.getQuestions().get(0).getQuotes().get(0).getQuoteNumber()));
			assertTrue(bet.getRegistered().getUserName().equals("user50"));
			
		} catch (BetOnSameQuote e) {
			// if the program goes to this point OK  
			fail();
		} catch (NotEnoughMoney e) {
			fail();
		} finally {
			//Remove the created objects in the database (cascade removing)   
			testDA.open();
			boolean b=testDA.removeEvent(ev);
			boolean b2=testDA.removeUserWithName("user50");
			testDA.close();
			System.out.println("Finally "+b+" "+b2);          
		}
	}
	*/
	
	/*
	@Test
	public void testMuga2() {
		try {

			//define paramaters
			String eventText="A vs B";
			String queryText="query1";
			Float betMinimum=new Float(2);

			Set<Quote> quotes = new HashSet<>();


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
			this.ev = testDA.addEventWithQuestionWithQuoteWithUser(eventText,oneDate,queryText, betMinimum, "kuota1"
					, 2, "user49", userDate);

			testDA.close();

			quotes.addAll(this.ev.getQuestions().get(0).getQuotes());

			//invoke System Under Test (sut)  
			Vector<Registered> alreadyBet = new Vector<>();
			alreadyBet.add(new Registered("fol1", "123", "", "", userDate, "", "", 50));
			Bet bet = sut.addBet(49, this.ev, quotes, "user49", alreadyBet);


			//check results
			assertTrue(bet.getValue()==49.0);
			assertTrue(bet.getQuotes().get(0).getQuoteNumber().equals(this.ev.getQuestions().get(0).getQuotes().get(0).getQuoteNumber()));
			assertTrue(bet.getRegistered().getUserName().equals("user49"));
			
		} catch (BetOnSameQuote e) {
			// if the program goes to this point OK  
			fail();
		} catch (NotEnoughMoney e) {
			fail();
		} finally {
			//Remove the created objects in the database (cascade removing)   
			testDA.open();
			boolean b=testDA.removeEvent(ev);
			boolean b2=testDA.removeUserWithName("user49");
			testDA.close();
			System.out.println("Finally "+b+" "+b2);          
		}
	}
	*/
	
	@Test
	public void testMuga3() {
		try {

			//define paramaters
			String eventText="A vs B";
			String queryText="query1";
			Float betMinimum=new Float(2);

			Set<Quote> quotes = new HashSet<>();


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
			this.ev = testDA.addEventWithQuestionWithQuoteWithUser(eventText,oneDate,queryText, betMinimum, "kuota1"
					, 2, "user51", userDate);

			testDA.close();

			quotes.addAll(this.ev.getQuestions().get(0).getQuotes());

			//invoke System Under Test (sut)  
			Vector<Registered> alreadyBet = new Vector<>();
			
			Bet bet = sut.addBet(51, this.ev, quotes, "user51", alreadyBet);


			// if the program goes to this point fail 
			fail();
			
		} catch (BetOnSameQuote e) {
			// if the program goes to this point OK  
			fail();
		} catch (NotEnoughMoney e) {
			assertTrue(true);
		} finally {
			//Remove the created objects in the database (cascade removing)   
			testDA.open();
			boolean b=testDA.removeEvent(ev);
			boolean b2=testDA.removeUserWithName("user51");
			testDA.close();
			System.out.println("Finally "+b+" "+b2);          
		}
	}
	
}

