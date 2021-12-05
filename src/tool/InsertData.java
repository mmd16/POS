package tool;

import java.text.ParseException;
import java.time.LocalDate;

import db.InventoryDataBase;
import db.UserDataBase;
import membership.GoldMembership;
import membership.PlatinumMembership;
import product.Product;
import product.ProductFactory;
import user.Employee;
import user.Manager;
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
		Manager e = new Manager("aiden", "M", "null", "123123123", "1");
		Employee emp = new Employee("Susie", "F", "null", "54362711", "2");
		Member aero = new Member("aero", "2001", "M", "Aero@email.com", "123");
		Member user2 = new Member("Customer", "Invalid");
		Member karina = new Member("karina", "2000", "F", "Kar1na@email.com", "1234");
		Member katrina = new Member("katrina", "2000", "F", "Kar2na@email.com", "12345");
		Member plat = new Member("palmer", "2003", "M", "pa1mer@email.com", "0011");
		Member platrob = new Member("palmerob", "2003", "M", "pa1merob@email.com", "0012");
		Member gold = new Member("ethan", "1969", "M", "Eth4n@email.com", "0202");
		Member goldrob = new Member("ethannet", "1969", "M", "Eth5n@email.com", "0203");
		plat.setMembership(new PlatinumMembership(0.75, 18000, "Platinum"));
		gold.setMembership(new GoldMembership(0.8, 9000, "Gold"));
		userDB.add(e);
		userDB.add(emp);
		userDB.add(aero);
		userDB.add(user2);
		userDB.add(karina);
		userDB.add(katrina);
		userDB.add(plat);
		userDB.add(platrob);
		userDB.add(gold);
		userDB.add(goldrob);
		// End

		// Product Creations
		String str = "2026-08-16";
		String str1 = "2026-08-17";
		LocalDate s1 = LocalDate.parse(str);
		LocalDate s2 = LocalDate.parse(str1);
		ProductFactory productFactory = ProductFactory.getInstance();
		productFactory.createProduct("candies", "Food", 10.0, 1000, s1, "Dajsu");
		productFactory.createProduct("Coke", "Drinks", 10.0, 100, s1, "Coca");
		productFactory.createProduct("Coke", "Drinks", 10.0, 300, s2, "Coca");
		productFactory.createProduct("Calcium Blocks", "Pharamacy", 150.0, 100, s1, "Xander");
		productFactory.createProduct("Calcium Blocks", "Pharamacy", 150.0, 100, s2, "Xander");
		productFactory.createProduct("Salt", "CookingIngredients", 20.0, 20, s1, "Xander");
		productFactory.createProduct("Salt", "CookingIngredients", 20.0, 290, s2, "Xander");
		Product salt = invenDB.searchProduct("Salt", "CookingIngredients");
		Product medicine = invenDB.searchProduct("Calcium Blocks", "Pharamacy");
		Product coke = invenDB.searchProduct("Coke", "Drinks");
		Product candies = invenDB.searchProduct("candies", "Food");
		// End

		// User Cart Initialization
		aero.addProductToCart(candies, 10, LocalDate.now());
		aero.addProductToCart(medicine, 100, LocalDate.now());
		karina.addProductToCart(candies, 60, LocalDate.now());
		katrina.addProductToCart(salt, 60, LocalDate.now());
		user2.addProductToCart(salt, 50, LocalDate.now());
		plat.addProductToCart(candies, 90, LocalDate.now());
		platrob.addProductToCart(coke, 90, LocalDate.now());
		gold.addProductToCart(coke, 10, s2);
		goldrob.addProductToCart(salt, 12, s2);
		// End

	}
}