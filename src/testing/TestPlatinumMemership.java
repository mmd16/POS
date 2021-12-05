package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import membership.PlatinumMembership;

public class TestPlatinumMemership {
	@Test
	void testUpgradeMembership_2() {
		PlatinumMembership mem = new PlatinumMembership(0.75, 18000, "Platinum");
		mem.upgradeMembership();
		assertEquals("Platinum", mem.upgradeMembership().getMembershipLevel());
	}
}
