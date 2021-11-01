package membership;

import java.util.HashMap;
import java.util.Map;

import user.Member;

public abstract class Membership
{
	private String membershipLevel;
	private double discountRate;
	private double accumulatedSpending = 0;
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

	public double getAccumulatedSpending() {
		return accumulatedSpending;
	}

	public void addAccumulatedSpending(double spding) {
		this.accumulatedSpending += spding;
	}

	public void deductAccumulatedSpending(double spding) {
		this.accumulatedSpending -= spding;
	}
	
	public double getUpgradeRequirement() {
		return upgradeRequirement;
	}

	public void setUpgradeRequirement(double upgradeRequirement) {
		this.upgradeRequirement = upgradeRequirement;
	}

	public void setaccumulatedSpending(double spending)
	{
		this.accumulatedSpending += spending;
	}
	
	public double checkRemainProgress(){
		return upgradeRequirement - accumulatedSpending;
	}
	
	public boolean checkQualified(){
		if(accumulatedSpending>=upgradeRequirement)
		{
			return true;
		}
		return false;
	}	
	
	public Membership upgradeMembership() {
		return this;
	}
}