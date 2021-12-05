package product;

import java.time.LocalDate;

public class CookingIngredients extends Product {

	public CookingIngredients(String name, double price, int inventory, LocalDate expireDate, String brand) {
		super(name, "CookingIngredients", price, inventory, expireDate, brand);

	}

}
