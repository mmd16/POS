package controller;

import java.util.Scanner;

import staff.Employee;
import user.User;

public class UserManagementController implements Controller {
	private static User user;
	private Employee employee; // thinking of separate general staff apart from manager
	private static UserManagementController instance;
	
	private UserManagementController (Employee employee) {
		this.employee = employee;
	}

	public static void setInstance(Employee employee) {
		if (instance == null)
			instance = new UserManagementController (employee);
		else {
			// throw exception
		}
	}
	
	public static Controller getInstance() {
		return instance;
	}

	@Override
	public void execute() {
		int digit = -1;
		boolean end = false;
		System.out.println("Welcome to User Management System");
		do {
//			System.out.println("Waiting the Card Reader to response...");
			System.out.println("To continue, please proceed your actions");
			System.out.println("Input (1) for Membership enquires");
			System.out.println("Input (2) for Orders Enquires");
			System.out.println("Input (3) for Points Enquires");
			System.out.println("Input (4) to leave");
			digit = MainController.sc.nextInt();
			switch (digit) {
			case 1:
				System.out.printf("Customer owns a %s membership\n", user.getMembership().getName());
				break;
			case 2:
				user.printOrders();
				break;
			case 3:
				System.out.printf("The current points in the account is %d\n", user.getPoints());
				break;
			case 4:
				System.out.println("Leaving User Management System...");
				end = true;
				break;
			}
		} while (end == false);
	}


	public static void setUser(User u) {
		user = u;
		
	}

}
