package controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import user.Order;
import user.User;

public class MembershipController implements Controller {

	private static MembershipController instance;
	private User user;
	private String uid = "-1";

	private MembershipController(User user) {
		this.user = user;
	}

	public static void setInstance(User user) {
		if (instance == null)
			instance = new MembershipController(user);
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
		System.out.printf("To check %s's membership details, please proceed your actions\n", user.getUserName());
		String cmd = MainController.sc.next();
		do {
			System.out.println("Input commands for further membership management:");
			switch (cmd) {
			case "checkMem":
				System.out.printf("Customer owns a %s membership\n", user.getMembership().getName());
				break;
			case "checkPoint":	
				System.out.printf("The current points in the account is %d\n", user.getPoints());         
				break;
			case "exit":
				System.out.println("Leaving Membership System...");
				end = true;
				break;
			}
		} while (end == false);
	}
}