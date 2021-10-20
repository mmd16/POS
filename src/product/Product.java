package product;

import java.util.ArrayList;

import random.productCodeGenerator;
import user.User;

public class Product {
	private String name;
	private String type;	
	private String productCode;
	private double price;
	private int inventory = 0;
	protected static ArrayList<Product> productList = new ArrayList<>();
	
	public Product(String name, String type, double price, int inventory) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.inventory = inventory;
		this.productCode = productCodeGenerator.generateProductCode(type);
		productList.add(this);
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
	
	//product name should not be the same
	public static boolean checkExistingProduct(String name) {
		for (Product product : productList) {
			if (product.getName().equals(name)) {
				return true;
			}
		}
		return false;
	}


	
}
