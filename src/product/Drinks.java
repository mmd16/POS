package product;

import java.time.LocalDate;

public class Drinks extends Product {

	public Drinks(String name, double price, int inventory, LocalDate expireDate, String brand)  {
		super(name, "Drinks", price, inventory, expireDate, brand);
	}
	
}
