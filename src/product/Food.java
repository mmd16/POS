package product;

import java.time.LocalDate;

public class Food extends Product {

	public Food(String name, String type, double price, int inventory, LocalDate expireDate, String brand) {
		super(name, "Food", price, inventory, expireDate, brand);
	}

}
