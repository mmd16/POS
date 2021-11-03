package controller;

import product.Product;
import staff.Employee;
import tool.Tools;
import user.Member;

public class InventoryController implements Controller {
	private Employee employee;
	private Member member;
	private static InventoryController instance;

	// private function because it is singleton
	private InventoryController() {
		
	}
	
	public static void setInstance() {
		if (instance == null) {
			instance = new InventoryController();
		}
		else {
			// throw exception
		}
	}

	public static InventoryController getInstance() {
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
			int input = Tools.sc.nextInt();
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
			String productName = Tools.sc.next();
			System.out.println("Please input the category of the new product:");
			String productType = Tools.sc.next();
			System.out.println("Please input the Marked Price for the new product:");
			double markedPrice = Tools.sc.nextDouble();
			System.out.println("Please input the quantity of the new product:");
			int quantity = Tools.sc.nextInt();
			new Product(productName, productType, markedPrice, quantity);
			System.out.println("Do you have any other actions to continue ?");
			System.out.print("Please input (0) to continue");
			System.out.print("Please input (1) to exit");
			isFalse = continuationValidator(Tools.sc.nextInt());
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
	public void setMemberAndEmployee(Employee employee,Member member) {
		this.employee = employee;
		this.member = member;
		
	}

}
