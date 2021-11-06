package testMain;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import controller.CheckoutSystem;
import controller.InventorySystem;
import controller.MembershipSystem;
import controller.SalesSystem;
import product.Product;
import product.ProductFactory;
import staff.Employee;
import tool.Tools;
import user.Member;

public class TestMain {

	private static Employee employee;
	private static Member member;
	static boolean end = false;
	static int digit = 0;

	public static void main(String[] args) throws ParseException {
		Employee e = new Employee("ON9", "M", "null", "123123123", "1");
		new Member("aero", "2001", "M", "7HEAD", "123");
		new Member("Customer", "Invalid");
		Member user2 = new Member("karina", "2000", "F", "7HEAD", "1234");

		// Search of Employee//
		String temp;
		do {
			System.out.println("Please input your Worker ID for logging in the system...");
			temp = Tools.sc.next();
		} while (!Tools.idValidator("Employee", temp));
		employee = Employee.searchEmployee(temp);
		// Search of Employee//

		// --- Test of creating Product ---// By Karina
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yyyy");
		String str = "16/08/2016";
		LocalDate s1 = LocalDate.parse(str, formatter);
		ProductFactory productFactory = ProductFactory.getInstance();
		Product p1 = productFactory.createProduct("candies", "Food", 30.0, 10, s1, "QQ");
		Product p2 = productFactory.createProduct("candies", "Food", 30.0, 10, s1, "QQ");
		Product pp = ProductFactory.searchProductCodeByNameAndType("candies", "Food");
		// --- Test of creating Product ---//

		// --- Add Product to user's Cart for testing ---//
		user2.addProductToCart(pp, 11, LocalDate.now());
		// --- Add Product to user's Cart for testing ---//

		// Search of Member//
		String uid;
		do {
			System.out.println("Please input your Member ID for logging in the system...");
			uid = Tools.sc.next();
		} while (!Tools.idValidator("Member", uid));
		member = Member.getMember(uid);
		// Search of Member//

		System.out.println("Welcome to Morx Supermarket");
		member = Member.getMember(uid);// handle exception later
		do {
			System.out.println("To continue, please proceed your actions");
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
				InventorySystem inventory = InventorySystem.getInstance();
				inventory.setMemberAndEmployee(employee, member);
				inventory.execute();
				break;
			case 2:
				CheckoutSystem checkout = CheckoutSystem.getInstance();
				checkout.setMemberAndEmployee(employee, member);
				checkout.execute();
				break;
			case 3:
				SalesSystem sales = SalesSystem.getInstance();
				sales.setMemberAndEmployee(employee, member);
				sales.execute();
				break;
			case 4:
				MembershipSystem members = MembershipSystem.getInstance();
				members.setMemberAndEmployee(employee, member);
				members.execute();
				break;
			case 5:
				do {
					System.out.println("Please input your Worker ID for logging in the system...");
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
