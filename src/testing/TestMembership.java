package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import membership.GoldMembership;
import membership.PlatinumMembership;
import user.Member;

public class TestMembership {

	@Test
	void testGetMembershipLevel() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		assertEquals("Silver", mem.getMembership().getMembershipLevel());
	}
	
	@Test
	void testSetMembershipLevel() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		mem.getMembership().setMembershipLevel("null");
		assertEquals("null", mem.getMembership().getMembershipLevel());
	}
	
	@Test
	void testGetDiscountRate() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");	
		assertEquals(0.95, mem.getMembership().getDiscountRate(), 0);
	}
	
	@Test
	void testSetDiscountRate() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		mem.getMembership().setDiscountRate(0.5);
		assertEquals(0.5, mem.getMembership().getDiscountRate(), 0);
	}
	
	@Test
	void testGetUpgradeRequirement() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		assertEquals(5000, mem.getMembership().getUpgradeRequirement());
	}
	
	@Test
	void testSetUpgradeRequirement() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		mem.getMembership().setUpgradeRequirement(3000);
		assertEquals(3000, mem.getMembership().getUpgradeRequirement());
	}
	
	@Test
	void testUpgradeMembership_0() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		mem.getMembership().upgradeMembership();
		assertEquals("Gold", mem.getMembership().upgradeMembership().getMembershipLevel());
	}
	
	@Test
	void testUpgradeMembership_1() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		mem.setMembership(new GoldMembership(0.8, 9000, "Gold"));
		mem.getMembership().upgradeMembership();
		assertEquals("Platinum", mem.getMembership().upgradeMembership().getMembershipLevel());
	}
	@Test
	void testUpgradeMembership_2() {
		Member mem = new Member("John", "2001", "M", "JohnDoe@gmail.com");
		mem.setMembership(new PlatinumMembership(0.75, 18000, "Platinum"));
		mem.getMembership().upgradeMembership();
		assertEquals("Platinum", mem.getMembership().upgradeMembership().getMembershipLevel());
	}
	
	@Test
	void testUpgradeMembership_3() {
		Member mem = new Member("John", "M");
		mem.getMembership().upgradeMembership();
		assertEquals("Non-member", mem.getMembership().upgradeMembership().getMembershipLevel());
	}

}
