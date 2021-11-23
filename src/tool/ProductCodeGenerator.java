package tool;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

import db.InventoryDataBase;
import product.ProductFactory;

public class ProductCodeGenerator {

	public static ProductCodeGenerator instance = new ProductCodeGenerator();

	public static ProductCodeGenerator getInstance() {
		return instance;
	}

	public ProductCodeGenerator() {
	};

	private InventoryDataBase invenDB = InventoryDataBase.getInstance();
	private static AtomicInteger uniqueId = new AtomicInteger();

	public String generateProductCode(String name, String type) {
		if (invenDB.searchProduct(name, type) != null) {
			return invenDB.searchProduct(name, type).getProductCode();
		} else {
			int temp = 0;
			String rslt = "";
			temp = uniqueId.getAndIncrement();
			rslt = type + String.valueOf(temp);
			return rslt;
		}

	}

	public String generateOrderRefNo(LocalDate date) {
		int temp = 0;
		String rslt = "";
		String datestr = date.toString();
		temp = uniqueId.getAndIncrement();
		rslt = datestr + String.valueOf(temp);
		return rslt;
	}

}
