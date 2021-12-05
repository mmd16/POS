package product;

import java.time.LocalDate;

public class Pharamacy extends Product{

	public Pharamacy(String name, double price, int inventory, LocalDate expireDate, String brand) {
		super(name, "Pharamacy", price, inventory, expireDate, brand);

	}

}
