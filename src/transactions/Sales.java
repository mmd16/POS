package transactions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.concurrent.atomic.AtomicInteger;

import product.Product;
import staff.Employee;
import user.Cart;
import user.Member;


public class Sales {
	private LocalDate date;
	private Employee employee;
	private double markedprice;
	private double sellingPrice;
	private String productName;
	private String productCode;
	private String salesCode;
	private int quantity;
	protected static ArrayList<Sales> salesList = new ArrayList<>();
	private static AtomicInteger uniqueId =new AtomicInteger();
	public Sales(String productName, String productCode, int quantity, LocalDate Date, Employee employee, double markedprice, double sellingPrice) 
	{
		this.date = Date;
		this.employee = employee;
		this.productName = productName;
		this.productCode = productCode;
		this.quantity = quantity;
		this.markedprice = markedprice;
		this.sellingPrice = sellingPrice;
		this.salesCode = String.valueOf(uniqueId.getAndIncrement());
		salesList.add(this);
	}
	
	public String getProductCode() {
		return productCode;
	}


	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}


	public void setProductName(String productName) {
		this.productName = productName;
	}


	public static void removeSales(Sales s) 
	{
		salesList.remove(s);
	}
	
	public static Sales searchSales(String salesCode) {
		for(Sales s: salesList) 
		{
			if(salesCode.equals(s.getSalesCode()));
			return s;
		}
		return null;
	}
	
	public static ArrayList<Sales> getSalesList() {
		return salesList;
	}
	
	public static void sortSales() {
		Collections.sort(salesList, (x, y) -> x.date.compareTo(y.date));
	}
	
	public static void listSales() {
		sortSales();
		int index = 1;
		System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s%-10s\n", "No.", "Product Code", "Product Name", "Quantity", "Marked Price($)", "Selling Price($)", "Employee");
		for(Sales s: salesList) 
		{
			System.out.printf("%-10d%-10s%-10s%-10s%-10.2f%-10.2f%-10s\n", index, s.getProductCode(), s.getProductName(), s.getQuantity(), s.getMarkedprice(), s.getSellingPrice(), s.getEmployee().getName());
		}
	}
	
	public static boolean checkSales(String name) {
		for (Sales s : salesList) {
			if (s.getProductName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public String getSalesCode() {
		return salesCode;
	}

	public void setSalesCode(String salesCode) {
		this.salesCode = salesCode;
	}

	public static void clearSales() {
		salesList.clear();
	}
	
	public String getProductName() {
		return productName;
	}
	
	public int getQuantity() {
		return quantity;
	}


	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}


	public LocalDate getDate() {
		return date;
	}

	public String getStrDate() {
		DateTimeFormatter datestr = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		return datestr.format(this.date);
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

//	public static void printList() 
//	{
//		System.out.printf("%-15s%-20s%-20s%-20s%-15s\n", "Product Name", "Selling Numbers",  "Selling Price($)", "Recent Date", "SalesPerson");
//		for(Sales sale : salesList) 
//		{
//			System.out.printf("%-15s%-20s%-20s%-20s%-15s\n",  sale.getProductName(), sale.getSellNum(), sale.getPrice(), sale.getStrDate(), sale.getEmployee().getName());
//		}
//	}
	
	public static double getTotalRevenue(LocalDate date) 
	{
		double total = 0;
		for(Sales s: salesList) 
		{
			if(s.getDate().isEqual(date))
				total += s.getSellingPrice();
		}
		return total;
	}
	
//	public static void markSales(ArrayList<Product> productList, ArrayList<Cart> trolleyList, Employee e) {
//		for (Product p: productList) {
//			int totalSellNum = 0;
//			int totalPrice = 0;
//
//			LocalDate tempDate = null;
//			LocalDate latestDate = null;
//			
//			for (Cart t: trolleyList) {
//				if (p.getName().equals(t.getProductName())) {
//					totalSellNum += t.getItemNum();
//					totalPrice += t.getAllPrice();
//					// check latest date of trolley
//					if (latestDate == null || t.getDate().isAfter(latestDate)) {
//						latestDate = t.getDate();
//						tempDate = latestDate;
//					}
//					else if (t.getDate().isBefore(latestDate)) {
//						latestDate = tempDate;
//						tempDate = latestDate;
//					}
//				}
//				
//				// change sales latest date
//				for (Sales s: salesList) {
//					if (s.productName.equals(t.getProductName())) {
//						if (t.getDate().isAfter(s.date)) {
//							s.date = t.getDate();
//							latestDate = s.date;
//						}
//					}
//				}
//			}
//			
//			if (totalSellNum > 0 && totalPrice > 0 && !Sales.checkSales(p.getName())) {
//				User.createSales(p.getName(), totalSellNum, latestDate, e, totalPrice);
//			}
//			else if (totalSellNum > 0 && totalPrice > 0 && Sales.checkSales(p.getName())) {
//				Sales s = Sales.searchSales(p.getName());
//				s.addSellNum(totalSellNum);
//				s.addPrice(totalPrice);
//			}
//		}
//	}
	
}
