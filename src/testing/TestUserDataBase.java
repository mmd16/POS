package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import ageGroup.Adult;
import db.UserDataBase;
import user.Employee;
import user.Member;

public class TestUserDataBase {
	private UserDataBase userDB = UserDataBase.getInstance();

	@Test
	void testAddMember() {
		setup();
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		userDB.add(mem);
		assertEquals(false, userDB.checkMemberIsEmpty());
	}

	@Test
	void testAddEmployee() {
		setup();
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		userDB.add(emp);
		assertEquals(false, userDB.checkEmployeeIsEmpty());
	}

	@Test
	void testAddWrongType() {
		setup();
		Adult adult = new Adult();
		userDB.add(adult);
		assertEquals(true, userDB.checkMemberIsEmpty());
		assertEquals(true, userDB.checkEmployeeIsEmpty());
	}
	
	
	@Test
	void testRemoveMember() {
		setup();
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		userDB.add(mem);
		userDB.remove(mem);
		assertEquals(true, userDB.checkMemberIsEmpty());
	}

	@Test
	void testRemoveEmployee() {
		setup();
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		userDB.add(emp);
		userDB.remove(emp);
		assertEquals(true, userDB.checkEmployeeIsEmpty());
	}

	@Test
	void testRemoveWrongType() {
		setup();
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Adult adult = new Adult();
		userDB.add(emp);
		userDB.remove(adult);
		assertEquals(false, userDB.checkEmployeeIsEmpty());
	}
	
	@Test
	void testGetMember() {
		setup();
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com");
		userDB.add(mem);
		Member memFound = userDB.getMember(mem.getUserid());
		assertEquals(mem, memFound);
	}

	@Test
	void testGetEmployee() {
		setup();
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		userDB.add(emp);
		Employee empFound = userDB.getEmployee(emp.getWorkerid());
		assertEquals(emp, empFound);
	}

	@Before
	void setup() {
		userDB.clear();
	}
}
