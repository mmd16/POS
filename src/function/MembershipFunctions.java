package function;

import membership.NonMembership;
import membership.PlatinumMembership;
import user.Member;

public class MembershipFunctions {
	public MembershipFunctions() {
	};

	public String getMembershipLevel(Member member) {
		return member.getMembership().getMembershipLevel();
	}

	public int getPoints(Member member) {
		return member.getPoints();
	}

	public double checkRemainingProgress(Member member) {
		if (member.getMembership() instanceof PlatinumMembership && member.getMembership() instanceof NonMembership) {
//			System.out.println("You already had the highest level of membership! We are glad to be with you!");
			return 0;
		} else {
			return member.getMembership().getUpgradeRequirement() - member.getAccumulatedSpending();
		}

	}

	public void upgradeMembership(Member member) {
		if (checkMembership(member)) {
			while (checkRemainingProgress(member) < 0) {
				if (member.getMembership() instanceof PlatinumMembership) {
					break;
				} else {
					member.setMembership(member.getMembership().upgradeMembership());
				}

			}
		}
	}

	public boolean checkMembership(Member member) {
		if (member.getMembership() instanceof NonMembership)
			return false;
		return true;
	}
}
