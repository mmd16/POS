package product;

import java.util.Date;

public class Food extends Product {
	private Date expireDate;
	private String brand;
	
	public Food (String name, String description, String brand, String productCode, Date expireDate, double price) {
		super(name, description, "Food", price);
		this.expireDate = expireDate;
		this.brand = brand;
		productList.add(this);
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}






}
