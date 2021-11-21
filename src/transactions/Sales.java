package transactions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.atomic.AtomicInteger;

import exception.ExInvalidInput;
import product.Product;
import staff.Employee;

public class Sales {
	private LocalDate date;
	private Employee employee;
	private double markedPrice;
	private double sellingPrice;
	private double unitPrice;
	private String productName;
	private String productCode;
	private String productType;
	private String salesCode;
	private String orderRefNo;
	private Product product;
	private int quantity;
	protected static ArrayList<Sales> salesList = new ArrayList<>();
	private static AtomicInteger uniqueId = new AtomicInteger();

	public Sales(Product p, int quantity, LocalDate date, Employee employee, double markedprice, double sellingPrice,
			String orderRefNo) {
		this.date = date;
		this.employee = employee;
		this.product = p;
		this.productName = p.getName();
		this.productCode = p.getProductCode();
		this.productType = p.getType();
		this.quantity = quantity;
		this.unitPrice = p.getPrice();
		this.markedPrice = markedprice;
		this.sellingPrice = sellingPrice;
		this.orderRefNo = orderRefNo;
		this.salesCode = String.valueOf(uniqueId.getAndIncrement());
		salesList.add(this);
	}

	/**
	 * 
	 * @param date
	 * @return total revenue
	 * @throws ExInvalidInput
	 */

	public static boolean checkSalesIsEmpty() {
		return salesList.isEmpty();
	}

	public static double getTotalRevenue(LocalDate date, int digit) {
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
				if (s.getDate().getMonthValue() == (date.getMonthValue()))
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

	/**
	 * this is for salesList modification
	 * 
	 * @param s
	 */
	public static void addSales(Sales s) {
		salesList.add(s);
	}

	/**
	 * this is for salesList modification
	 * 
	 * @param s
	 */
	public static void removeSales(Sales s) {
		salesList.remove(s);
		sortSales();
	}

	public static void clearSales() {
		salesList.clear();
	}

	public static Sales getSalesByOrderRefNo(String orderRefNo) {
		for (Sales s : salesList) {
			if (s.getOrderRefNo().equals(orderRefNo))
				return s;
		}
		return null;
	}

	/**
	 * 
	 * @param salesCode
	 * @return sales
	 */
	public static Sales searchSales(String salesCode) {
		for (Sales s : salesList) {
			if (salesCode.equals(s.getSalesCode()))
				return s;
		}
		return null;
	}

	/**
	 * for sorting sales according to their dates
	 */
	public static void sortSales() {
		Collections.sort(salesList, (x, y) -> x.date.compareTo(y.date));
	}

	public static void listSales() {
		sortSales();
		int index = 1;
		System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "No.", "Product Code", "Product Name",
				"Quantity", "Marked Price($)", "Selling Price($)", "Employee", "Sales Code");
		for (Sales s : salesList) {
			System.out.printf("%-20d%-20s%-20s%-20s%-20.2f%-20.2f%-20s%-20s\n", index, s.getProductCode(),
					s.getProductName(), s.getQuantity(), s.getMarkedPrice(), s.getSellingPrice(),
					s.getEmployee().getName(), s.getSalesCode());
			index++;
		}
	}

	/**
	 * 
	 * @return formatted String date
	 */
	public String getStrDate() {
		DateTimeFormatter datestr = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		return datestr.format(this.date);
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public double getMarkedPrice() {
		return markedPrice;
	}

	public void setMarkedPrice(double markedprice) {
		this.markedPrice = markedprice;
	}

	public void adjustMarkedPrice() {
		this.markedPrice = this.unitPrice * this.getQuantity();
	}

	public double getSellingUnitPrice() {
		double unitPrice = this.getSellingPrice() / this.getQuantity();
		return unitPrice;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public void adjustSellingPrice(double price) {
		this.sellingPrice = price * this.getQuantity();
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getSalesCode() {
		return salesCode;
	}

	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void deductQuantity(int quantity) {
		this.quantity -= quantity;
	}

	public static ArrayList<Sales> getSalesList() {
		return salesList;
	}

	public static void setSalesList(ArrayList<Sales> salesList) {
		Sales.salesList = salesList;
	}

	public static AtomicInteger getUniqueId() {
		return uniqueId;
	}

	public static void setUniqueId(AtomicInteger uniqueId) {
		Sales.uniqueId = uniqueId;
	}

	public String getOrderRefNo() {
		return orderRefNo;
	}

	public void setOrderRefNo(String orderRefNo) {
		this.orderRefNo = orderRefNo;
	}

	public String getProductType() {
		return productType;
	}

	public void setProductType(String productType) {
		this.productType = productType;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

}
