package membership;

public abstract class Membership
{
	private String membershipLevel;
	private double discountRate;
	private double upgradeRequirement;
	
	public Membership(double discountRate, double upgradeRequirement, String level)
	{
		this.membershipLevel = level;
		this.discountRate = discountRate;
		this.upgradeRequirement = upgradeRequirement;
	}
	
	public Membership()
	{
		membershipLevel = "bronze";
		discountRate = 1;
		upgradeRequirement = 2000;
	}
	
	public String getMembershipLevel() {
		return membershipLevel;
	}

	public void setMembershipLevel(String membershipLevel) {
		this.membershipLevel = membershipLevel;
	}

	public double getDiscountRate() {
		return discountRate;
	}

	public void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	public double getUpgradeRequirement() {
		return upgradeRequirement;
	}

	public void setUpgradeRequirement(double upgradeRequirement) {
		this.upgradeRequirement = upgradeRequirement;
	}
	
	public double checkRemainProgress(double accumulatedSpending){
		return upgradeRequirement - accumulatedSpending;
	}

	public Membership upgradeMembership() {
		return this;
	}
}