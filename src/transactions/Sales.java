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
	private double markedprice;
	private double sellingPrice;
	private String productName;
	private String productCode;
	private String productType;
	private String salesCode;
	private String orderRefNo;
	private int quantity;
	protected static ArrayList<Sales> salesList = new ArrayList<>();
	private static AtomicInteger uniqueId = new AtomicInteger();

	public Sales(Product p, int quantity, LocalDate date, Employee employee, double markedprice, double sellingPrice,
			String orderRefNo) {
		this.date = date;
		this.employee = employee;
		this.productName = p.getName();
		this.productCode = p.getProductCode();
		this.productType = p.getType();
		this.quantity = quantity;
		this.markedprice = markedprice;
		this.sellingPrice = sellingPrice;
		this.orderRefNo = orderRefNo;
		this.salesCode = String.valueOf(uniqueId.getAndIncrement());
		salesList.add(this);
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
				;
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
		System.out.printf("%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "No.", "Product Code", "Product Name", "Quantity",
				"Marked Price($)", "Selling Price($)", "Employee");
		for (Sales s : salesList) {
			System.out.printf("%-20d%-20s%-20s%-20s%-20.2f%-20.2f%-20s\n", index, s.getProductCode(),
					s.getProductName(), s.getQuantity(), s.getMarkedprice(), s.getSellingPrice(),
					s.getEmployee().getName());
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

	/**
	 * 
	 * @param date
	 * @return total revenue
	 * @throws ExInvalidInput
	 */

	public static void getTotalRevenue(LocalDate date, int digit) {
		try {
			double total = 0;
			switch (digit) {
			case 1:
				total = 0;
				for (Sales s : salesList) {
					if (s.getDate().isEqual(date))
						total += s.getSellingPrice();
				}
				System.out.printf("The total revenue for today is $%.2f\n", total);
				break;
			case 2:
				total = 0;
				for (Sales s : salesList) {
					if (s.getDate().getMonthValue() == (date.getMonthValue()))
						total += s.getSellingPrice();
				}
				System.out.printf("The total revenue for this month is $%.2f\n", total);
				break;
			case 3:
				total = 0;
				for (Sales s : salesList) {
					if (s.getDate().getYear() == (date.getYear()))
						total += s.getSellingPrice();
				}
				System.out.printf("The total revenue for this year is $%.2f\n", total);
				break;
			default:
				throw new ExInvalidInput();
			}
		} catch (ExInvalidInput e) {
			System.out.println(e.getMessage());
		}
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

	public double getMarkedprice() {
		return markedprice;
	}

	public void setMarkedprice(double markedprice) {
		this.markedprice = markedprice;
	}

	public double getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(double sellingPrice) {
		this.sellingPrice = sellingPrice;
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

}
