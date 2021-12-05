package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import membership.GoldMembership;

public class TestGoldMembership {
	@Test
	void testUpgradeMembership_1() {
		GoldMembership mem = new GoldMembership(0.8, 9000, "Gold");
		mem.upgradeMembership();
		assertEquals("Platinum", mem.upgradeMembership().getMembershipLevel());
	}
}
