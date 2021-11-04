package product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

public class ProductFactory {

	public static ArrayList<Product> productList = new ArrayList<>();
	// test for order search product name
	public static ArrayList<Product> copyList = new ArrayList<>();

	public Product createProduct(String type, String name, String brand, Date date, double price, int inventory) {
		switch (type) {
		case "Food":
			if (searchProduct(name) == null) {
				Product f = new Food(name, brand, date, price, inventory);
				productList.add(f);
				copyList.add(f);
				return f;
			} else
				return null;
			// throw exception
		case "Equipment":
			if (searchProduct(name) == null) {
				Product e = new Equipment(name, brand, date, price, inventory);
				productList.add(e);
				copyList.add(e);
				return e;
			} else
				return null;
			// throw exception
		default:
			return null;
		// don't know what type
		}
	}

	public static Product searchProduct(String name) {
		for (Product product : productList) {
			if (product.getName().equals(name)) {
				return product;
			}
		}
		return null;
	}

	public int getNumofProduct() {
		int temp = 0;
		for (Product p : productList) {
			if (p.getName().equals(p.getName()) && p.getType().equals(p.getType()))
				temp++;
		}
		return temp;
	}

	public boolean checkinventory(int quantity) {
		if (getNumofProduct() >= quantity)
			return true;
		else
			return false;
	}

	public static void sortProduct() {
		Collections.sort(productList, (x, y) -> x.getType().compareTo(y.getType()));
	}

	public static void listInventory() {
		sortProduct();
		System.out.printf("%-10s%-20s%-10s%-10s\n", "Type", "Product Name", "Quantity", "Marked Price($)/unit");
		for (Product p : productList) {
			System.out.printf("%-10s%-20s%-10d%-10f\n", p.getType(), p.getName(), p.getInventory(), p.getPrice());
		}
	}
}
