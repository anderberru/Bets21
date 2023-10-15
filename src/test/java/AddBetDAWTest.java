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
import domain.Bet;
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
			//fail();
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
	public void test2() {
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
			this.ev = testDA.addEventWithQuestionWithQuoteWithUser(eventText,oneDate,queryText, betMinimum, "kuota1"
					, 2, username, userDate);

			testDA.close();

			quotes.addAll(this.ev.getQuestions().get(0).getQuotes());

			//invoke System Under Test (sut)  

			sut.addBet(52.0, this.ev, quotes, username, new Vector<Registered>());


			//if the program continues fail
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
			boolean b2=testDA.removeUserWithName(username);
			testDA.close();
			System.out.println("Finally "+b+" "+b2);          
		}
	}

	@Test
	public void test3() {
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
			this.ev = testDA.addEventWithQuestionWithQuoteWithUser(eventText,oneDate,queryText, betMinimum, "kuota1"
					, 2, username, userDate);

			testDA.close();

			quotes.addAll(this.ev.getQuestions().get(0).getQuotes());

			//invoke System Under Test (sut)  
			Vector<Registered> alreadyBet = new Vector<>();
			alreadyBet.add(new Registered("user2", "123", "", "", userDate, "", "", 50));
			Bet bet = sut.addBet(52.0, this.ev, quotes, username, alreadyBet);


			//check results
			assertEquals(bet, null);
		} catch (BetOnSameQuote e) {
			// if the program goes to this point OK  
			fail();
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
	public void test4() {
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
			this.ev = testDA.addEventWithQuestionWithQuoteWithUserWithFollowers(eventText,oneDate,queryText, betMinimum, "kuota1"
					, 2, "user3", userDate, "user2");

			testDA.close();

			quotes.addAll(this.ev.getQuestions().get(0).getQuotes());
			Vector<Registered> alreadyBet = new Vector<>();


			//invoke System Under Test (sut)  

			Bet bet = sut.addBet(10.0, this.ev, quotes, "user3", alreadyBet);


			//check results
			assertTrue(bet.getValue()==10.0);
			assertTrue(bet.getQuotes().get(0).getQuoteNumber().equals(this.ev.getQuestions().get(0).getQuotes().get(0).getQuoteNumber()));
			assertTrue(bet.getRegistered().getUserName().equals("user3"));
		} catch (BetOnSameQuote e) {
			// if the program goes to this point OK  
			fail();
		} catch (NotEnoughMoney e) {
			fail();
		} finally {
			//Remove the created objects in the database (cascade removing)   
			testDA.open();
			boolean b=testDA.removeEvent(ev);
			boolean b2=testDA.removeUserWithName("user3");
			testDA.removeUserWithName("user2");
			testDA.close();
			System.out.println("Finally "+b+" "+b2);          
		}
	}

	@Test
	public void test5() {
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
			this.ev = testDA.addEventWithQuestionWithQuoteWithUser(eventText,oneDate,queryText, betMinimum, "kuota1"
					, 2, "user4", userDate);

			testDA.close();

			quotes.addAll(this.ev.getQuestions().get(0).getQuotes());
			Vector<Registered> alreadyBet = new Vector<>();


			//invoke System Under Test (sut)  

			Bet bet = sut.addBet(10.0, this.ev, new HashSet<Quote>(), "user4", alreadyBet);


			//check results
			assertTrue(bet.getValue()==10.0);
			assertTrue(bet.getQuotes().size()==0);
			assertTrue(bet.getRegistered().getUserName().equals("user4"));
		} catch (BetOnSameQuote e) {
			// if the program goes to this point OK  
			fail();
		} catch (NotEnoughMoney e) {
			fail();
		} finally {
			//Remove the created objects in the database (cascade removing)   
			testDA.open();
			boolean b=testDA.removeEvent(ev);
			boolean b2=testDA.removeUserWithName("user4");
			testDA.removeUserWithName("user2");
			testDA.close();
			System.out.println("Finally "+b+" "+b2);          
		}
	}

	@Test
	public void test6() {
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
			this.ev = testDA.addEventWithQuestionWithQuoteWithUser(eventText,oneDate,queryText, betMinimum, "kuota1"
					, 2, "user5", userDate);

			testDA.close();

			quotes.addAll(this.ev.getQuestions().get(0).getQuotes());
			Vector<Registered> alreadyBet = new Vector<>();


			//invoke System Under Test (sut)  

			Bet bet = sut.addBet(10.0, this.ev, quotes, "user5", alreadyBet);


			//check results
			assertTrue(bet.getValue()==10.0);
			assertTrue(bet.getQuotes().get(0).getQuoteNumber().equals(this.ev.getQuestions().get(0).getQuotes().get(0).getQuoteNumber()));
			assertTrue(bet.getRegistered().getUserName().equals("user5"));
		} catch (BetOnSameQuote e) {
			// if the program goes to this point OK  
			fail();
		} catch (NotEnoughMoney e) {
			fail();
		} finally {
			//Remove the created objects in the database (cascade removing)   
			testDA.open();
			boolean b=testDA.removeEvent(ev);
			boolean b2=testDA.removeUserWithName("user5");
			testDA.removeUserWithName("user2");
			testDA.close();
			System.out.println("Finally "+b+" "+b2);          
		}
	}

	@Test
	public void test7() {
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
			this.ev = testDA.addEventWithQuestionWithQuoteWithUserWithFollowers(eventText,oneDate,queryText, betMinimum, "kuota1"
					, 2, "user6", userDate, "user7");

			testDA.close();

			quotes.addAll(this.ev.getQuestions().get(0).getQuotes());
			Vector<Registered> alreadyBet = new Vector<>();


			//invoke System Under Test (sut)  

			Bet bet = sut.addBet(10.0, this.ev, new HashSet<Quote>(), "user6", alreadyBet);


			//check results
			assertTrue(bet.getValue()==10.0);
			assertTrue(bet.getQuotes().size()==0);
			assertTrue(bet.getRegistered().getUserName().equals("user6"));
		} catch (BetOnSameQuote e) {
			// if the program goes to this point OK  
			fail();
		} catch (NotEnoughMoney e) {
			fail();
		} finally {
			//Remove the created objects in the database (cascade removing)   
			testDA.open();
			boolean b=testDA.removeEvent(ev);
			boolean b2=testDA.removeUserWithName("user6");
			testDA.removeUserWithName("user7");
			testDA.close();
			System.out.println("Finally "+b+" "+b2);          
		}
	}

	@Test
	public void test8() {
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
			this.ev = testDA.addEventWithQuestionWithQuoteWithUserWithFollowers(eventText,oneDate,queryText, betMinimum, "kuota1"
					, 2, "user8", userDate, "user9");

			testDA.close();

			quotes.addAll(this.ev.getQuestions().get(0).getQuotes());
			Vector<Registered> alreadyBet = new Vector<>();
			alreadyBet.add(new Registered("user9", "123", "", "", userDate, "", "", 50));

			//invoke System Under Test (sut)  

			Bet bet = sut.addBet(10.0, this.ev, quotes, "user8", alreadyBet);


			//check results
			assertTrue(bet.getValue()==10.0);
			assertTrue(bet.getQuotes().get(0).getQuoteNumber().equals(this.ev.getQuestions().get(0).getQuotes().get(0).getQuoteNumber()));
			assertTrue(bet.getRegistered().getUserName().equals("user8"));
		} catch (BetOnSameQuote e) {
			// if the program goes to this point OK  
			fail();
		} catch (NotEnoughMoney e) {
			fail();
		} finally {
			//Remove the created objects in the database (cascade removing)   
			testDA.open();
			boolean b=testDA.removeEvent(ev);
			boolean b2=testDA.removeUserWithName("user8");
			testDA.removeUserWithName("user9");
			testDA.close();
			System.out.println("Finally "+b+" "+b2);          
		}
	}
	
	@Test
	public void test9() {
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
			this.ev = testDA.addEventWithQuestionWithQuoteWithUserWithFollowers(eventText,oneDate,queryText, betMinimum, "kuota1"
					, 2, "user11", userDate, "user12");

			testDA.close();

			quotes.addAll(this.ev.getQuestions().get(0).getQuotes());
			Vector<Registered> alreadyBet = new Vector<>();
			
			alreadyBet.add(new Registered("user13", "123", "", "", userDate, "", "", 50));

			//invoke System Under Test (sut)  

			Bet bet = sut.addBet(10.0, this.ev, quotes, "user11", alreadyBet);


			//check results
			assertTrue(bet.getValue()==10.0);
			assertTrue(bet.getQuotes().get(0).getQuoteNumber().equals(this.ev.getQuestions().get(0).getQuotes().get(0).getQuoteNumber()));
			assertTrue(bet.getRegistered().getUserName().equals("user11"));
		} catch (BetOnSameQuote e) {
			// if the program goes to this point OK  
			fail();
		} catch (NotEnoughMoney e) {
			fail();
		} finally {
			//Remove the created objects in the database (cascade removing)   
			testDA.open();
			boolean b=testDA.removeEvent(ev);
			boolean b2=testDA.removeUserWithName("user11");
			testDA.removeUserWithName("user12");
			testDA.close();
			System.out.println("Finally "+b+" "+b2);          
		}
	}
}