package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import user.Manager;

public class TestManager {

	@Test
	void TestSetManager() {
		Manager e = new Manager("John", "M", "JohnDoe@gmail.com", "99999999");
		assertEquals("John", e.getName());
	}

}
