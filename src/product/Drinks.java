package product;

import java.time.LocalDate;

public class Drinks extends Product {
	private String brand;

	public Drinks(String name, String type, double price, int inventory, LocalDate expireDate, String brand)  {
		super(name, type, price, inventory, expireDate);
		this.brand = brand;
	}
	
	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}
