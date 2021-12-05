package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import membership.NonMembership;

public class TestNonMembership {
	@Test
	void testUpgradeMembership_3() {
		NonMembership mem =  new NonMembership();
		mem.upgradeMembership();
		assertEquals("Non-member", mem.upgradeMembership().getMembershipLevel());
	}
}
