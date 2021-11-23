//package testing;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//
//import org.junit.jupiter.api.Test;
//
//import ageGroup.Teenagers;
//import user.Member;
//
//
//class TestMember {
//
//	@Test
//	void testMemberAge() {
//		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
//		assertEquals("Adult",mem.getAgeGroup().getAgeGroup());
//	}
//	
//	@Test
//	void testMemberSetAge() {
//		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
//		Teenagers teen = new Teenagers();
//		mem.setAgeGroup(teen);
//		assertEquals("Teenagers",mem.getAgeGroup().getAgeGroup());
//	}
//	
//	@Test
//	void testCheckMembershipTrue() {
//		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com", "1111");
//		assertEquals(true, mem.checkMembership());
//	}
//	
//	@Test
//	void testCheckMembershipFalse() {
//		Member mem = new Member("John", "M");
//		assertEquals(false, mem.checkMembership());
//	}
//	
//	@Test
//	void testgetMembership() {
//		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com", "1111");
//		assertEquals("Silver", mem.getMembership().getMembershipLevel());
//	}
//	
//	@Test
//	void testGetAccumulatedSpending() {
//		Member mem = new Member("John", "M");
//		assertEquals(0, mem.getAccumulatedSpending());
//	}
//	
//	@Test
//	void testSetAccumulatedSpending() {
//		Member mem = new Member("John", "M");
//		mem.setAccumulatedSpending(5000);
//		assertEquals(5000, mem.getAccumulatedSpending());
//	}
//	
//	@Test
//	void testAddAccumulatedSpending() {
//		Member mem = new Member("John", "M");
//		mem.setAccumulatedSpending(5000);
//		mem.addAccumulatedSpending(100);
//		assertEquals(5100, mem.getAccumulatedSpending());
//	}
//	
//	@Test
//	void testDeductAccumulatedSpending() {
//		Member mem = new Member("John", "M");
//		mem.setAccumulatedSpending(5000);
//		mem.deductAccumulatedSpending(100);
//		assertEquals(4900, mem.getAccumulatedSpending());
//	}
//	
//	@Test
//	void testGetBirthOfYear() {
//		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
//		assertEquals("2001", mem.getBirthOfYear());
//	}
//	
//	@Test
//	void testSetBirthOfYear() {
//		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
//		mem.setBirthOfYear("2002");
//		assertEquals("2002", mem.getBirthOfYear());
//	}
//	
//	@Test
//	void testGetPoints() {
//		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
//		assertEquals(0, mem.getPoints());
//	}
//	
//	@Test
//	void testSetPoints() {
//		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
//		mem.setPoints(100);
//		assertEquals(100, mem.getPoints());
//	}
//	
//	@Test
//	void testAddPoints() {
//		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
//		mem.setPoints(100);
//		mem.addPoints(50);
//		assertEquals(150, mem.getPoints());
//	}
//	
//	@Test
//	void testDeductPoints() {
//		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
//		mem.setPoints(100);
//		mem.deductPoints(50);
//		assertEquals(50, mem.getPoints());
//	}
//	
//	@Test
//	void testGetUserName() {
//		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
//		assertEquals("John", mem.getUserName());
//	}
//	
//	@Test
//	void testSetUserName() {
//		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
//		mem.setUserName("Thomas");
//		assertEquals("Thomas", mem.getUserName());
//	}
//	
//	@Test
//	void testGetSex() {
//		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
//		assertEquals("M", mem.getSex());
//	}
//	
//	@Test
//	void testSetSex() {
//		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
//		mem.setSex("F");
//		assertEquals("F", mem.getSex());
//	}
//	
//	@Test
//	void testGetEmail() {
//		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
//		assertEquals("JohnDoe@gmail.com", mem.getEmail());
//	}
//	
//	@Test
//	void testSetEmail() {
//		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
//		mem.setEmail("Thomas@gmail.com");
//		assertEquals("Thomas@gmail.com", mem.getEmail());
//	}
//	
//	@Test
//	void testGetUserId() {
//		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com","1111");
//		assertEquals("1111", mem.getUserid());
//	}
//	
//	@Test
//	void testSetUserId() {
//		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com","1111");
//		mem.setUserid("1000");
//		assertEquals("1000", mem.getUserid());
//	}
//	
//	
//}
