package testing;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import product.Product;

class TestProduct {

	@Test
	void testGetExpirationDate() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		assertEquals(LocalDate.parse("2021-12-12"),prod.getExpireDate());
	}
	
	@Test
	void testSetExpirationDate() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		prod.setExpireDate(LocalDate.parse("2021-12-24"));
		assertEquals(LocalDate.parse("2021-12-24"),prod.getExpireDate());
	}
	
	@Test
	void testGetName() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		assertEquals("Chips",prod.getName());
	}
	
	@Test
	void testSetName() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		prod.setName("Crisps");
		assertEquals("Crisps",prod.getName());
	}
	
	@Test
	void testGetInventory() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		assertEquals(50,prod.getInventory());
	}
	
	@Test
	void testSetInventory() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		prod.setInventory(10);
		assertEquals(10,prod.getInventory());
	}
	
	@Test
	void testAddInventory() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		prod.addInventory(10);
		assertEquals(60,prod.getInventory());
	}
	
	@Test
	void testDeductInventory() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		prod.removeInventory(10);
		assertEquals(40,prod.getInventory());
	}
	
	@Test
	void testGetTyps() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		assertEquals("Food",prod.getType());
	}
	
	@Test
	void testSetTyps() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		prod.setType("Snack");
		assertEquals("Snack",prod.getType());
	}
	
	@Test
	void testGetPrice() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		assertEquals(20,prod.getPrice());
	}
	
	@Test
	void testSetPrice() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		prod.setPrice(30);
		assertEquals(30,prod.getPrice());
	}
	
	@Test
	void testProductCode() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		prod.setProductCode("1221");
		assertEquals("1221",prod.getProductCode());
	}
	
	@Test
	void testImportDate() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		prod.setImportDate(LocalDate.parse("2021-10-21"));
		assertEquals(LocalDate.parse("2021-10-21"),prod.getImportDate());
	}
	
	@Test
	void testGetBrand() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		assertEquals("Lais",prod.getBrand());
	}
	
	@Test
	void testSetBrand() {
		Product prod = new Product("Chips", "Food", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		prod.setBrand("Dorminto");
		assertEquals("Dorminto",prod.getBrand());
	}
}
