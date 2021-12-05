package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import db.UserDataBase;
import product.Product;
import tool.Tools;
import user.Employee;
import user.Member;

class TestTools {
	private Tools tools = Tools.getInstance();
	private UserDataBase userDB = UserDataBase.getInstance();

	@Test
	void testFindMax_1() {
		int[] testarray = { 1, 2, 8, 5, 7 };
		assertEquals(2, tools.findMax(testarray));
	}

	@Test
	void testFindMax_2() {
		int[] testarray = { 1, 2, 4, 5, 7 };
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
		setup();
		Employee e1 = new Employee("Tom", "M", "test@email.com", "12345678", "1001");
		userDB.add(e1);
		assertEquals(true, tools.idValidator("Employee", "1001"));
	}

	@Test
	void testIdValidator_1b() {
		setup();
		Employee e1 = new Employee("Tom", "M", "test@email.com", "12345678", "1001");
		userDB.add(e1);
		assertEquals(false, tools.idValidator("Employee", "1002"));
	}

	@Test
	void testIdValidator_2a() {
		setup();
		Member m1 = new Member("Tom", "2001", "M", "test@email.com", "1000");
		userDB.add(m1);
		assertEquals(true, tools.idValidator("Member", "1000"));
	}

	@Test
	void testIdValidator_2b() {
		setup();
		Member m1 = new Member("Tom", "2001", "M", "test@email.com", "1000");
		userDB.add(m1);
		assertEquals(false, tools.idValidator("Member", "999"));
	}

	@Test
	void testIdValidator_3() {
		setup();
		Member m1 = new Member("Tom", "2001", "M", "test@email.com", "1000");
		userDB.add(m1);
		assertEquals(false, tools.idValidator("Manager", "999"));
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

	@Test
	void testCartisEmpty_1() {
		setup();
		Member m1 = new Member("Tom", "2001", "M", "test@email.com", "1000");
		userDB.add(m1);
		assertEquals(true, tools.checkCartisEmpty(m1.getCart()));
	}

	@Test
	void testCartisEmpty_2() {
		setup();
		Member m1 = new Member("Tom", "2001", "M", "test@email.com", "1000");
		userDB.add(m1);
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		m1.addProductToCart(prod, 10, LocalDate.now());
		assertEquals(false, tools.checkCartisEmpty(m1.getCart()));
	}

	@Test
	void testGetAge() {
		setup();
		assertEquals(LocalDate.now().getYear() - 2001, tools.getAge(2001));
	}

	@Before
	void setup() {
		userDB.clear();
	}
}
