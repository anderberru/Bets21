import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import dataAccess.DataAccess;
import test.dataAccess.TestDataAccess;
import domain.Registered;
import domain.User;
import exceptions.AlreadyFollower;
import exceptions.AlreadyFollowing;
import exceptions.UserDoesNotExist;

public class FollowDAB {

	//sut:system under test
	static DataAccess sut=new DataAccess();

	//additional operations needed to execute the test 
	static TestDataAccess testDA=new TestDataAccess();


	Date date;

	private String currentUser;
	private String followedUser;

	//SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");




	@Before
	public void inicialize() {

		date = new Date("05/10/2022");


		//		Registered user1 = new Registered("Paco", "1234", "Pepe", "1234", date, "cash", "mail", 0);
		//		Registered user2 = new Registered("Pepe", "1234", "Pepe", "1234", date, "cash", "mail", 0);


		//		testDA.open();
		//		testDA.removeUser(user1);
		//		testDA.removeUser(user2);
		//		testDA.close();
	}


	//Follow ondo joan beha da.
	@Test
	public void test1() {
		
		testDA.open();
		User user1 = testDA.addUser("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		User user2 = testDA.addUser("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
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

	@Test
	public void test2() {
		
		testDA.open();
		//User user1 = testDA.addUser("Paco", "1234", "Paco", "1234", "cash", date, "mail", 0);
		User user2 = testDA.addUser("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		testDA.close();

		currentUser = "Paco";
		followedUser = "Pepe";

		try {

			sut.follow(currentUser, followedUser);
			fail("No exception");
		} catch (UserDoesNotExist e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Another exception");
		} finally {
			testDA.open();
			//testDA.removeUser(user1);
			testDA.removeUser(user2);
			testDA.close();
		}

	}

	@Test
	public void test3() {
		
		testDA.open();
		User user1 = testDA.addUser("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		//User user2 = testDA.addUser("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		testDA.close();

		currentUser = "Paco";
		followedUser = "Pepe";

		try {
			sut.follow(currentUser, followedUser);
			fail("No exception");
		} catch (UserDoesNotExist e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Another exception");
		} finally {
			testDA.open();
			testDA.removeUser(user1);
			//testDA.removeUser(user2);
			testDA.close();
		}

	}

	@Test
	public void test4() {
		
		testDA.open();
		User user1 = testDA.addUser("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		User user2 = testDA.addUser("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		testDA.close();

		currentUser = "Paco";
		followedUser = null;

		try {
			sut.follow(currentUser, followedUser);
			fail("No exception");
		} catch (UserDoesNotExist e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Another exception");
		} finally {
			testDA.open();
			testDA.removeUser(user1);
			testDA.removeUser(user2);
			testDA.close();
		}

	}

	@Test
	public void test5() {
		
		testDA.open();
		User user1 = testDA.addUser("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		User user2 = testDA.addUser("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		testDA.close();

		currentUser = "Paco";
		followedUser = "Paco";

		try {
			sut.follow(currentUser, followedUser);
			fail("No exception");
		} catch (UserDoesNotExist e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Another exception");
		} finally {
			testDA.open();
			testDA.removeUser(user1);
			testDA.removeUser(user2);
			testDA.close();
		}

	}

	@Test
	public void test6() {
		
		testDA.open();
		User user1 = testDA.addUser("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		User user2 = testDA.addUser("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		testDA.close();

		currentUser = "Paco";
		followedUser = "Pepe";

		try {
			sut.follow(currentUser, followedUser);

			sut.follow(currentUser, followedUser);
			fail("No exception");
		} catch (AlreadyFollower e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Another exception");
		} finally {
			testDA.open();
			testDA.removeUser(user1);
			testDA.removeUser(user2);
			testDA.close();
		}

	}

	@Test
	public void test7() {
		
		testDA.open();
		User user1 = testDA.addUser("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		User user2 = testDA.addUser("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
		testDA.close();

		currentUser = "Paco";
		followedUser = "Pepe";

		try {
			sut.follow(currentUser, followedUser);

			sut.follow(currentUser, followedUser);
			fail("No exception");
		} catch (AlreadyFollowing e) {
			assertTrue(true);
		} catch (Exception e) {
			fail("Another exception");
		} finally {
			testDA.open();
			testDA.removeUser(user1);
			testDA.removeUser(user2);
			testDA.close();
		}

	}

}
