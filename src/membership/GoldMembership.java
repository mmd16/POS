package membership;

public class GoldMembership extends Membership
{
	public GoldMembership(double discountRate, double upgradeRequirement, String level)
	{
		super(discountRate, upgradeRequirement, level);
	}
	
	@Override
	public Membership upgradeMembership()
	{
		return new PlatinumMembership(0.75, 18000, "Platinum");
	}
}
