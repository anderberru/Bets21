import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import org.junit.Test;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Bet;
import domain.Event;
import domain.Question;
import domain.Quote;
import domain.Registered;
import exceptions.BetOnSameQuote;
import exceptions.EventFinished;
import exceptions.EventHasResults;
import exceptions.LessThanMinBet;
import exceptions.NotEnoughMoney;
import exceptions.QuestionAlreadyExist;

import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mockito;

import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class AddBetBLBM {
	DataAccess dataAccess=Mockito.mock(DataAccess.class);
	Event mockedEvent=Mockito.mock(Event.class);
	Question mockedQuestion=Mockito.mock(Question.class);
	Quote mockedQuote=Mockito.mock(Quote.class);

	@InjectMocks
	BLFacade sut=new BLFacadeImplementation(dataAccess);

	//sut.createQuestion:  The event has one question with a queryText. 


	@Test
	public void test1() {
		try {
			//define paramaters
			String queryText="proba galdera";
			Float betMinimum=new Float(2);
			String username = "Ander";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Set<Quote> quotes = new HashSet<>();
			Set<Question> questions = new HashSet<>();
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("09/10/2023");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			//configure Mock
			Mockito.doReturn("A irabazi du").when(mockedEvent).getResult();
			

			//invoke System Under Test (sut) 
			sut.addBet(50, mockedEvent, questions, quotes, username, oneDate);

			//verify the results
		
			ArgumentCaptor<Float> valueCaptor = ArgumentCaptor.forClass(Float.class);
			ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
			ArgumentCaptor<String> usernameCaptor = ArgumentCaptor.forClass(String.class);
			

			Mockito.verify(dataAccess,Mockito.times(0)).addBet(valueCaptor.capture(), eventCaptor.capture(), quotes, usernameCaptor.capture(), Mockito.any());
			
			
		

		} catch (EventHasResults e) {
			assertTrue(true);
		} catch (EventFinished e) {
			fail();
		} catch (LessThanMinBet e) {
			fail();
		} catch (NotEnoughMoney e) {
			fail();
		} catch (BetOnSameQuote e) {
			fail();
		}
	}
	
	@Test
	public void test2() {
		try {
			//define paramaters
			String queryText="proba galdera";
			Float betMinimum=new Float(2);
			String username = "Ander";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Set<Quote> quotes = new HashSet<>();
			Set<Question> questions = new HashSet<>();
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("09/10/2023");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			//configure Mock
			
			

			//invoke System Under Test (sut) 
			sut.addBet(50, null, questions, quotes, username, oneDate);

			//verify the results
		
			ArgumentCaptor<Float> valueCaptor = ArgumentCaptor.forClass(Float.class);
			ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
			ArgumentCaptor<String> usernameCaptor = ArgumentCaptor.forClass(String.class);
			

			Mockito.verify(dataAccess,Mockito.times(0)).addBet(valueCaptor.capture(), eventCaptor.capture(), quotes, usernameCaptor.capture(), Mockito.any());
			
			
		

		} catch (EventHasResults e) {
			fail();
		} catch (EventFinished e) {
			fail();
		} catch (LessThanMinBet e) {
			fail();
		} catch (NotEnoughMoney e) {
			fail();
		} catch (BetOnSameQuote e) {
			fail();
		} catch (Exception e) {
			assertTrue(true);
		}
	}


	@Test
	public void test3() {
		try {
			//define paramaters
			String queryText="proba galdera";
			Float betMinimum=new Float(2);
			String username = "Ander";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Set<Quote> quotes = new HashSet<>();
			Set<Question> questions = new HashSet<>();
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("09/10/2022");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			//configure Mock
			Mockito.doReturn(null).when(mockedEvent).getResult();
			//Mockito.doReturn(oneDate).when(mockedEvent).getEventDate();
			

			//invoke System Under Test (sut) 
			sut.addBet(50, mockedEvent, questions, quotes, username, oneDate);

			//verify the results
		
			ArgumentCaptor<Float> valueCaptor = ArgumentCaptor.forClass(Float.class);
			ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
			ArgumentCaptor<String> usernameCaptor = ArgumentCaptor.forClass(String.class);
			

			Mockito.verify(dataAccess,Mockito.times(0)).addBet(valueCaptor.capture(), eventCaptor.capture(), quotes, usernameCaptor.capture(), Mockito.any());
			
			
		

		} catch (EventHasResults e) {
			fail();
		} catch (EventFinished e) {
			assertTrue(true);
		} catch (LessThanMinBet e) {
			fail();
		} catch (NotEnoughMoney e) {
			fail();
		} catch (BetOnSameQuote e) {
			fail();
		}
	}
	
	@Test
	public void test4() {
		try {
			//define paramaters
			String queryText="proba galdera";
			Float betMinimum=new Float(2);
			String username = "Ander";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Set<Quote> quotes = new HashSet<>();
			Set<Question> questions = new HashSet<>();
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("09/10/2024");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			//configure Mock
			Mockito.doReturn(null).when(mockedEvent).getResult();
			Mockito.doReturn(new Float(60)).when(mockedQuestion).getBetMinimum();
			//Mockito.doReturn(oneDate).when(mockedEvent).getEventDate();
			questions.add(mockedQuestion);

			//invoke System Under Test (sut) 
			sut.addBet(50, mockedEvent, questions, quotes, username, oneDate);

			//verify the results
		
			ArgumentCaptor<Float> valueCaptor = ArgumentCaptor.forClass(Float.class);
			ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
			ArgumentCaptor<String> usernameCaptor = ArgumentCaptor.forClass(String.class);
			

			Mockito.verify(dataAccess,Mockito.times(0)).addBet(valueCaptor.capture(), eventCaptor.capture(), quotes, usernameCaptor.capture(), Mockito.any());
			
			
		

		} catch (EventHasResults e) {
			fail();
		} catch (EventFinished e) {
			fail();
		} catch (LessThanMinBet e) {
			assertTrue(true);
		} catch (NotEnoughMoney e) {
			fail();
		} catch (BetOnSameQuote e) {
			fail();
		}
	}
	
	@Test
	public void test5() {
		try {
			//define paramaters
			String username = "Ander";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Set<Quote> quotes = new HashSet<>();
			Set<Question> questions = new HashSet<>();
			Vector<Quote> vquotes = new Vector<>();
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("09/10/2024");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			Registered reg = new Registered("Ander", "123", null, null, null, null, null, 100);
			//configure Mock
			Mockito.doReturn(null).when(mockedEvent).getResult();
			Mockito.doReturn(new Float(1)).when(mockedQuestion).getBetMinimum();
			Mockito.doReturn("kuota").when(mockedQuote).getBet_description();
			//Mockito.doReturn(oneDate).when(mockedEvent).getEventDate();
			quotes.add(mockedQuote);
			vquotes.add(mockedQuote);
			Bet returnedBet = new Bet(50, vquotes, reg);
			Mockito.doReturn(returnedBet).when(dataAccess).addBet(50, mockedEvent, quotes, username, new Vector<Registered>());
			questions.add(mockedQuestion);
			quotes.add(mockedQuote);
			vquotes.add(mockedQuote);
			//invoke System Under Test (sut) 
			Bet b = sut.addBet(50, mockedEvent, questions, quotes, username, oneDate);

			//verify the results
		
			ArgumentCaptor<Float> valueCaptor = ArgumentCaptor.forClass(Float.class);
			ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
			ArgumentCaptor<String> usernameCaptor = ArgumentCaptor.forClass(String.class);
			

			Mockito.verify(dataAccess,Mockito.times(1)).addBet(valueCaptor.capture(), eventCaptor.capture(), Mockito.any(), usernameCaptor.capture(), Mockito.any());
			
			assertTrue(b.getValue()==returnedBet.getValue());
			assertTrue(b.getQuotes().get(0).getBet_description().equals(returnedBet.getQuotes().get(0).getBet_description()));
			assertTrue(b.getRegistered().getUserName().equals(returnedBet.getRegistered().getUserName()));

		} catch (EventHasResults e) {
			fail();
		} catch (EventFinished e) {
			fail();
		} catch (LessThanMinBet e) {
			fail();
		} catch (NotEnoughMoney e) {
			fail();
		} catch (BetOnSameQuote e) {
			fail();
		}
	}
	
	@Test
	public void test6() {
		try {
			//define paramaters
			String username = "Ander";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Set<Quote> quotes = new HashSet<>();
			Set<Question> questions = new HashSet<>();
			Vector<Quote> vquotes = new Vector<>();
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("09/10/2024");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			Registered reg = new Registered("Ander", "123", null, null, null, null, null, 100);
			//configure Mock
			Mockito.doReturn(null).when(mockedEvent).getResult();
			Mockito.doReturn(new Float(1)).when(mockedQuestion).getBetMinimum();
		
			quotes.add(mockedQuote);
			vquotes.add(mockedQuote);
			Bet returnedBet = new Bet(50, vquotes, reg);
			Mockito.when((dataAccess).addBet(50, mockedEvent, quotes, username, new Vector<Registered>())).thenThrow(new BetOnSameQuote(" "){});
			questions.add(mockedQuestion);
			quotes.add(mockedQuote);
			vquotes.add(mockedQuote);
			//invoke System Under Test (sut) 
			Bet b = sut.addBet(50, mockedEvent, questions, quotes, username, oneDate);

			//verify the results
		
			ArgumentCaptor<Float> valueCaptor = ArgumentCaptor.forClass(Float.class);
			ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
			ArgumentCaptor<String> usernameCaptor = ArgumentCaptor.forClass(String.class);
			

			Mockito.verify(dataAccess,Mockito.times(1)).addBet(valueCaptor.capture(), eventCaptor.capture(), Mockito.any(), usernameCaptor.capture(), Mockito.any());
			
			assertTrue(b.getValue()==returnedBet.getValue());
			assertTrue(b.getQuotes().get(0).getBet_description().equals(returnedBet.getQuotes().get(0).getBet_description()));
			assertTrue(b.getRegistered().getUserName().equals(returnedBet.getRegistered().getUserName()));

		} catch (EventHasResults e) {
			fail();
		} catch (EventFinished e) {
			fail();
		} catch (LessThanMinBet e) {
			fail();
		} catch (NotEnoughMoney e) {
			fail();
		} catch (BetOnSameQuote e) {
			assertTrue(true);
		}
	}
	
	@Test
	public void test7() {
		try {
			//define paramaters
			String username = "Ander";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Set<Quote> quotes = new HashSet<>();
			Set<Question> questions = new HashSet<>();
			Vector<Quote> vquotes = new Vector<>();
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("09/10/2024");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			Registered reg = new Registered("Ander", "123", null, null, null, null, null, 100);
			//configure Mock
			Mockito.doReturn(null).when(mockedEvent).getResult();
			Mockito.doReturn(new Float(1)).when(mockedQuestion).getBetMinimum();
		
			quotes.add(mockedQuote);
			vquotes.add(mockedQuote);
			Bet returnedBet = new Bet(50, vquotes, reg);
			Mockito.when((dataAccess).addBet(50, mockedEvent, quotes, username, new Vector<Registered>())).thenThrow(new NotEnoughMoney(" "){});
			questions.add(mockedQuestion);
			quotes.add(mockedQuote);
			vquotes.add(mockedQuote);
			//invoke System Under Test (sut) 
			Bet b = sut.addBet(50, mockedEvent, questions, quotes, username, oneDate);

			//verify the results
		
			ArgumentCaptor<Float> valueCaptor = ArgumentCaptor.forClass(Float.class);
			ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
			ArgumentCaptor<String> usernameCaptor = ArgumentCaptor.forClass(String.class);
			

			Mockito.verify(dataAccess,Mockito.times(1)).addBet(valueCaptor.capture(), eventCaptor.capture(), Mockito.any(), usernameCaptor.capture(), Mockito.any());
			
			assertTrue(b.getValue()==returnedBet.getValue());
			assertTrue(b.getQuotes().get(0).getBet_description().equals(returnedBet.getQuotes().get(0).getBet_description()));
			assertTrue(b.getRegistered().getUserName().equals(returnedBet.getRegistered().getUserName()));

		} catch (EventHasResults e) {
			fail();
		} catch (EventFinished e) {
			fail();
		} catch (LessThanMinBet e) {
			fail();
		} catch (NotEnoughMoney e) {
			assertTrue(true);
		} catch (BetOnSameQuote e) {
			fail();
		}
	}
	
	@Test
	public void test8() {
		try {
			//define paramaters
			String username = "Ander";
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Set<Quote> quotes = new HashSet<>();
			Set<Question> questions = new HashSet<>();
			Vector<Quote> vquotes = new Vector<>();
			Date oneDate=null;;
			try {
				oneDate = sdf.parse("09/10/2024");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	

			Registered reg = new Registered("Ander", "123", null, null, null, null, null, 100);
			//configure Mock
			Mockito.doReturn(null).when(mockedEvent).getResult();
			Mockito.doReturn(new Float(1)).when(mockedQuestion).getBetMinimum();
		
			quotes.add(mockedQuote);
			vquotes.add(mockedQuote);
			
			Mockito.doReturn(null).when(dataAccess).addBet(50, mockedEvent, quotes, username, new Vector<Registered>());
			questions.add(mockedQuestion);
			quotes.add(mockedQuote);
			vquotes.add(mockedQuote);
			//invoke System Under Test (sut) 
			Bet b = sut.addBet(50, mockedEvent, questions, quotes, username, oneDate);

			//verify the results
		
			ArgumentCaptor<Float> valueCaptor = ArgumentCaptor.forClass(Float.class);
			ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
			ArgumentCaptor<String> usernameCaptor = ArgumentCaptor.forClass(String.class);
			

			Mockito.verify(dataAccess,Mockito.times(1)).addBet(valueCaptor.capture(), eventCaptor.capture(), Mockito.any(), usernameCaptor.capture(), Mockito.any());
			
			assertEquals(b, null);
		} catch (EventHasResults e) {
			fail();
		} catch (EventFinished e) {
			fail();
		} catch (LessThanMinBet e) {
			fail();
		} catch (NotEnoughMoney e) {
			fail();
		} catch (BetOnSameQuote e) {
			fail();
		}
	}
}
