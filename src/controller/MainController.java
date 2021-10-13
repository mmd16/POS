package controller;

import java.util.Scanner;

import staff.Employee;
import user.User;

public class MainController implements Controller, Staff{
	private Employee employee;
	public static void main(String[] args) 
	{
		int temp, temp2;
		MainController main = new MainController();
		Scanner sc = new Scanner(System.in);  //manual input first, later create an arraylist
		System.out.println("Please input your Worker ID for logging in the system...");
		temp2 = sc.nextInt();
		main.setStaff(Employee.searchEmployee(temp2)); //exception can be added later, eg invalid id sth else
		sc.close();
		main.execute();	
	}

	@Override
	public void execute() {
		boolean end = false;
		int digit = 0;
		do 
		{
			Scanner sc = new Scanner(System.in);  
			System.out.println("Welcome to XXX supermarket");
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
						sales.execute();
						break;
					case 2:
						InventoryController inventory = new InventoryController();
						inventory.execute();
						break;
					case 3:
						UserManagementController userManage = new UserManagementController();
						userManage.setStaff(employee);
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
