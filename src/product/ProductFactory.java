package product;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import System.SalesSystem;
import ageGroup.AgeGroupFactory;
import exception.ExInvalidDate;
import exception.ExInvalidInput;
import exception.ExNoSalesExists;
import exception.ExProductNameNotExists;
import exception.ExProductTypeNotExists;
import exception.ExZeroOrNegative;
import transactions.Sales;

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
			switch (type) {
			case "Food":
			case "food":
				if (searchProduct(name) == null) {
					Product f = new Food(name, "Food", price, inventory, expireDate, brand);
					productList.add(f);
					copyList.add(f);
					f.addProductToQueue(new Food(name, "Food", price, inventory, expireDate, brand));
					return f;
				} else {
					Product product = searchProductCodeByNameAndType(name, "Food");
					product.addProductToQueue(new Food(name, "Food", price, inventory, expireDate, brand));
					product.addInventory(inventory);
					return product;
				}
			case "CookingIngredients":
			case "cookingIngredients":
				if (searchProduct(name) == null) {
					Product f = new CookingIngredients(name, "CookingIngredients", price, inventory, expireDate, brand);
					productList.add(f);
					copyList.add(f);
					f.addProductToQueue(
							new CookingIngredients(name, "CookingIngredients", price, inventory, expireDate, brand));
					return f;
				} else {
					Product product = searchProductCodeByNameAndType(name, "CookingIngredients");
					product.addProductToQueue(
							new Food(name, "CookingIngredients", price, inventory, expireDate, brand));
					product.addInventory(inventory);
					return product;
				}
			case "Drinks":
			case "drinks":
				if (searchProduct(name) == null) {
					Product f = new Drinks(name, "Drinks", price, inventory, expireDate, brand);
					productList.add(f);
					copyList.add(f);
					f.addProductToQueue(new Drinks(name, "Drinks", price, inventory, expireDate, brand));
					return f;
				} else {
					Product product = searchProductCodeByNameAndType(name, "Drinks");
					product.addProductToQueue(new Food(name, "Drinks", price, inventory, expireDate, brand));
					product.addInventory(inventory);
					return product;
				}
			case "Pharamacy":
			case "pharamacy":
				if (searchProduct(name) == null) {
					Product f = new Pharamacy(name, "Pharamacy", price, inventory, expireDate, brand);
					productList.add(f);
					copyList.add(f);
					f.addProductToQueue(new Pharamacy(name, "Pharamacy", price, inventory, expireDate, brand));
					return f;
				} else {
					Product product = searchProductCodeByNameAndType(name, "Pharamacy");
					product.addProductToQueue(new Food(name, "Pharamacy", price, inventory, expireDate, brand));
					product.addInventory(inventory);
					return product;
				}
//					throw new ExProductNameExists(); // 5. product name exists

			default:
				throw new ExProductTypeNotExists(); // 4. type is not in food / equipment (done)
			}
		} catch (ExZeroOrNegative e) {
			System.out.println(e.getMessage());
		} catch (ExProductTypeNotExists e) {
			System.out.println(e.getMessage());
		} catch (ExInvalidDate e) {
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
		System.out.printf("%-30s%-20s%-10s%-10s\n", "Type", "Product Name", "Quantity", "Marked Price($)/unit");
		for (Product p : productList) {
//			System.out.println(p.getInventory());
			System.out.printf("%-30s%-20s%-10d%-10.2f\n", p.getType(), p.getName(), p.getInventory(), p.getPrice());
//			p.printElementsinQueue();
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

	public static int getTotalSalesNum() {
		int temp = 0;
		for (Product p : productList) {
			for (Sales s : p.getSalesList()) {
				temp += s.getQuantity();
			}
		}
		return temp;
	}

	public static double getSalesPercentageForProduct(int digit, Product product, boolean ageFilter, int age) {
		try {
			if (Sales.checkSalesIsEmpty()) {
				throw new ExNoSalesExists();
			} else {
				digit += (ageFilter == true) ? 3 : 0;
				int TotalSales = ProductFactory.getTotalSalesNum();
				int salesForProduct = 0;
				double percentage;
				switch (digit) {
				case 0:
					salesForProduct = product.countSales(LocalDate.now(), 0);
					percentage = (salesForProduct * 100 / TotalSales);
					return percentage;
				case 1:
					salesForProduct = product.countSales(LocalDate.now(), 1);
					percentage = (salesForProduct * 100 / TotalSales);
					return percentage;
				case 2:
					salesForProduct = product.countSales(LocalDate.now(), 2);
					percentage = (salesForProduct * 100 / TotalSales);
					return percentage;
				case 3:
					salesForProduct = product.countSales(LocalDate.now(), 0, AgeGroupFactory.integerToAgeGroup(age));
					percentage = (salesForProduct * 100 / TotalSales);
					return percentage;
				case 4:
					salesForProduct = product.countSales(LocalDate.now(), 1, AgeGroupFactory.integerToAgeGroup(age));
					percentage = (salesForProduct * 100 / TotalSales);
					return percentage;
				case 5:
					salesForProduct = product.countSales(LocalDate.now(), 2, AgeGroupFactory.integerToAgeGroup(age));
					percentage = (salesForProduct * 100 / TotalSales);
					return percentage;
				}

			}
		} catch (ExNoSalesExists e) {
			System.out.println(e.getMessage());
		}
		return 0;
	};

	public static Product printHighestSalesProduct(int digit, boolean ageFilter, int age) {
		try {
			if (Sales.checkSalesIsEmpty()) {
				throw new ExNoSalesExists();
			} else {
				digit += (ageFilter == true) ? 3 : 0;
				Product productTemp = null;
				int temp = 0;
				switch (digit) {
				case 0:
					temp = 0;
					for (Product product : productList) {
						if (temp < product.countSales(LocalDate.now(), 0)) {
							temp = product.countSales(LocalDate.now(), 0);
							productTemp = product;
						}
					}
					return productTemp;
				case 1:
					temp = 0;
					for (Product product : productList) {
						if (temp < product.countSales(LocalDate.now(), 1)) {
							temp = product.countSales(LocalDate.now(), 1);
							productTemp = product;
						}
					}
					return productTemp;
				case 2:
					temp = 0;
					for (Product product : productList) {
						if (temp < product.countSales(LocalDate.now(), 2)) {
							temp = product.countSales(LocalDate.now(), 2);
							productTemp = product;
						}
					}
					return productTemp;
				case 3:
					temp = 0;
					for (Product product : productList) {
						if (temp < product.countSales(LocalDate.now(), 0, AgeGroupFactory.integerToAgeGroup(age))) {
							temp = product.countSales(LocalDate.now(), 0, AgeGroupFactory.integerToAgeGroup(age));
							productTemp = product;
						}
					}
					return productTemp;
				case 4:
					temp = 0;
					for (Product product : productList) {
						if (temp < product.countSales(LocalDate.now(), 1, AgeGroupFactory.integerToAgeGroup(age))) {
							temp = product.countSales(LocalDate.now(), 1, AgeGroupFactory.integerToAgeGroup(age));
							productTemp = product;
						}
					}
					return productTemp;
				case 5:
					temp = 0;
					for (Product product : productList) {
						if (temp < product.countSales(LocalDate.now(), 2, AgeGroupFactory.integerToAgeGroup(age))) {
							temp = product.countSales(LocalDate.now(), 2, AgeGroupFactory.integerToAgeGroup(age));
							productTemp = product;
						}
					}
					return productTemp;
				}
			}

		} catch (ExNoSalesExists e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public int countProduct() {
		return productList.size();
	}

	// for order to search
	public static ArrayList<Product> getCopyList() {
		return copyList;
	}
}
