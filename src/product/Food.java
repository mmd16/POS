package product;

import java.util.Date;

public class Food extends Product {
	private Date expireDate;
	private String brand;
	
	public Food (String name, String brand, Date expireDate, double price, int inventory) {
		super(name, "Food", price, inventory);
		this.expireDate = expireDate;
		this.brand = brand;
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
