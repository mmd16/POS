package function;

import java.text.ParseException;
import java.time.LocalDate;

import db.InventoryDataBase;
import product.Product;
import product.ProductFactory;

public class InventoryFunctions {
	private ProductFactory productFactory = ProductFactory.getInstance();
	private InventoryDataBase invenDB = InventoryDataBase.getInstance();
	public InventoryFunctions() {
	};

	public Product createProduct(String productName, String productType, double markedPrice, int quantity,
			String productDate, String productBrand) throws ParseException {
		return productFactory.createProduct(productName, productType, markedPrice, quantity, LocalDate.parse(productDate),
				productBrand);
	}
	
	public Product removeProduct(String productName, String productType) {
		Product p = invenDB.searchProduct(productName, productType);
		invenDB.remove(p);
		return p;
	}

	public void listInventory() {
		invenDB.listInventory();
	}
}
