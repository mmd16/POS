package product;

import java.util.ArrayList;

import random.productCodeGenerator;

public class Product {
	private String name;
	private String description;
	private String type;	
	private String productCode;
	private double price;
	protected static ArrayList<Product> productList = new ArrayList<>();
	
	public Product(String name, String description, String type, double price) {
		this.name = name;
		this.description = description;
		this.type = type;
		this.price = price;
		this.productCode = productCodeGenerator.generateProductCode(type);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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
