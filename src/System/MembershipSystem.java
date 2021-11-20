package System;

import user.Member;

public class MembershipSystem {
	public static String getMembershipLevel(Member member) {
		return member.getMembership().getMembershipLevel();
	}

	public static int getPoints(Member member) {
		return member.getPoints();
	}

	public static double checkRemainingProgress(Member member) {
		return member.checkRemainingProgress();
	}
}
