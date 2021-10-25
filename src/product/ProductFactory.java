package product;

import java.util.Date;

public class ProductFactory { 
	public Product createProduct(String type, String name, String brand, Date date, double price, int inventory) {
        switch(type) {
            case "Food":
        		if (Food.searchProduct(name) == null) 
        			return new Food(name, brand, date, price, inventory);
        		else
        			return null;
        			// throw exception
            case "Equipment":
        		if (Equipment.searchProduct(name) == null) 
        			return new Equipment(name, brand, date, price, inventory);
        		else
        			return null;
        			// throw exception
        	default:
        		return null;
        		// don't know what type 
        }
    }
}
