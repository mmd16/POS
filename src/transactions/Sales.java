package transactions;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import product.Product;
import staff.Employee;


public class Sales {
	private LocalDate date;
	private Employee employee;
	private Product product;
	private double price;
	protected static ArrayList<Sales> salesList = new ArrayList<>();

	public Sales(LocalDate Date, Employee employee, Product product) 
	{
		this.date = Date;
		this.employee = employee;
		this.product = product;
		this.price = product.getPrice();
		salesList.add(this);
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

	public void setPrice(double price) {
		this.price = price;
	}
	
	public static void printList() 
	{
		System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s\n", "ProductType", "ProductCode", "Product Name", "Selling Price($)", "Date", "SalesPerson");
		for(Sales sale : salesList) 
		{
			System.out.printf("%-10s%-10s%-10s%-10s%-10s%-10s\n", sale.getProduct().getType(), sale.getProduct().getProductCode(), sale.getProduct().getName(), Double.toString(sale.getProduct().getPrice()), sale.getStrDate(), sale.getEmployee().getName());
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
