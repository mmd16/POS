package product;

import java.text.ParseException;
import java.time.LocalDate;
import db.InventoryDataBase;
import exception.ExInvalidDate;
import exception.ExProductTypeNotExists;
import exception.ExZeroOrNegative;

public class ProductFactory {
	public static ProductFactory instance = new ProductFactory();
	
	public static ProductFactory getInstance() {
		return instance;
	}

	private ProductFactory() {

	}
	private InventoryDataBase invenDB = InventoryDataBase.getInstance();
	public Product createProduct(String name, String type, double price, int inventory, LocalDate expireDate,
			String brand) throws ParseException {
		try {
			// -- exception -- //
			// 1. check if the date is valid and after today
			// 2. price is non negative
			// 3. inventory is non negative
			// 4. type is not in food / equipment (done)
			// 5. product name exists (done)

			// 1.
			if (expireDate.isBefore(LocalDate.now()))
				throw new ExInvalidDate();
			// 2.
			if (price <= 0)
				throw new ExZeroOrNegative();

			// 3.
			if (inventory <= 0)
				throw new ExZeroOrNegative();

			// 4.
			Product product;
			switch (type) {
			case "Food":
			case "food":
				if (invenDB.searchProduct(name, type) == null) {
					product = new Food(name, "Food", price, inventory, expireDate, brand);
					invenDB.add(product);
					product.addProductToQueue(new Food(name, "Food", price, inventory, expireDate, brand));
					break;
				} else {
					product = invenDB.searchProduct(name, "Food");
					product.addProductToQueue(new Food(name, "Food", price, inventory, expireDate, brand));
					product.addInventory(inventory);
					break;
				}
			case "CookingIngredients":
			case "cookingIngredients":
				if (invenDB.searchProduct(name, type) == null) {
					product = new CookingIngredients(name, "CookingIngredients", price, inventory, expireDate, brand);
					invenDB.add(product);
					product.addProductToQueue(
							new CookingIngredients(name, "CookingIngredients", price, inventory, expireDate, brand));
					break;
				} else {
					product = invenDB.searchProduct(name, "CookingIngredients");
					product.addProductToQueue(
							new Food(name, "CookingIngredients", price, inventory, expireDate, brand));
					product.addInventory(inventory);
					break;
				}
			case "Drinks":
			case "drinks":
				if (invenDB.searchProduct(name, type) == null) {
					product = new Drinks(name, "Drinks", price, inventory, expireDate, brand);
					invenDB.add(product);
					product.addProductToQueue(new Drinks(name, "Drinks", price, inventory, expireDate, brand));
					break;
				} else {
					product = invenDB.searchProduct(name, "Drinks");
					product.addProductToQueue(new Food(name, "Drinks", price, inventory, expireDate, brand));
					product.addInventory(inventory);
					break;
				}
			case "Pharamacy":
			case "pharamacy":
				if (invenDB.searchProduct(name, type) == null) {
					product = new Pharamacy(name, "Pharamacy", price, inventory, expireDate, brand);
					invenDB.add(product);
					product.addProductToQueue(new Pharamacy(name, "Pharamacy", price, inventory, expireDate, brand));
					break;
				} else {
					product = invenDB.searchProduct(name, "Pharamacy");
					product.addProductToQueue(new Food(name, "Pharamacy", price, inventory, expireDate, brand));
					product.addInventory(inventory);
					break;
				}
			default:
				throw new ExProductTypeNotExists(); // 4. type is not in food / equipment (done)
			}
			return product;
		} catch (ExZeroOrNegative e) {
			System.out.println(e.getMessage());
		} catch (ExProductTypeNotExists e) {
			System.out.println(e.getMessage());
		} catch (ExInvalidDate e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
}
