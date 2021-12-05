package testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import db.SalesDataBase;
import product.Food;
import product.Product;
import transactions.Sales;
import user.Employee;
import user.Manager;

class TestProduct {
	SalesDataBase salesDB = SalesDataBase.getInstance();
	
	@Test
	void testGetExpirationDate() {
		Product prod = new Food("Chips", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		assertEquals(LocalDate.parse("2021-12-12"), prod.getExpireDate());
	}

	@Test
	void testSetExpirationDate() {
		Product prod = new Food("Chips", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		prod.setExpireDate(LocalDate.parse("2021-12-24"));
		assertEquals(LocalDate.parse("2021-12-24"), prod.getExpireDate());
	}

	@Test
	void testGetName() {
		Product prod = new Food("Chips", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		assertEquals("Chips", prod.getName());
	}

	@Test
	void testSetName() {
		Product prod = new Food("Chips", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		prod.setName("Crisps");
		assertEquals("Crisps", prod.getName());
	}

	@Test
	void testGetInventory() {
		Product prod = new Food("Chips", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		assertEquals(50, prod.getInventory());
	}

	@Test
	void testSetInventory() {
		Product prod = new Food("Chips", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		prod.setInventory(10);
		assertEquals(10, prod.getInventory());
	}

	@Test
	void testAddInventory() {
		Product prod = new Food("Chips", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		prod.addInventory(10);
		assertEquals(60, prod.getInventory());
	}

	@Test
	void testDeductInventory() {
		Product prod = new Food("Chips", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		prod.removeInventory(10);
		assertEquals(40, prod.getInventory());
	}

	@Test
	void testGetTyps() {
		Product prod = new Food("Chips", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		assertEquals("Food", prod.getType());
	}

	@Test
	void testSetTyps() {
		Product prod = new Food("Chips", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		prod.setType("Snack");
		assertEquals("Snack", prod.getType());
	}

	@Test
	void testGetPrice() {
		Product prod = new Food("Chips", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		assertEquals(20, prod.getPrice());
	}

	@Test
	void testSetPrice() {
		Product prod = new Food("Chips", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		prod.setPrice(30);
		assertEquals(30, prod.getPrice());
	}

	@Test
	void testProductCode() {
		Product prod = new Food("Chips", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		prod.setProductCode("1221");
		assertEquals("1221", prod.getProductCode());
	}

	@Test
	void testImportDate() {
		Product prod = new Food("Chips", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		prod.setImportDate(LocalDate.parse("2021-10-21"));
		assertEquals(LocalDate.parse("2021-10-21"), prod.getImportDate());
	}

	@Test
	void testGetBrand() {
		Product prod = new Food("Chips", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		assertEquals("Lais", prod.getBrand());
	}

	@Test
	void testSetBrand() {
		Product prod = new Food("Chips", 20, 50, LocalDate.parse("2021-12-12"), "Lais");
		prod.setBrand("Dorminto");
		assertEquals("Dorminto", prod.getBrand());
	}

	@Test
	void testSetSalesList() {
		Product p = new Food("candies", 1.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Product p1 = new Food("candiesPro", 1.0, 200, LocalDate.parse("2026-08-17"), "DajsuPro");
		Manager e = new Manager("ON9", "M", "null", "123123123", "1");
		Sales s = new Sales(p, 20, LocalDate.now(), (Employee) e, 1.0, 1.0, "123321");
		this.salesDB.add(s);
		p.addSales(s);
		p1.setSalesList(p.getSalesList());
		assertEquals(false, p1.checkSalesIsEmpty());
	}

	@Test
	void testGetSalesList() {
		Product p = new Food("candies", 1.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Manager e = new Manager("ON9", "M", "null", "123123123", "1");
		Sales s = new Sales(p, 20, LocalDate.now(), (Employee) e, 1.0, 1.0, "123321");
		this.salesDB.add(s);
		p.addSales(s);
		assertEquals(false, p.getSalesList().isEmpty());
	}

	@Test
	void testAddSales() {
		Product p = new Food("candies", 1.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Manager e = new Manager("ON9", "M", "null", "123123123", "1");
		Sales s = new Sales(p, 20, LocalDate.now(), (Employee) e, 1.0, 1.0, "123321");
		this.salesDB.add(s);
		p.addSales(s);
		assertEquals(false, p.getSalesList().isEmpty());
	}

	@Test
	void testRemoveSales() {
		Product p = new Food("candies", 1.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Manager e = new Manager("ON9", "M", "null", "123123123", "1");
		Sales s = new Sales(p, 20, LocalDate.now(), (Employee) e, 1.0, 1.0, "123321");
		this.salesDB.add(s);
		p.addSales(s);
		p.removeSales(s);
		assertEquals(true, p.getSalesList().isEmpty());
	}

	@Test
	void testAddProducttoQueue() {
		Product p = new Food("candies", 1.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Product p1 = new Food("candies", 1.0, 200, LocalDate.parse("2026-08-15"), "Dajsu");
		p.addProductToQueue(p);
		p.addProductToQueue(p1);
		assertEquals("2026-08-15", p.getProductQueue().peek().getExpireDate().toString());
	}

	@Test
	void testGetProducttoQueue() {
		Product p = new Food("candies", 1.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Product p1 = new Food("candies", 1.0, 200, LocalDate.parse("2026-08-15"), "Dajsu");
		p.addProductToQueue(p);
		p.addProductToQueue(p1);
		assertEquals(false, p.getProductQueue().isEmpty());
	}

	@Test
	void testSetProducttoQueue() {
		Product mainProduct1 = new Food("candies", 1.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Product mainProduct2 = new Food("candies", 1.0, 200, LocalDate.parse("2026-08-17"), "Dajsu");
		Product subProduct = new Product("candies", "Food", 1.0, 200, LocalDate.parse("2026-08-15"), "Dajsu");
		mainProduct1.addProductToQueue(mainProduct1);
		mainProduct1.addProductToQueue(subProduct);
		mainProduct2.setProductQueue(mainProduct1.getProductQueue());
		assertEquals("2026-08-15", mainProduct2.getProductQueue().peek().getExpireDate().toString());
	}
}
