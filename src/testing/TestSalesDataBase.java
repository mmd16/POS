package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.time.LocalDate;

import org.junit.Before;
import org.junit.jupiter.api.Test;

import db.SalesDataBase;
import product.Product;
import staff.Employee;
import transactions.Sales;

public class TestSalesDataBase {

	private SalesDataBase salesDB = SalesDataBase.getInstance();

	@Test
	public void testGetTotalSalesNum() {
		setup();
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.now(), emp, 40.0, 20.0, "0001");
		salesDB.add(sale);
		assertEquals(1, salesDB.getTotalSalesNum());
	}

	@Test
	public void testSortSales() {
		setup();
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-11-30"), emp, 40.0, 20.0, "0001");
		Sales sale2 = new Sales(prod, 1, LocalDate.parse("2021-12-01"), emp, 40.0, 20.0, "0002");
		salesDB.add(sale2);
		salesDB.add(sale);
		salesDB.sortSales();
		assertEquals(sale, salesDB.getSalesbyPosition(0));
	}

	@Test
	public void testSeachSales_0() {
		setup();
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-11-30"), emp, 40.0, 20.0, "0001");
		salesDB.add(sale);
		assertEquals(sale, salesDB.searchSales(sale.getSalesCode()));
	}

	@Test
	public void testSeachSales_1() {
		setup();
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-11-30"), emp, 40.0, 20.0, "0001");
		salesDB.add(sale);
		assertEquals(null, salesDB.searchSales("123"));
	}

	@Test
	public void testGetSalesByOrderRefNo_0() {
		setup();
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-11-30"), emp, 40.0, 20.0, "0001");
		salesDB.add(sale);
		assertEquals(sale, salesDB.getSalesByOrderRefNo(sale.getOrderRefNo()));
	}

	@Test
	public void testGetSalesByOrderRefNo_1() {
		setup();
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-11-30"), emp, 40.0, 20.0, "0001");
		salesDB.add(sale);
		assertEquals(null, salesDB.getSalesByOrderRefNo("123"));
	}

	@Test
	public void testCheckSalesIsEmpty() {
		setup();
		assertEquals(true, salesDB.checkSalesIsEmpty());
	}

	@Test
	public void testGetTotalRevenue_0() {
		setup();
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-10-30"), emp, 40.0, 20.0, "0001");
		salesDB.add(sale);
		assertEquals(0, salesDB.getTotalRevenue(LocalDate.now(), 0));
	}

	@Test
	public void testGetTotalRevenue_1() {
		setup();
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.now(), emp, 40.0, 20.0, "0001");
		salesDB.add(sale);
		assertEquals(20, salesDB.getTotalRevenue(LocalDate.now(), 0));
	}

	@Test
	public void testGetTotalRevenue_2() {
		setup();
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-11-30"), emp, 40.0, 20.0, "0001");
		salesDB.add(sale);
		assertEquals(20, salesDB.getTotalRevenue(LocalDate.now(), 1));
	}

	@Test
	public void testGetTotalRevenue_3() {
		setup();
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-11-30"), emp, 40.0, 20.0, "0001");
		salesDB.add(sale);
		assertEquals(20, salesDB.getTotalRevenue(LocalDate.now(), 2));
	}

	@Test
	public void testGetTotalRevenue_4() {
		setup();
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2023-11-30"), emp, 40.0, 20.0, "0001");
		salesDB.add(sale);
		assertEquals(0, salesDB.getTotalRevenue(LocalDate.parse("2021-11-30"), 2));
	}
	
	@Test
	public void testGetTotalRevenue_5() {
		setup();
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-11-30") , emp, 40.0, 20.0, "000");
		salesDB.add(sale);
		assertEquals(0, salesDB.getTotalRevenue(LocalDate.parse("2021-12-30"), 1));
	}
	
	@Test
	public void testGetTotalRevenue_6() {
		setup();
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-11-30") , emp, 40.0, 20.0, "000");
		salesDB.add(sale);
		assertEquals(0, salesDB.getTotalRevenue(LocalDate.parse("2022-11-30"), 1));
	}
	
	@Test
	public void testGetTotalRevenue_7() {
		setup();
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-11-30") , emp, 40.0, 20.0, "000");
		salesDB.add(sale);
		assertEquals(0, salesDB.getTotalRevenue(LocalDate.parse("2022-11-30"), 2));
	}
	
	@Test
	public void testGetTotalRevenue_8() {
		setup();
		assertEquals(0, salesDB.getTotalRevenue(LocalDate.parse("2022-11-30"), -1));
	}
	
	@Test
	public void testRemoveSales() {
		setup();
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-11-30") , emp, 40.0, 20.0, "000");
		salesDB.add(sale);
		salesDB.remove(sale);
		assertEquals(true, salesDB.checkSalesIsEmpty());
	}
	
	@Test
	public void testAddSales() {
		setup();
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-11-30") , emp, 40.0, 20.0, "000");
		salesDB.add(sale);
		assertEquals(false, salesDB.checkSalesIsEmpty());
	}
	
	@Test
	public void testListSales_0() throws Exception {
		setup();
		setOutput();
		salesDB.listSales();
		assertEquals("There is no sales yet.\n", getOutput());
	}
	
	@Test
	public void testListSales_1() throws Exception {
		setup();
		setOutput();
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales s = new Sales(prod, 1, LocalDate.parse("2021-11-30") , emp, 40.0, 20.0, "000");
		salesDB.add(s);
		String str1 = String.format("%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s%-20s\n", "No.", "Product Code", "Product Name",
				"Quantity", "Marked Price($)", "Selling Price($)", "Employee", "Sales Code", "Order Ref No");
		String str2 = String.format("%-20d%-20s%-20s%-20s%-20.2f%-20.2f%-20s%-20s%-20s\n", 1, s.getProductCode(),
				s.getProductName(), s.getQuantity(), s.getMarkedPrice(), s.getSellingPrice(),
				s.getEmployee().getName(), s.getSalesCode(), s.getOrderRefNo());
		String rslt = str1 + str2;
		salesDB.listSales();
		assertEquals(rslt, getOutput());
	}
	
	@Before
	public void setup() {
		salesDB.clearSalesList();
	}
	
	PrintStream oldPrintStream;

	ByteArrayOutputStream bos;

	ByteArrayInputStream testIn;

	private void setOutput() throws Exception {
		this.oldPrintStream = System.out;
		this.bos = new ByteArrayOutputStream();
		System.setOut(new PrintStream(this.bos));
	}

	private String getOutput() {
		System.setOut(this.oldPrintStream);
		return this.bos.toString();
	}

}
