package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.text.ParseException;
import java.time.LocalDate;

import org.junit.Test;

import System.CheckoutSystem;
import product.Product;
import product.ProductFactory;
import staff.Employee;
import staff.Manager;
import transactions.Sales;
import user.Cart;
import user.CompletedCart;
import user.Member;

public class TestCheckoutSystem {

	@Test
	public void testValidator_1() {
		Manager a = new Manager("ON9", "M", "null", "123123123", "1");
		boolean rslt = CheckoutSystem.validator(a);
		assertEquals(true, rslt);
	}

	@Test
	public void testValidator_2() {
		Employee b = new Employee("ON9", "M", "null", "123123123", "1");
		boolean rslt = CheckoutSystem.validator(b);
		assertEquals(false, rslt);
	}

	@Test
	public void testSearchHistoryForRefund_1() throws ParseException {
		Member aero = new Member("aero", "2001", "M", "7HEAD", "123");
		ProductFactory productFactory = ProductFactory.getInstance();
		Product p = productFactory.createProduct("candies", "Food", 30.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Cart c = new Cart(p, 10, LocalDate.now());
		CompletedCart cf = new CompletedCart(c, "0", "s123");
		aero.addProductToCompletedCart(c, "0", "s123");
		CompletedCart complete = CheckoutSystem.searchHistoryForRefund("0", "candies", "Food", 1, aero);
		assertEquals(cf.getOrderRefNo(), complete.getOrderRefNo());
	}

	@Test
	public void testSearchHistoryForRefund_2() throws ParseException {
		Member aero = new Member("aero", "2001", "M", "7HEAD", "123");
		ProductFactory productFactory = ProductFactory.getInstance();
		Product p = productFactory.createProduct("candies", "Food", 30.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Cart c = new Cart(p, 10, LocalDate.now());
		new CompletedCart(c, "0", "s123");
		aero.addProductToCompletedCart(c, "0", "s123");
		CompletedCart complete = CheckoutSystem.searchHistoryForRefund("0", "candies", "pp", 1, aero);
		assertEquals(null, complete);
	}

	@Test
	public void testSearchHistoryForRefund_3() throws ParseException {
		Member aero = new Member("aero", "2001", "M", "7HEAD", "123");
		ProductFactory productFactory = ProductFactory.getInstance();
		Product p = productFactory.createProduct("candies", "Food", 30.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Cart c = new Cart(p, 10, LocalDate.now());
		new CompletedCart(c, "0", "s123");
		aero.addProductToCompletedCart(c, "0", "s123");
		CompletedCart complete = CheckoutSystem.searchHistoryForRefund("0", "candie", "Food", 1, aero);
		assertEquals(null, complete);
	}

	@Test
	public void testSearchHistoryForRefund_4() throws ParseException {
		Member aero = new Member("aero", "2001", "M", "7HEAD", "123");
		ProductFactory productFactory = ProductFactory.getInstance();
		Product p = productFactory.createProduct("candies", "Food", 30.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Cart c = new Cart(p, 10, LocalDate.now());
		new CompletedCart(c, "0", "s123");
		aero.addProductToCompletedCart(c, "0", "s123");
		CompletedCart complete = CheckoutSystem.searchHistoryForRefund("1", "candies", "Food", 1, aero);
		assertEquals(null, complete);
	}

	@Test
	public void testSearchHistoryForRefund_6() throws ParseException {
		Member aero = new Member("aero", "2001", "M", "7HEAD", "123");
		CompletedCart complete = CheckoutSystem.searchHistoryForRefund("0", "candies", "Food", 1, aero);
		assertEquals(null, complete);
	}

	@Test
	public void testSearchSales_1() throws ParseException {
		ProductFactory productFactory = ProductFactory.getInstance();
		Product p = productFactory.createProduct("candies", "Food", 30.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Manager e = new Manager("ON9", "M", "null", "123123123", "1");
		Sales s1 = new Sales(p, 20, LocalDate.now(), e, 30.0, 30.0, "0");
		Sales s2 = Sales.searchSales("0");
		assertEquals("0", s2.getOrderRefNo());
	}
	
	@Test
	public void testSearchSales_2() throws ParseException {
		ProductFactory productFactory = ProductFactory.getInstance();
		Product p = productFactory.createProduct("candies", "Food", 30.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Manager e = new Manager("ON9", "M", "null", "123123123", "1");
		new Sales(p, 20, LocalDate.now(), e, 30.0, 30.0, "0");
		Sales s3 = Sales.searchSales("123123");
		assertEquals(null, s3);
	}
	
	@Test
	public void testSearchSales_3() throws ParseException {
		Sales s4 = Sales.searchSales("123123");
		assertEquals(null, s4);
	}
	
	@Test
	public void testSalesDeductQuantity() throws ParseException {
		ProductFactory productFactory = ProductFactory.getInstance();
		Product p = productFactory.createProduct("candies", "Food", 30.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Manager e = new Manager("ON9", "M", "null", "123123123", "1");
		Sales s5 = new Sales(p, 20, LocalDate.now(), e, 30.0, 30.0, "0");
		s5.deductQuantity(10);
		assertEquals(10, s5.getQuantity());
	}
	
	@Test
	public void testAdjustMarkedPrice() throws ParseException {
		ProductFactory productFactory = ProductFactory.getInstance();
		Product p = productFactory.createProduct("candies", "Food", 1, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Manager e = new Manager("ON9", "M", "null", "123123123", "1");
		Sales s6 = new Sales(p, 20, LocalDate.now(), e, 1.0, 1.0, "0");
		s6.deductQuantity(10);
		s6.adjustMarkedPrice();
		assertEquals(10, s6.getMarkedPrice());
	}

	@Test
	public void testAdjustSellingPrice() throws ParseException {
		ProductFactory productFactory = ProductFactory.getInstance();
		Product p = productFactory.createProduct("candies", "Food", 1, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Manager e = new Manager("ON9", "M", "null", "123123123", "1");
		Sales s7 = new Sales(p, 20, LocalDate.now(), e, 1.0, 1.0, "0");
		s7.deductQuantity(10);
		s7.adjustSellingPrice(2);
		assertEquals(20, s7.getSellingPrice());
	}
	
	@Test
	public void testRemoveSales() throws ParseException {
		ProductFactory productFactory = ProductFactory.getInstance();
		Product p = productFactory.createProduct("candies", "Food", 1, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Manager e = new Manager("ON9", "M", "null", "123123123", "1");
		Sales s = new Sales(p, 20, LocalDate.now(), e, 1.0, 1.0, "1234");
		Sales.removeSales(s);
		Sales rslt = Sales.searchSales("1234");
		assertEquals(null, rslt);
	}
	
	@Test
	public void testProductRemoveSales() throws ParseException {
		ProductFactory productFactory = ProductFactory.getInstance();
		Product p = productFactory.createProduct("candies", "Food", 1, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Manager e = new Manager("ON9", "M", "null", "123123123", "1");
		Sales s = new Sales(p, 20, LocalDate.now(), e, 1.0, 1.0, "0");
		p.addSales(s);
		p.removeSales(s);
		boolean rslt = p.checkSalesIsEmpty();
		assertEquals(true, rslt);
	}
//	@Test
//	public void testRefund() throws ParseException {
//		Member aero = new Member("aero", "2001", "M", "7HEAD", "123");
//		ProductFactory productFactory = ProductFactory.getInstance();
//		Product p = productFactory.createProduct("candies", "Food", 30.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
//		Cart c = new Cart(p, 10, LocalDate.now());
//		CompletedCart cf = new CompletedCart(c, "0", "s123");
//		aero.addProductToCompletedCart(c, "0", "s123");
//	};

}
