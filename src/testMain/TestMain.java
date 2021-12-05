package testMain;

import java.text.ParseException;

import controller.CheckoutController;
import controller.InventoryController;
import controller.MembershipController;
import controller.SalesController;
import db.UserDataBase;
import tool.InsertData;
import tool.Tools;
import user.Employee;
import user.Member;

public class TestMain {

	private static Employee employee;
	private static Member member;
	static boolean end = false;
	static int digit = 0;

	public static void main(String[] args) throws ParseException {
		InsertData insertData = InsertData.getInstance();
		Tools tools = Tools.getInstance();
		UserDataBase userDB = UserDataBase.getInstance();
		insertData.loadData();
		// Search of Employee//
		String temp;
		do {
			System.out.println("Please input your Worker ID for logging in the system...");
			temp = Tools.sc.next();
		} while (!tools.idValidator("Employee", temp));
		employee = userDB.getEmployee(temp);
		// Search of Employee//

		// Search of Member//
		String uid;
		do {
			System.out.println("Please input your Member ID for logging in the system...");
			uid = Tools.sc.next();
		} while (!tools.idValidator("Member", uid));
		member = userDB.getMember(uid);
		// Search of Member//

		System.out.println("Welcome to Morx Supermarket");
		do {
			System.out.println("To continue, please proceed your actions");
			System.out.println("Input (1) for accessing Inventory System");
			System.out.println("Input (2) for accessing Checkout System");
			System.out.println("Input (3) for accessing Sales System");
			System.out.println("Input (4) for accessing Membership System");
			System.out.println("Input (5) for switching member"); 
			System.out.println("Input (6) to exit");
			while (!Tools.sc.hasNextInt()) {
				System.out.println("*** Input Error! ***");
				System.out.println("Input (1) for accessing Inventory System");
				System.out.println("Input (2) for accessing Checkout System");
				System.out.println("Input (3) for accessing Sales System");
				System.out.println("Input (4) for accessing Membership System");
				System.out.println("Input (5) for switching member"); 
				System.out.println("Input (6) to exit");
				Tools.sc.next();
			}
			int digit = Tools.sc.nextInt();
			Tools.sc.nextLine();
			if (!tools.inputValidator(1, 6, digit))
				continue;
			switch (digit) {
			case 1:
				InventoryController inventory = InventoryController.getInstance();
				inventory.setMemberAndEmployee(employee, member);
				inventory.execute();
				break;
			case 2:
				CheckoutController checkout = CheckoutController.getInstance();
				checkout.setMemberAndEmployee(employee, member);
				checkout.execute();
				break;
			case 3:
				SalesController sales = SalesController.getInstance();
				sales.setMemberAndEmployee(employee, member);
				sales.execute();
				break;
			case 4:
				MembershipController members = MembershipController.getInstance();
				members.setMemberAndEmployee(employee, member);
				members.execute();
				break;
			case 5:
				do {
					System.out.println("---Please input your Member ID for logging in the system...---");
					uid = Tools.sc.next();
				} while (!tools.idValidator("Member", uid));
				member = userDB.getMember(uid);
				break;
			case 6:
				Tools.sc.close();
				System.out.println("Bye");
				end = true;
				break;
			}

		} while (end == false);

	}

}
