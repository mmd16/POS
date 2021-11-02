package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import staff.Employee;
import user.Order;
import user.Member;

public class MembershipController implements Controller {
	
	private static MembershipController instance;
	private Member member;
	private String uid = "-1";
	private Employee employee;
	
	MembershipController(Employee employee) {
		this.employee = employee;
	}
	public static void setInstance(Employee employee) {
		if (instance == null) {
			instance = new MembershipController(employee);
		}
		else {
			// throw exception
		}
	}
	

	
	public static Controller getInstance() {
		return instance;
	}

	@Override
	public void execute() {
		boolean end = false;
		do {
			System.out.printf("To check %s's membership details, please proceed your actions\n", member.getUserName());
			System.out.println("Input (1) for checking the Customer Membership Status");
			System.out.println("Input (2) for checking the Customer's points");
			System.out.println("Input (3) for checking the status to another level of Membership");
			System.out.println("Input (4) for exit");
			int cmd = MainController.sc.nextInt();
			switch (cmd) {
			case 1:
				System.out.printf("The customer owns a %s Membership.\n", member.getMembership().getMembershipLevel());
				break;
			case 2:
				System.out.printf("The customer owns %s points.\n", member.getPoints());
				break;
			case 3:
				System.out.printf("The customer still needs to spend $%f to upgrade.", member.getMembership().checkRemainProgress());
				break;
			case 4:
				System.out.println("Exiting....");
				end = true;
				break;
			}
		} while (end == false);
	}

	@Override
	public void setUser(Member member) {
		this.member = member;
		
	}
}