package user;

import java.time.LocalDate;
import java.util.ArrayList;

import product.Product;
import staff.Employee;
import transactions.Sales;

public class Trolley {
	private User user;
	private ArrayList<Product> productList;
	private LocalDate Date;
	
	public Trolley(User user, ArrayList<Product> productList, LocalDate Date) //for testing use;
	{
		this.user = user;
		this.productList = productList;
		this.Date = Date;
	}
	
	public Trolley(User user,  LocalDate Date) 
	{
		this.user = user;
		this.productList = new ArrayList<Product>();
		this.Date = Date;
	}
	

	public double calculateTotal(ArrayList<Product> product) 
	{
		double rslt = 0;
		for(Product p: product) 
		{
			rslt += p.getPrice();
		}
		return rslt;
	}
	
	public void createSales(ArrayList<Product> product, Employee e, LocalDate date) 
	{
		for(Product p: product) 
		{
			new Sales(date, e, p);
		}
	}
	
	public User getUser() {
		return user;
	}
	
	public void setUser(User user) {
		this.user = user;
	}
	
	public ArrayList<Product> getProductList() {
		return productList;
	}
	
	public void setProductList(ArrayList<Product> productList) {
		this.productList = productList;
	}
	
	public void addProduct(Product p) {
		this.productList.add(p);
	}
	
	public void removeProduct(Product p) {
		this.productList.remove(p);
	}
	
	public LocalDate getDate() {
		return Date;
	}
	
	public void setDate(LocalDate date) {
		Date = date;
	}

	public boolean checkifEmpty() 
	{
		if(productList.isEmpty())
			return true;
		else
			return false;
	}
}
