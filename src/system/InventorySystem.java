package system;

import java.text.ParseException;
import java.time.LocalDate;

import db.InventoryDataBase;
import function.InventoryFunctions;
import product.ProductFactory;
import tool.Tools;

public class InventorySystem {
	private InventoryFunctions inventoryFunction = new InventoryFunctions();
	private Tools tools = Tools.getInstance();
	private static InventorySystem instance;
	
	private InventorySystem() {
	};

	public static InventorySystem getInstance() {
		if (instance == null) {
			instance = new InventorySystem();
			return instance;
		} else {
			return instance;
		}
	}

	public void addProducts() throws ParseException {
		boolean isFalse = false;
		do {
			try {

				System.out.println("Please input the name for the new product:");
				String productName = Tools.sc.next();
				System.out.println("Please input the category of the new product:");
				String productType = Tools.sc.next();
				System.out.println("Please input the brand of the new product:");
				String productBrand = Tools.sc.next();
				System.out.println("Please input the food expire date / equipment warranty date in yyyy-mm-dd format");
				String productDate = Tools.sc.next();
				System.out.println("Please input the Marked Price for the new product:");
				double markedPrice = Tools.sc.nextDouble();
				System.out.println("Please input the quantity of the new product:");
				int quantity = Tools.sc.nextInt();
				inventoryFunction.createProduct(productName, productType, markedPrice, quantity,
						productDate, productBrand);
				System.out.println("Do you have any other actions to continue ?");
				System.out.print("Please input (1) to continue \nPlease input (0) to exit\n");
				isFalse = tools.continuationValidator(Tools.sc.nextInt());
			} catch (Exception e) {
				System.out.println("*** Input Error! ***"); // for example, int field cannot input as string
			}
		} while (isFalse == false);
	}

	public void deleteProduct() {
		System.out.println("Please input the name for the product you want to delete:");
		String productName = Tools.sc.next();
		System.out.println("Please input the Type for the product you want to delete:");
		String productType = Tools.sc.next();
		inventoryFunction.removeProduct(productName, productType);
	}

	public void listInventory() {
		inventoryFunction.listInventory();
	}

}
