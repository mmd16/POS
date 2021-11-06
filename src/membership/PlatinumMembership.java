package membership;

public class PlatinumMembership extends Membership {
	public PlatinumMembership(double discountRate, double upgradeRequirement, String level) {
		super(discountRate, upgradeRequirement, level);
	}

	@Override
	public Membership upgradeMembership() {

		System.out.println("You already had the highest level of membership! We are glad to be with you!");
		return this;
	}
}