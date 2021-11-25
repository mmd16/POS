package testing;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import function.MembershipFunctions;
import user.Member;

class TestMembershipFunctions {
	private MembershipFunctions membership = new MembershipFunctions();

	@Test
	void testGetMembershipLevel() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com", "1111");
		assertEquals("Silver", membership.getMembershipLevel(mem));
	}

	@Test
	void testGetPoint() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com", "1111");
		mem.addPoints(1000);
		assertEquals(1000, membership.getPoints(mem));
	}

	@Test
	void testCheckRemainingProgress() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com", "1111");
		assertEquals(5000, membership.checkRemainingProgress(mem));
	}
}
