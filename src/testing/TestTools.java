package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import staff.Employee;
import tool.Tools;
import user.Member;

class TestTools {
	private Tools tools = Tools.getInstance();
	@Test
	void testFindMax_1() {
		int[] testarray = {1, 2, 8, 5, 7};
		assertEquals(2, tools.findMax(testarray));
	}
	
	@Test
	void testFindMax_2() {
		int[] testarray = {1, 2, 4, 5, 7};
		assertEquals(4, tools.findMax(testarray));
	}
	
	@Test
	void testInputValidator_1() {
		assertEquals(true, tools.inputValidator(1, 14, 3));
	}
	
	@Test
	void testInputValidator_2() {
		assertEquals(false, tools.inputValidator(1, 14, 15));
	}
	
	@Test
	void testInputValidator_3() {
		assertEquals(false, tools.inputValidator(1, 14, 0));
	}
	
	@Test
	void testIdValidator_1a() {
		new Employee("Tom", "M", "test@email.com", "12345678","1001");
		assertEquals(true, tools.idValidator("Employee","1001"));
	}
	
	@Test
	void testIdValidator_1b() {
		new Employee("Tom", "M", "test@email.com", "12345678","1001");
		assertEquals(false, tools.idValidator("Employee","1002"));
	}
	
	@Test
	void testIdValidator_2a() {
		new Member("Tom", "2001", "M", "test@email.com","1000");
		assertEquals(true, tools.idValidator("Member","1000"));
	}
	
	@Test
	void testIdValidator_2b() {
		new Member("Tom", "2001", "M", "test@email.com","1000");
		assertEquals(false, tools.idValidator("Member","999"));
	}
	
	@Test
	void testIdValidator_3() {
		new Member("Tom", "2001", "M", "test@email.com","1000");
		assertEquals(false, tools.idValidator("Manager","999"));
	}
	
	@Test
	void testContinuationValidator_1() {
		assertEquals(true, tools.continuationValidator(0));
	}
	
	@Test
	void testContinuationValidator_2() {
		assertEquals(false, tools.continuationValidator(1));
	}
	
	@Test
	void testReturnStrForMoments_1() {
		assertEquals("Today", tools.returnStrforMoments(0));
	}
	
	@Test
	void testReturnStrForMoments_2() {
		assertEquals("This Month", tools.returnStrforMoments(1));
	}
	
	@Test
	void testReturnStrForMoments_3() {
		assertEquals("This Year", tools.returnStrforMoments(2));
	}
	
	@Test
	void testReturnStrForMoments_4() {
		assertEquals(null, tools.returnStrforMoments(3));
	}
	

	
}
