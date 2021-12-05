package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import membership.SilverMembership;

public class TestSilverMembership {
	@Test
	void testUpgradeMembership_0() {
		SilverMembership mem = new SilverMembership(0.95, 5000, "Silver");
		mem.upgradeMembership();
		assertEquals("Gold", mem.upgradeMembership().getMembershipLevel());
	}
}
