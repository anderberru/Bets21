import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import dataAccess.DataAccess;
import domain.Registered;
import exceptions.AlreadyFollower;
import exceptions.AlreadyFollowing;
import exceptions.UserDoesNotExist;

@RunWith(MockitoJUnitRunner.class)
public class FollowBLBMTest {

	DataAccess dataAccess=Mockito.mock(DataAccess.class);
	
	@InjectMocks
	BLFacade sut=new BLFacadeImplementation(dataAccess);
	
	@Test
	public void test1() {
		Date date=null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			date = sdf.parse("05/10/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dataAccess.register("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		dataAccess.register("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		
		
		try {
			dataAccess.follow("Paco", "Pepe");
			Vector<Registered> users = dataAccess.getUsers();
			Mockito.verify(dataAccess).follow("Paco", "Pepe");
			Mockito.verify(dataAccess).getUsers();
			
			Boolean x;
			if(users.get(0).isFollower(users.get(1)) && users.get(1).isFollowing(users.get(0))) {
				x=true;
			}else {
				x=false;
			}
			
			assertTrue(x);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail();
			e.printStackTrace();
		} 
		
	}
	
	
	@Test
	public void test2() {
		Date date=null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			date = sdf.parse("05/10/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dataAccess.register("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		//dataAccess.register("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		
		
		try {
			dataAccess.follow("Paco", "Pepe");
			Mockito.verify(dataAccess).follow("Paco", "Pepe");
			
			fail();
			
		} catch (UserDoesNotExist e) {
			// TODO Auto-generated catch block
			assertTrue(true);
			e.printStackTrace();
		} catch (Exception e) {
			fail();
		}
		
	}
	
	
	@Test
	public void test3() {
		Date date=null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			date = sdf.parse("05/10/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//dataAccess.register("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		dataAccess.register("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		
		
		try {
			dataAccess.follow("Paco", "Pepe");
			Mockito.verify(dataAccess).follow("Paco", "Pepe");
			
			fail();
			
		} catch (UserDoesNotExist e) {
			// TODO Auto-generated catch block
			assertTrue(true);
			e.printStackTrace();
		} catch (Exception e) {
			fail();
		}
		
	}
	
	@Test
	public void test4() {
		Date date=null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			date = sdf.parse("05/10/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dataAccess.register("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		dataAccess.register("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		
		
		try {
			dataAccess.follow("Paco", null);
			Mockito.verify(dataAccess).follow("Paco", null);
			
			fail();
			
		} catch (UserDoesNotExist e) {
			// TODO Auto-generated catch block
			assertTrue(true);
			e.printStackTrace();
		} catch (Exception e) {
			fail();
		}
		
	}
	
	@Test
	public void test5() {
		Date date=null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			date = sdf.parse("05/10/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dataAccess.register("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		//dataAccess.register("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		
		
		try {
			dataAccess.follow("Paco", "Paco");
			Mockito.verify(dataAccess).follow("Paco", "Paco");
			
			fail();
			
		} catch (UserDoesNotExist e) {
			// TODO Auto-generated catch block
			assertTrue(true);
			e.printStackTrace();
		} catch (Exception e) {
			fail();
		}
		
	}
	
	@Test
	public void test6() {
		Date date=null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			date = sdf.parse("05/10/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dataAccess.register("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		dataAccess.register("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		
		
		try {
			dataAccess.follow("Paco", "Pepe");
			
			Mockito.verify(dataAccess).follow("Paco", "Pepe");
			
			
			fail();
		} catch (AlreadyFollower e) {
			// TODO Auto-generated catch block
			assertTrue(true);
			e.printStackTrace();
		} catch (Exception e) {
			fail();
		}
		
	}
	
	@Test
	public void test7() {
		Date date=null;
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		try {
			date = sdf.parse("05/10/2022");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		dataAccess.register("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		dataAccess.register("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		
		
		try {
			dataAccess.follow("Paco", "Pepe");
			
			Mockito.verify(dataAccess).follow("Paco", "Pepe");
			
			
			fail();
		} catch (AlreadyFollowing e) {
			// TODO Auto-generated catch block
			assertTrue(true);
			e.printStackTrace();
		} catch (Exception e) {
			fail();
		}
		
	}


}
