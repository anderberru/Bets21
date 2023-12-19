import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import dataAccess.DataAccessFollow;
import domain.Registered;
import exceptions.AlreadyFollower;
import exceptions.AlreadyFollowing;
import exceptions.UserDoesNotExist;
import test.dataAccess.TestDataAccess;

public class FollowDAB2 {
	//sut:system under test
		static DataAccessFollow sut=new DataAccessFollow();

		//additional operations needed to execute the test 
		static TestDataAccess testDA=new TestDataAccess();


		//private Date date;

		private String currentUser;
		private String followedUser;





		@Before
		public void inicialize() {

			//		Registered user1 = new Registered("Paco", "1234", "Pepe", "1234", date, "cash", "mail", 0);
			//		Registered user2 = new Registered("Pepe", "1234", "Pepe", "1234", date, "cash", "mail", 0);


			//		testDA.open();
			//		testDA.removeUser(user1);
			//		testDA.removeUser(user2);
			//		testDA.close();
		}


		//Follow ondo joan beha da.
//		@Test
//		public void test1() {
//			Date date=null;
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//			try {
//				date = sdf.parse("05/10/2022");
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			testDA.open();
//			
//			Registered user1 = testDA.addUser("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
//			Registered user2 = testDA.addUser("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
//			
//			testDA.close();
//
//			currentUser = "Paco";
//			followedUser = "Pepe";
//			
//
//			try {
//				sut.follow(currentUser, followedUser);
//				
//				assertTrue(user1.isFollowing(user2));
//			} catch (Exception e) {
//				fail();
//			} finally {
//				testDA.open();
//				testDA.removeUser(user1);
//				testDA.removeUser(user2);
//				testDA.close();
//				
//			}
//
//		}

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
			//Registered user1 = testDA.addUser("Paco", "1234", "Paco", "1234", "cash", date, "mail", 0);
			Registered user2 = testDA.addUser("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
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
			//Registered user2 = testDA.addUser("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
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

//		@Test
//		public void test4() {
//			Date date=null;
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//			try {
//				date = sdf.parse("05/10/2022");
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			testDA.open();
//			Registered user1 = testDA.addUser("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
//			Registered user2 = testDA.addUser("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
//			testDA.close();
//
//			currentUser = "Paco";
//			followedUser = null;
//
//			try {
//				sut.follow(currentUser, followedUser);
//				fail("No exception");
//			} catch (UserDoesNotExist e) {
//				assertTrue(true);
//			} catch (Exception e) {
//				fail("Another exception");
//			} finally {
//				testDA.open();
//				testDA.removeUser(user1);
//				testDA.removeUser(user2);
//				testDA.close();
//			}
//
//		}
//
//		@Test
//		public void test5() {
//			Date date=null;
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//			try {
//				date = sdf.parse("05/10/2022");
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			testDA.open();
//			Registered user1 = testDA.addUser("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
//			Registered user2 = testDA.addUser("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
//			testDA.close();
//
//			currentUser = "Paco";
//			followedUser = "Paco";
//
//			try {
//				sut.follow(currentUser, followedUser);
//				fail("No exception");
//			} catch (UserDoesNotExist e) {
//				assertTrue(true);
//			} catch (Exception e) {
//				fail("Another exception");
//			} finally {
//				testDA.open();
//				testDA.removeUser(user1);
//				testDA.removeUser(user2);
//				testDA.close();
//			}
//
//		}
//
//		@Test
//		public void test6() {
//			Date date=null;
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//			try {
//				date = sdf.parse("05/10/2022");
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			testDA.open();
//			Registered user1 = testDA.addUser("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
//			Registered user2 = testDA.addUser("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
//			testDA.close();
//
//			currentUser = "Paco";
//			followedUser = "Pepe";
//
//			try {
//				sut.follow(currentUser, followedUser);
//
//				sut.follow(currentUser, followedUser);
//				fail("No exception");
//			} catch (AlreadyFollower e) {
//				assertTrue(true);
//			} catch (Exception e) {
//				fail("Another exception");
//			} finally {
//				testDA.open();
//				testDA.removeUser(user1);
//				testDA.removeUser(user2);
//				testDA.close();
//			}
//
//		}
//
//		@Test
//		public void test7() {
//			Date date=null;
//			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
//			try {
//				date = sdf.parse("05/10/2022");
//			} catch (ParseException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			
//			testDA.open();
//			Registered user1 = testDA.addUser("Paco", "1234", "Pepe", "1234", "cash", date, "mail", 0);
//			Registered user2 = testDA.addUser("Pepe", "1234", "Pepe", "1234", "cash", date, "mail", 0);
//			testDA.close();
//
//			currentUser = "Paco";
//			followedUser = "Pepe";
//
//			try {
//				sut.follow(currentUser, followedUser);
//
//				sut.follow(currentUser, followedUser);
//				fail("No exception");
//			} catch (AlreadyFollowing e) {
//				assertTrue(true);
//			} catch (Exception e) {
//				fail("Another exception");
//			} finally {
//				testDA.open();
//				testDA.removeUser(user1);
//				testDA.removeUser(user2);
//				testDA.close();
//			}
//
//		}

}
