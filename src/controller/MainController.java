package controller;

import java.util.Scanner;

public class MainController implements Controller{
	
	public static void main(String[] args) 
	{
		new MainController().execute();
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
						new SalesController().execute();
						break;
					case 2:
						new InventoryController().execute();
						break;
					case 3:
						new UserManagementController().execute();
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
	
}
