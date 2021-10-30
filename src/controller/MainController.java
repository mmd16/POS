package controller;

import java.util.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

import product.Product;
import product.ProductFactory;
import staff.Employee;
import user.Order;
import user.User;

public class MainController {

	private static Employee employee;
	private static User user;
	static boolean end = false;
	static int digit = 0;

	public final static Scanner sc = new Scanner(System.in);

	public static void main(String[] args) throws ParseException {
		Employee e = new Employee("ON9", "M", "null", "123123123", "1");
		User user3 = new User("ON9", "000", "M", "7HEAD", "123");
		User user2 = new User("karina", "001", "F", "7HEAD", "1234");

		System.out.println("Please input your Worker ID for logging in the system...");
		String temp = sc.next();
		Employee checkEmployee = Employee.searchEmployee(temp);

		if (checkEmployee != null) {

			employee = checkEmployee;

			//setAllInstance();

			// no need to input the product information to create, default set is also ok.

			// -- karina: test new product -- //
			String s1 = "2021-10-20";
			Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(s1);
			ProductFactory productFactory = new ProductFactory();
			Product p1 = productFactory.createProduct("Food", "candies", "sakurazaka", d1, 30.0, 10);
			Product p2 = productFactory.createProduct("Equipment", "e1", "abc", d1, 1000, 50);
			System.out.println(Product.countProduct());
			Product p3 = productFactory.createProduct("Equipment", "e1", "abc", d1, 1000, 50);
			System.out.println(Product.countProduct());
			Product.removeProduct("e1");
			System.out.println(Product.countProduct());
			// -- end of test new product -- //
			
			// test sales function
			Product p4 = productFactory.createProduct("Food", "noodles", "Food Panda", d1, 30.0, 30);
			Product p5 = productFactory.createProduct("Equipment", "cellPhone", "Apple", d1, 250.0, 10);
			// end			
			
			// test order function
			Product p6 = productFactory.createProduct("Food", "fries", "wrt", d1, 30.0, 0);
			String s2 = "25/10/2021";
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
			LocalDate d2 = LocalDate.parse(s2, formatter);
			//user.createOrder(user, p6, d2, 3);
			// end
						
			System.out.printf("hi %s\n", employee.getName());
			// exception can be added later, eg invalid id sth else
			System.out.println("Welcome to XXX supermarket");
			System.out.println("Please ask our customer to input their id, leave it as 0 if they are not the members.");
			
			do {								
				System.out.println("To continue, please proceed your actions");
				System.out.println("Input (1) for accessing Checkout System");
				System.out.println("Input (2) for accessing Sales System");
				System.out.println("Input (3) for accessing User Management System");
				digit = sc.nextInt();
				if (digit > 4) {
					System.out.println("Please Input valid number"); // exception
				} else {
					switch (digit) {
					case 1:
						String anothertemp = sc.next();
						user = User.searchUserID(anothertemp);
						if (user != null)
							// change place and fix the null user bug.
							setAllInstance();					
						else {
							System.out.println("Sorry the inputted customer ID is wrong! Please input again!");
							continue;
						}		
						CheckoutController checkout = (CheckoutController) CheckoutController.getInstance();
						checkout.execute();
						break;
					case 2:
						SalesController sales = (SalesController) SalesController.getInstance();
						sales.execute();
						break;
					case 3:
						UserManagementController userManage = (UserManagementController) UserManagementController
								.getInstance();
						UserManagementController.setUser(user);
						userManage.execute();
						break;
					case 4:
						sc.close();
						System.out.println("Bye");
						end = true;
						break;
					}
				}
			} while (end == false);
		}

	}

	static void setAllInstance() {
		// karina:
		// 1. employee won't change
		// 2. since we need to calculate all sales data and inventory number,
		//    i think we should create only one instance of controllers
		// 3. but i don't know how to handle user wuwu
		CheckoutController.setInstance(employee, user);
		SalesController.setInstance(employee);		
		UserManagementController.setInstance(employee);
	}

}
