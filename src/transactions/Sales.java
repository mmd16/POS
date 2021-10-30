package transactions;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import product.Product;
import staff.Employee;
import user.Trolley;
import user.User;


public class Sales {
	private LocalDate date;
	private Employee employee;
	private double price;
	private String productName;
	private int sellNum;
	protected static ArrayList<Sales> salesList = new ArrayList<>();

	public Sales(String productName, int sellNum, LocalDate Date, Employee employee, double price) 
	{
		this.date = Date;
		this.employee = employee;
		this.productName = productName;
		this.sellNum = sellNum;
		this.price = price;
		salesList.add(this);
	}
	
	public static Sales searchSales(String name) {
		for (Sales s : salesList) {
			if (s.getProductName().equals(name)) {
				return s;
			}
		}
		return null;
	}
	
	public static ArrayList<Sales> getSalesList() {
		return salesList;
	}
	
	public static boolean checkSales(String name) {
		for (Sales s : salesList) {
			if (s.getProductName().equals(name)) {
				return true;
			}
		}
		return false;
	}
	
	public static void clearSales() {
		salesList.clear();
	}
	
	public String getProductName() {
		return productName;
	}
	
	public void addSellNum(int sellNum) {
		this.sellNum += sellNum;
	}
	
	public void addPrice(double price) {
		this.price += price;
	}
	
	public int getSellNum() {
		return sellNum;
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

	public double getPrice() {
		return price;
	}
	
	public static void printList() 
	{
		System.out.printf("%-15s%-20s%-20s%-20s%-15s\n", "Product Name", "Selling Numbers",  "Selling Price($)", "Recent Date", "SalesPerson");
		for(Sales sale : salesList) 
		{
			System.out.printf("%-15s%-20s%-20s%-20s%-15s\n",  sale.getProductName(), sale.getSellNum(), sale.getPrice(), sale.getStrDate(), sale.getEmployee().getName());
		}
	}
	
	public static double getTotalRevenue() 
	{
		double temp = 0;
		for(Sales sale : salesList) 
		{
			temp += sale.getPrice();
		}
		return temp;
	}
	
	public static void markSales(ArrayList<Product> productList, ArrayList<Trolley> trolleyList, Employee e) {
		for (Product p: productList) {
			int totalSellNum = 0;
			int totalPrice = 0;

			LocalDate tempDate = null;
			LocalDate latestDate = null;
			
			for (Trolley t: trolleyList) {
				if (p.getName().equals(t.getProductName())) {
					totalSellNum += t.getItemNum();
					totalPrice += t.getAllPrice();
					// check latest date of trolley
					if (latestDate == null || t.getDate().isAfter(latestDate)) {
						latestDate = t.getDate();
						tempDate = latestDate;
					}
					else if (t.getDate().isBefore(latestDate)) {
						latestDate = tempDate;
						tempDate = latestDate;
					}
				}
				
				// change sales latest date
				for (Sales s: salesList) {
					if (s.productName.equals(t.getProductName())) {
						if (t.getDate().isAfter(s.date)) {
							s.date = t.getDate();
							latestDate = s.date;
						}
					}
				}
			}
			
			if (totalSellNum > 0 && totalPrice > 0 && !Sales.checkSales(p.getName())) {
				User.createSales(p.getName(), totalSellNum, latestDate, e, totalPrice);
			}
			else if (totalSellNum > 0 && totalPrice > 0 && Sales.checkSales(p.getName())) {
				Sales s = Sales.searchSales(p.getName());
				s.addSellNum(totalSellNum);
				s.addPrice(totalPrice);
			}
		}
	}
	
}
