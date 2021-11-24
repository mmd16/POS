package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import staff.Employee;
import staff.Manager;

public class TestManager {

	@Test
	void TestSetEmployee() {
		Manager e = new Manager("John", "M", "JohnDoe@gmail.com", "99999999");
		String id = e.getWorkerid();
		Employee e2 = new Employee("Johnny", "M", "JohnDoe@gmail.com", "99999999", "1");
		e2.setWorkerid(e.getWorkerid());
		assertEquals(id, e2.getWorkerid());
	}

}
