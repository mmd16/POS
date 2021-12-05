package testing;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import function.MembershipFunctions;
import membership.PlatinumMembership;
import tool.Tools;
import user.Member;

class TestMembershipFunctions {
	private MembershipFunctions membership = new MembershipFunctions();
	private Tools tools = Tools.getInstance();
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
	void testCheckRemainingProgress_1() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com", "1111");
		mem.setMembership(new PlatinumMembership(0.75, 18000, "Platinum"));
		assertEquals(0, membership.checkRemainingProgress(mem));
	}
	
	@Test
	void testCheckRemainingProgress_2() {
		Member mem = new Member("John","M");
		assertEquals(0, membership.checkRemainingProgress(mem));
	}
	@Test
	void testCheckRemainingProgress_3() {
		Member mem = new Member("John", "2001" ,"M", "JohnDoe@gmail.com", "1111");
		assertEquals(5000, membership.checkRemainingProgress(mem));
	}
	
	@Test
	void testUpgradeMembership_1() {
		Member mem = new Member("John","M");
		membership.upgradeMembership(mem);
		assertEquals("Non-member", membership.getMembershipLevel(mem));
	}
	
	@Test
	void testUpgradeMembership_2() {
		Member mem = new Member("John", "2001" ,"M", "JohnDoe@gmail.com", "1111");
		mem.setMembership(new PlatinumMembership(0.75, 18000, "Platinum"));
		membership.upgradeMembership(mem);
		assertEquals("Platinum", membership.getMembershipLevel(mem));
	}
	
	@Test
	void testUpgradeMembership_3() {
		Member mem = new Member("John", "2001" ,"M", "JohnDoe@gmail.com", "1111");
		mem.setAccumulatedSpending(8000);
		membership.upgradeMembership(mem);
		assertEquals("Gold", membership.getMembershipLevel(mem));
	}
	
	@Test
	void testCheckMembership_1() {
		Member mem = new Member("John","M");
		assertEquals(false, tools.checkMembership(mem));
	}
	
	@Test
	void testCheckMembership_2() {
		Member mem = new Member("John", "2001" ,"M", "JohnDoe@gmail.com", "1111");
		assertEquals(true, tools.checkMembership(mem));
	}
}
