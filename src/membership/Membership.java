package membership;

public abstract class Membership {
	private String name;
	private double discountRate = 1.0;
	private double accumulatedSpending = 0;
	private double upgradeThreshold;
	private boolean qualifiedForUpgrade = false;

	public Membership(String name, double discountRate, double upgradeThreshold) {
		this.setName(name);
		this.setDiscountRate(discountRate);
		this.setUpgradeThreshold(upgradeThreshold);
	}
	public Membership(String name, double discountRate, double upgradeThreshold,Membership previousMembership) {
		this.setName(name);
		this.setDiscountRate(discountRate);
		this.setUpgradeThreshold(upgradeThreshold);
		this.addSpending(previousMembership.getAccumulatedSpending()-previousMembership.getUpgradeThreshold());
	}
	public double getDiscountRate() {
		return discountRate;
	}

	private void setDiscountRate(double discountRate) {
		this.discountRate = discountRate;
	}

	public String getName() {
		return name;
	}

	private void setName(String name) {
		this.name = name;
	}
	
	public double getAccumulatedSpending() {
		return this.accumulatedSpending;
	}

	public void addSpending(double spending) {
		this.accumulatedSpending += spending;
		if (this.accumulatedSpending >= this.upgradeThreshold) {
			this.setQualifiedForUpgrade(true);
		}
	}

	public double sepndingToUpgrade() {
		double spendingToUpgrade=this.upgradeThreshold - this.accumulatedSpending;
		if(spendingToUpgrade<0.0) {spendingToUpgrade=0.0;}
		return spendingToUpgrade;
	}

	public double getUpgradeThreshold() {
		return this.upgradeThreshold;
	}

	private void setUpgradeThreshold(double upgradeThreshold) {
		this.upgradeThreshold = upgradeThreshold;
	}

	public boolean isQualifiedForUpgrade() {
		return qualifiedForUpgrade;
	}

	private void setQualifiedForUpgrade(boolean qualifiedForUpgrade) {
		this.qualifiedForUpgrade = qualifiedForUpgrade;
	}
}
