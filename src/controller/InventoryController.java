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
			try {
			ProductFactory productFactory = ProductFactory.getInstance();
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
			productFactory.createProduct(productType, productName, productBrand, productDate, markedPrice, quantity);

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
