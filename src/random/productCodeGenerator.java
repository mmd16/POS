package random;

import java.util.concurrent.atomic.AtomicInteger;

public class productCodeGenerator {
	
	public productCodeGenerator() {
	};

	private static AtomicInteger uniqueId = new AtomicInteger();

	public static String generateProductCode(String type) {
		int temp = 0;
		String rslt = "";
		temp = uniqueId.getAndIncrement();
		rslt = type + String.valueOf(temp);
		return rslt;
	}
}
