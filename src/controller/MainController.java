package controller;

import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;


import product.Product;
import product.ProductFactory;
import staff.Employee;
import user.User;

public class MainController implements Controller, Staff{
	private static Employee employee;
	private static User user;
	public final static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) throws ParseException 
	{
		Employee e = new Employee("ON9", "M", "null", "123123123", "1");
		//Here I think we can change the parameter password("000") into membershipID, so that when the ID start with 0, it is a non-member, 
		//if it start with 1, it is a silver member and so on.
		User u = new User("ON9", "000", "M", "7HEAD", "123");
		MainController mains = new MainController();
		System.out.println("Please input your Worker ID for logging in the system...");
		String temp = sc.next();
		// Here maybe add a date input for marking the payment date later
		employee = Employee.searchEmployee(temp);
		
		// For creating the product part, I think you should allow staff to input the product data himself rather than setting a default one
		// So for marking a sales payment, it should also allow the staff to input the product code or name to mark down the record with his name and date
		
		// -- test new product -- //
		String s1 = "2021-10-20";
		Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(s1);
		
		ProductFactory productFactory = new ProductFactory();
		Product p1 = productFactory.createProduct("Food", "f1", "sakurazaka", d1, 30.0, 3);
		Product p2 = productFactory.createProduct("Equipment","e1", "abc", d1, 1000, 50);
		System.out.println(Product.countProduct());
		Product p3 = productFactory.createProduct("Equipment","e1", "abc", d1, 1000, 50);
		System.out.println(Product.countProduct());
		Product.removeProduct("e1");
		System.out.println(Product.countProduct());
		// -- end of test new product -- //
		
		mains.execute();	
	}

	@Override
	public void execute() {
		boolean end = false;
		int digit = 0;  
		do 
		{
			System.out.printf("hi %s\n", employee.getName());
			 //exception can be added later, eg invalid id sth else
			System.out.println("Welcome to XXX supermarket");
			System.out.println("Please ask our customer to input their id, leave it as 0 if they are not the members.");
			String anothertemp = sc.next();
			user = User.searchUser(anothertemp);
			System.out.println("To continue, please proceed your actions");
			System.out.println("Input (1) for accessing Sales System");
			System.out.println("Input (2) for accessing Inventory System");
			System.out.println("Input (3) for accessing User Management System");
			digit = sc.nextInt();
			if(digit > 4) 
			{
				System.out.println("Please Input valid number");// exception
			}
			else 
			{
				switch(digit)
				{
					case 1:
						SalesController sales = new SalesController();
						sales.setStaff(employee);
						sales.setUser(user);
						sales.execute();
						break;
					case 2:
						InventoryController inventory = new InventoryController();
						inventory.execute();
						break;
					case 3:
						UserManagementController userManage = new UserManagementController();
						userManage.setStaff(employee);
						userManage.setUser(user);
						userManage.execute();
						break;
					case 4:
						sc.close();
						System.out.println("Bye");
						end = true;
						break;	
				}
			}
		}
		while(end == false);
		
	}

	
	@Override
	public void setStaff(Employee employee) 
	{
		this.employee = employee;
	}

}
