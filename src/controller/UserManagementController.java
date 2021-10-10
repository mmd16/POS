package controller;

import java.util.Scanner;

import staff.Employee;
import user.User;

public class UserManagementController implements Controller, CurrentCustomer, Staff {
	private User user;
	private Employee employee; // thinking of separate general staff apart from manager

	@Override
	public void execute() {
		int digit = -1;
		boolean end = false;
		System.out.println("Welcome to User Management System");
		do {
			Scanner sc = new Scanner(System.in);
			System.out.println("Waiting the Card Reader to response...");

			System.out.println("To continue, please proceed your actions");
			System.out.println("Input (1) for Membership enquires");
			System.out.println("Input (2) for Orders Enquires");
			System.out.println("Input (3) for Points Enquires");
			System.out.println("Input (4) to leave");
			digit = sc.nextInt();
			switch (digit) {
			case 1:
				System.out.printf("Customer owns a %s membership", user.getMembershipGrade());
				break;
			case 2:
				user.printOrders();
				break;
			case 3:
				System.out.printf("The current points in the account is", user.getPoints());
				break;
			case 4:
				sc.close();
				System.out.println("Leaving User Management System...");
				end = true;
				break;
			}
		} while (end == false);
	}

	@Override
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void setStaff(Employee employee) {
		this.employee = employee;
	}

}
