package db;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import ageGroup.AgeGroup;
import ageGroup.AgeGroupFactory;
import product.Product;
import transactions.MemberSale;
import transactions.Sales;

public class InventoryDataBase implements Database {
	public ArrayList<Product> productList;
	public ArrayList<Product> highestSalesProductList;

	private InventoryDataBase() {
		productList = new ArrayList<>();
		highestSalesProductList = new ArrayList<>();
	};

	private static InventoryDataBase instance;

	public static InventoryDataBase getInstance() {
		if (instance == null) {
			instance = new InventoryDataBase();
			return instance;
		} else {
			return instance;
		}
	}

	@Override
	public <T> void add(T p) {
		this.productList.add((Product) p);
	}

	@Override
	public <T> void remove(T p) {
		this.productList.remove((Product) p);
	}

	public void sortProduct() {
		Collections.sort(productList, (x, y) -> x.getType().compareTo(y.getType()));
	}

	public void listInventory() {
		if (checkInventoryIsEmpty()) {
			System.out.print("The inventory is empty.\n");
		} else {
			sortProduct();
			System.out.printf("%-30s%-20s%-10s%-10s\n", "Type", "Product Name", "Quantity", "Marked Price($)/unit");
			for (Product p : productList) {
				System.out.printf("%-30s%-20s%-10d%-10.2f\n", p.getType(), p.getName(), p.getInventory(), p.getPrice());
			}
		}
	}

	public void addProductstoInventory(Product superProduct, Product subProduct) {
		superProduct.addProductToQueue(subProduct);
	}

	public Product searchProduct(String productName, String productType) {
		for (Product p : productList) {
			if (p.getName().equals(productName) && p.getType().equals(productType))
				return p;
		}
		return null;
	}

	public Product searchProductByPosition(int digit) {
		return productList.get(digit);
	}

	public boolean checkInventoryIsEmpty() {
		return productList.isEmpty();
	}

	public Product printHighestSalesProductForDifferentAgeGroups(int digit, int age) {
		highestSalesProductList.clear();
		Product productTemp = null;
		int temp = 0;
		switch (digit) {
		case 0:
			temp = 0;
			for (Product product : productList) {
				if (temp < countSales(LocalDate.now(), 0, AgeGroupFactory.integerToAgeGroup(age), product)) {
					temp = countSales(LocalDate.now(), 0, AgeGroupFactory.integerToAgeGroup(age), product);
					productTemp = product;
				}
			}
			highestSalesProductList.add(productTemp);
			for (Product product2 : productList) {
				if (product2 != productTemp && temp == countSales(LocalDate.now(), 0, product2)) {
					highestSalesProductList.add(product2);
				}
			}
			break;
		case 1:
			temp = 0;
			for (Product product : productList) {
				if (temp < countSales(LocalDate.now(), 1, AgeGroupFactory.integerToAgeGroup(age), product)) {
					temp = countSales(LocalDate.now(), 1, AgeGroupFactory.integerToAgeGroup(age), product);
					productTemp = product;
				}
			}
			highestSalesProductList.add(productTemp);
			for (Product product2 : productList) {
				if (product2 != productTemp && temp == countSales(LocalDate.now(), 0, product2)) {
					highestSalesProductList.add(product2);
				}
			}
			break;
		case 2:
			temp = 0;
			for (Product product : productList) {
				if (temp < countSales(LocalDate.now(), 2, AgeGroupFactory.integerToAgeGroup(age), product)) {
					temp = countSales(LocalDate.now(), 2, AgeGroupFactory.integerToAgeGroup(age), product);
					productTemp = product;
				}
			}
			highestSalesProductList.add(productTemp);
			for (Product product2 : productList) {
				if (product2 != productTemp && temp == countSales(LocalDate.now(), 0, product2)) {
					highestSalesProductList.add(product2);
				}
			}
			break;
		}
		return productTemp;
	}

	public Product printHighestSalesProduct(int digit) {
		highestSalesProductList.clear();
		Product productTemp = null;
		int temp = 0;
		switch (digit) {
		case 0:
			temp = 0;
			for (Product product : productList) {
				if (temp < countSales(LocalDate.now(), 0, product)) {
					temp = countSales(LocalDate.now(), 0, product);
					productTemp = product;
				}
			}
			highestSalesProductList.add(productTemp);
			for (Product product2 : productList) {
				if (product2 != productTemp && temp == countSales(LocalDate.now(), 0, product2)) {
					highestSalesProductList.add(product2);
				}
			}
			break;
		case 1:
			temp = 0;
			for (Product product : productList) {
				if (temp < countSales(LocalDate.now(), 1, product)) {
					temp = countSales(LocalDate.now(), 1, product);
					productTemp = product;
				}
			}
			highestSalesProductList.add(productTemp);
			for (Product product2 : productList) {
				if (product2 != productTemp && temp == countSales(LocalDate.now(), 0, product2)) {
					highestSalesProductList.add(product2);
				}
			}
			break;
		case 2:
			temp = 0;
			for (Product product : productList) {
				if (temp < countSales(LocalDate.now(), 2, product)) {
					temp = countSales(LocalDate.now(), 2, product);
					productTemp = product;
				}
			}
			highestSalesProductList.add(productTemp);
			for (Product product2 : productList) {
				if (product2 != productTemp && temp == countSales(LocalDate.now(), 0, product2)) {
					highestSalesProductList.add(product2);
				}
			}
			break;
		}
		return productTemp;
	}
//	public Product printHighestSalesProduct(int digit, boolean ageFilter, int age) {
//		highestSalesProductList.clear();
//		Product productTemp = null;
//		digit += (ageFilter == true) ? 3 : 0;
//		int temp = 0;
//		switch (digit) {
//		case 0:
//			temp = 0;
//			for (Product product : productList) {
//				if (temp < countSales(LocalDate.now(), 0, product)) {
//					temp = countSales(LocalDate.now(), 0, product);
//					productTemp = product;
//				}
//			}
//			highestSalesProductList.add(productTemp);
//			for (Product product2 : productList) {
//				if (product2 != productTemp && temp == countSales(LocalDate.now(), 0, product2)) {
//					highestSalesProductList.add(product2);
//				}
//			}
//
//			break;
//		case 1:
//			temp = 0;
//			for (Product product : productList) {
//				if (temp < countSales(LocalDate.now(), 1, product)) {
//					temp = countSales(LocalDate.now(), 1, product);
//					productTemp = product;
//				}
//			}
//			highestSalesProductList.add(productTemp);
//			for (Product product2 : productList) {
//				if (product2 != productTemp && temp == countSales(LocalDate.now(), 0, product2)) {
//					highestSalesProductList.add(product2);
//				}
//			}
//			break;
//		case 2:
//			temp = 0;
//			for (Product product : productList) {
//				if (temp < countSales(LocalDate.now(), 2, product)) {
//					temp = countSales(LocalDate.now(), 2, product);
//					productTemp = product;
//				}
//			}
//			highestSalesProductList.add(productTemp);
//			for (Product product2 : productList) {
//				if (product2 != productTemp && temp == countSales(LocalDate.now(), 0, product2)) {
//					highestSalesProductList.add(product2);
//				}
//			}
//			break;
//		case 3:
//			temp = 0;
//			for (Product product : productList) {
//				if (temp < countSales(LocalDate.now(), 0, AgeGroupFactory.integerToAgeGroup(age), product)) {
//					temp = countSales(LocalDate.now(), 0, AgeGroupFactory.integerToAgeGroup(age), product);
//					productTemp = product;
//				}
//			}
//			highestSalesProductList.add(productTemp);
//			for (Product product2 : productList) {
//				if (product2 != productTemp && temp == countSales(LocalDate.now(), 0, product2)) {
//					highestSalesProductList.add(product2);
//				}
//			}
//			break;
//		case 4:
//			temp = 0;
//			for (Product product : productList) {
//				if (temp < countSales(LocalDate.now(), 1, AgeGroupFactory.integerToAgeGroup(age), product)) {
//					temp = countSales(LocalDate.now(), 1, AgeGroupFactory.integerToAgeGroup(age), product);
//					productTemp = product;
//				}
//			}
//			highestSalesProductList.add(productTemp);
//			for (Product product2 : productList) {
//				if (product2 != productTemp && temp == countSales(LocalDate.now(), 0, product2)) {
//					highestSalesProductList.add(product2);
//				}
//			}
//			break;
//		case 5:
//			temp = 0;
//			for (Product product : productList) {
//				if (temp < countSales(LocalDate.now(), 2, AgeGroupFactory.integerToAgeGroup(age), product)) {
//					temp = countSales(LocalDate.now(), 2, AgeGroupFactory.integerToAgeGroup(age), product);
//					productTemp = product;
//				}
//			}
//			highestSalesProductList.add(productTemp);
//			for (Product product2 : productList) {
//				if (product2 != productTemp && temp == countSales(LocalDate.now(), 0, product2)) {
//					highestSalesProductList.add(product2);
//				}
//			}
//			break;
//		}
//		return productTemp;
//	}

	public int getHighestProductListSize() {
		return highestSalesProductList.size();
	}

	public ArrayList<Product> getHighestSalesProductList() {
		return highestSalesProductList;
	}

	public int countSales(LocalDate date, int digit, Product p) {
		int temp = 0;
		switch (digit) {
		case 0:
			temp = 0;
			for (Sales s : p.getSalesList()) {
				if (s.getDate().isEqual(date))
					temp += s.getQuantity();
			}
			break;
		case 1:
			temp = 0;
			for (Sales s : p.getSalesList()) {
				if (s.getDate().getMonthValue() == date.getMonthValue() && s.getDate().getYear() == date.getYear())
					temp += s.getQuantity();
			}
			break;
		case 2:
			temp = 0;
			for (Sales s : p.getSalesList()) {
				if (s.getDate().getYear() == date.getYear())
					temp += s.getQuantity();
			}
			break;
		}
		return temp;
	}

	public int countSales(LocalDate date, int digit, AgeGroup ageGroup, Product p) {
		int temp = 0;
		switch (digit) {
		case 0:
			temp = 0;
			for (Sales s : p.getSalesList()) {
				if (s instanceof MemberSale) {
					if (s.getDate().isEqual(date)
							&& ((MemberSale) s).getMember().getAgeGroup().getClass().equals(ageGroup.getClass()))
						temp += s.getQuantity();
				}
			}
			break;
		case 1:
			temp = 0;
			for (Sales s : p.getSalesList()) {
				if (s instanceof MemberSale) {
					if (s.getDate().getMonthValue() == date.getMonthValue() && s.getDate().getYear() == date.getYear()
							&& ((MemberSale) s).getMember().getAgeGroup().getClass().equals(ageGroup.getClass()))
						temp += s.getQuantity();
				}
			}
			break;
		case 2:
			temp = 0;
			for (Sales s : p.getSalesList()) {
				if (s instanceof MemberSale) {
					if (s.getDate().getYear() == date.getYear()
							&& ((MemberSale) s).getMember().getAgeGroup().getClass().equals(ageGroup.getClass()))
						temp += s.getQuantity();
				}
			}
			break;
		}
		return temp;
	}

	public double getSalesPercentageForProduct(int digit, Product product, boolean ageFilter, int age, int TotalSales) {

		double percentage = 0;
		digit += (ageFilter == true) ? 3 : 0;
		int salesForProduct = 0;
		switch (digit) {
		case 0:
			salesForProduct = countSales(LocalDate.now(), 0, product);
			percentage = (salesForProduct * 100 / TotalSales);
			break;
		case 1:
			salesForProduct = countSales(LocalDate.now(), 1, product);
			percentage = (salesForProduct * 100 / TotalSales);
			break;
		case 2:
			salesForProduct = countSales(LocalDate.now(), 2, product);
			percentage = (salesForProduct * 100 / TotalSales);
			break;
		case 3:
			salesForProduct = countSales(LocalDate.now(), 0, AgeGroupFactory.integerToAgeGroup(age), product);
			percentage = (salesForProduct * 100 / TotalSales);
			break;
		case 4:
			salesForProduct = countSales(LocalDate.now(), 1, AgeGroupFactory.integerToAgeGroup(age), product);
			percentage = (salesForProduct * 100 / TotalSales);
			break;
		case 5:
			salesForProduct = countSales(LocalDate.now(), 2, AgeGroupFactory.integerToAgeGroup(age), product);
			percentage = (salesForProduct * 100 / TotalSales);
			break;
		}
		return percentage;
	};

	public void deductInventoryofProductsFromQueue(int inventory, Product product) {
		int inventoryForCompareUse = inventory;
		while (inventoryForCompareUse > 0) {
			Product p = product.getProductQueue().element();
			if (p.getInventory() > inventoryForCompareUse) {
				p.removeInventory(inventoryForCompareUse);
				inventoryForCompareUse = 0;
			} else if (p.getInventory() == inventoryForCompareUse) {
				product.getProductQueue().poll();
				inventoryForCompareUse = 0;
			} else {
				Product gar = product.getProductQueue().poll();
				inventoryForCompareUse -= gar.getInventory();
			}
		}
		product.removeInventory(inventory);
	}

	public void clear() {
		productList.clear();
	}
}
