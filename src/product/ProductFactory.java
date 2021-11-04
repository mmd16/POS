package product;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import exception.*;

public class ProductFactory {
	public static ProductFactory instance = new ProductFactory();
	public static ArrayList<Product> productList = new ArrayList<>();
	// test for order search product name
	public static ArrayList<Product> copyList = new ArrayList<>();

	public static ProductFactory getInstance() {
		return instance;
	}

	private ProductFactory() {

	}

	public Product createProduct(String type, String name, String brand, String date, double price, int inventory)
			throws ParseException {
		try {
			// -- exception -- //
			// 1. check if the date is valid and after today
			// 2. price is non negative
			// 3. inventory is non negative
			// 4. type is not in food / equipment (done)
			// 5. product name exists (done)

			// 1.
			Date d = new SimpleDateFormat("yyyy-MM-dd").parse(date);

			// 2.
			if (price <= 0)
				throw new ExZeroOrNegative();

			// 3.
			if (inventory <= 0)
				throw new ExZeroOrNegative();

			// 4.
			switch (type) {
			case "Food":
			case "food":
				if (searchProduct(name) == null) {
					Product f = new Food(name, brand, d, price, inventory);
					productList.add(f);
					copyList.add(f);
					return f;
				} else
					throw new ExProductNameExists(); // 5. product name exists
			case "Equipment":
			case "equipment":
				if (searchProduct(name) == null) {
					Product e = new Equipment(name, brand, d, price, inventory);
					productList.add(e);
					copyList.add(e);
					return e;
				} else
					throw new ExProductNameExists(); // 5. product name exists
			default:
				throw new ExProductTypeNotExists(); // 4. type is not in food / equipment (done)
			}
		} catch (ExZeroOrNegative e) {
			System.out.println(e.getMessage());
		} catch (ExProductTypeNotExists e) {
			System.out.println(e.getMessage());
		} catch (ExProductNameExists e) {
			System.out.println(e.getMessage());
		}
		return null;

	}

	public void removeProduct(String name) {
		Product temp = null;
		try {
			for (Product product : productList) {
				if (product.getName().equals(name)) {
					temp = product;
					productList.remove(product);
					break;
				}
			}
			if (temp == null) // didn't get result in the above for loop
				throw new ExProductNameNotExists();
			
		} catch (ExProductNameNotExists e) {
			System.out.println(e.getMessage());
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

	public void sortProduct() {
		Collections.sort(productList, (x, y) -> x.getType().compareTo(y.getType()));
	}

	public void listInventory() {
		sortProduct();
		System.out.printf("%-10s%-20s%-10s%-10s\n", "Type", "Product Name", "Quantity", "Marked Price($)/unit");
		for (Product p : productList) {
			System.out.printf("%-10s%-20s%-10d%-10f\n", p.getType(), p.getName(), p.getInventory(), p.getPrice());
		}
	}

	// product name should not be the same
	public boolean checkExistingProduct(String name) {
		for (Product product : productList) {
			if (product.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public int countProduct() {
		return productList.size();
	}

	// for order to search
	public static ArrayList<Product> getCopyList() {
		return copyList;
	}
}
