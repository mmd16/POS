package testMain;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

import controller.*;
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
		Member user3 = new Member("aero", "000", "M", "7HEAD", "123");
		Member user2 = new Member("karina", "001", "F", "7HEAD", "1234");

		System.out.println("Please input your Worker ID for logging in the system...");
		String temp = Tools.sc.next();
		Employee checkEmployee = Employee.searchEmployee(temp);

		if (checkEmployee != null) {

			employee = checkEmployee;



			// no need to input the product information to create, default set is also ok.

			// -- karina: test new product -- //
			String s1 = "2021-10-20";
			ProductFactory productFactory = ProductFactory.getInstance();
			Product p1 = productFactory.createProduct("Food", "candies", "sakurazaka", s1, 30.0, 10);
			Product p2 = productFactory.createProduct("Equipment", "e1", "abc", s1, 1000, 50);
			productFactory.removeProduct("e1");
			System.out.println(productFactory.countProduct());


			
			System.out.printf("hi %s\n", employee.getName());
			// exception can be added later, eg invalid id sth else
			System.out.println("Welcome to XXX supermarket");		
			do {								
				System.out.println("Please ask our customer to input their id, leave it as 0 if they are not the members.");
				String uid = Tools.sc.next();
				member = Member.getMember(uid);// handle exception later
				System.out.println("To continue, please proceed your actions");
				System.out.println("Input (1) for accessing Inventory System");
				System.out.println("Input (2) for accessing Sales System");
				System.out.println("Input (3) for accessing Order System");
				System.out.println("Input (4) for accessing Membership System");
				digit = Tools.sc.nextInt();
				if (digit > 5) {
					System.out.println("Please Input valid number"); // exception
				} else {
					switch (digit) {
					case 1:
						InventoryController inventory = InventoryController.getInstance();
						inventory.setMemberAndEmployee(employee,member);
						inventory.execute();
						break;
					case 2:
						SalesController sales = SalesController.getInstance();
						sales.setMemberAndEmployee(employee,member);
						sales.execute();
						break;
					case 4:
						MembershipController members = MembershipController.getInstance();
						members.setMemberAndEmployee(employee,member);
						members.execute();
						break;
					case 5:
						Tools.sc.close();
						System.out.println("Bye");
						end = true;
						break;
					}
				}
			} while (end == false);
		
		}
	}
			// -- end of test new product -- //
			
//			// test sales function
//			Product p4 = productFactory.createProduct("Food", "noodles", "Food Panda", d1, 30.0, 30);
//			Product p5 = productFactory.createProduct("Equipment", "cellPhone", "Apple", d1, 250.0, 10);
//			// end			
//			
//			// test order function
//			Product p6 = productFactory.createProduct("Food", "fries", "wrt", d1, 30.0, 0);
//			String s2 = "25/10/2021";
//			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
//			LocalDate d2 = LocalDate.parse(s2, formatter);
//			user3.createOrder(user3, p6, d2, 3);
////			OrderController.setInstance(user3);
//			user2.createOrder(user2, p4, d2, 5);
////			OrderController.setInstance(user2);
			// end



	

}
