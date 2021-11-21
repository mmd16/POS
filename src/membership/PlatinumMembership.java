package membership;

public class PlatinumMembership extends Membership {
	public PlatinumMembership(double discountRate, double upgradeRequirement, String level) {
		super(discountRate, upgradeRequirement, level);
	}

	@Override
	public Membership upgradeMembership() {
		return this;
	}
}