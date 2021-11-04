package controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import product.Product;
import product.ProductFactory;
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
		} else {
			// throw exception
		}
	}

	public static InventoryController getInstance() {
		return instance;
	}

	@Override
	public void execute() throws ParseException {
		System.out.println("Welcome to Inventory Management System.");
		boolean end = false;
		do {
			System.out.println("Input commands for further sales management:");
			System.out.println("Input (1) for addProducts");
			System.out.println("Input (2) for check inventory");
			System.out.println("Input (3) for exit");
			int input = Tools.sc.nextInt();
			switch (input) {
			case 1:
				addProducts();
				break;
			case 2:
				ProductFactory.listInventory();
				break;
			case 3:
				end = true;
				break;
			}
		} while (end == false);

	}

	public void addProducts() throws ParseException {
		boolean isFalse = false;
		do {

			ProductFactory productFactory = new ProductFactory();
			System.out.println("Please input the name for the new product:");
			String productName = Tools.sc.next();
			System.out.println("Please input the category of the new product: Food/Equipment");
			String productType = Tools.sc.next();

			if (!(productType.equals("food") || productType.equals("Food") || productType.equals("Equipment")
					|| productType.equals("Equipment"))) {
				System.out.println("Please input Food or Equipment!");
				continue;
			}

			System.out.println("Please input the brand of the new product:");
			String productBrand = Tools.sc.next();

			if (productType.equals("food") || productType.equals("Food"))
				System.out.println("Please input the food expire date in yyyy-mm-dd format");
			else
				System.out.println("Please input the equipment warranty date in yyyy-mm-dd format");

			String productDate = Tools.sc.next();

			Date date = new SimpleDateFormat("yyyy-MM-dd").parse(productDate);

			System.out.println("Please input the Marked Price for the new product:");
			double markedPrice = Tools.sc.nextDouble();
			System.out.println("Please input the quantity of the new product:");
			int quantity = Tools.sc.nextInt();
			productFactory.createProduct(productType, productName, productBrand, date, markedPrice, quantity);
			// new Product(productName, productType, markedPrice, quantity);
			System.out.println("Do you have any other actions to continue ?");
			System.out.print("Please input (1) to continue / (0) to exit");
			isFalse = continuationValidator(Tools.sc.nextInt());
		} while (isFalse == false);
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
