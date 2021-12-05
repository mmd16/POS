package controller;

import function.MembershipFunctions;
import tool.Tools;
import user.Employee;
import user.Member;

public class MembershipController implements Controller {
	
	private static MembershipController instance;
	private Member member;
	private Employee employee;
	private Tools tools = Tools.getInstance();
	private MembershipFunctions membershipFunctions = new MembershipFunctions();
	// private function because it is singleton
	private MembershipController() {
	}
	
	public static MembershipController getInstance() {
		if (instance == null) {
			instance = new MembershipController();
			return instance;
		} else {
			return instance;
		}
	}

	@Override
	public void execute() {
		boolean end = false;
		do {
			System.out.printf("Hi %s, To check %s's membership details, please proceed your actions\n", employee.getName(), member.getUserName());
			System.out.println("Input commands for further management");
			System.out.println("Input (1) for checking the Customer Membership Status");
			System.out.println("Input (2) for checking the Customer's points");
			System.out.println("Input (3) for checking the status to another level of Membership");
			System.out.println("Input (4) for exit");
			while (!Tools.sc.hasNextInt()) {
				System.out.println("*** Input Error! ***");
				System.out.println("Input commands for further management");
				System.out.println("Input (1) for checking the Customer Membership Status");
				System.out.println("Input (2) for checking the Customer's points");
				System.out.println("Input (3) for checking the status to another level of Membership");
				System.out.println("Input (4) for exit");
				Tools.sc.next();
			}
			int cmd = Tools.sc.nextInt();
			if(!tools.inputValidator(1, 4, cmd))
				continue;
			switch (cmd) {
			case 1:
				getMembershipLevel(member);
				break;
			case 2:
				getPoints(member);
				break;
			case 3:
				checkRemainingProgress(member);
				break;
			case 4:
				System.out.println("Exiting....");
				end = true;
				break;
			}
		} while (end == false);
	}

	@Override
	public void setMemberAndEmployee(Employee employee,Member member) {
		this.employee = employee;
		this.member = member;
		
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