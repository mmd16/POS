package db;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;

import ageGroup.AgeGroupFactory;
import exception.ExNoSalesExists;
import product.Product;
import transactions.Sales;

public class SalesDataBase implements Database {
	public ArrayList<Sales> salesList;

	private SalesDataBase() {
		salesList = new ArrayList<>();
	};

	private static SalesDataBase instance;

	public static SalesDataBase getInstance() {
		if (instance == null) {
			instance = new SalesDataBase();
			return instance;
		} else {
			return instance;
		}
	}

	@Override
	public <T> void add(T s) {
		this.salesList.add((Sales) s);

	}

	@Override
	public <T> void remove(T s) {
		this.salesList.remove((Sales) s);
	}

	public int getTotalSalesNum() {
		int temp = 0;
		for (Sales s : salesList) {
			temp += s.getQuantity();
		}
		return temp;
	}

	public void sortSales() {
		Collections.sort(salesList, (x, y) -> x.getDate().compareTo(y.getDate()));
	}

	public double getTotalRevenue(LocalDate date, int digit) {
		double total = 0;
		switch (digit) {
		case 0:
			total = 0;
			for (Sales s : salesList) {
				if (s.getDate().isEqual(date))
					total += s.getSellingPrice();
			}
			return total;
		case 1:
			total = 0;
			for (Sales s : salesList) {
				if (s.getDate().getMonthValue() == (date.getMonthValue()) && s.getDate().getYear() == (date.getYear()))
					total += s.getSellingPrice();
			}
			return total;
		case 2:
			total = 0;
			for (Sales s : salesList) {
				if (s.getDate().getYear() == (date.getYear()))
					total += s.getSellingPrice();
			}
			return total;
		}

		return 0;

	}

	public void listSales() {
		if (checkSalesIsEmpty()) {
			System.out.print("There is no sales yet.\n");
		} else {
			sortSales();
			int index = 1;
			System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "No.", "Product Code", "Product Name",
					"Quantity", "Marked Price($)", "Selling Price($)", "Employee", "Sales Code", "Order Ref No");
			for (Sales s : salesList) {
				System.out.printf("%-20d%-20s%-20s%-20s%-20.2f%-20.2f%-20s%-20s%-20s\n", index, s.getProductCode(),
						s.getProductName(), s.getQuantity(), s.getMarkedPrice(), s.getSellingPrice(),
						s.getEmployee().getName(), s.getSalesCode(), s.getOrderRefNo());
				index++;
			}
		}

	}

	public Sales getSalesbyPosition(int digit) {
		return salesList.get(digit);
	}

	public Sales searchSales(String salesCode) {
		for (Sales s : salesList) {
			if (salesCode.equals(s.getSalesCode()))
				return s;
		}
		return null;
	}

	public Sales getSalesByOrderRefNo(String orderRefNo) {
		for (Sales s : salesList) {
			if (s.getOrderRefNo().equals(orderRefNo))
				return s;
		}
		return null;
	}

	public boolean checkSalesIsEmpty() {
		return salesList.isEmpty();
	}

	public void clearSalesList() {
		salesList.clear();
	}
}
