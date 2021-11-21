package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import staff.Employee;
import tool.Tools;
import user.Member;

class TestTools {

	@Test
	void testFindMax_1() {
		int[] testarray = {1, 2, 8, 5, 7};
		assertEquals(2, Tools.findMax(testarray));
	}
	
	@Test
	void testFindMax_2() {
		int[] testarray = {1, 2, 4, 5, 7};
		assertEquals(4, Tools.findMax(testarray));
	}
	
	@Test
	void testInputValidator_1() {
		assertEquals(true, Tools.inputValidator(1, 14, 3));
	}
	
	@Test
	void testInputValidator_2() {
		assertEquals(false, Tools.inputValidator(1, 14, 15));
	}
	
	@Test
	void testInputValidator_3() {
		assertEquals(false, Tools.inputValidator(1, 14, 0));
	}
	
	@Test
	void testIdValidator_1a() {
		new Employee("Tom", "M", "test@email.com", "12345678","1001");
		assertEquals(true, Tools.idValidator("Employee","1001"));
	}
	
	@Test
	void testIdValidator_1b() {
		new Employee("Tom", "M", "test@email.com", "12345678","1001");
		assertEquals(false, Tools.idValidator("Employee","1002"));
	}
	
	@Test
	void testIdValidator_2a() {
		new Member("Tom", "2001", "M", "test@email.com","1000");
		assertEquals(true, Tools.idValidator("Member","1000"));
	}
	
	@Test
	void testIdValidator_2b() {
		new Member("Tom", "2001", "M", "test@email.com","1000");
		assertEquals(false, Tools.idValidator("Member","999"));
	}
	
	@Test
	void testIdValidator_3() {
		new Member("Tom", "2001", "M", "test@email.com","1000");
		assertEquals(false, Tools.idValidator("Manager","999"));
	}
	
	@Test
	void testContinuationValidator_1() {
		assertEquals(true, Tools.continuationValidator(0));
	}
	
	@Test
	void testContinuationValidator_2() {
		assertEquals(false, Tools.continuationValidator(1));
	}
	
	@Test
	void testReturnStrForMoments_1() {
		assertEquals("Today", Tools.returnStrforMoments(0));
	}
	
	@Test
	void testReturnStrForMoments_2() {
		assertEquals("This Month", Tools.returnStrforMoments(1));
	}
	
	@Test
	void testReturnStrForMoments_3() {
		assertEquals("This Year", Tools.returnStrforMoments(2));
	}
	
	@Test
	void testReturnStrForMoments_4() {
		assertEquals(null, Tools.returnStrforMoments(3));
	}
	

	
}
