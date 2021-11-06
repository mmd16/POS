package controller;


import staff.Employee;
import tool.Tools;
import user.Member;

public class MembershipSystem implements Controller {
	
	private static MembershipSystem instance;
	private Member member;
	private Employee employee;
	
	
	// private function because it is singleton
	private MembershipSystem() {
	}
	
	public static MembershipSystem getInstance() {
		if (instance == null) {
			instance = new MembershipSystem();
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
			System.out.println("Input (1) for checking the Customer Membership Status");
			System.out.println("Input (2) for checking the Customer's points");
			System.out.println("Input (3) for checking the status to another level of Membership");
			System.out.println("Input (4) for exit");
			int cmd = Tools.sc.nextInt();
			if(!Tools.inputValidator(1, 4, cmd))
				continue;
			switch (cmd) {
			case 1:
				System.out.printf("The customer owns a %s Membership.\n", member.getMembership().getMembershipLevel());
				break;
			case 2:
				System.out.printf("The customer owns %s points.\n", member.getPoints());
				break;
			case 3:
				System.out.printf("The customer still needs to spend $%.2f to upgrade.\n", member.checkRemainingProgress());
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
}