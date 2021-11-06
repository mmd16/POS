package product;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import exception.ExInvalidInput;
import exception.ExProductNameNotExists;
import exception.ExProductTypeNotExists;
import exception.ExZeroOrNegative;

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

	public Product createProduct(String name, String type, double price, int inventory,
			LocalDate expireDate, String brand) throws ParseException {
		try {
			// -- exception -- //
			// 1. check if the date is valid and after today
			// 2. price is non negative
			// 3. inventory is non negative
			// 4. type is not in food / equipment (done)
			// 5. product name exists (done)

			// 1.
			LocalDate d = LocalDate.now();// change the Date object to LocalDate object

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
					Product f = new Food(name, type, price, inventory, expireDate, brand);
					productList.add(f);
					copyList.add(f);
					f.addProductToQueue(new Food(name, type, price, inventory, expireDate, brand));
					return f;
				} else 
				{
					Product product = searchProductCodeByNameAndType(name, type);
					product.addProductToQueue(new Food(name, type, price, inventory, expireDate, brand));
					product.addInventory(inventory);
					break;
				}
			case "CookingIngredients":
			case "cookingIngredients":
				if (searchProduct(name) == null) {
					Product f = new CookingIngredients(name, type, price, inventory, expireDate, brand);
					productList.add(f);
					copyList.add(f);
					f.addProductToQueue(new CookingIngredients(name, type, price, inventory, expireDate, brand));
					return f;
				} else 
				{
					Product product = searchProductCodeByNameAndType(name, type);
					product.addProductToQueue(new Food(name, type, price, inventory, expireDate, brand));
					product.addInventory(inventory);
					break;
				}
			case "Drinks":
			case "drinks":
				if (searchProduct(name) == null) {
					Product f = new Drinks(name, type, price, inventory, expireDate, brand);
					productList.add(f);
					copyList.add(f);
					f.addProductToQueue(new Drinks(name, type, price, inventory, expireDate, brand));
					return f;
				} else 
				{
					Product product = searchProductCodeByNameAndType(name, type);
					product.addProductToQueue(new Food(name, type, price, inventory, expireDate, brand));
					product.addInventory(inventory);
					break;
				}
			case "Pharamacy":
			case "pharamacy":
				if (searchProduct(name) == null) {
					Product f = new Pharamacy(name, type, price, inventory, expireDate, brand);
					productList.add(f);
					copyList.add(f);
					f.addProductToQueue(new Pharamacy(name, type, price, inventory, expireDate, brand));
					return f;
				} else 
				{
					Product product = searchProductCodeByNameAndType(name, type);
					product.addProductToQueue(new Food(name, type, price, inventory, expireDate, brand));
					product.addInventory(inventory);
					break;
				}
//					throw new ExProductNameExists(); // 5. product name exists

			default:
				throw new ExProductTypeNotExists(); // 4. type is not in food / equipment (done)
			}
		} catch (ExZeroOrNegative e) {
			System.out.println(e.getMessage());
		} catch (ExProductTypeNotExists e) {
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
//			p.printElementsinQueue();  debug use
		}
	}

	// product name should not be the same
	public static boolean checkExistingProduct(String name, String type) {
		for (Product product : productList) {
			if (product.getName().equals(name) && product.getType().equals(type)) {
				return true;
			}
		}
		return false;
	}

	public static Product searchProductCodeByNameAndType(String name, String type) {
		for (Product product : productList) {
			if (product.getName().equals(name) && product.getType().equals(type)) {
				return product;
			}
		}
		return null; /// exception
	}

	public static void printHighestSalesProduct(int digit) 
	{
		try 
		{
			Product productTemp = null;
			int temp = 0;
			switch(digit) 
			{
			case 1:
				temp = 0;
				for (Product product : productList) {
					if(temp < product.countSalesForToday(LocalDate.now())) 
					{
						temp = product.countSalesForToday(LocalDate.now());
						productTemp = product;
					}
				}
				System.out.printf("The most popular product is %s\n", productTemp.getName());
				break;
			case 2:
				temp = 0;
				for (Product product : productList) {
					if(temp < product.countSalesForthisMonth(LocalDate.now())) 
					{
						temp = product.countSalesForthisMonth(LocalDate.now());
						productTemp = product;
					}
				}
				System.out.printf("The most popular product is %s\n", productTemp.getName());
				break;
			case 3:
				temp = 0;
				for (Product product : productList) {
					if(temp < product.countSalesForthisYear(LocalDate.now())) 
					{
						temp = product.countSalesForthisYear(LocalDate.now());
						productTemp = product;
					}
				}
				System.out.printf("The most popular product is %s\n", productTemp.getName());
				break;
			default:
				throw new ExInvalidInput();
			}
		}catch (ExInvalidInput e) {
			System.out.println(e.getMessage());
		}
	}
	public int countProduct() {
		return productList.size();
	}

	// for order to search
	public static ArrayList<Product> getCopyList() {
		return copyList;
	}
}
