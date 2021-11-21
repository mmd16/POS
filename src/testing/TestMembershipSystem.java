package testing;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import System.MembershipSystem;
import user.Member;

class TestMembershipSystem {

	@Test
	void testGetMembershipLevel() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com", "1111");
		assertEquals("Silver", MembershipSystem.getMembershipLevel(mem));
	}
	
	@Test
	void testGetPoint() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com", "1111");
		mem.addPoints(1000);
		assertEquals(1000, MembershipSystem.getPoints(mem));
	}
	
	@Test
	void testCheckRemainingProgress() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com", "1111");
		assertEquals(5000, MembershipSystem.checkRemainingProgress(mem));
	}
}
