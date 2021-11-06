package random;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

import product.ProductFactory;

public class ProductCodeGenerator {

	public ProductCodeGenerator() {
	};

	private static AtomicInteger uniqueId = new AtomicInteger();

	public static String generateProductCode(String name, String type) {
		if (ProductFactory.checkExistingProduct(name, type)) {
			return ProductFactory.searchProductCodeByNameAndType(name, type).getProductCode();
		} else {
			int temp = 0;
			String rslt = "";
			temp = uniqueId.getAndIncrement();
			rslt = type + String.valueOf(temp);
			return rslt;
		}

	}

	public static String generateOrderRefNo(LocalDate date) {
		int temp = 0;
		String rslt = "";
		String datestr = date.toString();
		temp = uniqueId.getAndIncrement();
		rslt = datestr + String.valueOf(temp);
		return rslt;
	}

}
