package controller;

import staff.Employee;
import system.CheckoutSystem;
import tool.Tools;
import user.Member;

public class CheckoutController implements Controller {
	private Employee employee;
	private Member member;
	private static CheckoutController instance;
	private CheckoutSystem checkout = CheckoutSystem.getInstance();
	private Tools tools = Tools.getInstance();
	// private function because it is singleton
	private CheckoutController() {

	}

	public static CheckoutController getInstance() {
		if (instance == null) {
			instance = new CheckoutController();
			return instance;
		} else {
			return instance;
		}
	}

	@Override
	public void execute() {
		System.out.printf("---Welcome to Checkout System, %s.---\n", employee.getName());
		boolean end = false;
		do {
			System.out.println("Input commands for further management:");
			System.out.println("Input (1) for Checkout");
			System.out.println("Input (2) for refund");
			System.out.println("Input (3) for exit");
			int input = Tools.sc.nextInt();
			if (!tools.inputValidator(1, 4, input))
				continue;
			switch (input) {
			case 1:
				checkout.checkoutProcedure(member, employee);
				break;
			case 2:
				checkout.refund(member, employee);
				break;
			case 3:
				System.out.println("Exiting...");
				end = true;
				break;
			}
		} while (end == false);
	}

	@Override
	public void setMemberAndEmployee(Employee employee, Member member) {
		this.employee = employee;
		this.member = member;

	}
}
