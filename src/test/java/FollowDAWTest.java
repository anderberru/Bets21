import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import org.junit.Test;

import dataAccess.DataAccess;
import domain.Registered;
import domain.User;
import exceptions.AlreadyFollower;
import exceptions.AlreadyFollowing;
import test.dataAccess.TestDataAccess;

public class FollowDAW {

	//sut:system under test
	static DataAccess sut=new DataAccess();

	//additional operations needed to execute the test 
	static TestDataAccess testDA=new TestDataAccess();


	//private Date date;

	private String currentUser;
	private String followedUser;



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

		testDA.open();

		Registered user1 = testDA.addUser("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		Registered user2 = testDA.addUser("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		testDA.close();

		currentUser = "Paco";
		followedUser = null;

		try {
			sut.follow(currentUser, followedUser);
			fail();
		} catch (Exception e) {
			assertTrue(true);
			
		} finally {
			testDA.open();
			testDA.removeUser(user1);
			testDA.removeUser(user2);
			testDA.close();
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

		testDA.open();

		Registered user1 = testDA.addUser("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		Registered user2 = testDA.addUser("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		testDA.close();

		currentUser = "Paco";
		followedUser = "Paco";

		try {
			sut.follow(currentUser, followedUser);
			fail();
		} catch (Exception e) {
			assertTrue(true);
			
		} finally {
			testDA.open();
			testDA.removeUser(user1);
			testDA.removeUser(user2);
			testDA.close();
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

		testDA.open();

		Registered user1 = testDA.addUser("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		Registered user2 = testDA.addUser("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		Vector<Registered> followers = new Vector<Registered>();
		followers.add(user2);
		user1.setFollowers(followers);
		testDA.close();

		currentUser = "Paco";
		followedUser = "Pepe";

		try {
			sut.follow(currentUser, followedUser);
			fail();
		}catch (AlreadyFollower e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("another exception");
			
		} finally {
			testDA.open();
			testDA.removeUser(user1);
			testDA.removeUser(user2);
			testDA.close();
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

		testDA.open();

		Registered user1 = testDA.addUser("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		Registered user2 = testDA.addUser("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		Vector<Registered> following = new Vector<Registered>();
		following.add(user1);
		user2.setFollowing(following);
		testDA.close();

		currentUser = "Paco";
		followedUser = "Pepe";

		try {
			sut.follow(currentUser, followedUser);
			fail();
		}catch (AlreadyFollowing e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("another exception");
			
		} finally {
			testDA.open();
			testDA.removeUser(user1);
			testDA.removeUser(user2);
			testDA.close();
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

		testDA.open();

		Registered user1 = testDA.addUser("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		Registered user2 = testDA.addUser("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		testDA.close();

		currentUser = "Paco";
		followedUser = "Pepe";

		try {
			sut.follow(currentUser, followedUser);
			assertTrue(true);
		} catch (Exception e) {
			fail();
		} finally {
			testDA.open();
			testDA.removeUser(user1);
			testDA.removeUser(user2);
			testDA.close();
		}
	}

}
