package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import staff.Employee;

public class TestEmployee {

	@Test
	void TestSetEmployee() {
		Employee e = new Employee("John", "M", "JohnDoe@gmail.com", "99999999");
		String id = e.getWorkerid();
		Employee e2 = new Employee("Johnny", "M", "JohnDoe@gmail.com", "99999999", "1");
		e2.setWorkerid(e.getWorkerid());
		assertEquals(id, e2.getWorkerid());
	}

	@Test
	void testGetName() {
		Employee e = new Employee("John", "M", "JohnDoe@gmail.com", "99999999", "0");
		assertEquals("John", e.getName());
	}

	@Test
	void testSetName() {
		Employee e = new Employee("John", "M", "JohnDoe@gmail.com", "99999999", "0");
		e.setName("Johnny");
		assertEquals("Johnny", e.getName());
	}

	@Test
	void testGetSex() {
		Employee e = new Employee("John", "M", "JohnDoe@gmail.com", "99999999", "0");
		assertEquals("M", e.getSex());
	}

	@Test
	void testSetSex() {
		Employee e = new Employee("John", "M", "JohnDoe@gmail.com", "99999999", "0");
		e.setSex("F");
		assertEquals("F", e.getSex());
	}

	@Test
	void testGetEmail() {
		Employee e = new Employee("John", "M", "JohnDoe@gmail.com", "99999999", "0");
		assertEquals("JohnDoe@gmail.com", e.getEmail());
	}

	@Test
	void testSetEmail() {
		Employee e = new Employee("John", "M", "JohnDoe@gmail.com", "99999999", "0");
		e.setEmail("JonnyDoe@gmail.com");
		assertEquals("JonnyDoe@gmail.com", e.getEmail());
	}

	@Test
	void testGetPhoneNum() {
		Employee e = new Employee("John", "M", "JohnDoe@gmail.com", "99999999", "0");
		assertEquals("99999999", e.getPhonenum());
	}

	@Test
	void testSetPhoneNum() {
		Employee e = new Employee("John", "M", "JohnDoe@gmail.com", "99999999", "0");
		e.setPhonenum("11111111");
		assertEquals("11111111", e.getPhonenum());
	}

	@Test
	void testGetWorkerID() {
		Employee e = new Employee("John", "M", "JohnDoe@gmail.com", "99999999", "0");
		assertEquals("0", e.getWorkerid());
	}

	@Test
	void testSetWorkerID() {
		Employee e = new Employee("John", "M", "JohnDoe@gmail.com", "99999999", "0");
		e.setWorkerid("1");
		assertEquals("1", e.getWorkerid());
	}
}
