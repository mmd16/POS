package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import product.Product;
import user.Cart;
import user.CompletedCart;

public class TestCompletedCart {

	@Test
	public void testGetCart() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		CompletedCart complete = new CompletedCart(c, "123", "123");
		assertEquals(c, complete.getCart());
	}
	
	@Test
	public void testSetCart() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		Cart b = new Cart(prod, 10, LocalDate.now());
		CompletedCart complete = new CompletedCart(c, "123", "123");
		complete.setCart(b);
		assertEquals(b, complete.getCart());
	}
	
	@Test
	public void testGetOrderRefNo() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		CompletedCart complete = new CompletedCart(c, "123", "123");
		assertEquals("123", complete.getOrderRefNo());
	}
	
	@Test
	public void testSetOrderRefNo() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		CompletedCart complete = new CompletedCart(c, "123", "123");
		complete.setOrderRefNo("234");
		assertEquals("234", complete.getOrderRefNo());
	}
	
	@Test
	public void testGetSalesCode() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		CompletedCart complete = new CompletedCart(c, "123", "123");
		assertEquals("123", complete.getSalesCode());
	}
	
	@Test
	public void testSetSalesCode() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		Cart c = new Cart(prod, 20, LocalDate.now());
		CompletedCart complete = new CompletedCart(c, "123", "123");
		complete.setSalesCode("234");
		assertEquals("234", complete.getSalesCode());
	}
}
