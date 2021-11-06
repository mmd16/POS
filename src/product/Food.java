package product;

import java.time.LocalDate;

public class Food extends Product {
	private String brand;

	public Food(String name, String type, double price, int inventory, LocalDate expireDate, String brand) {
		super(name, "Food", price, inventory, expireDate);
		this.brand = brand;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

}
