package controller;

import java.text.ParseException;
import product.ProductFactory;
import staff.Employee;
import tool.Tools;
import user.Member;

public class InventorySystem implements Controller {
	private Employee employee;
	@SuppressWarnings("unused")
	private Member member;
	private static InventorySystem instance;
	ProductFactory productFactory = ProductFactory.getInstance();

	// private function because it is singleton
	private InventorySystem() {

	}

	public static InventorySystem getInstance() {
		if (instance == null) {
			instance = new InventorySystem();
			return instance;
		} else {
			return instance;
		}
	}

	@Override
	public void execute() throws ParseException {
		System.out.printf("Hi %s, Welcome to Inventory Management System.\n", employee.getName());
		boolean end = false;
		do {
			System.out.println("Input commands for further sales management:");
			System.out.println("Input (1) for addProducts");
			System.out.println("Input (2) for deleteProducts");
			System.out.println("Input (3) for check inventory");
			System.out.println("Input (4) for exit");
			int input = Tools.sc.nextInt();
			if(!Tools.inputValidator(1, 4, input))
				continue;
			switch (input) {
			case 1:
				addProducts();
				break;
			case 2:
				deleteProduct();
				break;
			case 3:
				productFactory.listInventory();
				break;
			case 4:
				end = true;
				break;
			}
		} while (end == false);

	}

	public void addProducts() throws ParseException {
		boolean isFalse = false;
		do {
			try {
			
			System.out.println("Please input the name for the new product:");
			String productName = Tools.sc.next();
			System.out.println("Please input the category of the new product: Food/Equipment");
			String productType = Tools.sc.next();
			System.out.println("Please input the brand of the new product:");
			String productBrand = Tools.sc.next();
			System.out.println("Please input the food expire date / equipment warranty date in yyyy-mm-dd format");
			String productDate = Tools.sc.next();
			System.out.println("Please input the Marked Price for the new product:");
			double markedPrice = Tools.sc.nextDouble();
			System.out.println("Please input the quantity of the new product:");
			int quantity = Tools.sc.nextInt();
			productFactory.createProduct(productName, productType, markedPrice, quantity, Tools.localDateFormatter(productDate), productBrand);
			System.out.println("Do you have any other actions to continue ?");
			System.out.print("Please input (1) to continue / (0) to exit");
			isFalse = continuationValidator(Tools.sc.nextInt());
			} catch (Exception e) {
				System.out.println("*** Input Error! ***"); //for example, int field cannot input as string
				Tools.sc.reset();
				Tools.sc.next(); 
				continue;
			}
		} while (isFalse == false);
	}
	
	public void deleteProduct() {
		System.out.println("Please input the name for the product you want to delete:");
		String productName = Tools.sc.next();
		productFactory.removeProduct(productName);
		
	}

	public boolean continuationValidator(int digit) {
		if (digit == 0)
			return true;
		return false;
	}

	@Override
	public void setMemberAndEmployee(Employee employee, Member member) {
		this.employee = employee;
		this.member = member;

	}

}
