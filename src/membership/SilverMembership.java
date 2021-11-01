package membership;

public class SilverMembership extends Membership
{
	public SilverMembership(double discountRate, double upgradeRequirement, String level)
	{
		super(discountRate, upgradeRequirement, level);
	}
	
	@Override
	public Membership upgradeMembership()
	{
		return new GoldMembership(0.8, 9000, "Gold");
	}
}