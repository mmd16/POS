package controller;

import product.Product;
import staff.Employee;
import user.Member;

public class InventoryController implements Controller {
	private Employee employee;
	private Member member;
	private static InventoryController instance;

	private InventoryController(Employee employee) {
		this.employee = employee;
	}
	
	public static void setInstance(Employee employee) {
		if (instance == null) {
			instance = new InventoryController(employee);
		}
		else {
			// throw exception
		}
	}

	public static Controller getInstance() {
		return instance;
	}

	@Override
	public void execute() {
		System.out.println("Welcome to Inventory Management System.");
		boolean end = false;			
		do {
			System.out.println("Input commands for further sales management:");
			System.out.println("Input (1) for addProducts");
			System.out.println("Input (2) for check inventory");
			System.out.println("Input (3) for exit");
			int input = MainController.sc.nextInt();
			switch(input) 
			{
			case 1:
				addProducts();
				break;
			case 2:
				Product.listInventory();
				break;
			case 3:
				end = true;
				break;
			}
		}while(end == false);
		
	}

	public void addProducts() 
	{
		boolean isFalse = false;
		do 
		{
			System.out.println("Please input the name for the new product:");
			String productName = MainController.sc.next();
			System.out.println("Please input the category of the new product:");
			String productType = MainController.sc.next();
			System.out.println("Please input the Marked Price for the new product:");
			double markedPrice = MainController.sc.nextDouble();
			System.out.println("Please input the quantity of the new product:");
			int quantity = MainController.sc.nextInt();
			new Product(productName, productType, markedPrice, quantity);
			System.out.println("Do you have any other actions to continue ?");
			System.out.print("Please input (0) to continue");
			System.out.print("Please input (1) to exit");
			isFalse = continuationValidator(MainController.sc.nextInt());
		}
		while(isFalse == false);
	}
	
	public boolean continuationValidator(int digit) 
	{
		if(digit == 0)
			return true;
		return false;
	}
	
	@Override
	public void setUser(Member member) {
		
		this.member = member;
		
	}

}
