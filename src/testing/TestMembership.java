package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import membership.GoldMembership;
import membership.NonMembership;
import membership.PlatinumMembership;
import membership.SilverMembership;

public class TestMembership {

	@Test
	void testGetMembershipLevel() {
		GoldMembership mem = new GoldMembership(0.8, 9000, "Gold");
		assertEquals("Gold", mem.getMembershipLevel());
	}
	
	@Test
	void testSetMembershipLevel() {
		GoldMembership mem = new GoldMembership(0.8, 9000, "Gold");
		mem.setMembershipLevel("null");
		assertEquals("null", mem.getMembershipLevel());
	}
	
	@Test
	void testGetDiscountRate() {
		GoldMembership mem = new GoldMembership(0.8, 9000, "Gold");
		assertEquals(0.8, mem.getDiscountRate(), 0);
	}
	
	@Test
	void testSetDiscountRate() {
		GoldMembership mem = new GoldMembership(0.8, 9000, "Gold");
		mem.setDiscountRate(0.5);
		assertEquals(0.5, mem.getDiscountRate(), 0);
	}
	
	@Test
	void testGetUpgradeRequirement() {
		GoldMembership mem = new GoldMembership(0.8, 9000, "Gold");
		assertEquals(9000, mem.getUpgradeRequirement());
	}
	
	@Test
	void testSetUpgradeRequirement() {
		GoldMembership mem = new GoldMembership(0.8, 9000, "Gold");
		mem.setUpgradeRequirement(3000);
		assertEquals(3000, mem.getUpgradeRequirement());
	}
	
	@Test
	void testUpgradeMembership_0() {
		SilverMembership mem = new SilverMembership(0.95, 5000, "Silver");
		mem.upgradeMembership();
		assertEquals("Gold", mem.upgradeMembership().getMembershipLevel());
	}
	
	@Test
	void testUpgradeMembership_1() {
		GoldMembership mem = new GoldMembership(0.8, 9000, "Gold");
		mem.upgradeMembership();
		assertEquals("Platinum", mem.upgradeMembership().getMembershipLevel());
	}

	@Test
	void testUpgradeMembership_2() {
		PlatinumMembership mem = new PlatinumMembership(0.75, 18000, "Platinum");
		mem.upgradeMembership();
		assertEquals("Platinum", mem.upgradeMembership().getMembershipLevel());
	}
	
	@Test
	void testUpgradeMembership_3() {
		NonMembership mem =  new NonMembership();
		mem.upgradeMembership();
		assertEquals("Non-member", mem.upgradeMembership().getMembershipLevel());
	}

}
