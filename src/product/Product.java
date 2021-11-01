package product;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import random.ProductCodeGenerator;
import user.Member;

public class Product {
	private String name;
	private String type;
	private String productCode;
	private double price;
	private int inventory = 0;
	private boolean salesExist;
	protected static ArrayList<Product> productList = new ArrayList<>();
	//test for order search product name
	public static ArrayList<Product> copyList = new ArrayList<>();

	public Product(String name, String type, double price, int inventory) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.inventory = inventory;
		salesExist = false;
		this.productCode = ProductCodeGenerator.generateProductCode(type);
		productList.add(this);
		//test
		copyList.add(this);
	}
	
	public boolean getSalesExist() {
		return salesExist;
	}
	
	public void setSalesExist() {
		salesExist = true;
	}


	public String getName() {
		return name;
	}

	public int getInventory() {
		return inventory;
	}

	public void setInventory(int inventory) {
		this.inventory = inventory;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public static Product searchProduct(String name) {
		for (Product product : productList) {
			if (product.getName().equals(name)) {
				return product;
			}
		}
		return null;
	}

	public int getNumofProduct() 
	{
		int temp = 0;
		for(Product p: productList) 
		{
			if(p.getName().equals(this.getName()) && p.getType().equals(this.getType()))
					temp++;
		}
		return temp;
	}
	
	public boolean checkinventory(int quantity) 
	{
		if(getNumofProduct()>=quantity)
			return true;
		else
			return false;
	}
	
	public static void sortProduct() 
	{
		Collections.sort(productList, (x, y) -> x.type.compareTo(y.type));
	}
	public static void listInventory() 
	{
		sortProduct();
		System.out.printf("%-10s%-10s%-10s%-10f\n","Type","Product Name","Quantity","Marked Price($)/unit");
		for(Product p: productList) 
		{
			System.out.printf("%-10s%-10s%-10s%-10f\n", p.getType(),p.getName(), p.getInventory(), p.getPrice());
		}
	}
	
	
	
	
	
	
	
	
	
	
	// product name should not be the same
	public static boolean checkExistingProduct(String name) {
		for (Product product : productList) {
			if (product.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}

	public static int countProduct() {
		return productList.size();
	}

	public static void removeProduct(String name) {
		for (Product product : productList) {
			if (product.getName().equals(name)) {
				productList.remove(product);
				break;
			}
		}
		// throw exception when product not found
	}
	
	// for order to search
	public static ArrayList<Product> getCopyList() {
		return copyList;
	}

}
