package controller;

import java.text.ParseException;

import exception.ExProductNotExist;
import function.InventoryFunctions;
import product.Product;
import tool.Tools;
import user.Employee;
import user.Member;

public class InventoryController implements Controller {
	private Employee employee;
	@SuppressWarnings("unused")
	private Member member;
	private static InventoryController instance;
	private InventoryFunctions inventoryFunction = new InventoryFunctions();
	private Tools tools = Tools.getInstance();

	// private function because it is singleton
	private InventoryController() {

	}

	public static InventoryController getInstance() {
		if (instance == null) {
			instance = new InventoryController();
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
			System.out.println("Input commands for further management");
			System.out.println("Input (1) for addProducts");
			System.out.println("Input (2) for deleteProducts");
			System.out.println("Input (3) for check inventory");
			System.out.println("Input (4) for exit");
			while (!Tools.sc.hasNextInt()) {
				System.out.println("*** Input Error! ***");
				System.out.println("Input commands for further management");
				System.out.println("Input (1) for addProducts");
				System.out.println("Input (2) for deleteProducts");
				System.out.println("Input (3) for check inventory");
				System.out.println("Input (4) for exit");
				Tools.sc.next();
			}
			int input = Tools.sc.nextInt();
			Tools.sc.nextLine();
			if (!tools.inputValidator(1, 4, input))
				continue;
			switch (input) {
			case 1:
				addProducts();
				break;
			case 2:
				deleteProduct();
				break;
			case 3:
				listInventory();
				break;
			case 4:
				end = true;
				break;
			}
		} while (end == false);

	}

	@Override
	public void setMemberAndEmployee(Employee employee, Member member) {
		this.employee = employee;
		this.member = member;

	}

	public void addProducts() throws ParseException {
		boolean isFalse = false;
		do {
			try {
				System.out.println("Please input the name for the new product:");
				String productName = Tools.sc.nextLine();
				System.out.println("Please input the category of the new product:");
				String productType = Tools.sc.nextLine();
				System.out.println("Please input the brand of the new product:");
				String productBrand = Tools.sc.nextLine();
				System.out.println("Please input the food expire date / equipment warranty date in yyyy-mm-dd format");
				String productDate = Tools.sc.nextLine();
				System.out.println("Please input the Marked Price for the new product:");
				double markedPrice = Double.parseDouble(Tools.sc.nextLine());
				System.out.println("Please input the quantity of the new product:");
				int quantity = Integer.valueOf(Tools.sc.nextInt());
				inventoryFunction.createProduct(productName, productType, markedPrice, quantity, productDate,
						productBrand);
				System.out.println("*** Task Completed ***");
				System.out.println("Do you have any other actions to continue ?");
				System.out.print("Please input (1) to continue \nPlease input (0) to exit\n");
				while (!Tools.sc.hasNextInt()) {
					System.out.println("*** Input Error! ***");
					System.out.println("Do you have any other actions to continue ?");
					System.out.print("Please input (1) to continue \nPlease input (0) to exit\n");
					Tools.sc.next();
				}
				isFalse = tools.continuationValidator(Tools.sc.nextInt());
				Tools.sc.nextLine();
			} catch (Exception e) {
				System.out.println("*** Input Error! ***"); // for example, int field cannot input as string
			}
		} while (isFalse == false);
	}

	public void deleteProduct() {
		try {
			System.out.println("Please input the name for the product you want to delete:");
			String productName = Tools.sc.next();
			System.out.println("Please input the Type for the product you want to delete:");
			String productType = Tools.sc.next();
			Product p = inventoryFunction.removeProduct(productName, productType);
			if (p == null) {
				throw new ExProductNotExist();
			} else {
				System.out.println("*** Task Completed ***");
			}
		} catch (ExProductNotExist e) {
			System.out.print(e.getMessage());
		}

	}

	public void listInventory() {
		inventoryFunction.listInventory();
	}

}
