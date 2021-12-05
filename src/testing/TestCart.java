package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import product.Product;
import user.Cart;

public class TestCart {

	@Test
	public void testGetUnitPrice() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		assertEquals(20, c.getUnitPrice(), 0);
	}

	@Test
	public void testSetUnitPrice() {
		Product prod = new Product("Sugars", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		c.setUnitPrice(10);
		assertEquals(10, c.getUnitPrice(), 0);
	}

	@Test
	public void testGetProduct() {
		Product prod = new Product("Chocolate", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		Product prodd = c.getProduct();
		assertEquals(prod, prodd);
	}

	@Test
	public void testSetProduct() {
		Product prod = new Product("Mushroom", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		Product prodd = new Product("candies", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		c.setProduct(prodd);
		boolean rslt = prod.equals(c.getProduct());
		assertEquals(false, rslt);
	}

	@Test
	public void testGetProductName() {
		Product prod = new Product("Biscuit", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		assertEquals("Biscuit", c.getProductName());
	}

	@Test
	public void testSetProductName() {
		Product prod = new Product("Flower Seeds", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		c.setProductName("Bye");
		assertEquals("Bye", c.getProductName());
	}

	@Test
	public void testGetProductCode() {
		Product prod = new Product("M&M", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		c.setProductCode("123");
		assertEquals("123", c.getProductCode());
	}

	@Test
	public void testSetProductCode() {
		Product prod = new Product("Minons", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		c.setProductCode("1234");
		assertEquals("1234", c.getProductCode());
	}

	@Test
	public void testGetQuantity() {
		Product prod = new Product("Bee", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		assertEquals(20, c.getQuantity());
	}

	@Test
	public void testSetQuantity() {
		Product prod = new Product("Saddie", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		c.setQuantity(10);
		assertEquals(10, c.getQuantity());
	}

	@Test
	public void testDeductQuantity() {
		Product prod = new Product("Haoot", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		c.deductQuantity(10);
		assertEquals(10, c.getQuantity());
	}

	@Test
	public void testGetAllPrice() {
		Product prod = new Product("Happy", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		assertEquals(400, c.getAllPrice(), 0);
	}
	
	@Test
	public void testSetAllPrice() {
		Product prod = new Product("Sad", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		c.setAllPrice(100);
		assertEquals(100, c.getAllPrice(), 0);
	}
	
	@Test
	public void testGetDate() {
		Product prod = new Product("Cry", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		assertEquals(LocalDate.now(), c.getDate());
	}

	@Test
	public void testSetDate() {
		Product prod = new Product("Alpha", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		LocalDate date = LocalDate.parse("2021-12-20");
		c.setDate(date);
		assertEquals(date, c.getDate());
	}
}
