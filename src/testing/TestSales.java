package testing;

import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.Test;

import product.Product;
import transactions.Sales;
import user.Employee;

class TestSales {
	@Test
	void testGetStrDate() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001");
		assertEquals("21/12/2021" ,sale.getStrDate());
	}
	
	@Test
	void testGetDate() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001");
		assertEquals(LocalDate.parse("2021-12-21") ,sale.getDate());
	}
	
	@Test
	void testSetDate() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001");
		sale.setDate(LocalDate.parse("2021-12-24"));
		assertEquals(LocalDate.parse("2021-12-24") ,sale.getDate());
	}
	
	@Test
	void testGetEmployee() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001");
		assertEquals(emp ,sale.getEmployee());
	}
	
	@Test
	void testSetEmployee() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Employee emp2 = new Employee("James", "M", "James@email.com", "12345679", "1235");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 20.0, "0001");
		sale.setEmployee(emp2);
		assertEquals(emp2 ,sale.getEmployee());
	}
	
	@Test
	void testGetMarkedPrice() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-12-21"), emp, 40.0, 20.0, "0001");
		assertEquals(40.0 ,sale.getMarkedPrice(),0.001);
	}
	
	@Test
	void testSetMarkedPrice() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-12-21"), emp, 40.0, 20.0, "0001");
		sale.setMarkedPrice(60.0);
		assertEquals(60.0 ,sale.getMarkedPrice(),0.001);
	}
	
	@Test
	void testGetSellingPrice() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 40.0, "0001");
		assertEquals(40.0 ,sale.getSellingPrice(),0.001);
	}
	
	@Test
	void testSetSellingPrice() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-12-21"), emp, 20.0, 40.0, "0001");
		sale.setSellingPrice(50.0);
		assertEquals(50.0 ,sale.getSellingPrice(),0.001);
	}
	
	@Test
	void testGetProductName() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-12-21"), emp, 20.00, 20.00, "0001");
		assertEquals("Chips" ,sale.getProductName());
	}
	
	@Test
	void testSetProductName() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-12-21"), emp, 20.00, 20.00, "0001");
		sale.setProductName("Crisps");
		assertEquals("Crisps" ,sale.getProductName());
	}
	
	@Test
	void testProductCode() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-12-21"), emp, 20.00, 20.00, "0001");
		sale.setProductCode("0011");
		assertEquals("0011" ,sale.getProductCode());
	}
	
	@Test
	void testSalesCode() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-12-21"), emp, 20.00, 20.00, "0001");
		sale.setSalesCode("0101");
		assertEquals("0101" ,sale.getSalesCode());
	}
	
	@Test
	void testGetQuantity() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-12-21"), emp, 20.00, 20.00, "0001");
		assertEquals(1 ,sale.getQuantity());
	}
	
	@Test
	void testSetQuantity() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 1, LocalDate.parse("2021-12-21"), emp, 20.00, 20.00, "0001");
		sale.setQuantity(3);
		assertEquals(3 ,sale.getQuantity());
	}
	
	@Test
	void testDeductQuantity() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 3, LocalDate.parse("2021-12-21"), emp, 20.00, 20.00, "0001");
		sale.deductQuantity(2);
		assertEquals(1 ,sale.getQuantity());
	}
	
	@Test
	void testUniqueId() {
		AtomicInteger uniqueId = new AtomicInteger(1132);
		Sales.setUniqueId(uniqueId);
		assertEquals( 1132,Sales.getUniqueId().intValue());
	}
	
	@Test
	void testGetOrderRefNo() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 3, LocalDate.parse("2021-12-21"), emp, 20.00, 20.00, "0001");
		assertEquals("0001" ,sale.getOrderRefNo());
	}
	
	@Test
	void testSetOrderRefNo() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 3, LocalDate.parse("2021-12-21"), emp, 20.00, 20.00, "0001");
		sale.setOrderRefNo("0123");
		assertEquals("0123" ,sale.getOrderRefNo());
	}
	
	@Test
	void testGetProductType() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 3, LocalDate.parse("2021-12-21"), emp, 20.00, 20.00, "0001");
		assertEquals("Food" ,sale.getProductType());
	}
	
	@Test
	void testSetProductType() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 3, LocalDate.parse("2021-12-21"), emp, 20.00, 20.00, "0001");
		sale.setProductType("Snack");
		assertEquals("Snack" ,sale.getProductType());
	}
	
	@Test
	void testGetProduct() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 3, LocalDate.parse("2021-12-21"), emp, 20.00, 20.00, "0001");
		assertEquals(prod ,sale.getProduct());
	}
	
	@Test
	void testSetProduct() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Product prod2 = new Product("Candy", "Food", 13, 100, LocalDate.parse("2021-11-12"), "Lewis");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 3, LocalDate.parse("2021-12-21"), emp, 20.00, 20.00, "0001");
		sale.setProduct(prod2);
		assertEquals(prod2 ,sale.getProduct());
	}
	
	@Test
	void testGetUnitPrice() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 3, LocalDate.parse("2021-12-21"), emp, 20.00, 20.00, "0001");
		assertEquals(20.0 ,sale.getUnitPrice(), 0.001);
	}
	
	@Test
	void testSetUnitPrice() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Employee emp = new Employee("Tom", "M", "Tom@email.com", "12345678", "1234");
		Sales sale = new Sales(prod, 3, LocalDate.parse("2021-12-21"), emp, 20.00, 20.00, "0001");
		sale.setUnitPrice(40.00);
		assertEquals(40.0 ,sale.getUnitPrice(), 0.001);
	}
}
