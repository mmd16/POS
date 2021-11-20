package testMain;

import java.text.ParseException;

import controller.CheckoutController;
import controller.InventoryController;
import controller.MembershipController;
import controller.SalesController;
import staff.Employee;
import tool.InsertData;
import tool.Tools;
import user.Member;

public class TestMain {

	
	private static Employee employee;
	private static Member member;
	static boolean end = false;
	static int digit = 0;

	public static void main(String[] args) throws ParseException {
		InsertData.loadData();
		// Search of Employee//
		String temp;
		do {
			System.out.println("Please input your Worker ID for logging in the system...");
			temp = Tools.sc.next();
		} while (!Tools.idValidator("Employee", temp));
		employee = Employee.searchEmployee(temp);
		// Search of Employee//

		// Search of Member//
		String uid;
		do {
			System.out.println("Please input your Member ID for logging in the system...");
			uid = Tools.sc.next();
		} while (!Tools.idValidator("Member", uid));
		member = Member.getMember(uid);
		// Search of Member//

		System.out.println("---Welcome to Morx Supermarket---");
		do {
			System.out.println("---To continue, please proceed your actions---");
			System.out.println("Input (1) for accessing Inventory System");
			System.out.println("Input (2) for accessing Checkout System");
			System.out.println("Input (3) for accessing Sales System");
			System.out.println("Input (4) for accessing Membership System");
			System.out.println("Input (5) for switching member"); // Bad Eng, can be changed.
			System.out.println("Input (6) to exit");
			digit = Tools.sc.nextInt();
			if (!Tools.inputValidator(1, 6, digit))
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
				} while (!Tools.idValidator("Member", uid));
				member = Member.getMember(uid);
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
