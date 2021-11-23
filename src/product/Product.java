package product;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

import ageGroup.AgeGroup;
import tool.ProductCodeGenerator;
import transactions.MemberSale;
import transactions.Sales;

public class Product {
	private String name;
	private String type;
	private String productCode;
	private String brand;
	private double price;
	private int inventory = 0;
	private LocalDate expireDate;
	private LocalDate importDate;
	private ArrayList<Sales> salesList;
	private PriorityQueue<Product> productQueue;
	private ProductCodeGenerator codeGen = ProductCodeGenerator.getInstance();
	public Product(String name, String type, double price, int inventory, LocalDate expireDate, String brand) {
		this.name = name;
		this.type = type;
		this.price = price;
		this.brand = brand;
		this.inventory = inventory;
		this.importDate = LocalDate.now();
		this.expireDate = expireDate;
		this.productCode = codeGen.generateProductCode(name, type);
		this.salesList = new ArrayList<>();
		this.productQueue = new PriorityQueue<Product>(new Comparator<Product>() {
			@Override
			public int compare(Product p1, Product p2) {
				LocalDate d1 = p1.getExpireDate();
				LocalDate d2 = p2.getExpireDate();
				return d1.compareTo(d2);
			}
		});
	}


	public ArrayList<Sales> getSalesList() {
		return salesList;
	}

	public void setSalesList(ArrayList<Sales> salesList) {
		this.salesList = salesList;
	}

	public void addSales(Sales s) {
		this.salesList.add(s);
	}

	public void removeSales(Sales s) {
		this.salesList.remove(s);
	}

	public void addProductToQueue(Product p) {
		productQueue.add(p);
	}

	public LocalDate getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(LocalDate expireDate) {
		this.expireDate = expireDate;
	}

	public PriorityQueue<Product> getProductQueue() {
		return productQueue;
	}

	public void setProductQueue(PriorityQueue<Product> productQueue) {
		this.productQueue = productQueue;
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

	public void addInventory(int inventory) {
		this.inventory += inventory;
	}

	public void removeInventory(int inventory) {
		this.inventory -= inventory;
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

	public LocalDate getImportDate() {
		return importDate;
	}

	public void setImportDate(LocalDate importDate) {
		this.importDate = importDate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public boolean checkSalesIsEmpty() {
		return this.salesList.isEmpty();
	}
}
