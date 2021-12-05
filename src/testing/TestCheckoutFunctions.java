package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.InputStream;
import java.text.ParseException;
import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import db.SalesDataBase;
import function.CheckoutFunctions;
import product.Product;
import product.ProductFactory;
import transactions.Sales;
import user.Cart;
import user.CompletedCart;
import user.Employee;
import user.Manager;
import user.Member;

public class TestCheckoutFunctions {
	private CheckoutFunctions checkout = new CheckoutFunctions();

	private SalesDataBase salesDB = SalesDataBase.getInstance();

	ProductFactory productFactory = ProductFactory.getInstance();

	final InputStream systemIn = System.in;

	@Test
	public void testValidator_1() {
		Manager a = new Manager("beta", "M", "null", "123123123", "1");
		boolean rslt = this.checkout.validator((Employee) a);
		assertEquals(true, rslt);
	}

	@Test
	public void testValidator_2() {
		Employee b = new Employee("beta", "M", "null", "123123123", "1");
		boolean rslt = this.checkout.validator(b);
		assertEquals(false, rslt);
	}

	@Test
	public void testSearchHistoryForRefund_1() throws ParseException {
		Member aero = new Member("aero", "2001", "M", "7HEAD", "123");
		Product p = new Product("candies", "Food", 30.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Cart c = new Cart(p, 10, LocalDate.now());
		CompletedCart cf = new CompletedCart(c, "0", "s123");
		aero.addProductToCompletedCart(c, "0", "s123");
		CompletedCart complete = this.checkout.searchHistoryForRefund("0", "candies", "Food", 1, aero);
		assertEquals(cf.getOrderRefNo(), complete.getOrderRefNo());
	}

	@Test
	public void testSearchHistoryForRefund_2() throws ParseException {
		Member aero = new Member("aero", "2001", "M", "7HEAD", "123");
		Product p = new Product("candies", "Food", 30.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Cart c = new Cart(p, 10, LocalDate.now());
		aero.addProductToCompletedCart(c, "0", "s123");
		CompletedCart complete = this.checkout.searchHistoryForRefund("0", "candies", "pp", 1, aero);
		assertEquals(null, complete);
	}

	@Test
	public void testSearchHistoryForRefund_3() throws ParseException {
		Member aero = new Member("aero", "2001", "M", "7HEAD", "123");
		Product p = new Product("candies", "Food", 30.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Cart c = new Cart(p, 10, LocalDate.now());
		aero.addProductToCompletedCart(c, "0", "s123");
		CompletedCart complete = this.checkout.searchHistoryForRefund("0", "candie", "Food", 1, aero);
		assertEquals(null, complete);
	}

	@Test
	public void testSearchHistoryForRefund_4() throws ParseException {
		Member aero = new Member("aero", "2001", "M", "7HEAD", "123");
		Product p = new Product("candies", "Food", 30.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Cart c = new Cart(p, 10, LocalDate.now());
		aero.addProductToCompletedCart(c, "0", "s123");
		CompletedCart complete = this.checkout.searchHistoryForRefund("1", "candies", "Food", 1, aero);
		assertEquals(null, complete);
	}

	@Test
	public void testAdjustMarkedPrice() throws ParseException {
		Product p = new Product("candies", "Food", 1.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Manager e = new Manager("ON9", "M", "null", "123123123", "1");
		Sales s6 = new Sales(p, 20, LocalDate.now(), e, 1.0, 1.0, "0");
		s6.deductQuantity(10);
		this.checkout.adjustMarkedPrice(s6);
		assertEquals(10.0, s6.getMarkedPrice(), 0);
	}

	@Test
	public void testAdjustSellingPrice() throws ParseException {
		Product p = new Product("candies", "Food", 1.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Manager e = new Manager("ON9", "M", "null", "123123123", "1");
		Sales s7 = new Sales(p, 20, LocalDate.now(), e, 1.0, 1.0, "0");
		s7.deductQuantity(10);
		this.checkout.adjustSellingPrice(2.0, s7);
		assertEquals(20.0, s7.getSellingPrice(), 0);
	}

	@Test
	public void testcheckForSales_1() throws ParseException {
		Product p = new Product("candies", "Food", 1.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Manager e = new Manager("ON9", "M", "null", "123123123", "1");
		Sales s = new Sales(p, 20, LocalDate.now(), e, 1.0, 1.0, "123321");
		this.salesDB.add(s);
		p.addSales(s);
		this.checkout.checkForSales(s, 20);
		Sales rslt = this.salesDB.getSalesByOrderRefNo("123321");
		assertEquals(null, rslt);
	}

	@Test
	public void testcheckForSales_2() throws ParseException {
		Product p = new Product("candies", "Food", 1.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Manager e = new Manager("ON9", "M", "null", "123123123", "1");
		Sales s = new Sales(p, 20, LocalDate.now(), e, 1.0, 1.0, "123321");
		this.salesDB.add(s);
		p.addSales(s);
		this.checkout.checkForSales(s, 10);
		Sales rslt = this.salesDB.getSalesByOrderRefNo("123321");
		assertEquals(s.getQuantity(), rslt.getQuantity());
	}

	@Test
	public void testCheckForCart_1() throws ParseException {
		Member alpha = new Member("alpha", "2001", "M", "7HEAD", "1619");
		Product p = new Product("candies", "Food", 30.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Cart c = new Cart(p, 10, LocalDate.now());
		CompletedCart complete = alpha.addProductToCompletedCart(c, "0", "s123");
		this.checkout.checkForCart(complete, 10, alpha);
		assertEquals(true, alpha.getCompletedCart().isEmpty());
	}

	@Test
	public void testCheckForCart_2() throws ParseException {
		Member alpha = new Member("alpha", "2001", "M", "7HEAD", "1619");
		Product p = new Product("candies", "Food", 30.0D, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Cart c = new Cart(p, 10, LocalDate.now());
		CompletedCart complete = alpha.addProductToCompletedCart(c, "0", "s123");
		this.checkout.checkForCart(complete, 5, alpha);
		assertEquals(5, c.getQuantity());
	}

	@Test
	public void testConfirmSales_1() throws ParseException {
		Employee b = new Employee("beta", "M", "null", "123123123", "1");
		Member alpha = new Member("alpha", "2001", "M", "7HEAD", "1619");
		Product p = this.productFactory.createProduct("candies", "Food", 30.0D, 200, LocalDate.parse("2026-08-17"),
				"Dajsu");
		Cart c = new Cart(p, 10, LocalDate.now());
		Sales s = this.checkout.confirmSales(c, alpha, "123", b);
		assertEquals("123", s.getOrderRefNo());
	}

	@Test
	public void testConfirmSales_2() throws ParseException {
		Employee b = new Employee("beta", "M", "null", "123123123", "1");
		Member alpha = new Member("alpha", "M");
		Product p = this.productFactory.createProduct("candies", "Food", 30.0D, 200, LocalDate.parse("2026-08-17"),
				"Dajsu");
		Cart c = new Cart(p, 10, LocalDate.now());
		Sales s = this.checkout.confirmSales(c, alpha, "123", b);
		assertEquals("123", s.getOrderRefNo());
	}

	@Test
	public void testCheckout() throws ParseException {
		Employee b = new Employee("beta", "M", "null", "123123123", "1");
		Member alpha = new Member("alpha", "M");
		Product p = this.productFactory.createProduct("candies", "Food", 30.0, 200, LocalDate.parse("2026-08-17"),
				"Dajsu");
		alpha.addProductToCart(p, 10, LocalDate.now());
		this.checkout.checkout(alpha, b);
		assertEquals(true, alpha.getCart().isEmpty());
	}

	@Test
	public void testRefund_1() {
		Product p = new Product("candies", "Food", 1.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Member alpha = new Member("alpha", "2001", "M", "7HEAD", "1619");
		Manager e = new Manager("ON9", "M", "null", "123123123", "1");
		Sales s = new Sales(p, 20, LocalDate.now(), (Employee) e, 1.0, 1.0, "1233a1");
		Cart c = new Cart(p, 20, LocalDate.now());
		this.salesDB.add(s);
		p.addSales(s);
		CompletedCart complete = alpha.addProductToCompletedCart(c, s.getOrderRefNo(), s.getSalesCode());
		this.checkout.refund(complete, 1, e, alpha);
		assertEquals(19, complete.getCart().getQuantity());
	}

	@Test
	public void testRefund_2() {
		Product p = new Product("candies", "Food", 1.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Manager e = new Manager("ON9", "M", "null", "123123123", "1");
		Sales s = new Sales(p, 20, LocalDate.now(), (Employee) e, 1.0, 1.0, "1233a2");
		new Cart(p, 20, LocalDate.now());
		this.salesDB.add(s);
		p.addSales(s);
		this.checkout.refund(s.getOrderRefNo(), 20, e);
		assertEquals(null, this.salesDB.getSalesByOrderRefNo(s.getOrderRefNo()));
	}

	@Test
	public void testUpdateCart() throws ParseException {
		Member finala = new Member("finala", "2001", "M", "7HEAD", "16191817");
		Product p = this.productFactory.createProduct("candies", "Food", 30.0, 200, LocalDate.parse("2026-08-17"),
				"Dajsu");
		Cart c = finala.addProductToCart(p, 100, LocalDate.now());
		this.checkout.updateCart(c, 5);
		assertEquals(5, c.getQuantity());
	}

	@Test
	public void testRemoveProductInCart() throws ParseException {
		Member finala = new Member("finala", "2001", "M", "7HEAD", "16191817");
		Product p = this.productFactory.createProduct("candies", "Food", 30.0, 200, LocalDate.parse("2026-08-17"),
				"Dajsu");
		Cart c = finala.addProductToCart(p, 100, LocalDate.now());
		this.checkout.removeProductInCart(finala, c);
		assertEquals(true, finala.getCart().isEmpty());
	}

	@Test
	public void testchangeForPayment_0() {
		boolean rslt = this.checkout.changeForthePayment(100.0, 100.0);
		assertEquals(true, rslt);
	}

	@Test
	public void testchangeForPayment_1() {
		boolean rslt = this.checkout.changeForthePayment(200.0, 100.0);
		assertEquals(false, rslt);
	}

	@Test
	public void testapplydiscount() {
		Member member = new Member("finala", "2001", "M", "7HEAD", "16191817");
		double rslt = this.checkout.applyDiscount(100.0, member);
		assertEquals(95.0, rslt, 0);
	}

	@Test
	public void testCountTotalPrice() throws ParseException {
		Member member = new Member("finala", "2001", "M", "7HEAD", "16191817");
		Product p = this.productFactory.createProduct("candies", "Food", 30.0, 200, LocalDate.parse("2026-08-17"),
				"Dajsu");
		member.addProductToCart(p, 1, LocalDate.now());
		assertEquals(30.0, this.checkout.countTotalPrice(member.getCart()), 0);
	}

	@Test
	public void testCountFinalPrice() throws ParseException {
		Member member = new Member("finala", "2001", "M", "7HEAD", "16191817");
		Product p = this.productFactory.createProduct("candies", "Food", 30.0, 200, LocalDate.parse("2026-08-17"),
				"Dajsu");
		member.addProductToCart(p, 1, LocalDate.now());
		assertEquals(28.5, this.checkout.countFinalPrice(member));
	}

	@Test
	public void testRemoveCartAfterTransactions() throws ParseException {
		Member member = new Member("baron", "M");
		Product p = this.productFactory.createProduct("candies", "Food", 30.0, 200, LocalDate.parse("2026-08-17"),
				"Dajsu");
		member.addProductToCart(p, 1, LocalDate.now());
		this.checkout.removeCartAfterTransaction(member);
		assertEquals(true, member.getCart().isEmpty());
	}

	@Test
	public void testCountPoints_1() {
		Member member = new Member("baron", "M");
		int rslt = this.checkout.countPoints(100.0, member);
		assertEquals(0, rslt);
	}

	@Test
	public void testCountPoints_2() {
		Member finala = new Member("finala", "2001", "M", "7HEAD", "16191817");
		int rslt = this.checkout.countPoints(100.0, finala);
		assertEquals(1, rslt);
	}

	@Test
	public void testCountPoints_3() {
		Member finala = new Member("finala", "2001", "M", "7HEAD", "16191817");
		int rslt = this.checkout.countPoints(99.0, finala);
		assertEquals(0, rslt);
	}
	
	@Test
	public void testSearchCart_1() throws ParseException {
		Member member = new Member("finala", "2001", "M", "7HEAD", "16191817");
		Product p = this.productFactory.createProduct("candies", "Food", 30.0, 200, LocalDate.parse("2026-08-17"),
				"Dajsu");
		Cart c = member.addProductToCart(p, 1, LocalDate.now());
		assertEquals(c, this.checkout.SearchCart(member, "candies"));
	}
	
	@Test
	public void testSearchCart_2() throws ParseException {
		Member member = new Member("finala", "2001", "M", "7HEAD", "16191817");
		assertEquals(null, this.checkout.SearchCart(member, "candies"));
	}
	
	@Test
	public void testSearchCart_3() throws ParseException {
		Member member = new Member("finala", "2001", "M", "7HEAD", "16191817");
		Product p = this.productFactory.createProduct("candies", "Food", 30.0, 200, LocalDate.parse("2026-08-17"),
				"Dajsu");
		member.addProductToCart(p, 1, LocalDate.now());
		assertEquals(null, this.checkout.SearchCart(member, "sugar"));
	}
	
}
