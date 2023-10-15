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
public class RemoveEventBLBMTest {
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


			sut.removeEvent(mockedEvent);
			
			ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
			
			Mockito.verify(dataAccess,Mockito.times(1)).removeEvent(eventCaptor.capture());
		} catch (Exception e) {
			fail();}
	}
	
	@Test
	public void test2() {
		try {


			sut.removeEvent(null);
			
			ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
			
			Mockito.verify(dataAccess,Mockito.times(1)).removeEvent(eventCaptor.capture());
		} catch (Exception e) {
			assertTrue(true);}
	} 
	
	@Test
	public void test3() {
		try {
			
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			Date oneDate=null;
			
			try {
				oneDate = sdf.parse("09/10/2023");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			Event ev = new Event("d1", oneDate);

			sut.removeEvent(ev);
			
			ArgumentCaptor<Event> eventCaptor = ArgumentCaptor.forClass(Event.class);
			
			Mockito.verify(dataAccess,Mockito.times(1)).removeEvent(eventCaptor.capture());
		} catch (Exception e) {
			assertTrue(true);}
	}
	
	
}