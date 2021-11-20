package user;

import java.time.LocalDate;
import java.util.ArrayList;

import product.Product;

public class Cart {
	private Product product;
	private String productName;
	private String productCode;
	private int quantity;
	private double allPrice;
	private LocalDate Date;
	private double unitPrice;
	
	public Cart(Product product, int quantity, LocalDate Date) {
		this.product = product;
		this.productName = product.getName();
		this.productCode = product.getProductCode();
		this.quantity = quantity;
		this.Date = Date;
		this.allPrice = product.getPrice() * quantity;
		this.unitPrice = product.getPrice();
	}

	public static double countTotal(ArrayList<Cart> cartList) {
		double rslt = 0;
		for (Cart cart : cartList) {
			rslt += cart.getAllPrice();
		}
		return rslt;
	}
	
	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public void deductQuantity(int quantity) {
		this.quantity -= quantity;
	}

	public double getAllPrice() {
		return allPrice;
	}

	public void setAllPrice(double allPrice) {
		this.allPrice = allPrice;
	}

	public LocalDate getDate() {
		return Date;
	}

	public void setDate(LocalDate date) {
		Date = date;
	}

}
