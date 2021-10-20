package controller;

import java.util.Scanner;

import staff.Employee;
import user.User;

public class MainController implements Controller, Staff{
	private static Employee employee;
	private static User user;
	public final static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) 
	{
		Employee e = new Employee("ON9", "M", "null", "123123123", "1");
		User u = new User("ON9", "000", "M", "7HEAD", "123");
		MainController mains = new MainController();
		System.out.println("Please input your Worker ID for logging in the system...");
		String temp = sc.next();
		employee = Employee.searchEmployee(temp);
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
