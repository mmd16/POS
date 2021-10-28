package transactions;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import product.Product;
import staff.Employee;
import user.Trolley;


public class Sales {
	private LocalDate date;
	private Employee employee;
	private Product product;
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
	
	public void setSellNum(int sellNum) {
		this.sellNum += sellNum;
	}
	
	public void setPrice(double price) {
		this.price += price;
	}
	
	public int getSellNum() {
		return sellNum;
	}

	public LocalDate getDate() throws ParseException {
		return date;
	}

	public String getStrDate() {
		DateTimeFormatter datestr = DateTimeFormatter.ofPattern("dd/MM/uuuu");
		return datestr.format(this.date);
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

	public Product getProduct() {
		return product;
	}

	public void setProductCode(Product product) {
		this.product = product;
	}

	public double getPrice() {
		return price;
	}
	
	public static void printList() 
	{
		System.out.printf("%-15s%-20s%-20s%-20s%-15s\n", "Product Name", "Selling Numbers",  "Selling Price($)", "Date", "SalesPerson");
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
	
}
