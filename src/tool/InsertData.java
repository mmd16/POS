package tool;

import java.text.ParseException;
import java.time.LocalDate;

import db.InventoryDataBase;
import db.UserDataBase;
import product.Product;
import product.ProductFactory;
import staff.Manager;
import user.Member;

public class InsertData {
	ProductFactory productFactory = ProductFactory.getInstance();
	private InventoryDataBase invenDB = InventoryDataBase.getInstance();
	private UserDataBase userDB = UserDataBase.getInstance();
	public static InsertData instance = new InsertData();

	public static InsertData getInstance() {
		return instance;
	}

	private InsertData() {

	}

	public void loadData() throws ParseException {
		// User Creations
		Manager e = new Manager("ON9", "M", "null", "123123123", "1");
		Member aero = new Member("aero", "2001", "M", "7HEAD", "123");
		Member user2 = new Member("Customer", "Invalid");
		Member karina = new Member("karina", "2000", "F", "7HEAD", "1234");
		userDB.add(e);
		userDB.add(aero);
		userDB.add(user2);
		userDB.add(karina);
		// End

		// Product Creations
		String str = "2026-08-16";
		String str1 = "2026-08-17";
		LocalDate s1 = LocalDate.parse(str);
		LocalDate s2 = LocalDate.parse(str1);
		ProductFactory productFactory = ProductFactory.getInstance();
		productFactory.createProduct("candies", "Food", 30.0, 200, s1, "Dajsu");
		productFactory.createProduct("candies", "Food", 30.0, 200, s2, "Dajsu");
		productFactory.createProduct("Coke", "Drinks", 10.0, 500, s1, "Coca");
		productFactory.createProduct("Coke", "Drinks", 10.0, 500, s2, "Coca");
		productFactory.createProduct("Calcium Blocks", "Pharamacy", 150.0, 100, s1, "Xander");
		productFactory.createProduct("Calcium Blocks", "Pharamacy", 150.0, 100, s2, "Xander");
		productFactory.createProduct("Salt", "CookingIngredients", 20.0, 100, s1, "Xander");
		productFactory.createProduct("Salt", "CookingIngredients", 20.0, 100, s2, "Xander");
		Product salt = invenDB.searchProduct("Salt", "CookingIngredients");
		Product medicine = invenDB.searchProduct("Calcium Blocks", "Pharamacy");
		Product coke = invenDB.searchProduct("Coke", "Drinks");
		Product candies = invenDB.searchProduct("candies", "Food");
		// End

		// User Cart Initialization
		aero.addProductToCart(candies, 10, LocalDate.now());
		aero.addProductToCart(salt, 60, LocalDate.now());
		karina.addProductToCart(candies, 100, LocalDate.now());
		karina.addProductToCart(medicine, 100, LocalDate.now());
		karina.addProductToCart(coke, 560, LocalDate.now());
		user2.addProductToCart(coke, 200, LocalDate.now());
		user2.addProductToCart(salt, 50, LocalDate.now());
		// End

	}
}
