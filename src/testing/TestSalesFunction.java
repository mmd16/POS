package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import db.InventoryDataBase;
import db.SalesDataBase;
import function.SalesFunction;
import product.Product;
import product.ProductFactory;
import transactions.MemberSale;
import transactions.Sales;
import user.Employee;
import user.Member;

public class TestSalesFunction {
	private SalesFunction sales = new SalesFunction();

	private ProductFactory product = ProductFactory.getInstance();

	private SalesDataBase salesDB = SalesDataBase.getInstance();

	private InventoryDataBase invenDB = InventoryDataBase.getInstance();
	PrintStream oldPrintStream;

	ByteArrayOutputStream bos;

	ByteArrayInputStream testIn;

	@Test
	void testGetTotalSalesNum() {
		setup();
		int rslt = this.sales.getTotalSalesNum();
		assertEquals(0, rslt);
	}

	@Test
	void testCheckForTotalRevenue() {
		setup();
		Product prod = new Product("Chips", "Food", 20.0D, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 3, LocalDate.now(), emp, 60.0, 60.0, "0001");
		this.salesDB.add(sale);
		double rslt = this.sales.getTotalRevenue(LocalDate.now(), 1);
		assertEquals(60.0, rslt, 0.0);
	}

	@Test
	void testCheckIsSalesIsEmpty() {
		setup();
		boolean rslt = this.sales.checkSalesIsEmpty();
		assertEquals(true, rslt);
	}

	@Test
	void testSalesPercentageForProduct_1() throws Exception {
		setOutput();
		setup();
		Product prod = new Product("Chips", "Food", 20.0, 50, LocalDate.parse("2021-12-12"), "Lais");
		double rslt = this.sales.getSalesPercentageForProduct(0, prod, false, 0, 10);
		assertEquals("*** No Sales Exists! ***\n", getOutput());
		assertEquals(0.0, rslt);
	}

	@Test
	void testSalesPercentageForProduct_2() throws Exception {
		setOutput();
		setup();
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com", "1111");
		Product prod = this.product.createProduct("Chips", "Food", 20.0, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(prod, 3, LocalDate.now(), emp, 60.0, 60.0, "0001", mem);
		this.salesDB.add(sale);
		prod.addSales(sale);
		double rslt = this.sales.getSalesPercentageForProduct(1, prod, true, 1, 3);
		assertEquals(100.0, rslt);
	}

	@Test
	void testSalesPercentageForProduct_3() throws Exception {
		setOutput();
		setup();
		Member mem = new Member("John", "2003", "M", "JohnDoe@gmail.com", "1111");
		Product prod = this.product.createProduct("Chips", "Food", 20.0, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(prod, 3, LocalDate.now(), emp, 60.0, 60.0, "0001", mem);
		this.salesDB.add(sale);
		prod.addSales(sale);
		double rslt = this.sales.getSalesPercentageForProduct(1, prod, false, 1, 3);
		assertEquals(100.0, rslt);
	}
	
	@Test
	void testPrintHighestSalesProduct_1() throws Exception {
		setOutput();
		setup();
		Product rslt = this.sales.printHighestSalesProduct(0, false, 0);
		assertEquals("*** No Sales Exists! ***\n", getOutput());
		assertEquals(null, rslt);
	}

	@Test
	void testPrintHighestSalesProduct_2() throws Exception {
		setOutput();
		setup();
		Product prod = this.product.createProduct("Chips(Sour)", "Food", 20.0, 50, LocalDate.parse("2021-12-24"),
				"Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 3, LocalDate.now(), emp, 60.0, 60.0, "0002");
		this.salesDB.add(sale);
		this.invenDB.add(prod);
		prod.addSales(sale);
		Product rslt = this.sales.printHighestSalesProduct(1, false, 0);
		assertEquals(prod, rslt);
	}

	@Test
	void testPrintHighestSalesProduct_3() throws Exception {
		setOutput();
		setup();
		Member mem = new Member("John", "2002", "M", "JohnDoe@gmail.com", "1111");
		Member mem2 = new Member("Johnny", "1980", "M", "JohnDoe@gmail.com", "1112");
		Product prod = this.product.createProduct("Chips(Sour)", "Food", 20.0, 50, LocalDate.parse("2021-12-24"),
				"Lais");
		Product prod2 = this.product.createProduct("Chips(Spicy)", "Food", 20.0, 50, LocalDate.parse("2021-12-24"),
				"Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		MemberSale sale = new MemberSale(prod, 3, LocalDate.now(), emp, 60.0, 60.0, "0002", mem);
		MemberSale sale2 = new MemberSale(prod2, 10, LocalDate.now(), emp, 200.0, 200.0, "0003", mem2);
		this.salesDB.add(sale);
		this.salesDB.add(sale2);
		this.invenDB.add(prod);
		this.invenDB.add(prod2);
		prod.addSales(sale);
		prod2.addSales(sale2);
		Product rslt = this.sales.printHighestSalesProduct(1, true, 1);
		assertEquals(prod, rslt);
	}

	@Test
	void testlistSales() throws Exception {
		setOutput();
		setup();
		this.sales.listSales();
		assertEquals("There is no sales yet.\n", getOutput());
	}

	@Test
	void testGetHighestSalesProductList() {
		setup();
		assertEquals(true ,this.sales.getHighestSalesProductList().isEmpty());
	}

	private void setOutput() throws Exception {
		this.oldPrintStream = System.out;
		this.bos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(this.bos));
	}

	private String getOutput() {
		System.setOut(this.oldPrintStream);
		return this.bos.toString();
	}

	@Before
	public void setup() {
		this.salesDB.clearSalesList();
		this.invenDB.clear();
	}
}
