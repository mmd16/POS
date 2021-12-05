package function;

import membership.NonMembership;
import membership.PlatinumMembership;
import tool.Tools;
import user.Member;

public class MembershipFunctions {
	private Tools tools = Tools.getInstance();
	public MembershipFunctions() {
	};

	public String getMembershipLevel(Member member) {
		return member.getMembership().getMembershipLevel();
	}

	public int getPoints(Member member) {
		return member.getPoints();
	}

	public double checkRemainingProgress(Member member) {
		if (member.getMembership() instanceof PlatinumMembership || member.getMembership() instanceof NonMembership) {
			return 0;
		} else {
			return member.getMembership().getUpgradeRequirement() - member.getAccumulatedSpending();
		}

	}

	public void upgradeMembership(Member member) {
		if (tools.checkMembership(member)) {
			while (checkRemainingProgress(member) <= 0) {
				if (member.getMembership() instanceof PlatinumMembership) {
					break;
				} else {
					member.setMembership(member.getMembership().upgradeMembership());
				}

			}
		}
	}

}
