package System;

import membership.PlatinumMembership;
import user.Member;

public class MembershipSystem {

	private static MembershipSystem instance;

	private MembershipSystem() {
	};

	public static MembershipSystem getInstance() {
		if (instance == null) {
			instance = new MembershipSystem();
			return instance;
		} else {
			return instance;
		}
	}

	public String getMembershipLevel(Member member) {
		return member.getMembership().getMembershipLevel();
	}

	public int getPoints(Member member) {
		return member.getPoints();
	}

	public double checkRemainingProgress(Member member) {
		if(member.getMembership() instanceof PlatinumMembership) 
		{
			System.out.println("You already had the highest level of membership! We are glad to be with you!");
			return 0;
		}
		else 
		{
			return member.checkRemainingProgress();
		}
		
	}
}
