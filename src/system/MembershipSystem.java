package system;

import function.MembershipFunctions;
import membership.PlatinumMembership;
import user.Member;

public class MembershipSystem {

	private static MembershipSystem instance;
	private MembershipFunctions membershipFunctions = new MembershipFunctions();

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

	public void getMembershipLevel(Member member) {
		System.out.printf("The customer owns a %s Membership.\n", membershipFunctions.getMembershipLevel(member));
	}

	public void getPoints(Member member) {
		System.out.printf("The customer owns %d points.\n", membershipFunctions.getPoints(member));
	}

	public void checkRemainingProgress(Member member) {
		if (membershipFunctions.getMembershipLevel(member).equals("Platinum")) {
			System.out.println("The customer already had the highest level of membership!");
		} else if (membershipFunctions.getMembershipLevel(member).equals("Non-member")) {
			System.out.println("Sorry, the customer is still not a member yet!.");
		} else {
			System.out.printf("The customer still needs to spend $%.2f to upgrade.\n",
					membershipFunctions.checkRemainingProgress(member));
		}

	}
}
