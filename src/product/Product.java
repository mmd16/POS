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


	public Product(String name, String type, double price, int inventory) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.inventory = inventory;
		salesExist = false;
		this.productCode = ProductCodeGenerator.generateProductCode(type);
		
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

	
	
	
	
	
	
	
	
	
	
	
	

}
