package function;

import java.time.LocalDate;
import java.util.ArrayList;

import db.InventoryDataBase;
import db.SalesDataBase;
import exception.ExNoSalesExists;
import product.Product;

public class SalesFunction {
	public SalesFunction() {
	}

	private SalesDataBase salesDB = SalesDataBase.getInstance();
	private InventoryDataBase invenDB = InventoryDataBase.getInstance();

	public int getTotalSalesNum() {
		return salesDB.getTotalSalesNum();
	};

	public double getTotalRevenue(LocalDate date, int daysUnit) {
		return salesDB.getTotalRevenue(date, daysUnit);
	}

	public boolean checkSalesIsEmpty() {
		return salesDB.checkSalesIsEmpty();
	}

	public double getSalesPercentageForProduct(int digit, Product product, boolean ageFilter, int age, int TotalSales) {
		try {
			double percentage = 0;
			if (salesDB.checkSalesIsEmpty()) {
				throw new ExNoSalesExists();
			} else {
				if (ageFilter == true) {
					percentage = invenDB.getSalesPercentageForProductForDifferentAgeGroup(digit, product, age,
							TotalSales);
				} else {
					percentage = invenDB.getSalesPercentageForProduct(digit, product, TotalSales);
				}
			}
			return percentage;

		} catch (ExNoSalesExists e) {
			System.out.print(e.getMessage());
		}
		return 0;
	};

	public Product printHighestSalesProduct(int digit, boolean ageFilter, int age) {
		try {
			Product productTemp = null;
			if (salesDB.checkSalesIsEmpty()) {
				throw new ExNoSalesExists();
			} else {
				if (ageFilter == true) {
					productTemp = invenDB.printHighestSalesProductForDifferentAgeGroups(digit, age);
				} else {
					productTemp = invenDB.printHighestSalesProduct(digit);
				}
			}
			return productTemp;
		} catch (ExNoSalesExists e) {
			System.out.print(e.getMessage());
		}
		return null;
	}

	public void listSales() {
		salesDB.listSales();
	}

	public ArrayList<Product> getHighestSalesProductList() {
		return invenDB.getHighestSalesProductList();
	}
}
