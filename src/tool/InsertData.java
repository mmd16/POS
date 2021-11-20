package tool;

import java.text.ParseException;
import java.time.LocalDate;

import product.Product;
import product.ProductFactory;
import staff.Manager;
import user.Member;

public class InsertData {
	ProductFactory productFactory = ProductFactory.getInstance();

	public static void loadData() throws ParseException {
		// User Creations
		Manager e = new Manager("ON9", "M", "null", "123123123", "1");
		Member aero = new Member("aero", "2001", "M", "7HEAD", "123");
		Member user2 = new Member("Customer", "Invalid");
		Member karina = new Member("karina", "2000", "F", "7HEAD", "1234");
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
		Product salt = ProductFactory.searchProductCodeByNameAndType("Salt", "CookingIngredients");
		Product medicine = ProductFactory.searchProductCodeByNameAndType("Calcium Blocks", "Pharamacy");
		Product coke = ProductFactory.searchProductCodeByNameAndType("Coke", "Drinks");
		Product candies = ProductFactory.searchProductCodeByNameAndType("candies", "Food");
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
