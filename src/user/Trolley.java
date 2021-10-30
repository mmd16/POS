package user;

import java.time.LocalDate;
import java.util.ArrayList;

import product.Product;
import staff.Employee;
import transactions.Sales;

public class Trolley {
	private String productName;
	private int itemNum;
	private double allPrice;
	private static ArrayList<Trolley> trolleyList = new ArrayList<>();
	private LocalDate Date;
	
	public Trolley(Product product, int itemNum, LocalDate Date) //Improving
	{
		this.productName = product.getName();
		this.itemNum = itemNum;
		this.Date = Date;
		this.allPrice = product.getPrice() * itemNum;
	}

	public static double calculateTotal(ArrayList<Trolley> trolleyList) 
	{
		double rslt = 0;
		for(Trolley t: trolleyList) 
		{
			rslt += t.getAllPrice();
		}
		return rslt;
	}
	
	public static ArrayList<Trolley> getTrolleyList() {
		return trolleyList;
	}
	
	public static void clearTrolley() {
		trolleyList.clear();
	}
	
	public void addProduct(Trolley t) {
		trolleyList.add(t);
	}
	
	public String getProductName() {
		return productName;
	}
	
	public int getItemNum() {
		return itemNum;
	}
	
	public LocalDate getDate() {
		return Date;
	}
	
	public double getAllPrice() {
		return allPrice;
	}
	
	public void deductInventory(Product product) {
		int result = product.getInventory() - this.getItemNum();
		product.setInventory(result);
	}

	public static boolean checkInventory(Product product, int items) 
	{
		if(product.getInventory() < items)
			return true;
		else
			return false;
	}
	
	public static boolean checkTrolley() 
	{
		if(trolleyList.isEmpty())
			return true;
		else
			return false;
	}
}
